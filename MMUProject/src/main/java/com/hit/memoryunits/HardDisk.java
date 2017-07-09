package com.hit.memoryunits;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import com.hit.util.MMULogger;

public class HardDisk {
	public final int _SIZE =1000;
	public final String DEFAULT_FILE_NAME ="harddisk.txt";
	public static HardDisk hardDisk = null;
	private Map hardDiskMap;
	public static HardDisk getInstance()
	{
		if(hardDisk==null)
		{
			hardDisk = new HardDisk();
		}
		return hardDisk;
	}
	
	private HardDisk() {
		hardDiskMap=new HashMap<Long,Page<byte[]>>( _SIZE);
		initializeMap();
		 writeTofile();
	}


	public Page<byte[]> pageFault(Long pageId) {
		
		Page<byte[]> retPage = (Page<byte[]>) hardDiskMap.get(pageId);
		MMULogger.getInstance().write("PF:"+pageId, Level.INFO);
		return retPage;
		
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage,Long moveToRamId) {
		
		Page<byte[]> pageMoveToRam = (Page<byte[]>) hardDiskMap.get(moveToRamId);
		hardDiskMap.put(moveToHdPage.getPageId(),moveToHdPage);
		writeTofile();
		MMULogger.getInstance().write("PR:MTH "+moveToHdPage.getPageId()+" MTR:"+moveToRamId, Level.INFO);
		return pageMoveToRam;
	}
	private void writeTofile()
	{
		try{
		FileOutputStream outPage = new FileOutputStream(DEFAULT_FILE_NAME);
		ObjectOutputStream streamOut = new ObjectOutputStream(outPage);
		streamOut.writeObject(hardDiskMap);
		streamOut.close();
		outPage.close();
		}
		catch (IOException e) {
		MMULogger.getInstance().write("IOException: Cant write the the hard disk file"+e.getMessage(), Level.SEVERE);
		}
	}
	
	private void initializeMap()
	{
		Long j=new Long(0);
		for(int i=0;i<_SIZE;i++)
		{
			hardDiskMap.put(j,new Page<byte[]>(j,new byte[]{0,0}));
			j++;
		}
	}
}
