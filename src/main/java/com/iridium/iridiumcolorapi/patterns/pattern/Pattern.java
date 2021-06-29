package com.iridium.iridiumcolorapi.patterns.pattern;

import com.iridium.iridiumcolorapi.patterns.PatternType;

public abstract class Pattern {

    private final PatternType patternType;

    public Pattern(PatternType patternType) {
        this.patternType = patternType;
    }

    public abstract String process(String text, String... values);

    public PatternType getPatternType() {
        return patternType;
    }

}
