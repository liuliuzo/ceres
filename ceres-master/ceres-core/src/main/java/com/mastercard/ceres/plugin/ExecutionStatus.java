package com.liuliu.ceres.plugin;

import java.util.Arrays;

/**
 * @className ExecutionStatus
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public enum ExecutionStatus {

    SUCCESS(1), 
    SKIPPED(-1), 
    DISABLED(-2), 
    FAILED(-3), 
    BODY_AWAIT(-4), 
    ASYNC_AWAIT(-5);

    private int status;

    ExecutionStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static ExecutionStatus getByValue(final int status) {
        return Arrays.stream(values()).filter(em -> em.getStatus() == (status)).findFirst().orElse(null);
    }

    public static ExecutionStatus getByValueOrElseThrows(final int status) {
        return Arrays.stream(values()).filter(em -> em.getStatus() == (status)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
