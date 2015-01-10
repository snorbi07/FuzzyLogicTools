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
import javax.xml.bind.annotation.XmlTransient;

import com.google.common.collect.Lists;

@XmlRootElement(name = "type2FuzzySet")
class Type2FuzzySetBean {

	private static final int NUMBER_OF_BOUNDARY_DEFINITIONS = 2; // upper bound and lower bound
	private static final int LOWER_BOUND_MEMBERSHIP_FUNCTION_INDEX = 1; 
	private static final int UPPER_BOUND_MEMBERSHIP_FUNCTION_INDEX = 0; 
	
	private String name;

	private List<MembershipFunctionBean> membershipFunctions;

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	@XmlElementWrapper(name = "membershipFunctions")
	@XmlElement(name = "membershipFunction")
	public void setMembershipFunctions(List<MembershipFunctionBean> parameters) {
		this.membershipFunctions = parameters;
	}

	public List<MembershipFunctionBean> getMembershipFunctions() {
		return membershipFunctions;
	}

	private void validateState() {
		if (membershipFunctions == null) {
			throw new IllegalStateException(
					"Membership functions are not initialized or corrupted!");
		}
		if (membershipFunctions.isEmpty()) {
			throw new IllegalStateException(
					"Missing membership function definitions!");
		}
		
		if (membershipFunctions.size() != NUMBER_OF_BOUNDARY_DEFINITIONS) {
			throw new IllegalStateException("Expected '"
					+ NUMBER_OF_BOUNDARY_DEFINITIONS
					+ "' membership function definitions, got: '"
					+ membershipFunctions.size() + "'");
		}
	}

	@XmlTransient
	public void setUpperMembershipFunctionBean(MembershipFunctionBean mfb) {
		if (membershipFunctions == null) {
			membershipFunctions = Lists.newArrayListWithExpectedSize(NUMBER_OF_BOUNDARY_DEFINITIONS);
		}
		
		membershipFunctions.set(UPPER_BOUND_MEMBERSHIP_FUNCTION_INDEX, mfb);
	}

	@XmlTransient
	public void setLowerMembershipFunctionBean(MembershipFunctionBean mfb) {
		if (membershipFunctions == null) {
			membershipFunctions = Lists.newArrayListWithExpectedSize(NUMBER_OF_BOUNDARY_DEFINITIONS);
		}
		
		membershipFunctions.set(LOWER_BOUND_MEMBERSHIP_FUNCTION_INDEX, mfb);
	}
	
	public MembershipFunctionBean getUpperMembershipFunctionBean() {
		validateState();
		return membershipFunctions.get(UPPER_BOUND_MEMBERSHIP_FUNCTION_INDEX);
	}

	public MembershipFunctionBean getLowerMembershipFunctionBean() {
		validateState();
		return membershipFunctions.get(LOWER_BOUND_MEMBERSHIP_FUNCTION_INDEX);
	}

	public static Type2FuzzySetBean valueOf(String xml) {
		Type2FuzzySetBean mfb = null;

		try {
			JAXBContext context = 
					JAXBContext.newInstance(Type2FuzzySetBean.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Reader input = new StringReader(xml);
			Object object = unmarshaller.unmarshal(input);
			if (!(object instanceof Type2FuzzySetBean)) {
				throw new IllegalArgumentException("Expected Type2FuzzySetBean, got: " + object.getClass().getSimpleName());
			}
			assert object instanceof Type2FuzzySetBean;
			mfb = (Type2FuzzySetBean) object;
		} catch (JAXBException ex) {
			throw new IllegalArgumentException("Invalid membership function definition!", ex);
		}

		return mfb;
	}

}
