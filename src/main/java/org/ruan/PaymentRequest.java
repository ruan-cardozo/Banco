package org.ruan;

import java.math.BigDecimal;

public class PaymentRequest {
	private Long accountId;

	private BigDecimal value;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getValue() {
		return value;
	}

}
