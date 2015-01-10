package com.norbertsram.flt.xml;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.norbertsram.flt.mf.SShapedMembershipFunction;

public class SShapedMembershipFunctionConverterTest {

	@Test
	public void testToBean() {
		SShapedMembershipFunction mf = new SShapedMembershipFunction(3.0, 6.0);
    	MembershipFunctionBean mfb = SShapedMembershipFunctionConverter.INSTANCE.toBean(mf);
    	
    	String name = mfb.getName();
    	boolean isValid = "smf".equals(name);
    	
    	List<Double> parameters = mfb.getParameters();
    	isValid &= parameters.size() == 2;
    	isValid &= parameters.get(0) == 3.0;
    	isValid &= parameters.get(1) == 6.0;
    	
    	assertTrue(isValid);
	}

	@Test
	public void testToMembershipFunction() {
		MembershipFunctionBean mfb = new MembershipFunctionBean();
		mfb.setName("smf");
		List<Double> parameters = Arrays.asList(3.0, 6.0);
		mfb.setParameters(parameters);
		SShapedMembershipFunction membershipFunction = 
				SShapedMembershipFunctionConverter.INSTANCE.toMembershipFunction(mfb);
		double a = membershipFunction.getA();
		double b = membershipFunction.getB();
		
		boolean isValid = a == 3.0 && b == 6.0;
		
		assertTrue(isValid);
	}

	
}
