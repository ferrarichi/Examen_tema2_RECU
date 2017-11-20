import java.util.ArrayList;


public class Taller {
	
	private String nombre;
	private ArrayList<Direccion> direcciones;
	private ArrayList<Coche> coches;

	
	
	
	public Taller(String nombre, ArrayList<Direccion> direcciones, ArrayList<Coche> coches) {
		super();
		this.nombre = nombre;
		this.direcciones = direcciones;
		this.coches = coches;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(ArrayList<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public ArrayList<Coche> getCoches() {
		return coches;
	}

	public void setCoches(ArrayList<Coche> coches) {
		this.coches = coches;
	}

	
	public void print(){
		String datos = "";
		datos += ("Nombre Taller: " + this.nombre + "\n");
		for (int i=0; i<direcciones.size(); i++) {
			datos += ("Direccion nº" + (i+1) + ": \n");
			datos += ("	Nombre: " + direcciones.get(i).getNombre() + "\n");
			datos += ("	Poblacion: " + direcciones.get(i).getPoblacion() + "\n");
		}
		datos += ("Nombre Taller: " + this.nombre + "\n");
		for (int i=0; i<coches.size(); i++) {
			datos += ("Coches nº" + (i+1) + ": \n");
			datos += ("	Marca: " + coches.get(i).getMarca() + "\n");
			datos += ("	Modelo: " + coches.get(i).getModelo() + "\n");
		}
		System.out.println(datos);
	}

}
