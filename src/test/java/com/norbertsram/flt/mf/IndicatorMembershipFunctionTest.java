package com.norbertsram.flt.mf;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Indicator membership function.
 */

public class IndicatorMembershipFunctionTest
{

	@Test        
    public void testCalculation()
    {        
    	MembershipFunction mf = new IndicatorMembershipFunction(2.0, 4.0);
    	
    	double val = mf.valueFor(0.0000);    	
    	assertTrue(val == 0.0000);
    	
    	val = mf.valueFor(2.0000);
    	assertTrue(val == 1.0000);

    	val = mf.valueFor(3.0000);
    	assertTrue(val == 1.0000);

    	val = mf.valueFor(4.0000);
    	assertTrue(val == 1.0000);
    	
    	val = mf.valueFor(5.0000);
    	assertTrue(val == 0.0000);
    }
    
}
