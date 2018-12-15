package com.statstracker.forfornitegame.util;

import android.text.TextUtils;

public class NumberUtil {

    public static float string2Float(String floatStr, float def) {
        if (TextUtils.isEmpty(floatStr)) {
            return def;
        }

        try {
            float f = Float.valueOf(floatStr);
            return f;
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }
}
