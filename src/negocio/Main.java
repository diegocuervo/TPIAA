package negocio;

import java.io.IOException;
import java.io.InputStream;
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
		////////CARGO PROPERTIES////////////

		////////////////SIMULACI�N/////////////////
		CampoDeJuego campo = new CampoDeJuego(carga, cantCargas, ancho, alto);
		campo.dibujar();
		
		for (int i = 0; i < iteraciones; i++) {
	    	TimeUnit.SECONDS.sleep(1);
	    	campo.iterarCargas();
			campo.actualizar();
		}
		////////////////SIMULACI�N/////////////////

		/////////C�LCULO DE �REA: MONTECARLO///////
		Double porcentaje = campo.porcentajeCubiertoMonteCarlo(muestrasMonteCarlo);
		System.out.println("El porcentaje cubierto del campo es:" + porcentaje);
		/////////C�LCULO DE �REA: MONTECARLO///////
	}
}
