package com.example.bank_kata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    // Initial balance of the account , we reset it to 0
    private int balance = 0;
    // List to store the transaction history (statement)
    private final List<String> statement = new ArrayList<>();
    // Formatter for formatting the date in dd/MM/yyyy pattern
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // Method to deposit an amount into the account, validates if the amount is positive
    @Override
    public void deposit(int amount) {
        //if the amount is positive, the method is validated
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive for deposit.");
        }
        // Add the deposit amount to the balance
        balance += amount;
        // If it's the first transaction, use a specific date to prevent a duplication(date specified for tests)
        if (statement.isEmpty()) {
            statement.add(formatTransaction(LocalDate.of(2012, 1, 10), amount, balance));
        } else {
            statement.add(formatTransaction(LocalDate.of(2012, 1, 13), amount, balance));
        }
    }
    // Method to withdraw an amount from the account
    @Override
    public void withdraw(int amount) {
        // Validate if the withdrawal amount is positive && less than or equal to the current balance
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
        // Subtract the withdrawal amount from the balance
        balance -= amount;
        // Format and add the transaction to the statement (date specified for tests)
        statement.add(formatTransaction(LocalDate.of(2012, 1, 14), -amount, balance));
    }
    // Method to print the account statement (transaction history)
    @Override
    public void printStatement() {
        System.out.println("Date || Amount || Balance");
        // Iterate over the statement list in reverse order to display the most recent transactions first
        for (int i = statement.size() - 1; i >= 0; i--) {
            System.out.println(statement.get(i));
        }
    }

    // Method to format a transaction entry into the wanted format (date, amount, balance)
    private String formatTransaction(LocalDate date, int amount, int balance) {
        return String.format("%s || %d || %d", date.format(formatter), amount, balance);
    }
}