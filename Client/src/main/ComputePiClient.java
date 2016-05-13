package main;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.*;
import rmi.ComputePiRemote;

public class ComputePiClient {

	public static void main(String[] args) {
		/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
		}*/
		
		try {
			Registry myRegistry = LocateRegistry.getRegistry("localhost", 1099);
			ComputePiRemote computePiRemote = (ComputePiRemote) myRegistry.lookup("ComputePi");
			
			int gesamtZahl=11000;
			int tropfenViertelkreis = computePiRemote.computePi(gesamtZahl);
			BigDecimal pi = new BigDecimal(4 * (double) tropfenViertelkreis/gesamtZahl);
			System.out.println("Tropfen: "+gesamtZahl+", davon Tropfen im Viertelkreis: "+tropfenViertelkreis+"."+"\nDie Naeherung fuer Pi: "+pi); 
		} catch (Exception e) {
			System.err.println("ComputePiClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}