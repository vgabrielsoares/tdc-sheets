#!/bin/bash
# Script para setup inicial do projeto

echo "🚀 Configurando ambiente TDC Sheets..."

# Verificar se Docker está instalado
if ! command -v docker &> /dev/null; then
    echo "❌ Docker não encontrado. Por favor, instale o Docker primeiro."
    exit 1
fi

# Verificar se Docker Compose está instalado
if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose não encontrado. Por favor, instale o Docker Compose primeiro."
    exit 1
fi

# Criar arquivo .env se não existir
if [ ! -f .env ]; then
    echo "📝 Criando arquivo .env..."
    cp .env.example .env
    echo "✅ Arquivo .env criado. Configure as variáveis necessárias."
fi

# Subir banco de dados
echo "🗄️ Iniciando banco de dados..."
docker-compose up -d database

# Aguardar banco ficar disponível
echo "⏳ Aguardando banco de dados..."
sleep 10

# Executar migrações
echo "🔧 Executando migrações do banco..."
cd backend && ./gradlew liquibaseUpdate && cd ..

# Instalar dependências do frontend
echo "📦 Instalando dependências do frontend..."
cd frontend && npm install && cd ..

echo "✅ Setup concluído! Use 'npm run dev' para iniciar o desenvolvimento."
