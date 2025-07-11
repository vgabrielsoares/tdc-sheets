Write-Host "=== EXTRAINDO FOREIGN KEYS ===" -ForegroundColor Yellow

$backupFile = "e:\projetos\tdc-sheets\backend\src\main\resources\db\changelog\generated-changelog.xml.backup"
$backupContent = Get-Content $backupFile -Raw

# Captura todas as linhas com addForeignKeyConstraint
$fkLines = $backupContent -split "`n" | Where-Object { $_ -match "addForeignKeyConstraint" }

Write-Host "Total de FKs encontradas: $($fkLines.Count)" -ForegroundColor Green

# Salva em arquivo temporário para análise
$fkLines | Out-File "e:\projetos\tdc-sheets\backend\todas-foreign-keys.txt" -Encoding UTF8

Write-Host "Foreign keys salvas em: todas-foreign-keys.txt" -ForegroundColor Cyan

# Lista as primeiras 10
Write-Host "`nPrimeiras 10 foreign keys:" -ForegroundColor Yellow
$fkLines | Select-Object -First 10 | ForEach-Object { Write-Host "  $_" }
