#!/bin/bash
set -e

echo "🔧 Configurando banco de dados TDC Sheets..."

# Configurar timezone
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    -- Configurar timezone
    SET timezone = 'America/Sao_Paulo';
    
    -- Criar extensões necessárias
    CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
    CREATE EXTENSION IF NOT EXISTS "pg_trgm";
    CREATE EXTENSION IF NOT EXISTS "unaccent";
    
    -- Configurar encoding
    ALTER DATABASE $POSTGRES_DB SET timezone TO 'America/Sao_Paulo';
    ALTER DATABASE $POSTGRES_DB SET client_encoding TO 'UTF8';
    ALTER DATABASE $POSTGRES_DB SET default_text_search_config TO 'portuguese';
EOSQL

echo "✅ Configuração do banco concluída!"
