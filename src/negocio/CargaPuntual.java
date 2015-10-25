package negocio;

import graphics.Ellipse;

public class CargaPuntual {
	private Double carga;
	private Double posX;
	private Double posY;
	private static Double RADIO_CHICO = new Double(3);
	
	/**
	 * Inicializa el campo en una posición al azar dentro del campo de juego con ancho y alto especificado
	 * @param carga
	 */
	public CargaPuntual(Double carga, Double maxAncho, Double maxAlto) {
		this.setCarga(carga);
		this.setPosX(Math.random() * maxAncho);
		this.setPosY(Math.random() * maxAlto);
	}

	public void dibujar(){
		Ellipse carga = new Ellipse(this.getPosX()-RADIO_CHICO, 
									this.getPosY()-RADIO_CHICO, 
									RADIO_CHICO*2, 
									RADIO_CHICO*2);
		Ellipse radio = new Ellipse(this.getPosX()-this.getCarga(), 
									this.getPosY()-this.getCarga(), 
									this.getCarga()*2, 
									this.getCarga()*2);
		
		carga.draw();
		radio.draw();
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
	
}
