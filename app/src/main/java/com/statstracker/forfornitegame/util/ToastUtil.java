package com.statstracker.forfornitegame.util;

import android.widget.Toast;

import com.statstracker.forfornitegame.base.PubgApplication;

public class ToastUtil {
    /**
     * 显示提示框
     * @param text
     */
    public static void showToas(String text) {
        Toast.makeText(PubgApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }
}
