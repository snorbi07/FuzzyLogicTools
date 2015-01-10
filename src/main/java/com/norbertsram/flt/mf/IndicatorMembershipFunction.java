package com.norbertsram.flt.mf;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Objects.ToStringHelper;
import com.norbertsram.flt.common.Interval;

public class IndicatorMembershipFunction implements MembershipFunction {

	private final Interval interval;
	
	public IndicatorMembershipFunction(Interval interval) {
		this.interval = Preconditions.checkNotNull(interval);
	}
	
	public IndicatorMembershipFunction(double lowerBound, double upperBound) {
		this(new Interval(lowerBound, upperBound));
	}
	
	public Double valueFor(Double x) {
		checkNotNull(x);
		double lowerBound = interval.getLowerBound();
		double upperBound = interval.getUpperBound();
		double result = 0.0;
		
		if (x >= lowerBound && x <= upperBound) {
			result = 1.0;
		}
		
		return result;
	}

	public Interval getInterval() {
		return interval;
	}
	
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());

		stringHelper.add("interval", getInterval());
		
		return stringHelper.toString();
	}

}
