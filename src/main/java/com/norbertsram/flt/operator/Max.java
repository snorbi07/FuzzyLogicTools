package com.norbertsram.flt.operator;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public enum Max implements Operator {
	INSTANCE;

	public double apply(double arg0, double arg1) {
		return Math.max(arg0, arg1);
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());
		
		return stringHelper.toString();
	}
	
}
