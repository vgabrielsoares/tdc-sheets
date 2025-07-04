package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de sentidos conforme definido no sistema TDC
 */
public enum TiposSentidos {
    COMUM("Comum"),
    AGUCADO("Aguçado"),
    CEGO("Cego"),
    SURDO("Surdo");

    private final String displayName;

    TiposSentidos(String displayName) {
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
