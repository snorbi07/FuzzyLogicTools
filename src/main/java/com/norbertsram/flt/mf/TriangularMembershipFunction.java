package com.norbertsram.flt.mf;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class TriangularMembershipFunction implements MembershipFunction {

	private final double a;
	private final double b;
	private final double c;
	
	public TriangularMembershipFunction(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Double valueFor(Double x) {
		checkNotNull(x);
		double result;		
		
		if (x <= a) {
			result = 0.0;
		}
		else if (x >= a && x <= b) {
			double nominator = x - a;
			double denominator = b - a;
			result = nominator / denominator;
		}
		else if (x >= b && x <= c) {
			double nominator = c - x;
			double denominator = c - b;
			result = nominator / denominator;
		}
		else if (x >= c) {
			result = 0.0;
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
		stringHelper.add("c", c);
		
		return stringHelper.toString();
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}

}
