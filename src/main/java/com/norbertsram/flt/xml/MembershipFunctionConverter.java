package com.norbertsram.flt.xml;

import com.norbertsram.flt.mf.MembershipFunction;

interface MembershipFunctionConverter<T extends MembershipFunction> {
	
	public MembershipFunctionBean toBean(T mf);
	
	public T toMembershipFunction(MembershipFunctionBean mfb);
	
}
