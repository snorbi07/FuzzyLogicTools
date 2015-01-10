package com.norbertsram.flt.defuzzification;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.norbertsram.flt.variable.Domain;
import com.norbertsram.flt.variable.FuzzySet;

public class Centroid implements Defuzzify {

	private final Domain domain;

	public Centroid(Domain domain) {
		this.domain = Preconditions.checkNotNull(domain);
	}

	public double apply(FuzzySet set) {
		List<Double> values = getDomainValues(set);
		double area = area(values);
		checkArgument(area >= 0.0, area);

		double weightedSum = 0.0;
		for (double domainValue : domain) {
			double x = set.valueFor(domainValue);
			weightedSum += domainValue * x;
		}

		double result = weightedSum / area;

		return result;
	}

	private List<Double> getDomainValues(FuzzySet set) {
		List<Double> results = 
				Lists.newArrayListWithExpectedSize(domain.size());

		for (double value : domain) {
			double result = set.valueFor(value);
			results.add(result);
		}

		return results;
	}

	private double area(List<Double> values) {
		double area = 0.0;
		for (double value : values) {
			assert value > 0.0;
			area += value;
		}

		return area;
	}

}
