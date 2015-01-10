package com.norbertsram.flt.variable;

import static org.junit.Assert.assertTrue;

import java.util.List;


import org.junit.Test;

import com.google.common.collect.Lists;
import com.norbertsram.flt.mf.TriangularMembershipFunction;
import com.norbertsram.flt.variable.Domain;
import com.norbertsram.flt.variable.Set;
import com.norbertsram.flt.variable.Variable;

public class VariableTest {
	
	@Test
	public void testCreation() {
		Domain variableDomain = new Domain(0.0, 1.0);
		Set mf1 = new Set("mf1", 
				new TriangularMembershipFunction(0.4, 0.0, -0.4));
		Set mf2 = new Set("mf2",
				new TriangularMembershipFunction(0.1, 0.5, 0.9));
		Set mf3 = new Set("mf3",
				new TriangularMembershipFunction(0.6, 1.0, 1.4));

		List<Set> sets = Lists.newArrayList();
		sets.add(mf1);
		sets.add(mf2);
		sets.add(mf3);
		
		boolean variableIsCreated = true;
		try {
			Variable var = new Variable("input1", variableDomain, sets);
			assert var != null;
		}
		catch (NullPointerException npe) {
			variableIsCreated = false;
		}
		catch (IllegalArgumentException e) {
			variableIsCreated = false;
		}
		assertTrue(variableIsCreated);
		
		boolean isIllegalVariable = false;
		try {
			Variable invalidVariable = new Variable(null, null, sets);
			assert invalidVariable != null;
		}
		catch (NullPointerException npe) {
			isIllegalVariable = true;
		}
		assertTrue(isIllegalVariable);
		
		Set dupliacteMf = 
				new Set("mf1", 
						new TriangularMembershipFunction(0.4, 0.0, -0.4));
		sets.add(dupliacteMf);
		isIllegalVariable = false;
		try {
			Variable invalidVariable = new Variable("input1", variableDomain, sets);
			assert invalidVariable != null;
		}
		catch (IllegalArgumentException e) {
			isIllegalVariable = true;
		}
		assertTrue(isIllegalVariable);		
	}
	
	

}
