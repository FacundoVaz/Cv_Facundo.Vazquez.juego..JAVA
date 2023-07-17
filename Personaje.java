package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;




public class Personaje {
	//pos del jugador
	private int x=20;		//pos x del jugador al iniciar el juego;		
	private int y;
	private int velocidadx=2;

	//dimensiones del juegador
	private int ancho=20;	
	private int alto=50;
	
	//vida y puntos del jugador
	private int vidas=3;
	private int puntosExp=0;
	private int enemigosEliminados=0;
	
	//y del piso actual para verificar en que piso se encuentra
	private int ypiso;
	
	//variables para controlar eventos
	private boolean puedeSaltar;
	private boolean saltando;
	private boolean bajando;
	private int alturaMaxima;
	private boolean derecha;

	private int velocidadDisparo=5;

	

public Personaje (Mapa suelo){

	this.y=suelo.getYpiso();	//el y del personaje es el ypiso del primer piso

	this.ypiso=suelo.getYpiso();	//guardo en ypiso el Y del primer piso


	this.saltando=false;
	this.bajando=false;
	this.puedeSaltar=false;
	this.alturaMaxima=ypiso-(190-(alto/2));		//techo del siguiente piso para que no salte 2 pisos seguidos
	this.derecha=true;

	
}

// los if son las "paredes"
public void moverDerecha() {
	if (x<800) {
	x=x+this.velocidadx;}
}

public void moverIzquierda() {
	if (x>0) {
	x=x-this.velocidadx;}
}

public void moverSalto() {
	if (y==ypiso) {
		y=y-50;	
	}
}

public void comenzarSuperSalto() {
	this.saltando=true;
}
public void superSalto(Mapa suelo) {
	if(this.puedeSaltar(suelo)) {	//si esta en un hueco
		if (this.saltando) {
			y=y-5;
		}
		if(this.bajando) {
			y=y+5;
		}
		if (y <=alturaMaxima) {
			this.bajando=true;
			this.saltando=false;
		}
		if (y >= ypiso) {
			this.bajando=false;
			this.alturaMaxima=ypiso-(190-(alto/2));	//actualizo la altura maxima para el proximo piso
		}
	}
}


public void moverAbajo() {
	if (y<ypiso) {
		y++;	
	}
}

public void agacharse() {
	this.alto=25;	//le disminuyo a la mitad el alto el hitbox
}

public void parada() {
	this.alto=50;
}

public void gravedad(Mapa suelo) {
	//si el piso actual es el piso0 y el Y del jugador es menor al piso 1 (quiere decir que estar en el piso 1
	if (y<suelo.getYpiso1() && ypiso==suelo.getYpiso()) { 
		ypiso=suelo.getYpiso1();		//cambio el piso actual al piso 1		
		this.moverAbajo();
	}
	
	else if (y<suelo.getYpiso2()&& ypiso==suelo.getYpiso1()) {
		ypiso=suelo.getYpiso2();
		this.moverAbajo();
	}
	
	else if (y<suelo.getYpiso3()&& ypiso==suelo.getYpiso2()) {
		ypiso=suelo.getYpiso3();
		this.moverAbajo();
	}
	
	else if (y<suelo.getYpiso4()&& ypiso==suelo.getYpiso3()) {
		ypiso=suelo.getYpiso4();
		this.moverAbajo();
	}
	
	else {
		this.moverAbajo();
	}
	
	//gravedad en los huecos
	
	//si estoy en el piso1 && el x del personaje se encuentra pasando el final del piso1
	//actualizo el ypiso al piso que le sigue abajo
	if(ypiso==suelo.getYpiso1() && x>suelo.getFinPiso1()) {
		ypiso=suelo.getYpiso();
	}
	if(ypiso==suelo.getYpiso2() && x<suelo.getFinPiso2()) {
		ypiso=suelo.getYpiso1();
	}
	if(ypiso==suelo.getYpiso3() && x>suelo.getFinPiso3()) {
		ypiso=suelo.getYpiso2();;
	}
	if(ypiso==suelo.getYpiso4() && x<suelo.getFinPiso4()) {
		ypiso=suelo.getYpiso3();
	}
}
public boolean puedeSaltar(Mapa suelo) {
	
	//si esta en un piso y en el hueco del piso que le sigue puede saltar sino no
	if (ypiso==suelo.getYpiso() && x>suelo.getFinPiso1()) {
		puedeSaltar=true;
		return this.puedeSaltar;
	}
	if (ypiso==suelo.getYpiso1() && x<suelo.getFinPiso2()) {
		puedeSaltar=true;
		return this.puedeSaltar;
	}
	if (ypiso==suelo.getYpiso2() && x>suelo.getFinPiso3()) {
		puedeSaltar=true;
		return this.puedeSaltar;
	}
	if (ypiso==suelo.getYpiso3() && x<suelo.getFinPiso4()) {
		puedeSaltar=true;
		return this.puedeSaltar;
	}
	else{puedeSaltar=false;}
	
	return this.puedeSaltar;
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

public int getYpiso() {
	return ypiso;
}
public boolean isSaltando() {
	return saltando;
}
public boolean isDerecha() {
	return derecha;
}

public void setDerecha(boolean derecha) {
	this.derecha = derecha;
}

public int getVidas() {
	return vidas;
}

public void setVidas(int vidas) {
	this.vidas = vidas;
}
public void setPuntosExp(int puntosExp) {
	this.puntosExp = puntosExp;
}

public int getPuntosExp() {
	return puntosExp;
}
public void setEnemigosEliminados(int enemigosEliminados) {
	this.enemigosEliminados = enemigosEliminados;
}
public int getEnemigosEliminados() {
	return enemigosEliminados;
}

//dibujo	
public void dibujar (Entorno entorno, Image imagen) {
	//entorno.dibujarRectangulo(x, y-(alto/2), ancho, alto, 0,Color.WHITE );	//hitbox
	entorno.dibujarImagen(imagen, x, y-30, 0, 0.02);		//el 0.02 es el escalado! 
}
public void dibujarAgachada (Entorno entorno, Image imagen) {	//metodo distinto porque las imagenes tienen distinto tamaño
	//entorno.dibujarRectangulo(x, y-(alto/2), ancho, alto, 0,Color.WHITE );	//hitbox
	entorno.dibujarImagen(imagen, x, y-13, 0, 0.02);		
}


public Disparos disparar() {
	int velocidad= this.isDerecha() ? this.velocidadDisparo : -this.velocidadDisparo;
	return new Disparos(this.getX(),this.getY()-(this.alto/2),20,20,velocidad );
	
}











	}