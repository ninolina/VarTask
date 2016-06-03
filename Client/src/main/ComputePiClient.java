package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.*;
import rmi.ComputePiRemote;

public class ComputePiClient {

	public static void main(String[] args) {
		
		 if (System.getSecurityManager() == null) {
		 System.setSecurityManager(new SecurityManager()); }
		 

		try {
			// Number of Servers (First Argument)
			int server = Integer.parseInt(args[0]);
			// Number of "tropfenZahl" (First Argument after Servers IP)
			int tropfenZahl = Integer.parseInt(args[server + 1]);
			int tropfenViertelkreis = 0;
			int gesamtTreffer = 0;

			for (int i = 0; i < server; i++) {
				String name = "//" + args[1 + i] + "/ComputePi";
				Registry myRegistry = LocateRegistry.getRegistry();
				ComputePiRemote computePiRemote = (ComputePiRemote) myRegistry.lookup(name);

				// Calculate hints per Server
				tropfenViertelkreis = computePiRemote.computePi(tropfenZahl);
				// Calculate hints all Server
				gesamtTreffer = gesamtTreffer + tropfenViertelkreis;

				System.out.println("Server " + (i + 1) + " hat " + tropfenViertelkreis + " Treffer.");
			}

			BigDecimal pi = new BigDecimal(4 * (double) gesamtTreffer / (tropfenZahl * server));
			System.out.println("Tropfenanzahl pro Server: " + tropfenZahl + "\nTropfen im Viertelkreis aller Server: "
					+ gesamtTreffer + "." + "\nDie Naeherung fuer Pi: " + pi);

		} catch (Exception e) {
			System.err.println("ComputePiClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}