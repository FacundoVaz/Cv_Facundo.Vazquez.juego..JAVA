package juego;

import java.awt.Color;

import entorno.Entorno;

public class Rayo {
	private int x;
	private int y;
	private int ancho = 15;
	private int alto = 3;
	private int velocidad = 4;

	boolean derecha;
	
	public Rayo(int x, int y, boolean derecha ) {
		this.x=x;
		this.y=y-20;		//para que el personaje pueda esquivar agachandose
		this.derecha=derecha;	//para saber hacia que lado va a avanzar el rayo
	}
	
	public void avanzar() {
		if (this.derecha) {
			this.x=x+velocidad;
		}
		else {
			this.x=x-velocidad;
		}	
	}
	
	//getters de posiciones de velociraptor
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

	
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto,0,Color.green);
	}

}
