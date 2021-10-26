package com.iridium.iridiumcolorapi;

import org.bukkit.Bukkit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IridiumColorAPILegacyTest {

    static MockedStatic<Bukkit> mockedStatic;

    @BeforeAll
    static void setup() {
        mockedStatic = Mockito.mockStatic(Bukkit.class);
        mockedStatic.when(Bukkit::getVersion).thenReturn("1.8.8-R0.3-SNAPSHOT");
    }

    @Test
    void process() {
        assertEquals("§8Test", IridiumColorAPI.process("<SOLID:123456>Test"));
        assertEquals("§1T§1e§3s§3t", IridiumColorAPI.process("<GRADIENT:2C08BA>Test</GRADIENT:028A97>"));
        assertEquals("§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*", IridiumColorAPI.process("<GRADIENT:123456>******************************************************************************************************************************************************************************************************</GRADIENT:ABCDEF>"));
    }

    @Test
    void listProcess() {
        List<String> list = Arrays.asList("<SOLID:123456>Test", "<GRADIENT:2C08BA>Test</GRADIENT:028A97>", "<GRADIENT:123456>******************************************************************************************************************************************************************************************************</GRADIENT:ABCDEF>");
        List<String> result = IridiumColorAPI.process(list);

        assertEquals("§8Test", result.get(0));
        assertEquals("§1T§1e§3s§3t", result.get(1));
        assertEquals("§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*§8*", result.get(2));
    }

    @Test
    void color() {
        assertEquals("§1Test", IridiumColorAPI.color("Test", Color.BLUE));
        assertEquals("§6Test", IridiumColorAPI.color("Test", Color.YELLOW));
        assertEquals("§0Test", IridiumColorAPI.color("Test", Color.BLACK));
    }

    @Test
    void rainbow() {
        assertEquals("§4T§ae§bs§5t", IridiumColorAPI.rainbow("Test", 1));
        assertEquals("§fT§fe§es§7t", IridiumColorAPI.rainbow("Test", 100));
        assertEquals("§0T§0e§0s§0t", IridiumColorAPI.rainbow("Test", 0));
        assertEquals("§f*§7*§f*§7*§f*§7*§d*§f*§d*§f*§d*§f*§d*§f*§f*§7*§f*§7*§f*§7*§f*§7*§d*§f*§d*§f*§d*§f*§d*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§e*§f*§7*§e*§7*§6*§7*§e*§f*§e*§6*§e*§e*§7*§c*§f*§e*§f*§7*§e*§f*§6*§d*§e*§f*§e*§6*§e*§7*§7*§e*§7*§7*§7*§e*§7*§7*§7*§c*§e*§7*§7*§c*§e*§7*§7*§e*§7*§7*§7*§e*§7*§7*§7*§c*§e*§7*§e*§7*§e*§7*§e*§7*§e*§e*§7*§e*§7*§e*§7*§e*§7*§7*§e*§7*§e*§7*§e*§7*§e*§e*§7*§e*§7*§e*§f*§6*§f*§e*§7*§6*§e*§f*§e*§f*§e*§f*§6*§e*§f*§e*§f*§e*§f*§e*§7*§6*§e*§f*§e*§f*§e*", IridiumColorAPI.rainbow("**************************************************************************************************************************************************************************", 100));
    }

    @Test
    void stripColorFormatting() {
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("§8Test"));
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("§1T§1e§3s§3t"));
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("&1T§1e&3s§3t"));
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("&1T&1e&3s&3t"));
        assertEquals("**************************************************************************************************************************************************************************", IridiumColorAPI.stripColorFormatting("§f*§7*§f*§7*§f*§7*§d*§f*§d*§f*§d*§f*§d*§f*§f*§7*§f*§7*§f*§7*§f*§7*§d*§f*§d*§f*§d*§f*§d*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§f*§e*§f*§7*§e*§7*§6*§7*§e*§f*§e*§6*§e*§e*§7*§c*§f*§e*§f*§7*§e*§f*§6*§d*§e*§f*§e*§6*§e*§7*§7*§e*§7*§7*§7*§e*§7*§7*§7*§c*§e*§7*§7*§c*§e*§7*§7*§e*§7*§7*§7*§e*§7*§7*§7*§c*§e*§7*§e*§7*§e*§7*§e*§7*§e*§e*§7*§e*§7*§e*§7*§e*§7*§7*§e*§7*§e*§7*§e*§7*§e*§e*§7*§e*§7*§e*§f*§6*§f*§e*§7*§6*§e*§f*§e*§f*§e*§f*§6*§e*§f*§e*§f*§e*§f*§e*§7*§6*§e*§f*§e*§f*§e*"));
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("<SOLID:123456>Test"));
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("<GRADIENT:2C08BA>Test</GRADIENT:028A97>"));
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("<RAINBOW1>Test</RAINBOW>"));
        assertEquals("Test", IridiumColorAPI.stripColorFormatting("<RAINBOW100>Test</RAINBOW>"));

        assertEquals("<player>", IridiumColorAPI.stripColorFormatting("<player>"));
        assertEquals("<html>Test</html>", IridiumColorAPI.stripColorFormatting("<html>Test</html>"));
        assertEquals("<windows>Test</tests100>", IridiumColorAPI.stripColorFormatting("<windows>Test</tests100>"));
        assertEquals("&&&&&Test&&&&&", IridiumColorAPI.stripColorFormatting("&&&&&Test&&&&&"));
    }

    @AfterAll
    static void tearDown() {
        mockedStatic.close();
    }

}
