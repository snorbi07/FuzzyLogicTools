package com.norbertsram.flt.rule;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.norbertsram.flt.common.Context;
import com.norbertsram.flt.common.ImmutableContext;
import com.norbertsram.flt.fuzzy.Crisp;
import com.norbertsram.flt.fuzzy.Fuzzy;
import com.norbertsram.flt.operator.Operator;
import com.norbertsram.flt.variable.Consequent;
import com.norbertsram.flt.variable.FuzzySet;
import com.norbertsram.flt.variable.State;

public class Rule {
	private static final double DEFAULT_RULE_WEIGHT = 1.0;

	private final Fuzzy fuzzy;
	private final Context<State> inputVariables;
	private final Context<State> outputVariables;
	private final ConnectionType connectionType;
	private final double weight;

	private final Operator fuzzyOperation;

	public Rule(Fuzzy fuzzy, Context<State> inputVariables, ConnectionType ct,
			Context<State> outputVariables, double weight) {
		this.fuzzy = checkNotNull(fuzzy);
		this.inputVariables = checkNotNull(inputVariables);
		this.outputVariables = checkNotNull(outputVariables);
		this.connectionType = checkNotNull(ct);
		checkArgument(weight < 0.0, "Weight must be greater then 0.0!");
		this.weight = weight;
		this.fuzzyOperation = getFuzzyOperationMethod();
	}

	public Rule(Fuzzy fuzzy, Context<State> inputVariables, ConnectionType ct,
			Context<State> outputVariables) {
		this(fuzzy, inputVariables, ct, outputVariables, DEFAULT_RULE_WEIGHT);
	}

	private Operator getFuzzyOperationMethod() {
		Operator fuzzyOperation = null;

		if (connectionType == ConnectionType.AND) {
			fuzzyOperation = fuzzy.getAndMethod();
		} else {
			fuzzyOperation = fuzzy.getOrMethod();
		}

		return fuzzyOperation;
	}

	private Context<Double> calculateVariableValues(Context<Crisp> crispValues) {
		Collection<String> variableNames = inputVariables.getIdentifiers();
		Context<Double> results = ImmutableContext.newContext();

		for (String varName : variableNames) {
			State varState = inputVariables.get(varName);
			Crisp varCrispValue = crispValues.get(varName);
			double crispValue = varCrispValue.getValue(); 
			double value = varState.valueFor(crispValue);
			results = results.put(varName, value);
		}

		return results;
	}

	private double calculateAntecedent(Context<Double> antecedents) {
		Iterator<Double> values = antecedents.getValues().iterator();

		double antecedent = values.next();
		while (values.hasNext()) {
			double next = values.next();
			antecedent = fuzzyOperation.apply(antecedent, next);
		}

		return antecedent;
	}

	public Context<FuzzySet> evaluate(Context<Crisp> crispValues) {
		checkArgument(isValidInputContext(crispValues),
				"Required input values missing!");

		Context<Double> variableValues = calculateVariableValues(crispValues);
		double antecedent = calculateAntecedent(variableValues);

		Context<FuzzySet> consequents = ImmutableContext.newContext();
		Collection<String> outputNames = outputVariables.getIdentifiers();
		for (String output : outputNames) {
			State outputState = outputVariables.get(output);
			FuzzySet consequent = 
					new Consequent(outputState.getSet(), fuzzy.getImpliciationMethod(), antecedent);

			consequents = consequents.put(output, consequent);
		}

		return consequents;
	}

	private boolean isValidInputContext(Context<Crisp> crispValues) {
		Collection<String> inputValueNames = crispValues.getIdentifiers();
		Collection<String> inputVariableNames = inputVariables.getIdentifiers();

		boolean isValid = true;
		for (String name : inputVariableNames) {
			boolean nameFound = inputValueNames.contains(name);
			if (!nameFound) {
				isValid = false;
				break;
			}
		}

		return isValid;
	}

	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());

		stringHelper.add("fuzzy.name", fuzzy.getName());
		stringHelper.add("inputs", inputVariables);
		stringHelper.add("outputs", outputVariables);
		stringHelper.add("connectionType", connectionType);
		stringHelper.add("weight", weight);

		return stringHelper.toString();
	}

}
