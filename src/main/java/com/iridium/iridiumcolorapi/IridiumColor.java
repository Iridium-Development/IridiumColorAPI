package com.iridium.iridiumcolorapi;

import com.iridium.iridiumcolorapi.patterns.GradientPattern;
import com.iridium.iridiumcolorapi.patterns.PatternType;
import com.iridium.iridiumcolorapi.patterns.RainbowPattern;
import com.iridium.iridiumcolorapi.patterns.SolidPattern;
import com.iridium.iridiumcolorapi.patterns.pattern.Pattern;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public class IridiumColor {

    private static final List<Pattern> PATTERNS = Arrays.asList(new GradientPattern(), new SolidPattern(), new RainbowPattern());

    private PatternType patternType;
    private String text;
    private String[] values;

    protected IridiumColor() {
    }

    /**
     * Sets a PatterType
     *
     * @param patternType a PatterType.
     * @return IridiumColor class.
     */
    public IridiumColor setPatternType(PatternType patternType) {
        this.patternType = patternType;
        return this;
    }

    /**
     * Sets a text
     *
     * @param text a text that wants to convert.
     * @return IridiumColor class.
     * @throws NullPointerException if {@code text} is null.
     */
    public IridiumColor setText(String text) {
        if (text == null) throw new IllegalArgumentException("The variable text is null! ");
        this.text = text;
        return this;
    }

    /**
     * @param values an array of string.
     * @return IridiumColor class.
     * @throws IllegalStateException    if {@code patternType} is null.
     * @throws IllegalArgumentException if {@code values} length is different than PatternType size.
     */
    public IridiumColor setValue(String... values) {
        if (patternType == null)
            throw new IllegalStateException("You must set a PatternType before calling this one!");
        if (values.length != patternType.getParametersSize())
            throw new IllegalArgumentException(String.format("The values length has a different size! - Values size: %d, Pattern size: %d", values.length, patternType.getParametersSize()));
        this.values = values;
        return this;
    }

    /**
     * Get a string from the given pattern.
     *
     * @return a String from the given pattern or
     * an empty String if something occurs whilst the process.
     */
    public String process() {
        String string = "";
        if (text != null)
            for (Pattern pattern : PATTERNS)
                if (pattern.getPatternType() == patternType)
                    string = pattern.process(text, values);
        return string;
    }

    /**
     * Stores an IridiumColor into a file configuration.
     *
     * @param configuration a file configuration where you want to write the data.
     * @param location      path.
     */
    public void save(FileConfiguration configuration, String location) {
        if (this.patternType != null && this.text != null && this.values != null) {
            configuration.set(location + ".text", text);
            configuration.set(location + ".type", patternType.toString());
            configuration.set(location + ".value", patternType == PatternType.GRADIENT ? values[0] + ";" + values[1] : values[0]);
        } else throw new IllegalStateException("You must set every variable that has IridiumColor class.");
    }

}
