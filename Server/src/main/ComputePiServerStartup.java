
package main;

import java.rmi.registry.Registry;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import rmi.ComputePiImpl;

public class ComputePiServerStartup {

	public static void main(String[] args) {
		
		/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
		}*/
		String name = "//" + args[0] + "/ComputePi";
		
		try {
			try { 
				// create registry
				LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			} catch (Exception e) {
				System.out.println("Registry ist nicht erstellt!");
			}
			
			// create Server
			//ComputePiImpl computePiServer = new ComputePiImpl();
			Naming.rebind(name, new ComputePiImpl());
			System.out.println("ComputePiServer ist bereit!"); 
			
		} catch (Exception e) {
			System.err.println("ComputePiServer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
