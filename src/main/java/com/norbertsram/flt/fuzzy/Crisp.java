package com.norbertsram.flt.fuzzy;

public class Crisp {

	private final double value;

	public static Crisp valueOf(double crispValue) {
		return new Crisp(crispValue);
	}
	
	public Crisp(double value) {
		super();
		this.value = value;
	}

	public double getValue() {
		return value;
	}
	
}
