package negocio;

import java.beans.DesignMode;
import java.util.List;

import graphics.Color;
import graphics.Ellipse;

public class CargaPuntual {
	private Double carga;
	private Double posX;
	private Double posY;
	private static Double RADIO_CHICO = new Double(3);
	private Double FACTOR_DESPLAZAMIENTO = new Double(1);
	private Double FACTOR_DESPLAZAMIENTO_PAREDES = new Double(1);
	private Desplazamiento desplazamiento;
	private Ellipse puntoCarga;
	private Ellipse radio;
	private Double maxAncho;
	private Double maxAlto;
	
	
	/**
	 * Inicializa el campo en una posici�n al azar dentro del campo de juego con ancho y alto especificado
	 * @param carga
	 */
	public CargaPuntual(Double carga, Double maxAncho, Double maxAlto, 
			Double factorDesplazamiento, Double factorDesplazamientoParedes) {
		this.setCarga(carga);
		this.setPosX(Math.random() * maxAncho);
		this.setPosY(Math.random() * maxAlto);
		this.setDesplazamiento(new Desplazamiento(new Double(0), new Double(0)));
		
		this.puntoCarga = new Ellipse(this.getPosX()-RADIO_CHICO, 
				this.getPosY()-RADIO_CHICO, 
				RADIO_CHICO*2, 
				RADIO_CHICO*2);
		this.radio = new Ellipse(this.getPosX()-this.getCarga(), 
				this.getPosY()-this.getCarga(), 
				this.getCarga()*2, 
				this.getCarga()*2);
		
		this.maxAlto = maxAlto;
		this.maxAncho = maxAncho;
		this.FACTOR_DESPLAZAMIENTO = factorDesplazamiento;
		this.FACTOR_DESPLAZAMIENTO_PAREDES = factorDesplazamientoParedes;
	}

	public void dibujar(){
		
		this.puntoCarga.draw();
		this.radio.draw();
		this.radio.setColor(new Color(0, 0, 200));
	}
	
	public void actualizar(){
		if (this.posX == this.maxAncho || this.posX == 0) {
			this.desplazamiento.setX(new Double(0));
		} 
		if (this.posY == this.maxAlto || this.posY == 0){
			this.desplazamiento.setY(new Double(0));
		}
		this.puntoCarga.translate(this.desplazamiento.getX(), this.desplazamiento.getY());
		this.radio.translate(this.desplazamiento.getX(), this.desplazamiento.getY());

	}
	
	public void calcularDeplazamiento(List<CargaPuntual> cargas){
		
		Desplazamiento despl = this.desplazamientoPorParedes(this.maxAlto, this.maxAncho);
		
		for (CargaPuntual cargaPuntual : cargas) {
			if (!this.equals(cargaPuntual) && estaDentroDelRadio(cargaPuntual)) {
				Desplazamiento d = this.versorDireccionOtraCarga(cargaPuntual);
				d.multiplicar(cargaPuntual.carga);
				d.dividir(this.distancia(cargaPuntual));
				d.multiplicar(FACTOR_DESPLAZAMIENTO);
				despl.sumar(d);
			}
		}
		
		//Restringido al �rea del campo
		
		if (this.posX + this.desplazamiento.getX() > this.maxAncho) {
			this.desplazamiento.setX(this.maxAncho - this.posX);
		} else if (this.posX + this.desplazamiento.getX() < 0) {
			this.desplazamiento.setX(0-this.posX);
		}
		
		if (this.posY + this.desplazamiento.getY() > this.maxAlto) {
			this.desplazamiento.setY(this.maxAlto - this.posY);
		}else if (this.posY + this.desplazamiento.getY() < 0){
			this.desplazamiento.setY(0-this.posY);
		}
		
		this.setDesplazamiento(despl);
	}
	
	public void mover(){
		
		Double destinox = this.posX + desplazamiento.getX();
		Double destinoy = this.posY + desplazamiento.getY();
		
		if (destinox > this.maxAncho) {
			destinox = this.maxAncho;
		} else if (destinox < 0){
			destinox = new Double(0);
		}
		
		if (destinoy > this.maxAlto) {
			destinoy = this.maxAlto;
		} else if (destinoy < 0){
			destinoy = new Double(0);
		}
		
		this.setPosX(destinox);
		this.setPosY(destinoy);
	}
	
	public Desplazamiento versorDireccionOtraCarga(CargaPuntual cp){
		Double despX = new Double(this.getPosX() - cp.getPosX());
		Double despY = new Double(this.getPosY() - cp.getPosY());
		Double distancia = this.distancia(cp);
		
		despX = despX/distancia;
		despY = despY/distancia;
		
		return new Desplazamiento(despX, despY);
	}
	
	private Desplazamiento desplazamientoPorParedes(Double alto, Double ancho){
		
		Desplazamiento despl = new Desplazamiento(new Double(0), new Double(0));
		
		//ABAJO
		Desplazamiento d1 = new Desplazamiento(new Double(0), new Double(1));
		d1.multiplicar(this.carga);
		d1.multiplicar(FACTOR_DESPLAZAMIENTO_PAREDES);
		d1.dividir(this.posY);
		despl.sumar(d1);
		//ARRIBA
		Desplazamiento d2 = new Desplazamiento(new Double(0), new Double(-1));
		d2.multiplicar(this.carga);
		d2.multiplicar(FACTOR_DESPLAZAMIENTO_PAREDES);
		d2.dividir(this.maxAlto - this.posY);
		despl.sumar(d2);
		//IZQUIERDA
		Desplazamiento d3 = new Desplazamiento(new Double(1), new Double(0));
		d3.multiplicar(this.carga);
		d3.multiplicar(FACTOR_DESPLAZAMIENTO_PAREDES);
		d3.dividir(this.posX);
		despl.sumar(d3);
		//DERECHA
		Desplazamiento d4 = new Desplazamiento(new Double(-1), new Double(0));
		d4.multiplicar(this.carga);
		d4.multiplicar(FACTOR_DESPLAZAMIENTO_PAREDES);
		d4.dividir(this.maxAncho - this.posX);		
		despl.sumar(d4);
		
		return despl;
	}
	
	private Boolean estaDentroDelRadio(CargaPuntual cp){
		return this.distancia(cp) < 2*this.carga;
	}
	
	public Double distancia(CargaPuntual cp){
		return this.distancia(cp.posX, cp.posY);
	}
	
	public Double distancia(Double x, Double y){
		return Math.sqrt(((this.posX - x)*(this.posX - x)) + ((this.posY - y)*(this.posY - y)));
	}
	
	public Desplazamiento puntoComoDesplazamiento(){
		return new Desplazamiento(this.getPosX(), this.getPosY());
	}
	
	public Double areaCubierta(List<CargaPuntual> cargas){
		//TODO
		return null;
	}
	
	public Double areaDentroDelCampo(){
		//Fuera hacia la izquierda
		if (this.posX - this.carga < 0){
			return this.areaCircular(this.carga) - areaFuera(this.carga, this.carga - this.posX);
		}
		//Fuera hacia la izquierda
		if (this.posX + this.carga > this.maxAncho) {
			return this.areaCircular(this.carga) - areaFuera(this.carga, this.carga + this.posX - this.maxAncho);
		}
		//Fuera hacia abajo
		if (this.posY + this.carga > this.maxAlto) {
			return this.areaCircular(this.carga) - areaFuera(this.carga, this.posY + this.carga - this.maxAlto);
		}
		//Fuera hacia arriba
		if (this.posY - this.carga < 0) {
			return this.areaCircular(this.carga) - areaFuera(this.carga, this.carga - this.posY);
		}
		//Totalmente adentro del campo
		return this.areaCircular(this.carga);
	}
	
	/**
	 * F�rmula citada en el paper
	 * @param r Radio del c�rculo
	 * @param k Porci�n del radio fuera del campo
	 * @return �rea fuera del campo
	 */
	private Double areaFuera(Double r, Double k){
		return (r*r*Math.acos((r-k)/r))-((r-k)*Math.sqrt(2*k*r-(r*r))) ;
	}
	
	private Double areaCircular(Double r){
		return Math.PI*r*r;
	}
	
	public boolean equals(CargaPuntual c) {
		return this.getPosX().equals(c.getPosX()) && this.getPosY().equals(c.getPosY());
	}
	
	public Boolean cubreElPunto(Double x, Double y){
		return this.distancia(x,y) < this.carga;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.equals((CargaPuntual)obj);
	}
	
	/**
	 * @return the carga
	 */
	public Double getCarga() {
		return carga;
	}

	/**
	 * @param carga the carga to set
	 */
	public void setCarga(Double carga) {
		this.carga = carga;
	}

	/**
	 * @return the posX
	 */
	public Double getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(Double posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public Double getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(Double posY) {
		this.posY = posY;
	}

	/**
	 * @return the desplazamiento
	 */
	public Desplazamiento getDesplazamiento() {
		return desplazamiento;
	}

	/**
	 * @param desplazamiento the desplazamiento to set
	 */
	public void setDesplazamiento(Desplazamiento desplazamiento) {
		this.desplazamiento = desplazamiento;
	}
	
	
}
