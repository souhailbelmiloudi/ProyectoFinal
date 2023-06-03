package ventanas;

import java.util.ArrayList;
import java.util.Random;

/**
 * La clase Participantes representa una lista de participantes y proporciona metodos para agregar participantes y seleccionar uno al azar.
 */
public class Participantes {
	
	/** El nombre del participante */
	String nombre;
	
	/** Instancia de la clase Sign_in para acceder al nombre del participante conectado */
	Sign_in participate_conectado = new Sign_in();

	/** Lista para almacenar los nombres de los participantes */
	static ArrayList<String> nombres = new ArrayList<>();

	/** Instancia de la clase Random para generar numeros aleatorios */
	Random rand = new Random();

	/**
	 * Crea una nueva instancia de la clase Participantes.
	 * Si hay un participante conectado (nombre no es nulo), se agrega a la lista de nombres.
	 */
	public Participantes() {
		super();
		if (Sign_in.nombre != null) {
			nombres.add(Sign_in.nombre);
		}
	}

	/**
	 * Obtiene el nombre del participante.
	 * @return el nombre del participante
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del participante.
	 * @param nombre el nombre del participante a establecer
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la lista de nombres de los participantes.
	 * @return la lista de nombres de los participantes
	 */
	public ArrayList<String> getNombres() {
		return nombres;
	}

	/**
	 * Establece la lista de nombres de los participantes.
	 * @param nombres la lista de nombres de los participantes a establecer
	 */
	public void setNombres(ArrayList<String> nombres) {
		Participantes.nombres = nombres;
	}

	/**
	 * Anade un participante a la lista de nombres.
	 * @param nombre el nombre del participante a anadir
	 */
	public void addParticipantes(String nombre) {
		try {
			nombres.add(nombre);
			System.out.println(nombre + " añadido");
		} catch (Exception e) {
			System.out.println("Error al añadir participante");
		}
	}

	/**
	 * Selecciona aleatoriamente un participante de la lista de nombres.
	 * @return el nombre del participante seleccionado aleatoriamente
	 */
	public String elegirPartisipante() {
		String nombreElegido = nombres.get(rand.nextInt(nombres.size()));
		return nombreElegido; 
	}
}
