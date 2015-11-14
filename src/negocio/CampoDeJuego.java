package negocio;

import graphics.Color;
import graphics.Rectangle;

import java.util.ArrayList;
import java.util.List;

import sun.security.krb5.internal.crypto.Des;

public class CampoDeJuego {
	private List<CargaPuntual> cargas;
	private Double ancho;
	private Double alto;
	private Rectangle rect;
	
	/**
	 * Inicializa un campo de juego con cargas posicionadas al azar
	 * @param carga
	 * @param cantidadCargas
	 * @param ancho
	 * @param alto
	 */
	public CampoDeJuego(Double carga, Integer cantidadCargas, Double ancho, Double alto,
			Double factorDesplazamiento, Double factorDesplazamientoParedes) {
		
		this.setCargas(new ArrayList<CargaPuntual>());
		for (int i = 0; i < cantidadCargas; i++) {
			CargaPuntual cp = new CargaPuntual(carga, ancho, alto, factorDesplazamiento, factorDesplazamientoParedes);
			this.getCargas().add(cp);
		}
		this.setAlto(alto);
		this.setAncho(ancho);
		this.rect = new Rectangle(0,0,ancho,alto);
	}
	
	public void dibujar(){

		rect.draw();
		rect.setColor(new Color(0, 100, 0));
		rect.fill();
		
		for (CargaPuntual carga : this.getCargas()) {
			carga.dibujar();
		}
		
	}
	
	public void actualizar(){
		for (CargaPuntual carga : this.getCargas()) {
			carga.actualizar();
		}
	}
	
	public void iterarCargas(){
		for (CargaPuntual carga : this.getCargas()) {
			carga.calcularDeplazamiento(this.getCargas());
		}
		for (CargaPuntual carga : this.getCargas()) {
			carga.mover();
		}
	}
	
	public Boolean estaCubiertoPorUnAspersor(Double x, Double y){
		for (CargaPuntual carga : this.getCargas()) {
			if (carga.cubreElPunto(x, y)) 
				return true;
		}
		return false;
	}
	
	public String informarPosicionesCargas(){
		StringBuilder sb = new StringBuilder();
		sb.append("Posición de las cargas:\n");
		Integer i = 0;
		for (CargaPuntual carga : this.getCargas()) {
			i++;
			sb.append("Carga #" + i + "\n");
			sb.append("\tPosX: " + carga.getPosX() + "\r\n");
			sb.append("\tPosY: " + carga.getPosY() + "\r\n");
		}
		 
		return sb.toString();
	}
	
	public Double porcentajeCubiertoMonteCarlo(Integer muestras){
		
//		List<Desplazamiento> puntosMuestreo = this.generarPuntosMuestreo(muestras);
//		
//		Integer cantidadCubierta = this.cantidadDePuntosCubierta(puntosMuestreo);
//		
//		Double porcentaje = (new Double(cantidadCubierta) / new Double(muestras)) * 100;
//
//		return porcentaje;

		List<Desplazamiento> puntosAspersores = new ArrayList<Desplazamiento>();
		
		for (CargaPuntual carga : this.getCargas()) {
			puntosAspersores.add(carga.puntoComoDesplazamiento());
		}
		
		return CalculadorDeSuperficie.porcentajeCubiertoMonteCarlo(this.ancho, this.alto, muestras, 
				this.cargas.get(0).getCarga(), puntosAspersores);
	}
	
	public Double porcentajeAreaDesperdiciada(Double porcentajeCubierto){
		return (this.areaDesperdiciada(porcentajeCubierto) / this.areaMaxCubiertaPosible()) * 100;
	}
	
	public Double areaDesperdiciada(Double porcentajeCubierto){
		Double amcp = this.areaMaxCubiertaPosible();
		Double ac = this.areaCubierta(porcentajeCubierto);
		return amcp - ac;
	}
	
	public Double areaCubierta(Double porcentajeCubierto){
		return this.areaTotal() * porcentajeCubierto / new Double(100);
	}
	
	public Double areaMaxCubiertaPosible(){
		Double radio = this.cargas.get(0).getCarga();
		Double cantidad = new Double(this.cargas.size());
		return cantidad * Math.PI * radio*radio;
	}
	
	private List<Desplazamiento> generarPuntosMuestreo(Integer cantidad){
		
		List<Desplazamiento> puntos = new ArrayList<Desplazamiento>();
		
		for (int i = 0; i < cantidad; i++) {
			Desplazamiento punto = new Desplazamiento(Math.random() * this.ancho, Math.random() * this.alto);
			puntos.add(punto);
		}
		
		return puntos;
	}
	
	private Integer cantidadDePuntosCubierta(List<Desplazamiento> puntos){
		Integer cant = 0;
		for (Desplazamiento despl : puntos) {
			if (this.estaCubiertoPorUnAspersor(despl.getX(), despl.getY())) {
				cant++;
			}
		}
		return cant;
	}
	
	public Double areaTotal(){
		return this.alto * this.ancho;
	}
	
	/**
	 * @return the cargas
	 */
	public List<CargaPuntual> getCargas() {
		return cargas;
	}

	/**
	 * @param cargas the cargas to set
	 */
	public void setCargas(List<CargaPuntual> cargas) {
		this.cargas = cargas;
	}

	/**
	 * @return the ancho
	 */
	public Double getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public Double getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(Double alto) {
		this.alto = alto;
	}
	
	
}
