package com.norbertsram.flt.mf;

import static com.norbertsram.flt.common.Constant.EPSILON;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Triangular memberShip function.
 */

public class TrapezoidalMembershipFunctionTest
{

	@Test        
    public void testCalculation()
    {        
    	MembershipFunction mf = new TrapezoidalMembershipFunction(3.0, 6.0, 8.0, 10.0);
    	
    	double val = mf.valueFor(0.0000);    	
    	assertTrue(val == 0.0000);
    	
    	val = mf.valueFor(3.2000);
    	assertTrue(Math.abs(val - 0.06666) < EPSILON);

    	val = mf.valueFor(4.9000);
    	assertTrue(Math.abs(val - 0.63333) < EPSILON);
    	
    	val = mf.valueFor(6.5000);
    	assertTrue(Math.abs(val - 1.00000) < EPSILON);
    	
    	val = mf.valueFor(7.4000);
    	assertTrue(Math.abs(val - 1.00000) < EPSILON);

    	val = mf.valueFor(8.5000);
    	assertTrue(Math.abs(val - 0.75000) < EPSILON);

    	val = mf.valueFor(9.9000);
    	assertTrue(Math.abs(val - 0.05000) < EPSILON);    	

    	val = mf.valueFor(10.500);
    	assertTrue(val == 0.0000);    	
    }
    
}
