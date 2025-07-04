package com.tdc.sheets.entity.enums;

/**
 * Enum para raridades de equipamentos conforme definido no sistema TDC
 */
public enum Raridades {
    COMUM("Comum"),
    ATIPICO("Atípico"),
    RARO("Raro"),
    MUITO_RARO("Muito Raro"),
    LENDARIO("Lendário");

    private final String displayName;

    Raridades(String displayName) {
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
