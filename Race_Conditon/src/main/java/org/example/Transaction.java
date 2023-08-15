package org.example;

public record Transaction(Account source, Account destination, long amount) implements Runnable {
    private void reduce(){
        synchronized (source){
            source.setBalance(source.getBalance()-amount);
        }
    }
    private void add(){
        synchronized (destination){
            destination.setBalance(destination.getBalance()+amount);
        }
    }
    @Override
    public void run() {
        reduce();
        add();
        System.out.println(Thread.currentThread());
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
