package com.norbertsram.flt.operator;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public enum ProbabilisticOr implements Operator {
	INSTANCE;

	public double apply(double arg0, double arg1) {
		double result = arg0 + arg1 - arg0 * arg1;
		return result;
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());
		
		return stringHelper.toString();
	}
	
}
