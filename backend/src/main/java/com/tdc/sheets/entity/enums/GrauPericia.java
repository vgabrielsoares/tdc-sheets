package com.tdc.sheets.entity.enums;

/**
 * Enum para os graus de perícia/habilidade no sistema TDC
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
public enum GrauPericia {
    LEIGO("Leigo", 0),
    ADEPTO("Adepto", 1),
    VERSADO("Versado", 2),
    MESTRE("Mestre", 3),
    APEX_HUMANO("Ápex Humano", 4);

    private final String displayName;
    private final int bonus;

    GrauPericia(String displayName, int bonus) {
        this.displayName = displayName;
        this.bonus = bonus;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
