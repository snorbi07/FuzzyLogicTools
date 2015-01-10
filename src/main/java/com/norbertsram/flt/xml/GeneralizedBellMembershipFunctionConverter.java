package com.norbertsram.flt.xml;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.norbertsram.flt.mf.GeneralizedBellMembershipFunction;


enum GeneralizedBellMembershipFunctionConverter implements
		MembershipFunctionConverter<GeneralizedBellMembershipFunction> {
	INSTANCE;
	;
	
	private final static int NUMBER_OF_GBELLMF_PARAMETERS = 3;
	final static String NAME = "gbellmf";
	
	@Override
	public MembershipFunctionBean toBean(GeneralizedBellMembershipFunction mf) {
		MembershipFunctionBean mfb = new MembershipFunctionBean();
		mfb.setName("gbellmf");
		List<Double> parameters = new ArrayList<>();
		parameters.add(mf.getA());
		parameters.add(mf.getB());
		parameters.add(mf.getC());
		mfb.setParameters(parameters);
		return mfb;
	}

	@Override
	public GeneralizedBellMembershipFunction toMembershipFunction(
			MembershipFunctionBean mfb) {
		List<Double> parameters = mfb.getParameters();
		checkArgument(parameters.size() == NUMBER_OF_GBELLMF_PARAMETERS);

		Iterator<Double> parameterIterator = parameters.iterator();
		double a = parameterIterator.next();
		double b = parameterIterator.next();
		double c = parameterIterator.next();

		GeneralizedBellMembershipFunction mf = 
				new GeneralizedBellMembershipFunction(a, b, c);

		return mf;
	}

}
