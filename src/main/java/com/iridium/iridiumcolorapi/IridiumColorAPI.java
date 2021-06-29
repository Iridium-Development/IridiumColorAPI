package com.iridium.iridiumcolorapi;

import com.google.common.collect.ImmutableMap;
import com.iridium.iridiumcolorapi.patterns.PatternType;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IridiumColorAPI {

    /**
     * The current version of the server in the form of a major version.
     * If the static initialization for this fails then there's something wrong with the server software.
     *
     * @since 1.0.0
     */
    private static final int VERSION = Integer.parseInt(getMajorVersion(Bukkit.getVersion()).substring(2));

    /**
     * @since 1.0.0
     */
    private static final boolean SUPPORTS_RGB = VERSION >= 16;

    private static final List<String> SPECIAL_COLORS = Arrays.asList("&l", "&n", "&o", "&k", "&m");

    /**
     * All legacy colours.
     *
     * @since 1.0.0
     */
    private static final Map<Color, ChatColor> COLORS = ImmutableMap.<Color, ChatColor>builder()
            .put(new Color(0), ChatColor.getByChar('0'))
            .put(new Color(170), ChatColor.getByChar('1'))
            .put(new Color(43520), ChatColor.getByChar('2'))
            .put(new Color(43690), ChatColor.getByChar('3'))
            .put(new Color(11141120), ChatColor.getByChar('4'))
            .put(new Color(11141290), ChatColor.getByChar('5'))
            .put(new Color(16755200), ChatColor.getByChar('6'))
            .put(new Color(11184810), ChatColor.getByChar('7'))
            .put(new Color(5592405), ChatColor.getByChar('8'))
            .put(new Color(5592575), ChatColor.getByChar('9'))
            .put(new Color(5635925), ChatColor.getByChar('a'))
            .put(new Color(5636095), ChatColor.getByChar('b'))
            .put(new Color(16733525), ChatColor.getByChar('c'))
            .put(new Color(16733695), ChatColor.getByChar('d'))
            .put(new Color(16777045), ChatColor.getByChar('e'))
            .put(new Color(16777215), ChatColor.getByChar('f')).build();

    /**
     * Returns a new instance of IridiumColor.
     *
     * @return a new instance of IridiumColor.
     */
    public static IridiumColor newInstance() {
        return new IridiumColor();
    }

    /**
     * Retrieves an IridiumColor from configuration.
     *
     * @param fileConfiguration a file configuration where you want to retrieve the IridiumColor.
     * @param path              path of the iridium color.
     * @return an instance of IridiumColor.
     */
    public static IridiumColor retrieve(@Nonnull FileConfiguration fileConfiguration, @Nonnull String path) {
        if (!fileConfiguration.contains(path))
            throw new IllegalArgumentException("There's no configuration section in the given path!");
        ConfigurationSection section = fileConfiguration.getConfigurationSection(path);
        String text = section.getString("text", "This is a text."),
                type = section.getString("type", "SOLID"),
                configValue = section.getString("value", "FF0080");
        PatternType patternType = PatternType.valueOf(type);
        String[] values = new String[patternType.getParametersSize()];
        if (patternType == PatternType.GRADIENT) {
            String[] split = configValue.split(";");
            values[0] = split[0];
            values[1] = split[1];
        } else
            values[0] = configValue;
        return newInstance()
                .setPatternType(patternType)
                .setText(text)
                .setValue(values);
    }

    /**
     * colors the given string
     *
     * @param input String to color
     * @param color Color that wants to set
     * @since 1.0.0
     */
    @Nonnull
    public static String color(@Nonnull String input, @Nonnull Color color) {
        return (SUPPORTS_RGB ? ChatColor.of(color) : getClosestColor(color)) + input;
    }

    /**
     * Colors a String with gradiant.
     *
     * @param string The string that wants to color
     * @param start  The starting gradiant
     * @param end    The ending gradiant
     * @since 1.0.0
     */
    @Nonnull
    public static String color(@Nonnull String string, @Nonnull Color start, @Nonnull Color end) {
        StringBuilder specialColors = new StringBuilder();
        for (String color : SPECIAL_COLORS) {
            if (string.contains(color)) {
                specialColors.append(color);
                string = string.replace(color, "");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        ChatColor[] colors = createGradient(start, end, string.length());
        String[] characters = string.split("");
        for (int i = 0; i < string.length(); i++) {
            stringBuilder.append(colors[i]).append(specialColors).append(characters[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * Colors a String with rainbow.
     *
     * @param string     The string that wants to rainbow
     * @param saturation The saturation of the rainbow colour
     * @since 1.0.3
     */
    @Nonnull
    public static String rainbow(@Nonnull String string, float saturation) {
        StringBuilder specialColors = new StringBuilder();
        for (String color : SPECIAL_COLORS) {
            if (string.contains(color)) {
                specialColors.append(color);
                string = string.replace(color, "");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        ChatColor[] colors = createRainbow(string.length(), saturation);
        String[] characters = string.split("");
        for (int i = 0; i < string.length(); i++) {
            stringBuilder.append(colors[i]).append(specialColors).append(characters[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * Get a colour from hex code.
     *
     * @param string The colour's hex code
     * @since 1.0.0
     */
    @Nonnull
    public static ChatColor getColor(@Nonnull String string) {
        return SUPPORTS_RGB ? ChatColor.of(new Color(Integer.parseInt(string, 16))) : getClosestColor(new Color(Integer.parseInt(string, 16)));
    }

    /**
     * Returns a rainbow array of chat colors.
     *
     * @param step       How many colors we return
     * @param saturation The saturation of the rainbow
     * @return The array of colors
     * @since 1.0.3
     */
    @Nonnull
    private static ChatColor[] createRainbow(int step, float saturation) {
        ChatColor[] colors = new ChatColor[step];
        double colorStep = (1.00 / step);
        for (int i = 0; i < step; i++) {
            Color color = Color.getHSBColor((float) (colorStep * i), saturation, saturation);
            if (SUPPORTS_RGB) {
                colors[i] = ChatColor.of(color);
            } else {
                colors[i] = getClosestColor(color);
            }
        }
        return colors;
    }

    /**
     * Returns a gradient array of chat colors.
     *
     * @param start The starting color.
     * @param end   The ending color.
     * @param step  How many colors we return.
     * @author TheViperShow
     * @since 1.0.0
     */
    @Nonnull
    private static ChatColor[] createGradient(@Nonnull Color start, @Nonnull Color end, int step) {
        ChatColor[] colors = new ChatColor[step];
        int stepR = Math.abs(start.getRed() - end.getRed()) / (step - 1);
        int stepG = Math.abs(start.getGreen() - end.getGreen()) / (step - 1);
        int stepB = Math.abs(start.getBlue() - end.getBlue()) / (step - 1);
        int[] direction = new int[]{
                start.getRed() < end.getRed() ? +1 : -1,
                start.getGreen() < end.getGreen() ? +1 : -1,
                start.getBlue() < end.getBlue() ? +1 : -1
        };

        for (int i = 0; i < step; i++) {
            Color color = new Color(start.getRed() + ((stepR * i) * direction[0]), start.getGreen() + ((stepG * i) * direction[1]), start.getBlue() + ((stepB * i) * direction[2]));
            if (SUPPORTS_RGB) {
                colors[i] = ChatColor.of(color);
            } else {
                colors[i] = getClosestColor(color);
            }
        }
        return colors;
    }


    /**
     * Returns the closest legacy colour from a rgb color.
     *
     * @param color the Color that wants to transform
     * @since 1.0.0
     */
    @Nonnull
    private static ChatColor getClosestColor(Color color) {
        Color nearestColor = null;
        double nearestDistance = Integer.MAX_VALUE;

        for (Color constantColor : COLORS.keySet()) {
            double distance = Math.pow(color.getRed() - constantColor.getRed(), 2) + Math.pow(color.getGreen() - constantColor.getGreen(), 2) + Math.pow(color.getBlue() - constantColor.getBlue(), 2);
            if (nearestDistance > distance) {
                nearestColor = constantColor;
                nearestDistance = distance;
            }
        }
        return COLORS.get(nearestColor);
    }

    /**
     * Get a major version (..., 1.9, 1.10, ..., 1.14).
     * In most cases, you cannot use this method.
     *
     * @param version Supports {@link Bukkit#getVersion()}, {@link Bukkit#getBukkitVersion()} and normal formats such as "1.14"
     * @return a String of a major version.
     * @since 1.0.0
     */
    @Nonnull
    private static String getMajorVersion(@Nonnull String version) {
        Validate.notEmpty(version, "Cannot get major Minecraft version from null or empty string");

        // getVersion()
        int index = version.lastIndexOf("MC:");
        if (index != -1) {
            version = version.substring(index + 4, version.length() - 1);
        } else if (version.endsWith("SNAPSHOT")) {
            // getBukkitVersion()
            index = version.indexOf('-');
            version = version.substring(0, index);
        }

        // 1.13.2, 1.14.4, etc...
        int lastDot = version.lastIndexOf('.');
        if (version.indexOf('.') != lastDot) version = version.substring(0, lastDot);

        return version;
    }

}
