package com.iridium.iridiumcolorapi.patterns;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import com.iridium.iridiumcolorapi.patterns.pattern.Pattern;

import java.awt.*;

public class GradientPattern extends Pattern {

    public GradientPattern() {
        super(PatternType.GRADIENT);
    }

    /**
     * Applies a gradient pattern to the given String.
     * Output may be the same as the input if the given pattern isn't correct or something else.
     */
    public String process(String text, String... values) {
        String start = values[0],
                end = values[0];
        return IridiumColorAPI.color(text, new Color(Integer.parseInt(start, 16)), new Color(Integer.parseInt(end, 16)));
    }

}
