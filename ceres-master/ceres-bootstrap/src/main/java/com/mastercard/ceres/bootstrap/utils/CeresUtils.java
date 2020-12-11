package com.mastercard.ceres.bootstrap.utils;

public class CeresUtils {
    private static String qualify(String attr) {
        return CeresUtils.class.getName() + "." + attr;
    }

    public static final String CLIENT_RESPONSE_ATTR = qualify("gatewayClientResponse");
}
