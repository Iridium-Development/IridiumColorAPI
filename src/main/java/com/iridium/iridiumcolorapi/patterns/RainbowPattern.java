package com.iridium.iridiumcolorapi.patterns;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import com.iridium.iridiumcolorapi.patterns.pattern.Pattern;

public class RainbowPattern extends Pattern {

    public RainbowPattern() {
        super(PatternType.RAINBOW);
    }

    /**
     * Applies a rainbow pattern to the given String.
     * Output may be the same as the input if the given pattern isn't correct or something else.
     */
    public String process(String text, String... values) {
        String saturation = values[0];
        return IridiumColorAPI.rainbow(text, Float.parseFloat(saturation));
    }

}
