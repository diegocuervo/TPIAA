package negocio;

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
	}

	public void dibujar(){
		
		this.puntoCarga.draw();
		this.radio.draw();
	}
	
	public void actualizar(){
		this.puntoCarga.translate(this.desplazamiento.getX(), this.desplazamiento.getY());
		this.radio.translate(this.desplazamiento.getX(), this.desplazamiento.getY());
	}
	
	public void calcularDeplazamiento(List<CargaPuntual> cargas){
		
		Double x = new Double(0);
		Double y = new Double(0);
		
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
		
		this.setDesplazamiento(despl);
	}
	
	public void mover(){
		this.setPosX(this.posX + desplazamiento.getX());
		this.setPosY(this.posY + desplazamiento.getY());
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
