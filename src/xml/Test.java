package xml;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Test {
	
	public static void bienFormado(String archivo) {
		try {
			File archivoXML = new File(archivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document documentoXML = builder.parse(archivoXML);
			
			System.out.println("El archivo esta bien formado");
		}catch(Exception e) {
			System.err.println("Ha ocurido un error.");
		}
		
	}
	
	public static void esValido(String archivo) {
		try {
			File archivoXML = new File(archivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setValidating(true);
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document documentoXML = builder.parse(archivoXML);
			System.out.println("Archivo analizado.");
		}catch(Exception e) {
			System.err.println("Ha ocurido un error.");
		}
	}
	
	public static void datosDocumento(String archivo) {
		try {
			File archivoXML = new File(archivo);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setValidating(true);
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document documentoXML = builder.parse(archivoXML);
			
			documentoXML.getDocumentElement().normalize();
						
			System.out.println("Elemento raiz: " + documentoXML.getDocumentElement().getNodeName());
			
			NodeList nodos = documentoXML.getElementsByTagName("receta");

			System.out.println("Contenido: \n");
			
			for(int i = 0; i < nodos.getLength(); i++) {
				Node nodo = nodos.item(i);
				String elemento = nodo.getNodeName();
				System.out.println("Elemento actual: " + elemento);
				
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodo;
					
					System.out.println("Nombre: " + element.getElementsByTagName("nombre").item(0).getTextContent());
					Element tipo = (Element)(element.getElementsByTagName("tipo").item(0));
					System.out.println("Tipo: " + tipo.getAttribute("definicion"));
					
					Node ingred = element.getElementsByTagName("ingredientes").item(0);
					NodeList ingredientes = ingred.getChildNodes();
					
					int iC = 0;
					System.out.println("--------" + element.getElementsByTagName("ingredientes").item(0).getNodeName() + "--------");
					for(int j = 0; j < ingredientes.getLength(); j++) {
						Node aux = ingredientes.item(j);
						
						if(aux.getNodeType() == Node.ELEMENT_NODE) {
							Element ingredEle = (Element)(ingredientes);
							Element ing = (Element)(ingredEle.getElementsByTagName("ingrediente").item(iC));
							System.out.println(ing.getAttribute("nombre") + " " + ing.getAttribute("cantidad"));
							iC++;
						}						
					}
					
					System.out.println("---------------------------");
					
					System.out.println("Calorías: " + element.getElementsByTagName("calorias").item(0).getTextContent());
					
					Node pasosNod = element.getElementsByTagName("pasos").item(0);
					NodeList pasos = pasosNod.getChildNodes();
					System.out.println("--------" + element.getElementsByTagName("pasos").item(0).getNodeName() + "--------");
					iC = 0;
					for(int j = 0; j < pasos.getLength(); j++) {
						Node aux = pasos.item(j);
						
						if(aux.getNodeType() == Node.ELEMENT_NODE) {
							Element pasosEle = (Element)(pasos);
							Element pas = (Element)(pasosEle.getElementsByTagName("paso").item(iC));
							System.out.println("Paso " + pas.getAttribute("orden") + ": " + pas.getTextContent());
							iC++;
						}						
					}
					System.out.println("---------------------");
					System.out.println("Dificultad: " + element.getElementsByTagName("dificultad").item(0).getTextContent());
					System.out.println("Tiempo: " + element.getElementsByTagName("tiempo").item(0).getTextContent());
					System.out.println("Elaboración: " + element.getElementsByTagName("elaboracion").item(0).getTextContent());
				}
				System.out.println();
			}
			
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.- Verificar si esta bien formado");
		System.out.println("2.- Verificar si es válido");
		System.out.println("3.- Mostrar datos del archivo");
		int opcion = sc.nextInt();
		sc.close();
		
		if(opcion == 1) {
			Test.bienFormado("ejemplo.xml");
		}else if(opcion == 2) {
			Test.esValido("ejemplo.xml");
		}else {
			Test.datosDocumento("ejemplo.xml");
		}
		
	}

}