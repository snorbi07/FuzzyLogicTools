package com.norbertsram.flt.xml;

import com.norbertsram.flt.mf.MembershipFunction;
import com.norbertsram.flt.variable.type2.Type2FuzzySet;

interface Type2FuzzySetConverter {
	
	public Type2FuzzySetBean toBean(Type2FuzzySet<MembershipFunction> fs);
	
	public Type2FuzzySet<MembershipFunction> toType2FuzzySet(Type2FuzzySetBean fsb);
	
}
