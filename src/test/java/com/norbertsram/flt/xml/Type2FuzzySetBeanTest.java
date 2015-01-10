package com.norbertsram.flt.xml;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class Type2FuzzySetBeanTest {

	private final String TYPE2_FUZZY_SET_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><type2FuzzySet name=\"intervalType2\"><membershipFunctions><membershipFunction name=\"trimf\"><parameters><parameter>2.0</parameter><parameter>3.0</parameter></parameters></membershipFunction><membershipFunction name=\"trimf\"><parameters><parameter>4.0</parameter><parameter>5.0</parameter></parameters></membershipFunction></membershipFunctions></type2FuzzySet>";

	@Test
	public void testMarshaling() {
		MembershipFunctionBean mf1 = new MembershipFunctionBean();
		mf1.setName("trimf");
		List<Double> parameters1 = new ArrayList<Double>();
		parameters1.add(2.0);
		parameters1.add(3.0);
		mf1.setParameters(parameters1);

		MembershipFunctionBean mf2 = new MembershipFunctionBean();
		mf2.setName("trimf");
		List<Double> parameters2 = new ArrayList<Double>();
		parameters2.add(4.0);
		parameters2.add(5.0);
		mf2.setParameters(parameters2);

		List<MembershipFunctionBean> membershipFunctions = new ArrayList<>(2);
		membershipFunctions.add(mf1);
		membershipFunctions.add(mf2);

		Type2FuzzySetBean fuzzySet = new Type2FuzzySetBean();
		fuzzySet.setName("intervalType2");
		fuzzySet.setMembershipFunctions(membershipFunctions);

		try {
			JAXBContext context = JAXBContext
					.newInstance(Type2FuzzySetBean.class);
			Marshaller marshaller = context.createMarshaller();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			marshaller.marshal(fuzzySet, output);
			String marshalled = output.toString();
			assertTrue(TYPE2_FUZZY_SET_XML.equals(marshalled));
		} catch (JAXBException ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testUnmarshaling() {
		try {
			JAXBContext context = JAXBContext.newInstance(Type2FuzzySetBean.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Reader input = new StringReader(TYPE2_FUZZY_SET_XML);
			Object object = unmarshaller.unmarshal(input);
			assertTrue(object instanceof Type2FuzzySetBean);
			Type2FuzzySetBean set = (Type2FuzzySetBean) object;
			String name = set.getName();
			assertTrue("intervalType2".equals(name));
			List<MembershipFunctionBean> mFs = set.getMembershipFunctions();
			assertTrue(mFs.size() == 2);

			MembershipFunctionBean lowerMf = mFs.get(0);
			List<Double> lowerMfParameters = lowerMf.getParameters();
			assertTrue(lowerMfParameters.get(0) == 2.0);
			assertTrue(lowerMfParameters.get(1) == 3.0);

			MembershipFunctionBean upperMf = mFs.get(1);
			List<Double> upperMfParameters = upperMf.getParameters();
			assertTrue(upperMfParameters.get(0) == 4.0);
			assertTrue(upperMfParameters.get(1) == 5.0);
		} 
		catch (JAXBException ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
	}

}
