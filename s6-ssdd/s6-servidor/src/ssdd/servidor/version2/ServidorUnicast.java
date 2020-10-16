package ssdd.servidor.version2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ssdd.comun.IServidor;
import ssdd.comun.Mensaje;

public class ServidorUnicast extends UnicastRemoteObject 
					  implements IServidor  {

	protected ServidorUnicast() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private Map<Integer, Mensaje> mensajes = new HashMap<Integer, Mensaje>();

	public void escribir(Mensaje mensaje) throws RemoteException {
		
		if (mensajes == null) {
			mensajes = new HashMap<Integer, Mensaje>();			
		}
		
		mensajes.put(mensajes.size(), mensaje);
		
		System.out.println(mensaje.getEmisor() + " envia un mensaje: " + 
							mensaje.getTexto());
	}

	public List<Mensaje> leer(int indice) throws RemoteException {
		
		if (mensajes == null || mensajes.size()==0) 
			return null;
		
		if (indice>mensajes.size())
			throw new RuntimeException("Indice de mensajes incorrecto (" + indice + ")");
		
		List<Mensaje> pendientes = new ArrayList<Mensaje>();	
		
		for (int i=indice; i<mensajes.size(); i++) {
			pendientes.add(mensajes.get(i));
		}
			
		return pendientes;
	}
}
