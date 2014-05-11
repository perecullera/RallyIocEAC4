package com.ioc.rallyioc;

import java.util.ArrayList;
import java.util.List;

public class Vago extends Element {
	
	public static final int NORD = 0;
	public static final int EST = 1;
	public static final int SUD = 2;
	public static final int OEST = 3;

	public int direccio;
	
	

	public Vago(int x, int y) {
		super(x, y,ELEMENT_VAGO);
		
		// TODO Auto-generated constructor stub
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
		
		public int getX (){
			return x;
		}
		public int getY (){
			return y;
		}

		public void avanca(int cotxeAnticX, int cotxeAnticY, int direccio2) {
			// TODO Auto-generated method stub
			x = cotxeAnticX;
			y = cotxeAnticY;
			direccio = direccio2;
			
			}
	
	

}
