package com.ioc.rallyioc;

import java.util.ArrayList;
import java.util.List;

public class Cotxe extends Element {
	// Ordenades en el sentit de les agulles del
	// rellotge, perquè sigui més fàcil girar
	public static final int NORD = 0;
	public static final int EST = 1;
	public static final int SUD = 2;
	public static final int OEST = 3;

	public int direccio;
	
	List<Vago> llista = new ArrayList<Vago>();

	public Cotxe(int x, int y) {
		super(x, y, ELEMENT_COTXE);
		direccio = NORD;
		Vago vago1 = new Vago(x, y+1); 
		Vago vago2 = new Vago(x,y+2);
		llista.add(vago1);
		llista.add(vago2);
	}

	// Avança el cotxe en la direcció en què està apuntant
	public void avanca() {
		// Avancem una casella en la direcció en què apunta el cotxe
		int anticX;
		int anticY;
		int direccioAnt;
		anticX= x;
		anticY= y;
		direccioAnt = direccio;
		
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
		
		//movem els vagons, comen�ant x l'�ltim
		for(int i = llista.size()-1; i >= 0;i--){
			Vago [] llistat  = new Vago[llista.size()];
			llista.toArray(llistat);
			if (i==0){
				llistat[0].avanca(anticX,anticY,direccioAnt);
			}else {
				llistat[i].avanca(llistat[i-1].x,llistat[i-1].y,llistat[i-1].direccio);
			}
		}
		
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

	public void addVago() {
		// TODO Auto-generated method stub
		Vago [] llistat  = new Vago[llista.size()];
		llista.toArray(llistat);
		Vago vago = new Vago(llistat[llista.size()-1].x, llistat[llista.size()-1].y);
		llista.add(vago);
		
		
	}
}