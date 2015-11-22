package Genetico;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import graphics.Text;
import negocio.Desplazamiento;

public class MainGenetico {
	public static void main(String[] args) throws Exception {
		
		///////////////CARGO PROPERTIES////////////
		Properties prop = new Properties();
		InputStream input = new FileInputStream("configGenetico.properties");
		prop.load(input);
		
		Double ancho = new Double(prop.getProperty("anchoCampo"));
		Double alto = new Double(prop.getProperty("altoCampo"));
		Integer cantAspersores = new Integer(prop.getProperty("cantidadAspersores"));
		Integer tamanoPoblacion = new Integer(prop.getProperty("tamanoPoblacion"));
		Integer muestrasMonteCarlo = Integer.parseInt(prop.getProperty("muestrasMonteCarlo"));
		Integer tiempoEntreIteraciones = Integer.parseInt(prop.getProperty("tiempoEntreIteraciones"));
		Integer iteraciones = Integer.parseInt(prop.getProperty("iteraciones"));
		Integer cantASeleccionar = Integer.parseInt(prop.getProperty("cantASeleccionar"));
		Double probMutacion = new Double(prop.getProperty("probabilidadMutacion"))/100;
		Double radio = new Double(prop.getProperty("radio"));
		Double porcentajeCorte = new Double(prop.getProperty("porcentajeDeCorte"));
		String SEPARADOR = prop.getProperty("separadorCSV");
		///////////////CARGO PROPERTIES////////////
		
		
		///////////GENERAR POBLACION INICIAL///////////////
		List<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		List<Cromosoma> mejoresPorIteracion = new ArrayList<Cromosoma>();
		
		for (int i = 0; i < tamanoPoblacion; i++) {
			
			Cromosoma c = new Cromosoma(cantAspersores, ancho, alto, muestrasMonteCarlo, radio);
			
			for (int j = 0; j < cantAspersores; j++) {
				Double altoNuevo = new Double(Math.random() * alto);
				Double anchoNuevo = new Double(Math.random() * alto);
				
				Gen g = new Gen(anchoNuevo, altoNuevo, radio);
				c.getGenes().add(g);
			}
			poblacion.add(c);
		}
		///////////GENERAR POBLACION INICIAL///////////////
		
		////////////////////SIMULACION///////////////////

		Text ejecutando = new Text(0, 0, "Ejecutando...");
		ejecutando.grow(200, 100);
		ejecutando.translate(200, 100);
		
		
		Text iteracion = new Text(0,0, "");
		iteracion.grow(200, 100);
		iteracion.translate(200, 300);
		
		ejecutando.draw();
		for (int i = 0; i < iteraciones; i++) {
			
			int it = i+1;
			iteracion.getLabel().setText("Iteración " + it);
			iteracion.draw();
			
			List<Cromosoma> seleccionados = seleccionar(poblacion, cantASeleccionar);
			
			List<Cromosoma> poblacionCruzada = cruzar(seleccionados, tamanoPoblacion, 
					cantAspersores, ancho, alto, muestrasMonteCarlo, radio);
			
			mutar(poblacionCruzada, probMutacion, ancho, alto);
			
			Cromosoma mejorIndividuo = mejorIndividuo(poblacionCruzada);
			mejoresPorIteracion.add(mejorIndividuo);
			
			if (mejorIndividuo.getAptitud() > porcentajeCorte) {
				break;
			}
			
			poblacion = poblacionCruzada;
		}

		////////////////////SIMULACION///////////////////

	
		/////////////////////////LOGS/////////////////////////////////////
		
		PrintWriter writerCSV = new PrintWriter("resultadosGenetico.csv");
		writerCSV	.append("Iteracion")
					.append(SEPARADOR)
					.append("Porcentaje")
					.append("\n");
		Integer j = 0;
		for (Cromosoma c : mejoresPorIteracion) {
			j++;
			writerCSV.append(j.toString())
					.append(SEPARADOR);
			if (c.getAptitud().toString().length()>6) writerCSV.append(c.getAptitud().toString().substring(0, 6));
			else if (c.getAptitud().toString().length() == 5) writerCSV.append(c.getAptitud().toString()).append("0");
			else if (c.getAptitud().toString().length() == 4) writerCSV.append(c.getAptitud().toString()).append("00");
			else writerCSV.append(c.getAptitud().toString());
			writerCSV.append("\n");
			
		}
		writerCSV.close();

		mejorIndividuo(mejoresPorIteracion).dibujar();
		/////////////////////////LOGS/////////////////////////////////////

	}
	
	public static List<Cromosoma> seleccionar(List<Cromosoma> poblacion, Integer cantASeleccionar){
		poblacion.sort(null);
		List<Cromosoma> sublist = poblacion.subList(0,cantASeleccionar);
		return sublist;
	}
	
	/**
	 * 
	 * Implementa cruzamiento al azar
	 * 
	 */
	public static List<Cromosoma> cruzar(List<Cromosoma> poblacionSeleccionada, Integer tamanoPoblacion,
			Integer cantidadAspersores, Double ancho, Double alto, Integer cantMuestras, Double radio){
		
		List<Cromosoma> poblacionNueva = new ArrayList<Cromosoma>();
		
		for (int i = 0; i < tamanoPoblacion; i++) {
			Cromosoma nuevo = new Cromosoma(cantidadAspersores, ancho, alto, cantMuestras, radio);
			
			Random generador = new Random();
			Cromosoma padre1 = poblacionSeleccionada.get(generador.nextInt(poblacionSeleccionada.size() ));
			Cromosoma padre2 = poblacionSeleccionada.get(generador.nextInt(poblacionSeleccionada.size() ));

			
			for (int j = 0; j < cantidadAspersores; j++) {
				Gen gen;
				if (Math.random() < new Double(0.5)) {
					//PADRE 1
					gen = padre1.getGenes().get(j);
				} else {
					//PADRE 2
					gen = padre2.getGenes().get(j);
				}
				nuevo.getGenes().add(gen);
			}
			
			poblacionNueva.add(nuevo);
		}		
		
		return poblacionNueva;
	}
	
	
	
	public static void mutar(List<Cromosoma> poblacion, Double probMutacion, Double ancho, Double alto){
		Random generador = new Random();
		
		if (Math.random() < probMutacion) {
			Cromosoma IndividuoMutado = poblacion.get(generador.nextInt(poblacion.size() ));
			
			Gen genMutado = IndividuoMutado.getGenes()
					.get(generador.nextInt(
							IndividuoMutado.getGenes().size()
					));
			Desplazamiento desplazamiento = new Desplazamiento(new Double(Math.random() * ancho), new Double (Math.random() * alto));
			genMutado.setDesplazamiento(desplazamiento);
		}
		
		
	}
	
	public static Cromosoma mejorIndividuo(List<Cromosoma> poblacion){
		poblacion.sort(null);
		return poblacion.get(0);
	}
}
