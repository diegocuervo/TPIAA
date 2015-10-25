package negocio;

import graphics.Color;
import graphics.Rectangle;

import java.util.ArrayList;
import java.util.List;

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
	public CampoDeJuego(Double carga, Integer cantidadCargas, Double ancho, Double alto) {
		
		this.setCargas(new ArrayList<CargaPuntual>());
		for (int i = 0; i < cantidadCargas; i++) {
			CargaPuntual cp = new CargaPuntual(carga, ancho, alto);
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
