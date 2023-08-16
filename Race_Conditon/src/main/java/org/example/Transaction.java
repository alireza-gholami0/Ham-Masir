package org.example;

public class Transaction implements Runnable{
    private final Account source;
    private final Account destination;
    private final long amount;

    public Transaction(Account source, Account destination, long amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public void run() {
        source.reduce(amount);
        destination.add(amount);
    }

    public Account getSource() {
        return source;
    }

    public Account getDestination() {
        return destination;
    }

    public long getAmount() {
        return amount;
    }

}
