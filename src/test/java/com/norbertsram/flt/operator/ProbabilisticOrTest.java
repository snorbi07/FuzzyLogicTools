package com.norbertsram.flt.operator;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.primitives.Doubles;
import com.norbertsram.flt.operator.Operator;
import com.norbertsram.flt.operator.ProbabilisticOr;

public class ProbabilisticOrTest {

	@Test
	public void testOperator() {		
		double a = 1.0;
		double b = 2.0;
		double c = 3.0;
		
		Operator op = ProbabilisticOr.INSTANCE;
				
		double result = op.apply(a, b);
		double orResult = a + b - a * b; 
		assertTrue(Doubles.compare(result, orResult) == 0);
		
		result = op.apply(c, b);		
		orResult = c + b - c * b;
		assertTrue(Doubles.compare(result, orResult) == 0);
		
		result = op.apply(a, c);
		orResult = a + c - a * c;
		assertTrue(Doubles.compare(result, orResult) == 0);		
	}
	
}
