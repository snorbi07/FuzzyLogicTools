package com.norbertsram.flt.fuzzy;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.norbertsram.flt.common.Context;
import com.norbertsram.flt.common.ImmutableContext;
import com.norbertsram.flt.defuzzification.Defuzzify;
import com.norbertsram.flt.operator.Operator;
import com.norbertsram.flt.rule.Rule;
import com.norbertsram.flt.variable.Aggregate;
import com.norbertsram.flt.variable.Domain;
import com.norbertsram.flt.variable.FuzzySet;
import com.norbertsram.flt.variable.Variable;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class Engine {
	
	private final Fuzzy fuzzy;
	private final Collection<Rule> rules;
	
	public Engine(Fuzzy fuzzy, Collection<Rule> rules) {
		this.fuzzy = checkNotNull(fuzzy);
		this.rules = Collections.unmodifiableCollection(checkNotNull(rules));
		
		checkArgument(!rules.isEmpty(), "Must specify rules!");
	}
	
	public Context<Crisp> evaluate(Context<Crisp> crispInputs) {
		Context<Crisp> crispValues = checkNotNull(crispInputs);		
		
		Collection<Context<FuzzySet>> ruleResults = evaluateRules(crispValues);		
		
		Context<Aggregate> aggregates = aggregateResults(ruleResults);
		
//		Context<List<Double>> aggregatedSets = applyAggregations(aggregateResults);
		
		Context<Crisp> defuzzifyAggregates = defuzzifyAggregates(aggregates);
		
		return defuzzifyAggregates;		
	}
		
	private Collection<Context<FuzzySet>> evaluateRules(final Context<Crisp> crispValues) {
		Collection<Context<FuzzySet>> ruleResults = 
			Collections2.transform(rules, new Function<Rule, Context<FuzzySet>>() {
	
			public Context<FuzzySet> apply(Rule rule) {				
				return rule.evaluate(crispValues);			
			}
			
		});
		
		return ruleResults;
	}
	
	private Context<Aggregate> aggregateResults(Collection<Context<FuzzySet>> ruleResults) {		
		Context<Aggregate> results = ImmutableContext.newContext();
		Set<String> outputs = getOutputNames(ruleResults);
		Operator aggregationMethod = fuzzy.getAggregationMethod();
		
		for (String outputName : outputs) {
			Domain variableDomain = getVariableDomain(outputName);
			Collection<FuzzySet> valuesForVariable = getValuesForVariable(outputName, ruleResults);
			Aggregate aggregate = new Aggregate(aggregationMethod, variableDomain, valuesForVariable);
			results = results.put(outputName, aggregate);			
		}
	
		return results;
	}

	private Domain getVariableDomain(String variableName) {
		Context<Variable> outputs = fuzzy.getOutputs();
		Variable variable = outputs.get(variableName);
		Domain domain = variable.getDomain();
		return domain;
	}
	
	private Collection<FuzzySet> getValuesForVariable(String variable, Collection<Context<FuzzySet>> rulesResults) {
		checkNotNull(variable);
		
		List<FuzzySet> values = Lists.newArrayListWithExpectedSize(rulesResults.size());
		
		for (Context<FuzzySet> result : rulesResults) {
			FuzzySet fuzzySet = result.get(variable);
			values.add(fuzzySet);
		}
		
		return values;
	}
	
	private Set<String> getOutputNames(Collection<Context<FuzzySet>> rulesResults) {
		Set<String> names = Sets.newHashSet();
		
		for (Context<FuzzySet> rulesResult : rulesResults) {
			Collection<String> identifiers = rulesResult.getIdentifiers();
			names.addAll(identifiers);
		}
			 
		return names;		
	}
//	
//	private Context<List<Double>> applyAggregations(Context<Aggregate> aggregates) {
//		Collection<String> variables = aggregates.getIdentifiers();
//		Context<List<Double>> aggregatedSets = ImmutableContext.newContext();
//		
//		for(String variable : variables) {
//			Aggregate aggregate = aggregates.get(variable);			
//			List<Double> aggregatedSet = applyAggregation(variable, aggregate);
//			aggregatedSets = aggregatedSets.put(variable, aggregatedSet);
//		}
//		
//		return aggregatedSets;		
//	}
//	
	private Crisp defuzzifyAggregate(Aggregate aggregate) {
		Defuzzify defuzzificationMethod = fuzzy.getDefuzzificationMethod();
		double defuzzifiedValue = defuzzificationMethod.apply(aggregate);
		return Crisp.valueOf(defuzzifiedValue);
	}
	
	private Context<Crisp> defuzzifyAggregates(Context<Aggregate> aggregates) {
		Collection<String> identifiers = aggregates.getIdentifiers();
		
		Context<Crisp> results = ImmutableContext.newContext();
		for (String variableName : identifiers) {
			Aggregate aggregate = aggregates.get(variableName);			
			Crisp defuzzifiedValue = defuzzifyAggregate(aggregate);
			results = results.put(variableName, defuzzifiedValue);			
		}
		
		return results;
	}
	
}
