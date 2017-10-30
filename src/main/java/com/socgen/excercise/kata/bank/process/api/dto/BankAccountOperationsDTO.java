package com.socgen.excercise.kata.bank.process.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.socgen.excercise.kata.bank.model.Account;
import com.socgen.excercise.kata.bank.model.Statement;

/**
 * @author Farah
 *
 *	<p>The Data Transfer Object that works as a data cluster of IBankAccountOperations API.</p>
 *
 */
public class BankAccountOperationsDTO implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2377026980374048433L;
	
	/**
	 * Account
	 */
	private Account account;
	
	/**
	 * Unsigned amount
	 */
	private BigDecimal unsignedAmount;
	
	/**
	 * List of statements
	 */
	private List<Statement> statements = new ArrayList<>(); 
	
	/**
	 * Accessor of account
	 * 
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	
	/**
	 * Mutator of account
	 * 
	 * @param account
	 */
	public void setAccount(Account account) {
		this.account = account;
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
	 * Accessor of statements
	 * 
	 * @return the statements
	 */
	public List<Statement> getStatements() {
		return statements;
	}
	
	/**
	 * Mutator of statements
	 * 
	 * @param statements
	 */
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

}
