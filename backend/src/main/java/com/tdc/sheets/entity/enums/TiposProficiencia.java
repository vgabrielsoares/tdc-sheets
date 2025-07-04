package com.tdc.sheets.entity.enums;

/**
 * Enum para tipos de proficiência conforme definido no sistema TDC
 */
public enum TiposProficiencia {
    ARMAS_SIMPLES("Armas Simples"),
    ARMAS_COMPLEXAS("Armas Complexas"),
    ARMAS_MARCIAIS("Armas Marciais"),
    ARMAS_PESADAS("Armas Pesadas"),
    IDIOMA_COMUM("Idioma - Comum"),
    IDIOMA_PRIMORDIAL("Idioma - Primordial"),
    IDIOMA_RUNICO("Idioma - Rúnico"),
    IDIOMA_ANAO("Idioma - Anão"),
    IDIOMA_AQUATICO("Idioma - Aquático"),
    IDIOMA_DRACONICO("Idioma - Dracônico"),
    IDIOMA_ELFICO("Idioma - Élfico"),
    IDIOMA_GIGANTE("Idioma - Gigante"),
    IDIOMA_GNOMICO("Idioma - Gnômico"),
    IDIOMA_INFERNAL("Idioma - Infernal"),
    IDIOMA_OOPARNEELA("Idioma - Oopar'neela"),
    IDIOMA_ORC("Idioma - Orc"),
    IDIOMA_SILVESTRE("Idioma - Silvestre"),
    IDIOMA_URURIMI("Idioma - Ururimi"),
    PROTECAO_LEVE("Proteção Leve"),
    PROTECAO_MEDIA("Proteção Média"),
    PROTECAO_PESADA("Proteção Pesada"),
    ESCUDOS("Escudos"),
    INTRUMENTO_HABILIDADE("Instrumento de Habilidade"),
    INTRUMENTO_OFICIO("Instrumento de Ofício");

    private final String displayName;

    TiposProficiencia(String displayName) {
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
