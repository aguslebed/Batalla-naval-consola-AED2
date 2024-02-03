package AgustinLebed.BatallaNaval;

public class Destructor extends Barco {
	
	final static int tamanio = 6;
	final int valorPorGolpe = 300; 
	
	public Destructor() 
	{	
		//this.setTamaño(this.tamaño);
		super(tamanio);
		setDaños(0);
	}
	


	public int getValorPorGolpe() // esto devuelve el valor de un solo disparo pegado. Si se hunde el barco completo el puntaje va a ser valorPorGolpe * tamaño del barco.
	{
		return this.valorPorGolpe;
	}
	
	

}
