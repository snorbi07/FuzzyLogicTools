package com.norbertsram.flt.xml;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.norbertsram.flt.mf.TrapezoidalMembershipFunction;

public class TrapezoidalMembershipFunctionConverterTest {

	@Test
	public void testToBean() {
    	TrapezoidalMembershipFunction mf = new TrapezoidalMembershipFunction(3.0, 6.0, 8.0, 10.0);
    	MembershipFunctionBean mfb = TrapezoidalMembershipFunctionConverter.INSTANCE.toBean(mf);
    	
    	String name = mfb.getName();
    	boolean isValid = TrapezoidalMembershipFunctionConverter.NAME.equals(name);
    	
    	List<Double> parameters = mfb.getParameters();
    	isValid &= parameters.size() == TrapezoidalMembershipFunctionConverter.NUMBER_OF_TRAPMF_PARAMETERS;
    	isValid &= parameters.get(0) == 3.0;
    	isValid &= parameters.get(1) == 6.0;
    	isValid &= parameters.get(2) == 8.0;
    	isValid &= parameters.get(3) == 10.0;
    	
    	assertTrue(isValid);
	}

	@Test
	public void testToMembershipFunction() {
		MembershipFunctionBean mfb = new MembershipFunctionBean();
		mfb.setName(TrapezoidalMembershipFunctionConverter.NAME);
		List<Double> parameters = Arrays.asList(3.0, 6.0, 8.0, 10.0);
		mfb.setParameters(parameters);
		
		TrapezoidalMembershipFunction membershipFunction = TrapezoidalMembershipFunctionConverter.INSTANCE.toMembershipFunction(mfb);
		
		double a = membershipFunction.getA();
		double b = membershipFunction.getB();
		double c = membershipFunction.getC();
		double d = membershipFunction.getD();
		
		boolean isValid = a == 3.0 && b == 6.0 && c == 8.0 && d == 10.0;
		
		assertTrue(isValid);
	}

	
}
