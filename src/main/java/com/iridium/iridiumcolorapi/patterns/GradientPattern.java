package com.iridium.iridiumcolorapi.patterns;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradientPattern implements IPattern {

    Pattern pattern = Pattern.compile("<GRADIENT:([0-9A-Fa-f]{6})>(.*?)</GRADIENT:([0-9A-Fa-f]{6})>");

    public String process(String string) {
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String start = matcher.group(1);
            String end = matcher.group(3);
            String content = matcher.group(2);
            string = string.replace(matcher.group(), IridiumColorAPI.color(content, new Color(Integer.parseInt(start, 16)), new Color(Integer.parseInt(end, 16))));
        }
        return string;
    }
}
