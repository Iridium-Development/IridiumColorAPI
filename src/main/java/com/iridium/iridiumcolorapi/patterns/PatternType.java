package com.iridium.iridiumcolorapi.patterns;

public enum PatternType {

    GRADIENT(2),
    RAINBOW(1),
    SOLID(1);

    private final int parametersSize;

    PatternType(int parametersSize) {
        this.parametersSize = parametersSize;
    }

    public int getParametersSize() {
        return parametersSize;
    }

}
