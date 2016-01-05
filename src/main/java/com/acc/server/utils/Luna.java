package com.acc.server.utils;

/**
 * Created by demius on 27.05.2015.
 */
public class Luna {

    public static String monthFromLuna(int luna) {
        int month = luna % 100;
        return month < 10 ? "0" + month : String.valueOf(month);
    }

    public static int prev(int luna) {
        int month = luna % 100;
        int year = luna / 100;
        if (month == 1) {
            year--;
            month = 12;
        } else {
            month--;
        }
        return month + year * 100;
    }

    public static int nou(int luna) {
        int month = luna % 100;
        int year = luna / 100;
        if (month == 12) {
            year++;
            month = 1;
        } else {
            month++;
        }
        return month + year * 100;
    }

    public static int delta(int luna, int delta) {
        if (delta == 0) return luna;
        else {
            int lm = luna % 100;
            int l_year = luna / 100 + delta / 12;
            int l_mm = lm + delta % 12;
            if (l_mm < 1) {
                l_year -= 1; l_mm += 12;
            } else if (l_mm > 12) {
                l_year += 1;
                l_mm -= 12;
            }
            return l_year * 100 + l_mm;
        }
    }

}
