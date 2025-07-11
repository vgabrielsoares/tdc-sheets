# Script de Extracao de Schema do Backup
param(
    [string]$BackupFile = "generated-changelog.xml.backup",
    [string]$OutputDir = "verification-reports"
)

# Criar diretorio se nao existir
if (!(Test-Path $OutputDir)) {
    New-Item -ItemType Directory -Path $OutputDir -Force
    Write-Host "Criado diretorio: $OutputDir" -ForegroundColor Green
}

Write-Host "Iniciando extracao..." -ForegroundColor Cyan

# Verificar arquivo
if (!(Test-Path $BackupFile)) {
    Write-Error "Arquivo nao encontrado: $BackupFile"
    exit 1
}

# Ler conteudo
$content = Get-Content $BackupFile -Raw

# Arrays para dados
$tables = @()
$indexes = @()

# Extrair tabelas
$tablePattern = '<createTable.*?tableName="([^"]+)".*?>(.*?)</createTable>'
$tableMatches = [regex]::Matches($content, $tablePattern, [System.Text.RegularExpressions.RegexOptions]::Singleline)

Write-Host "Encontradas $($tableMatches.Count) tabelas" -ForegroundColor Yellow

foreach ($match in $tableMatches) {
    $tableName = $match.Groups[1].Value
    $tableContent = $match.Groups[2].Value
    
    # Contar colunas
    $columnMatches = [regex]::Matches($tableContent, '<column.*?name="([^"]+)"')
    $columnCount = $columnMatches.Count
    
    # Extrair remarks
    $remarks = ""
    if ($match.Groups[0].Value -match 'remarks="([^"]+)"') {
        $remarks = $matches[1]
    }
    
    $tables += [PSCustomObject]@{
        TableName = $tableName
        ColumnCount = $columnCount
        Remarks = $remarks
    }
}

# Extrair indices
$indexPattern = '<createIndex.*?indexName="([^"]+)".*?tableName="([^"]+)"'
$indexMatches = [regex]::Matches($content, $indexPattern)

Write-Host "Encontrados $($indexMatches.Count) indices" -ForegroundColor Yellow

foreach ($match in $indexMatches) {
    $indexes += [PSCustomObject]@{
        IndexName = $match.Groups[1].Value
        TableName = $match.Groups[2].Value
    }
}

# Gerar relatorio de tabelas
$report = "# Relatorio de Tabelas - Backup Schema`n`n"
$report += "Total de Tabelas: $($tables.Count)`n`n"
$report += "| # | Nome da Tabela | Colunas | Remarks |`n"
$report += "|---|---|---|---|`n"

for ($i = 0; $i -lt $tables.Count; $i++) {
    $table = $tables[$i]
    $remarksText = if ($table.Remarks) { $table.Remarks } else { "-" }
    $report += "| $($i+1) | $($table.TableName) | $($table.ColumnCount) | $remarksText |`n"
}

$report | Out-File "$OutputDir\tables-report.md" -Encoding UTF8

# Gerar checklist de verificacao
$checklistAuth = @("user", "authorities", "user_authority")
$checklistCore = @("ficha_personagem", "descricao_personagem", "log_alteracoes", "ficha_snapshot", "compartilhamento_ficha", "feiticos_personagem_habilidades_personagem")
$checklistAtributos = @("atributos_personagem", "atributos_origem", "atributos_linhagem")
$checklistLinhagem = @("linhagem", "origem", "origem_custom", "itens_origem", "habilidades_origem", "deslocamento_linhagem", "idiomas_linhagem", "sentidos_linhagem", "caracteristicas_ancestralidade", "tamanho")

$allMapped = $checklistAuth + $checklistCore + $checklistAtributos + $checklistLinhagem
$checklistSistema = $tables | Where-Object { $_.TableName -notin $allMapped } | ForEach-Object { $_.TableName }

$checklist = "# Checklist de Verificacao por Changelog`n`n"

$checklist += "## 002-auth-changelog.xml`n"
$checklist += "Tabelas esperadas: $($checklistAuth.Count)`n`n"
foreach ($tableName in $checklistAuth) {
    $found = $tables | Where-Object { $_.TableName -eq $tableName }
    $status = if ($found) { "Encontrada" } else { "FALTANDO" }
    $checklist += "- [ ] $tableName - $status`n"
}

$checklist += "`n## 003-core-ficha-changelog.xml`n"
$checklist += "Tabelas esperadas: $($checklistCore.Count)`n`n"
foreach ($tableName in $checklistCore) {
    $found = $tables | Where-Object { $_.TableName -eq $tableName }
    $status = if ($found) { "Encontrada" } else { "FALTANDO" }
    $checklist += "- [ ] $tableName - $status`n"
}

$checklist += "`n## 004-atributos-changelog.xml`n"
$checklist += "Tabelas esperadas: $($checklistAtributos.Count)`n`n"
foreach ($tableName in $checklistAtributos) {
    $found = $tables | Where-Object { $_.TableName -eq $tableName }
    $status = if ($found) { "Encontrada" } else { "FALTANDO" }
    $checklist += "- [ ] $tableName - $status`n"
}

$checklist += "`n## 005-linhagem-origem-changelog.xml`n"
$checklist += "Tabelas esperadas: $($checklistLinhagem.Count)`n`n"
foreach ($tableName in $checklistLinhagem) {
    $found = $tables | Where-Object { $_.TableName -eq $tableName }
    $status = if ($found) { "Encontrada" } else { "FALTANDO" }
    $checklist += "- [ ] $tableName - $status`n"
}

$checklist += "`n## 006-sistema-rpg-changelog.xml`n"
$checklist += "Tabelas restantes: $($checklistSistema.Count)`n`n"
foreach ($tableName in $checklistSistema) {
    $checklist += "- [ ] $tableName - Encontrada`n"
}

$checklist | Out-File "$OutputDir\verification-checklist.md" -Encoding UTF8

# Lista de todas as tabelas para referencia
$allTablesList = "# Lista Completa de Tabelas`n`n"
$sortedTables = $tables | Sort-Object TableName
foreach ($table in $sortedTables) {
    $allTablesList += "- $($table.TableName) ($($table.ColumnCount) colunas)`n"
}

$allTablesList | Out-File "$OutputDir\all-tables-list.md" -Encoding UTF8

Write-Host "Concluido! Relatorios salvos em: $OutputDir" -ForegroundColor Green
Write-Host "Tabelas encontradas: $($tables.Count)" -ForegroundColor White
Write-Host "Indices encontrados: $($indexes.Count)" -ForegroundColor White
