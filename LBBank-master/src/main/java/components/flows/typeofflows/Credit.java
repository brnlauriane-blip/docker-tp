package components.flows.typeofflows;

import components.flows.Flow;

//1.3.3 Creation of the (Transfer,) Credit, (Debit) classes

public class Credit extends Flow {

    public Credit(String comment, double amount, int targetAccountNumber) {
        super(comment, amount, targetAccountNumber);

    }

}
