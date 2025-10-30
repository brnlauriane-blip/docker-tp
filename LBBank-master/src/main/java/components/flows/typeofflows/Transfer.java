package components.flows.typeofflows;

import components.flows.Flow;

// 1.3.3 Creation of the Transfer, (Credit, Debit) classes

public class Transfer extends Flow {

    private int issuerAccountNumber;

    public Transfer(String comment, double amount, int targetAccountNumber, int issuerAccount) {
        super(comment, amount, targetAccountNumber);
        this.issuerAccountNumber = issuerAccount;
    }

    public int getIssuerAccountNumber() {
        return issuerAccountNumber;
    }
}
