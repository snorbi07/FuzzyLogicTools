package com.norbertsram.flt.xml;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "membershipFunction")
class MembershipFunctionBean {
	
	private String name;
	
	private List<Double> parameters;

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getParameters() {
		return parameters;
	}

	@XmlElementWrapper(name = "parameters")
	@XmlElement(name = "parameter")
	public void setParameters(List<Double> parameters) {
		this.parameters = parameters;
	}
	
	public static MembershipFunctionBean valueOf(String xml) {
		MembershipFunctionBean mfb = null;

		try {
			JAXBContext context = JAXBContext.newInstance(MembershipFunctionBean.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Reader input = new StringReader(xml);
			Object object = unmarshaller.unmarshal(input);
			assert object instanceof MembershipFunctionBean;
			mfb = (MembershipFunctionBean) object;
		} 
		catch (JAXBException ex) {
			throw new IllegalArgumentException("Invalid membership function definition!", ex);
		}
		
		return mfb;
	}
	
}
