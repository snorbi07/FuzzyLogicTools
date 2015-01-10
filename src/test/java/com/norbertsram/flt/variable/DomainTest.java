package com.norbertsram.flt.variable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.google.common.primitives.Doubles;
import com.norbertsram.flt.variable.Domain;

public class DomainTest {

	@Test
	public void testDomainBoundsSetup() {
		boolean isRangeInvalid = false;
		try {
			new Domain(5.0, 3.0);
		}
		catch (IllegalArgumentException e){
			isRangeInvalid = true;
		}
		
		assertTrue(isRangeInvalid);
	}
	
	@Test
	public void testDomainStep() {
		boolean isStepInvalid = false;
		try {
			new Domain(1.0, 2.0, 0.0);
		}
		catch (IllegalArgumentException e){
			isStepInvalid = true;
		}
		assertTrue(isStepInvalid);
		
		isStepInvalid = false;
		try {
			new Domain(1.0, 2.0, -1.0);
		}
		catch (IllegalArgumentException e){
			isStepInvalid = true;
		}
		
		assertTrue(isStepInvalid);
	}
	
	@Test
	public void testDomainBounds() {
		double start = 0.0;
		double end = 10.0;
		Domain domain = new Domain(start, end);
		assertTrue(domain.contains(start));
		assertTrue(domain.contains(end));
		assertFalse(domain.contains(start - 1.0));
		assertFalse(domain.contains(end + 1.0));
	}
	
	@Test
	public void testDomainIteration() {
		double start = 0.0;
		double end = 10.0;
		Domain domain = new Domain(start, end);
		
		Iterator<Double> domainValues = domain.iterator();
		
		for(double i = 0.0; i <= end; i += Domain.DEFAULT_STEP) {
			assertTrue(domainValues.hasNext());			
			double currentValue = domainValues.next();
			int result = Doubles.compare(currentValue, i);
			assertTrue(result == 0);
		}
		
		assertFalse(domainValues.hasNext());
	}
	
}
