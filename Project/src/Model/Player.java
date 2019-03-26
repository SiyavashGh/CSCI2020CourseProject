package Model;

import EnumConstants.SessionVariable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Player {
	
	private String name;
	private int playerID;
	private boolean myTurn;

	private int PlayerID;
	private Socket socket;
	private DataInputStream fromPlayer;
	private DataOutputStream toPlayer;
	
	public Player(String name){
		this.name = name;
		
		setMyTurn(false);
	}

	public String getName(){
		return this.name;
	}
	
	public int getPlayerID() {
		return playerID;
	}


	public void setPlayerID(int playerID) {
		this.playerID = playerID;
		SessionVariable.myID.setValue(playerID);
	}


	public boolean isMyTurn() {
		return myTurn;
	}


	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public Player(int ID, Socket s){
		this.PlayerID = ID;
		this.socket = s;

		try{
			fromPlayer = new DataInputStream(socket.getInputStream());
			toPlayer = new DataOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			//trying to use a auto catch block, must google how its used properly
			//e.printStackTrace();
		}
	}

	public int sendData(int data){
		try {
			this.toPlayer.writeInt(data);
			return 1; //Successfull
		} catch (IOException e) {
			System.out.println("sending: Player not found");
			//e.printStackTrace();
			return 99;	//failure
		}
	}

	public int receiveData(){
		int data = 0;;
		try{
			data = this.fromPlayer.readInt();
			return data;
		}catch (IOException e) {
			System.out.println("Waiting: No respond from Player");
			return 99;
		}
	}

	public void closeConnection(){
		try {
			this.socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public boolean isOnline(){
		return socket.isConnected();
	}
	

	
	
}
