package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Mapa {
	
	private int xpiso;	
	private int ypiso;
	private int xpiso1;	
	private int ypiso1;
	private int xpiso2;	
	private int ypiso2;
	private int xpiso3;	
	private int ypiso3;
	private int xpiso4;	
	private int ypiso4;
	//medidas de los pisos
	private int ancho;
	private int alto;
	
	//x donde termina cada piso
	private int finPiso1;
	private int finPiso2;
	private int finPiso3;
	private int finPiso4;
	
	//medidas y posicion de la computadora
	private int xPc = 400;
	private int yPc;
	private int anchoPc = 40;
	private int altoPc = 40;
	
	private int xVida=400;
	private int yVida;
	private int anchoVida=30;
	private int altoVida=30;
	
	public Mapa(Entorno entorno){
		this.ancho=650; //ancho de los pisos de arriba
		this.alto=20; //alto de los pisos
		
		//los pisos van con diferencia de 120 menos los 20 pixels que ocupa el ancho de cada uno
		//y quedaria una distancia entre pisos de 100
		this.xpiso=400;
		this.ypiso=600;
		
		this.xpiso1=300;
		this.ypiso1=480;
		
		this.xpiso2=500;
		this.ypiso2=360;
		
		this.xpiso3=300;
		this.ypiso3=240;
		
		this.xpiso4=500;
		this.ypiso4=120;
		
		//valores en x donde termina cada piso
		this.finPiso1=xpiso1+(ancho/2);
		this.finPiso2=xpiso2-(ancho/2);
		this.finPiso3=xpiso3+(ancho/2);
		this.finPiso4=xpiso4-(ancho/2);
		
		this.yPc=ypiso4-40;
		
		this.yVida=ypiso3 -anchoVida;
		
	}
	
	public void dibujarPiso(Entorno entorno, Image pc, Image vida ) {
		entorno.dibujarRectangulo(xpiso, ypiso, 820, alto, 0, Color.black); //piso 820 es el largo de la pantalla 
		entorno.dibujarRectangulo(xpiso1, ypiso1, ancho, alto, 0, Color.black); 
		entorno.dibujarRectangulo(xpiso2, ypiso2, ancho, alto, 0, Color.black);
		entorno.dibujarRectangulo(xpiso3, ypiso3, ancho, alto, 0, Color.black);
		entorno.dibujarRectangulo(xpiso4, ypiso4, ancho, alto, 0, Color.black);
		//entorno.dibujarRectangulo(xVida, yVida, anchoVida, altoVida, 0, Color.red);
		entorno.dibujarImagen(vida, xVida, yVida, 0, 0.3);
		entorno.dibujarImagen(pc, xPc, yPc,0, 0.5);
	}


	//getter de Y de cada piso
	public int getYpiso() {
		return ypiso-alto/2;
	}
	public int getYpiso1() {
		return ypiso1-alto/2;
	}
	public int getYpiso2() {
		return ypiso2-alto/2;
	}
	public int getYpiso3() {
		return ypiso3-alto/2;
	}
	public int getYpiso4() {
		return ypiso4-alto/2;
	}
	
	//getters del x del final de cada piso

	public int getFinPiso1() {
		return finPiso1;
	}
	public int getFinPiso2() {
		return finPiso2;
	}
	public int getFinPiso3() {
		return finPiso3;
	}
	public int getFinPiso4() {
		return finPiso4;
	}
	
	//getters de dimensiones del pc
	public int getxPc() {
		return xPc;
	}


	public int getyPc() {
		return yPc;
	}


	public int getAnchoPc() {
		return anchoPc;
	}
	//getters de dimensiones del item vida
	public int getxVida() {
		return xVida;
	}


	public int getyVida() {
		return yVida;
	}


	public int getAnchoVida() {
		return anchoVida;
	}
	
	public void setxVida(int xVida) {
		this.xVida = xVida;
	}






	

}
