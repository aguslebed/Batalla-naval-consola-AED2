package AgustinLebed.BatallaNaval;

public class PortaAviones extends Barco {
	
	final static int tamanio = 8;
	final int valorPorGolpe = 500;
	
	
	public PortaAviones() 
	{
		
		super(tamanio);
	}
	
	public int getValorPorGolpe() 
	{
		return this.valorPorGolpe;
	}

}
