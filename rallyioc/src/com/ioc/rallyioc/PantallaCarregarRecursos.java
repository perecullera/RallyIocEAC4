package com.ioc.rallyioc;

import com.ioc.motor.Pantalla;
import com.ioc.motor.Joc;
import com.ioc.motor.Grafics;
import com.ioc.motor.Grafics.PixmapFormat;

public class PantallaCarregarRecursos extends Pantalla {
	public PantallaCarregarRecursos(Joc joc) {
		super(joc);
	}

	@Override
	public void actualitza(float temps) {
		Grafics g = joc.getGraphics();
		Recursos.fons = g.nouPixmap("grafics/fons.png", PixmapFormat.RGB565);
		Recursos.logo = g.nouPixmap("grafics/logo.png", PixmapFormat.ARGB4444);
		Recursos.menuPrincipal = g.nouPixmap("grafics/menuprincipal.png",
				PixmapFormat.ARGB4444);
		Recursos.botons = g.nouPixmap("grafics/botons.png",
				PixmapFormat.ARGB4444);
		Recursos.ajuda1 = g.nouPixmap("grafics/ajuda1.png",
				PixmapFormat.ARGB4444);
		Recursos.ajuda2 = g.nouPixmap("grafics/ajuda2.png",
				PixmapFormat.ARGB4444);
		Recursos.ajuda3 = g.nouPixmap("grafics/ajuda3.png",
				PixmapFormat.ARGB4444);
		Recursos.numeros = g.nouPixmap("grafics/numeros.png",
				PixmapFormat.ARGB4444);
		Recursos.preparat = g.nouPixmap("grafics/preparat.png",
				PixmapFormat.ARGB4444);
		Recursos.pausa = g.nouPixmap("grafics/menupausa.png",
				PixmapFormat.ARGB4444);
		Recursos.gameOver = g.nouPixmap("grafics/gameover.png",
				PixmapFormat.ARGB4444);
		Recursos.cotxeAdalt = g.nouPixmap("grafics/cotxedalt.png",
				PixmapFormat.ARGB4444);
		Recursos.cotxeEsquerra = g.nouPixmap("grafics/cotxeesquerra.png",
				PixmapFormat.ARGB4444);
		Recursos.cotxeAbaix = g.nouPixmap("grafics/cotxebaix.png",
				PixmapFormat.ARGB4444);
		Recursos.cotxeDreta = g.nouPixmap("grafics/cotxedreta.png",
				PixmapFormat.ARGB4444);
		Recursos.obstacle = g.nouPixmap("grafics/obstacle.png",
				PixmapFormat.ARGB4444);
		Recursos.gasolina = g.nouPixmap("grafics/gasolina.png",
				PixmapFormat.ARGB4444);
		Recursos.soClick = joc.getAudio().nouSo("audio/click.wav");
		Recursos.soGasolina = joc.getAudio().nouSo("audio/gasolina.wav");
		Recursos.soCrash = joc.getAudio().nouSo("audio/crash.wav");

		Recursos.soMusica = joc.getAudio().nouMusica("audio/funky.mp3");
		Recursos.soMusica.setBucle(true);

		Configuracio.carregaConfiguracio(joc.getFileIO());

		// Activem la configuració de so
		com.ioc.motor.implementacio.MotorSo.soActivat = Configuracio.soActivat;
		com.ioc.motor.implementacio.MotorMusica.musicaActivada = Configuracio.soActivat;

		joc.setPantalla(new PantallaMenuPrincipal(joc));
	}

	@Override
	public void mostra(float temps) {
		// Es podria mostrar el logo del joc o carregant aquí
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void llibera() {
	}
}