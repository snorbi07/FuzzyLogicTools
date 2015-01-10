package com.norbertsram.flt.variable;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.norbertsram.flt.mf.MembershipFunction;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a possible fuzzy set, such as 'tall' for example.
 * The fuzzy set does not belong to any specific variable/input. 
 *  
 * @author norbi
 *
 */
public class Set implements FuzzySet {
	
	private final String name;
	private final MembershipFunction membershipFunction;
	
	public Set(String name, MembershipFunction membershipFunction) {		
		this.name = checkNotNull(name);
		this.membershipFunction = checkNotNull(membershipFunction);
	}

	public String getName() {
		return name;
	}
	
	@Override
	public Double valueFor(Double crispValue) {
		double value = membershipFunction.valueFor(crispValue);		
		return value;
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());
		
		stringHelper.add("name", name);
		stringHelper.add("membershipFunction", membershipFunction);
		
		return stringHelper.toString();
	}

}
