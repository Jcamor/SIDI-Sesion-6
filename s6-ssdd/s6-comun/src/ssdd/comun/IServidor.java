package ssdd.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServidor extends Remote {
	
	void escribir(Mensaje mensaje) throws RemoteException;
	List<Mensaje> leer(int indice) throws RemoteException;

}
