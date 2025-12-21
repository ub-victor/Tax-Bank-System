package _27269.operation;

public class BankCharge {
    private int chargeId;
    private String description;
    private double amount;
    private String appliedDate;

    public BankCharge(int chargeId, String description, double amount, String appliedDate) {
        validateInput(description, amount, appliedDate);
        this.chargeId = chargeId;
        this.description = description;
        this.amount = amount;
        this.appliedDate = appliedDate;
    }

    private void validateInput(String description, double amount, String appliedDate) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (appliedDate == null || appliedDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Applied date cannot be empty");
        }
    }

    public int getChargeId() {
        return chargeId;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getAppliedDate() {
        return appliedDate;
    }
}
