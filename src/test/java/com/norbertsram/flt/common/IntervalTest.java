package com.norbertsram.flt.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {
	// 2 sets marked as: [A, B] and [X, Y]
	
	@Test
	public void testOverlapping() {
		// case: A X B Y 
		Interval ab = new Interval(1.0, 3.0);
		Interval xy = new Interval(2.0, 4.0);
		boolean doIntersect = ab.isIntersect(xy);
		assertTrue("case: A X B Y", doIntersect);
		assertTrue(doIntersect == xy.isIntersect(ab));
		Interval intersect = ab.intersect(xy);
		assertTrue(intersect.getLowerBound() == 2.0);
		assertTrue(intersect.getUpperBound() == 3.0);

		// case: X A Y B
		ab = new Interval(2.0, 5.0);
		xy = new Interval(1.0, 4.0);
		doIntersect = ab.isIntersect(xy);
		assertTrue("case: X A Y B", doIntersect);
		assertTrue(doIntersect == xy.isIntersect(ab));
		intersect = ab.intersect(xy);
		assertTrue(intersect.getLowerBound() == 2.0);
		assertTrue(intersect.getUpperBound() == 4.0);
	}
	
	@Test
	public void testEnclosing() {
		// case: A X Y B
		Interval ab = new Interval(1.0, 5.0);
		Interval xy = new Interval(2.0, 4.0);
		boolean doIntersect = ab.isIntersect(xy);
		assertTrue("case: A X Y B", doIntersect);
		assertTrue(doIntersect == xy.isIntersect(ab));
		Interval intersect = ab.intersect(xy);
		assertTrue(intersect.getLowerBound() == 2.0);
		assertTrue(intersect.getUpperBound() == 4.0);
		
		
		// case: X A B Y
		ab = new Interval(2.0, 3.0);
		xy = new Interval(1.0, 4.0);
		doIntersect = ab.isIntersect(xy);
		assertTrue("case: X A B Y", doIntersect);
		assertTrue(doIntersect == xy.isIntersect(ab));
		intersect = ab.intersect(xy);
		assertTrue(intersect.getLowerBound() == 2.0);
		assertTrue(intersect.getUpperBound() == 3.0);
	}
	
	@Test
	public void testDisjoint() {
		// case: A B X Y
		Interval ab = new Interval(1.0, 2.0);
		Interval xy = new Interval(3.0, 4.0);
		boolean doIntersect = ab.isIntersect(xy);
		assertTrue("case: A B X Y", !doIntersect);
		assertTrue(doIntersect == xy.isIntersect(ab));
		Interval intersect = ab.intersect(xy);
		assertTrue(Interval.isEmpty(intersect));
		Interval disjoint = ab.disjoint(xy);
		assertTrue(disjoint.getLowerBound() == 2.0);
		assertTrue(disjoint.getUpperBound() == 3.0);
		
		// case: X Y A B
		ab = new Interval(4.0, 5.0);
		xy = new Interval(1.0, 2.0);
		doIntersect = ab.isIntersect(xy);
		assertTrue("case: X Y A B", !doIntersect);
		assertTrue(doIntersect == xy.isIntersect(ab));
		intersect = ab.intersect(xy);
		assertTrue(Interval.isEmpty(intersect));
		disjoint = ab.disjoint(xy);
		assertTrue(disjoint.getLowerBound() == 2.0);
		assertTrue(disjoint.getUpperBound() == 4.0);
		
	}
	
}
