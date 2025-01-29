package com.example.bank_kata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // Creating account utilising the interface AccountService
        AccountService account = new Account();

        // Deposit a balance of 1000 into the account
        account.deposit(1000);
        // Deposit a balance of 2000 into the account
        account.deposit(2000);
        // Withdraw a balance of 500 from the account
        account.withdraw(500);
        // Display the transaction history of the account in certain dates
        account.printStatement();
    }
}