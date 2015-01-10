package com.norbertsram.flt.mf;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class TrapezoidalMembershipFunction implements MembershipFunction {

	private final double a;
	private final double b;
	private final double c;
	private final double d;
	
	public TrapezoidalMembershipFunction(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public Double valueFor(Double x) {
		checkNotNull(x);
		double result;		
		
		if (x <= a) {
			result = 0.0;
		}
		else if (a <= x && x <= b) {
			double nominator = x - a;
			double denominator = b - a;
			result = nominator / denominator;
		}
		else if (b <= x && x <= c) {
			result = 1.0;
		}
		else if (c <= x && x <= d) {
			double nominator = d - x;
			double denominator = d - c;
			result = nominator / denominator;
		}
		else if (d <= x) {
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
		stringHelper.add("d", d);
		
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
	
	public double getD() {
		return d;
	}

}
