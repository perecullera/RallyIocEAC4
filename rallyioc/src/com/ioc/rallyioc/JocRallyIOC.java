package com.ioc.rallyioc;

import com.ioc.motor.implementacio.MotorJoc;

import com.ioc.motor.Pantalla;

public class JocRallyIOC extends MotorJoc

{

	@Override
	public Pantalla getPantallaInicial()

	{

		return new PantallaCarregarRecursos(this);

	}

}