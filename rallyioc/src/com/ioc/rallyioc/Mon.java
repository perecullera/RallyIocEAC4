package com.ioc.rallyioc;

import java.util.Random;

//Representació lògica del món del joc
public class Mon {
	static final int MON_AMPLE = 10;
	static final int MON_ALT = 13;
	static final int INCREMENT_PUNTUACIO = 10;
	static final float TICK_INICIAL = 0.5f;
	static final float TICK_DECREMENT = 0.01f;

	static final int COTXE_INIT_X = 5;
	static final int COTXE_INIT_Y = 7;

	public Cotxe cotxe;
	public boolean gameOver = false;
	public int puntuaci� = 0;

	Element mapa[][];
	Random random = new Random();
	float tempsAcumulat = 0;

	// Cada quant es computa un frame
	static float tick = TICK_INICIAL;

	public Mon() {
		// Creem el món buit
		mapa = new Element[MON_AMPLE][MON_ALT];

		// Creem el cotxe
		cotxe = new Cotxe(COTXE_INIT_X, COTXE_INIT_Y);

		// Introduïm el cotxe al mapa per assegurar-nos que no es crea
		// la gasolina o l'obstacle nou en la posició del cotxe
		mapa[COTXE_INIT_X][COTXE_INIT_Y] = cotxe;

		// Situem una primera gasolina
		situaElement(Element.ELEMENT_GASOLINA);

		tick = TICK_INICIAL;
		puntuaci� = 0;
	}

	// Fica gasolina o obstacle aleatòriament al mapa
	private void situaElement(int tipus) {
		int posX;
		int posY;

		// Cerquem si és possible
		while (true) {
			posX = random.nextInt(MON_AMPLE);
			posY = random.nextInt(MON_ALT);

			// Si la posició del mapa està buida
			if (mapa[posX][posY] == null) {
				// Mirem que no estigui massa aprop del cotxe
				// Si està al menys a 4 posicions (distància de Manhattan)
				if ((Math.abs(cotxe.x - posX) + Math.abs(cotxe.y - posY)) > 4)
					break;
			}
		}

		// Creem l'element
		if (tipus == Element.ELEMENT_GASOLINA) {
			// Creem la gasolina i la situem al mapa
			mapa[posX][posY] = new Gasolina(posX, posY);
		} else {
			// Creem obstacle
			mapa[posX][posY] = new Obstacle(posX, posY);
		}
	}

	// Actualitzem el mapa
	public void actualitza(float increment) {
		int cotxeAnticX;
		int cotxeAnticY;

		// Si s'ha acabat el joc no hi ha res a actualitzar!
		if (gameOver)
			return;

		tempsAcumulat += increment;

		while (tempsAcumulat > tick) {
			tempsAcumulat -= tick;

			// Guardem on és el cotxeabans d'avançar
			cotxeAnticX = cotxe.x;
			cotxeAnticY = cotxe.y;

			// Avancem el cotxe
			cotxe.avanca();

			// Comprovem si ha xocat amb algun element
			if (mapa[cotxe.x][cotxe.y] != null) {
				// Amb què ha xocat el cotxe?
				switch (mapa[cotxe.x][cotxe.y].tipus) {
				case Element.ELEMENT_GASOLINA:
					puntuaci� += INCREMENT_PUNTUACIO;

					// Eliminem aquesta gasolina
					mapa[cotxe.x][cotxe.y] = null;
					situaElement(Element.ELEMENT_GASOLINA);
					situaElement(Element.ELEMENT_OBSTACLE);

					// Incrementem la velocitat
					// Decrementem el temps de tick, per tant el joc anirà més
					// ràpid
					tick -= TICK_DECREMENT;
					break;

				case Element.ELEMENT_OBSTACLE:
					gameOver = true;
					return;
				}
			} else {
				// El lloc que ocupava el cotxe ara està buit
				mapa[cotxeAnticX][cotxeAnticY] = null;

				// i actualitzem la seva posició actual
				mapa[cotxe.x][cotxe.y] = cotxe;
			}
		}
	}
}