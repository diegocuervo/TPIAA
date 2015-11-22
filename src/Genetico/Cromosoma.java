package Genetico;

import java.util.ArrayList;
import java.util.List;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.impl.DefaultConfiguration;

public class Cromosoma {

	private List<Gen> genes;
	private int cantAspersores;
	
	public Cromosoma(int cantidadAspersores){
		genes= new ArrayList<Gen>();
		cantAspersores=cantidadAspersores;
		
	}

	
	public void crearCromosoma()throws Exception{
	Configuration conf = new DefaultConfiguration();
	Chromosome sampleChromosome = new Chromosome(conf,(Gen[])genes.toArray());
	  conf.setSampleChromosome( sampleChromosome );
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

	
	
}
