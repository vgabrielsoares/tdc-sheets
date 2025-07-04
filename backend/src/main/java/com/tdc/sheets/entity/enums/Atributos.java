package com.tdc.sheets.entity.enums;

/**
 * Enum para os seis atributos básicos do sistema TDC
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
public enum Atributos {
    AGILIDADE("Agilidade"),
    CONSTITUICAO("Constituição"),
    FORCA("Força"),
    INFLUENCIA("Influência"),
    MENTE("Mente"),
    PRESENCA("Presença");

    private final String displayName;

    Atributos(String displayName) {
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
