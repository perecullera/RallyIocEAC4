package com.ioc.rallyioc;

//Aquestaclasserepresentalasuperclassedecada element que pot estaralmapa
public abstract class Element {
	public static final int ELEMENT_GASOLINA = 0;
	public static final int ELEMENT_OBSTACLE = 1;
	public static final int ELEMENT_COTXE = 2;
	public static final int ELEMENT_VAGO = 3;

	public int x;
	public int y;
	public int tipus;

	public Element(int x, int y, int tipus) {
		this.x = x;
		this.y = y;
		this.tipus = tipus;
	}
}