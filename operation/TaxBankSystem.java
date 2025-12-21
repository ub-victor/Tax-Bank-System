package _27269.operation;

import _27269.taxinterface.TaxBankOperations;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TaxBankSystem implements TaxBankOperations {
    private final Map<Integer, TaxAccount> accounts = new HashMap<>();
    private final Map<Integer, TaxPayer> taxpayers = new HashMap<>();
    private final Map<Integer, Transaction> transactions = new HashMap<>();
    private final AtomicInteger accountIdGen = new AtomicInteger(1000);
    private final AtomicInteger taxPayerIdGen = new AtomicInteger(1);
    private final AtomicInteger txnIdGen = new AtomicInteger(1);
    private final AtomicInteger taxIdGen = new AtomicInteger(1);

    @Override
    public TaxAccount openAccount(TaxPayer taxPayer, String accountType) {
        int tpId = taxPayer.getTaxPayerId();
        taxpayers.putIfAbsent(tpId, taxPayer);
        int accId = accountIdGen.getAndIncrement();
        TaxAccount acc = new TaxAccount(accId, tpId, accountType, 0.0, "Active");
        accounts.put(accId, acc);
        return acc;
    }

    @Override
    public boolean closeAccount(int accountId) {
        TaxAccount acc = accounts.get(accountId);
        if (acc == null) return false;
        if (!"Active".equalsIgnoreCase(acc.getStatus())) return false;
        if (acc.getBalance() < 0) return false;
        acc.setStatus("Closed");
        return true;
    }

    @Override
    public Transaction processTransaction(int accountId, Transaction transaction) {
        TaxAccount acc = accounts.get(accountId);
        if (acc == null) {
            return null;
        }
        double amt = transaction.getAmount();
        String type = transaction.getTransactionType();
        String status = "Failed";
        if ("Deposit".equalsIgnoreCase(type)) {
            acc.setBalance(acc.getBalance() + amt);
            status = "Succeeded";
        } else if ("Withdraw".equalsIgnoreCase(type)) {
            if (acc.getBalance() >= amt) {
                acc.setBalance(acc.getBalance() - amt);
                status = "Succeeded";
            }
        } else if ("Transfer".equalsIgnoreCase(type)) {
            int targetId = transaction.getReferenceNumber();
            TaxAccount target = accounts.get(targetId);
            if (target != null && acc.getBalance() >= amt) {
                acc.setBalance(acc.getBalance() - amt);
                target.setBalance(target.getBalance() + amt);
                status = "Succeeded";
            }
        }
        Transaction stored = new Transaction(txnIdGen.getAndIncrement(), accountId, amt, transaction.getTransactionDate(), transaction.getTransactionType(), transaction.getReferenceNumber(), status);
        transactions.put(stored.getTransactionId(), stored);
        return stored;
    }

    @Override
    public Tax applyTax(int accountId, Tax tax) {
        TaxAccount acc = accounts.get(accountId);
        if (acc == null) return null;
        int pct = tax.getPercentage();
        double deduction = acc.getBalance() * pct / 100.0;
        acc.setBalance(acc.getBalance() - deduction);
        return tax;
    }

    @Override
    public TaxAccount checkAccountStatus(int accountId) {
        return accounts.get(accountId);
    }

    @Override
    public String generateReport(String operationType, String referenceId) {
        return String.format("Operation:%s Reference:%s Timestamp:%d Status:OK", operationType, referenceId, System.currentTimeMillis());
    }
}