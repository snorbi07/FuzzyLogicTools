package com.norbertsram.flt.mf;

import com.norbertsram.flt.mf.MembershipFunction;

public enum IdentityMembershipFunction implements MembershipFunction {
	INSTANCE;

	public Double valueFor(Double input) {
		return input;
	}

}
