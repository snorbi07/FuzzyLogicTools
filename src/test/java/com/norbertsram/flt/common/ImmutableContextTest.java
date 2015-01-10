/*
 * Author            : AdNovum Informatik AG
 * Version Number    : $Revision: $
 * Date of last edit : $Date: $
 */

package com.norbertsram.flt.common;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

/**
 * @author AdNovum Informatik AG
 */
public class ImmutableContextTest {

	@Test
	public void testNoSideEffect() {
		Context<String> context = ImmutableContext.newContext();
		context.put("var1", "val1");
		
		boolean notInContext = false;
		try {
			context.get("var1");
		}
		catch (IllegalArgumentException e) {
			notInContext = true;
		}
		assertTrue("Implementation contains side effects!", notInContext);
	}
	
	@Test
	public void testState() {
		Context<String> context = ImmutableContext.newContext();
		Context<String> updatedContext = context.put("var1", "val1");
		
		String val1 = updatedContext.get("var1");
		assertTrue("Retrieved value is incorrect!", val1.equals(val1));
		assertTrue("Shared state between contexts!", context != updatedContext);
	}
	
	@Test
	public void testApiSafety() {
		Context<String> context = ImmutableContext.newContext();
		Context<String> updatedContext = context.put("var1", "val1").put("var2", "val2");
		
		Collection<String> values = updatedContext.getValues();
		assertTrue(values.size() == 2);
		Iterator<String> valIterator = values.iterator();
		assertTrue("Retrieved value is incorrect!", valIterator.next().equals("val1"));
		assertTrue("Retrieved value is incorrect!", valIterator.next().equals("val2"));
		
		Collection<String> identifiers = updatedContext.getIdentifiers();
		assertTrue(identifiers.size() == 2);
		Iterator<String> varIterator = identifiers.iterator();
		assertTrue("Retrieved value is incorrect!", varIterator.next().equals("var1"));
		assertTrue("Retrieved value is incorrect!", varIterator.next().equals("var2"));
		
		boolean readOnly = false;
		try {
			values.add("Test");
		}
		catch (UnsupportedOperationException e) {
			readOnly = true;
		}
		assertTrue("Context 'value' state is writable!", readOnly);

		readOnly = false;
		try {
			identifiers.add("Test");
		}
		catch (UnsupportedOperationException e) {
			readOnly = true;
		}
		assertTrue("Context 'identifier' state is writable!", readOnly);
	}

}
