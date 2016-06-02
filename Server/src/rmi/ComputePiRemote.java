package rmi;

import java.rmi.*;

public interface ComputePiRemote extends Remote {

	public int computePi(int tropfenZahl) throws RemoteException;
}
