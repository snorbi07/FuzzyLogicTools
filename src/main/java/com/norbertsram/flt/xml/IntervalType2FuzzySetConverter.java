package com.norbertsram.flt.xml;

import com.norbertsram.flt.mf.MembershipFunction;
import com.norbertsram.flt.variable.type2.IntervalType2FuzzySet;
import com.norbertsram.flt.variable.type2.Type2FuzzySet;

enum IntervalType2FuzzySetConverter implements Type2FuzzySetConverter {
	
	INSTANCE;
	
	final static String NAME = "intervalType2";
	
	@Override
	public Type2FuzzySetBean toBean(Type2FuzzySet<MembershipFunction> fs) {
		// TODO: implement!
		throw new UnsupportedOperationException("Missing implementation!");
	}

	@Override
	public Type2FuzzySet<MembershipFunction> toType2FuzzySet(Type2FuzzySetBean fsb) {
		MembershipFunctionBean upperMembershipFunctionBean = fsb.getUpperMembershipFunctionBean();
		MembershipFunctionBean lowerMembershipFunctionBean = fsb.getLowerMembershipFunctionBean();
		
		MembershipFunction umf = MembershipFunctionBuilder.build(upperMembershipFunctionBean);
		MembershipFunction lmf = MembershipFunctionBuilder.build(lowerMembershipFunctionBean);
		
		return new IntervalType2FuzzySet(umf, lmf);
	}

}
