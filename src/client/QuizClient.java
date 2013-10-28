package client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;

import common.QuizQuestionInterface;
import common.QuizServerInterface;

public class QuizClient {
	public static void main(String[] args) throws RemoteException {
		//Generate server url string from console input
		final String host = String.format("rmi://%s:%d/", args[0], Registry.REGISTRY_PORT);
		final Scanner s = new Scanner(System.in);
		boolean b;
		boolean newQuestion = true;
		try {
			//Get the server instance
			QuizServerInterface server = (QuizServerInterface) Naming
					.lookup(host + "QuizServer");
			while (newQuestion) {
				QuizQuestionInterface question = server.getQuestion();
				b = true;
				while (b) {
					System.out.println(question.getQuestion());
					String answer = s.next();
					if (question.checkAnswer(answer)) {
						System.out.println("Correct!");
						b = false;
						System.out.println("More Questions? y/n");
						String in = s.next();
						newQuestion = in.equals("y");
					} else {
						System.out.println("Wrong! Try again? y/n");
						String again = s.next();
						b = again.equals("y");
						if(!b){
							System.out.println("Different Question? y/n");
							String in = s.next();
							newQuestion = in.equals("y");
						}
					}
				}
			}
		} catch (MalformedURLException | RemoteException | NotBoundException
				| ServerNotActiveException e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
}
