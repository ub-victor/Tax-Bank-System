package _27269.taxinterface;

import _27269.operation.TaxAccount;
import _27269.operation.TaxPayer;
import _27269.operation.Transaction;
import _27269.operation.Tax;

public interface TaxBankOperations {
    TaxAccount openAccount(TaxPayer taxPayer, String accountType);
    boolean closeAccount(int accountId);
    Transaction processTransaction(int accountId, Transaction transaction);
    Tax applyTax(int accountId, Tax tax);
    TaxAccount checkAccountStatus(int accountId);
    String generateReport(String operationType, String referenceId);
}