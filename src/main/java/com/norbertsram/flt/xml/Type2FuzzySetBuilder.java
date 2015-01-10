package com.norbertsram.flt.xml;

import com.google.common.collect.ImmutableMap;
import com.norbertsram.flt.mf.MembershipFunction;
import com.norbertsram.flt.variable.type2.Type2FuzzySet;

public class Type2FuzzySetBuilder {

	private static final ImmutableMap<String,Type2FuzzySetConverter> converter;
	static {
		converter = new ImmutableMap.Builder<String, Type2FuzzySetConverter>()
				.put(IntervalType2FuzzySetConverter.NAME, IntervalType2FuzzySetConverter.INSTANCE)				
				.build();
	}
	
	public static <T extends MembershipFunction> Type2FuzzySet<T> build(String mfXml) {
		Type2FuzzySetBean mfb = Type2FuzzySetBean.valueOf(mfXml);
	
		return build(mfb);
	}
	
	static  <T extends MembershipFunction> Type2FuzzySet<T> build(Type2FuzzySetBean fsb) {
		if (fsb == null) {
			throw new IllegalArgumentException("Unsupported membership function declaration!");
		}
		
		String fsType = fsb.getName();
		Type2FuzzySetConverter fuzzySetConverter = converter.get(fsType);
		boolean unsupportedMembershipFunctionType = fuzzySetConverter == null;
		if (unsupportedMembershipFunctionType) {
			throw new IllegalArgumentException("Unsupported fuzzy set type: '" + fsType + "'");
		}
		
		@SuppressWarnings("unchecked")
		Type2FuzzySet<T> type2FuzzySet = (Type2FuzzySet<T>) fuzzySetConverter.toType2FuzzySet(fsb);
				
		return type2FuzzySet;
	}
	
}