package Genetico;

import java.util.ArrayList;
import java.util.List;

import graphics.Canvas;
import graphics.Color;
import graphics.Rectangle;
import negocio.CalculadorDeSuperficie;
import negocio.Desplazamiento;

public class Cromosoma implements Comparable<Cromosoma>{

	private List<Gen> genes;
	private int cantAspersores;
	private Double ancho;
	private Double alto;
	private Integer cantMuestras;
	private Double radio;
	private Double aptitud;
	private Rectangle rect;
	private Boolean dibujado = false;
	
	public Cromosoma(int cantidadAspersores, Double ancho, Double alto, Integer cantMuestras, Double radio) {
		this.genes = new ArrayList<Gen>();
		this.cantAspersores = cantidadAspersores;
		this.setAncho(ancho);
		this.setAlto(alto);
		this.setCantMuestras(cantMuestras);
		this.setRadio(radio);
		this.setAptitud(null);
		this.rect = new Rectangle(0,0,ancho,alto);
	}

	
	public List<Gen> getGenes() {
		return genes;
	}


	public void setGenes(List<Gen> genes) {
		this.genes = genes;
	}


	public int getCantAspersores() {
		return cantAspersores;
	}


	public void setCantAspersores(int cantAspersores) {
		this.cantAspersores = cantAspersores;
	}
	
	public List<Desplazamiento> puntosAspersores(){
		List<Desplazamiento> despl = new ArrayList<Desplazamiento>();
		
		for (Gen gen : genes) {
			despl.add(gen.getDesplazamiento());
		}
		return despl;
	}
	
	public Double getAptitud(){
		
		if (this.aptitud == null) {
			this.setAptitud(CalculadorDeSuperficie
				.porcentajeCubiertoMonteCarlo(ancho, alto, cantMuestras, radio, this.puntosAspersores()));
		}
		
		return this.aptitud;
	}
	

	public Double getAncho() {
		return ancho;
	}


	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}


	public Double getAlto() {
		return alto;
	}


	public void setAlto(Double alto) {
		this.alto = alto;
	}


	public Integer getCantMuestras() {
		return cantMuestras;
	}


	public void setCantMuestras(Integer cantMuestras) {
		this.cantMuestras = cantMuestras;
	}


	public Double getRadio() {
		return radio;
	}


	public void setRadio(Double radio) {
		this.radio = radio;
	}


	public void setAptitud(Double aptitud) {
		this.aptitud = aptitud;
	}
	
	public void dibujar(){
		
		if (!dibujado) {
			rect.draw();
			rect.setColor(new Color(0, 100, 0));
			rect.fill();
			dibujado = true;
		}


		for (Gen gen : genes) {
			gen.dibujar();
		}
		
	}


	@Override
	public int compareTo(Cromosoma o) {
//		YO menor igual o mayor
		int result = 0;
		
		if (this.getAptitud() > o.getAptitud()) {
			result = -1;
		} else if (this.getAptitud() < o.getAptitud()){
			result = 1;
		}
		
		return result;
	}
	
}











