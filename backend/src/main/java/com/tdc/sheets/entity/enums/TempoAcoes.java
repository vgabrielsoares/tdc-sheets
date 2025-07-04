package com.tdc.sheets.entity.enums;

/**
 * Enum para tempo de ações conforme definido no sistema TDC
 */
public enum TempoAcoes {
    COMPLETA("Ação Completa"),
    REACAO("Reação"),
    MAIOR("Ação Maior"),
    DUAS_MENORES("Duas Ações Menores"),
    UMA_MENOR("Uma Ação Menor"),
    LIVRE("Ação Livre");

    private final String displayName;

    TempoAcoes(String displayName) {
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
