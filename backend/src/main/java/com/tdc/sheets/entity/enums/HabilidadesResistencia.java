package com.tdc.sheets.entity.enums;

/**
 * Enum para habilidades de resistência conforme definido no sistema TDC
 */
public enum HabilidadesResistencia {
    DETERMINACAO("Determinação"),
    REFLEXO("Reflexo"),
    TENACIDADE("Tenacidade"),
    VIGOR("Vigor");

    private final String displayName;

    HabilidadesResistencia(String displayName) {
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
