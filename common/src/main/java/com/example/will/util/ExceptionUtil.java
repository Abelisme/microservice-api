package com.example.will.util;

import org.apache.commons.lang3.StringUtils;

public class ExceptionUtil {
    public static final String EXCEPTION_OCCUR = "EXCEPTION_OCCUR";

    private ExceptionUtil() {}

    public static Exception createException(Throwable cause, Object... messages) {
        return new Exception(StringUtils.join(messages), cause);
    }
}
