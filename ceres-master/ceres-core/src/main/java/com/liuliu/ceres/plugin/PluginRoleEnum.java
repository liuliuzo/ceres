package com.liuliu.ceres.plugin;

/**
 * @className PluginRoleEnum
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public enum PluginRoleEnum {

    /**
     * Sys Plugin role enum.
     */
    SYS(0, "sys"),

    /**
     * Custom Plugin role enum.
     */
    CUSTOM(1, "custom");

    private PluginRoleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;

    private final String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
