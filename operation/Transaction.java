package _27269.operation;

public class Transaction {
    private int transactionId;
    private int accountID;
    private double amount;
    private String transactionDate;
    private String transactionType;
    private int referenceNumber;
    private String status;

    public Transaction(int transactionId, int accountID, double amount, String transactionDate, String transactionType, int referenceNumber, String status) {
        validateInput(amount, transactionDate, transactionType, status);
        this.transactionId = transactionId;
        this.accountID = accountID;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.referenceNumber = referenceNumber;
        this.status = status;
    }

    private void validateInput(double amount, String transactionDate, String transactionType, String status) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (transactionDate == null || transactionDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction date cannot be empty");
        }
        if (transactionType == null || (!transactionType.equalsIgnoreCase("Deposit") && !transactionType.equalsIgnoreCase("Withdraw") && !transactionType.equalsIgnoreCase("Transfer"))) {
            throw new IllegalArgumentException("Transaction type must be Deposit, Withdraw, or Transfer");
        }
        if (status == null || (!status.equalsIgnoreCase("Pending") && !status.equalsIgnoreCase("Succeeded") && !status.equalsIgnoreCase("Failed"))) {
            throw new IllegalArgumentException("Status must be Pending, Succeeded, or Failed");
        }
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountID() {
        return accountID;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountID=" + accountID +
                ", amount=" + amount +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", referenceNumber=" + referenceNumber +
                ", status='" + status + '\'' +
                '}';
    }
}
