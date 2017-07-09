package com.hit.memoryunits;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import com.hit.algorithm.IAlgoCache;
import com.hit.util.MMULogger;

public class MemoryManagementUnit {
	
	public IAlgoCache<Long, Long> algo;
	public RAM ram;
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Long> algo) {
		this.algo = algo;
		ram = new RAM(ramCapacity);
		MMULogger.getInstance().write("RC:"+ramCapacity,Level.INFO);
	}
	public synchronized Page<byte[]>[] getPages(Long[] pageIds)
	{
		Page<byte[]>[] retPages = new Page[pageIds.length];
		HardDisk hardDisk = HardDisk.getInstance();
		for(int i=0;i<pageIds.length;i++)
		{
			List<Long> currentPageId = Arrays.asList(pageIds[i]);
			List <Long> requestPage = algo.putElement(currentPageId, currentPageId);
			if(requestPage.isEmpty())//RAM is not full
				{
					Page retPage=hardDisk.pageFault(pageIds[i]);
					ram.addPage(retPage);
				}
			else//RAM is full
				{
					Page<byte[]> moveToHardPage = ram.getPage(requestPage.get(0));
					System.out.println(moveToHardPage);
					ram.removePage(moveToHardPage);
					Page<byte[]> moveToRamPage = hardDisk.pageReplacement(moveToHardPage, pageIds[i]);
					ram.addPage(moveToRamPage);
				}
			retPages[i]=ram.getPage(pageIds[i]);
			}
			
		return retPages;
		
	}
	
	
	
	
	

}
