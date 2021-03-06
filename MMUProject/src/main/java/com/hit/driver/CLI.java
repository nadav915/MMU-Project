package com.hit.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.logging.Level;
import com.hit.util.MMULogger;
import com.hit.view.View;


public class CLI extends Observable implements Runnable,View{
	public static String LRU ="LRU";
	public static String LFU = "LFU";
	public static String SECOND_CHANCE ="SECOND CHANCE";
	public static String START = "START";
	public static String STOP = "STOP";
	public static String LOCAL = "LOCAL";
	public static String REMOTE = "REMOTE";
	public InputStreamReader reader;
	public OutputStreamWriter writer;
	public String arr[]=new String[3];
	
	public CLI(InputStream in,OutputStream out)
	{
		reader = new InputStreamReader(in);
		writer = new OutputStreamWriter(out);
	}
	@Override
	public void run() {
		start();
	}
	public void write(String string) throws IOException
	{
		System.out.println(string);
	}
	
	
	@Override
	public void start() {
		
		BufferedReader br = new BufferedReader(reader);
		try {
			write("Please enter START to start the program and STOP to stop it");
			String commend =br.readLine();
			while(!commend.equals(STOP))
			{
				if(commend.equals(START))
				{
					write("Please enter the requierd algorithem and RAM capacity and REMOTE or LOCAL");
					String commend2 = br.readLine();
					int i = commend2.indexOf(" ");
					String algo = commend2.substring(0,i);
					commend2 =commend2.substring(i+1);
					 i = commend2.indexOf(" ");
					String RamCapacity = commend2.substring(0,i);
					String config =	commend2.substring(i+1);
					while(!algo.equals(LRU)&&!algo.equals(LFU)&&!algo.equals(SECOND_CHANCE)&&!config.equals(REMOTE)&&!config.equals(LOCAL))
					{
						write("Not valid command...");
						write("Please enter the requierd algorithem and RAM capacity");
						commend2 = br.readLine();
						i = commend2.indexOf(" ");
						algo = commend2.substring(0,i);
						commend2 =commend2.substring(i+1);
						i = commend2.indexOf(" ");
						RamCapacity = commend2.substring(0,i);
						config = commend2.substring(i+1);
					}
					arr[0]=algo;
					arr[1]=RamCapacity;
					arr[2]=config;
					setChanged();
					notifyObservers(arr);
				}
				else
				{
					write("Not valid command...");
				}
				commend = br.readLine();
			}
			System.out.println("Thank you..");
		}
		catch (IOException e ) {
			MMULogger.getInstance().write("IOException:"+e.getMessage(), Level.SEVERE);
		}
		
	}
	
	

}
