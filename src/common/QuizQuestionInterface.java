package common;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizQuestionInterface extends Remote{
	String getQuestion() throws RemoteException;
	boolean checkAnswer(String s) throws RemoteException;
}
