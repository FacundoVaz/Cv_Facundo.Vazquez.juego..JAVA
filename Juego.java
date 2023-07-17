package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Mapa suelo;
	
	private Personaje Barbarianna;
	
	//enemigo
	private Velociraptor enemigo;
	private LinkedList<Velociraptor> listaEnemigos;
	private int contadorAparicionDeEnemigos=100;	//contador para que aparezcan enemigos
	private Velociraptor eliminarEnemigo;//enemigo a eliminar
	
	//rayos
	private LinkedList<Rayo> listaRayos;
	private Rayo eliminarRayo;

	//disparos
	LinkedList<Disparos> listaDisparos;
	private Disparos eliminarDisparos;
	private	int cantDisparos;
	
	//imagenes
	private Image fondo;
	private Image fondoGameOver;
	private Image fondoGanador;
	private Image computadora;
	private Image vida;
	
	private Image disparoimagen;
	private Image BarIzquierda;
	private Image BarDerecha;
	private Image BarDisparoIzquierda;
	private Image BarDisparoDerecha;
	private Image BarAbajoIzquierda;
	private Image BarAbajoDerecha;
	
	private Image velociraptorIzq;
	private Image velociraptorDer;
	
	private boolean gano;
	
	private Clip musicaFondo;
	Juego()
	{
	
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 3 - v1", 800,600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		this.suelo = new Mapa(entorno);
		
		this.Barbarianna = new Personaje(suelo);
		
		this.enemigo = new Velociraptor(suelo);
		this.listaEnemigos=new LinkedList<Velociraptor>();		//creo una lista con los velociraptores para tener la cuenta de cuantos hay
		this.listaEnemigos.add(enemigo);
		this.eliminarEnemigo=null;
		
		this.listaDisparos= new LinkedList<Disparos>(); 
		this.eliminarDisparos=null;
		this.cantDisparos=1;
		
		this.listaRayos=new LinkedList<Rayo>();
		this.eliminarRayo=null;
		
		this.gano=false;
		
		//imagenes
		
		BarDerecha = Herramientas.cargarImagen("imagenes/BarDerecha.png");		//guardo las imagenes
		BarIzquierda = Herramientas.cargarImagen("imagenes/BarIzquierda.png");
		BarAbajoDerecha = Herramientas.cargarImagen("imagenes/Bar-Abajo-Derecha.png");		
		BarAbajoIzquierda = Herramientas.cargarImagen("imagenes/Bar-Abajo-Izquierda.png");
		BarDisparoDerecha = Herramientas.cargarImagen("imagenes/bar-disparo-derecha.png");		
		BarDisparoIzquierda = Herramientas.cargarImagen("imagenes/bar-disparo-izquierda.png");
		
		disparoimagen = Herramientas.cargarImagen("imagenes/Trueno.png");
		vida = Herramientas.cargarImagen("imagenes/vida.png");
		
		velociraptorIzq = Herramientas.cargarImagen("imagenes/VelociraptorIzquierda.png");
		velociraptorDer = Herramientas.cargarImagen("imagenes/VelociraptorDerecha1.png");
		
		fondo=Herramientas.cargarImagen("imagenes/fondo.jpg");
		fondoGameOver=Herramientas.cargarImagen("imagenes/fondoGameOver.jpg");
		fondoGanador=Herramientas.cargarImagen("imagenes/fondoGanador.jpg");
		computadora = Herramientas.cargarImagen("imagenes/pc.png");
		
		musicaFondo=Herramientas.cargarSonido("Sonidos/musicaFondo.wav");

		
		// Inicia el juego!
		
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
		public void tick()
		{	
			if(Barbarianna.getVidas()>0 && this.gano==false) {
				musicaFondo.start();	//inicio la musica de fondo
				entorno.dibujarImagen(fondo, 400, 300, 0, 0.85);	//dibujo primero el fondo
				
				actualizarEstado();
				
				procesarEventos();
				
				dibujarEntidades();
			}
			else if(gano ==true){
				entorno.dibujarImagen(fondoGanador, 400, 300, 0, 0.85);
				entorno.cambiarFont("Arial", 20, Color.WHITE);
				entorno.escribirTexto("puntos: "+Barbarianna.getPuntosExp(),350,500);
				entorno.escribirTexto("Enemigos Eliminados: "+ Barbarianna.getEnemigosEliminados(),300,550);
			}
			else {
				entorno.dibujarImagen(fondoGameOver, 400, 300, 0, 0.85);
				entorno.cambiarFont("Arial", 20, Color.WHITE);
				entorno.escribirTexto("puntos: "+Barbarianna.getPuntosExp(),350,500);
				entorno.escribirTexto("Enemigos Eliminados: "+ Barbarianna.getEnemigosEliminados(),300,550);
			}
		}
		
		
		public void actualizarEstado(){
			//salto de barbarianna
			if(Barbarianna.isSaltando()) {
				Barbarianna.superSalto(suelo);
			}
			
			//velociraptores movimiento
			for (Velociraptor enemigo: listaEnemigos) {
				enemigo.desplazar();
				enemigo.gravedad(suelo);
			}
			
			//creacion de velociraptores nuevos
			contadorAparicionDeEnemigos --;			
			if (contadorAparicionDeEnemigos == 0 && listaEnemigos.size()<10) {
					Velociraptor enemigo= new Velociraptor(suelo);
					listaEnemigos.add(enemigo);								 //crea un nuevo enemigo que lo agrega a la lista
					contadorAparicionDeEnemigos=200;		//reinicio el contador
			}
			
			//eliminar velociraptores fuera de pantalla
			for(Velociraptor enemigo:listaEnemigos) {
				if(enemigo.getX()<-20 ) {
					eliminarEnemigo=enemigo;
				}
			}
			listaEnemigos.remove(eliminarEnemigo);
			
			//disparos automaticos de los velociraptores
			for(Velociraptor enemigo: listaEnemigos) {
				enemigo.disparoRayo(listaRayos);
				
			}
			
			//movimiento de los rayos y eliminacion fuera de pantalla
			for(Rayo rayo:listaRayos) {
				if(rayo.getX()<0 || rayo.getX()>800) {
					eliminarRayo=rayo;
				}
				rayo.avanzar();
			}	
			listaRayos.remove(eliminarRayo);
			
			//movimiento de los disparos y eliminacion fuera de la pantalla
			for (Disparos disparo: this.listaDisparos) {
				if (disparo.getX()< 0|| disparo.getX()>800 ) {
					eliminarDisparos=disparo;
				}					
			disparo.avanzar();
			
			}
			listaDisparos.remove(eliminarDisparos);

			
			//deteccion de coliciones

			Colisiones.detectarColision(Barbarianna, listaEnemigos, listaRayos,listaDisparos, suelo);

			if(Colisiones.gano(Barbarianna, suelo)) {
				this.gano=true;
			}
		}
		
		public void procesarEventos(){

			if( this.entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				Barbarianna.moverDerecha();
				Barbarianna.setDerecha(true);		//agrego variable para saber cual fue su ultimo movimiento
				
			}
			if (this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA))	{
				Barbarianna.moverIzquierda();
				Barbarianna.setDerecha(false);
				
			}
			if ( this.entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				Barbarianna.moverSalto();
				
			}
			
			if ( this.entorno.sePresiono(entorno.TECLA_SHIFT)&& Barbarianna.puedeSaltar(suelo)){
				if(Barbarianna.getY()==Barbarianna.getYpiso()) {		//solo si barbarianna esta parada en el piso puede saltar al siguiente piso
					Barbarianna.comenzarSuperSalto();
				}
			}
			
			if ( this.entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				Barbarianna.agacharse();
			}
			if ( !this.entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				Barbarianna.parada();
			}
						
			if (this.entorno.sePresiono(entorno.TECLA_ARRIBA)==false) {
				Barbarianna.gravedad(suelo);
			}
		
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO) && listaDisparos.size()<cantDisparos) {
				this.listaDisparos.add(this.Barbarianna.disparar());	
				}
		}
		
		
		public void dibujarEntidades(){

			suelo.dibujarPiso(this .entorno, this.computadora, this.vida) ;
			
			//texto en pantalla
			entorno.cambiarFont("Arial", 20, Color.WHITE);
			entorno.escribirTexto("vidas restantes: "+Barbarianna.getVidas(), 20, 20);
			entorno.escribirTexto("puntos: "+Barbarianna.getPuntosExp(),700,20);
			entorno.escribirTexto("Enemigos Eliminados: "+ Barbarianna.getEnemigosEliminados(),300, 20);
			
			//dibujo de Barbarianna
			if(Barbarianna.isDerecha() && this.entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				Barbarianna.dibujarAgachada(entorno, BarAbajoDerecha);
			}
			else if(!Barbarianna.isDerecha() && this.entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				Barbarianna.dibujarAgachada(entorno, BarAbajoIzquierda);
			}
			
			else if(Barbarianna.isDerecha() && this.entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
				Barbarianna.dibujar(entorno, BarDisparoDerecha);
			}
			else if(!Barbarianna.isDerecha() && this.entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
				Barbarianna.dibujar(entorno, BarDisparoIzquierda);
			}
			
			else if(Barbarianna.isDerecha()) {	//si el ultimo movimiento fue a la derecha dibujo la imagen derecha	
				Barbarianna.dibujar(entorno, BarDerecha);}
			
			else{						//sino dibujo la imagen izquierda
				Barbarianna.dibujar(entorno, BarIzquierda);}
			
			
			//dibujo de Velociraptores
			for(Velociraptor enemigo:listaEnemigos) {	//recorro la lista de enemigos
				enemigo.dibujar(entorno, velociraptorIzq, velociraptorDer);				//y dibujo cada uno
			}
			
			//dibujo de rayos

			for(Rayo rayo:listaRayos) {
				rayo.dibujar(entorno);
				
			}

			//dibujo de disparos
			for(Disparos disparo: this.listaDisparos) {
				disparo.dibujarDisparos(disparoimagen, entorno);
			}
		
		
		
		}
		
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
