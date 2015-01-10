package com.norbertsram.flt.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Preconditions;
import com.norbertsram.flt.mf.TrapezoidalMembershipFunction;

enum TrapezoidalMembershipFunctionConverter implements MembershipFunctionConverter<TrapezoidalMembershipFunction> {
	INSTANCE;
	;
	
	final static int NUMBER_OF_TRAPMF_PARAMETERS = 4;
	final static String NAME = "trapmf";
	
	@Override
	public MembershipFunctionBean toBean(TrapezoidalMembershipFunction mf) {
		MembershipFunctionBean mfb = new MembershipFunctionBean();
		
		mfb.setName(NAME);
		List<Double> parameters = new ArrayList<>();
		parameters.add(mf.getA());
		parameters.add(mf.getB());
		parameters.add(mf.getC());
		parameters.add(mf.getD());
		mfb.setParameters(parameters);
		
		return mfb;
	}

	@Override
	public TrapezoidalMembershipFunction toMembershipFunction(MembershipFunctionBean mfb) {
		List<Double> parameters = mfb.getParameters();
		Preconditions.checkArgument(parameters.size() == NUMBER_OF_TRAPMF_PARAMETERS);

		Iterator<Double> parameterIterator = parameters.iterator();
		double a = parameterIterator.next();
		double b = parameterIterator.next();
		double c = parameterIterator.next();
		double d = parameterIterator.next();

		TrapezoidalMembershipFunction mf = new TrapezoidalMembershipFunction(a,	b, c, d);

		return mf;
	}

}
