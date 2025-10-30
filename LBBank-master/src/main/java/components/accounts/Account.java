package components.accounts;

import components.*;
import components.accounts.types.CurrentAccount;
import components.flows.typeofflows.Credit;
import components.flows.typeofflows.Debit;
import components.flows.Flow;
import components.flows.typeofflows.Transfer;

// 1.2.1 Creation of Account class

public abstract class Account {

    protected String label;
    protected double balance;
    protected int accountNumber;
    protected Client client;
    protected static int accountCounter = 1;

    public Account (String label, Client client) {
        if(client == null) throw new IllegalArgumentException("Client cannot be null");
        this.label = label;
        this.balance = 0.00;
        this.client = client;
        this.accountNumber = accountCounter++;
    }

// 1.2.1 Creation of Account class

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double amount) {
        this.balance = amount;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        String clientStr = (client != null) ? client.toString() : "Unknown client";
        return clientStr + ", account #" + accountNumber + " (" + getAccountType() + ") - Balance: " +
                String.format("%.2f", balance) + " €";
    }

//1.3.5 Updating accounts

    public void applyFlows(Flow flowsCollection) {
        if (flowsCollection instanceof Credit && this.accountNumber == flowsCollection.getTargetAccountNumber()) {
            this.balance += flowsCollection.getAmount();
        } else if (flowsCollection instanceof Debit && this.accountNumber == flowsCollection.getTargetAccountNumber()) {
            this.balance -= flowsCollection.getAmount();
        } else if (flowsCollection instanceof Transfer t) {
            if (this.accountNumber == t.getTargetAccountNumber()) {
                this.balance += t.getAmount();
            } else if (this.accountNumber == t.getIssuerAccountNumber()) {
                this.balance -= t.getAmount();
            }
        }
        this.balance = Math.round(this.balance * 100.0) / 100.0;
    }

    public static int getAccountCounter() {
        return accountCounter;
    }

//2.2 XML file of account

    public String getAccountType() {
        return this instanceof CurrentAccount ? "Current Account" : "Savings Account";
    }
}