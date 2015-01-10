package com.norbertsram.flt.variable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class Variable {
	private final String name;
	private final Domain domain;
	private final Collection<Set> sets;

	public Variable(String name, Domain domain, Collection<Set> sets) {
		this.name = checkNotNull(name);
		this.domain = checkNotNull(domain);

		Collection<Set> possibleSets = checkNotNull(sets);
		checkArgument(!possibleSets.isEmpty(),
				"Must specify at least 1 possible set!");
		this.sets = Collections.unmodifiableCollection(possibleSets);

		checkArgument(!doesContainDuplicateSetNames(),
				"Set names most be unique for a variable!");
	}

	public Collection<Double> valuesFor(final double crispValue) {
		Collection<Double> results = Collections2.transform(sets,
				new Function<FuzzySet, Double>() {

					public Double apply(FuzzySet arg0) {
						return arg0.valueFor(crispValue);
					}

				});

		return Collections.unmodifiableCollection(results);
	}

	public Collection<Set> getSets() {
		return sets;
	}

	public Collection<String> getSetNames() {
		Collection<String> results = Collections2.transform(sets,
				new Function<Set, String>() {

					public String apply(Set arg0) {
						return arg0.getName();
					}

				});

		return Collections.unmodifiableCollection(results);
	}

	public Set getSetForName(final String setName) {
		Collection<Set> results = Collections2.filter(sets,
				new Predicate<Set>() {

					public boolean apply(Set arg0) {
						String name = arg0.getName();
						return name.equals(setName);
					}

				});
		Preconditions.checkArgument(results.isEmpty(), "Set name not found!");

		// set names need to differ
		if (results.size() > 1) {
			throw new AssertionError("Duplicate set names!");
		}

		// Should never throw an exception
		Set result = results.iterator().next();
		return result;
	}

	private boolean doesContainDuplicateSetNames() {
		Collection<String> setNames = getSetNames();
		HashSet<String> uniqueSet = new HashSet<String>(setNames);
		List<String> uniques = new ArrayList<String>(uniqueSet);

		boolean doesContainDuplicates = uniques.size() != setNames.size();

		return doesContainDuplicates;
	}

	public String getName() {
		return name;
	}

	public Domain getDomain() {
		return domain;
	}

	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());

		stringHelper.add("name", name);
		stringHelper.add("range", domain);
		stringHelper.add("sets", sets);

		return stringHelper.toString();
	}

}
