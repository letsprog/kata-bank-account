package com.socgen.excercise.kata.bank.process.api;

import com.socgen.excercise.kata.bank.process.api.dto.BankAccountOperationsDTO;

/**
 * @author Farah
 *
 * This is the API which declare the operations which can be done by an account
 */
public interface IBankAccountOperations {
	
	/**
	 * User story  1 :
	 * 
	 * This method is used to make a deposit in the given account.
	 * 
	 * @param bankAccountOperationsDTO This DTO must have these data set : the account with its RIB and an unsigned amount.
	 * 
	 */
	void deposit(BankAccountOperationsDTO bankAccountOperationsDTO);

	/**
	 *
	 * User story  2 :
	 * 
	 * This method is used to make a withdrawal from the given account.
	 * 
	 * @param bankAccountOperationsDTO  This DTO must have these data set : the account with its RIB and an unsigned amount.
	 * 
	 */
	void withdraw(BankAccountOperationsDTO bankAccountOperationsDTO);
	
	/**
	 * 
	 * User story  3 :
	 * 
	 * This method is used to generate the statements history. As a result the statements collection will be generated.
	 * 
	 * @param bankAccountOperationsDTO This DTO must have these data set : the account with its RIB and its transactions.
	 */
	void generateHistory(BankAccountOperationsDTO bankAccountOperationsDTO);
	
}
