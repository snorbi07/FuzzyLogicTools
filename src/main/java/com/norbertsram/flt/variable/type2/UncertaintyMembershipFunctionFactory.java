package com.norbertsram.flt.variable.type2;

import com.norbertsram.flt.mf.MembershipFunction;

public interface UncertaintyMembershipFunctionFactory {
	
	MembershipFunction create(double x, double lowerMembershipFunctionBound, double upperMembershipFunctionBound);
	
}
