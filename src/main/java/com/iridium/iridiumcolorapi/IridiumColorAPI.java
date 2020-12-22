package com.iridium.iridiumcolorapi;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.HashMap;

public class IridiumColorAPI {
    /**
     * The current version of the server in the a form of a major version.
     * If the static initialization for this fails, you know something's wrong with the server software.
     *
     * @since 1.0.0
     */
    private static final int VERSION = Integer.parseInt(getMajorVersion(Bukkit.getVersion()).substring(2));

    /**
     * Cached result if the server version is after the v1.16 rgb update.
     *
     * @since 1.0.0
     */
    private static final boolean SUPPORTSRGB = supports(16);

    /**
     * Cached result of all legacy colors.
     *
     * @since 1.0.0
     */
    private static final HashMap<Color, ChatColor> colors = new HashMap<Color, ChatColor>() {{
        put(new Color(0), ChatColor.getByChar('0'));
        put(new Color(170), ChatColor.getByChar('1'));
        put(new Color(43520), ChatColor.getByChar('2'));
        put(new Color(43690), ChatColor.getByChar('3'));
        put(new Color(11141120), ChatColor.getByChar('4'));
        put(new Color(11141290), ChatColor.getByChar('5'));
        put(new Color(16755200), ChatColor.getByChar('6'));
        put(new Color(11184810), ChatColor.getByChar('7'));
        put(new Color(5592405), ChatColor.getByChar('8'));
        put(new Color(5592575), ChatColor.getByChar('9'));
        put(new Color(5635925), ChatColor.getByChar('a'));
        put(new Color(5636095), ChatColor.getByChar('b'));
        put(new Color(16733525), ChatColor.getByChar('c'));
        put(new Color(16733695), ChatColor.getByChar('d'));
        put(new Color(16777045), ChatColor.getByChar('e'));
        put(new Color(16777215), ChatColor.getByChar('f'));
    }};

    /**
     * Returns a gradient array of chat colors.
     *
     * @param start The starting color.
     * @param end   The ending color.
     * @param step  How many colors we return.
     * @since 1.0.0
     */
    @Nonnull
    public static ChatColor[] createGradient(@Nonnull Color start, @Nonnull Color end, int step) {
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
            if (SUPPORTSRGB) {
                colors[i] = ChatColor.of(color);
            } else {
                colors[i] = getClosestColor(color);
            }
        }
        return colors;
    }


    /**
     * Returns the closest legacy color from an rgb color
     *
     * @param color The color we want to transform
     * @since 1.0.0
     */
    @Nonnull
    private static ChatColor getClosestColor(Color color) {
        Color nearestColor = null;
        int nearestDistance = Integer.MAX_VALUE;

        for (Color constantColor : colors.keySet()) {
            if (nearestDistance > Math.sqrt(Math.pow(color.getRed() - constantColor.getRed(), 2) - Math.pow(color.getGreen() - constantColor.getGreen(), 2) - Math.pow(color.getBlue() - constantColor.getBlue(), 2))) {
                nearestColor = constantColor;
            }
        }
        return colors.get(nearestColor);
    }

    /**
     * Checks if the specified version is the same version or higher than the current server version.
     *
     * @param version the major version to be checked. "1." is ignored. E.g. 1.12 = 12 | 1.9 = 9
     * @return true of the version is equal or higher than the current version.
     * @since 1.0.0
     */
    public static boolean supports(int version) {
        return VERSION >= version;
    }

    /**
     * Gets the exact major version (..., 1.9, 1.10, ..., 1.14)
     * In most cases, you shouldn't be using this method.
     *
     * @param version Supports {@link Bukkit#getVersion()}, {@link Bukkit#getBukkitVersion()} and normal formats such as "1.14"
     * @return the exact major version.
     * @see #supports(int)
     * @since 1.0.0
     */
    @Nonnull
    public static String getMajorVersion(@Nonnull String version) {
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
