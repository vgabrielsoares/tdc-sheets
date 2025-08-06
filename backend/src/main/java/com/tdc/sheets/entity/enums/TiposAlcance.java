package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de alcance conforme definido no sistema TDC
 */
public enum TiposAlcance {
    PESSOAL("Pessoal"),
    ADJACENTE("Adjacente"),
    TOQUE("Toque"),
    CURTO("Curto"),
    MEDIO("Médio"),
    LONGO("Longo"),
    MUITO_LONGO("Muito Longo"),
    ILIMITADO("Ilimitado");

    private final String displayName;

    TiposAlcance(String displayName) {
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
