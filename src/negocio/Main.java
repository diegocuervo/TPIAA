package negocio;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		CampoDeJuego campo = new CampoDeJuego(new Double(100), 15, new Double(500), new Double(750));
		
		campo.dibujar();
		
		for (int i = 0; i < 50; i++) {
	    	TimeUnit.SECONDS.sleep(1);
	    	campo.iterarCargas();
			campo.actualizar();
		}
		
		System.out.println("HOla");
		
	}
}
