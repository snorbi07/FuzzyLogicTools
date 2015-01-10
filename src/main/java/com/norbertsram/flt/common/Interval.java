package com.norbertsram.flt.common;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.primitives.Doubles;

public final class Interval {

	public static final Interval EMPTY = new Interval(0.0, 0.0);
	
	private final double lowerBound;
	private final double upperBound;
	
	public Interval(double lowerBound, double upperBound) {
		boolean isLesserOrEqual = Doubles.compare(lowerBound, upperBound) <= 0;
		Preconditions.checkArgument(isLesserOrEqual, "Lower bound must be less than the upper bound!");
		
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public double getUpperBound() {
		return upperBound;
	}
	
	public boolean isIntersect(Interval other) {
		return (other.getLowerBound()  < upperBound) && (lowerBound < other.getUpperBound()); 
	}
	
	public boolean isDisjoint(Interval other) {
		return !isIntersect(other);
	}
	
	public static boolean isEmpty(Interval interval) {
		return interval.equals(EMPTY);
	}
	
	public Interval intersect(Interval other) {
		// 2 sets are represented as [a, b] = this and [x, y] = other
		final double a = lowerBound;
		final double b = upperBound;
		final double x = other.getLowerBound();
		final double y = other.getUpperBound();
		Interval result = EMPTY;

		// a x b y 
		if (a <= x && x <= b && b <= y) {
			result = new Interval(x, b);			
		}
		// a x y b 
		else if (a <= x && y <= b) {
			result = other;
		}
		// x a b y
		else if (x <= a && b <= y) {
			result = this;
		}
		// x a y b
		else if (x <= a && a <= y && y <= b) {
			result = new Interval(a, y);
		}
		
		return result;
	}
	
	public Interval disjoint(Interval other) {
		if (isIntersect(other)) {
			return EMPTY;
		}
		
		double lBound = 0.0;
		double uBound = 0.0;
		if (upperBound < other.getUpperBound()) {
			lBound = upperBound;
			uBound = other.getLowerBound();
		}
		else {
			lBound = other.getUpperBound();
			uBound = lowerBound;
		}
		
		return new Interval(lBound, uBound);
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());

		stringHelper.add("lowerBound", lowerBound);
		stringHelper.add("upperBound", upperBound);
		
		return stringHelper.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp = Double.doubleToLongBits(lowerBound);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(upperBound);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Interval)) {
			return false;
		}
		Interval other = (Interval) obj;
		if (Double.doubleToLongBits(lowerBound) != Double.doubleToLongBits(other.lowerBound)) {
			return false;
		}
		if (Double.doubleToLongBits(upperBound) != Double.doubleToLongBits(other.upperBound)) {
			return false;
		}

		return true;
	}
	
}
