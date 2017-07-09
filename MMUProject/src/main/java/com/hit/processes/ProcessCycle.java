package com.hit.processes;

import java.util.List;

public class ProcessCycle {

	private  int sleepMs;
	private List<Long> pages;
	private List<byte[]> data;
	
	public ProcessCycle(int sleepMs, List<Long> pages, List<byte[]> data) {
		this.sleepMs = sleepMs;
		this.pages = pages;
		this.data = data;
	}

	public int getSleepMs() {
		return sleepMs;
	}

	public void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}

	public List<Long> getPages() {
		return pages;
	}

	public void setPages(List<Long> pages) {
		this.pages = pages;
	}

	public List<byte[]> getData() {
		return data;
	}

	public void setData(List<byte[]> data) {
		this.data = data;
	}
	
	
	
}
