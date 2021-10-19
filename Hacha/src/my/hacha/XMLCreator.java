package my.hacha;

import java.io.File;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLCreator {
	

	//Historial
	public static void XMLGenerator(File fileSelected, long size, int numSplits, String directorio) {
		
						
		
	try {
		File xml = new File("Historial.xml");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document doc;
	    Element rootElement;
	    
	    if(xml.exists() && !xml.isDirectory()) { 
	          doc = docBuilder.parse(xml);
	          rootElement = doc.getDocumentElement();
	      
	    }else {
	    	  doc = docBuilder.newDocument();
	    	//Elemento raíz
	    	  rootElement = doc.createElement("ProyectoTitan");
		      doc.appendChild(rootElement);
	      }  
			
	     
	        
	      Element elementoPadre = doc.createElement("Archivo");
	      rootElement.appendChild(elementoPadre);
	      
	      
	      //Primer elemento
	      Element elemento1 = doc.createElement("Nombre");
	      elemento1.setTextContent(fileSelected.getName().substring(0, fileSelected.getName().lastIndexOf(".")));
	      elementoPadre.appendChild(elemento1);
	      
	      //Segundo elemento
	      Element elemento2 = doc.createElement("Peso");
	      elemento2.setTextContent(Long.toString(size));
	      elementoPadre.appendChild(elemento2);
	      
	      //Tercero elemento
	      Element elemento3 = doc.createElement("Partes");
	      elemento3.setTextContent(Integer.toString(numSplits));
	      elementoPadre.appendChild(elemento3);
	      
	      //Cuarto elemento
	      Element elemento4 = doc.createElement("Directorio");
	      elemento4.setTextContent(directorio);
	      elementoPadre.appendChild(elemento4);
	      
	      //Quinto elemento
	      Element elemento5 = doc.createElement("Extension");
	      elemento5.setTextContent(fileSelected.getName().substring(fileSelected.getName().lastIndexOf(".")));
	      elementoPadre.appendChild(elemento5);
	      
	      
	      //Se escribe el contenido del XML en un archivo
	      TransformerFactory transformerFactory = TransformerFactory.newInstance();
	      Transformer transformer = transformerFactory.newTransformer();
	     
	         
    	  
    	  DOMSource source = new DOMSource(doc);
  	      StreamResult result = new StreamResult(new File("Historial.xml"));
  	      transformer.transform(source, result);
  	      
		}catch (TransformerException | ParserConfigurationException | SAXException | IOException e) {
		e.printStackTrace();
		}
	}
	
	
	//XML por fichero
	public static void XMLperfile(File fileSelected, long size, int numSplits, String directorio) {
					
		
	try {
		File xml = new File(FileManagement.arreglarRuta(fileSelected) + fileSelected.getName().substring(0, fileSelected.getName().lastIndexOf(".")) +".xml");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document doc;
	    Element rootElement;
	   
	       
	          
	      
	
	     doc = docBuilder.newDocument();
	     rootElement = doc.createElement("ProyectoTitan");
		 doc.appendChild(rootElement);
	      
			
	     
	        
	      Element elementoPadre = doc.createElement("Archivo");
	      rootElement.appendChild(elementoPadre);
	      
	      
	      //Primer elemento
	      Element elemento1 = doc.createElement("Nombre");
	      elemento1.setTextContent(fileSelected.getName().substring(0, fileSelected.getName().lastIndexOf(".")));
	      elementoPadre.appendChild(elemento1);
	      
	      //Segundo elemento
	      Element elemento2 = doc.createElement("KB");
	      elemento2.setTextContent(Long.toString(size));
	      elementoPadre.appendChild(elemento2);
	      
	      //Tercero elemento
	      Element elemento3 = doc.createElement("Partes");
	      elemento3.setTextContent(Integer.toString(numSplits));
	      elementoPadre.appendChild(elemento3);
	      
	      //Cuarto elemento
	      Element elemento4 = doc.createElement("Directorio");
	      elemento4.setTextContent(directorio);
	      elementoPadre.appendChild(elemento4);
	      
	      //Quinto elemento
	      Element elemento5 = doc.createElement("Extension");
	      elemento5.setTextContent(fileSelected.getName().substring(fileSelected.getName().lastIndexOf(".")));
	      elementoPadre.appendChild(elemento5);
	      
	      
	      //Se escribe el contenido del XML en un archivo
	      TransformerFactory transformerFactory = TransformerFactory.newInstance();
	      Transformer transformer = transformerFactory.newTransformer();
	     
	         
    	  
    	  DOMSource source = new DOMSource(doc);
  	      StreamResult result = new StreamResult(new File(FileManagement.arreglarRuta(fileSelected) +fileSelected.getName().substring(0, fileSelected.getName().lastIndexOf(".")) +".xml"));
  	      transformer.transform(source, result);
  	      
		}catch (TransformerException | ParserConfigurationException e) {
		e.printStackTrace();
		}
	}
}
