package com.socgen.excercise.kata.bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.socgen.excercise.kata.bank.model.enums.TransactionType;

/**
 * @author Farah
 *
 *	<p>A Transaction represents a deposit or a withdrawal operation.</p>
 *
 */
public class Transaction implements Serializable {
	
	/**
	 *  Serial Version UID
	 */
	private static final long serialVersionUID = -3065213724657046670L;
	
	/**
	 * A technical ID generated from a randomized UUID
	 */
	private String id;
	
	/**
	 * The amount to deposit or to withdraw without signe
	 */
	private BigDecimal unsignedAmount;
	
	/**
	 * The transaction creation time.
	 */
	private LocalDateTime createdAt;
	
	/**
	 * The transaction type : DEPOSIT or WITHDRAWAL. It is required to calculate balance, too.
	 */
	private TransactionType transactionType;
	
	/**
	 *  Accessor of id
	 *  
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Mutator of id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Accessor of transactionType
	 *  
	 * @return the transactionType
	 */
	public TransactionType getTransactionType() {
		return transactionType;
	}
	
	/**
	 * Mutator of transactionType
	 * 
	 * @param transactionType
	 */
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	/**
	 * Accessor of unsignedAmount
	 * 
	 * @return the unsignedAmount
	 */
	public BigDecimal getUnsignedAmount() {
		return unsignedAmount;
	}
	
	/**
	 * Mutator of unsignedAmount
	 * 
	 * @param unsignedAmount
	 */
	public void setUnsignedAmount(BigDecimal unsignedAmount) {
		this.unsignedAmount = unsignedAmount;
	}
	
	/**
	 * Accessor of createdAt
	 * 
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * Mutator of createdAt
	 * @param createdAt
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
