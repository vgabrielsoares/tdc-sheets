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
  PRIMARY KEY ("id")
);

CREATE TABLE "defesa" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "defesa_personagem" "INTEGER" NOT NULL DEFAULT 15,
  "armadura" "INTEGER" NOT NULL,
  "vestindo_armadura" "BOOLEAN" DEFAULT false,
  "limite_agilidade" "INTEGER",
  "outro_mod" "INTEGER",
  PRIMARY KEY ("id")
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
  PRIMARY KEY ("id")
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
  "defesa_15" integer NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "talento__poderes_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER",
  "requisitos_talentos_poderes_id" integer,
  "nome" varchar NOT NULL,
  "descricao" text NOT NULL,
  "linhagem" boolean,
  PRIMARY KEY ("id")
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
  "linhagem_id" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "arquetipos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "arquetipo_id" "INTEGER" NOT NULL,
  "nivel_arquetipo" "INTEGER" NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "classe" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "descricao" text,
  "requisitos_classe_id" "INTEGER" NOT NULL,
  "defesa_5" integer,
  "defesa_10" integer,
  "defesa_15" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "classes_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "classe_id" "INTEGER" NOT NULL,
  "nivel" "INTEGER" NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "requisitos_classe" (
  "id" SERIAL PRIMARY KEY,
  "classe_id" "INTEGER" NOT NULL,
  "nivel_minimo" "INTEGER" NOT NULL,
  "primeiro_arquetipo" arquetipos_nome NOT NULL,
  "segundo_arquetipo" arquetipos_nome,
  "primeiro_arquetipo_nivel" "INTEGER" NOT NULL,
  "segundo_arquetipo_nivel" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "habilidade" (
  "id" SERIAL PRIMARY KEY,
  "pericia" "VARCHAR" NOT NULL,
  "descricao" "TEXT",
  "atributo_chave" atributos,
  "penalidade_carga" boolean,
  "instrumento" boolean,
  "proficiencia" boolean,
  "combate" boolean,
  PRIMARY KEY ("id")
);

CREATE TABLE "uso_habilidade" (
  "id" SERIAL PRIMARY KEY,
  "habilidade_id" "INTEGER" NOT NULL,
  "nome" varchar,
  "descricao" text,
  PRIMARY KEY ("id")
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
  PRIMARY KEY ("id")
);

CREATE TABLE "deslocamento_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "deslocamento" tipos_deslocamento NOT NULL,
  "valor" "INTEGER" NOT NULL,
  "bonus" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "origem" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "descricao" text,
  "habilidade_especial" "TEXT",
  PRIMARY KEY ("id")
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
  "valor_generico_negativo" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "itens_origem" (
  "id" SERIAL PRIMARY KEY,
  "origem_id" "INTEGER" NOT NULL,
  "equipamento_id" integer,
  "quantidade" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "habilidades_origem" (
  "id" SERIAL PRIMARY KEY,
  "origem_id" "INTEGER" NOT NULL,
  "habilidade_id" "INTEGER" NOT NULL,
  "fixa" "BOOLEAN",
  PRIMARY KEY ("id")
);

CREATE TABLE "origem_custom" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER",
  "nome" "VARCHAR",
  "descricao" text,
  "habilidade_especial" "TEXT",
  PRIMARY KEY ("id")
);

CREATE TABLE "linhagem" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "tamanho" tipos_tamanho,
  "altura_media" varchar,
  "peso_medio" "VARCHAR",
  "maioridade" "VARCHAR",
  "expectativa_vida" "VARCHAR",
  "descricao" "TEXT",
  PRIMARY KEY ("id")
);

CREATE TABLE "deslocamento_linhagem" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "deslocamento" tipos_deslocamento,
  "valor" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "idiomas_linhagem" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "idioma" idiomas,
  "alfabeto" alfabetos,
  PRIMARY KEY ("id")
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
  "valor_generico_negativo" integer,
  PRIMARY KEY ("id")
);

CREATE TABLE "sentidos_linhagem" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "audicao" tipos_sentidos NOT NULL,
  "olfato" tipos_sentidos NOT NULL,
  "visao" tipos_sentidos NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "caracteristicas_ancestralidade" (
  "id" SERIAL PRIMARY KEY,
  "linhagem_id" integer,
  "nome" varchar,
  "descricao" text,
  "fixa" boolean,
  PRIMARY KEY ("id")
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
  "tenacidade" integer,
  PRIMARY KEY ("id")
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
  "aprimoramento" "TEXT",
  PRIMARY KEY ("id")
);

CREATE TABLE "classes_feitico" (
  "id" SERIAL PRIMARY KEY,
  "feitico_id" "INTEGER" NOT NULL,
  "classe_feitico" classes_feiticos,
  PRIMARY KEY ("id")
);

CREATE TABLE "caracteristica_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "nome" "VARCHAR" NOT NULL,
  "arquetipo_id" "INTEGER" NOT NULL,
  "descricao" "TEXT" NOT NULL,
  "nivel" integer NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "habilidades_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER" NOT NULL,
  "habilidade_id" "TEXT" NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "proficiencias_arquetipo" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER" NOT NULL,
  "proficiencia" tipos_proficiencia,
  PRIMARY KEY ("id")
);

CREATE TABLE "proficiencias_classe" (
  "id" SERIAL PRIMARY KEY,
  "classe_id" "INTEGER" NOT NULL,
  "proficiencia" tipos_proficiencia,
  PRIMARY KEY ("id")
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
  "absorcao" "BOOLEAN" DEFAULT false,
  PRIMARY KEY ("id")
);

CREATE TABLE "atributos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "agilidade" "INTEGER" NOT NULL,
  "agilidade_temp" "INTEGER",
  "constituicao" "INTEGER" NOT NULL,
  "constituicao_temp" "INTEGER",
  "forca" "INTEGER" NOT NULL,
  "forca_temp" "INTEGER",
  "influencia" "INTEGER" NOT NULL,
  "influencia_temp" "INTEGER",
  "mente" "INTEGER" NOT NULL,
  "mente_temp" "INTEGER",
  "presenca" "INTEGER" NOT NULL,
  "presenca_temp" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "sentidos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "audicao" tipos_sentidos NOT NULL,
  "mod_audicao" "INTEGER",
  "olfato" tipos_sentidos NOT NULL,
  "mod_olfato" "INTEGER",
  "visao" tipos_sentidos NOT NULL,
  "mod_visao" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "acoes_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "acao" "VARCHAR" NOT NULL,
  "acao_disponivel" "BOOLEAN" NOT NULL DEFAULT false,
  PRIMARY KEY ("id")
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
  PRIMARY KEY ("id")
);

CREATE TABLE "user" (
  "id" SERIAL PRIMARY KEY,
  "nome_completo" "VARCHAR" NOT NULL,
  "username" "VARCHAR" NOT NULL,
  "senha" "VARCHAR" NOT NULL,
  "created_at" "TIMESTAMP" NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "authorities" (
  "id" SERIAL PRIMARY KEY,
  "authority" "VARCHAR" NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "user_authority" (
  "id" "INTEGER" NOT NULL,
  "user_id" SERIAL PRIMARY KEY,
  "authority_id" "INTEGER" NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "dano_ataque" (
  "id" SERIAL PRIMARY KEY,
  "ataque_personagem_id" "INTEGER",
  "dado" "VARCHAR(7)",
  "mod" "INTEGER",
  "bonus" "INTEGER",
  "critico" "VARCHAR(8)",
  PRIMARY KEY ("id")
);

CREATE TABLE "feiticos_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "feiticos_id" "INTEGER",
  "habilidade" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "caracteristicas_arquetipos" (
  "id" SERIAL PRIMARY KEY,
  "arquetipo_id" "INTEGER",
  "nivel" "INTEGER",
  "descricao" "TEXT",
  PRIMARY KEY ("id")
);

CREATE TABLE "habilidades_classe" (
  "id" SERIAL PRIMARY KEY,
  "classe_id" "INTEGER",
  "nivel" "INTEGER",
  "descricao" "TEXT",
  "primeiro_arquetipo" arquetipos_nome NOT NULL,
  "segundo_arquetipo" arquetipos_nome,
  "primeiro_arquetipo_nivel" "INTEGER" NOT NULL,
  "segundo_arquetipo_nivel" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "proficiencias_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "proficiencia" "TIPOS_PROFICIENCIA" NOT NULL,
  "proficiente" "BOOLEAN" NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "inventario_personagem" (
  "id" SERIAL PRIMARY KEY,
  "carga_personagem_id" "INTEGER" NOT NULL,
  "equipamento_id" "INTEGER",
  "item" "VARCHAR(255)",
  "descricao_item" "TEXT",
  "quantidade_item" "INTEGER",
  "peso_item" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "carga_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "carga_atual" "INTEGER",
  "capacidade_carga" "INTEGER",
  "bonus_capacidade_carga" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "cunhagem_personagem" (
  "id" SERIAL PRIMARY KEY,
  "carga_personagem_id" "INTEGER" NOT NULL,
  "c_fisico" "INTEGER",
  "po_fisico" "INTEGER",
  "pp_fisico" "INTEGER",
  "c_banco" "INTEGER",
  "po_banco" "INTEGER",
  "pp_banco" "INTEGER",
  PRIMARY KEY ("id")
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
  "anotacoes" "TEXT",
  PRIMARY KEY ("id")
);

CREATE TABLE "particularidade" (
  "id" SERIAL PRIMARY KEY,
  "particularidade" "VARCHAR" NOT NULL,
  "efeito" "TEXT",
  "oposto" "INTEGER",
  "pontos" "INTEGER",
  PRIMARY KEY ("id")
);

CREATE TABLE "particularidades_personagem" (
  "id" SERIAL PRIMARY KEY,
  "ficha_personagem_id" "INTEGER" NOT NULL,
  "particularidade_id" "VARCHAR",
  PRIMARY KEY ("id")
);

CREATE TABLE "equipamentos" (
  "id" SERIAL PRIMARY KEY,
  "raridade" raridades,
  "peso" "INTEGER",
  "preco" "VARCHAR",
  "tipo" tipos_equipamentos,
  "descricao" "TEXT",
  PRIMARY KEY ("id")
);

CREATE INDEX "ficha_personagem_index_0" ON "ficha_personagem" ("id", "user_id");

ALTER TABLE "arquetipos_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "arquetipo" ADD FOREIGN KEY ("id") REFERENCES "arquetipos_personagem" ("arquetipo_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classes_personagem" ADD FOREIGN KEY ("ficha_personagem_id") REFERENCES "ficha_personagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classe" ADD FOREIGN KEY ("id") REFERENCES "classes_personagem" ("classe_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classes_feitico" ADD FOREIGN KEY ("feitico_id") REFERENCES "feiticos" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "classe" ADD FOREIGN KEY ("requisitos_classe_id") REFERENCES "requisitos_classe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("origem_id") REFERENCES "origem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "habilidades_personagem" ADD FOREIGN KEY ("habilidade_id") REFERENCES "habilidade" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "ficha_personagem" ADD FOREIGN KEY ("id") REFERENCES "habilidades_personagem" ("ficha_personagem_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

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

ALTER TABLE "talento__poderes_arquetipo" ADD FOREIGN KEY ("nome") REFERENCES "requisitos_talentos_poderes" ("id");

ALTER TABLE "requisitos_talentos_poderes" ADD FOREIGN KEY ("talento_poder_requisito") REFERENCES "talento__poderes_arquetipo" ("id");

ALTER TABLE "requisitos_talentos_poderes" ADD FOREIGN KEY ("linhagem_id") REFERENCES "linhagem" ("id");

ALTER TABLE "atributos_origem" ADD FOREIGN KEY ("origem_id") REFERENCES "origem" ("id");

ALTER TABLE "origem_custom" ADD FOREIGN KEY ("id") REFERENCES "itens_origem" ("origem_id");
