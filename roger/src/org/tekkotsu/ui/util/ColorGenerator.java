package org.tekkotsu.ui.util;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class ColorGenerator {
	public static Color highContrast(Color back)
	{
		RGB rgb = back.getRGB();
		
		/*
		 * Need to calculate percieved brightness to select
		 * an appropriate font color.
		 */
		int brightness;
			
		int r = rgb.red;
		int b = rgb.blue;
		int g = rgb.green;
			
		//http://alienryderflex.com/hsp.html
		brightness = (int)Math.sqrt(
			.241f * r * r +
			.691f * g * g +
			.068f * b * b
		);	
			
		if (brightness < 140 /* empirical threshold */ ) {
			return ColorConstants.white;
		}
		
		return ColorConstants.black;
	}
	
	public static RGB fade(RGB rgb, int delta)
	{
		int red = fade(rgb.red,delta);
		int green = fade(rgb.green,delta);
		int blue = fade(rgb.blue,delta);
		
		return new RGB(red,green,blue);
	}
	
	private static int fade(int channel, int delta) {
		channel = ((int)Math.round(channel*0.2) + delta);
		if (channel<0) channel = 0;
		if (channel>255) channel = 255;
		return channel;
	}

	public static Color borderColor(Color backgroundColor) {
		return ColorConstants.black;
	}
}
