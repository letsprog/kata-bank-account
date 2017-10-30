package com.socgen.exercise.kata.bank.process;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.socgen.excercise.kata.bank.model.Account;
import com.socgen.excercise.kata.bank.model.Transaction;
import com.socgen.excercise.kata.bank.model.enums.TransactionType;
import com.socgen.excercise.kata.bank.process.BankAccountOperations;
import com.socgen.excercise.kata.bank.process.api.dto.BankAccountOperationsDTO;


/**
 * @author Farah
 * 
 * BankAccountOperations UTs
 * 
 */
public class BankAccountOperationsTest {

	/**
	 * Deposit Method Nominal Case Test
	 */
	@Test
	public void depositNominalCaseTest() {

		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Account account = new Account();
		account.setRib("164640211118834110000184");
		bankAccountOperationsDTO.setAccount(account);
		bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal("40"));

		// WHEN
		new BankAccountOperations().deposit(bankAccountOperationsDTO);

		// THEN
		Assert.assertEquals(1, bankAccountOperationsDTO.getAccount().getTransactions().size());
		Assert.assertNotNull(bankAccountOperationsDTO.getAccount().getTransactions().get(0).getId());
		Assert.assertNotNull(bankAccountOperationsDTO.getAccount().getTransactions().get(0).getCreatedAt());
		Assert.assertEquals(new BigDecimal("40"), bankAccountOperationsDTO.getAccount().getTransactions().get(0).getUnsignedAmount());
		Assert.assertEquals(TransactionType.DEPOSIT, bankAccountOperationsDTO.getAccount().getTransactions().get(0).getTransactionType());

	}

	/**
	 * Deposit Method Non Nominal Case : RIB is missing which requires throwing NullPointerException.
	 */
	@Test(expected=NullPointerException.class)
	public void depositBadArgumentsRIBMissingTest() {
		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Account account = new Account();
		bankAccountOperationsDTO.setAccount(account);
		bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal("40"));

		// WHEN
		new BankAccountOperations().deposit(bankAccountOperationsDTO);

		// THEN
		// NullPointerException is expected
	}

	/**
	 * Deposit Method Non Nominal Case : Amount is negative which requires throwing IllegalArgumentException.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void depositBadArgumentsNegativeAmountTest() {
		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Account account = new Account();
		bankAccountOperationsDTO.setAccount(account);
		account.setRib("164640211118834110000184");
		bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal("-40"));

		// WHEN
		new BankAccountOperations().deposit(bankAccountOperationsDTO);

		// THEN
		// IllegalArgumentException is expected
	}

	/**
	 * Withdraw Method Nominal Case Test
	 */
	@Test
	public void withdrawNominalCaseTest() {
		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Account account = new Account();
		account.setRib("164640211118834110000184");
		bankAccountOperationsDTO.setAccount(account);
		bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal("40"));

		// WHEN
		new BankAccountOperations().withdraw(bankAccountOperationsDTO);

		// THEN
		Assert.assertEquals(1, bankAccountOperationsDTO.getAccount().getTransactions().size());
		Assert.assertNotNull(bankAccountOperationsDTO.getAccount().getTransactions().get(0).getId());
		Assert.assertNotNull(bankAccountOperationsDTO.getAccount().getTransactions().get(0).getCreatedAt());
		Assert.assertEquals(new BigDecimal("40"), bankAccountOperationsDTO.getAccount().getTransactions().get(0).getUnsignedAmount());
		Assert.assertEquals(TransactionType.WITHDRAWAL, bankAccountOperationsDTO.getAccount().getTransactions().get(0).getTransactionType());

	}

	/**
	 * Withdraw method Non Nominal Case : RIB is missing which requires throwing NullPointerException.
	 */
	@Test(expected=NullPointerException.class)
	public void withdrawBadArgumentsRIBMissingTest() {

		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Account account = new Account();
		bankAccountOperationsDTO.setAccount(account);
		bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal("40"));

		// WHEN
		new BankAccountOperations().withdraw(bankAccountOperationsDTO);

		// THEN
		// NullPointerException is expected

	}

	/**
	 * Withdraw Method Non Nominal Case : Amount is negative which requires throwing IllegalArgumentException.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void withdrawBadArgumentsNegativeAmountTest() {

		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Account account = new Account();
		account.setRib("164640211118834110000184");
		bankAccountOperationsDTO.setAccount(account);
		bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal("-40"));

		// WHEN
		new BankAccountOperations().withdraw(bankAccountOperationsDTO);

		// THEN
		// IllegalArgumentException is expected

	}

	/**
	 * Generate History Method Nominal Case Test
	 */
	@Test
	public void generateHistoryNominalCaseTest() {

		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();
		Transaction t3 = new Transaction();

		t1.setUnsignedAmount(new BigDecimal("10"));
		t2.setUnsignedAmount(new BigDecimal("20"));
		t3.setUnsignedAmount(new BigDecimal("30"));

		t1.setTransactionType(TransactionType.DEPOSIT);
		t2.setTransactionType(TransactionType.WITHDRAWAL);
		t3.setTransactionType(TransactionType.DEPOSIT);

		t1.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 01));
		t2.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 02));
		t3.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 03));


		List<Transaction> transactions = new ArrayList<>();
		transactions.add(t1);
		transactions.add(t2);
		transactions.add(t3);

		Account account = new Account();
		account.setRib("164640211118834110000184");
		account.setTransactions(transactions);
		bankAccountOperationsDTO.setAccount(account);

		// WHEN
		new BankAccountOperations().generateHistory(bankAccountOperationsDTO);

		// THEN
		Assert.assertEquals(3, bankAccountOperationsDTO.getStatements().size());
		Assert.assertEquals(t1, bankAccountOperationsDTO.getStatements().get(0).getTransaction());
		Assert.assertEquals(t2, bankAccountOperationsDTO.getStatements().get(1).getTransaction());
		Assert.assertEquals(t3, bankAccountOperationsDTO.getStatements().get(2).getTransaction());
	}

	/**
	 * Generate History Method Nominal Case Test : The list doesn't contains transaction ordered by date. 
	 * The test must verifies that statements are ordered by dateTime for balance calculation correctness.
	 */
	@Test
	public void generateHistoryForTransactionNotOrderedByDateTest() {

		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();
		Transaction t3 = new Transaction();

		t1.setUnsignedAmount(new BigDecimal("10"));
		t2.setUnsignedAmount(new BigDecimal("20"));
		t3.setUnsignedAmount(new BigDecimal("30"));

		t1.setTransactionType(TransactionType.DEPOSIT);
		t2.setTransactionType(TransactionType.WITHDRAWAL);
		t3.setTransactionType(TransactionType.DEPOSIT);

		t1.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 03)); // 3rd transaction in time
		t2.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 02)); // 2nd transaction in time
		t3.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 01)); // 1st transaction in time


		List<Transaction> transactions = new ArrayList<>();
		transactions.add(t1);
		transactions.add(t2);
		transactions.add(t3);

		Account account = new Account();
		account.setRib("164640211118834110000184");
		account.setTransactions(transactions);
		bankAccountOperationsDTO.setAccount(account);

		// WHEN
		new BankAccountOperations().generateHistory(bankAccountOperationsDTO);

		// THEN
		Assert.assertEquals(3, bankAccountOperationsDTO.getStatements().size());
		Assert.assertEquals(t3, bankAccountOperationsDTO.getStatements().get(0).getTransaction());
		Assert.assertEquals(t2, bankAccountOperationsDTO.getStatements().get(1).getTransaction());
		Assert.assertEquals(t1, bankAccountOperationsDTO.getStatements().get(2).getTransaction());
	}

	/**
	 * Generate History method Non Nominal Case : RIB is missing which requires throwing NullPointerException.
	 */
	@Test(expected=NullPointerException.class)
	public void generateHistoryBadArgumentsTest() {

		// GIVEN
		BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();

		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();
		Transaction t3 = new Transaction();

		t1.setUnsignedAmount(new BigDecimal("10"));
		t2.setUnsignedAmount(new BigDecimal("20"));
		t3.setUnsignedAmount(new BigDecimal("30"));

		t1.setTransactionType(TransactionType.DEPOSIT);
		t2.setTransactionType(TransactionType.WITHDRAWAL);
		t3.setTransactionType(TransactionType.DEPOSIT);

		t1.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 01));
		t2.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 02));
		t3.setCreatedAt(LocalDateTime.of(2017, 10, 22, 00, 00, 03));


		List<Transaction> transactions = new ArrayList<>();
		transactions.add(t1);
		transactions.add(t2);
		transactions.add(t3);

		Account account = new Account();
		account.setTransactions(transactions);
		bankAccountOperationsDTO.setAccount(account);

		// WHEN
		new BankAccountOperations().generateHistory(bankAccountOperationsDTO);

		// THEN
		// NullPointerException is expected
	}

}
