package com.ioc.rallyioc;

import com.ioc.motor.Grafics;
import com.ioc.motor.Input.EventTouch;

public class Util {

	// Retorna si un event de touch està dins d'un rectangle
	public static boolean dins(EventTouch event, int x, int y, int ample,
			int alt) {
		if (((event.x > x) && (event.x < x + ample - 1))
				&& ((event.y > y) && (event.y < y + alt - 1)))
			return true;
		else
			return false;
	}

	public static void dibuixaText(Grafics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			if (character == ' ') {
				x += 20;
				continue;
			}
			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}
			g.dibuixaPixmap(Recursos.numeros, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}
}
