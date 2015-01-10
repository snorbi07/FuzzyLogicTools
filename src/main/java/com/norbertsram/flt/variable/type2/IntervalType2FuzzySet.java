package com.norbertsram.flt.variable.type2;

import com.norbertsram.flt.mf.IndicatorMembershipFunction;
import com.norbertsram.flt.mf.MembershipFunction;

public class IntervalType2FuzzySet extends Type2FuzzySet<MembershipFunction> {

	public IntervalType2FuzzySet(MembershipFunction umf, MembershipFunction lmf) {
		super(umf, lmf, IndicatorMembershipFunctionFactory.INSTANCE);
	}
	
	@Override
	public IndicatorMembershipFunction valueFor(Double input) {
		return (IndicatorMembershipFunction) super.valueFor(input);
	}
	
}
