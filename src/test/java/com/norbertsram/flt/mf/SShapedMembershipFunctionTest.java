package com.norbertsram.flt.mf;

import static com.norbertsram.flt.common.Constant.EPSILON;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;

import org.junit.Test;

/**
 * Unit test for Triangular memberShip function.
 */

public class SShapedMembershipFunctionTest
{

	@Test        
    public void testCalculation()
    {        
    	MembershipFunction mf = new SShapedMembershipFunction(1.0, 8.0);
    	DecimalFormat decimalForm = new DecimalFormat("#.####");
    	
    	double val = mf.valueFor(0.0000);    	
    	assertTrue(val == 0.0000);
    	
    	val = mf.valueFor(2.4000);
    	assertTrue(Math.abs(val - 0.08000) < EPSILON);

    	val = mf.valueFor(4.9000);
    	double roundedVal = Double.valueOf(decimalForm.format(val));
    	boolean isEqual = 0.6078 == roundedVal; 
    	assertTrue(isEqual);
    	
    	val = mf.valueFor(7.4000);
    	roundedVal = Double.valueOf(decimalForm.format(val));
    	isEqual = 0.9853 == roundedVal; 
    	assertTrue(isEqual);

    	val = mf.valueFor(9.9000);
    	assertTrue(Math.abs(val - 1.00000) < EPSILON);    	
    }
    
}
