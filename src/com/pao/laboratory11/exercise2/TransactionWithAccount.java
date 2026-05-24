package com.pao.laboratory11.exercise2;


import com.pao.laboratory11.exercise1.Transaction;

public class TransactionWithAccount extends Transaction {
    private final String accountId;

    public TransactionWithAccount(int id, double amount, String date, String country, String channel, String accountId) {

        super(id, amount, date, country, channel);
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}