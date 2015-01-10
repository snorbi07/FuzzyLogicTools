package com.norbertsram.flt.variable.type2;

import com.norbertsram.flt.mf.IndicatorMembershipFunction;
import com.norbertsram.flt.mf.MembershipFunction;

enum IndicatorMembershipFunctionFactory implements UncertaintyMembershipFunctionFactory{
	INSTANCE;

	@Override
	public MembershipFunction create(double x, double lowerBound, double upperBound) {
		return new IndicatorMembershipFunction(lowerBound, upperBound);
	}

}
