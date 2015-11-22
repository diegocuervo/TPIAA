package Genetico;

import negocio.Desplazamiento;

import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

public class Gen extends BaseGene implements Gene {

	private Desplazamiento desplazamiento;

	public Gen(Configuration conf, double x, double y)
			throws InvalidConfigurationException {
		super(conf);
		this.setDesplazamiento(new Desplazamiento(x, y));
	}

	public Desplazamiento getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(Desplazamiento desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyMutation(int arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPersistentRepresentation()
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllele(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setToRandomValue(RandomGenerator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueFromPersistentRepresentation(String arg0)
			throws UnsupportedOperationException,
			UnsupportedRepresentationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object getInternalValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Gene newGeneInternal() {
		// TODO Auto-generated method stub
		return null;
	}
}



