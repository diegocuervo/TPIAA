package Genetico;

import negocio.Desplazamiento;

import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

public class Gen {

	
	private Desplazamiento desplazamiento;

	public Gen(Double x, Double y){
		this.setDesplazamiento(new Desplazamiento(x, y));
	}
	
	public Desplazamiento getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(Desplazamiento desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

}



