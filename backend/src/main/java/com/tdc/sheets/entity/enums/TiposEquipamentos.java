package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de equipamentos conforme definido no sistema TDC
 */
public enum TiposEquipamentos {
    PEDRAS_PRECIOSAS("Pedras Preciosas"),
    OBRAS_ARTES("Obras de Arte"),
    MERCADORIAS_TECIDOS("Mercadorias - Tecidos"),
    MERCADORIAS_METAIS("Mercadorias - Metais"),
    MERCADORIAS_ANIMAIS("Mercadorias - Animais"),
    MERCADORIAS_PROVISOES("Mercadorias - Provisões"),
    AVENTURA_EXPLORACAO_VIAGEM("Aventura, Exploração e Viagem"),
    COMIDA_BEBIDA("Comida e Bebida"),
    FEITICARIA_CONDUITES("Feitiçaria - Conduítes"),
    FEITICARIA_POCOES("Feitiçaria - Poções"),
    FEITICARIA_OUTROS("Feitiçaria - Outros"),
    FERRAMENTAS_HABILIDADE("Ferramentas de Habilidade"),
    FERRAMENTAS_OFICIO("Ferramentas de Ofício"),
    FONTES_LUZ("Fontes de Luz"),
    HERBALISMO("Herbalismo"),
    INSTRUMENTOS_MUSICAIS("Instrumentos Musicais"),
    MUNICOES("Munições"),
    PRODUTOS_ALQUIMICOS("Produtos Alquímicos"),
    RECIPIENTES("Recipientes"),
    UTILITARIOS("Utilitários"),
    VENENOS("Venenos"),
    VESTIMENTAS("Vestimentas"),
    ARMAS_SIMPLES("Armas Simples"),
    ARMAS_MARCIAIS("Armas Marciais"),
    ARMAS_COMPLEXAS("Armas Complexas"),
    ARMAS_PESADAS("Armas Pesadas"),
    PROTECOES_LEVES("Proteções Leves"),
    PROTECOES_MEDIAS("Proteções Médias"),
    PROTECOES_PESADAS("Proteções Pesadas"),
    ESCUDOS("Escudos"),
    TRALHA("Tralha"),
    MATERIAIS_INGREDIENTES("Materiais e Ingredientes"),
    MATERIAIS_CRIACAO("Materiais de Criação");

    private final String displayName;

    TiposEquipamentos(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
