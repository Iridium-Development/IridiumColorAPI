package com.iridium.iridiumcolorapi.patterns.handler;

import com.iridium.iridiumcolorapi.patterns.GradientPattern;
import com.iridium.iridiumcolorapi.patterns.RainbowPattern;
import com.iridium.iridiumcolorapi.patterns.SolidPattern;
import com.iridium.iridiumcolorapi.patterns.pattern.Pattern;

import java.util.Arrays;
import java.util.List;

public class PatternHandler {

    public static final List<Pattern> PATTERNS = Arrays.asList(new GradientPattern(), new SolidPattern(), new RainbowPattern());

}
