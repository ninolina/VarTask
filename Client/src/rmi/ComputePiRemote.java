package rmi;

import java.rmi.*;

public interface ComputePiRemote extends Remote {

	public int computePi(int gesamtZahl) throws RemoteException;
}
