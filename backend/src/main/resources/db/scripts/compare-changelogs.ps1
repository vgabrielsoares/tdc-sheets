# Script de Comparacao Detalhada de Changelogs
param(
    [string]$BackupFile = "generated-changelog.xml.backup"
)

Write-Host "=== COMPARACAO DETALHADA DE CHANGELOGS ===" -ForegroundColor Cyan

# Funcao para extrair tabelas de um arquivo XML
function Get-TablesFromFile {
    param([string]$FilePath)
    
    if (!(Test-Path $FilePath)) {
        Write-Warning "Arquivo nao encontrado: $FilePath"
        return @()
    }
    
    $content = Get-Content $FilePath -Raw
    $tablePattern = '<createTable.*?tableName="([^"]+)"'
    $matches = [regex]::Matches($content, $tablePattern)
    
    $tables = @()
    foreach ($match in $matches) {
        $tables += $match.Groups[1].Value
    }
    
    return $tables
}

# Funcao para extrair definicao completa de uma tabela
function Get-TableDefinition {
    param([string]$FilePath, [string]$TableName)
    
    $content = Get-Content $FilePath -Raw
    $tablePattern = "<createTable.*?tableName=`"$TableName`".*?>(.*?)</createTable>"
    $match = [regex]::Match($content, $tablePattern, [System.Text.RegularExpressions.RegexOptions]::Singleline)
    
    if ($match.Success) {
        return $match.Groups[0].Value
    }
    
    return $null
}

# Extrair tabelas do backup (referencia)
Write-Host "Extraindo tabelas do backup..." -ForegroundColor Yellow
$backupTables = Get-TablesFromFile $BackupFile
Write-Host "Backup contem $($backupTables.Count) tabelas" -ForegroundColor Green

# Definir mapeamento esperado
$expectedMapping = @{
    "002-auth-changelog.xml" = @("user", "authorities", "user_authority")
    "003-core-ficha-changelog.xml" = @("ficha_personagem", "descricao_personagem", "log_alteracoes", "ficha_snapshot", "compartilhamento_ficha", "feiticos_personagem_habilidades_personagem")
    "004-atributos-changelog.xml" = @("atributos_personagem", "atributos_origem", "atributos_linhagem")
    "005-linhagem-origem-changelog.xml" = @("linhagem", "origem", "origem_custom", "itens_origem", "habilidades_origem", "deslocamento_linhagem", "idiomas_linhagem", "sentidos_linhagem", "caracteristicas_ancestralidade", "tamanho")
}

# Calcular tabelas restantes para sistema-rpg
$mappedTables = @()
foreach ($key in $expectedMapping.Keys) {
    $mappedTables += $expectedMapping[$key]
}
$remainingTables = $backupTables | Where-Object { $_ -notin $mappedTables }
$expectedMapping["006-sistema-rpg-changelog.xml"] = $remainingTables

# Relatorio detalhado
$report = "# RELATORIO DE COMPARACAO DETALHADA`n`n"
$report += "Data: $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')`n`n"

$allIssues = @()
$totalExpected = 0
$totalFound = 0
$totalMissing = 0
$totalExtra = 0

foreach ($changelogFile in $expectedMapping.Keys) {
    Write-Host "Verificando $changelogFile..." -ForegroundColor Yellow
    
    $expectedTables = $expectedMapping[$changelogFile]
    $actualTables = Get-TablesFromFile $changelogFile
    
    $report += "## $changelogFile`n`n"
    $report += "**Esperadas**: $($expectedTables.Count) tabelas`n"
    $report += "**Encontradas**: $($actualTables.Count) tabelas`n`n"
    
    # Verificar tabelas faltando
    $missingTables = $expectedTables | Where-Object { $_ -notin $actualTables }
    $extraTables = $actualTables | Where-Object { $_ -notin $expectedTables }
    
    if ($missingTables.Count -gt 0) {
        $report += "### TABELAS FALTANDO ($($missingTables.Count)):`n"
        foreach ($table in $missingTables) {
            $report += "- [ ] **$table** - FALTANDO`n"
            $allIssues += "FALTANDO: $table em $changelogFile"
        }
        $report += "`n"
    }
    
    if ($extraTables.Count -gt 0) {
        $report += "### TABELAS EXTRAS ($($extraTables.Count)):`n"
        foreach ($table in $extraTables) {
            $report += "- [ ] **$table** - EXTRA (nao deveria estar aqui)`n"
            $allIssues += "EXTRA: $table em $changelogFile"
        }
        $report += "`n"
    }
    
    # Verificar tabelas corretas
    $correctTables = $expectedTables | Where-Object { $_ -in $actualTables }
    if ($correctTables.Count -gt 0) {
        $report += "### TABELAS CORRETAS ($($correctTables.Count)):`n"
        foreach ($table in $correctTables) {
            $report += "- [x] $table - OK`n"
        }
        $report += "`n"
    }
    
    # Atualizar contadores
    $totalExpected += $expectedTables.Count
    $totalFound += $correctTables.Count
    $totalMissing += $missingTables.Count
    $totalExtra += $extraTables.Count
    
    $report += "---`n`n"
}

# Resumo final
$report += "# RESUMO FINAL`n`n"
$report += "| Metrica | Valor |`n"
$report += "|---------|-------|`n"
$report += "| Total Esperado | $totalExpected |`n"
$report += "| Total Encontrado | $totalFound |`n"
$report += "| Total Faltando | $totalMissing |`n"
$report += "| Total Extra | $totalExtra |`n"
$report += "| Percentual Correto | $([math]::Round(($totalFound / $totalExpected) * 100, 2))% |`n`n"

if ($allIssues.Count -gt 0) {
    $report += "## TODOS OS PROBLEMAS ENCONTRADOS`n`n"
    foreach ($issue in $allIssues) {
        $report += "- $issue`n"
    }
    $report += "`n"
}

# Status geral
if ($totalMissing -eq 0 -and $totalExtra -eq 0) {
    $report += "STATUS: ✅ TODOS OS CHANGELOGS ESTAO CORRETOS!`n"
    $status = "SUCCESS"
} else {
    $report += "STATUS: ❌ PROBLEMAS ENCONTRADOS - VERIFICACAO NECESSARIA`n"
    $status = "ISSUES"
}

# Salvar relatorio
$outputFile = "verification-reports\detailed-comparison-report.md"
$report | Out-File $outputFile -Encoding UTF8

# Exibir resumo no console
Write-Host "`n=== RESUMO DA COMPARACAO ===" -ForegroundColor Cyan
Write-Host "Total Esperado: $totalExpected" -ForegroundColor White
Write-Host "Total Correto: $totalFound" -ForegroundColor Green
Write-Host "Total Faltando: $totalMissing" -ForegroundColor Red
Write-Host "Total Extra: $totalExtra" -ForegroundColor Yellow
Write-Host "Percentual: $([math]::Round(($totalFound / $totalExpected) * 100, 2))%" -ForegroundColor White

if ($status -eq "SUCCESS") {
    Write-Host "`nSTATUS: TODOS OS CHANGELOGS CORRETOS!" -ForegroundColor Green
} else {
    Write-Host "`nSTATUS: PROBLEMAS ENCONTRADOS!" -ForegroundColor Red
    Write-Host "Veja detalhes em: $outputFile" -ForegroundColor Yellow
}

Write-Host "`nRelatorio salvo em: $outputFile" -ForegroundColor Cyan
