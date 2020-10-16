package ssdd.servidor.version1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import ssdd.comun.Codebase;
import ssdd.comun.IServidor;
import ssdd.comun.PoliticaSeguridad;

public class InicioServidor {
	public static void main(String[] args) throws Exception {		
		
		// Gestor de seguridad - no es necesario en este ejemplo, 
		// pero lo incluiremos con carater did�ctico
		System.setProperty("java.security.policy", 
				PoliticaSeguridad.getLocationOfPolicyFile());
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }	
		// java.rmi.server.codebase - no es necesario en este ejemplo,
        // pero lo incluiremos con carater did�ctico
		Codebase.setCodeBase(IServidor.class);
		//Arrancamos el enlazador en el servidor. Tambi�n podriamos hacerlo desde consola:
		// path_bin_proyecto_comun/rmiregistry 8888
		LocateRegistry.createRegistry(8888);		
		
		Servidor servidor = new Servidor();						//Podr�a ser otro puerto		
		IServidor remote = (IServidor)UnicastRemoteObject.exportObject(servidor, 8889);

		Registry registry = LocateRegistry.getRegistry(8888);
		registry.rebind("chat", remote);		
		
		System.out.println("Chat Preparado, presione enter para terminar");
		System.in.read();
		registry.unbind("chat");
		UnicastRemoteObject.unexportObject(servidor, true);
		System.out.println("Chat cerrado");
	}
}
