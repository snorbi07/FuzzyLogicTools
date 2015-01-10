package com.norbertsram.flt.variable.type2;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.norbertsram.flt.mf.MembershipFunction;
import com.norbertsram.flt.variable.GeneralizedFuzzySet;

public class Type2FuzzySet<T extends MembershipFunction> implements GeneralizedFuzzySet<Double, T> {

	// membership functions defining the footprint of uncertainty (FOU)
	private final MembershipFunction lowerMembershipFunction;

	private final MembershipFunction upperMembershipFunction;
	
	// type of membership function defining the 3rd dimension (second-order uncertainty fuzzy set model)	
	private final UncertaintyMembershipFunctionFactory uncertaintyMembershipFunctionFactory;

	public Type2FuzzySet(MembershipFunction umf, MembershipFunction lmf, UncertaintyMembershipFunctionFactory uMfFactory) {
		upperMembershipFunction = checkNotNull(umf);
		lowerMembershipFunction = checkNotNull(lmf);
		uncertaintyMembershipFunctionFactory = checkNotNull(uMfFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T valueFor(Double input) {
		double upperMfBound = upperMembershipFunction.valueFor(input);
		double lowerMfBound = lowerMembershipFunction.valueFor(input);
		
		MembershipFunction uncertaintyMf = uncertaintyMembershipFunctionFactory.create(input, lowerMfBound, upperMfBound);
		if (uncertaintyMf == null) {
			String arguments = "{ input = " + input + ", upperBound = " + upperMfBound + ", lowerBound = " + lowerMfBound + "}";
			String factoryName = uncertaintyMembershipFunctionFactory.getClass().getCanonicalName();
			throw new IllegalStateException("Got null from membership function factory: " + factoryName + " for arguments: " + arguments);
		}
		
		return (T) uncertaintyMf;
	}

	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());

		stringHelper.add("lowerMembershipFunction", lowerMembershipFunction);
		stringHelper.add("upperMembershipFunction", upperMembershipFunction);
		stringHelper.add("uncertaintyMembershipFunctionFactory", uncertaintyMembershipFunctionFactory);

		return stringHelper.toString();
	}

	public MembershipFunction getLowerMembershipFunction() {
		return lowerMembershipFunction;
	}

	public MembershipFunction getUpperMembershipFunction() {
		return upperMembershipFunction;
	}

}
