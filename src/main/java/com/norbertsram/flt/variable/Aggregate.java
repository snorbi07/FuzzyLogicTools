package com.norbertsram.flt.variable;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.norbertsram.flt.operator.Operator;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class Aggregate implements FuzzySet {

	private final Operator aggregationMethod;
	private final Domain domain;
	private final Collection<FuzzySet> consequents;

	public Aggregate(Operator aggregationMethod, Domain domain,
			Collection<FuzzySet> consequents) {
		this.aggregationMethod = checkNotNull(aggregationMethod);
		this.domain = checkNotNull(domain);
		this.consequents = checkNotNull(consequents);

		checkArgument(!consequents.isEmpty(), "No values to aggregate!");
	}

	public Domain getDomain() {
		return domain;
	}

	private List<Double> valuesFor(double domainValue) {
		List<Double> results =
				Lists.newArrayListWithExpectedSize(consequents.size());

		for (FuzzySet fs : consequents) {
			double result = fs.valueFor(domainValue);
			results.add(result);
		}

		return results;
	}

	@Override
	public Double valueFor(Double domainValue) {
		List<Double> values = valuesFor(domainValue);

		Iterator<Double> valueIterator = values.iterator();

		double result = valueIterator.next();
		while (valueIterator.hasNext()) {
			double value = valueIterator.next();
			result = aggregationMethod.apply(result, value);
		}

		return result;
	}

}
