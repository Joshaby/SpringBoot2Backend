package com.joshaby.springboot2backend.controllers.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerUtils {

    public static String paramDecoder(String param) {
        return URLDecoder.decode(param, StandardCharsets.UTF_8);
    }

    public static List<Integer> paramToList(String param) {
        return Arrays.stream(param.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
