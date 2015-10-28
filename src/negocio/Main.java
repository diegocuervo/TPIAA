package negocio;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {

		////////CARGO PROPERTIES////////////
		Properties prop = new Properties();
		InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties");
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
		////////CARGO PROPERTIES////////////

		////////////////SIMULACI�N/////////////////
		StringBuilder sbAreas = new StringBuilder();
		CampoDeJuego campo = new CampoDeJuego(carga, cantCargas, ancho, alto);
		campo.dibujar();
		
		Integer cantIteraciones = 0;
		for (int i = 0; i < iteraciones; i++) {
			cantIteraciones++;
	    	TimeUnit.SECONDS.sleep(tiempoEntreIteraciones);
	    	campo.iterarCargas();
			campo.actualizar();
			Double porcentajeCubierto = campo.porcentajeCubiertoMonteCarlo(muestrasMonteCarlo);
			sbAreas.append("�rea cubierta iteraci�n " + i + ": " + porcentajeCubierto + "\n");
			if (	cortarPorPorcentaje &&
					porcentajeCubierto.compareTo(porcentajeDeCorte) >= 0) {
				break;
			}
		}
		////////////////SIMULACI�N/////////////////

		/////////C�LCULO DE �REA FINAL///////
		Double porcentaje = campo.porcentajeCubiertoMonteCarlo(muestrasMonteCarlo);
		System.out.println("El porcentaje cubierto del campo es:" + porcentaje);
		System.out.println("La cantidad de iteraciones realizadas fue: " + cantIteraciones);
		/////////C�LCULO DE �REA FINAL///////
		
		PrintWriter writer = new PrintWriter("resultados.log");
		writer.println(campo.informarPosicionesCargas());
		writer.println(sbAreas.toString());
		writer.println("El porcentaje cubierto del campo es: " + porcentaje);
		writer.println("La cantidad de iteraciones realizadas fue: " + cantIteraciones);
		writer.close();
	}
}
