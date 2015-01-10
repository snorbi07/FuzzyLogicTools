package com.norbertsram.flt.variable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.primitives.Doubles;
import com.norbertsram.flt.mf.MembershipFunction;
import com.norbertsram.flt.mf.IdentityMembershipFunction;
import com.norbertsram.flt.mf.TriangularMembershipFunction;
import com.norbertsram.flt.variable.FuzzySet;
import com.norbertsram.flt.variable.Set;

public class SetTest {

	@Test
	public void testSetCreation() {
		boolean creationFailed = false;
		try {
			new Set("TestSet", null);
		}
		catch(NullPointerException e) {
			creationFailed = true;
		}
		assertTrue(creationFailed);
		
		creationFailed = false;
		try {
			new Set(null, IdentityMembershipFunction.INSTANCE);
		}
		catch(NullPointerException e) {
			creationFailed = true;
		}
		assertTrue(creationFailed);		
		
		creationFailed = false;
		try {
			new Set("TestSet", IdentityMembershipFunction.INSTANCE);
		}
		catch(NullPointerException e) {
			creationFailed = true;
		}
		assertFalse(creationFailed);
	}
	
	@Test
	public void testSetValues() {
		MembershipFunction mf = new TriangularMembershipFunction(3.0, 6.0, 8.0);
		FuzzySet set = new Set("TestSet", mf);
	
		double mfValue = mf.valueFor(0.0000);
		double setValue = set.valueFor(0.0000);		
    	assertTrue(Doubles.compare(mfValue, setValue) == 0);
    	
    	mfValue = mf.valueFor(3.2000);
    	setValue = set.valueFor(3.2000);		
    	assertTrue(Doubles.compare(mfValue, setValue) == 0);
    	
    	mfValue = mf.valueFor(9.9000);
    	setValue = set.valueFor(9.9000);		
    	assertTrue(Doubles.compare(mfValue, setValue) == 0);
	}
	
}
