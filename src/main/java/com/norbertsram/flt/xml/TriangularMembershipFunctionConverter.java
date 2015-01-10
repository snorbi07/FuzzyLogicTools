package com.norbertsram.flt.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Preconditions;
import com.norbertsram.flt.mf.TriangularMembershipFunction;

enum TriangularMembershipFunctionConverter implements
		MembershipFunctionConverter<TriangularMembershipFunction> {
	INSTANCE;
	;
	
	final static int NUMBER_OF_TRIMF_PARAMETERS = 3;
	final static String NAME = "trimf";
	
	@Override
	public MembershipFunctionBean toBean(TriangularMembershipFunction mf) {
		MembershipFunctionBean mfb = new MembershipFunctionBean();
		mfb.setName(NAME);
		List<Double> parameters = new ArrayList<>();
		parameters.add(mf.getA());
		parameters.add(mf.getB());
		parameters.add(mf.getC());
		mfb.setParameters(parameters);
		return mfb;
	}

	@Override
	public TriangularMembershipFunction toMembershipFunction(
			MembershipFunctionBean mfb) {
		List<Double> parameters = mfb.getParameters();
		Preconditions
				.checkArgument(parameters.size() == NUMBER_OF_TRIMF_PARAMETERS);

		Iterator<Double> parameterIterator = parameters.iterator();
		double a = parameterIterator.next();
		double b = parameterIterator.next();
		double c = parameterIterator.next();

		TriangularMembershipFunction mf = 
				new TriangularMembershipFunction(a,	b, c);

		return mf;
	}

}
