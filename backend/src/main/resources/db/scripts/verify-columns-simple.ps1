# Script de Verificacao Detalhada de Colunas - Versao Simples
param(
    [string]$BackupFile = "generated-changelog.xml.backup",
    [string]$OutputDir = "verification-reports"
)

Write-Host "=== VERIFICACAO DETALHADA DE COLUNAS ===" -ForegroundColor Cyan

# Criar diretorio de saida
if (!(Test-Path $OutputDir)) {
    New-Item -ItemType Directory -Path $OutputDir -Force | Out-Null
}

# Funcao para extrair colunas de uma tabela
function Get-TableColumns {
    param([string]$FilePath, [string]$TableName)
    
    if (!(Test-Path $FilePath)) {
        return @()
    }
    
    $content = Get-Content $FilePath -Raw
    $tablePattern = "<createTable.*?tableName=`"$TableName`".*?>(.*?)</createTable>"
    $tableMatch = [regex]::Match($content, $tablePattern, [System.Text.RegularExpressions.RegexOptions]::Singleline)
    
    if (!$tableMatch.Success) {
        return @()
    }
    
    $tableContent = $tableMatch.Groups[1].Value
    $columnPattern = '<column\s+([^>]*?)(?:/>|>(.*?)</column>)'
    $columnMatches = [regex]::Matches($tableContent, $columnPattern, [System.Text.RegularExpressions.RegexOptions]::Singleline)
    
    $columns = @()
    foreach ($match in $columnMatches) {
        $columnDef = $match.Groups[1].Value
        
        # Extrair nome
        $name = ""
        if ($columnDef -match 'name="([^"]+)"') { $name = $matches[1] }
        
        # Extrair tipo
        $type = ""
        if ($columnDef -match 'type="([^"]+)"') { $type = $matches[1] }
        
        # Extrair propriedades
        $autoIncrement = $columnDef -match 'autoIncrement="true"'
        $nullable = !($columnDef -match 'nullable="false"')
        $primaryKey = $columnDef -match 'primaryKey="true"'
        
        # Extrair default values
        $defaultValue = ""
        if ($columnDef -match 'defaultValueNumeric="([^"]+)"') { $defaultValue = "numeric:" + $matches[1] }
        elseif ($columnDef -match 'defaultValueBoolean="([^"]+)"') { $defaultValue = "boolean:" + $matches[1] }
        elseif ($columnDef -match 'defaultValueComputed="([^"]+)"') { $defaultValue = "computed:" + $matches[1] }
        elseif ($columnDef -match 'defaultValue="([^"]+)"') { $defaultValue = "string:" + $matches[1] }
        
        $columns += [PSCustomObject]@{
            Name = $name
            Type = $type
            Nullable = $nullable
            PrimaryKey = $primaryKey
            AutoIncrement = $autoIncrement
            DefaultValue = $defaultValue
            FullDef = $match.Groups[0].Value
        }
    }
    
    return $columns
}

# Funcao para comparar colunas
function Compare-Columns {
    param($BackupCols, $ChangelogCols, $TableName, $ChangelogFile)
    
    $issues = @()
    $correctCols = 0
    
    # Verificar se numero de colunas e igual
    if ($BackupCols.Count -ne $ChangelogCols.Count) {
        $issues += "Numero de colunas diferente: Backup=" + $BackupCols.Count + ", Changelog=" + $ChangelogCols.Count
    }
    
    # Verificar cada coluna do backup
    foreach ($backupCol in $BackupCols) {
        $changelogCol = $ChangelogCols | Where-Object { $_.Name -eq $backupCol.Name }
        
        if (!$changelogCol) {
            $issues += "Coluna faltando: '" + $backupCol.Name + "'"
            continue
        }
        
        $colIssues = @()
        
        if ($changelogCol.Type -ne $backupCol.Type) {
            $colIssues += "tipo diferente (backup: '" + $backupCol.Type + "', changelog: '" + $changelogCol.Type + "')"
        }
        
        if ($changelogCol.Nullable -ne $backupCol.Nullable) {
            $colIssues += "nullable diferente (backup: " + $backupCol.Nullable + ", changelog: " + $changelogCol.Nullable + ")"
        }
        
        if ($changelogCol.PrimaryKey -ne $backupCol.PrimaryKey) {
            $colIssues += "primaryKey diferente (backup: " + $backupCol.PrimaryKey + ", changelog: " + $changelogCol.PrimaryKey + ")"
        }
        
        if ($changelogCol.AutoIncrement -ne $backupCol.AutoIncrement) {
            $colIssues += "autoIncrement diferente (backup: " + $backupCol.AutoIncrement + ", changelog: " + $changelogCol.AutoIncrement + ")"
        }
        
        if ($changelogCol.DefaultValue -ne $backupCol.DefaultValue) {
            $colIssues += "defaultValue diferente (backup: '" + $backupCol.DefaultValue + "', changelog: '" + $changelogCol.DefaultValue + "')"
        }
        
        if ($colIssues.Count -gt 0) {
            $issues += "Coluna '" + $backupCol.Name + "': " + ($colIssues -join ', ')
        } else {
            $correctCols++
        }
    }
    
    # Verificar colunas extras no changelog
    foreach ($changelogCol in $ChangelogCols) {
        $backupCol = $BackupCols | Where-Object { $_.Name -eq $changelogCol.Name }
        if (!$backupCol) {
            $issues += "Coluna extra: '" + $changelogCol.Name + "' (nao existe no backup)"
        }
    }
    
    return @{
        Issues = $issues
        CorrectColumns = $correctCols
        TotalBackupColumns = $BackupCols.Count
        TotalChangelogColumns = $ChangelogCols.Count
    }
}

# Extrair tabelas do backup
Write-Host "Extraindo tabelas do backup..." -ForegroundColor Yellow

$content = Get-Content $BackupFile -Raw
$tablePattern = '<createTable.*?tableName="([^"]+)"'
$tableMatches = [regex]::Matches($content, $tablePattern)

$backupTableColumns = @{}
foreach ($match in $tableMatches) {
    $tableName = $match.Groups[1].Value
    $columns = Get-TableColumns $BackupFile $tableName
    $backupTableColumns[$tableName] = $columns
    Write-Host ("  " + $tableName + ": " + $columns.Count + " colunas") -ForegroundColor Gray
}

# Mapeamento de changelogs
$changelogMapping = @{
    "002-auth-changelog.xml" = @("user", "authorities", "user_authority")
    "003-core-ficha-changelog.xml" = @("ficha_personagem", "descricao_personagem", "log_alteracoes", "ficha_snapshot", "compartilhamento_ficha", "feiticos_personagem_habilidades_personagem")
    "004-atributos-changelog.xml" = @("atributos_personagem", "atributos_origem", "atributos_linhagem")
    "005-linhagem-origem-changelog.xml" = @("linhagem", "origem", "origem_custom", "itens_origem", "habilidades_origem", "deslocamento_linhagem", "idiomas_linhagem", "sentidos_linhagem", "caracteristicas_ancestralidade", "tamanho")
}

# Calcular restante para sistema-rpg
$mappedTables = @()
$changelogMapping.Keys | ForEach-Object { $mappedTables += $changelogMapping[$_] }
$allTables = $backupTableColumns.Keys | Sort-Object
$remainingTables = $allTables | Where-Object { $_ -notin $mappedTables }
$changelogMapping["006-sistema-rpg-changelog.xml"] = $remainingTables

# Verificar cada changelog
$report = "# RELATORIO DE VERIFICACAO DE COLUNAS`n`n"
$report += "Data: " + (Get-Date -Format 'yyyy-MM-dd HH:mm:ss') + "`n`n"

$totalIssues = 0
$totalCorrectColumns = 0
$totalBackupColumns = 0
$totalTables = 0

foreach ($changelogFile in $changelogMapping.Keys) {
    Write-Host ("`nVerificando " + $changelogFile + "...") -ForegroundColor Yellow
    
    $expectedTables = $changelogMapping[$changelogFile]
    $changelogIssues = 0
    $changelogCorrectColumns = 0
    $changelogBackupColumns = 0
    
    $report += "## " + $changelogFile + "`n`n"
    
    foreach ($tableName in $expectedTables) {
        $backupColumns = $backupTableColumns[$tableName]
        $changelogColumns = Get-TableColumns $changelogFile $tableName
        
        $comparison = Compare-Columns $backupColumns $changelogColumns $tableName $changelogFile
        
        $changelogIssues += $comparison.Issues.Count
        $changelogCorrectColumns += $comparison.CorrectColumns
        $changelogBackupColumns += $comparison.TotalBackupColumns
        
        if ($comparison.Issues.Count -eq 0) {
            $report += "### OK " + $tableName + "`n"
            $report += "**Status**: PERFEITO - " + $comparison.TotalBackupColumns + " colunas identicas`n`n"
            Write-Host ("  OK " + $tableName + ": " + $comparison.TotalBackupColumns + " colunas OK") -ForegroundColor Green
        } else {
            $report += "### ERRO " + $tableName + "`n"
            $report += "**Colunas corretas**: " + $comparison.CorrectColumns + "/" + $comparison.TotalBackupColumns + "`n"
            $report += "**Problemas**:`n"
            foreach ($issue in $comparison.Issues) {
                $report += "- " + $issue + "`n"
            }
            $report += "`n"
            Write-Host ("  ERRO " + $tableName + ": " + $comparison.Issues.Count + " problemas") -ForegroundColor Red
        }
    }
    
    $totalIssues += $changelogIssues
    $totalCorrectColumns += $changelogCorrectColumns
    $totalBackupColumns += $changelogBackupColumns
    $totalTables += $expectedTables.Count
    
    # Status do changelog
    if ($changelogIssues -eq 0) {
        $report += "**Status**: OK PERFEITO - Todas as colunas corretas`n`n"
        Write-Host ("OK " + $changelogFile + ": PERFEITO!") -ForegroundColor Green
    } else {
        $report += "**Status**: ERRO " + $changelogIssues + " problemas encontrados`n`n"
        Write-Host ("ERRO " + $changelogFile + ": " + $changelogIssues + " problemas") -ForegroundColor Red
    }
    
    $report += "---`n`n"
}

# Resumo final
$percentCorrect = if ($totalBackupColumns -gt 0) { [math]::Round(($totalCorrectColumns / $totalBackupColumns) * 100, 2) } else { 0 }

$report += "# RESUMO FINAL`n`n"
$report += "| Metrica | Valor |`n"
$report += "|---------|-------|`n"
$report += "| Tabelas verificadas | " + $totalTables + " |`n"
$report += "| Colunas esperadas | " + $totalBackupColumns + " |`n"
$report += "| Colunas corretas | " + $totalCorrectColumns + " |`n"
$report += "| Total de problemas | " + $totalIssues + " |`n"
$report += "| Percentual correto | " + $percentCorrect + "% |`n`n"

if ($totalIssues -eq 0) {
    $report += "## VERIFICACAO 100% APROVADA!`n`n"
    $report += "Todas as colunas estao exatamente identicas ao backup original.`n"
    $status = "SUCCESS"
} else {
    $report += "## PROBLEMAS ENCONTRADOS`n`n"
    $report += "Necessario corrigir " + $totalIssues + " problemas de colunas.`n"
    $status = "ISSUES"
}

# Salvar relatorio
$outputFile = Join-Path $OutputDir "column-verification-detailed.md"
$report | Out-File $outputFile -Encoding UTF8

# Resumo no console
Write-Host "`n=== RESUMO FINAL ===" -ForegroundColor Cyan
Write-Host ("Tabelas: " + $totalTables) -ForegroundColor White
Write-Host ("Colunas esperadas: " + $totalBackupColumns) -ForegroundColor White
Write-Host ("Colunas corretas: " + $totalCorrectColumns) -ForegroundColor Green
Write-Host ("Problemas: " + $totalIssues) -ForegroundColor $(if ($totalIssues -eq 0) { "Green" } else { "Red" })
Write-Host ("Percentual: " + $percentCorrect + "%") -ForegroundColor White

if ($status -eq "SUCCESS") {
    Write-Host "`nTODAS AS COLUNAS ESTAO CORRETAS!" -ForegroundColor Green
} else {
    Write-Host "`nPROBLEMAS ENCONTRADOS!" -ForegroundColor Red
}

Write-Host ("`nRelatorio: " + $outputFile) -ForegroundColor Cyan
