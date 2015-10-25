package negocio;

public class Desplazamiento {
	private Double x;
	private Double y;
	
	public Desplazamiento(Double x, Double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public void sumar(Desplazamiento d){
		this.setX(x + d.getX());
		this.setY(y + d.getY());
	}
	
	public void sumar(Double xd, Double yd){
		this.setX(x+xd);
		this.setY(y+yd);
	}
	
	public void multiplicar(Double a){
		this.setX(x*a);
		this.setY(y*a);
	}
	
	public void dividir(Double a){
		this.setX(x/a);
		this.setY(y/a);
	}

	/**
	 * @return the x
	 */
	public Double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(Double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(Double y) {
		this.y = y;
	}
	
	
}
