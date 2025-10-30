package components.accounts.types;

import components.Client;
import components.accounts.Account;

//1.2.2 Creation of the CurrentAccount (and SavingsAccount)

public class CurrentAccount extends Account {

    public CurrentAccount(Client client) {
        super("Current Account", client);
    }
}
