/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appguiswing;


import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.soap.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.xml.ws.*;

public class NodeApp extends JFrame{

	private JFrame frame;
	private JPanel recievedMSG;
	private JPanel sendMSG;
        private JPanel logsMSG;
	private JTextField nameField, myPort, tarPort;
	private JTextField toField, messageField;

	private static JTextArea writeMSG, logsArea;
	private JButton connectButton, sendButton;
	private JTextArea inboxField;
	private JLabel nameLabel, myPortLabel, tarPortLabel;
	private JLabel toLabel, messageLabel;
	private JScrollPane scroll, scrollLogs, scrollMsg;
        private JComboBox chooseClient;
	
	private MessageFactory msgFactory;
	private SOAPMessage soapMsg;
	private SOAPPart soapPart;
	private SOAPEnvelope soapEnvelope;
	private SOAPHeader soapHeader;
	private SOAPBody soapBody;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static Socket nextNodeSocket;
	private static Thread serverThread;
        private static NodeApp nodeApp;
	
	private static ArrayList<SOAPMessage> inbox;
	
	private static int portB;
	private static int portA;
        private static String name;
	
	public NodeApp(){
		super(name);
		setSize(580, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
                nodeApp=this;
		inbox = new ArrayList<>();
		
		try {
			msgFactory = MessageFactory.newInstance();
			soapMsg = msgFactory.createMessage();
			soapPart = soapMsg.getSOAPPart();
			soapEnvelope = soapPart.getEnvelope();
			soapHeader = soapEnvelope.getHeader();
			soapBody = soapEnvelope.getBody();
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}
               
		
		recievedMSG = new JPanel();
		recievedMSG.setBounds(298, 55, 259, 348);
		recievedMSG.setBorder(BorderFactory.createTitledBorder("Out"));
		getContentPane().add(recievedMSG);
		recievedMSG.setLayout(null);
		
		messageLabel = new JLabel("Message:");
		messageLabel.setBounds(12, 23, 56, 16);
		recievedMSG.add(messageLabel);
				
		inboxField = new JTextArea();
		//inboxField.setBounds(12, 52, 224, 242);
		inboxField.setLineWrap(true);
                inboxField.setEditable(false);
                scrollMsg = new JScrollPane(inboxField);
		scrollMsg.setBounds(12, 52, 224, 242);
		recievedMSG.add(scrollMsg);
			
		sendMSG = new JPanel();
		sendMSG.setBounds(12, 57, 260, 190);
		sendMSG.setBorder(BorderFactory.createTitledBorder("New message:"));
		sendMSG.setLayout(null);
		add(sendMSG);  
                
               
                chooseClient=new JComboBox(new String[]{"unicast","broadcast"});
                chooseClient.setBounds(12, 45, 120, 20);
                chooseClient.addActionListener(e -> {
			if(chooseClient.getSelectedIndex()==0)
                            toField.setEditable(true);
                        else{
                            toField.setEditable(false);
                            toField.setText("");
                        }
		});
                sendMSG.add(chooseClient);
                
                logsMSG = new JPanel();
		logsMSG.setBounds(12, 250, 260, 150);
		logsMSG.setBorder(BorderFactory.createTitledBorder("Logs:"));
		logsMSG.setLayout(null);
		add(logsMSG);  
                
                logsArea = new JTextArea();
                logsArea.setEditable(false);
                scrollLogs = new JScrollPane(logsArea);
		scrollLogs.setBounds(12, 20, 224, 120);
		//logsArea.setBounds(12, 20, 224, 120); 
		logsMSG.add(scrollLogs);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(12, 155, 97, 25);
		connectButton.addActionListener(e -> {
                    try{
                        nextNodeSocket = new Socket("localhost", portB);
                        System.out.println("Connected");
                        connectButton.setEnabled(false);
                    }catch(IOException e1)
                    {
                        e1.printStackTrace();
                    }
		});
		sendMSG.add(connectButton);
		
		writeMSG = new JTextArea();
		scroll = new JScrollPane(writeMSG);
		scroll.setBounds(12, 80, 224, 70);
		sendMSG.add(scroll);
		
		toLabel = new JLabel("To:");
		toLabel.setBounds(12, 24, 42, 16);
		sendMSG.add(toLabel);
		
		toField = new JTextField();
		toField.setBounds(52, 21, 184, 22);
		sendMSG.add(toField);
		
		
		sendButton = new JButton("Send");
		sendButton.setBounds(124, 155, 112, 25);
		sendButton.addActionListener(e -> {
			try {
                            
                                try {
                                    msgFactory = MessageFactory.newInstance();
                                    soapMsg = msgFactory.createMessage();
                                    soapPart = soapMsg.getSOAPPart();
                                    soapEnvelope = soapPart.getEnvelope();
                                    soapHeader = soapEnvelope.getHeader();
                                    soapBody = soapEnvelope.getBody();
                                 } catch (SOAPException e1) {
                                         e1.printStackTrace();
                                 }
                            
                                if(chooseClient.getSelectedIndex()==0)
                                    soapHeader.addTextNode(toField.getText());
                                else
                                    soapHeader.addTextNode("toAll");
                                
                                //System.out.println(chooseClient.getSelectedIndex());

				SOAPBodyElement element = soapBody.addBodyElement(soapEnvelope.createName("JAVA", "LAB", "6"));
				element.addChildElement("test").addTextNode("Message From " + name + ": ");
				
				element.addTextNode(writeMSG.getText());
				PrintStream out = new PrintStream(nextNodeSocket.getOutputStream(), true);
                                
				soapMsg.writeTo(out);
				out.flush();
				out.print("\n");
				out.flush();
			} catch (SOAPException | NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
		});
		sendMSG.add(sendButton);
		
		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(12, 13, 45, 16);
		add(nameLabel);
		
		myPortLabel = new JLabel("My Port:");
		myPortLabel.setBounds(134, 13, 56, 16);
		add(myPortLabel);
		
		tarPortLabel = new JLabel("TargetPort:");
		tarPortLabel.setBounds(278, 12, 82, 19);
		add(tarPortLabel);
		
		nameField = new JTextField(name);
		nameField.setEditable(false);
		nameField.setBounds(69, 10, 53, 22);
		add(nameField);
		
		myPort = new JTextField(Integer.toString(portA));
		myPort.setEditable(false);
		myPort.setBounds(202, 10, 53, 22);
		add(myPort);
		
		tarPort = new JTextField(Integer.toString(portB));
		tarPort.setEditable(false);
		tarPort.setBounds(366, 10, 53, 22);
		getContentPane().add(tarPort);
		
		setVisible(true);
	}
        
        public int addToInbox(SOAPMessage soapMess)
        {
            	try {
			msgFactory = MessageFactory.newInstance();
			soapMsg = msgFactory.createMessage();
			soapPart = soapMsg.getSOAPPart();
			soapEnvelope = soapPart.getEnvelope();
			soapHeader = soapEnvelope.getHeader();
			soapBody = soapEnvelope.getBody();
		} catch (SOAPException e1) {
			e1.printStackTrace();
		}
            
            String nMess=null;
            try {
                nMess = soapMess.getSOAPBody().getTextContent();
            } catch (SOAPException ex) {
                Logger.getLogger(NodeApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            String messTo = null;
            try { 
                messTo = soapMess.getSOAPHeader().getTextContent();
            } catch (SOAPException ex) {
                Logger.getLogger(NodeApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                 
            if(!(messTo.equals(name)))
            {
                if(name.equals(String.valueOf(nMess.charAt(13)))){
                    if(!messTo.equals("toAll"))
                        logsArea.append("RECEIVER NOT FOUND\n");
                    return 0;
                }
            }
            
            
            if(messTo.equals(name) || messTo.equals("toAll")){
                    try {
                        inboxField.append(soapMess.getSOAPBody().getTextContent()+"\n");
                } catch (SOAPException ex) {
                    Logger.getLogger(NodeApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(!messTo.equals(name) || messTo.equals("toAll"))
            {
                try {
                     
                    if(!messTo.equals("toAll"))
                        logsArea.append("Unicast message from " + nMess.charAt(13) + " to " + messTo + " forwarded\n");
                    else
                        logsArea.append("Broadcast messages from " + nMess.charAt(13) + " forwarded\n");
                    
                        System.out.println("PRZESYLANIE WIADOMOSCI DALEJ ");
                        
                        nMess = soapMess.getSOAPBody().getTextContent();
                    
                        soapHeader.addTextNode(messTo);
                        
                        SOAPBodyElement element = soapBody.addBodyElement(soapEnvelope.createName("JAVA", "LAB", "6"));
                        element.addChildElement("test").addTextNode(nMess.substring(0,15)+" ");

                        nMess = soapMess.getSOAPBody().getTextContent();
                        element.addTextNode(nMess.substring(16,nMess.length()));
                        PrintStream out = new PrintStream(nextNodeSocket.getOutputStream(), true);
                        
                        nMess = soapMess.getSOAPBody().getTextContent();
                        System.out.println(nMess);
                        soapMsg.writeTo(out);
                        out.flush();
                        out.print("\n");
                        out.flush();
                } catch (SOAPException | NumberFormatException | IOException e1) {
                        e1.printStackTrace();
                }
            }
            return 0;
        }
        
	
	public static void main(String[] args) {
            
            name = args[0];
            portA=Integer.parseInt(args[1]);
            portB=Integer.parseInt(args[2]);
            
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NodeApp window = new NodeApp();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			serverSocket = new ServerSocket(portA);
			clientSocket = serverSocket.accept();
			serverThread = new Server(clientSocket, inbox, nodeApp);
			serverThread.start();
		} catch (NumberFormatException | IOException e1) {
		e1.printStackTrace();
		}
	}
}
