package com.cbfacademy.restapiexercise.ious;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * The IOU class represents an IOU (I Owe You) entity with details such as ID, borrower, lender, amount, and date/time.
 */
public class IOU {

	/**
	 * The unique identifier for the IOU.
	 */
	private final UUID id;

	/**
	 * The name of the borrower in the IOU transaction.
	 */
	private String borrower;

	/**
	 * The name of the lender in the IOU transaction.
	 */
	private String lender;

	/**
	 * The amount of money in the IOU transaction.
	 */
	private BigDecimal amount;

	/**
	 * The date and time when the IOU was created.
	 */
	private Instant dateTime;

	/**
	 * Parameterised constructor to create an IOU with specified details.
	 *
	 * @param borrower The name of the borrower.
	 * @param lender   The name of the lender.
	 * @param amount   The amount of money in the IOU.
	 * @param dateTime The date and time when the IOU was created.
	 */
	public IOU(String borrower, String lender, BigDecimal amount, Instant dateTime) {
		this.id = UUID.randomUUID();
		this.borrower = borrower;
		this.lender = lender;
		this.amount = amount;
		this.dateTime = dateTime;
	}

	/**
	 * Get the ID of the IOU.
	 *
	 * @return The unique identifier for the IOU.
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Get the name of the borrower.
	 *
	 * @return The name of the borrower in the IOU transaction.
	 */
	public String getBorrower() {
		return this.borrower;
	}

	/**
	 * Set the name of the borrower.
	 *
	 * @param borrower The name of the borrower to set in the IOU transaction.
	 */
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	/**
	 * Get the name of the lender.
	 *
	 * @return The name of the lender in the IOU transaction.
	 */
	public String getLender() {
		return this.lender;
	}

	/**
	 * Set the name of the lender.
	 *
	 * @param lender The name of the lender to set in the IOU transaction.
	 */
	public void setLender(String lender) {
		this.lender = lender;
	}

	/**
	 * Get the amount of money in the IOU transaction.
	 *
	 * @return The amount of money in the IOU.
	 */
	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * Set the amount of money in the IOU transaction.
	 *
	 * @param amount The amount of money to set in the IOU.
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Get the date and time when the IOU was created.
	 *
	 * @return The date and time of the IOU creation.
	 */
	public Instant getDateTime() {
		return this.dateTime;
	}
}
