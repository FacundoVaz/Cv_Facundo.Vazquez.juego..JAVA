package juego;

import java.awt.Point;
import java.util.LinkedList;

public class Colisiones {
	//con este metodo detecto todas las coliciones
	public static void detectarColision(Personaje Barbarianna, LinkedList<Velociraptor> listaEnemigos, LinkedList<Rayo> listaRayos, LinkedList<Disparos> listaDisparos, Mapa vida) {
		
		//colicion entre barbarianna y velociraptores
		Velociraptor eliminarEnemigo = null;			//declaro un enemigo para que no de error la lista al eliminar	
		for(Velociraptor enemigo: listaEnemigos) {			
			if(Colisiones.hayColision(Barbarianna, enemigo)) {
				eliminarEnemigo=enemigo;							//si hay colicion guardo el enemigo para despues borrarlo de la lista
				Barbarianna.setVidas(Barbarianna.getVidas()-1);	// y resto una vida al personaje
			}
		}
		listaEnemigos.remove(eliminarEnemigo);
		
		//colicion entre barbarianna y rayos lase
		//mismo procedimiento con cada rayo de los velociraptores
		Rayo eliminarRayo = null;
		if (!listaRayos.isEmpty()) {
			for(Rayo rayo: listaRayos) {
				if(Colisiones.hayColision(Barbarianna, rayo)) {
					eliminarRayo=rayo;
					Barbarianna.setVidas(Barbarianna.getVidas()-1);
				}
			}
			listaRayos.remove(eliminarRayo);
		}
		
		//colicion entre disparos y velociraptores
		//mismo procedimiento con cada disparo y los velociraptores
		Disparos eliminarDisparo=null;
		if (!listaDisparos.isEmpty()) {
			for(Disparos disparo: listaDisparos) {
				for (Velociraptor enemigo: listaEnemigos) {
					if(Colisiones.hayColision(disparo, enemigo)) {
						eliminarDisparo=disparo;
						eliminarEnemigo=enemigo;
						Barbarianna.setPuntosExp(Barbarianna.getPuntosExp()+10);
						Barbarianna.setEnemigosEliminados(Barbarianna.getEnemigosEliminados()+1);
					}
				}
			}
			listaDisparos.remove(eliminarDisparo);
			listaEnemigos.remove(eliminarEnemigo);
		}
		
		//colicion entre Barbarianna e item de vida
		if(hayColision(Barbarianna,vida)) {
			Barbarianna.setVidas(Barbarianna.getVidas()+1);
			vida.setxVida(900);
		}
	}
	
	public static double distancia(Point p1, Point p2) {		//calculo la distancia entre los centros de los objetos dados
		int distanciaX = p1.x - p2.x;
		int distanciaY = p1.y - p2.y;
		double resultado = Math.sqrt((Math.pow(distanciaX,2))+(Math.pow(distanciaY,2)));
		return resultado;	
	}
	
	public static boolean hayColision(Personaje barbarianna, Velociraptor enemigo) {
		Point centroPersonaje = new Point(barbarianna.getX(),barbarianna.getY());	//guardo el centro de cada personaje
		Point centroEnemigo = new Point(enemigo.getX(),enemigo.getY());
		double distanciaCentros = distancia(centroPersonaje,centroEnemigo);		//calculo la distancia que hay entre los dos objetos
		
		double distanciaAltos = ((barbarianna.getAlto()/2)+(enemigo.getAlto()/2));
		double distanciaAnchos = ((barbarianna.getAncho()/2)+(enemigo.getAncho()/2));	//y la distancia de los centros si estuvieran pegados
		if(distanciaCentros<=distanciaAnchos || distanciaCentros <= distanciaAltos) { //si la distancia de los centros es menor quiere decir que hay colision
			return true;
		}
		return false;
	}
	public static boolean hayColision(Personaje barbarianna, Rayo rayo) {	//idem al de arriba pero con los rayos
		Point centroPersonaje = new Point(barbarianna.getX(),barbarianna.getY()-10);
		Point centroRayo = new Point(rayo.getX(),rayo.getY());
		double distanciaCentros = distancia(centroPersonaje,centroRayo);
		
		double distanciaAnchos = ((barbarianna.getAncho()/2)+(rayo.getAncho()/2));
		double distanciaAltos = ((barbarianna.getAlto()/2)+(rayo.getAlto()/2));
		if(distanciaCentros<=distanciaAnchos || distanciaCentros <= distanciaAltos) {
			return true;
		}
		return false;
	}
	
	public static boolean hayColision(Disparos disparo, Velociraptor enemigo ) {	//idem al de arriba pero con los rayos
		Point centroDisparo = new Point(disparo.getX(),disparo.getY());
		Point centroEnemigo = new Point(enemigo.getX(),enemigo.getY());
		double distanciaCentros = distancia(centroDisparo,centroEnemigo);
		
		double distanciaAnchos = ((disparo.getAncho()/2)+(enemigo.getAncho()/2));
		double distanciaAltos = ((disparo.getAlto()/2)+(enemigo.getAlto()/2));
		if(distanciaCentros<=distanciaAnchos || distanciaCentros <= distanciaAltos) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	public static boolean hayColision(Personaje barbarianna, Mapa vida) {
		Point centroPersonaje = new Point(barbarianna.getX(),barbarianna.getY());
		Point centroVida = new Point(vida.getxVida(),vida.getyVida());
		double distanciaCentros = distancia(centroPersonaje,centroVida);
		
		double distanciaAnchos = ((barbarianna.getAncho()/2)+(vida.getAnchoVida()/2));
		if(distanciaCentros<=distanciaAnchos) {
			return true;	//si el personaje toca la vida retorno true 
		}
		return false;
	}


	
	public static boolean gano(Personaje barbarianna, Mapa pc) {
		Point centroPersonaje = new Point(barbarianna.getX(),barbarianna.getY());
		Point centropc = new Point(pc.getxPc(),pc.getyPc());
		double distanciaCentros = distancia(centroPersonaje,centropc);
		
		double distanciaAnchos = ((barbarianna.getAncho()/2)+(pc.getAnchoPc()/2));
		if(distanciaCentros<=distanciaAnchos) {
			return true;	//si el personaje toca la pc retorno true para avisar que gano
		}
		return false;
	}
	
	
}
