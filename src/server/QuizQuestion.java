package server;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;


import common.QuizQuestionInterface;



public class QuizQuestion extends UnicastRemoteObject implements QuizQuestionInterface{

	private static final long serialVersionUID = 5109394968328048990L;
	
	final private String question;
	final private String answer;
	
	
	public QuizQuestion() throws RemoteException, ServerNotActiveException {
		super(QuizServer.ObjectPort);
		final Random rnd = new Random();
		final int rand1 = rnd.nextInt(100);
		final int rand2  = rnd.nextInt(100);
		question = String.format("Calculate: %d + %d", rand1, rand2);
		answer = Integer.toString(rand1+rand2);
		System.out.println(question+" = "+answer);
		System.out.println(UnicastRemoteObject.getClientHost().toString());
	}

	@Override
	public String getQuestion() throws RemoteException {
		return question;
	}

	@Override
	public boolean checkAnswer(String s) throws RemoteException {
		System.out.println(s);
		return s.equals(answer);
	}


}
