package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de alfabetos
 * Corresponde ao tipo "alfabetos" no PostgreSQL
 */
public enum Alfabetos {
    COMUM("Comum"),
    PRIMORDIAL("Primordial"),
    RUNICO("Rúnico"),
    GNOMICO("Gnômico"),
    AQUATICO("Aquático"),
    DRACONICO("Dracônico"),
    ELFICO("Élfico"),
    GIGANTE("Gigante"),
    GLASNEE("Glasnee"),
    SILVESTRE("Silvestre");

    private final String displayName;

    Alfabetos(String displayName) {
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
