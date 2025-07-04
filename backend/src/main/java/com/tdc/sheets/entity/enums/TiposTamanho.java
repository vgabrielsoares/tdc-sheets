package com.tdc.sheets.entity.enums;

/**
 * Enum para os tipos de tamanho dos personagens no sistema TDC
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
public enum TiposTamanho {
    MINUSCULO("Minúsculo"),
    PEQUENO("Pequeno"),
    MEDIO("Médio"),
    GRANDE("Grande"),
    ENORME("Enorme"),
    COLOSSAL("Colossal");

    private final String displayName;

    TiposTamanho(String displayName) {
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
