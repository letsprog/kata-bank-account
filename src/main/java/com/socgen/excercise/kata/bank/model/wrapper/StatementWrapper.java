package com.socgen.excercise.kata.bank.model.wrapper;

import com.socgen.excercise.kata.bank.commons.KataBankAccountConstants;
import com.socgen.excercise.kata.bank.model.Statement;
import com.socgen.excercise.kata.bank.model.Transaction;

/**
 * @author Farah
 *
 *	This is a simple wrapper to a Statement to generate a formated line that will be displayed on the Console.
 *
 */
public class StatementWrapper extends Wrapper {
	
	/**
	 * The statement to be wrapped
	 */
	private Statement statement;
	
	public StatementWrapper(Statement statement)
	{
		this.statement = statement;
	}

	@Override
	public String getLine() {

		Transaction transaction = statement.getTransaction();
		
		return new StringBuilder()
		.append(transaction.getId())
		.append(KataBankAccountConstants.COLUMNS_SEPARATOR)
		.append(transaction.getTransactionType())
		.append(KataBankAccountConstants.COLUMNS_SEPARATOR)
		.append(transaction.getCreatedAt())
		.append(KataBankAccountConstants.COLUMNS_SEPARATOR)
		.append(transaction.getUnsignedAmount())
		.append(KataBankAccountConstants.COLUMNS_SEPARATOR)
		.append(statement.getBalance())
		.toString();		
		
	}
	
	

}
