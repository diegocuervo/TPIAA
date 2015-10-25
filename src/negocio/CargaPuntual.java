package negocio;

import java.util.Random;

public class CargaPuntual {
	private Double carga;
	private Double posX;
	private Double posY;
	
	/**
	 * Inicializa el campo en una posición al azar dentro del campo de juego con ancho y alto especificado
	 * @param carga
	 */
	public CargaPuntual(Double carga, Double maxAncho, Double maxAlto) {
		this.setCarga(carga);
		this.setPosX(Math.random() * maxAncho);
		this.setPosY(Math.random() * maxAlto);
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
