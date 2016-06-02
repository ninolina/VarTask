package main;
import java.rmi.Naming;
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
			
			Registry myRegistry = LocateRegistry.getRegistry("localhost");
			ComputePiRemote computePiRemote = (ComputePiRemote) myRegistry.lookup("ComputePi");
			// Number of Servers
			int server = Integer.parseInt(args[0]);
			int tropfenZahl= Integer.parseInt(args[server+1]);
			int tropfenViertelkreis = 0;
			
			for(int i = 0 ; i < server; i++)
            {
                //Calculate tropfenZahl pro Server
                int gesamtZahlServer = tropfenZahl / server;
                
                //Calculate tropfenViertelkreis
                tropfenViertelkreis = computePiRemote.computePi(gesamtZahlServer) + tropfenViertelkreis;

                System.out.println("Server " + (i + 1) + " Anzahl Treffer: "  + tropfenViertelkreis);

                if (i == (server -1) ){
                    BigDecimal pi = new BigDecimal(4*(double) tropfenViertelkreis/tropfenZahl);
                    System.out.println("Tropfen: "+tropfenZahl+", davon Tropfen im Viertelkreis: "+tropfenViertelkreis+"."+"\nDie Naeherung fuer Pi: "+pi);
                    break;
                }

            }
		} catch (Exception e) {
			System.err.println("ComputePiClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}