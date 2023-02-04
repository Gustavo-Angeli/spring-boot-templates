package com.gusta.template.utils;

import java.text.*;
import java.util.*;

public class FormattedBirthDate {
    public static final String formatted(String s) {
        Date dt = null;
        try {
            dt = new SimpleDateFormat("ddMMyyyy").parse(s);
            if (dt.getTime() > new Date().getTime()) throw new IllegalArgumentException("InvalidDateException");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(dt);
        return formattedDate;
    }
}
