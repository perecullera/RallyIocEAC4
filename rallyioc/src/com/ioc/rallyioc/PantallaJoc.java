package com.ioc.rallyioc;

import java.util.List;

import android.graphics.Color;

import com.ioc.motor.Grafics;
import com.ioc.motor.Input.EventTouch;
import com.ioc.motor.Joc;
import com.ioc.motor.Pantalla;
import com.ioc.motor.Pixmap;

public class PantallaJoc extends Pantalla {

	enum EstatDelJoc {
		Preparat,
		Jugant,
		Pausa,
		GameOver
		}
	EstatDelJoc estat = EstatDelJoc.Preparat;
	Mon mon = new Mon();
	int puntsAntics = 0;
	String puntuacio = "0";

	public PantallaJoc(Joc joc) {
		super(joc);

		if (Configuracio.soActivat)
			Recursos.soMusica.play();
		else
			Recursos.soMusica.pausa();
	}

	public void mostra(float increment) {

		Grafics g = joc.getGraphics();

		g.dibuixaPixmap(Recursos.fons, 0, 0);

		// Dibuixem el món
		dibuixaMon(mon);

		if (estat == EstatDelJoc.Preparat)
			dibuixaPreparat();

		if (estat == EstatDelJoc.Jugant)
			dibuixaJugant();

		if (estat == EstatDelJoc.Pausa)
			dibuixaPausa();

		if (estat == EstatDelJoc.GameOver)
			dibuixaGameOver();

		// Dibuixem la puntuació centrada en la pantalla. La posició X
		// depèn de la llargada de la puntuació
		Util.dibuixaText(g, puntuacio, g.getAmple() / 2 - puntuacio.length()
				* 20 / 2, g.getAlt() - 42);
	}

	private void dibuixaMon(Mon mon) {
		Grafics g = joc.getGraphics();
		Cotxe cotxe = mon.cotxe;
		

		// Recorreguem tot el món i dibuixem el que ens trobem
		for (int x = 0; x < Mon.MON_AMPLE; x++) {
			for (int y = 0; y < Mon.MON_ALT; y++) {
				// Si hi ha un element en aquesta posició del món
				if (mon.mapa[x][y] != null) {
					// Dibuixem l'element corresponent
					switch (mon.mapa[x][y].tipus) {
					case Element.ELEMENT_GASOLINA:
						g.dibuixaPixmap(Recursos.gasolina, x * 32, y * 32);
						break;
					case Element.ELEMENT_OBSTACLE:
						g.dibuixaPixmap(Recursos.obstacle, x * 32, y * 32);
						break;
					}
				}
			}
		}

		// Cotxe
		Pixmap cotxePixmap = null;

		switch (cotxe.direccio) {
		case Cotxe.NORD:
			cotxePixmap = Recursos.cotxeAdalt;
			break;
		case Cotxe.SUD:
			cotxePixmap = Recursos.cotxeAbaix;
			break;
		case Cotxe.EST:
			cotxePixmap = Recursos.cotxeDreta;
			break;
		case Cotxe.OEST:
			cotxePixmap = Recursos.cotxeEsquerra;
			break;
		}
		g.dibuixaPixmap(cotxePixmap, cotxe.x * 32, cotxe.y * 32);
		
		// Vago
		for (int i=0;i<cotxe.llista.size();i++){
			Vago vago = cotxe.llista.get(i);
			
			Pixmap vagoPixmap = null;

			switch (vago.direccio) {
				case Cotxe.NORD:
					vagoPixmap = Recursos.vagoAdalt;
					break;
				case Cotxe.SUD:
					vagoPixmap = Recursos.vagoAbaix;
					break;
				case Cotxe.EST:
					vagoPixmap = Recursos.vagoDreta;
					break;
				case Cotxe.OEST:
					vagoPixmap = Recursos.vagoEsquerra;
					break;
				}
			g.dibuixaPixmap(vagoPixmap, vago.x * 32, vago.y * 32);
		}
		
	}

	private void dibuixaPreparat() {
		Grafics g = joc.getGraphics();

		g.dibuixaPixmap(Recursos.preparat, 47, 100);
		g.dibuixaLinia(0, 416, 480, 416, Color.BLACK);
	}

	private void dibuixaJugant() {
		Grafics g = joc.getGraphics();

		g.dibuixaPixmap(Recursos.botons, 0, 0, 64, 128, 64, 64);
		g.dibuixaLinia(0, 416, 480, 416, Color.BLACK);
		g.dibuixaPixmap(Recursos.botons, 0, 416, 64, 64, 64, 64);
		g.dibuixaPixmap(Recursos.botons, 256, 416, 0, 64, 64, 64);
	}

	private void dibuixaPausa() {
		Grafics g = joc.getGraphics();

		g.dibuixaPixmap(Recursos.pausa, 80, 100);
		g.dibuixaLinia(0, 416, 480, 416, Color.BLACK);
	}

	private void dibuixaGameOver() {
		Grafics g = joc.getGraphics();

		g.dibuixaPixmap(Recursos.gameOver, 62, 100);
		g.dibuixaPixmap(Recursos.botons, 128, 200, 0, 128, 64, 64);
		g.dibuixaLinia(0, 416, 480, 416, Color.BLACK);
	}

	@Override
	public void actualitza(float increment) {
		List<EventTouch> touchEvents = joc.getInput().getEventsTouch();
		joc.getInput().getEventsTeclat();

		// Depenent de l'estat de la partida
		switch (estat) {
		case Preparat:
			actualitzaPreparat(touchEvents);
			break;

		case Jugant:
			actualitzaJugant(touchEvents, increment);
			break;

		case Pausa:
			actualitzaPausa(touchEvents);
			break;

		case GameOver:
			actualitzaGameOver(touchEvents);
			break;
		}
	}

	private void actualitzaPreparat(List<EventTouch> touchEvents) {
		// Senzillamentsi s'ha premutlapantallacomencem a jugar
		if (touchEvents.size() > 0)
			estat = EstatDelJoc.Jugant;
	}

	private void actualitzaPausa(List<EventTouch> touchEvents) {
		int mida = touchEvents.size();

		// Per cada event
		for (int i = 0; i < mida; i++) {
			EventTouch event = touchEvents.get(i);

			if (event.tipus == EventTouch.AIXECAT) {
				if (event.x > 80 && event.x <= 240) {
					// Tornem a jugar
					if (event.y > 100 && event.y <= 148) {
						Recursos.soClick.play(1);
						estat = EstatDelJoc.Jugant;
						return;
					}

					// Tornem al menú principal
					if (event.y > 148 && event.y < 196) {
						Recursos.soClick.play(1);
						joc.setPantalla(new PantallaMenuPrincipal(joc));
						return;
					}
				}
			}
		}
	}

	private void actualitzaGameOver(List<EventTouch> touchEvents) {
		int mida = touchEvents.size();

		for (int i = 0; i < mida; i++) {
			EventTouch event = touchEvents.get(i);
			if (event.tipus == EventTouch.AIXECAT) {
				if (event.x >= 128 && event.x <= 192 && event.y >= 200
						&& event.y <= 264) {
					Recursos.soClick.play(1);
					joc.setPantalla(new PantallaMenuPrincipal(joc));
					return;
				}
			}
		}
	}

	private void actualitzaJugant(List<EventTouch> touchEvents, float increment) {
		int mida = touchEvents.size();

		for (int i = 0; i < mida; i++) {
			EventTouch event = touchEvents.get(i);

			if (event.tipus == EventTouch.AIXECAT) {
				// Pulsacióalbotódepausa
				if (event.x < 64 && event.y < 64) {
					Recursos.soClick.play(1);
					estat = EstatDelJoc.Pausa;
					return;
				}
			}

			if (event.tipus == EventTouch.PREMUT) {
				// Fletxa esquerra
				if (event.x < 64 && event.y > 416) {
					mon.cotxe.giraEsquerra();
					
				}
				// Fletxadreta
				if (event.x > 256 && event.y > 416) {
					mon.cotxe.giraDreta();
					
				}
			}
		}

		// Actualitzem l'estat delmóndeljoc
		mon.actualitza(increment);

		// Hi ha hagutuna col·lisió
		if (mon.gameOver) {
			Recursos.soCrash.play(1);
			estat = EstatDelJoc.GameOver;
		}

		// Ha trobatunagasolina
		if (puntsAntics != mon.puntuaci�) {
			puntsAntics = mon.puntuaci�;
			// Actualitzem l'string
			puntuacio = "" + puntsAntics;

			// Reproduïm el so
			Recursos.soGasolina.play(1);
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llibera() {
		// TODO Auto-generated method stub

	}
}
