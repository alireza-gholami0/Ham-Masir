package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new FileReader("Accounts.csv"));
        List<Account> accounts = new ArrayList<>();
        String[] record = null;
        while ((record = reader.readNext()) != null) {
            record[2] = record[2].replaceAll(",","");
            Account account = new Account(Integer.parseInt(record[0]),record[1],Long.parseLong(record[2]));
            accounts.add(account);
        }
        reader = new CSVReader(new FileReader("Transactions.csv"));
        ExecutorService executer = Executors.newFixedThreadPool(100);
        while ((record = reader.readNext()) != null){
            record[2] = record[2].replaceAll(",","");
            executer.execute(new Transaction(accounts.get(Integer.parseInt(record[0])-1),accounts.get(Integer.parseInt(record[1])-1),Long.parseLong(record[2])));
        }
        executer.shutdown();
        if (executer.isShutdown()) {
            accounts.forEach(x -> {
                System.out.println("ID:" + x.getID());
                System.out.println("Name:" + x.getAccountName());
                System.out.println("Initial Balance:" + x.getInitialBalance());
                System.out.println("ّFinal Balance:" + x.getBalance());
                System.out.println("Balance Changes:" + (x.getBalance() - x.getInitialBalance()));
                System.out.println("----------------");
            });
        }
    }
}