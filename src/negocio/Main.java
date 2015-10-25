package negocio;

public class Main {
	public static void main(String[] args) {
		
		CampoDeJuego campo = new CampoDeJuego(new Double(100), 10, new Double(500), new Double(750));
		
		campo.dibujar();
		
		
	}
}
