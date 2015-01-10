package com.norbertsram.flt.operator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.primitives.Doubles;
import com.norbertsram.flt.operator.Min;
import com.norbertsram.flt.operator.Operator;

public class MinTest {

	@Test
	public void testOperator() {		
		double a = 1.0;
		double b = 2.0;
		double c = 3.0;
		
		Operator op = Min.INSTANCE;
		
		double result = op.apply(a, b);		
		assertTrue(Doubles.compare(result, a) == 0);
		
		result = op.apply(c, b);		
		assertTrue(Doubles.compare(result, b) == 0);
		
		result = op.apply(a, c);		
		assertTrue(Doubles.compare(result, a) == 0);		
	}
	
}
