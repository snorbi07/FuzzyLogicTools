package com.norbertsram.flt.mf;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import static com.google.common.base.Preconditions.checkNotNull;

public class GeneralizedBellMembershipFunction implements MembershipFunction {
	private final double a;
	private final double b;
	private final double c;

	public GeneralizedBellMembershipFunction(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Double valueFor(Double x) {
		checkNotNull(x);
		double base = (x - c) / a;
		double exponential = 2.0 * b;
		double absoluteBase = Math.abs(base);
		double denominator = 1.0 + Math.pow(absoluteBase, exponential);
		double nominator = 1.0;
		double result = nominator / denominator;

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
