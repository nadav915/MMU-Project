package com.hit.memoryunits.MMUTest;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;

public class MMUTest {

	@Test
	public void testMemoryManagementUnit() throws ClassNotFoundException, IOException {
		byte[] pageContent ={1,2,3,4,5,6,7,8};
		Long[] pageIds = {1L,2L,3L,4L,5L,6L,7L,8L};
		Page<byte[]>[] Pages = new Page[pageIds.length];
		byte j=0;
		for(int i=0;i<pageIds.length;i++)
		{
			Pages[i]= new Page(pageIds[i],new byte[]{j,j});
			j++;
		}
		int ramSize =4;
		IAlgoCache<Long,Long> algo = new LRUAlgoCacheImpl<>(ramSize);
		MemoryManagementUnit mmu = new MemoryManagementUnit(ramSize, algo);
		Page<byte[]>[] retPages = mmu.getPages(pageIds);
		for(int i=0;i<retPages.length;i++)
		{
			assertTrue(retPages[i].getPageId()==Pages[i].getPageId());
		}
		
	}

}
