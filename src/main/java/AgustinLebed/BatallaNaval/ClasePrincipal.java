package AgustinLebed.BatallaNaval;

import java.util.Random;
import java.util.Scanner;


public class ClasePrincipal {
	
	
	public static void main(String[] agrs) 
	{
		// Declaracion de las 2 pantallas de juego
		Escenario escenario1 = new Escenario();
		Escenario escenario2 = new Escenario();
		
		boolean jugando = true;
		int turno = 1, i,fila=0,columna=0,cont=0,cantMinas,maxMinas;
		
		String nombre,columnaSTR,filaSTR = new String();
		Scanner leer = new Scanner(System.in);
		Mina mina = new Mina();
		
		
		Jugador jugador1 = escenario1.getJugador();
        Jugador jugador2 = escenario2.getJugador();
                
     
        
        //Barcos del jugador 1       
		Barco crucero = escenario1.getJugador().getCrucero();
		Barco destructor = escenario1.getJugador().getDestructor();
		Barco portaAviones =  escenario1.getJugador().getPortaaviones();
		//Barcos del jugador 2
		Barco destructor2 = escenario2.getJugador().getDestructor();
		Barco crucero2 = escenario2.getJugador().getCrucero();
		Barco portaAviones2 = escenario2.getJugador().getPortaaviones();
		
	      
        nombre = "a";        
                
       destructor.setNombre("destructor");
       crucero.setNombre("crucero");
       portaAviones.setNombre("PortaAviones");
                
                
       destructor2.setNombre("destructor");
       crucero2.setNombre("crucero");
       portaAviones2.setNombre("PortaAviones");
                
       	//Elige de manera aleatoria si el barco se coloca de manera horizontal o vertical
       	horizontalOvertical(destructor);
       	horizontalOvertical(crucero);
       	horizontalOvertical(portaAviones);
		horizontalOvertical(destructor2);
		horizontalOvertical(crucero2);
		horizontalOvertical(portaAviones2);
		
		//se ponen los barcos en la matriz
		dibujarBarco(escenario1,destructor);
		dibujarBarco(escenario1,portaAviones);
		dibujarBarco(escenario1,crucero);
		
		dibujarBarco(escenario2,destructor2);
		dibujarBarco(escenario2,portaAviones2);
		dibujarBarco(escenario2,crucero2);
		
		
		
		
		cantMinas = -1;//Valor nulo para las minas
		maxMinas = ((escenario1.cantidadColumnas()-2) * (escenario1.cantidadFilas()-2))-destructor.getTamanio()-portaAviones.getTamanio()-crucero.getTamanio();
		//se colocan la misma cantidad de minas en el escenario, pero en distintos lugares
		while(cantMinas <=0 || cantMinas>=maxMinas) {
		System.out.println("¿Cuantas minas queres colocar en el escenario? ("+(maxMinas-1)+" o menos)");
		cantMinas = leer.nextInt();
		}
		escenario1.colocarMinas(1, 1, cantMinas, cont=0);
		escenario2.colocarMinas(1, 1, cantMinas, cont=0);
		
		
		
		
		//se cargan los jugadores
		System.out.println("Jugador nro 1 (Poner los nombres sin espacios) ");
		System.out.println("Nick del jugador:  ");
		nombre = (String)(leer.next());
		jugador1.setNick(nombre);
		System.out.println("Nombre del destructor: ");
		nombre = (String)(leer.next());
		jugador1.getDestructor().setNombre(nombre);
		System.out.println("Nombre del portaaviones: ");
		nombre = (String)(leer.next());
		jugador1.getPortaaviones().setNombre(nombre);
		System.out.println("Nombre del crucero: ");
		nombre = (String)(leer.next());
		jugador1.getCrucero().setNombre(nombre);
		
	
		System.out.println("Jugador nro 2 (Poner los nombres sin espacios) ");
		System.out.println("Nick del jugador:  ");
		nombre = leer.next();
		jugador2.setNick(nombre);
		System.out.println("Nombre del destructor: ");
		nombre = leer.next();
		jugador2.getDestructor().setNombre(nombre);
		System.out.println("Nombre del portaaviones: ");
		nombre = leer.next();
		jugador2.getPortaaviones().setNombre(nombre);
		System.out.println("Nombre del crucero: ");
		nombre = leer.next();
		jugador2.getCrucero().setNombre(nombre);
		
		
		
		
		
		
		//Bucle principal
		while(jugando==true) {
		
			System.out.println(escenario1.imprimirLinea(1000)+"                                             "+escenario2.imprimirLinea(1000));
		i=0; 
		while(i<=escenario1.getCantidadFilas()-1)
		{
			System.out.println(escenario1.imprimirLinea(i)+"                                                                         "+escenario2.imprimirLinea(i));
			i++;
		}
		System.out.println(escenario1.imprimirLinea(2000)+"                 "+escenario2.imprimirLinea(2000));
		System.out.println(escenario1.imprimirLinea(3000)+"                "+escenario2.imprimirLinea(3000));
		System.out.println(escenario1.imprimirLinea(4000)+"                     "+escenario2.imprimirLinea(4000));
		
	
		System.out.println("");
		if (turno==1) 
		{
			System.out.println("Turno del jugado nro "+turno+": "+jugador1.getNick());
		}else if (turno==2) 
		{
			System.out.println("Turno del jugado nro "+turno+": "+jugador2.getNick());
		}
		
	
	
		do {
			System.out.println("Ingresar la fila del disparo: ");
			filaSTR = leer.next();}	
			while(fila<=0 && fila>=11 && validacionNumero(filaSTR)==false);
			fila = Integer.parseInt(filaSTR);
			
		do {
			System.out.println("Ingresar la columna del disparo: ");
			columnaSTR = leer.next();
			}while(columna <=0 && columna >=24 && validacionNumero(columnaSTR));
			columna = Integer.parseInt(columnaSTR);
	
		
	
		//Los puntos de un jugador se suman en el jugador contrario
		if(turno == 1) 
		{	
			if(escenario2.getPantallaJuego()[fila][columna]==Mina.minaChar) 
			{
				escenario2.explotarMinas(fila, columna,mina);
			}else 
			{
				escenario2.disparo(fila, columna,jugador1);
			}
		
			turno = cambiarTurno(1);
			
		}else if (turno==2)
		{
		
			if(escenario1.getPantallaJuego()[fila][columna]==Mina.minaChar) 
			{
				escenario1.explotarMinas(fila, columna,mina);
			}else 
			{
				
				escenario1.disparo(fila, columna,jugador2);
			}
	
			turno = cambiarTurno(2);
			
		}
		
		
		//se verficia si algun jugador tiene los 3 barcos hundidos. Si da verdadero termina el juego y gana el jugador que todavia tiene barcos
		if(jugador1.getHundidos()==3 || jugador2.getHundidos()==3) 
		{
			jugando=false;	
		}
		
		
		}//buecle principal
		 
		
	
		//se muestran como quedaron las pantallas de juego cuando se termino la partida
		i=0; 
		while(i<=escenario1.getCantidadFilas()-1)
		{
			System.out.println(escenario1.imprimirLinea(i)+"                         "+escenario2.imprimirLinea(i));
			i++;
		}
		
		
		//Se da la informacion del jugador ganador
		if(jugador1.getHundidos()==3) 
		{
			System.out.println("El jugador ganador fue "+jugador2.getNick()+" y sumo "+jugador1.getPuntaje()+" puntos.");
		
	
		}else if(jugador2.getHundidos()==3) 
		{
			System.out.println("El jugador ganador fue "+jugador1.getNick()+" y sumo "+jugador2.getPuntaje()+" puntos.");
		}
			
		
}// Metodo main
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int cambiarTurno(int turno) // cambia el valor de turno entre 1 y 2;
	{
		if (turno == 1) 
		{
			turno = 2;
			
		}else if(turno==2)
		{
			turno = 1;
		}
		
		return turno;
	}
	
	//elige aleatoriamente si el barco va horizontal o vertical
	public static void horizontalOvertical(Barco barco) 
	{
		Random random = new Random();
		barco.setVertical(random.nextBoolean());
	}
	



public static void dibujarBarco(Escenario escenario,Barco barco) 
{
  int i=0;
  
  escenario.colocarBarco(barco);
  try {
  if (barco.getVertical()) {
	  for(i=barco.getFila(); i<(barco.getFila()+barco.getTamanio()); i++)
		  escenario.getPantallaJuego()[i][barco.getColumna()] = escenario.Bloque;
  }else {
	  for(i=barco.getColumna(); i<(barco.getColumna()+barco.getTamanio()); i++)
		  escenario.getPantallaJuego()[barco.getFila()][i] = escenario.Bloque;
  }
  }catch (Exception e) {
	System.out.println(e+" I="+i);
}//Try-catch
}

public boolean verificarSiPerdio(Escenario escenario) 
{ 
	int i,j,cont=0;
	boolean resultado;
	for(i=0; i<escenario.getCantidadFilas()-1; i++) 
	{
		for(j=0; j<escenario.getCantidadColumnas()-1; j++) 
		{
			if (escenario.getPantallaJuego()[i][j]==escenario.Tocado) 
			{
				cont++;
			}
		}
	}
	
	if(cont==escenario.getJugador().getDestructor().getTamanio() +
			escenario.getJugador().getPortaaviones().getTamanio() + 
			escenario.getJugador().getCrucero().getTamanio()) 
	{
		resultado = true;
	}else 
	{
		resultado = false;
	}
	return ( resultado );
	}

//Si la cadena tiene solo numero devulve verdadero, si no devuelve falso
public static boolean validacionNumero(String cadena) 
{
    int num;
    boolean resultado;
    try 
    {
        num = Integer.parseInt(cadena);
        return  true;
    }
    catch (Exception e) {
        return  false;
    }
    
    
    //return (resultado);
}


}


