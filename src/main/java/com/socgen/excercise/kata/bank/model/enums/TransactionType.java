package com.socgen.excercise.kata.bank.model.enums;

/**
 * @author Farah
 *
 *	The are 2 possible Transaction types : Deposit and Withdrawal operations
 */
public enum TransactionType {
	
	DEPOSIT("+"), WITHDRAWAL("-");
	
	private String signe;
	
	private TransactionType(String signe) 
	{
		this.signe = signe;
	}

	public String getSigne()
	{
		return signe;
	}
}
