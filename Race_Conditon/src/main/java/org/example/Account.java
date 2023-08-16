package org.example;

public class Account {
    private int ID;
    private String accountName;
    private long balance;
    private long initialBalance;

    public Account(int ID, String accountName, long balance) {
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
    public void reduce(long amount){
        synchronized (this){
            this.setBalance(this.getBalance()-amount);
        }
    }
    public void add(long amount){
        synchronized (this){
            this.setBalance(this.getBalance()+amount);
        }
    }
    @Override
    public String toString() {
        String output = "";
        output += "ID: " + this.getID() + "\n";
        output += "Name: " + this.getAccountName() + "\n";
        output += "Initial Balance: " + this.getInitialBalance() + "\n";
        output += "Final Balance: " + this.getBalance() + "\n";
        output += "Balance Changes: " + (this.getBalance() - this.getInitialBalance()) + "\n";
        return output;
    }
}
