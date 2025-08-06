package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de deslocamento conforme definido no sistema TDC
 */
public enum TiposDeslocamento {
    ANDANDO("Andando"),
    VOANDO("Voando"),
    NADANDO("Nadando"),
    ESCALANDO("Escalando"),
    ESCAVANDO("Escavando");

    private final String displayName;

    TiposDeslocamento(String displayName) {
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
