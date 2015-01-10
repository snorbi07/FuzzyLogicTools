package com.norbertsram.flt.variable;


import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.primitives.Doubles;
import com.norbertsram.flt.common.Constant;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public final class Domain implements Iterable<Double> {
	static public final double DEFAULT_STEP = Constant.EPSILON;
	
	private final double from;
	private final double to;
	private final double step;
	
	public Domain(double start, double end, double step) {
		from = start;
		to = end;
		this.step = step;
		
		boolean isSmaller = Doubles.compare(from, to) < 0;
		checkArgument(isSmaller, "Upper bound must be greater then lower bound!");		
		
		boolean isStepValid = Doubles.compare(step, 0.0) > 0;
		checkArgument(isStepValid, "Step must be greater then 0!");
	}
	
	public Domain(double start, double end) {
		this(start, end, DEFAULT_STEP);
	}

	public boolean contains(double value) {		
		boolean doesContain = 
				Doubles.compare(from, value) <= 0 && 
				Doubles.compare(to, value) >= 0; 
		return doesContain;			
	}

	public double getFrom() {
		return from;
	}

	public double getTo() {
		return to;
	}
	
	public double getStep() {
		return step;
	}
	
	public int size() {
		double size = (to - from) / step;
		
		return (int) size;
	}

	@Override
	public Iterator<Double> iterator() {
		return new DomainIterator(this);
	}
		
	@Override
	public String toString() {
		ToStringHelper stringHelper = Objects.toStringHelper(getClass());
		stringHelper.add("from", from);
		stringHelper.add("to", to);
		stringHelper.add("step", step);
	
		return stringHelper.toString();
	}
		
	public static final class DomainIterator implements Iterator<Double> {
		private final Domain domain;
		private double currentStep;
		
		private DomainIterator(Domain domain) {
			this.domain = checkNotNull(domain);
			currentStep = domain.getFrom();
		}
		
		public boolean hasNext() {
			boolean hasNext = domain.contains(currentStep);			  
			return hasNext;
		}

		public Double next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			double value = currentStep;			
			currentStep += domain.getStep();
			return value;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
