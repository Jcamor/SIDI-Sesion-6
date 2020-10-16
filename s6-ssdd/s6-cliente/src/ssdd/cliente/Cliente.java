package ssdd.cliente;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import ssdd.comun.IServidor;
import ssdd.comun.Mensaje;
import ssdd.comun.PoliticaSeguridad;

public class Cliente {

	private static IServidor servidor;
	private static int indice;
	private static String nombre;
	private static Console console = System.console();
	private static BufferedReader reader = new BufferedReader(
											new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		// Gestor de seguridad
		System.setProperty("java.security.policy", PoliticaSeguridad.getLocationOfPolicyFile());
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }	
		
		Registry registry = LocateRegistry.getRegistry(8888);
		servidor = (IServidor)registry.lookup("chat");
		indice = 0;
		System.out.println("Introduzca su nombre:");
		nombre = leerConcola();		
		String opt = "";			
		do {
		    System.out.println("Elija la operaci—n:");
		    System.out.println("1 - Escribir");
		    System.out.println("2 - Leer");
		    System.out.println("3 - Salir");
		    opt = leerConcola();
			switch (opt) {
				case "1": enviar(); break;
				case "2": recibir();  break;					
			}
		}
		while (!opt.equals("3"));					
	}
	private static void recibir() throws RemoteException {
		System.out.println("-- Mensajes recibidos --");
		List<Mensaje> mensajes = servidor.leer(indice);		
		System.out.println("Recibidos " + mensajes.size() + " mensajes nuevos.");		
		for (Mensaje mensaje : mensajes)  
			System.out.println(mensaje.getEmisor() + " : " + mensaje.getTexto());
		indice += mensajes.size();
		System.out.println();		
	}
	
	private static void enviar() throws RemoteException {
		System.out.println("Introduzca el texto del mensaje:");
		String texto = leerConcola();
		Mensaje mensaje = new Mensaje();		
		mensaje.setEmisor(nombre);
		mensaje.setTexto(texto);	
		servidor.escribir(mensaje);
	}
	
	private static String leerConcola() {	
		if (console != null) return console.readLine();
		try {
			return reader.readLine();
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
