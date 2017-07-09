package com.hit.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;


public class MMULogger {

	public final static String DEFALUT_FILE_NAME = "log.txt";
	private FileHandler handler;
	public static MMULogger mmuLogger = null;
	public static MMULogger getInstance()
	{
		if(mmuLogger==null)
		{
			mmuLogger = new MMULogger();
		}
		return mmuLogger;
	}
	
	private MMULogger(){
		try{
			handler = new FileHandler(DEFALUT_FILE_NAME);
			handler.setFormatter(new OnlyMessageFormatter());
		}
		catch (IOException e)
		{
			System.err.println("Cennot create Logger"+e.getMessage());
		}
		
	}
	
	public synchronized void write(String command,Level level)
	{
		LogRecord logRecord = new LogRecord(level, command);
		handler.publish(logRecord);
	}
	
	protected void finalize()
	{
		handler.close();
	}
	
	public class OnlyMessageFormatter extends Formatter{

		
		public OnlyMessageFormatter() {super(); }

		@Override
		public String format(final LogRecord record) {

			return record.getMessage()+System.lineSeparator();
		}
		
	}
	

	public static String getDefalutFileName() {
		return DEFALUT_FILE_NAME;
	}
	
	
}
