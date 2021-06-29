package com.iridium.iridiumcolorapi.patterns;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import com.iridium.iridiumcolorapi.patterns.pattern.Pattern;

public class SolidPattern extends Pattern {

    public SolidPattern() {
        super(PatternType.SOLID);
    }

    /**
     * Applies a solid RGB color to the given String.
     * Output may be the same as the input if the given pattern isn't correct or something else.
     */
    public String process(String text, String... values) {
        String color = values[0];
        return IridiumColorAPI.getColor(color) + text;
    }

}
