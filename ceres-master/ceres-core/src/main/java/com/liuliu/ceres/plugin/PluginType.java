package com.liuliu.ceres.plugin;

import java.util.Arrays;

/**
 * @className PluginType
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public enum PluginType {

    INBOUND("INBOUND"), 
    ENDPOINT("ENDPOINT"),
    OUTBOUND("OUTBOUND"),
    ERROR("ERROR");

    private final String shortName;

    private PluginType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return shortName;
    }

    public static PluginType getByValue(final String shortName) {
        return Arrays.stream(values()).filter(em -> em.getShortName().equals(shortName)).findFirst().orElse(null);
    }

    public static PluginType getByValueOrElseThrows(String shortName) {
        return Arrays.stream(values()).filter(em -> em.getShortName().equals(shortName)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
