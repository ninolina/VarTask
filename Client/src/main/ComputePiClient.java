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
		
		// Number of Servers
		int server = Integer.parseInt(args[0]);
		String name = "//" + args[server] + "/ComputePi";
		
		try {	
			/* Create Registry.
			LocateRegistry.getRegistry() sends invocations to the registry on server's local host on the default registry port of 1099*/
			Registry myRegistry = LocateRegistry.getRegistry();
			ComputePiRemote computePiRemote = (ComputePiRemote) myRegistry.lookup(name);
			
			int tropfenZahl= Integer.parseInt(args[server+1]);
			int tropfenViertelkreis = 0;
			
			for(int i = 0 ; i < server; i++)
            { 
                //Calculate tropfenViertelkreis
                tropfenViertelkreis = computePiRemote.computePi(tropfenZahl);

                System.out.println("Server " + (i + 1) + " hat "  + tropfenViertelkreis + " Treffer.");
            }
              
            BigDecimal pi = new BigDecimal(4*(double) tropfenViertelkreis/tropfenZahl);
            System.out.println("Tropfenanzahl: "+tropfenZahl+", Tropfen im Viertelkreis aller Server: "+tropfenViertelkreis+"."+"\nDie Naeherung fuer Pi: "+pi);
            
		} catch (Exception e) {
			System.err.println("ComputePiClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}