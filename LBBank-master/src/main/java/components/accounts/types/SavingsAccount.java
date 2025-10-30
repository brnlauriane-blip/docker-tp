package components.accounts.types;

import components.Client;
import components.accounts.Account;

//1.2.2 Creation of the (CurrentAccount and) SavingsAccount

public class SavingsAccount extends Account {
    public SavingsAccount(Client client) {
        super("Savings Account", client);
    }
}
