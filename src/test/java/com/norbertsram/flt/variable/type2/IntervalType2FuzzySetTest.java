package com.norbertsram.flt.variable.type2;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.norbertsram.flt.common.Interval;
import com.norbertsram.flt.mf.IndicatorMembershipFunction;
import com.norbertsram.flt.mf.MembershipFunction;
import com.norbertsram.flt.mf.TriangularMembershipFunction;

public class IntervalType2FuzzySetTest {
	
	private IntervalType2FuzzySet set;
	
	@Before
	public void init() {
		MembershipFunction upperMf = new TriangularMembershipFunction(2.0, 4.0, 6.0);
		MembershipFunction lowerMf = new TriangularMembershipFunction(3.0, 4.0, 5.0);
		set = new IntervalType2FuzzySet(upperMf, lowerMf);
	}	
	
	@Test
	public void testOverlapping() {
		IndicatorMembershipFunction mf = (IndicatorMembershipFunction) set.valueFor(3.0);
		Interval interval = mf.getInterval();
		assertTrue(interval.getLowerBound() == 0.0);
		assertTrue(interval.getUpperBound() == 0.5);
		
		mf = set.valueFor(3.5);
		interval = mf.getInterval();
		assertTrue(interval.getLowerBound() == 0.5);
		assertTrue(interval.getUpperBound() == 0.75);
				
		mf = set.valueFor(4.0);
		interval = mf.getInterval();
		assertTrue(interval.getLowerBound() == 1.0);
		assertTrue(interval.getUpperBound() == 1.0);
		
		mf = set.valueFor(4.5);
		interval = mf.getInterval();
		assertTrue(interval.getLowerBound() == 0.5);
		assertTrue(interval.getUpperBound() == 0.75);

		mf = set.valueFor(5.0);
		interval = mf.getInterval();
		assertTrue(interval.getLowerBound() == 0.0);
		assertTrue(interval.getUpperBound() == 0.5);
	}
	
	@Test
	public void testBoundries() {
		IndicatorMembershipFunction mf = set.valueFor(2.0);
		Interval interval = mf.getInterval();
		assertTrue(interval.getLowerBound() == 0.0);
		assertTrue(interval.getUpperBound() == 0.0);

		mf = set.valueFor(7.0);
		interval = mf.getInterval();
		assertTrue(interval.getLowerBound() == 0.0);
		assertTrue(interval.getUpperBound() == 0.0);
	}
}
