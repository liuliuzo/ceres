package com.mastercard.ceres.filter;

import java.util.Arrays;

/**
 * @className FilterType
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public enum FilterType {

    INBOUND("INBOUND"), 
    ENDPOINT("ENDPOINT"),
    OUTBOUND("OUTBOUND"),
    ERROR("OUTBOUND");

    private final String shortName;

    private FilterType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return shortName;
    }

    public static FilterType getByValue(final String shortName) {
        return Arrays.stream(values()).filter(em -> em.getShortName().equals(shortName)).findFirst().orElse(null);
    }

    public static FilterType getByValueOrElseThrows(String shortName) {
        return Arrays.stream(values()).filter(em -> em.getShortName().equals(shortName)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
