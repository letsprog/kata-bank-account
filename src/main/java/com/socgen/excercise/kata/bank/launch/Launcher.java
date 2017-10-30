package com.socgen.excercise.kata.bank.launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.socgen.excercise.kata.bank.commons.KataBankAccountConstants;
import com.socgen.excercise.kata.bank.helpers.DataChecker;
import com.socgen.excercise.kata.bank.model.Account;
import com.socgen.excercise.kata.bank.model.wrapper.StatementWrapper;
import com.socgen.excercise.kata.bank.process.BankAccountOperations;
import com.socgen.excercise.kata.bank.process.api.IBankAccountOperations;
import com.socgen.excercise.kata.bank.process.api.dto.BankAccountOperationsDTO;

/**
 * @author Farah
 *
 *
 *  <h1>** Purpose ** :</h1>
 *	<p>This class works as a demonstration for deposit, withdrawal and history operations.</p>
 *
 *  <h1>** Assumptions ** :</h1>
 *	<p>This application assumes that the client management and authentication are outscope.
 *  As a result Client was excluded from the model.</p>
 *
 *  <h1>** Technical Remark ** :</h1>
 *  To simplify the example and to bypass the database, there is a hardcoded map "accountsMapByRib" with :
 *  <ul>
 *  <li>Key : RIB
 *  <li>Value : An account instance
 *  </ul>
 *  
 *  <h1>** How it works ** :</h1>
 *  <ul>
 *  <li>Step 1 : The user is invited to choose a predefined RIB.
 *  <li>Step 2 : The user must choose the operation which is one of the followings : Deposit, Withdrawal or Statements history.
 *  <li>Step 3 : If the user has chosen Deposit or Withdrawal, he is invited to set a positive amount. Else, history will be displayed.
 *  <li>Step 4 : The user is invited to choose between continuing (hence going back to Step 1) or leaving the application.
 *  </ul>
 */
public class Launcher {



	public static void main(String[] args) throws IOException {

		IBankAccountOperations bankAccountOperations = new BankAccountOperations();

		//*********** Preparing Dataset ***********//
		Map<String,Account> accountsMapByRib = new HashMap<>();

		Account c1 = new Account();
		Account c2 = new Account();

		c1.setRib("164640211118834110000184");
		c2.setRib("190780211118834110002791");

		accountsMapByRib.put(c1.getRib(), c1);
		accountsMapByRib.put(c2.getRib(), c2);

		boolean continueApp = true;


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to Bank Account Operations App.");

		while(continueApp)
		{

			//*********** Selecting the account ***********//

			String ribOption;

			do
			{
				System.out.println("Choose one of your accounts by RIB : \n"
						+ " 1) " + c1.getRib() +"\n"
						+ " 2) " + c2.getRib() );

				ribOption = br.readLine();
			}
			while(!ribOption.equals("1") && !ribOption.equals("2"));

			Account selectedAccount = accountsMapByRib.get(ribOption.equals("1") ? c1.getRib() : c2.getRib());


			// *********** Selecting the operation ***********//
			String chosenOperation;
			do
			{
				System.out.println("Choose an operation : \n"
						+ " 1) Deposit.\n"
						+ " 2) Withdraw.\n"
						+ " 3) Operations history.");

				chosenOperation = br.readLine();
			}
			while(!chosenOperation.equals("1") && !chosenOperation.equals("2") && !chosenOperation.equals("3"));


			BankAccountOperationsDTO bankAccountOperationsDTO = new BankAccountOperationsDTO();
			bankAccountOperationsDTO.setAccount(selectedAccount);

			String unsignedAmount;

			switch(chosenOperation)
			{
			case "1":
				do 
				{
					System.out.println("Type an unsigned amount : ");
					unsignedAmount = br.readLine();
				}
				while(!DataChecker.checkIsUnsignedAmount(unsignedAmount));
				
				bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal(unsignedAmount));

				bankAccountOperations.deposit(bankAccountOperationsDTO);
				break;
			case "2":
				do 
				{
					System.out.println("Type a unsigned amount : ");
					unsignedAmount = br.readLine();
				}
				while(!DataChecker.checkIsUnsignedAmount(unsignedAmount));
				
				bankAccountOperationsDTO.setUnsignedAmount(new BigDecimal(unsignedAmount));
				bankAccountOperations.withdraw(bankAccountOperationsDTO);
				break;
			case "3":
				bankAccountOperations.generateHistory(bankAccountOperationsDTO);

				// Display History to console screen
				System.out.println(KataBankAccountConstants.HISTORY_HEADER);
				bankAccountOperationsDTO.getStatements().stream()
				.forEach(s->System.out.println(new StatementWrapper(s).getLine()));
				break;
			default:
			}

			// *********** Deciding if End-of-Program ***********//

			String selectedContinueOp;

			do
			{
				System.out.println("Do you want to continue : \n"
						+ " 1) Yes.\n"
						+ " 2) No.\n");

				selectedContinueOp = br.readLine();
			}
			while(!selectedContinueOp.equals("1") && !selectedContinueOp.equals("2"));

			if(selectedContinueOp.equals("2"))
			{
				continueApp = false;
			}

		}

		System.out.println("Bye. See you soon.");
	}
}
