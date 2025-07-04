package com.tdc.sheets.entity.enums;

/**
 * Enum para classes de feitiços conforme definido no sistema TDC
 */
public enum ClassesFeiticos {
    ABJURACAO("Abjuração"),
    DIVINACAO("Divinação"),
    ELEMENTAL("Elemental"),
    ENCANTAMENTO("Encantamento"),
    EVOCACAO("Evocação"),
    ILUSAO("Ilusão"),
    INVOCACAO("Invocação"),
    MANIPULACAO("Manipulação"),
    MISTICA("Mística"),
    NATURAL("Natural"),
    NECROMANCIA("Necromancia"),
    PROFANA("Profana"),
    SAGRADA("Sagrada"),
    TRANSLOCACAO("Translocação"),
    TRANSMUTACAO("Transmutação");

    private final String displayName;

    ClassesFeiticos(String displayName) {
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
