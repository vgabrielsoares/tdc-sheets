package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de dano conforme definido no sistema TDC
 */
public enum TiposDano {
    ACIDO("Ácido"),
    ELETRICO("Elétrico"),
    CORTANTE("Cortante"),
    PERFURANTE("Perfurante"),
    IMPACTO("Impacto"),
    FOGO("Fogo"),
    FRIO("Frio"),
    INTERNO("Interno"),
    MENTAL("Mental"),
    MISTICO("Místico"),
    PROFANO("Profano"),
    SAGRADO("Sagrado"),
    SONORO("Sonoro"),
    VENENO("Veneno");

    private final String displayName;

    TiposDano(String displayName) {
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
