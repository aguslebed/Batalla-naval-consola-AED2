package BatallaNaval;

public class Jugador {
	//atributos
	private String nick;
	private int puntaje; // los puntos que se al
	private Destructor destructor;
	private PortaAviones portaaviones;
	private Crucero crucero;
	private int hundidos;
	
	
	//Metodos
	public Jugador()  //constructor
	{
		this.setNick("pepe");
		this.setPuntaje(0);
		this.setHundidos(0);
		this.destructor = new Destructor();
		this.crucero = new Crucero();
		this.portaaviones = new PortaAviones();	
		
	
	}
	
	public boolean perdio() 
	{
		if (this.getHundidos() == 3) 
		{
			return true;
		}else 
		{
			return false;
		}
	}

	
	public void sumarPuntaje(int puntos) {
		this.puntaje +=puntos;
	}
	//Get y set
	public String getNick() {
		return nick;
	}

	
	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}


	public int getHundidos() {
		return hundidos;
	}
	public void sumarHundidos() 
	{
		this.hundidos +=1;
	}
	public void setHundidos(int hundidos) {
		this.hundidos = hundidos;
	}

	public Destructor getDestructor() {
		return destructor;
	}

	public void setDestructor(Destructor destructor) {
		this.destructor = destructor;
	}

	public PortaAviones getPortaaviones() {
		return portaaviones;
	}

	public void setPortaaviones(PortaAviones portaaviones) {
		this.portaaviones = portaaviones;
	}

	public Crucero getCrucero() {
		return crucero;
	}

	public void setCrucero(Crucero crucero) {
		this.crucero = crucero;
	}
	
	public String getHundidosString()
        {
            return "Barcos hundidos: "+this.hundidos;
        }
	
}
