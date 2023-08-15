package org.example;

public class Account {
    private int ID;
    private String accountName;
    private long balance;
    private long initialBalance;
    public Account(int ID, String accountName, long balance){
        this.ID = ID;
        this.accountName = accountName;
        this.balance = balance;
        this.initialBalance = balance;
    }

    public int getID() {
        return ID;
    }

    public String getAccountName() {
        return accountName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getInitialBalance() {
        return initialBalance;
    }
}
