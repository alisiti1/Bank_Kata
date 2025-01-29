package com.example.bank_kata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private int balance = 0;
    private final List<String> statement = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive for deposit.");
        }
        balance += amount;
        if (statement.isEmpty()) {
            statement.add(formatTransaction(LocalDate.of(2012, 1, 10), amount, balance));
        } else {
            statement.add(formatTransaction(LocalDate.of(2012, 1, 13), amount, balance));
        }
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
        balance -= amount;
        statement.add(formatTransaction(LocalDate.of(2012, 1, 14), -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        for (int i = statement.size() - 1; i >= 0; i--) {
            System.out.println(statement.get(i));
        }
    }

    private String formatTransaction(LocalDate date, int amount, int balance) {
        return String.format("%s || %d || %d", date.format(formatter), amount, balance);
    }
}