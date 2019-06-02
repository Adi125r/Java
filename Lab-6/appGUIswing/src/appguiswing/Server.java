package appguiswing;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class Server extends Thread{

	private BufferedReader reader;
	private Socket clientSocket;
	ArrayList<SOAPMessage> inbox;
        private NodeApp node;
	
	public Server(Socket clientSocket, ArrayList<SOAPMessage> inbox, NodeApp nodeApp)
	{
                this.node=nodeApp;
		this.clientSocket = clientSocket;
		this.inbox = inbox;
		try {
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	
	public void run() {
		String inputLine="";
		while(true) {
			try {
				while((inputLine = reader.readLine()) != "\n"){
				InputStream is = new ByteArrayInputStream(inputLine.getBytes());
				SOAPMessage soap = MessageFactory.newInstance().createMessage(null, is);
				node.addToInbox(soap);
				}
			} catch (IOException | SOAPException e) {
				e.printStackTrace();
			} 
			
		}
		
	}
        
}
