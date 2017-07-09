package com.hit.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LFUAlgoCacheImpl;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.SecondChanceAlgoCacheImpl;
import com.hit.driver.CLI;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.processes.ProcessCycles;
import com.hit.processes.RunConfiguration;
import com.hit.util.MMULogger;

public class MMUModel extends Observable implements Model {

public static String CONFIG_FILE_NAME ="bin/com/hit/config/Configuration.json";
public int numProcesses;
public int ramCapacity;
public static String[] configuration;	

	public static RunConfiguration readConfigurationFile()
	{
		try{
			RunConfiguration runConfig = new Gson().fromJson(new JsonReader(new FileReader(CONFIG_FILE_NAME)),RunConfiguration.class);
			return runConfig;
		}
		catch(FileNotFoundException e)
		{
			MMULogger.getInstance().write("FileNotFoundException:"+e.getMessage(), Level.SEVERE);
		}
		catch(JsonIOException | JsonSyntaxException e)
		{
			MMULogger.getInstance().write("JsonException:"+e.getMessage(), Level.SEVERE);
		}
		return null;
	}
	
	public static void runProcesses(List<com.hit.processes.Process> applications)
	{
		try{
			ExecutorService executor = Executors.newCachedThreadPool();
			for(com.hit.processes.Process i : applications)
			{
				executor.execute(i);
			}
			executor.shutdown();
			executor.awaitTermination(60,TimeUnit.SECONDS);
		}
		catch(InterruptedException e)
		{
			MMULogger.getInstance().write("InterruptedException:"+e.getMessage(), Level.SEVERE);
		}
	}
	
	public static List<com.hit.processes.Process> createProcesses(List<ProcessCycles> appliocationsScenarios,MemoryManagementUnit mmu)
	{
		 List<com.hit.processes.Process> process = new ArrayList<com.hit.processes.Process>();
		 int j=1;
		 for(ProcessCycles i :appliocationsScenarios)
		 {
			 process.add( new com.hit.processes.Process(mmu, j, i));
			 j++;
		 }
		return process;
		
	}
	
	@Override
	public void start() {
		
		IAlgoCache<Long, Long> algo = null;
		int capacity = Integer.parseInt(configuration[1]);
		if(configuration[0].equals(CLI.LFU))
		{
			algo = new LFUAlgoCacheImpl<>(capacity);
		}
		else if(configuration[0].equals(CLI.LRU))
		{
			algo = new LRUAlgoCacheImpl<>(capacity);
		}
		else
		{
			algo = new SecondChanceAlgoCacheImpl<>(capacity);
		}
		MemoryManagementUnit mmu = new MemoryManagementUnit(capacity, algo);
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
		List<com.hit.processes.Process> processes = createProcesses(processCycles, mmu);
		numProcesses=processes.size();
		MMULogger.getInstance().write("PN:"+processes.size(), Level.INFO);
		runProcesses(processes);
		ArrayList<String> lines=readLog();
		setChanged();
		notifyObservers(lines);
		
	}

	public void setConfiguration(String[] configuration) {
		this.configuration = configuration;
	}
	
	public ArrayList<String> readLog()
	{
		try {
			File logFile = new File(MMULogger.getInstance().getDefalutFileName());
			Scanner scan = new Scanner(logFile);
			ArrayList<String> lines=new ArrayList<String>();
			
			while(scan.hasNextLine())
			{
				lines.add(scan.nextLine());
			}
			lines.add(String.valueOf(numProcesses));
		      return lines;
		}
		catch (IOException e) {
			MMULogger.getInstance().write("IOException: Cant read from the log file file"+e.getMessage(), Level.SEVERE);
			e.printStackTrace();
		}
		return null;
		
	}
}
