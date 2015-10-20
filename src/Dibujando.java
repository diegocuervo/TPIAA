import java.util.concurrent.TimeUnit;



public class Dibujando {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("putos");
		Dibujando dibujito = new Dibujando();
		dibujito.dibujar();
		    }
	 public void dibujar(){
		 int vueltas=20;
		 Rectangle rectangulo = new Rectangle(150,100,100,100);
		 
	    	while(vueltas>0){
		    	
	    	rectangulo.draw();
	    	try{
	    	TimeUnit.SECONDS.sleep(2);
	    	}
	    	catch(java.lang.InterruptedException ie)
	    	{
	    		System.out.println(ie);
	    	}
	    	rectangulo.translate(-10,-10);
	    	vueltas=vueltas-1;
	    	
	 }
	}

}
