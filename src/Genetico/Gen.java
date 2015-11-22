package Genetico;

import negocio.Desplazamiento;

import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

import graphics.Color;
import graphics.Ellipse;

public class Gen {

	
	private Desplazamiento desplazamiento;
	private Double radioCarga;
	private static Double RADIO_CHICO = new Double(3);
	private Ellipse puntoCentro;
	private Ellipse radio;

	public Gen(Double x, Double y, Double radioCarga){
		this.setDesplazamiento(new Desplazamiento(x, y));
		this.radioCarga = radioCarga;
		this.puntoCentro = new Ellipse(
				this.getDesplazamiento().getX()-RADIO_CHICO, 
				this.getDesplazamiento().getY()-RADIO_CHICO, 
				RADIO_CHICO*2, 
				RADIO_CHICO*2);
		this.radio = new Ellipse(
				this.getDesplazamiento().getX()-this.radioCarga, 
				this.getDesplazamiento().getY()-this.radioCarga, 
				this.radioCarga*2, 
				this.radioCarga*2);

	}
	
	public Desplazamiento getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(Desplazamiento desplazamiento) {
		this.desplazamiento = desplazamiento;
	}
	
	public void dibujar(){
		this.puntoCentro.draw();
		this.radio.draw();
		this.radio.setColor(new Color(0, 0, 200));

	}

}



