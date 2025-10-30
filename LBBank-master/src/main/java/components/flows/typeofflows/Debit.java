package components.flows.typeofflows;

import components.flows.Flow;

//1.3.3 Creation of the (Transfer, Credit,) Debit classes

public class Debit extends Flow {

    public Debit(String comment, double amount, int targetAccountNumber) {
        super(comment, amount, targetAccountNumber);

    }

}
