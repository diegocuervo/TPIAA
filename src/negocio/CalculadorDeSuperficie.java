package negocio;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es toda estática! Hay tabla para el que quiera instanciar.
 * 
 * Es todo medio feo porque antes estas cosas eran responsabilidad de muchas clases,
 * y para dejar todo afuera, hay que pasar muchos parámetros.
 * Vos lo pediste, Adrián!
 * @author Demian
 *
 */
public final class CalculadorDeSuperficie {
	/**
	 * Esta es una de las cosas feas que tiene el código, la clase Desplazamiento
	 * a veces es usada como un vector y a veces como un punto.
	 * En este caso, es un punto! 
	 */
	public static Double porcentajeCubiertoMonteCarlo(Double ancho, Double alto, 
			Integer cantidadDeMuestras, Double radio, List<Desplazamiento> puntosAspersores){
		
		List<Desplazamiento> puntosMuestreo = generarPuntosMuestreo(cantidadDeMuestras, ancho, alto);

		Integer cantidadCubierta = cantidadDePuntosCubierta(puntosAspersores, puntosMuestreo, radio);

		Double porcentaje = (new Double(cantidadCubierta) / cantidadDeMuestras) * 100;
		
		return porcentaje;
	}
	
	private static List<Desplazamiento> generarPuntosMuestreo(Integer cantidadDeMuestras, 
			Double ancho, Double alto){
		List<Desplazamiento> puntos = new ArrayList<Desplazamiento>();
		
		for (int i = 0; i < cantidadDeMuestras; i++) {
			Desplazamiento punto = new Desplazamiento(Math.random() * ancho, Math.random() * alto);
			puntos.add(punto);
		}
		
		return puntos;
	}
	
	private static Integer cantidadDePuntosCubierta(List<Desplazamiento> puntosAspersores, 
			List<Desplazamiento> puntosMuestreo, Double radio){
		
		Integer cant = 0;
		for (Desplazamiento despl : puntosMuestreo) {
			if (estaCubiertoPorAlgunAspersor(despl.getX(), despl.getY(), puntosAspersores, radio)) {
				cant++;
			}
		}
		return cant;
	}
	
	private static Boolean estaCubiertoPorAlgunAspersor(Double x, Double y, 
			List<Desplazamiento> puntosAspersores, Double radio){
		
		for (Desplazamiento aspersor : puntosAspersores) {
			if (estaCubierto(x, y, aspersor, radio)) 
				return true;
		}
		return false;

	}
	
	private static Boolean estaCubierto(Double x, Double y, Desplazamiento aspersor, Double radio){
		return aspersor.distancia(x,y) < radio;
	}
	
	
	private CalculadorDeSuperficie() {}
}
