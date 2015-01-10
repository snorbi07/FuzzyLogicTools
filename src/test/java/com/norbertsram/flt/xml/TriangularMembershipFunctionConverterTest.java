package com.norbertsram.flt.xml;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.norbertsram.flt.mf.TriangularMembershipFunction;
import com.norbertsram.flt.xml.MembershipFunctionBean;
import com.norbertsram.flt.xml.TriangularMembershipFunctionConverter;

public class TriangularMembershipFunctionConverterTest {

	@Test
	public void testToBean() {
    	TriangularMembershipFunction mf = new TriangularMembershipFunction(3.0, 6.0, 8.0);
    	MembershipFunctionBean mfb = TriangularMembershipFunctionConverter.INSTANCE.toBean(mf);
    	
    	String name = mfb.getName();
    	boolean isValid = TriangularMembershipFunctionConverter.NAME.equals(name);
    	
    	List<Double> parameters = mfb.getParameters();
    	isValid &= parameters.size() == TriangularMembershipFunctionConverter.NUMBER_OF_TRIMF_PARAMETERS;
    	isValid &= parameters.get(0) == 3.0;
    	isValid &= parameters.get(1) == 6.0;
    	isValid &= parameters.get(2) == 8.0;
    	
    	assertTrue(isValid);
	}

	@Test
	public void testToMembershipFunction() {
		MembershipFunctionBean mfb = new MembershipFunctionBean();
		mfb.setName(TriangularMembershipFunctionConverter.NAME);
		List<Double> parameters = Arrays.asList(3.0, 6.0, 8.0);
		mfb.setParameters(parameters);
		
		TriangularMembershipFunction membershipFunction = TriangularMembershipFunctionConverter.INSTANCE.toMembershipFunction(mfb);
		
		double a = membershipFunction.getA();
		double b = membershipFunction.getB();
		double c = membershipFunction.getC();
		
		boolean isValid = a == 3.0 && b == 6.0 && c == 8.0;
		
		assertTrue(isValid);
	}

	
}
