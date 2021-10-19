package my.hacha;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class UnirFicheros {
	
	public static void unirFicheros(File fileSelected, String ruta) {
		
		
		  try {
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		      DocumentBuilder db = dbf.newDocumentBuilder();
		      Document document = db.parse(fileSelected);
		      document.getDocumentElement().normalize();
		      
		      NodeList nList = document.getElementsByTagName("ProyectoTitan");
		      Node nNode = nList.item(0);
		      
		     
		    	  Element e = (Element) nNode;
		    	  String nombre = e.getElementsByTagName("Nombre").item(0).getTextContent();
		    	  int numSplits = Integer.parseInt(e.getElementsByTagName("Partes").item(0).getTextContent());
		    	  String extension = e.getElementsByTagName("Extension").item(0).getTextContent();
		    	  String directorio = e.getElementsByTagName("Directorio").item(0).getTextContent();
		    	  
		    	  File archivoNuevo = new File(directorio+nombre +"Unido"+ extension);
		    	  
		    	  try {
		    	  
		    		  archivoNuevo.createNewFile();
		    		  FileOutputStream fos = new FileOutputStream(archivoNuevo);
		    		  
		    		  for( int i = 1 ; i <= numSplits; i++) {
		    			  
		    			  File ficheroSeleccionado = new File(directorio+nombre+"Parte"+i);
		    			  FileInputStream fis = new FileInputStream(ficheroSeleccionado);
		    			  byte [] aux = new byte[fis.available()];
		    			  fis.read(aux);
		    			  fos.write(aux);
		    			  fis.close();
		    			  
		    		  } 
		    		  fos.close();
		    	  }catch (IOException io) {
		    		  System.out.println("Error al generar el archivo.");
		    		  
		    	  }	    	  
		      
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
		
		
	}

