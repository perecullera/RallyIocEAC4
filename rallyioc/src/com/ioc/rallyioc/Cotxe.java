package com.ioc.rallyioc;

public class Cotxe extends Element {
	// Ordenades en el sentit de les agulles del
	// rellotge, perquè sigui més fàcil girar
	public static final int NORD = 0;
	public static final int EST = 1;
	public static final int SUD = 2;
	public static final int OEST = 3;

	public int direccio;

	public Cotxe(int x, int y) {
		super(x, y, ELEMENT_COTXE);
		direccio = NORD;
	}

	// Avança el cotxe en la direcció en què està apuntant
	public void avanca() {
		// Avancem una casella en la direcció en què apunta el cotxe
		switch (direccio) {
		case NORD:
			y--;
			break;
		case SUD:
			y++;
			break;
		case EST:
			x++;
			break;
		case OEST:
			x--;
			break;

		}

		// Si sortim del mapa, tornem a entrar per l'altre costat
		if (x < 0)
			x = 9;
		if (x > 9)
			x = 0;
		if (y < 0)
			y = 12;
		if (y > 12)
			y = 0;
	}

	// Modifiquem la direcció del cotxe
	public void giraEsquerra() {
		direccio--;
		if (direccio < NORD)
			direccio = OEST;
	}

	public void giraDreta() {
		direccio++;
		if (direccio > OEST)
			direccio = NORD;
	}
}