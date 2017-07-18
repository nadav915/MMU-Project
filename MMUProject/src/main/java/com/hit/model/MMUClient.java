package com.hit.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MMUClient implements Runnable,Model {

	public String[] commands;
	public String error;
	public MMUClient(String[] commands) {
		super();
		this.commands = commands;
	}
	@Override
	public void run() {
		start();
	}
	
	
	@Override
	public void start() {
		
					Socket myServer;
					try {
						myServer = new Socket("localhost", 12345);
						ObjectOutputStream output=new ObjectOutputStream(myServer.getOutputStream());
						ObjectInputStream input=new ObjectInputStream(myServer.getInputStream()); 
						String messageFromServer=(String)input.readObject();
						System.out.println("message from server: "+messageFromServer);
						output.writeObject(commands[0]+" "+commands[1]+" "+commands[2]+".json");
						output.flush();
						messageFromServer=(String)input.readObject();
						if(messageFromServer.equals("GOOD")){
						setError("good");
						FileOutputStream fos=new FileOutputStream(new File("config1.json"),false);
						byte b[]=new byte [1024];
						long bytesRead;
						do
						{
						bytesRead = input.read(b, 0, b.length);
						fos.write(b,0,(int)bytesRead);
						}while(!(bytesRead<1024));
						System.out.println("Comleted transferring the file");
						fos.close(); 
						}
						else{
							setError("error");
						}
						output.close(); 
						input.close();
						myServer.close();  
						
					} 
					catch (IOException | ClassNotFoundException e) {
						System.out.println("Client error");
					}
					
					catch (Exception e) {
						System.out.println("Client error");
					}
					
				}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		System.out.println("error set to "+error);
		this.error = error;
	}

	
	

	}

	

