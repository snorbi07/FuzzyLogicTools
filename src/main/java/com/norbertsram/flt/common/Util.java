package com.norbertsram.flt.common;

public class Util {

	public static double round(double value) {
		final double NUMBER_OF_DIGITS = 1E5;
		return (double) Math.round(value * NUMBER_OF_DIGITS) / NUMBER_OF_DIGITS;
	}

}
