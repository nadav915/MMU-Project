package com.hit.memoryunits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RAM {
	
	int initialCapacity;
	private HashMap<Long,Page<byte[]>> pages; 
	public RAM(int initialCapacity) {
		this.initialCapacity = initialCapacity;
		pages =new HashMap<Long,Page<byte[]>>(initialCapacity);
	}

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	
	public Page<byte[]> getPage(Long pageId)
	{
		Page<byte[]> retPage = pages.get(pageId);
		return retPage;
	}
	

	public HashMap<Long, Page<byte[]>> getPages() {
		return pages;
	}
	
	public Page<byte[]>[] getPages(Long[] pageIds){
		List<Long> gotPageIds = Arrays.asList(pageIds);
		int j=0;
		Page<byte[]>[] retPages = new Page[pageIds.length];
		for(Long i : gotPageIds){
			if(pages.containsKey(i))
			{
				retPages[j]=pages.get(i);
				j++;
	
			}
		}
		return retPages;
	}
	

	public void setPages(HashMap<Long, Page<byte[]>> pages) {

		this.pages = pages;
	}
	
	public void addPage(Page<byte[]> addPage)
	{
			pages.put( addPage.getPageId(), addPage);
	}
	
	public void addPages(Page<byte[]>[] addPages) {
		List<Page> gotPages = Arrays.asList(addPages);
		for(Page<byte[]> i : gotPages)
		{	
				pages.put(i.getPageId(), i);		
		}
		
	}

	public void removePage(Page<byte[]> removePage)
	{	
		if(pages.containsKey(removePage.getPageId()))
		{
			pages.remove(removePage.getPageId());
		}
	}
	
	public void removePages(Page<byte[]>[] removePages){
		List<Page> remPages = Arrays.asList(removePages);
		for(Page<byte[]> i : remPages){
			if(pages.containsKey(i))
			{
				pages.remove(i.getPageId());
			}
		}
	}
}
