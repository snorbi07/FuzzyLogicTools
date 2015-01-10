package com.norbertsram.flt.mf;

import com.norbertsram.flt.variable.FuzzySet;

public interface MembershipFunction extends FuzzySet {
	public Double valueFor(Double input);
}
