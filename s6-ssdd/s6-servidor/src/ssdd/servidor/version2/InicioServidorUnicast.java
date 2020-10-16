package ssdd.servidor.version2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import ssdd.comun.Codebase;
import ssdd.comun.IServidor;
import ssdd.comun.PoliticaSeguridad;

public class InicioServidorUnicast {
	public static void main(String[] args) throws Exception {		
		
		// Gestor de seguridad - no es necesario en este ejemplo, 
		// pero lo incluiremos con carater didáctico
		System.setProperty("java.security.policy", 
				PoliticaSeguridad.getLocationOfPolicyFile());
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }	
		// java.rmi.server.codebase - no es necesario en este ejemplo,
        // pero lo incluiremos con carater didáctico
		Codebase.setCodeBase(IServidor.class);
		//Arrancamos el enlazador en el servidor. También podriamos hacerlo desde consola:
		// path_bin_proyecto_comun/rmiregistry 8888
		LocateRegistry.createRegistry(8888);		
		
		ServidorUnicast servidor = new ServidorUnicast();				

		Registry registry = LocateRegistry.getRegistry(8888);
		registry.rebind("chat", servidor);
		
		System.out.println("Chat Preparado, presione enter para terminar");
		System.in.read();
		registry.unbind("chat");
		UnicastRemoteObject.unexportObject(servidor, true);
		System.out.println("Chat cerrado");
	}
}
