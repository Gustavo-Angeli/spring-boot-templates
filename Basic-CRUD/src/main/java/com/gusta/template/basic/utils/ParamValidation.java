package com.gusta.template.basic.utils;

public class ParamValidation {

    // methods created to shorten "if...throw new ...Exception" in code
    public static final <V> void checkIfIsNullOrBlankThrowingEx(V variable) {
        if (variable == null) {
            throw new IllegalArgumentException("Please put a valid value");
        }
        if (variable.toString().isBlank()) {
            throw new IllegalArgumentException("Please put a valid value");
        }
    }
    // end

    // method created to shorten "variable == null" or "variable.toString().isBlank()" in code
    public static final <V> Boolean checkIfIsNullOrBlank(V variable) {
        if (variable == null) {
            return true;
        }
        if (variable.toString().isBlank()) {
            return true;
        }
        return false;
    }
    // end

}
