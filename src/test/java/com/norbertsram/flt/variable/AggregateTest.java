package com.norbertsram.flt.variable;

import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.norbertsram.flt.mf.TriangularMembershipFunction;
import com.norbertsram.flt.operator.Min;
import com.norbertsram.flt.operator.Operator;

public class AggregateTest {
	
	@Test
	public void testSingleConsequent() {
		Domain domain = new Domain(0.0, 10.0);
		Operator aggregationMethod = Min.INSTANCE;
		List<FuzzySet> consequents = Lists.newArrayList();
		Consequent consequent1 = new Consequent(
				new Set("consequent1", new TriangularMembershipFunction(0.0, 3.0, 5.0)), 
				Min.INSTANCE, 
				0.8
				);
		consequents.add(consequent1);
		
 		Aggregate aggregate = new Aggregate(aggregationMethod, domain, consequents);
		
		double result = aggregate.valueFor(3.0);
		assertTrue(result == 0.8);
		
		result = aggregate.valueFor(0.0);
		assertTrue(result == 0.0);

		result = aggregate.valueFor(4.0);
		assertTrue(result == 0.5);
	}

	@Test
	public void testMultipleConsequent() {
		Domain domain = new Domain(0.0, 10.0);
		Operator aggregationMethod = Min.INSTANCE;
		List<FuzzySet> consequents = Lists.newArrayList();
		
		Consequent consequent1 = new Consequent(
				new Set("consequent1", new TriangularMembershipFunction(0.0, 3.0, 5.0)), 
				Min.INSTANCE, 
				0.8
				);
		consequents.add(consequent1);
		
		Consequent consequent2 = new Consequent(
				new Set("consequent2", new TriangularMembershipFunction(1.0, 1.5, 4.5)), 
				Min.INSTANCE, 
				0.6
				);
		consequents.add(consequent2);	
		
		Consequent consequent3 = new Consequent(
				new Set("consequent1", new TriangularMembershipFunction(1.5, 5.0, 9.0)), 
				Min.INSTANCE, 
				0.9
				);
		consequents.add(consequent3);
		
		Aggregate aggregate = new Aggregate(aggregationMethod, domain, consequents);
		
		
		DecimalFormat decimalForm = new DecimalFormat("#.####");
		
		double result = aggregate.valueFor(2.0);
		double roundedVal = Double.valueOf(decimalForm.format(result));
		assertTrue(roundedVal == 0.1429);
		
		result = aggregate.valueFor(0.0);
		roundedVal = Double.valueOf(decimalForm.format(result));
		assertTrue(roundedVal == 0.0);
		
		result = aggregate.valueFor(4.0);
		roundedVal = Double.valueOf(decimalForm.format(result));
		assertTrue(roundedVal == 0.1667);
	}
	
}
