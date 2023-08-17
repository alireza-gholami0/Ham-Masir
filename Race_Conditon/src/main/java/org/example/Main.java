package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        // Create a CSVReader object to read the file
        CSVReader reader = new CSVReader(new FileReader("Accounts.csv"));
        List<Account> accounts = new ArrayList<>();
        // Declare a string array to store each record of the CSV file
        String[] record = null;
        // Create an Account object with the id, name, and balance fields
        while ((record = reader.readNext()) != null) {
            record[2] = record[2].replaceAll(",","");
            Account account = new Account(Integer.parseInt(record[0]),record[1],Long.parseLong(record[2]));
            accounts.add(account);
        }
        // Sort the list of accounts by their id in ascending order
        accounts.sort((x,y)-> (Integer.compare(x.getID(), y.getID())));
        reader = new CSVReader(new FileReader("Transactions.csv"));
        // Create a ThreadPool object with 10 threads to execute the transactions
        ThreadPool threadPool = new ThreadPool(10);
        // Create a Transaction object with the source, destination, and amount fields
        while ((record = reader.readNext()) != null){
            record[2] = record[2].replaceAll(",","");
            threadPool.execute(new Transaction(accounts.get(Integer.parseInt(record[0])-1),accounts.get(Integer.parseInt(record[1])-1),Long.parseLong(record[2])));
        }
        // Shutdown the thread pool and wait for all tasks to finish
        threadPool.shutdown();
        // Print the final state of each account
        accounts.forEach(x-> System.out.println(x.toString()));
    }
}
