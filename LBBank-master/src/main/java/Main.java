import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import components.accounts.Account;
import components.Client;
import components.flows.typeofflows.Credit;
import components.accounts.types.CurrentAccount;
import components.flows.typeofflows.Debit;
import components.flows.Flow;
import components.accounts.types.SavingsAccount;
import components.flows.typeofflows.Transfer;

public class Main {
    public static void main(String[] args) throws Exception {

//1.1.2 Creation of main class for tests

        ArrayList<Client> clientsCollection = generateClient();
        System.out.println("LIST OF CLIENTS");
        displayClients(clientsCollection);
        System.out.println();

//1.2.3 Creation of table account

        ArrayList<Account> accountsCollection = generateAccount(clientsCollection);
        System.out.println("GENERATION OF CLIENT ACCOUNTS");
        displayAccount(accountsCollection);
        System.out.println();

//1.3.1 Adaptation of the table of accounts

        Map<Integer, Account> mapAccount = generateMapAccounts(accountsCollection);
        System.out.println("GENERATION OF CLIENT ACCOUNTS WITH ADAPTATIONS");
        displayMapAccounts(mapAccount);
        System.out.println();

//1.3.4 Creation of the flow array

        ArrayList<Flow> flowsCollection = generateFlow(accountsCollection);
        System.out.println("CREATION OF FLOWS FROM MAIN");
        displayFlow(flowsCollection);
        System.out.println();

//1.3.5 Updating accounts

        System.out.println("BALANCE");
        applyFlows(flowsCollection, mapAccount);
        displayMapAccounts(mapAccount);
        System.out.println();

//2.1 Json file of flows

        System.out.println("FLOW APPLICATION FROM JSON FILE");
        ArrayList<Flow> flowsFromJson = loadFlowsFromJsonFile("resources/flows.json");
        displayFlow(flowsFromJson);
        System.out.println();

        System.out.println("AFTER APPLICATION (JSON FLOW)");
        applyFlows(flowsFromJson, mapAccount);
        checkNegativeBalances(mapAccount);
        displayMapAccounts(mapAccount);
        System.out.println();

//2.2 XML file of accounts

        System.out.println("ASSOCIATION OF CLIENT NUMBERS WITH NAMES FROM XML FILE");
        loadClientsFromXml("resources/accounts.xml", mapAccount);
        checkNegativeBalances(mapAccount);
        displayMapAccounts(mapAccount);
        System.out.println();

    }

//1.1.2 Creation of main class for tests

    private static void displayClients(ArrayList<Client> clientsCollection) {
        clientsCollection.stream().forEach(System.out::println);
    }

    private static ArrayList<Client> generateClient() {
        ArrayList<Client> clientsCollection = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            clientsCollection.add(new Client("LastName" + i, "FirstName" + i, i));
        }
        return clientsCollection;
    }

//1.2.3 Creation of table account

    private static ArrayList<Account> generateAccount(ArrayList<Client> clientsCollection) {
        ArrayList<Account> accountsCollection = new ArrayList<>();
        for (Client c : clientsCollection) {
            accountsCollection.add(new CurrentAccount(c));
            accountsCollection.add(new SavingsAccount(c));
        }
        return accountsCollection;
    }

    private static void displayAccount(ArrayList<Account> accountsCollection) {
        accountsCollection.stream().forEach(System.out::println);
    }

//1.3.1 Adaptation of the table of accounts

    public static Map<Integer, Account> generateMapAccounts(ArrayList<Account> accountsCollection) {
        Map<Integer, Account> map = new Hashtable<>();
        for (Account a : accountsCollection) {
            map.put(a.getAccountNumber(), a);
        }
        return map;
    }

    public static void displayMapAccounts(Map<Integer, Account> map) {
        map.values()
                .stream()
                .sorted(Comparator.comparingDouble(Account::getBalance))
                .forEach(System.out::println);
    }

// 1.3.4 Creation of the flow array
    private static ArrayList<Flow> generateFlow(ArrayList<Account> accountsCollection) {
        ArrayList<Flow> flowsCollection = new ArrayList<>();

        flowsCollection.add(new Debit("Debit", 50.0, 1));
        accountsCollection.stream()
                .filter(c -> c instanceof CurrentAccount)
                .forEach(c -> flowsCollection.add(new Credit("Credit on Current accounts", 100.50, c.getAccountNumber())));
        accountsCollection.stream()
                .filter(c -> c instanceof SavingsAccount)
                .forEach(c -> flowsCollection.add(new Credit("Credit on Savings accounts", 1500, c.getAccountNumber())));
        flowsCollection.add(new Transfer("Money transfer from account 1 to account 2", 50, 2, 1));

        return flowsCollection;
    }

    private static void displayFlow(ArrayList<Flow> flowsCollection) {
        flowsCollection.stream().forEach(System.out::println);
    }

// 1.3.5 Updating accounts

    public static void applyFlows(ArrayList<Flow> flowsCollection, Map<Integer, Account> accountsCollection) {
        flowsCollection.forEach(f -> accountsCollection.values().forEach(c -> c.applyFlows(f)));
    }

    public static void checkNegativeBalances(Map<Integer, Account> accountsCollection) {
        accountsCollection.values().stream()
                .filter(c -> c.getBalance() < 0)
                .forEach(c -> System.out.println("Warning: negative balance " + c));
    }


// 2.1 JSON file of flows

    public static ArrayList<Flow> loadFlowsFromJsonFile(String flows) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(flows));

        ArrayList<Flow> flowsCollection = new ArrayList<>();

        for (JsonNode node : root) {
            String type = node.get("type").asText();
            String comment = node.get("comment").asText();
            double amount = node.get("amount").asDouble();
            int target = node.get("targetAccountNumber").asInt();

            switch (type) {
                case "Credit":
                    flowsCollection.add(new Credit(comment, amount, target));
                    break;
                case "Debit":
                    flowsCollection.add(new Debit(comment, amount, target));
                    break;
                case "Transfer":
                    int issuer = node.get("issuerAccountNumber").asInt();
                    flowsCollection.add(new Transfer(comment, amount, target, issuer));
                    break;
            }
        }
        return flowsCollection;
    }

//2.2 XML file of account

    public static void loadClientsFromXml(String xmlFilePath, Map<Integer, Account> mapAccount) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
        JsonNode root = xmlMapper.readTree(xml);

        for (JsonNode node : root.withArray("account")) {
            JsonNode clientNode = node.get("client");
            if (clientNode == null) continue;

            int clientNumber = clientNode.get("clientNumber").asInt();
            String lastName = clientNode.get("lastName").asText();
            String firstName = clientNode.get("firstName").asText();

            for (Account a : mapAccount.values()) {
                if (a.getClient().getClientNumber() == clientNumber) {
                    a.getClient().setLastName(lastName);
                    a.getClient().setFirstName(firstName);
                }
            }
        }
    }
}
