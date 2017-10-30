package com.socgen.excercise.kata.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Farah
 *
 * The account which aggregates the deposit and withdrawal transactions
 *
 */
public class Account implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8825235567370585194L;
	
	/**
	 * RIB : Functional ID
	 */
	private String rib; 
	
	/**
	 * Transactions which were committed by the account.
	 */
	private List<Transaction> transactions = new ArrayList<>();

	/**
	 * Accessor of transactions
	 * 
	 * @return the transaction
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Mutator of transactions
	 * 
	 * @param transactions
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * Accessor of rib
	 * 
	 * @return the rib
	 */
	public String getRib() {
		return rib;
	}

	/**
	 * Mutator of rib
	 * 
	 * @param rib
	 */
	public void setRib(String rib) {
		this.rib = rib;
	}

	
	
}
