package negocio;

import java.beans.DesignMode;
import java.util.List;

import graphics.Ellipse;

public class CargaPuntual {
	private Double carga;
	private Double posX;
	private Double posY;
	private static Double RADIO_CHICO = new Double(3);
	private static Double FACTOR_DEPLAZAMIENTO = new Double(15);
	private Desplazamiento desplazamiento;
	private Ellipse puntoCarga;
	private Ellipse radio;
	private Double maxAncho;
	private Double maxAlto;
	
	
	/**
	 * Inicializa el campo en una posición al azar dentro del campo de juego con ancho y alto especificado
	 * @param carga
	 */
	public CargaPuntual(Double carga, Double maxAncho, Double maxAlto) {
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
	}

	public void dibujar(){
		
		this.puntoCarga.draw();
		this.radio.draw();
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
		
		Desplazamiento despl = new Desplazamiento(new Double(0), new Double(0));
		
		for (CargaPuntual cargaPuntual : cargas) {
			if (!this.equals(cargaPuntual) && estaDentroDelRadio(cargaPuntual)) {
				Desplazamiento d = this.versorDireccionOtraCarga(cargaPuntual);
				d.multiplicar(cargaPuntual.carga);
				d.dividir(this.distancia(cargaPuntual));
				d.multiplicar(FACTOR_DEPLAZAMIENTO);
				despl.sumar(d);
			}
		}
		
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
	
	private Boolean estaDentroDelRadio(CargaPuntual cp){
		return this.distancia(cp) < 2*this.carga;
	}
	
	public Double distancia(CargaPuntual cp){
		return Math.sqrt(((this.posX - cp.posX)*(this.posX - cp.posX)) + ((this.posY-cp.posY)*(this.posY-cp.posY)));
	}
	
	public boolean equals(CargaPuntual c) {
		return this.getPosX().equals(c.getPosX()) && this.getPosY().equals(c.getPosY());
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
