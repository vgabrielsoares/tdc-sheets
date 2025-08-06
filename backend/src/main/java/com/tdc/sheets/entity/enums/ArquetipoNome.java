package com.tdc.sheets.entity.enums;

/**
 * Enum para os tipos de arquétipos disponíveis no sistema TDC
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
public enum ArquetipoNome {
    ACADEMICO("Acadêmico"),
    ACOLITO("Acólito"),
    COMBATENTE("Combatente"),
    FEITICEIRO("Feiticeiro"),
    LADINO("Ladino"),
    NATURAL("Natural");

    private final String displayName;

    ArquetipoNome(String displayName) {
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
