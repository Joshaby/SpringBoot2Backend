package com.joshaby.springboot2backend.services.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicesUtils {

    private static int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    private static List<Integer> toListInteger(String numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (int i = 0; i < numbers.length(); i ++) {
            numbersList.add((int) numbers.charAt(i) - 48);
        }
        return numbersList;
    }

    public static boolean isCPF(String cpf) {
        if (cpf.length() != 11) return false;
        final List<Integer> numbers = toListInteger(cpf);
        Integer d1 = 0, d2 = 0;
        for (int i = 8, j = 2; i >= 0; i --, j ++) {
            d1 += (numbers.get(i) * j);
        }
        d1 = 11 - mod(d1, 11);
        if (d1 >= 10) d1 = 0;
        d2 += d1 * 2;
        for (int i = 8, j = 3; i >= 0; i --, j ++) {
            d2 += (numbers.get(i) * j);
        }
        d2 = 11 - mod(d2, 11);
        if (d2 >= 10) d2 = 0;
        return Objects.equals(numbers.get(9), d1) && Objects.equals(numbers.get(10), d2);
    }

    public static boolean isCNPJ(String cnpj) {
        if (cnpj.length() != 14) return false;
        final List<Integer> numbers = toListInteger(cnpj);
        Integer d1 = 0, d2 = 0;
        for (int i = 11, j = 2; i >= 0; i --, j ++) {
            d1 += (numbers.get(i) * j);
            j = (j == 9) ? 1 : j;
        }
        d1 = 11 - mod(d1, 11);
        if (d1 >= 10) d1 = 0;
        d2 += d1 * 2;
        for (int i = 11, j = 3; i >= 0; i --, j ++) {
            d2 += (numbers.get(i) * j);
            j = (j == 9) ? 1 : j;
        }
        d2 = 11 - mod(d2, 11);
        if (d2 >= 10) d2 = 0;
        return Objects.equals(numbers.get(12), d1) && Objects.equals(numbers.get(13), d2);
    }
}