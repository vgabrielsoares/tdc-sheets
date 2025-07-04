package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de idiomas
 * Corresponde ao tipo "idiomas" no PostgreSQL
 */
public enum Idiomas {
    COMUM("Comum"),
    PRIMORDIAL("Primordial"),
    RUNICO("Rúnico"),
    ANAO("Anão"),
    AQUATICO("Aquático"),
    DRACONICO("Dracônico"),
    ELFICO("Élfico"),
    GIGANTE("Gigante"),
    GNOMICO("Gnômico"),
    INFERNAL("Infernal"),
    OOPARNEELA("Oopar'neela"),
    ORC("Orc"),
    SILVESTRE("Silvestre"),
    URURIMI("Ururimi");

    private final String displayName;

    Idiomas(String displayName) {
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
