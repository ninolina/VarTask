package rmi;

import java.math.*;
import java.rmi.*;
import java.rmi.server.*;

@SuppressWarnings("serial")
public class ComputePiImpl extends UnicastRemoteObject implements ComputePiRemote {
	
	public ComputePiImpl() throws RemoteException {
		super();
	}
	

	@Override
	public int computePi(int gesamtZahl) throws RemoteException {
		// Tropfen im Viertelkreis
		int tropfenViertelkreis=0;
		// Koordinaten des Punktes P
		double x;
		double y;
				
		for(int i = 0; i < gesamtZahl; i++) {
			x = Math.random();
			y = Math.random();
					
			if(Math.hypot(x, y) < 1){
				tropfenViertelkreis=tropfenViertelkreis+1;
			}
		}
		//System.out.printf("Tropfen: " + gesamtZahl + ", davon Tropfen im Viertelkreis: " + tropfenViertelkreis);
		return tropfenViertelkreis;
	}
}
