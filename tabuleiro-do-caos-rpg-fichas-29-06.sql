CREATE TYPE "arquetipos_nome" AS ENUM (
  'ACADEMICO',
  'ACOLITO',
  'COMBATENTE',
  'FEITICEIRO',
  'LADINO',
  'NATURAL'
);

CREATE TYPE "matriz_feiticos" AS ENUM (
  'ADIAFANA',
  'ANA',
  'ARCANA',
  'ELFICA',
  'GNOMICA',
  'INFERNAL',
  'LUZIDIA',
  'MUNDANA',
  'NATURAL',
  'PRIMORDIAL'
);

CREATE TYPE "classes_feiticos" AS ENUM (
  'EVOCACAO',
  'MANIPULACAO'
);

CREATE TYPE "grau_pericia" AS ENUM (
  'LEIGO',
  'ADEPTO',
  'VERSADO',
  'MESTRE',
  'APEX_HUMANO'
);

CREATE TYPE "atributos" AS ENUM (
  'AGILIDADE',
  'CONSTITUICAO',
  'FORCA',
  'INFLUENCIA',
  'MENTE',
  'PRESENCA'
);

CREATE TYPE "tipos_dano" AS ENUM (
  'CORTANTE',
  'PERFURANTE',
  'IMPACTO',
  'FOGO'
);

CREATE TYPE "raridades" AS ENUM (
  'COMUM',
  'ATIPICO',
  'RARO',
  'MUITO_RARO',
  'LENDARIO'
);

CREATE TYPE "tipos_sentidos" AS ENUM (
  'COMUM',
  'AGUCADO',
  'CEGO',
  'SURDO'
);

CREATE TYPE "tipos_deslocamento" AS ENUM (
  'ANDANDO',
  'VOANDO',
  'NADANDO',
  'ESCALANDO',
  'ESCAVANDO'
);

CREATE TYPE "tipos_alcance" AS ENUM (
  'PESSOAL',
  'ADJACENTE',
  'TOQUE',
  'CURTO',
  'MÉDIO',
  'LONGO',
  'MUITO_lONGO',
  'ILIMITADO'
);

CREATE TYPE "tipos_proficiencia" AS ENUM (
  'ARMAS_SIMPLES',
  'ARMAS_COMPLEXAS',
  'ARMAS_MARCIAIS',
  'ARMAS_PESADAS',
  'IDIOMA_COMUM',
  'IDIOMA_PRIMORDIAL',
  'IDIOMA_RUNICO',
  'IDIOMA_ANAO',
  'IDIOMA_AQUATICO',
  'IDIOMA_DRACONICO',
  'IDIOMA_ELFICO',
  'IDIOMA_GIGANTE',
  'IDIOMA_GNOMICO',
  'IDIOMA_INFERNAL',
  'IDIOMA_OOPARNEELA',
  'IDIOMA_ORC',
  'IDIOMA_SILVESTRE',
  'IDIOMA_URURIMI',
  'PROTECAO_LEVE',
  'PROTECAO_MEDIA',
  'PROTECAO_PESADA',
  'INTRUMENTO_HABILIDADE',
  'INTRUMENTO_OFICIO'
);

CREATE TYPE "tipos_tamanho" AS ENUM (
  'MINUSCULO',
  'PEQUENO',
  'MEDIO',
  'GRANDE',
  'ENORME',
  'COLOSSAL'
);

CREATE TYPE "habilidades_resistencia" AS ENUM (
  'DETERMINACAO',
  'REFLEXO',
  'TENACIDADE',
  'VIGOR'
);

CREATE TYPE "tempo_acoes" AS ENUM (
  'COMPLETA',
  'REACAO',
  'MAIOR',
  'DUAS_MENORES',
  'UMA_MENOR',
  'LIVRE'
);

CREATE TYPE "tipos_resistencia" AS ENUM (
  'ATAQUE',
  'RESISTENCIA',
  'RESISTENCIA_PARCIAL',
  'AUTOMATICO'
);

CREATE TYPE "tipos_equipamentos" AS ENUM (
  'PEDRAS_PRECIOSAS',
  'OBRAS_ARTES',
  'MERCADORIAS_TECIDOS',
  'MERCADORIAS_METAIS',
  'MERCADORIAS_ANIMAIS',
  'MERCADORIAS_PROVISOES',
  'AVENTURA_EXPLORACAO_VIAGEM',
  'COMIDA_BEBIDA',
  'FEITICARIA_CONDUITES',
  'FEITICARIA_POCOES',
  'FEITICARIA_OUTROS',
  'FERRAMENTAS_HABILIDADE',
  'FERRAMENTAS_OFICIO',
  'FONTES_LUZ',
  'HERBALISMO',
  'INSTRUMENTOS_MUSICAIS',
  'MUNICOES',
  'PRODUTOS_ALQUIMICOS',
  'RECIPIENTES',
  'UTILITARIOS',
  'VENENOS',
  'VESTIMENTAS',
  'ARMAS_SIMPLES',
  'ARMAS_MARCIAIS',
  'ARMAS_COMPLEXAS',
  'ARMAS_PESADAS',
  'PROTECOES_LEVES',
  'PROTECOES_MEDIAS',
  'PROTECOES_PESADAS',
  'ESCUDOS',
  'TRALHA',
  'MATERIAIS_INGREDIENTES',
  'MATERIAIS_CRIACAO'
);

CREATE TYPE "idiomas" AS ENUM (
  'COMUM',
  'PRIMORDIAL',
  'RUNICO',
  'ANAO',
  'AQUATICO',
  'DRACONICO',
  'ELFICO',
  'GIGANTE',
  'GNOMICO',
  'INFERNAL',
  'OOPARNEELA',
  'ORC',
  'SILVESTRE',
  'URURIMI'
);

CREATE TYPE "alfabetos" AS ENUM (
  'COMUM',
  'PRIMORDIAL',
  'RUNICO',
  'GNOMICO',
  'AQUATICO',
  'DRACONICO',
  'ELFICO',
  'GIGANTE',
  'GLASNEE',
  'SILVESTRE'
);

CREATE TABLE "ficha_personagem" (
  "id" SERIAL PRIMARY KEY,
  "nome_personagem" "VARCHAR" NOT NULL,
  "user_id" "INTEGER" NOT NULL,
  "background" "TEXT",
  "origem_id" "INTEGER" NOT NULL,
  "linhagem_id" "INTEGER" NOT NULL,
  "experiencia" "INTEGER" NOT NULL DEFAULT 0,
  "habilidade_assinatura" "INTEGER" NOT NULL,
  "nivel_sorte" "INTEGER" NOT NULL DEFAULT 0,
  "tamanho" tipos_tamanho,
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP,
  "versao" INTEGER DEFAULT 1
);

CREATE TABLE "defesa" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "defesa_personagem" "INTEGER" NOT NULL DEFAULT 15,
  "armadura" "INTEGER" NOT NULL,
  "vestindo_armadura" "BOOLEAN" DEFAULT false,
  "limite_agilidade" "INTEGER",
  "outro_mod" "INTEGER",
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP
);

CREATE TABLE "pv_pp_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "pv_maximo" "INTEGER" NOT NULL DEFAULT 15,
  "pv_atual" "INTEGER" NOT NULL DEFAULT 15,
  "pv_temporario" "INTEGER",
  "pp_maximo" "INTEGER" NOT NULL DEFAULT 2,
  "pp_atual" "INTEGER" NOT NULL DEFAULT 2,
  "pp_temporario" "INTEGER",
  "limite_pp" "INTEGER",
  "limite_pp_bonus" "INTEGER",
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP
);

CREATE TABLE "arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "nome" arquetipos_nome NOT NULL,
  "pv_inicial" "INTEGER" NOT NULL,
  "pp_inicial" "INTEGER" NOT NULL,
  "pv_nivel" integer NOT NULL,
  "pp_nivel" integer NOT NULL,
  "defesa_5" integer NOT NULL,
  "defesa_10" integer NOT NULL,
  "defesa_15" integer NOT NULL
);

CREATE TABLE "talento__poderes_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER",
  "requisitos_talentos_poderes_id" integer,
  "nome" varchar NOT NULL,
  "descricao" text NOT NULL,
  "linhagem" boolean
);

CREATE TABLE "requisitos_talentos_poderes" (
  "id" SERIAL PRIMARY KEY,
  "valor_agilidade" integer,
  "valor_constituicao" integer,
  "valor_forca" integer,
  "valor_influencia" integer,
  "valor_mente" integer,
  "valor_presenca" integer,
  "habilidade_id" integer,
  "grau_habilidade" grau_pericia,
  "talento_poder_requisito" integer,
  "linhagem_id" integer
);

CREATE TABLE "arquetipos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "arquetipo_id" "INTEGER" NOT NULL,
  "nivel_arquetipo" "INTEGER" NOT NULL
);

CREATE TABLE "classe" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "descricao" text,
  "requisitos_classe_id" "INTEGER" NOT NULL,
  "defesa_5" integer,
  "defesa_10" integer,
  "defesa_15" integer
);

CREATE TABLE "classes_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "classe_id" "INTEGER" NOT NULL,
  "nivel" "INTEGER" NOT NULL
);

CREATE TABLE "requisitos_classe" (
  "id" SERIAL PRIMARY KEY,
  "classe_id" "INTEGER" NOT NULL,
  "nivel_minimo" "INTEGER" NOT NULL,
  "primeiro_arquetipo" arquetipos_nome NOT NULL,
  "segundo_arquetipo" arquetipos_nome,
  "primeiro_arquetipo_nivel" "INTEGER" NOT NULL,
  "segundo_arquetipo_nivel" "INTEGER"
);

CREATE TABLE "habilidade" (
  "id" SERIAL PRIMARY KEY,
  "pericia" "VARCHAR" NOT NULL,
  "descricao" "TEXT",
  "atributo_chave" atributos,
  "penalidade_carga" boolean,
  "instrumento" boolean,
  "proficiencia" boolean,
  "combate" boolean
);

CREATE TABLE "uso_habilidade" (
  "id" SERIAL PRIMARY KEY,
  "habilidade_id" "INTEGER" NOT NULL,
  "nome" varchar,
  "descricao" text
);

CREATE TABLE "habilidades_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "habilidade_id" "INTEGER" NOT NULL,
  "grau_habilidade" grau_pericia NOT NULL,
  "atributo_chave_personagem" atributos,
  "atributo_personagem_id" "INTEGER" NOT NULL,
  "mod_dado_habilidade" "INTEGER",
  "mod_temp" "INTEGER",
  "mod_outros" "INTEGER",
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP
);

CREATE TABLE "deslocamento_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "deslocamento" tipos_deslocamento NOT NULL,
  "valor" "INTEGER" NOT NULL,
  "bonus" integer
);

CREATE TABLE "origem" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "descricao" text,
  "habilidade_especial" "TEXT"
);

CREATE TABLE "atributos_origem" (
  "id" SERIAL PRIMARY KEY,
  "origem_id" "INTEGER" NOT NULL,
  "valor_agilidade" integer,
  "valor_constituicao" integer,
  "valor_forca" integer,
  "valor_influencia" integer,
  "valor_mente" integer,
  "valor_presenca" integer,
  "valor_generico_positivo" integer,
  "valor_generico_negativo" integer
);

CREATE TABLE "itens_origem" (
  "id" SERIAL PRIMARY KEY,
  "origem_id" "INTEGER" NOT NULL,
  "equipamento_id" integer,
  "quantidade" integer
);

CREATE TABLE "habilidades_origem" (
  "id" SERIAL PRIMARY KEY,
  "origem_id" "INTEGER" NOT NULL,
  "habilidade_id" "INTEGER" NOT NULL,
  "fixa" "BOOLEAN"
);

CREATE TABLE "origem_custom" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER",
  "nome" "VARCHAR",
  "descricao" text,
  "habilidade_especial" "TEXT"
);

CREATE TABLE "linhagem" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "tamanho" tipos_tamanho,
  "altura_media" varchar,
  "peso_medio" "VARCHAR",
  "maioridade" "VARCHAR",
  "expectativa_vida" "VARCHAR",
  "descricao" "TEXT"
);

CREATE TABLE "deslocamento_linhagem" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "deslocamento" tipos_deslocamento,
  "valor" integer
);

CREATE TABLE "idiomas_linhagem" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "idioma" idiomas,
  "alfabeto" alfabetos
);

CREATE TABLE "atributos_linhagem" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "valor_agilidade" integer,
  "valor_constituicao" integer,
  "valor_forca" integer,
  "valor_influencia" integer,
  "valor_mente" integer,
  "valor_presenca" integer,
  "valor_generico_positivo" integer,
  "valor_generico_negativo" integer
);

CREATE TABLE "sentidos_linhagem" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "audicao" tipos_sentidos NOT NULL,
  "olfato" tipos_sentidos NOT NULL,
  "visao" tipos_sentidos NOT NULL
);

CREATE TABLE "caracteristicas_ancestralidade" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "nome" varchar,
  "descricao" text,
  "fixa" boolean
);

CREATE TABLE "tamanho" (
  "id" SERIAL PRIMARY KEY,
  "nome" tipos_tamanho NOT NULL,
  "alcance" "INTEGER",
  "dano" "VARCHAR",
  "defesa" "INTEGER",
  "quadrados" decimal,
  "peso_carregado" integer,
  "manobras" integer,
  "nd_rastro" integer,
  "acrobacia" integer,
  "atletismo" integer,
  "furtividade" integer,
  "reflexos" integer,
  "tenacidade" integer
);

CREATE TABLE "feiticos" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "circulo" "INTEGER" NOT NULL,
  "matriz" matriz_feiticos NOT NULL,
  "alcance" tipos_alcance,
  "habilidade_resistencia" habilidades_resistencia,
  "componente_somatico" "BOOLEAN",
  "componente_verbal" "BOOLEAN",
  "componente_material" "BOOLEAN",
  "componente_circular" "BOOLEAN",
  "componente_material_descricao" "TEXT",
  "tempo_conjuracao" tempo_acoes NOT NULL,
  "tipo_resistencia" tipos_resistencia,
  "alvo" "VARCHAR",
  "area" "VARCHAR",
  "duracao" "VARCHAR",
  "descricao" "TEXT",
  "elevacao" "TEXT",
  "aprimoramento" "TEXT"
);

CREATE TABLE "classes_feitico" (
  "id" SERIAL PRIMARY KEY,
  "feitico_id" "INTEGER" NOT NULL,
  "classe_feitico" classes_feiticos
);

CREATE TABLE "caracteristica_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "arquetipo_id" "INTEGER" NOT NULL,
  "descricao" "TEXT" NOT NULL,
  "nivel" integer NOT NULL
);

CREATE TABLE "habilidades_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER" NOT NULL,
  "habilidade_id" "INTEGER" NOT NULL
);

CREATE TABLE "proficiencias_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER" NOT NULL,
  "proficiencia" tipos_proficiencia
);

CREATE TABLE "proficiencias_classe" (
  "id" SERIAL PRIMARY KEY,
  "classe_id" "INTEGER" NOT NULL,
  "proficiencia" tipos_proficiencia
);

CREATE TABLE "resistencias_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER",
  "tipo_dano" tipos_dano,
  "resistencia" "INTEGER" DEFAULT 0,
  "aprimorada" "BOOLEAN" DEFAULT false,
  "suprema" "BOOLEAN" DEFAULT false,
  "lendaria" "BOOLEAN" DEFAULT false,
  "invulnerabilidade" "BOOLEAN" DEFAULT false,
  "absorcao" "BOOLEAN" DEFAULT false
);

CREATE TABLE "atributos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "atributo" atributos NOT NULL,
  "valor" "INTEGER" NOT NULL,
  "valor_temp" "INTEGER",
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP,
  UNIQUE(ficha_personagem_id, atributo)
);

CREATE TABLE "sentidos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "audicao" tipos_sentidos NOT NULL,
  "mod_audicao" "INTEGER",
  "olfato" tipos_sentidos NOT NULL,
  "mod_olfato" "INTEGER",
  "visao" tipos_sentidos NOT NULL,
  "mod_visao" "INTEGER"
);

CREATE TABLE "acoes_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "acao" "VARCHAR" NOT NULL,
  "acao_disponivel" "BOOLEAN" NOT NULL DEFAULT false
);

CREATE TABLE "ataques_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER",
  "nome" "VARCHAR",
  "atributo" atributos,
  "habilidade_id" "INTEGER",
  "alcance" tipos_alcance,
  "dado_bonus" "INTEGER" DEFAULT 0,
  "mod_bonus" "INTEGER" DEFAULT 0,
  "custo_pp" "INTEGER",
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP
);

CREATE TABLE "user" (
  "id" SERIAL PRIMARY KEY,
  "nome_completo" "VARCHAR" NOT NULL,
  "username" "VARCHAR" NOT NULL,
  "senha" "VARCHAR" NOT NULL,
  "created_at" "TIMESTAMP" NOT NULL
);

CREATE TABLE "authorities" (
  "id" SERIAL PRIMARY KEY,
  "authority" "VARCHAR" NOT NULL
);

CREATE TABLE "user_authority" (
  "id" "INTEGER" NOT NULL,
  "user_id" SERIAL PRIMARY KEY,
  "authority_id" "INTEGER" NOT NULL
);

CREATE TABLE "dano_ataque" (
  "id" SERIAL PRIMARY KEY,
  "ataque_personagem_id" "INTEGER",
  "dado" "VARCHAR(7)",
  "mod" "INTEGER",
  "bonus" "INTEGER",
  "critico" "VARCHAR(8)"
);

CREATE TABLE "feiticos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "feiticos_id" "INTEGER",
  "habilidade" "INTEGER",
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP
);

CREATE TABLE "caracteristicas_arquetipos" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER",
  "nivel" "INTEGER",
  "descricao" "TEXT"
);

CREATE TABLE "habilidades_classe" (
  "id" SERIAL PRIMARY KEY,
  "classe_id" "INTEGER",
  "nivel" "INTEGER",
  "descricao" "TEXT",
  "primeiro_arquetipo" arquetipos_nome NOT NULL,
  "segundo_arquetipo" arquetipos_nome,
  "primeiro_arquetipo_nivel" "INTEGER" NOT NULL,
  "segundo_arquetipo_nivel" "INTEGER"
);

CREATE TABLE "proficiencias_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "proficiencia" tipos_proficiencia NOT NULL,
  "proficiente" "BOOLEAN" NOT NULL
);

CREATE TABLE "inventario_personagem" (
  "id" SERIAL PRIMARY KEY,
  "carga_personagem_id" "INTEGER" NOT NULL,
  "equipamento_id" "INTEGER",
  "item" "VARCHAR(255)",
  "descricao_item" "TEXT",
  "quantidade_item" "INTEGER",
  "peso_item" "INTEGER",
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP
);

CREATE TABLE "carga_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "carga_atual" "INTEGER",
  "capacidade_carga" "INTEGER",
  "bonus_capacidade_carga" "INTEGER"
);

CREATE TABLE "cunhagem_personagem" (
  "id" SERIAL PRIMARY KEY,
  "carga_personagem_id" "INTEGER" NOT NULL,
  "c_fisico" "INTEGER",
  "po_fisico" "INTEGER",
  "pp_fisico" "INTEGER",
  "c_banco" "INTEGER",
  "po_banco" "INTEGER",
  "pp_banco" "INTEGER"
);

CREATE TABLE "descricao_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "genero" "VARCHAR(255)",
  "pronomes" "VARCHAR(255)",
  "idade" "VARCHAR(255)",
  "altura" "VARCHAR(255)",
  "peso" "VARCHAR(255)",
  "tamanho" tipos_tamanho,
  "fe" "VARCHAR(255)",
  "pele" "VARCHAR(255)",
  "olhos" "VARCHAR(255)",
  "cabelos" "VARCHAR(255)",
  "aparencia_outros" "VARCHAR(255)",
  "particularidades" "TEXT",
  "conceito" "TEXT",
  "tracos_personalidade" "TEXT",
  "ideais" "TEXT",
  "falhas" "TEXT",
  "medos" "TEXT",
  "aliados" "TEXT",
  "organizacoes" "TEXT",
  "historia" "TEXT",
  "anotacoes" "TEXT"
);

CREATE TABLE "particularidade" (
  "id" SERIAL PRIMARY KEY,
  "particularidade" "VARCHAR" NOT NULL,
  "efeito" "TEXT",
  "oposto" "INTEGER",
  "pontos" "INTEGER"
);

CREATE TABLE "particularidades_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "particularidade_id" "INTEGER"
);

CREATE TABLE "equipamentos" (
  "id" SERIAL PRIMARY KEY,
  "raridade" raridades,
  "peso" "INTEGER",
  "preco" "VARCHAR",
  "tipo" tipos_equipamentos,
  "descricao" "TEXT"
);

CREATE INDEX "ficha_personagem_index_0" ON "ficha_personagem" ("id", "user_id");

-- Índices para performance
CREATE INDEX idx_habilidades_personagem_ficha ON habilidades_personagem(ficha_personagem_id);
CREATE INDEX idx_arquetipos_personagem_arquetipo ON arquetipos_personagem(arquetipo_id);
CREATE INDEX idx_classes_personagem_classe ON classes_personagem(classe_id);
CREATE INDEX idx_compartilhamento_user ON compartilhamento_ficha(user_id);
CREATE INDEX idx_compartilhamento_token ON compartilhamento_ficha(link_token);
CREATE INDEX idx_ficha_snapshot_personagem ON ficha_snapshot(ficha_personagem_id);
CREATE INDEX idx_user_username ON "user"(username);
CREATE INDEX idx_ficha_created_at ON ficha_personagem(created_at);
CREATE INDEX idx_ficha_updated_at ON ficha_personagem(updated_at);
CREATE INDEX idx_ficha_user_origem ON ficha_personagem(user_id, origem_id);
CREATE INDEX idx_atributos_ficha ON atributos_personagem(ficha_personagem_id);
CREATE INDEX idx_atributos_tipo ON atributos_personagem(atributo);
CREATE INDEX idx_atributos_ficha_tipo ON atributos_personagem(ficha_personagem_id, atributo);
CREATE INDEX idx_habilidades_grau ON habilidades_personagem(grau_habilidade);
CREATE INDEX idx_feiticos_circulo ON feiticos(circulo);
CREATE INDEX idx_equipamentos_tipo ON equipamentos(tipo);
CREATE INDEX idx_compartilhamento_ativo ON compartilhamento_ficha(ativo) WHERE ativo = true;

ALTER TABLE "arquetipos_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "arquetipos_personagem" ADD FOREIGN KEY ("arquetipo_id") REFERENCES "arquetipo" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classes_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classes_personagem" ADD FOREIGN KEY ("classe_id") REFERENCES "classe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classes_feitico" ADD FOREIGN KEY ("feitico_id") REFERENCES "feiticos" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classe" ADD FOREIGN KEY ("requisitos_classe_id") REFERENCES "requisitos_classe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("origem_id") REFERENCES "origem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "habilidades_personagem" ADD FOREIGN KEY ("habilidade_id") REFERENCES "habilidade" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "habilidades_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "origem_custom" ("ficha_personagem_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "deslocamento_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "arquetipo" ADD FOREIGN KEY ("id") REFERENCES "caracteristica_arquetipo" ("arquetipo_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "resistencias_personagem" ("ficha_personagem_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "atributos_personagem" ("ficha_personagem_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "sentidos_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "habilidades_personagem" ADD FOREIGN KEY ("atributo_personagem_id") REFERENCES "sentidos_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "pv_pp_personagem" ("ficha_personagem_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "defesa" ("ficha_personagem_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("habilidade_assinatura") REFERENCES "habilidades_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "acoes_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "ataques_personagem" ("ficha_personagem_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "user" ADD FOREIGN KEY ("id") REFERENCES "user_authority" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "authorities" ADD FOREIGN KEY ("id") REFERENCES "user_authority" ("authority_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "dano_ataque" ADD FOREIGN KEY ("ataque_personagem_id") REFERENCES "ataques_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "feiticos_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "feiticos_personagem" ADD FOREIGN KEY ("feiticos_id") REFERENCES "feiticos" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "caracteristicas_arquetipos" ADD FOREIGN KEY ("arquetipo_id") REFERENCES "arquetipos_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "habilidades_classe" ADD FOREIGN KEY ("classe_id") REFERENCES "classe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "proficiencias_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "inventario_personagem" ADD FOREIGN KEY ("carga_personagem_id") REFERENCES "carga_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "carga_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "cunhagem_personagem" ADD FOREIGN KEY ("carga_personagem_id") REFERENCES "carga_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "descricao_personagem" ("ficha_personagem_id");

CREATE TABLE "compartilhamento_ficha" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" INTEGER NOT NULL,
  "user_id" INTEGER NOT NULL,
  "nivel_acesso" VARCHAR(20) CHECK (nivel_acesso IN ('VISUALIZACAO', 'EDICAO')),
  "link_token" VARCHAR(100) UNIQUE,
  "expiracao" TIMESTAMP,
  "ativo" BOOLEAN DEFAULT true,
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "ficha_snapshot" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" INTEGER NOT NULL,
  "versao" INTEGER NOT NULL,
  "motivo" VARCHAR(100),
  "dados_ficha" JSONB,
  "data_snapshot" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "feiticos_personagem_habilidades_personagem" (
  "feiticos_personagem_habilidade" INTEGER,
  "habilidades_personagem_id" SERIAL,
  PRIMARY KEY ("feiticos_personagem_habilidade", "habilidades_personagem_id")
);

ALTER TABLE "feiticos_personagem_habilidades_personagem" ADD FOREIGN KEY ("feiticos_personagem_habilidade") REFERENCES "feiticos_personagem" ("habilidade");

ALTER TABLE "feiticos_personagem_habilidades_personagem" ADD FOREIGN KEY ("habilidades_personagem_id") REFERENCES "habilidades_personagem" ("id");


ALTER TABLE "particularidades_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id");

ALTER TABLE "particularidades_personagem" ADD FOREIGN KEY ("particularidade_id") REFERENCES "particularidade" ("id");

ALTER TABLE "habilidades_origem" ADD FOREIGN KEY ("origem_id") REFERENCES "origem" ("id");

ALTER TABLE "habilidades_origem" ADD FOREIGN KEY ("origem_id") REFERENCES "origem_custom" ("id");

ALTER TABLE "origem_custom" ADD FOREIGN KEY ("id") REFERENCES "ficha_personagem" ("origem_id");

ALTER TABLE "inventario_personagem" ADD FOREIGN KEY ("equipamento_id") REFERENCES "equipamentos" ("id");

ALTER TABLE "linhagem" ADD FOREIGN KEY ("tamanho") REFERENCES "tamanho" ("nome");

ALTER TABLE "idiomas_linhagem" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id");

ALTER TABLE "deslocamento_linhagem" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id");

ALTER TABLE "atributos_linhagem" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id");

ALTER TABLE "itens_origem" ADD FOREIGN KEY ("origem_id") REFERENCES "origem" ("id");

ALTER TABLE "sentidos_linhagem" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id");

ALTER TABLE "caracteristicas_ancestralidade" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id");

ALTER TABLE "habilidades_arquetipo" ADD FOREIGN KEY ("arquetipo_id") REFERENCES "arquetipo" ("id");

ALTER TABLE "habilidades_arquetipo" ADD FOREIGN KEY ("habilidade_id") REFERENCES "habilidade" ("id");

ALTER TABLE "proficiencias_arquetipo" ADD FOREIGN KEY ("arquetipo_id") REFERENCES "arquetipo" ("id");

ALTER TABLE "proficiencias_classe" ADD FOREIGN KEY ("classe_id") REFERENCES "classe" ("id");

ALTER TABLE "uso_habilidade" ADD FOREIGN KEY ("habilidade_id") REFERENCES "habilidade" ("id");

ALTER TABLE "talento__poderes_arquetipo" ADD FOREIGN KEY ("arquetipo_id") REFERENCES "arquetipo" ("id");

ALTER TABLE "talento__poderes_arquetipo" ADD FOREIGN KEY ("requisitos_talentos_poderes_id") REFERENCES "requisitos_talentos_poderes" ("id");

ALTER TABLE "requisitos_talentos_poderes" ADD FOREIGN KEY ("talento_poder_requisito") REFERENCES "talento__poderes_arquetipo" ("id");

ALTER TABLE "requisitos_talentos_poderes" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id");

ALTER TABLE "atributos_origem" ADD FOREIGN KEY ("origem_id") REFERENCES "origem" ("id");

ALTER TABLE "origem_custom" ADD FOREIGN KEY ("id") REFERENCES "itens_origem" ("origem_id");

-- Constraints de validação
ALTER TABLE "atributos_personagem" ADD CONSTRAINT check_atributos_range 
  CHECK (valor BETWEEN 1 AND 20);

ALTER TABLE "atributos_personagem" ADD CONSTRAINT check_valor_temp_range 
  CHECK (valor_temp IS NULL OR valor_temp BETWEEN 1 AND 20);

ALTER TABLE "ficha_personagem" ADD CONSTRAINT check_experiencia_positiva 
  CHECK (experiencia >= 0);

ALTER TABLE "ficha_personagem" ADD CONSTRAINT check_nivel_sorte_range 
  CHECK (nivel_sorte >= 0 AND nivel_sorte <= 10);

ALTER TABLE "pv_pp_personagem" ADD CONSTRAINT check_pv_positivo 
  CHECK (pv_maximo > 0 AND pv_atual >= 0);

ALTER TABLE "pv_pp_personagem" ADD CONSTRAINT check_pp_positivo 
  CHECK (pp_maximo >= 0 AND pp_atual >= 0);

ALTER TABLE "user" ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE "feiticos" ADD CONSTRAINT check_circulo_valido CHECK (circulo BETWEEN 0 AND 9);
ALTER TABLE "arquetipos_personagem" ADD CONSTRAINT check_nivel_arquetipo_positivo CHECK (nivel_arquetipo > 0);
ALTER TABLE "classes_personagem" ADD CONSTRAINT check_nivel_classe_positivo CHECK (nivel > 0);
ALTER TABLE "equipamentos" ADD CONSTRAINT check_peso_positivo CHECK (peso >= 0);
ALTER TABLE "carga_personagem" ADD CONSTRAINT check_carga_valida CHECK (carga_atual >= 0 AND capacidade_carga >= 0);

COMMENT ON TABLE "ficha_personagem" IS 'Armazena informações básicas de personagens do RPG';
COMMENT ON COLUMN "ficha_personagem"."habilidade_assinatura" IS 'Referência à habilidade na qual o personagem é especialista';
COMMENT ON COLUMN "ficha_personagem"."versao" IS 'Controle de versionamento da ficha';
COMMENT ON TABLE "compartilhamento_ficha" IS 'Controla compartilhamento de fichas entre usuários';
COMMENT ON TABLE "ficha_snapshot" IS 'Snapshots para versionamento de fichas em momentos importantes';

ALTER TABLE "compartilhamento_ficha" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;
ALTER TABLE "compartilhamento_ficha" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id") ON DELETE CASCADE;

ALTER TABLE "ficha_snapshot" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;

CREATE SCHEMA IF NOT EXISTS archive;

CREATE TABLE archive.ficha_personagem (LIKE ficha_personagem INCLUDING ALL);
CREATE TABLE archive.atributos_personagem (LIKE atributos_personagem INCLUDING ALL);
CREATE TABLE archive.habilidades_personagem (LIKE habilidades_personagem INCLUDING ALL);

-- Procedimento para arquivar fichas não acessadas há 1 ano
CREATE OR REPLACE FUNCTION archive_old_characters() 
RETURNS INTEGER
LANGUAGE plpgsql AS $$
DECLARE
    archived_count INTEGER := 0;
BEGIN
    -- Mover fichas antigas para o schema archive
    WITH old_fichas AS (
        SELECT id FROM ficha_personagem
        WHERE updated_at < NOW() - INTERVAL '1 year'
           OR (updated_at IS NULL AND created_at < NOW() - INTERVAL '1 year')
    )
    INSERT INTO archive.ficha_personagem
    SELECT * FROM ficha_personagem
    WHERE id IN (SELECT id FROM old_fichas);
    
    GET DIAGNOSTICS archived_count = ROW_COUNT;
    
    DELETE FROM ficha_personagem 
    WHERE updated_at < NOW() - INTERVAL '1 year'
       OR (updated_at IS NULL AND created_at < NOW() - INTERVAL '1 year');
    
    RETURN archived_count;
END; $$;

-- Trigger para atualizar campo updated_at automaticamente
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_ficha_personagem_updated_at BEFORE UPDATE
    ON ficha_personagem FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_atributos_personagem_updated_at BEFORE UPDATE
    ON atributos_personagem FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_habilidades_personagem_updated_at BEFORE UPDATE
    ON habilidades_personagem FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_pv_pp_personagem_updated_at BEFORE UPDATE
    ON pv_pp_personagem FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_defesa_updated_at BEFORE UPDATE
    ON defesa FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_inventario_personagem_updated_at BEFORE UPDATE
    ON inventario_personagem FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_ataques_personagem_updated_at BEFORE UPDATE
    ON ataques_personagem FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_feiticos_personagem_updated_at BEFORE UPDATE
    ON feiticos_personagem FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

ALTER TABLE "defesa" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;
ALTER TABLE "pv_pp_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;
ALTER TABLE "atributos_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;
ALTER TABLE "resistencias_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;
ALTER TABLE "ataques_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;
ALTER TABLE "descricao_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE CASCADE;

ALTER TABLE "habilidades_personagem" DROP CONSTRAINT IF EXISTS habilidades_personagem_atributo_personagem_id_fkey;
ALTER TABLE "habilidades_personagem" ADD FOREIGN KEY ("atributo_personagem_id") REFERENCES "atributos_personagem" ("id") ON DELETE RESTRICT;

-- Função para limpeza de tokens expirados de compartilhamento
CREATE OR REPLACE FUNCTION cleanup_expired_sharing_tokens()
RETURNS INTEGER
LANGUAGE plpgsql AS $$
DECLARE
    cleaned_count INTEGER := 0;
BEGIN
    UPDATE compartilhamento_ficha 
    SET ativo = false 
    WHERE expiracao < CURRENT_TIMESTAMP AND ativo = true;
    
    GET DIAGNOSTICS cleaned_count = ROW_COUNT;
    RETURN cleaned_count;
END; $$;

-- Função para criar snapshot da ficha
CREATE OR REPLACE FUNCTION create_ficha_snapshot(
    p_ficha_id INTEGER,
    p_motivo VARCHAR(100) DEFAULT 'Manual'
)
RETURNS INTEGER
LANGUAGE plpgsql AS $$
DECLARE
    v_versao INTEGER;
    v_dados JSONB;
    v_snapshot_id INTEGER;
BEGIN
    -- Incrementar versão
    UPDATE ficha_personagem 
    SET versao = versao + 1, updated_at = CURRENT_TIMESTAMP
    WHERE id = p_ficha_id
    RETURNING versao INTO v_versao;
    
    -- Criar dados JSON da ficha
    SELECT json_build_object(
        'ficha', row_to_json(fp.*),
        'atributos', row_to_json(ap.*),
        'created_at', CURRENT_TIMESTAMP
    )::JSONB INTO v_dados
    FROM ficha_personagem fp
    LEFT JOIN atributos_personagem ap ON fp.id = ap.ficha_personagem_id
    WHERE fp.id = p_ficha_id;
    
    -- Inserir snapshot
    INSERT INTO ficha_snapshot (ficha_personagem_id, versao, motivo, dados_ficha)
    VALUES (p_ficha_id, v_versao, p_motivo, v_dados)
    RETURNING id INTO v_snapshot_id;
    
    RETURN v_snapshot_id;
END; $$;

-- Views úteis para consultas comuns
CREATE VIEW vw_ficha_completa AS
SELECT 
    fp.*,
    u.username,
    o.nome as origem_nome,
    l.nome as linhagem_nome,
    (SELECT valor FROM atributos_personagem WHERE ficha_personagem_id = fp.id AND atributo = 'AGILIDADE') as agilidade,
    (SELECT valor FROM atributos_personagem WHERE ficha_personagem_id = fp.id AND atributo = 'CONSTITUICAO') as constituicao,
    (SELECT valor FROM atributos_personagem WHERE ficha_personagem_id = fp.id AND atributo = 'FORCA') as forca,
    (SELECT valor FROM atributos_personagem WHERE ficha_personagem_id = fp.id AND atributo = 'INFLUENCIA') as influencia,
    (SELECT valor FROM atributos_personagem WHERE ficha_personagem_id = fp.id AND atributo = 'MENTE') as mente,
    (SELECT valor FROM atributos_personagem WHERE ficha_personagem_id = fp.id AND atributo = 'PRESENCA') as presenca,
    pvpp.pv_atual, pvpp.pv_maximo, pvpp.pp_atual, pvpp.pp_maximo,
    d.defesa_personagem
FROM ficha_personagem fp
JOIN "user" u ON fp.user_id = u.id
LEFT JOIN origem o ON fp.origem_id = o.id
LEFT JOIN linhagem l ON fp.linhagem_id = l.id
LEFT JOIN pv_pp_personagem pvpp ON fp.id = pvpp.ficha_personagem_id
LEFT JOIN defesa d ON fp.id = d.ficha_personagem_id;

-- View para atributos pivotados
CREATE VIEW vw_atributos_personagem AS
SELECT 
    ficha_personagem_id,
    MAX(CASE WHEN atributo = 'AGILIDADE' THEN valor END) as agilidade,
    MAX(CASE WHEN atributo = 'AGILIDADE' THEN valor_temp END) as agilidade_temp,
    MAX(CASE WHEN atributo = 'CONSTITUICAO' THEN valor END) as constituicao,
    MAX(CASE WHEN atributo = 'CONSTITUICAO' THEN valor_temp END) as constituicao_temp,
    MAX(CASE WHEN atributo = 'FORCA' THEN valor END) as forca,
    MAX(CASE WHEN atributo = 'FORCA' THEN valor_temp END) as forca_temp,
    MAX(CASE WHEN atributo = 'INFLUENCIA' THEN valor END) as influencia,
    MAX(CASE WHEN atributo = 'INFLUENCIA' THEN valor_temp END) as influencia_temp,
    MAX(CASE WHEN atributo = 'MENTE' THEN valor END) as mente,
    MAX(CASE WHEN atributo = 'MENTE' THEN valor_temp END) as mente_temp,
    MAX(CASE WHEN atributo = 'PRESENCA' THEN valor END) as presenca,
    MAX(CASE WHEN atributo = 'PRESENCA' THEN valor_temp END) as presenca_temp
FROM atributos_personagem
GROUP BY ficha_personagem_id;

-- View para fichas compartilhadas
CREATE VIEW vw_fichas_compartilhadas AS
SELECT 
    cf.*,
    fp.nome_personagem,
    u_owner.username as proprietario,
    u_shared.username as usuario_compartilhado
FROM compartilhamento_ficha cf
JOIN ficha_personagem fp ON cf.ficha_personagem_id = fp.id
JOIN "user" u_owner ON fp.user_id = u_owner.id
JOIN "user" u_shared ON cf.user_id = u_shared.id
WHERE cf.ativo = true;

-- View para estatísticas do sistema
CREATE VIEW vw_estatisticas_sistema AS
SELECT 
    (SELECT COUNT(*) FROM ficha_personagem) as total_fichas,
    (SELECT COUNT(*) FROM "user") as total_usuarios,
    (SELECT COUNT(*) FROM compartilhamento_ficha WHERE ativo = true) as compartilhamentos_ativos,
    (SELECT COUNT(*) FROM ficha_snapshot) as total_snapshots,
    (SELECT COUNT(*) FROM archive.ficha_personagem) as fichas_arquivadas;

ALTER TABLE "habilidades_personagem" ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE "habilidades_personagem" ADD COLUMN "updated_at" TIMESTAMP;

ALTER TABLE "pv_pp_personagem" ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE "pv_pp_personagem" ADD COLUMN "updated_at" TIMESTAMP;

ALTER TABLE "defesa" ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE "defesa" ADD COLUMN "updated_at" TIMESTAMP;

ALTER TABLE "inventario_personagem" ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE "inventario_personagem" ADD COLUMN "updated_at" TIMESTAMP;

ALTER TABLE "ataques_personagem" ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE "ataques_personagem" ADD COLUMN "updated_at" TIMESTAMP;

ALTER TABLE "feiticos_personagem" ADD COLUMN "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE "feiticos_personagem" ADD COLUMN "updated_at" TIMESTAMP;

-- Função transacional para criar uma ficha completa
CREATE OR REPLACE FUNCTION criar_ficha_completa(
    p_nome_personagem VARCHAR,
    p_user_id INTEGER,
    p_origem_id INTEGER,
    p_linhagem_id INTEGER,
    p_atributos JSONB
)
RETURNS INTEGER
LANGUAGE plpgsql AS $$
DECLARE
    v_ficha_id INTEGER;
    v_atributo RECORD;
BEGIN
    -- Iniciar transação implícita
    
    -- Criar ficha principal
    INSERT INTO ficha_personagem (nome_personagem, user_id, origem_id, linhagem_id, habilidade_assinatura)
    VALUES (p_nome_personagem, p_user_id, p_origem_id, p_linhagem_id, 1) -- habilidade_assinatura temporária
    RETURNING id INTO v_ficha_id;
    
    FOR v_atributo IN 
        SELECT key as atributo_nome, value::INTEGER as valor
        FROM jsonb_each_text(p_atributos)
    LOOP
        INSERT INTO atributos_personagem (ficha_personagem_id, atributo, valor)
        VALUES (v_ficha_id, v_atributo.atributo_nome::atributos, v_atributo.valor);
    END LOOP;
    
    -- Criar registros dependentes padrão
    INSERT INTO pv_pp_personagem (ficha_personagem_id) VALUES (v_ficha_id);
    INSERT INTO defesa (ficha_personagem_id, armadura) VALUES (v_ficha_id, 0);
    INSERT INTO carga_personagem (ficha_personagem_id, carga_atual, capacidade_carga) VALUES (v_ficha_id, 0, 50);
    INSERT INTO descricao_personagem (ficha_personagem_id) VALUES (v_ficha_id);
    
    -- Criar snapshot inicial
    PERFORM create_ficha_snapshot(v_ficha_id, 'Criação da ficha');
    
    RETURN v_ficha_id;
    
EXCEPTION
    WHEN OTHERS THEN
        -- Em caso de erro, a transação será automaticamente revertida
        RAISE;
END; $$;

-- Função transacional para atualizar atributos com histórico
CREATE OR REPLACE FUNCTION atualizar_atributos_personagem(
    p_ficha_id INTEGER,
    p_atributos JSONB,
    p_motivo VARCHAR(100) DEFAULT 'Atualização de atributos'
)
RETURNS BOOLEAN
LANGUAGE plpgsql AS $$
DECLARE
    v_atributo RECORD;
    v_old_value INTEGER;
BEGIN
    -- Atualizar cada atributo
    FOR v_atributo IN 
        SELECT key as atributo_nome, value::INTEGER as novo_valor
        FROM jsonb_each_text(p_atributos)
    LOOP
        -- Buscar valor anterior para auditoria
        SELECT valor INTO v_old_value
        FROM atributos_personagem
        WHERE ficha_personagem_id = p_ficha_id 
        AND atributo = v_atributo.atributo_nome::atributos;
        
        -- Atualizar valor
        UPDATE atributos_personagem 
        SET valor = v_atributo.novo_valor
        WHERE ficha_personagem_id = p_ficha_id 
        AND atributo = v_atributo.atributo_nome::atributos;
        
        -- Log da mudança
        INSERT INTO log_alteracoes (ficha_personagem_id, tabela, campo, valor_anterior, valor_novo, motivo)
        VALUES (p_ficha_id, 'atributos_personagem', v_atributo.atributo_nome, v_old_value::TEXT, v_atributo.novo_valor::TEXT, p_motivo);
    END LOOP;
    
    -- Criar snapshot após mudanças significativas
    PERFORM create_ficha_snapshot(p_ficha_id, p_motivo);
    
    RETURN TRUE;
    
EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END; $$;

-- Função para transferir equipamento entre personagens
CREATE OR REPLACE FUNCTION transferir_equipamento(
    p_ficha_origem INTEGER,
    p_ficha_destino INTEGER,
    p_equipamento_id INTEGER,
    p_quantidade INTEGER
)
RETURNS BOOLEAN
LANGUAGE plpgsql AS $$
DECLARE
    v_carga_origem INTEGER;
    v_carga_destino INTEGER;
    v_quantidade_disponivel INTEGER;
    v_peso_item INTEGER;
    v_capacidade_destino INTEGER;
BEGIN
    -- Buscar cargas dos personagens
    SELECT id INTO v_carga_origem FROM carga_personagem WHERE ficha_personagem_id = p_ficha_origem;
    SELECT id INTO v_carga_destino FROM carga_personagem WHERE ficha_personagem_id = p_ficha_destino;
    
    -- Verificar quantidade disponível
    SELECT quantidade_item INTO v_quantidade_disponivel
    FROM inventario_personagem 
    WHERE carga_personagem_id = v_carga_origem AND equipamento_id = p_equipamento_id;
    
    IF v_quantidade_disponivel < p_quantidade THEN
        RAISE EXCEPTION 'Quantidade insuficiente. Disponível: %, Solicitado: %', v_quantidade_disponivel, p_quantidade;
    END IF;
    
    -- Buscar peso do item
    SELECT peso INTO v_peso_item FROM equipamentos WHERE id = p_equipamento_id;
    
    -- Verificar capacidade do destino
    SELECT capacidade_carga INTO v_capacidade_destino 
    FROM carga_personagem WHERE id = v_carga_destino;
    
    -- Transferir item
    UPDATE inventario_personagem 
    SET quantidade_item = quantidade_item - p_quantidade
    WHERE carga_personagem_id = v_carga_origem AND equipamento_id = p_equipamento_id;
    
    -- Adicionar ao destino ou criar novo registro
    INSERT INTO inventario_personagem (carga_personagem_id, equipamento_id, quantidade_item, peso_item)
    VALUES (v_carga_destino, p_equipamento_id, p_quantidade, v_peso_item)
    ON CONFLICT (carga_personagem_id, equipamento_id) 
    DO UPDATE SET quantidade_item = inventario_personagem.quantidade_item + p_quantidade;
    
    -- Atualizar cargas
    UPDATE carga_personagem 
    SET carga_atual = carga_atual - (v_peso_item * p_quantidade)
    WHERE id = v_carga_origem;
    
    UPDATE carga_personagem 
    SET carga_atual = carga_atual + (v_peso_item * p_quantidade)
    WHERE id = v_carga_destino;
    
    RETURN TRUE;
    
EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END; $$;

-- Tabela de log para auditoria completa
CREATE TABLE "log_alteracoes" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" INTEGER NOT NULL,
  "user_id" INTEGER,
  "tabela" VARCHAR(100) NOT NULL,
  "campo" VARCHAR(100),
  "valor_anterior" TEXT,
  "valor_novo" TEXT,
  "motivo" VARCHAR(100),
  "ip_address" INET,
  "user_agent" TEXT,
  "created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
