package components;

//1.1.1 Creation of the client class

public class Client {

    private String lastName;
    private String firstName;
    private final int clientNumber;
    private static int assignmentNumber = 0;

// Modifié pour 2.2 XML file of account

    public Client(String lastName, String firstName, int clientNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.clientNumber = clientNumber;

        if (clientNumber > assignmentNumber) {
            assignmentNumber = clientNumber;
        }
    }

//1.1.1 Creation of the client class

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getClientNumber() {
        return clientNumber;
    }

    @Override
    public String toString() {
        return "Client #" + clientNumber + ", " + lastName + " " + firstName;
    }
}
