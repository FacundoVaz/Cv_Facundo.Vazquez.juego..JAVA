package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;

public class Disparos {
		
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidadDisparo;
	
public Disparos (int x , int y , int ancho , int alto , int velocidadDisparo) { 

	this.x=x;
	this.y=y;
	this.ancho=ancho;
	this.alto=alto;
	this.velocidadDisparo = velocidadDisparo;
}

public void avanzar() {
	this.x=x+velocidadDisparo;

}


public void dibujarDisparos(Image imagen, Entorno entorno) {
	//entorno.dibujarRectangulo(this.x,this.y, ancho, alto,0, Color.white);	//hitbox
	entorno.dibujarImagen(imagen, this.x, this.y -10, 0, 0.5);

}

//getters
public int getX() {
	return x;
}

public int getY() {
	return y;
}

public int getAncho() {
	return ancho;
}

public int getAlto() {
	return alto;
}
}