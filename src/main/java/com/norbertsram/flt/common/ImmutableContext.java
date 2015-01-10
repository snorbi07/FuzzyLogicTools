package com.norbertsram.flt.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ImmutableMap;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class ImmutableContext<T> implements Context<T> {	
	private final Map<String, T> context;
	
	static public <E> ImmutableContext<E> newContext() {
		return new ImmutableContext<E>();
	}
	
	public ImmutableContext() {
		context = Collections.emptyMap();
	}
	
	private ImmutableContext(Map<String, T> context) {
		this.context = context;
	}
	
	public Context<T> put(String variableName, T value) {
		checkNotNull(variableName);
				
		ImmutableMap<String,T> copy =  
				new ImmutableMap.Builder<String,T>()				
					.putAll(context)
					.put(variableName, value)
					.build();
						
		ImmutableContext<T> newContext = new ImmutableContext<T>(copy);
		
		return newContext;
	}
	
	public T get(String variableName) {
		boolean containsKey = context.containsKey(variableName);
		checkArgument(containsKey, "Variable name not in context!");
		
		return context.get(variableName);
	}
	
	public Collection<String> getIdentifiers() {
		Set<String> keySet = context.keySet();		
		return Collections.unmodifiableList(new ArrayList<String>(keySet)); 
	}
	
	public Collection<T> getValues() {
		Collection<T> values = context.values();		
		return Collections.unmodifiableCollection(values); 
	}
	
	public boolean isEmpty() {
		return context.isEmpty();
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());
		
		Set<Entry<String,T>> entrySet = context.entrySet();
		
		for (Entry<String,T> entry : entrySet) {
			stringHelper.add(entry.getKey(), entry.getValue());			
		}
		
		return stringHelper.toString();
	}

}
