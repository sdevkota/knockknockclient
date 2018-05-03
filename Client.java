
/*
Java Client to connect to Knock Knock Server
*/
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static String textFromUser;
	private static String serverResponse;
	private static Boolean exit = true;
	
	public static void main(String[] args) {
		//final varaiables
		final String host = "ec2-34-218-41-170.us-west-2.compute.amazonaws.com";
		final int portNumber = 8989;
		try {
			Socket socket = new Socket(host, portNumber);
			BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//Read from server
			
			System.out.println("Server Response: " + in.readLine());
			while (exit == true) {
				//read user's input
				textFromUser = user.readLine();
				//write the text to server
				out.writeBytes(textFromUser + "\n");
				if(textFromUser.equalsIgnoreCase("exit")){
					exit = false;
				}
				else{
					serverResponse = in.readLine();
					System.out.println("Server : " + serverResponse);
				}
				
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
