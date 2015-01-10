package com.norbertsram.flt.mf;

import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;

import org.junit.Test;

/**
 * Unit test for Triangular memberShip function.
 */

public class GeneralizedBellMembershipFunctionTest
{

	@Test        
    public void testCalculation()
    {        
    	MembershipFunction mf = new GeneralizedBellMembershipFunction(2.0, 2.5, 4.5);
    	DecimalFormat decimalForm = new DecimalFormat("#.####");
    	
    	double val = mf.valueFor(2.5);
    	double roundedVal = Double.valueOf(decimalForm.format(val));
    	boolean isEqual = 0.5 ==  roundedVal; 
    	assertTrue(Double.toString(roundedVal), isEqual);
    	
    	val = mf.valueFor(4.600); 
    	roundedVal = Double.valueOf(decimalForm.format(val));
    	isEqual = 1.000 == roundedVal; 
    	assertTrue(isEqual);

    	val = mf.valueFor(7.50000);
    	roundedVal = Double.valueOf(decimalForm.format(val));
    	isEqual = 0.1164 == roundedVal; 
    	assertTrue(isEqual);
    	
    	val = mf.valueFor(9.8000); 
    	roundedVal = Double.valueOf(decimalForm.format(val));
    	isEqual = 0.0076 == roundedVal; 
    	assertTrue(isEqual);
    	
    	mf = new GeneralizedBellMembershipFunction(2.0, 4.0, 6.0);

    	val = mf.valueFor(0.0000);
		roundedVal = Double.valueOf(decimalForm.format(val));
		isEqual = 2.0E-4 ==  roundedVal; 
		assertTrue(isEqual);
		
		val = mf.valueFor(2.5000); //0.0112
		roundedVal = Double.valueOf(decimalForm.format(val));
		isEqual = 0.0112 == roundedVal; 
		assertTrue(isEqual);
		
		val = mf.valueFor(5.0000); //0.9961
		roundedVal = Double.valueOf(decimalForm.format(val));
		isEqual = 0.9961 == roundedVal; 
		assertTrue(isEqual);
		
		val = mf.valueFor(6.000); //1.0
		roundedVal = Double.valueOf(decimalForm.format(val));
		isEqual = 1.0 == roundedVal; 
		assertTrue(isEqual);
		
		val = mf.valueFor(9.9000); // 0.0048
		roundedVal = Double.valueOf(decimalForm.format(val));
		isEqual = 0.0048 == roundedVal;
		assertTrue(isEqual);    	
    	
    }
    
}
