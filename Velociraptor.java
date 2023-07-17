package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;
import entorno.Entorno;


public class Velociraptor {
	//dimensiones de velociraptor
	private int x=750;
	private int y=20;

	private int ancho=25;
	private int alto=25;
	private int velocidad=2;
	
	private boolean derecha;
	private int yPiso;
	private boolean cayendo;
	
	//contadores para crear disparos
	private int contNuevoRayo = 0;		//contador para disparar
	private int contRayo = (int)(Math.random()*(200-100+1)+100);		//contador aleatorio para saber cuando dispara
	
	public Velociraptor(Mapa suelo){
		this.cayendo=true;
		this.derecha=false;
		this.yPiso  = suelo.getYpiso4();	
	}
	
	public void desplazar() {	//movimiento a la derecha o izquierda
		if (this.derecha && !this.cayendo) {
			this.x=x+this.velocidad;
			contNuevoRayo ++;		//contador para los disparos

		}
		else if(!this.derecha && !cayendo){
				this.x=x-this.velocidad;
				contNuevoRayo ++;		//contador para los disparos
		}
	}
	
	public void gravedad(Mapa suelo) {
		if(y+(alto/2)==yPiso) {
			cayendo=false;
		}
		if (y+(alto/2)<yPiso) {
			cayendo=true;
			y=y+velocidad;
		}
		
		//caida de pisos -> si estoy en un piso y paso el final del mismo cambio de direccion y cambio al piso de abajo	
		if(yPiso==suelo.getYpiso4() && x+(ancho/2)<suelo.getFinPiso4()) {
			yPiso=suelo.getYpiso3();
			derecha=true;
		}
		else if(yPiso==suelo.getYpiso3() && x-(ancho/2)>suelo.getFinPiso3()) {
			yPiso=suelo.getYpiso2();
			derecha=false;;
		}
		else if(yPiso==suelo.getYpiso2() && x+(ancho/2)<suelo.getFinPiso2()) {
			yPiso=suelo.getYpiso1();
			derecha=true;
		}
		else if(yPiso==suelo.getYpiso1() && x-(ancho/2)>suelo.getFinPiso1()) {
			yPiso=suelo.getYpiso();
			derecha=false;
		}
		
	}
	
	public void disparoRayo(LinkedList<Rayo> listaRayos) {	//recibe de parametro una lista donde va a guardar los rayos
		if (contNuevoRayo==contRayo && cayendo == false) {	//cuando se cumple el contador de disparo se crea un nuevo rayo y se agrega a la lista
			contNuevoRayo = 0;
			 Rayo rayo = new Rayo(x,y, derecha); 
			 listaRayos.add(rayo);
		}
		
	}
	
	public void dibujar(Entorno entorno, Image imagenIzq, Image imagenDer) {
		if(derecha) {
			//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.CYAN);			//hitbox
			entorno.dibujarImagen(imagenDer, x, y-10,0, 0.3);
		}
		else {
			//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.CYAN);			//hitbox
			entorno.dibujarImagen(imagenIzq, x, y-10,0, 0.3);
		}
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
