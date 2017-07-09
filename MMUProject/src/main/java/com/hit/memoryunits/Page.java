package com.hit.memoryunits;

import java.io.Serializable;
import java.util.Arrays;

public class Page<T> implements Serializable {
	private T content;
	private long pageId;
	public Page(long id,T content) {
		this.content = content;
		this.pageId = id;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public long getPageId() {
		return pageId;
	}
	public void setPageId(long pageId) {
		this.pageId = pageId;
	}
	@Override
	public String toString() {
		return Arrays.toString((byte[]) content);
	}
	
	
	
	
	
	
	
}
