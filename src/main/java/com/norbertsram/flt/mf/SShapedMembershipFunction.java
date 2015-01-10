package com.norbertsram.flt.mf;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class SShapedMembershipFunction implements MembershipFunction {

	private final double a;
	private final double b;
	
	public SShapedMembershipFunction(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public Double valueFor(Double x) {
		checkNotNull(x);
		double result;		

		double coefficientMean = (a + b) / 2.0;
		
		if (x <= a) {
			result = 0.0;
		}
		else if (x >= a && x <= coefficientMean) {
			double nominator = x - a;
			double denominator = b - a;
			double rational = nominator / denominator;
			result = 2.0 * Math.pow(rational, 2.0);
		}
		else if (x >= coefficientMean && x <= b) {
			double nominator = x - b;
			double denominator = b - a;
			double rational = nominator / denominator;
			result = 1.0 - (2.0 * Math.pow(rational, 2.0));
		}
		else if (x >= b) {
			result = 1.0;
		}
		else {
			throw new IllegalArgumentException("Unable to process value: " + x);
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());

		stringHelper.add("a", a);
		stringHelper.add("b", b);
		
		return stringHelper.toString();
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

}
