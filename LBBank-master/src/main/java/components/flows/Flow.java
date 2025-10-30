package components.flows;

import components.flows.typeofflows.Credit;
import components.flows.typeofflows.Transfer;

import java.time.LocalDate;

//1.3.2 Creation of the Flow class

public abstract class Flow {

    private String comment;
    private final int idTransaction;
    private double amount;
    private int targetAccountNumber;
    private boolean effect;
    private final LocalDate dateOfFlow;
    private static int idCounter = 1;

    public Flow (String comment,double amount, int targetAccountNumber) {
        this.comment = comment;
        this.idTransaction = idCounter++;
        this.amount = amount;
        this.targetAccountNumber = targetAccountNumber;
        this.effect = false;
        this.dateOfFlow = LocalDate.now().plusDays(2); // 1.3.4 Creation of the flow array
    }

    public int getIdTransaction() {
        return idTransaction;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getTargetAccountNumber() {
        return targetAccountNumber;
    }
    public void setTargetAccountNumber(int targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }
    public boolean isEffect() {
        return effect;
    }
    public void setEffect(boolean effect) {
        this.effect = effect;
    }
    public LocalDate getDateOfFlow() {
        return dateOfFlow;
    }


//1.3.4 Creation of the flow array

    @Override
    public String toString() {

        String statusText = effect ? "Done" : "Pending";

        if (this instanceof Transfer t) {
            return "Transfer transaction id #" + idTransaction +
                    " - Amount: " + amount + "€" +
                    " from account #" + t.getIssuerAccountNumber() +
                    " to account #" + targetAccountNumber +
                    " - Transaction date: " + dateOfFlow +
                    " - Completed: " + statusText;
        } else if (this instanceof Credit) {
            return "Credit transaction id #" + idTransaction +
                    " - Amount: " + amount + "€" +
                    " from account #Unknown" +
                    " to account #" + targetAccountNumber +
                    " - Transaction date: " + dateOfFlow +
                    " - Completed: " + statusText;
        } else {
            return "Debit transaction id #" + idTransaction +
                    " - Amount: " + amount + "€" +
                    " from account #" + targetAccountNumber +
                    " to account #Unknown" +
                    " - Transaction date: " + dateOfFlow +
                    " - Completed: " + statusText;
        }
    }
}
