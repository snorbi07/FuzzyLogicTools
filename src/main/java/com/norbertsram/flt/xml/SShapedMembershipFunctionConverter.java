package com.norbertsram.flt.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Preconditions;
import com.norbertsram.flt.mf.SShapedMembershipFunction;

enum SShapedMembershipFunctionConverter implements
		MembershipFunctionConverter<SShapedMembershipFunction> {
	INSTANCE;
	;
	
	private final static int NUMBER_OF_SMF_PARAMETERS = 2;
	final static String NAME = "smf";

	
	@Override
	public MembershipFunctionBean toBean(SShapedMembershipFunction mf) {
		MembershipFunctionBean mfb = new MembershipFunctionBean();
		mfb.setName(NAME);
		List<Double> parameters = new ArrayList<>();
		parameters.add(mf.getA());
		parameters.add(mf.getB());
		mfb.setParameters(parameters);
		return mfb;
	}

	@Override
	public SShapedMembershipFunction toMembershipFunction(
			MembershipFunctionBean mfb) {
		List<Double> parameters = mfb.getParameters();
		Preconditions
				.checkArgument(parameters.size() == NUMBER_OF_SMF_PARAMETERS);

		Iterator<Double> parameterIterator = parameters.iterator();
		double a = parameterIterator.next();
		double b = parameterIterator.next();

		SShapedMembershipFunction mf = 
				new SShapedMembershipFunction(a, b);

		return mf;
	}

}
