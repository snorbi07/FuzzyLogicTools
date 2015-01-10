package com.norbertsram.flt.fuzzy;

import com.norbertsram.flt.common.Context;
import com.norbertsram.flt.defuzzification.Defuzzify;
import com.norbertsram.flt.operator.Operator;
import com.norbertsram.flt.variable.Variable;


import static com.google.common.base.Preconditions.checkNotNull;

public class Fuzzy {

	private final String name;
	private final Context<Variable> inputs;
	private final Context<Variable> outputs;
	private final Operator andMethod;
	private final Operator orMethod;
	private final Operator impliciationMethod;
	private final Operator aggregationMethod;
	private final Defuzzify defuzzificationMethod;
	
	public Fuzzy(String name, Operator andMethod, Operator orMethod, 
			Operator impliciation, Operator aggregation, Defuzzify defuzzification,
			Context<Variable> inputs, Context<Variable> outputs) {
		this.name = checkNotNull(name);
		this.inputs = checkNotNull(inputs);
		this.outputs = checkNotNull(outputs);
		this.andMethod = checkNotNull(andMethod);
		this.orMethod = checkNotNull(orMethod);
		this.impliciationMethod = checkNotNull(impliciation);
		this.aggregationMethod = checkNotNull(aggregation);
		this.defuzzificationMethod = checkNotNull(defuzzification);
	}

	public String getName() {
		return name;
	}
	
	public Context<Variable> getInputs() {
		return inputs;
	}

	public Context<Variable> getOutputs() {
		return outputs;
	}
		
	public Operator getAndMethod() {
		return andMethod;
	}

	public Operator getOrMethod() {
		return orMethod;
	}

	public Operator getImpliciationMethod() {
		return impliciationMethod;
	}

	public Operator getAggregationMethod() {
		return aggregationMethod;
	}

	public Defuzzify getDefuzzificationMethod() {
		return defuzzificationMethod;
	}
	
}
