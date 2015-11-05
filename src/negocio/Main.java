package negocio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {

		////////CARGO PROPERTIES////////////
		Properties prop = new Properties();
		InputStream input = new FileInputStream("config.properties");
		prop.load(input);

		Double carga = new Double(prop.getProperty("carga"));
		Integer cantCargas = new Integer(prop.getProperty("cantidadCargas"));
		Double ancho = new Double(prop.getProperty("anchoCampo"));
		Double alto = new Double(prop.getProperty("altoCampo"));
		Integer iteraciones = Integer.parseInt(prop.getProperty("iteraciones"));
		Integer muestrasMonteCarlo = Integer.parseInt(prop.getProperty("muestrasMonteCarlo"));
		Integer tiempoEntreIteraciones = Integer.parseInt(prop.getProperty("tiempoEntreIteraciones"));
		Double porcentajeDeCorte = new Double(prop.getProperty("porcentajeDeCorte"));
		Boolean cortarPorPorcentaje = Boolean.parseBoolean(prop.getProperty("cortarPorPorcentaje"));
		Double factorDeplazamiento = new Double(prop.getProperty("factorDeplazamiento"));
		ArrayList<Double> porcentajesPorIteracion = new ArrayList<Double>();
		ArrayList<Double> porcentajesDesperdiciadosPorIteracion = new ArrayList<Double>();
		////////CARGO PROPERTIES////////////

		////////////////SIMULACIÓN/////////////////
		StringBuilder sbAreas = new StringBuilder();
		CampoDeJuego campo = new CampoDeJuego(carga, cantCargas, ancho, alto, factorDeplazamiento);
		campo.dibujar();
		
		Integer cantIteraciones = 0;
		for (int i = 0; i < iteraciones; i++) {
			cantIteraciones++;
	    	TimeUnit.SECONDS.sleep(tiempoEntreIteraciones);
	    	campo.iterarCargas();
			campo.actualizar();
			Double porcentajeCubierto = campo.porcentajeCubiertoMonteCarlo(muestrasMonteCarlo);
			porcentajesPorIteracion.add(porcentajeCubierto);
			porcentajesDesperdiciadosPorIteracion.add(campo.porcentajeAreaDesperdiciada(porcentajeCubierto));
			sbAreas.append("Área cubierta iteración " + i + ": " + porcentajeCubierto + "\r\n");
			if (	cortarPorPorcentaje &&
					porcentajeCubierto.compareTo(porcentajeDeCorte) >= 0) {
				break;
			}
		}
		////////////////SIMULACIÓN/////////////////

		/////////CÁLCULO DE ÁREA FINAL///////
		Double porcentaje = campo.porcentajeCubiertoMonteCarlo(muestrasMonteCarlo);
		System.out.println("El porcentaje cubierto del campo es:" + porcentaje);
		System.out.println("La cantidad de iteraciones realizadas fue: " + cantIteraciones);
		/////////CÁLCULO DE ÁREA FINAL///////
		
		/////////////////////////LOGS/////////////////////////////////////
		PrintWriter writer = new PrintWriter("resultados.log");
		writer.println(campo.informarPosicionesCargas());
		writer.println(sbAreas.toString());
		writer.println("El porcentaje cubierto del campo es: " + porcentaje);
		writer.println("La cantidad de iteraciones realizadas fue: " + cantIteraciones);
		writer.println("El área máxima teórica posible de cobetura es: " + campo.areaMaxCubiertaPosible());
		writer.println("El área cubierta fue: " + campo.areaCubierta(porcentaje));
		writer.println("El area de riego desperdiciada: " + campo.areaDesperdiciada(porcentaje));
		writer.println("El porcentaje de area de riego desperdiciada: " + campo.porcentajeAreaDesperdiciada(porcentaje));
		writer.close();
		
		PrintWriter writerCSV = new PrintWriter("resultados.csv");
		writerCSV	.append("Iteracion;")
					.append("Porcentaje;")
					.append("PorcDesperd")
					.append("\n");
		
		Integer j = 0;
		for (Double p : porcentajesPorIteracion) {
			j++;
			writerCSV.append(j.toString())
					.append(";");
			if (p.toString().length()>6) writerCSV.append(p.toString().substring(0, 6));
			else if (p.toString().length() == 5) writerCSV.append(p.toString()).append("0");
			else if (p.toString().length() == 4) writerCSV.append(p.toString()).append("00");
			else writerCSV.append(p.toString());
			
			writerCSV.append(";");
			
			String porcentajeDesp = porcentajesDesperdiciadosPorIteracion.get(j-1).toString();
			if (porcentajeDesp.length()>6) writerCSV.append(porcentajeDesp.substring(0, 6));
			else if (porcentajeDesp.length() == 5) writerCSV.append(porcentajeDesp).append("0");
			else if (porcentajeDesp.length() == 4) writerCSV.append(porcentajeDesp).append("00");
			else writerCSV.append(porcentajeDesp);
			
			writerCSV.append("\n");
			
		}
		writerCSV.close();
		/////////////////////////LOGS/////////////////////////////////////
	}
}
