# Extrai todas as foreign keys do backup
Write-Host "=== EXTRAINDO FOREIGN KEYS DO BACKUP ===" -ForegroundColor Yellow

$backupFile = "e:\projetos\tdc-sheets\backend\src\main\resources\db\changelog\generated-changelog.xml.backup"
$reorganizedFile = "e:\projetos\tdc-sheets\backend\src\main\resources\db\changelog\007-foreign-keys-changelog.xml"

Write-Host "Extraindo foreign keys do backup..." -ForegroundColor Green

# Extrai foreign keys do backup
$backupContent = Get-Content $backupFile -Raw
$backupFKs = [regex]::Matches($backupContent, '<addForeignKeyConstraint[^>]*constraintName="([^"]*)"[^>]*/>') | 
    ForEach-Object { $_.Groups[1].Value } | Sort-Object

Write-Host "Foreign keys no backup: $($backupFKs.Count)" -ForegroundColor Cyan

# Extrai foreign keys do arquivo reorganizado
$reorganizedContent = Get-Content $reorganizedFile -Raw
$reorganizedFKs = [regex]::Matches($reorganizedContent, '<addForeignKeyConstraint[^>]*constraintName="([^"]*)"[^>]*') | 
    ForEach-Object { $_.Groups[1].Value } | Sort-Object

Write-Host "Foreign keys reorganizadas: $($reorganizedFKs.Count)" -ForegroundColor Cyan

Write-Host "`n=== COMPARAÇÃO ===" -ForegroundColor Yellow

# Verifica diferenças
$missing = Compare-Object $backupFKs $reorganizedFKs | Where-Object { $_.SideIndicator -eq "<=" }
$extra = Compare-Object $backupFKs $reorganizedFKs | Where-Object { $_.SideIndicator -eq "=>" }

if ($missing) {
    Write-Host "`nFOREIGN KEYS FALTANDO NO REORGANIZADO:" -ForegroundColor Red
    $missing | ForEach-Object { Write-Host "  - $($_.InputObject)" -ForegroundColor Red }
}

if ($extra) {
    Write-Host "`nFOREIGN KEYS EXTRAS NO REORGANIZADO:" -ForegroundColor Red
    $extra | ForEach-Object { Write-Host "  + $($_.InputObject)" -ForegroundColor Red }
}

if (-not $missing -and -not $extra) {
    Write-Host "`n✅ TODAS AS FOREIGN KEYS ESTÃO CORRETAS!" -ForegroundColor Green
    Write-Host "Total: $($backupFKs.Count) foreign keys" -ForegroundColor Green
} else {
    Write-Host "`n❌ ENCONTRADAS DIFERENÇAS!" -ForegroundColor Red
    Write-Host "Backup: $($backupFKs.Count) FKs" -ForegroundColor Yellow
    Write-Host "Reorganizado: $($reorganizedFKs.Count) FKs" -ForegroundColor Yellow
}

Write-Host "`n=== LISTAGEM COMPLETA ===" -ForegroundColor Yellow
Write-Host "FOREIGN KEYS NO BACKUP:" -ForegroundColor Cyan
$backupFKs | ForEach-Object { Write-Host "  $_" -ForegroundColor White }

Write-Host "`nFOREIGN KEYS REORGANIZADAS:" -ForegroundColor Cyan
$reorganizedFKs | ForEach-Object { Write-Host "  $_" -ForegroundColor White }
