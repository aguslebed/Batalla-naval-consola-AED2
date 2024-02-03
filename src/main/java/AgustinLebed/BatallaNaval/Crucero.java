package AgustinLebed.BatallaNaval;

public class Crucero  extends Barco{
	
	private static final int tamanio = 4;
	//final int tamaño = 4;
	final int valorPorGolpe = 100;
	
	public Crucero() 
	{
		//this.setTamaño(this.tamaño);
		super(tamanio);
		
	}
	
	public int getValorPorGolpe() 
	{
		return valorPorGolpe;
	}

}
