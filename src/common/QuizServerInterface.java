package common;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface QuizServerInterface extends Remote{
	QuizQuestionInterface getQuestion() throws RemoteException, MalformedURLException, ServerNotActiveException;
}
