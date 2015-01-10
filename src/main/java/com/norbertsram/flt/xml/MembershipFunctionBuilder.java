package com.norbertsram.flt.xml;

import com.google.common.collect.ImmutableMap;
import com.norbertsram.flt.mf.MembershipFunction;

public class MembershipFunctionBuilder {

	private static final ImmutableMap<String, MembershipFunctionConverter<?>> converter;
	static {
		converter = new ImmutableMap.Builder<String, MembershipFunctionConverter<?>>()
				.put(TriangularMembershipFunctionConverter.NAME, TriangularMembershipFunctionConverter.INSTANCE)
				.put(TrapezoidalMembershipFunctionConverter.NAME, TrapezoidalMembershipFunctionConverter.INSTANCE)
				.put(SShapedMembershipFunctionConverter.NAME, SShapedMembershipFunctionConverter.INSTANCE)
				.put(GeneralizedBellMembershipFunctionConverter.NAME, GeneralizedBellMembershipFunctionConverter.INSTANCE)
				.build();
	}
	
	public static MembershipFunction build(String mfXml) {
		MembershipFunctionBean mfb = MembershipFunctionBean.valueOf(mfXml);
		return build(mfb);
	}

	static MembershipFunction build(MembershipFunctionBean mfb) {
		if (mfb == null) {
			throw new IllegalArgumentException("Unsupported membership function declaration!");
		}
		
		String mfType = mfb.getName();
		MembershipFunctionConverter<?> membershipFunctionConverter = converter.get(mfType);
		boolean unsupportedMembershipFunctionType = membershipFunctionConverter == null;
		
		if (unsupportedMembershipFunctionType) {
			throw new IllegalArgumentException("Unsupported membership function type: '" + mfType + "'");
		}
		
		MembershipFunction membershipFunction = membershipFunctionConverter.toMembershipFunction(mfb);
		
		return membershipFunction;
	}
	
}