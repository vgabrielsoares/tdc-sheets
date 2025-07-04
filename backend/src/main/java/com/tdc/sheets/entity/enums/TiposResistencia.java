package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de resistência conforme definido no sistema TDC
 */
public enum TiposResistencia {
    ATAQUE("Ataque"),
    RESISTENCIA("Resistência"),
    RESISTENCIA_PARCIAL("Resistência Parcial"),
    AUTOMATICO("Automático");

    private final String displayName;

    TiposResistencia(String displayName) {
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
