package com.hit.processes;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;
import com.hit.util.MMULogger;

public class Process implements Runnable {
	private MemoryManagementUnit mmu;
	private int ProcessId;
	private ProcessCycles processCycles;
	
	
	public Process(MemoryManagementUnit mmu, int processId, ProcessCycles processCycles) {
		this.mmu = mmu;
		ProcessId = processId;
		this.processCycles = processCycles;
	}

	@Override
	public void run() {
		AtomicInteger j= new AtomicInteger(1);//for keeping track with witch process is currently running
		List<ProcessCycle> processCycleList = processCycles.getProcessCycles();
		for(ProcessCycle i:processCycleList)
		{
			Long[] moveTohardIds = new Long[i.getPages().size()];
			moveTohardIds = i.getPages().toArray(moveTohardIds);
			try {
			Page<byte[]>[] requestPages =	mmu.getPages(moveTohardIds);
			List <byte[]> data = i.getData();
			for(int k=0;k<requestPages.length;k++)//Write to content to the request pages
			{
				requestPages[k].setContent(data.get(k));
				MMULogger.getInstance().write("GP: P"+ProcessId+" "+requestPages[k].getPageId()+" "+requestPages[k], Level.INFO);
			}
				 Thread.sleep(i.getSleepMs());//sleep
			} catch (InterruptedException e) {
				MMULogger.getInstance().write("InterruptedException:"+e.getMessage(), Level.SEVERE);
			}
			j.incrementAndGet();
		}
		j.decrementAndGet();
		System.out.println("The process id number: "+ ProcessId+" is finished all of is "+j+ " tasks" );	
	}
	
	public int getProcessId() {
		return ProcessId;
	}


	public void setProcessId(int processId) {
		ProcessId = processId;
	}

}
