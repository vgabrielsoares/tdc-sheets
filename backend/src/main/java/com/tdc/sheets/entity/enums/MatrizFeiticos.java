package com.tdc.sheets.entity.enums;

/**
 * Enum para as matrizes mágicas disponíveis no sistema TDC
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
public enum MatrizFeiticos {
    ADIAFANA("Adiáfana"),
    ANA("Ana"),
    ARCANA("Arcana"),
    ELFICA("Élfica"),
    GNOMICA("Gnômica"),
    INFERNAL("Infernal"),
    LUZIDIA("Luzídia"),
    MUNDANA("Mundana"),
    NATURAL("Natural"),
    PRIMORDIAL("Primordial");

    private final String displayName;

    MatrizFeiticos(String displayName) {
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
