
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Parser {

	private Document dom = null;
	private ArrayList<Taller> talleres = new ArrayList<Taller>();

	public Parser() {
		
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	
	public void parseDocument() {
			//Obtenemos elemento Raiz
		Element docEl = dom.getDocumentElement();
		
		//Obtenemos NodeList
		NodeList nl = docEl.getElementsByTagName("taller");
		if (nl != null && nl.getLength() > 0) {
			for (int i=0; i<nl.getLength(); i++) {
				
				//Obtenemos elemento
				Element el = (Element) nl.item(i);
				
				//Obtenemos un taller
				Taller acNEW = getTaller(el);
				
				//Añadimos al array
				talleres.add(acNEW);
			}
		}
	}
	
	//Obtenemos datos del libro
		private Taller getTaller(Element accionEle) {
			
			//Empezamos a obtener cada elemento
			String nombre = getNombre(accionEle, "nombre");
			ArrayList <Direccion> direcciones = getDireccion(accionEle);
			ArrayList <Coche> coches = getCoche(accionEle);
				
			//Generamos Accion que se ha encontrado
			Taller actual = new Taller(nombre, direcciones, coches);
			
			return actual;
		}
		
		//Obtiene el nombre del Taller
		private String getNombre(Element ele, String tagName) {
			String txtValue = null;

			NodeList nl = ele.getElementsByTagName(tagName);
			Element el = (Element) nl.item(0);
			
				txtValue = el.getFirstChild().getNodeValue();
			return txtValue;
		}
		
		//Obtener direccion
		private ArrayList<Direccion> getDireccion(Element ele){
			ArrayList <Direccion> direcciones = new ArrayList<Direccion>();
					
			//Inspecciona cada direccion que hay en direcciones
			NodeList nlA = ele.getElementsByTagName("direccion");
			
			for (int i=0; i<nlA.getLength(); i++) {
				Element el = (Element) nlA.item(i);
				
				//Obtenemos valores de cada direccion
				String nombre = getTextValue(el, "nombre");
				String poblacion = getTextValue(el, "poblacion");
						
				//Añadimos valores a un objeto
				direcciones.add(new Direccion(nombre, poblacion));					
			}				

			return direcciones;
		}
		
		//Obtener ventas
			private ArrayList<Coche> getCoche(Element ele){
				ArrayList <Coche> coches = new ArrayList<Coche>();
						
				//Inspecciona cada operacion que hay en Operaciones
				NodeList nlA = ele.getElementsByTagName("coche");
				
				for (int i=0; i<nlA.getLength(); i++) {
					Element el = (Element) nlA.item(i);
					
					//Obtenemos valores de cada operacion
					String marca = getTextValue(el, "marca");
					String modelo = getTextValue(el, "modelo");
							
					//Añadimos valores a un objeto
					coches.add(new Coche(marca, modelo));				
				}				

				return coches;
			}
		//Obtiene el valor del elemento a buscar
			private String getTextValue(Element ele, String tagName) {
				String txtValue = null;
				NodeList nl = ele.getElementsByTagName(tagName);
				if (nl != null && nl.getLength() > 0) {
					Element el = (Element) nl.item(0);
					txtValue = el.getFirstChild().getNodeValue();
				}
				return txtValue;
			}
			
	
	public void print() {

		Iterator it = talleres.iterator();
		while (it.hasNext()) {
			Taller t = (Taller) it.next();
			t.print();
			System.out.println("-----------------------------\n");
		}
		
		
	}	
		
	//Devuelbe array
		public ArrayList<Taller> getTalleres(){
			return this.talleres;
		}
		
}
