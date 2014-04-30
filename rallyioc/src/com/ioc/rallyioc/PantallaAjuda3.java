package com.ioc.rallyioc;

import java.util.List;

import com.ioc.motor.Pantalla;
import com.ioc.motor.Grafics;
import com.ioc.motor.Joc;
import com.ioc.motor.Input.EventTouch;

//Mostrarà la primera de les pantalles d'ajuda
public class PantallaAjuda3 extends Pantalla {
	public PantallaAjuda3(Joc joc) {
		super(joc);
		if (Configuracio.soActivat)
			Recursos.soMusica.play();
		else
			Recursos.soMusica.pausa();
	}

	@Override
	public void actualitza(float temps) {
		// Obtenim els events d'Input
		List<EventTouch> events = joc.getInput().getEventsTouch();
		joc.getInput().getEventsTeclat();

		int mida = events.size();

		// Tractem tots els events d'Input
		for (int i = 0; i < mida; i++) {
			EventTouch event = events.get(i);

			if (event.tipus == EventTouch.AIXECAT) {
				// Posició del botó
				if (event.x > 256 && event.y > 416) {
					// Anem a la següent pantalla
					joc.setPantalla(new PantallaMenuPrincipal(joc));
					Recursos.soClick.play(1);
					return;
				}
			}
		}
	}

	@Override
	public void mostra(float temps) {
		// Mostrem els gràfics d'ajuda i el botó
		Grafics g = joc.getGraphics();
		g.dibuixaPixmap(Recursos.fons, 0, 0);
		g.dibuixaPixmap(Recursos.ajuda3, 64, 100);
		g.dibuixaPixmap(Recursos.botons, 256, 416, 0, 64, 64, 64);
	}

	@Override
	public void pause() {
		Recursos.soMusica.pausa();
	}

	@Override
	public void resume() {
		if (Configuracio.soActivat)
			Recursos.soMusica.play();
	}

	@Override
	public void llibera() {
	}
}