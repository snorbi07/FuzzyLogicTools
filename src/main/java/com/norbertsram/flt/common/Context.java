package com.norbertsram.flt.common;

import java.util.Collection;

public interface Context<T> {	
	
	public Context<T> put(String variableName, T value);
	
	public T get(String variableName);
	
	public Collection<String> getIdentifiers();
	
	public Collection<T> getValues();
	
	public boolean isEmpty();	
	
}
