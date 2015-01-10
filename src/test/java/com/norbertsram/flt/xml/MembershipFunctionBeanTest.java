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

import com.norbertsram.flt.xml.MembershipFunctionBean;


/**
 * Unit test for Triangular memberShip function.
 */

public class MembershipFunctionBeanTest
{

	private final String MEMBERSHIP_FUNCTION_XML = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><membershipFunction name=\"trimf\"><parameters><parameter>2.0</parameter><parameter>3.0</parameter></parameters></membershipFunction>";
	
	@Test        
	public void testMarshaling() {
		MembershipFunctionBean mf = new MembershipFunctionBean();
		mf.setName("trimf");
		List<Double> parameters = new ArrayList<Double>();
		parameters.add(2.0);
		parameters.add(3.0);
		mf.setParameters(parameters);

//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		<?xml version="1.0" encoding="UTF-8" standalone="yes"?>"
//		<membershipFunction name="trimf">
//		    <parameters>
//		        <parameter>2.0</parameter>
//		        <parameter>3.0</parameter>
//		    </parameters>
//		</membershipFunction>
		
		try {
			JAXBContext context = JAXBContext.newInstance(MembershipFunctionBean.class);
			Marshaller marshaller = context.createMarshaller();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			marshaller.marshal(mf, output);
			String marshalled = output.toString();
			assertTrue(MEMBERSHIP_FUNCTION_XML.equals(marshalled));
		} catch (JAXBException ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
    }
	
	@Test
	public void testUnmarshaling() {
		try {
			JAXBContext context = JAXBContext.newInstance(MembershipFunctionBean.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Reader input = new StringReader(MEMBERSHIP_FUNCTION_XML);
			Object object = unmarshaller.unmarshal(input);
			boolean isCorrectMf = false;
			if (object instanceof MembershipFunctionBean) {
				MembershipFunctionBean mf = (MembershipFunctionBean) object;
				String name = mf.getName();
				isCorrectMf = "trimf".equals(name);
				List<Double> parameters = mf.getParameters();
				isCorrectMf &= parameters.size() == 2;
				isCorrectMf &= parameters.get(0) == 2.0;
				isCorrectMf &= parameters.get(1) == 3.0;
			}
			assertTrue(isCorrectMf);
		} catch (JAXBException ex) {
			ex.printStackTrace();
			assertTrue(false);
		}
	}
    
}
