package server;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;


import common.QuizQuestionInterface;
import common.QuizServerInterface;

public class QuizServer extends UnicastRemoteObject implements
		QuizServerInterface {
	public static final int ObjectPort = 1100;

	protected QuizServer() throws RemoteException {
		super(ObjectPort);
	}

	private static final long serialVersionUID = 1489969220591692546L;

	@Override
	public QuizQuestionInterface getQuestion() throws RemoteException,
			ServerNotActiveException {
		return new QuizQuestion();
	}

	public static void main(String[] args) throws RemoteException,
			MalformedURLException {
		//create an rmi registry on port 1099
		LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		//set the hostname right, especially important if you want to use an external adress, e.g. dyndns
		System.getProperties().put("java.rmi.server.hostname",
				"127.0.0.1");
		QuizServerInterface server = new QuizServer();
		
		// If you want to avoid extending UnicastRemoteObject use this:
		// QuizServerInterface server = (QuizServerInterface)
		// UnicastRemoteObject.exportObject(new QuizServer(),1100);
		// Naming.rebind("QuizServer", server);
		
		Naming.rebind("QuizServer", server);
	}

}
