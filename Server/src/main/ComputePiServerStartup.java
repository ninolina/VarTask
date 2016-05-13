
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
		
		int port = 1099;
		try {
			// create on port 1099
			Registry registry = LocateRegistry.createRegistry(port);
			// create a new service named ComputePi
			registry.rebind("ComputePi", new ComputePiImpl());
			System.out.println("ComputePiServer (re)bound on port: " + port); 
			
		} catch (Exception e) {
			System.err.println("ComputePiServer exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Server ist bereit!"); 
	}

}
