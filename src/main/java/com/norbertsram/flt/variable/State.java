package com.norbertsram.flt.variable;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Preconditions;

public class State implements FuzzySet {

	private final Set set; 
	private final Variable variable;	

	public State(Variable variable, String setName) {
		this.variable = checkNotNull(variable);
		checkNotNull(setName);
		
		Collection<String> setNames = variable.getSetNames();
		boolean isValidSetName = setNames.contains(setName);
		checkArgument(isValidSetName,
				"Set does not belong to variable!");
		
		set = variable.getSetForName(setName);
	}
	
	public State(Variable variable, Set set) {		
		this(variable, Preconditions.checkNotNull(set).getName());
	}
	
	@Override
	public Double valueFor(Double crispValue) {
		Preconditions.checkArgument(isValidCrispValue(crispValue), 
				"Crisp value out of bounds!");
		
		double value = set.valueFor(crispValue);
		return value;		
	}
	
	private boolean isValidCrispValue(double crispValue) {
		boolean isValid = false;
		
		Domain range = variable.getDomain();		
		isValid = range.contains(crispValue);
		
		return isValid;
	}
	
	public Variable getVariable() {
		return variable;
	}
	
	public Set getSet() {
		return set;
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());

		stringHelper.add("variable", variable.getName());
		stringHelper.add("setName", set.getName());

		return stringHelper.toString();
	}

}
