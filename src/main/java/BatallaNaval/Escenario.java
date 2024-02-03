package BatallaNaval;

import java.util.Random;

public class Escenario {
	
	private char[][] pantallaJuego;
	private Jugador jugador;
	private Crucero crucero;
	private Destructor destructor;
	private PortaAviones portaAviones;
	private int turno;
	
	static final int cantidadFilas = 12;//si se esta haciendo en formulario hay que cambiar los tamaños de filas y columnas segun la cantidad que se pongan
    static final int cantidadColumnas = 25;
        
        //estas 3 coordenadas tienen que ser de tipo char si se ejecuta por consola o String si es con formularios
	static final char Bloque = 'X';     //Barco sin golpe(1 bloque)
	static final char Tocado = '0';     //Golpe en un barco
        static final char Agua   = '.';     //No hay barco
	
	public Escenario() 
	{
		this.pantallaJuego = new char[this.cantidadFilas][this.cantidadColumnas];
		this.setJugador(new Jugador());
		this.rellenarEscenario();// Cuando se intancia el escenario se rellena de puntos(Agua)
        this.destructor = getJugador().getDestructor();
        this.portaAviones = getJugador().getPortaaviones();
        this.crucero = getJugador().getCrucero();	
	}
	

	
	public void rellenarEscenario() 
	{
            int i,j;
		
		for (i=0; i<=this.cantidadFilas-1; i++) 
		{
			for (j=0; j<=this.cantidadColumnas-1; j++) 
			{
				this.pantallaJuego[i][j] = Agua;
                           
			}
		}
	}
	
	public void imprimirEscenario() //usar imprimir lineas
	{
		int i,j;
		
		for (i=0; i<=this.pantallaJuego.length-1; i++) 
		{
			for (j=0; j<=this.pantallaJuego.length-1; j++) 
			{
				System.out.print(this.pantallaJuego[i][j]+" ");
			}
			System.out.println("");
		}
	}

	
	

	
	
	
	public boolean coordenadasVacias(int fila, int columna, boolean vertical, int tamanio) 
	{	int i=0;
		boolean vacio = true;
		
		try {
	     if (vertical==false) 
	     {
	    	 
	    	 for (i=columna; i<columna+tamanio-1; i++ ){
		            if  (this.pantallaJuego [fila][i] == Bloque ) 
		            {   vacio = false ; 
		            break;
		            } 
		           }  	 
	     } else if (vertical==true && fila <=this.cantidadFilas()-tamanio-1) 
	     {
	    	 for (i=fila; i<fila+tamanio; i++ ){
		            if  (this.pantallaJuego [i][columna] == Bloque ) 
		            {   vacio = false ;
		            break;
		            } 
		    }  
	     }
	      
		}catch (Exception e) {
			System.out.println(e+" I="+i);
		}
	    return ( vacio ) ;
	}

	
	
	public void colocarBarco(Barco barco) 
	{
	int fila=0, columna=0,cont,filaaux, columnaaux;
	Random random  = new Random();
	
	cont=barco.getTamanio();
	
	fila = random.nextInt(this.cantidadFilas()-barco.getTamanio());
	
	columna = random.nextInt(this.cantidadColumnas()-barco.getTamanio());
	

	fila = Math.abs(fila);

	columna = Math.abs(columna);
	
			//Se verifica que coord x y coord y esten  adentro de la matriz, y que no haya otro barco en el casillero
		while(coordenadasVacias(fila, columna,barco.getVertical(),barco.getTamanio()) != true) 
		{	
			
			
			fila = random.nextInt(this.cantidadFilas());
			columna = random.nextInt(this.cantidadColumnas());
	
			fila = Math.abs(fila);

			columna = Math.abs(columna);
		
	
	}
		
		barco.setFila(fila);
		barco.setColumna(columna);
		
	}
	
	/*public boolean verificarCoordenadas(int cont, int coordX, int coordY,Barco barco)// cont tiene que incializarce con el tamaño del barco que se quiere ubicar. Si devuelve true es porque las coordenadas se pueden usar par aubicar al barco.
	{
		if (cont==0)
		{
			return true;
		}
		else
			if (barco.getVertical()==true && coordX>=0 && coordX<=this.cantidadFilas && coordY>=0 && coordY<=this.cantidadColumnas && this.pantallaJuego[coordX][coordY]!=Bloque) 
			{
				verificarCoordenadas(cont--,coordX,coordY++,barco);
			}
			else 
				if (barco.getVertical()==false && coordX>=0 && coordX<=this.cantidadFilas && coordY>=0 && coordY<=this.cantidadColumnas && this.pantallaJuego[coordX][coordY]!=Bloque) 
				{
					verificarCoordenadas(cont--,coordX++,coordY,barco);
				}
		else 
		{
			return false;
		}
	
	}*/
	
	

	
	
	public String imprimirLinea(int fila) 
	{
		int j;
		String linea = " ";
		
		//1000 son los datos del jugador
		//2000 son los datos del destructor
		//3000 son los datos del portaaviones
		//4000 son los datos del crucero
		
		switch (fila)
		{ 
		case 1000:
		{
			linea = "  Jugador: "+ jugador.getNick()+"  Puntos: "+jugador.getPuntaje()+" Barcos disponibles: "+(3-jugador.getHundidos()); 
			break;
		}
		case 2000:
		{
			if(destructor.getVertical()) 
			{
				linea = "  Destructor:"+destructor.getNombre()+" Daños: "+destructor.getDaños()+ " Coordenadas iniciales: ("+destructor.getFila()+","+destructor.getColumna()+") Coordenadas finales: ("+(destructor.getFila()+destructor.getTamanio()-1)+","+destructor.getColumna()+")";
                break;
			}else
			{
				linea = "  Destructor:"+destructor.getNombre()+" Daños: "+destructor.getDaños() + " Coordenadas iniciales: ("+destructor.getFila()+","+destructor.getColumna()+") Coordenadas finales: ("+destructor.getFila()+","+(destructor.getColumna()+destructor.getTamanio()-1)+")";
                break;
			}
			
		}
		case 3000:
		{
			if(portaAviones.getVertical()) 
			{
				linea = "  Portaaviones:"+portaAviones.getNombre()+" Daños: "+portaAviones.getDaños()+ " Coordenadas iniciales: ("+portaAviones.getFila()+","+portaAviones.getColumna()+") Coordenadas finales: ("+(portaAviones.getFila()+portaAviones.getTamanio()-1)+","+portaAviones.getColumna()+")";
                break;
			}else 
			{
				linea = "  Portaaviones:"+portaAviones.getNombre()+" Daños: "+portaAviones.getDaños() + " Coordenadas iniciales: ("+portaAviones.getFila()+","+portaAviones.getColumna()+") Coordenadas finales: ("+portaAviones.getFila()+","+(portaAviones.getColumna()+portaAviones.getTamanio()-1)+")";
                break;
			}
		}
		case 4000:
		{
			if(crucero.getVertical()) 
			{
				linea = "  Crucero:"+crucero.getNombre()+" Daños: "+crucero.getDaños()+ " Coordenadas iniciales: ("+crucero.getFila()+","+crucero.getColumna()+") Coordenadas finales: ("+(crucero.getFila()+crucero.getTamanio()-1)+","+crucero.getColumna()+")";
                break;
			}else
			{
				linea = "  Crucero:"+crucero.getNombre()+" Daños: "+crucero.getDaños() + " Coordenadas iniciales: ("+crucero.getFila()+","+crucero.getColumna()+") Coordenadas finales: ("+crucero.getFila()+","+(crucero.getColumna()+crucero.getTamanio()-1)+")";
                break;
			}
		}
		default:
                {	
		for(j=0; j<this.cantidadColumnas; j++) 
		{
		
			linea = linea + this.pantallaJuego[fila][j] + "" ;
		}
		break;
		}
		}//switch
		return linea;
	}
	
	
	
	public void  disparo(int fila, int columna, Jugador jugador) 
	{
		if(this.pantallaJuego[fila][columna]==Bloque) 
		{
			this.pantallaJuego[fila][columna]=Tocado;
			
			if(destructor.CoordBarco(fila, columna)== true ) 
			{
				this.destructor.sumarDaños();
				jugador.sumarPuntaje(this.destructor.getValorPorGolpe());
				System.out.println("Le pegaste al "+this.destructor.getNombre());
				verificarHundido(this.destructor);
			}
			if(crucero.CoordBarco(fila, columna)== true) 
			{	
				this.crucero.sumarDaños();
				jugador.sumarPuntaje(this.crucero.getValorPorGolpe());
				System.out.println("Le pegaste al "+this.crucero.getNombre());
				verificarHundido(this.crucero);
			}
			if(portaAviones.CoordBarco(fila, columna)==true) 
			{
				this.portaAviones.sumarDaños();
				jugador.sumarPuntaje(this.portaAviones.getValorPorGolpe());
				System.out.println("Le pegaste al "+this.portaAviones.getNombre());
				verificarHundido(this.portaAviones);
			
			}
			
			
		}else if(this.pantallaJuego[fila][columna]==Agua)
		{
			System.out.println("El disparo cayo en el agua");
		}else if(this.pantallaJuego[fila][columna]==Mina.minaChar) 
		{
			System.out.println("El disparo cayo en una mina");
		}
		

	}
	
	
	
	public boolean verificarHundido(Barco barco) 
	{
		int valorBarco = 0,i,cont=0;
		
		if (barco.getVertical()) 
		{
			for(i=barco.getFila(); i<(barco.getFila()+barco.getTamanio()); i++) 
			{
				if(this.pantallaJuego[i][barco.getColumna()]==Tocado) 
				{
					cont++;
				}
			}
			
		}else if (barco.getVertical()==false) 
		{
			 for(i=barco.getColumna(); i<(barco.getColumna()+barco.getTamanio()); i++) 
			 {
				 if(this.pantallaJuego[barco.getFila()][i]==Tocado) 
					{
						cont++;
					}
			 }
			
		}

		if(barco.getDaños()==barco.getTamanio()) 
		{
			barco.setHundido(true);
			this.jugador.sumarHundidos();
			return true;
		}else 
		{
			return false;
		}
	}
	
	
	public void colocarMinas(int fila, int columna, int cantidad, int cont) 
	{
	    Mina mina = new Mina();
		int j=0;
		Random random  = new Random();
	
		if (cont==cantidad) 
		{
			System.out.print("");
			
		}else 
		{
			fila = random.nextInt((this.cantidadFilas()-1) - 1 ) + 1;
			columna = random.nextInt((this.cantidadColumnas()-1) - 1) + 1;
			
			
			fila = Math.abs(fila);
			columna = Math.abs(columna);
			
			if (this.pantallaJuego[fila][columna]==Agua) 
			{
			    mina.setFila(fila);
			    mina.setColumna(columna);
				this.pantallaJuego[fila][columna]=mina.minaChar;
				colocarMinas(fila,columna,cantidad,++cont);
			}else 
			{
				colocarMinas(fila,columna,cantidad,cont);
			}
		}
	
	}
	
	
	public void explotarMinas(int fila, int columna,Mina mina) 
	{
		
		//si la explosion tiene agua en las 8 posiciones del alrededor se termina la iteracion
		if((this.pantallaJuego[fila][columna]==Agua) && (this.pantallaJuego[fila+1][columna]==Agua) && (this.pantallaJuego[fila-1][columna]==Agua)
				&& (this.pantallaJuego[fila-1][columna-1]==Agua) && (this.pantallaJuego[fila-1][columna+1]==Agua) && (this.pantallaJuego[fila+1][columna+1]==Agua)
				&&(this.pantallaJuego[fila][columna+1]==Agua) && (this.pantallaJuego[fila][columna-1]==Agua) && (this.pantallaJuego[fila+1][columna-1]==Agua)) 
		{
			System.out.println("Se esplotaron las minas en cadena ");
		}else 
			//Si tiene una mina en cualquiera de las 8 posiciones el alrededor se llama a explotar mina hasta que exploten todas
		{	
			this.pantallaJuego[fila][columna] = '@';
			
			if(this.pantallaJuego[fila+1][columna]==mina.minaChar) {
				disparo(fila+1, columna, jugador);
				explotarMinas(fila+1,columna,mina);
			}
			else 
			{
				disparo(fila+1, columna, jugador);
			}
			if(this.pantallaJuego[fila-1][columna]==mina.minaChar && limiteArribaMina(fila-1)) {
				disparo(fila-1, columna, jugador);
				explotarMinas(fila-1,columna,mina);
			}else
			{
				disparo(fila-1, columna, jugador);
			}
			if(this.pantallaJuego[fila-1][columna-1]==mina.minaChar && limiteArribaMina(fila-1) && limiteizquierdaMina(columna-1)) {
				disparo(fila-1, columna-1, jugador);
				explotarMinas(fila-1,columna-1,mina);
			}else 
			{
				disparo(fila-1, columna-1, jugador);
			}
			if(this.pantallaJuego[fila-1][columna+1]==mina.minaChar && limiteArribaMina(fila-1) && limiteDerechaMina(columna+1)) {
				disparo(fila-1, columna+1, jugador);
				explotarMinas(fila-1,columna+1,mina);
			}else 
			{
				disparo(fila-1, columna+1, jugador);
			}
			if(this.pantallaJuego[fila+1][columna+1]==mina.minaChar &&  limiteAbajoMina(fila+1) && limiteDerechaMina(columna+1)) {
				disparo(fila+1, columna+1, jugador);
				explotarMinas(fila+1,columna+1,mina);
			}else 
			{
				disparo(fila+1, columna+1, jugador);
				}
			if(this.pantallaJuego[fila][columna+1]==mina.minaChar && limiteDerechaMina(columna+1)) {
				disparo(fila, columna+1, jugador);
				explotarMinas(fila,columna+1,mina);
			}else 
			{
				disparo(fila, columna+1, jugador);
			}
			if(this.pantallaJuego[fila][columna-1]==mina.minaChar && limiteizquierdaMina(columna-1)) {
				disparo(fila, columna-1, jugador);
				explotarMinas(fila,columna-1,mina);
			}else 
			{
				disparo(fila, columna-1, jugador);
			}
			if(this.pantallaJuego[fila+1][columna-1]==mina.minaChar && limiteizquierdaMina(columna-1) && limiteAbajoMina(fila+1)) {
				disparo(fila+1, columna-1, jugador);
				explotarMinas(fila+1,columna-1,mina);
			}else 
			{
				disparo(fila+1, columna-1, jugador);
			}
		}
		
		
		//limpia la pantalla de juego 
		//si se saca este IF se puede ver el recorrido de la explosion de las minas
		//if(this.pantallaJuego[fila][columna] == '@') 
		//{
			//this.pantallaJuego[fila][columna] = '.';
		//}
		
	}
	
	
	public boolean limiteArribaMina(int fila) {
		boolean resultado;
		if (fila>=1) 
		{
			resultado =  true;
		}
		else 
		{
			resultado =  false;
		}
		return resultado;
	}
	
	public boolean limiteAbajoMina(int fila) {
		boolean resultado;
		if (fila<=this.pantallaJuego.length-1) 
		{
			resultado =  true;
		}
		else 
		{
			resultado =  false;
		}
		return resultado;
	}
	
	public boolean limiteizquierdaMina(int columna) {
		boolean resultado;
		if (columna>=1) 
		{
			resultado =  true;
		}
		else 
		{
			resultado =  false;
		}
		return resultado;
	}
	
	public boolean limiteDerechaMina(int columna) {
		boolean resultado;
		if (columna>=1) 
		{
			resultado =  true;
		}
		else 
		{
			resultado =  false;
		}
		return resultado;
	}
	//Get y set
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public int getCantidadFilas() 
	{
		return this.cantidadFilas;
	}
        
        public int getCantidadColumnas()
        {
        return this.cantidadColumnas;
        }
        
        public String getPantallaJuegoString(int i, int j)
        {
            return ""+this.pantallaJuego[i][j];
        }
        
        public char[][] getPantallaJuego()
        {
        	return this.pantallaJuego;
        }
        
        //esto son los numero que se van a usar para disparar
        public int cantidadFilas() 
        {
        	return this.pantallaJuego.length;
        }
        public int cantidadColumnas() 
        {
        	return this.pantallaJuego[0].length;
        			
        }
}
