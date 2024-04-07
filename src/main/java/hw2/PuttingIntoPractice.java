package hw2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PuttingIntoPractice {
    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    // Find all transactions for the year 2011 and sort them by amount
    public static List<Transaction> getTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();
    }

    //  Show a list of unique cities in which traders work.
    public static List<String> getUniqueCities(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .toList();
    }

    // Find all the traders from Cambridge and sort them by name.
    public static List<Trader> getTraders(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
    }

    //Return a string with all trader names sorted in alphabetical order.
    public static String getTradersNames(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted(String::compareTo)
                .collect(Collectors.joining(","));

    }

    // Find out if there’s any traders from Milan.
    public static boolean isTraderFromMilan(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .anyMatch(city -> city.equals("Milan"));
    }

    // Withdraw all transactions from Cambridge
    public static Map<Trader, Integer> withDrawTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(Transaction::getTrader, Collectors.summingInt(Transaction::getValue)));
    }

    //  What is the maximum amount of all transactions?
    public static int getMaxValue(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .orElse(-1);
    }

    //   What is the minimum amount of all transactions?
    public static int getMinValue(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo)
                .orElse(-1);
    }

}