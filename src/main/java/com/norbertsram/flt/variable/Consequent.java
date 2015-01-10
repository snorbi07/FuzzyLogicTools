package com.norbertsram.flt.variable;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.norbertsram.flt.operator.Operator;

/**
 * 
 * Consequent represents the 'right side' of the fuzzy inference calculation.
 * The calculation is done lazily. 
 * 
 */
public class Consequent implements FuzzySet {
	
	private final FuzzySet set;
	private final Operator implication;
	private final double antecedent;
	
	/**
	 *  
	 * @param set Represents the 'then' part (output) of a fuzzy rule 
	 * @param implication The operator used to calculate the value to be aggregated
	 * @param antecedent The value provided by the 'left side' of the fuzzy inference (fuzzified inputs)
	 */
	public Consequent(FuzzySet set, Operator implication, double antecedent) {
		this.set = Preconditions.checkNotNull(set);
		this.implication = Preconditions.checkNotNull(implication);
		this.antecedent = antecedent;
	}

	@Override
	public Double valueFor(Double input) {
		double mfValue = set.valueFor(input);
		double result = implication.apply(mfValue, antecedent);
		return result;	
	}
	
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());
		
		stringHelper.add("set", set);
		stringHelper.add("implication", implication);
		stringHelper.add("antecedent", antecedent);
	
		return stringHelper.toString();
	}

}
