package com.iridium.iridiumcolorapi.patterns;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import java.util.regex.Matcher;

public class RainbowPattern implements Pattern {

	java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<RAINBOW(|:)(|[0-9]{1,3})>(.*?)</RAINBOW>");

	/**
	 * Applies a rainbow pattern to the provided String.
	 * Output might me the same as the input if this pattern is not present.
	 *
	 * @param string The String to which this pattern should be applied to
	 * @return The new String with applied pattern
	 */
	@Override
	public String process(String string) {
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			String colon = matcher.group(1);
			String saturation = matcher.group(1);
			String content = matcher.group(2);
			if (colon.equals(":")) {
				string = string.replace(matcher.group(), IridiumColorAPI.rainbow(content, Float.parseFloat(saturation)));
			} else {
				string = string.replace(matcher.group(), IridiumColorAPI.rainbow(content, Float.parseFloat("1")));

			}
		}
		return string;
	}

}
