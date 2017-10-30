package com.socgen.excercise.kata.bank.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Farah
 *
 *	<p>This POJO purpose is to generate a statement from a transaction by calculating the balance.</p>
 */
public class Statement implements Serializable {
	
	/**
	 *  Serial Version UID
	 */
	private static final long serialVersionUID = 7094112549660153915L;
	
	/**
	 * The transaction
	 */
	private Transaction transaction;
	
	/**
	 * The balance to be calculated
	 */
	private BigDecimal balance;
	
	/**
	 * Accessor of transaction
	 * 
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	
	/**
	 * Mutator of transaction
	 * 
	 * @param transaction
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	/**
	 * Accessor of balance
	 * 
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	
	/**
	 * Mutator of balance
	 * 
	 * @param balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
