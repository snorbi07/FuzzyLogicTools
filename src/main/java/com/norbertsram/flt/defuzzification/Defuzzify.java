package com.norbertsram.flt.defuzzification;

import com.norbertsram.flt.variable.FuzzySet;

public interface Defuzzify {
	public double apply(FuzzySet set);
}
