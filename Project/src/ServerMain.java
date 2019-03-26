//this class is where the server is launched
import javax.swing.JFrame;


public class ServerMain {

	public static void main(String[] args) {
		//Creates server
		ServerApp server = new ServerApp();
		server.setSize(450,250);
		server.setVisible(true);
		server.setTitle("Checkers Server 3.0");
		server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//start Connection
		server.startRunning();
	}
}
