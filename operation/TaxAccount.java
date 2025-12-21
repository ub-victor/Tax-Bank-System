package _27269.operation;

public class TaxAccount {
    private int accountId;
    private int taxPayer;
    private String accountType;
    private double balance;
    private String status;

    public TaxAccount(int accountId, int taxPayer, String accountType, double balance, String status) {
        validateInput(accountType, balance, status);
        this.accountId = accountId;
        this.taxPayer = taxPayer;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
    }

    private void validateInput(String accountType, double balance, String status) {
        if (accountType == null || (!accountType.equalsIgnoreCase("Business") && !accountType.equalsIgnoreCase("Personal"))) {
            throw new IllegalArgumentException("Account type must be Business or Personal");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (status == null || (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Closed"))) {
            throw new IllegalArgumentException("Status must be Active or Closed");
        }
    }

    public int getAccountId() {
        return accountId;
    }

    public int getTaxPayer() {
        return taxPayer;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaxAccount{" +
                "accountId=" + accountId +
                ", taxPayer=" + taxPayer +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
