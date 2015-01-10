package com.norbertsram.flt.defuzzification;

import static com.norbertsram.flt.common.Constant.EPSILON;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.norbertsram.flt.common.Util;
import com.norbertsram.flt.mf.MembershipFunction;
import com.norbertsram.flt.mf.TriangularMembershipFunction;
import com.norbertsram.flt.variable.Domain;
import com.norbertsram.flt.variable.FuzzySet;
import com.norbertsram.flt.variable.Set;

public class CentroidTest {

	@Test
	public void testCalculationPositiveDomainTrimf() {
		MembershipFunction tmf = new TriangularMembershipFunction(5.0, 10.0, 15.0);
		FuzzySet set = new Set("test", tmf);
		Domain domain = new Domain(0.0, 20.0, 0.1);
		Defuzzify centroid = new Centroid(domain);

		double result = centroid.apply(set);
		final double expectedResult = 10.0;
		double delta = Math.abs(expectedResult - result);
		
    	assertTrue(delta < EPSILON);
	}

	@Test
	public void testCalculationNegativDomainTrimf() {
		Domain domain = new Domain(-5.0, 5.0, 0.1);
		MembershipFunction tmf = new TriangularMembershipFunction(-2.5, 0.0, 2.0);
		FuzzySet set = new Set("test", tmf);
		Defuzzify centroid = new Centroid(domain);
		
		double result = centroid.apply(set);
		final double expectedResult = -0.16667;
		
		double roundedResult = Util.round(result);
		double roundedExpected = Util.round(expectedResult);

		double delta = Math.abs(Math.abs(roundedResult) - Math.abs(roundedExpected));
		double roundedDelta = Util.round(delta);
		assertTrue(roundedDelta < EPSILON);
	}

}
