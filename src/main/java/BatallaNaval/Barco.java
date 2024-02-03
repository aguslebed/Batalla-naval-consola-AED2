package BatallaNaval;

public class  Barco {
	
	
	//Atributos
	private int tamanio;
	private String nombre;
	private int danios;
	private int fila;
	private int columna;
	private boolean vertical; /*Si es true, se va a generar desde coordsX,coordsY hacia abajo, 
								Si es falso se va a generar desde coordsX,coordsY hacia la derecha */
	private int puntajeBloque;
	private int valorBarco; 
	private boolean hundido;

	
	//Metodos de la clase Barco
	public  Barco(int tamanio) //Constructor
	{	
		this.setFila(1);
		this.setColumna(1);
		this.tamanio = tamanio;// cada barco le pasa su tamaño cuando se llama al constructor de Barco
	}
	
	public void sumarDaños() 
	{
		this.danios +=1;
	}
	
	public boolean hundido() 
	{
		if (this.danios == this.tamanio) 
			{
				return true;
			}else 
			{
				return false;
			}
		
	}

	
	 public Boolean CoordBarco ( int fila, int columna) { 
		 
	    Boolean coords = true;
	    if(this.vertical==true) {
	    	if(columna == this.columna &&  fila >= this.fila && fila <= this.fila+this.tamanio-1) 
		    {
		    	coords = true;
		    }else 
		    {
		    	coords = false;
		    }
	    	
	    } else if(this.vertical==false)
	    {
	    	
	    	if(fila == this.fila && columna >= this.columna && columna <= this.columna + this.tamanio-1 )
		    {
		    			coords = true;
		    }else 
		    {
		    	coords=false;
		    }
	    } 
	    		
	    		return coords ;
	    } 
	
	
	
	//Get y set
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getTamanio() 
	{
		return this.tamanio;
	}

	public void setTamanio(int tamaño) 
	{
		this.tamanio = tamaño;
	}

	
	public int getFila() {
		return this.fila;
	}

	
	public void setFila(int coordsX) {
		this.fila = coordsX;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int coordsY) {
		this.columna = coordsY;
	}

	public boolean getVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

	public int getValorBarco() {
		return valorBarco;
	}

	public void setValorBarco(int valorBarco) {
		this.valorBarco = valorBarco;
	}
	
	public int getDaños() 
	{
		return this.danios;
	}
	
	public void setDaños(int daño) 
	{
		this.danios = daño;
	}

	public int getPuntajeBloque() {
		return puntajeBloque;
	}

	
	
	public void setPuntajeBloque(int puntajeBloque) {
		this.puntajeBloque = puntajeBloque;
	}
        
        public String getDaniosString()
        {
            return "Daños: "+this.danios;
        }
        
        public String getFilaString()
        {
            return "Coordenada X inicial "+(this.fila+1);//el +1 es porque en la pantalla del juego es de 1 a 12, pero para el programa es de 0 a 11
        }
        
        public String getColumnaString()
        {
            return "Coordenada Y inicial "+(this.columna+1);
        }
        
        public void setHundido(boolean hundido) 
        {
        	this.hundido = hundido;
        }
        public boolean getHundido() 
        {
        	return this.hundido;
        }
}