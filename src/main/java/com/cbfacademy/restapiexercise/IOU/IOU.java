package com.cbfacademy.restapiexercise.IOU;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class IOU {

	UUID id;
	String borrower;
	String lender;
	BigDecimal amount;
	Instant dateTime;

	public IOU() {
	}

	public IOU(String borrower, String lender, BigDecimal amount, Instant dateTime) {
		this.id = UUID.randomUUID();
		this.borrower = borrower;
		this.lender = lender;
		this.amount = amount;
		this.dateTime = dateTime;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getBorrower() {
		return this.borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getLender() {
		return this.lender;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Instant getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Instant dateTime) {
		this.dateTime = dateTime;
	}
}