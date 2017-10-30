package com.socgen.excercise.kata.bank.process;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.socgen.excercise.kata.bank.model.Account;
import com.socgen.excercise.kata.bank.model.Statement;
import com.socgen.excercise.kata.bank.model.Transaction;
import com.socgen.excercise.kata.bank.model.enums.TransactionType;
import com.socgen.excercise.kata.bank.process.api.IBankAccountOperations;
import com.socgen.excercise.kata.bank.process.api.dto.BankAccountOperationsDTO;

/**
 * @author Farah
 *
 * <p>This class is the implementation of IBankAccountOperations interface.</p>
 * 
 */
public class BankAccountOperations implements IBankAccountOperations {

	final static Logger logger = Logger.getLogger(BankAccountOperations.class);
	
	@Override
	public void deposit(BankAccountOperationsDTO bankAccountOperationsDTO) {

		logger.debug("Beginning of Deposit Transaction.");
		
		Preconditions.checkNotNull(bankAccountOperationsDTO);		
		
		this.addTransaction(bankAccountOperationsDTO.getAccount(), 
				bankAccountOperationsDTO.getUnsignedAmount(), 
				TransactionType.DEPOSIT);
		
		logger.debug("End of Deposit Transaction.");
	}

	@Override
	public void withdraw(BankAccountOperationsDTO bankAccountOperationsDTO) {

		logger.debug("Beginning of Withdrawal Transaction.");
		
		Preconditions.checkNotNull(bankAccountOperationsDTO);
		
		this.addTransaction(bankAccountOperationsDTO.getAccount(), 
				bankAccountOperationsDTO.getUnsignedAmount(), 
				TransactionType.WITHDRAWAL);
		
		logger.debug("End of Withdrawal Transaction.");
	}

	@Override
	public void generateHistory(BankAccountOperationsDTO bankAccountOperationsDTO) {
		
		logger.debug("Beginning of History display.");
		
		Preconditions.checkNotNull(bankAccountOperationsDTO.getAccount(), "The account can't be null.");
		Preconditions.checkNotNull(bankAccountOperationsDTO.getAccount().getRib(), "The account RIB can't be null.");
		
		List<Transaction> transactions = bankAccountOperationsDTO.getAccount().getTransactions();
		
		// To make sure that the transaction are ordered by creation date ascendant 
		Comparator<Transaction> byTransactionDate = (t1,t2)-> t1.getCreatedAt().compareTo(t2.getCreatedAt());
		Collections.sort(transactions,byTransactionDate);
		
		List<Statement> statements = new ArrayList<>();
		
		for(int i=0 ; i<transactions.size() ; i++)
		{
			Transaction currTransaction = transactions.get(i);
			
			String transactionTypeSigne = currTransaction.getTransactionType().getSigne();
			
			Statement statement = new Statement();
			statement.setTransaction(currTransaction);
			
			BigDecimal signedAmount = currTransaction.getUnsignedAmount().multiply(new BigDecimal(transactionTypeSigne+"1"));
			
			if(i==0)
			{
				statement.setBalance(signedAmount);
			}
			else
			{
				statement.setBalance(signedAmount.add(statements.get(i-1).getBalance()));
			}
			
			statements.add(statement);
		}
		
		bankAccountOperationsDTO.setStatements(statements);
		
		logger.debug("End of History display.");
	}
	
	
	private void addTransaction(Account account, BigDecimal unsignedAmount, TransactionType transactionType)
	{
		Preconditions.checkNotNull(account, "The account can't be null.");
		Preconditions.checkNotNull(account.getRib(), "The account RIB can't be null.");
		Preconditions.checkNotNull(transactionType, "The transaction type can't be null.");
		Preconditions.checkNotNull(unsignedAmount, "The amount can't be null.");
		Preconditions.checkArgument(unsignedAmount.signum()!=-1, "The entered amount must be unsigned.");

		Transaction transaction = new Transaction();
		String transactionId = UUID.randomUUID().toString();
		transaction.setId(transactionId);
		transaction.setTransactionType(transactionType);
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setUnsignedAmount(unsignedAmount);

		account.getTransactions().add(transaction);
		
		logger.debug("Transaction of type : "+transactionType
				+" created with ID : "+transactionId
				+", for the account with RIB : "+account.getRib()
				+", with the amount "+unsignedAmount+".");
	}


}
