package _27269.main;

import _27269.operation.*;
import _27269.taxinterface.TaxBankOperations;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaxBankSystem system = new TaxBankSystem();
        AtomicInteger taxpayerIdGen = new AtomicInteger(1);
        AtomicInteger txnRefGen = new AtomicInteger(100);

        boolean running = true;
        while (running) {
            System.out.println("\n--- Tax Bank System ---");
            System.out.println("1. Open Account");
            System.out.println("2. Close Account");
            System.out.println("3. Process Transaction");
            System.out.println("4. Apply Tax");
            System.out.println("5. Check Account Status");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("Select option: ");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1":
                        openAccountMenu(sc, system, taxpayerIdGen);
                        break;
                    case "2":
                        closeAccountMenu(sc, system);
                        break;
                    case "3":
                        processTransactionMenu(sc, system);
                        break;
                    case "4":
                        applyTaxMenu(sc, system, txnRefGen);
                        break;
                    case "5":
                        checkAccountStatusMenu(sc, system);
                        break;
                    case "6":
                        generateReportMenu(sc, system);
                        break;
                    case "7":
                        running = false;
                        System.out.println("Thank you for using Tax Bank System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please select 1-7.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error: Invalid number format. " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Validation Error: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        sc.close();
    }

    private static void openAccountMenu(Scanner sc, TaxBankSystem system, AtomicInteger taxpayerIdGen) {
        try {
            System.out.println("\n--- Open New Account ---");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Phone: ");
            String phone = sc.nextLine();
            System.out.print("National ID: ");
            String nid = sc.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());

            int tpId = taxpayerIdGen.getAndIncrement();
            TaxPayer tp = new TaxPayer(tpId, name, email, phone, nid, age);

            System.out.print("Account type (Business/Personal): ");
            String accType = sc.nextLine();

            TaxAccount acc = system.openAccount(tp, accType);
            System.out.println("\n✓ Account opened successfully!");
            System.out.println(acc);
            System.out.println(system.generateReport("OpenAccount", String.valueOf(acc.getAccountId())));
        } catch (NumberFormatException ex) {
            System.out.println("Error: Invalid age format. Please enter a valid number.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Validation Error: " + ex.getMessage());
        }
    }

    private static void closeAccountMenu(Scanner sc, TaxBankSystem system) {
        try {
            System.out.println("\n--- Close Account ---");
            System.out.print("Account ID to close: ");
            int closeId = Integer.parseInt(sc.nextLine());
            boolean closed = system.closeAccount(closeId);
            if (closed) {
                System.out.println("\n✓ Account closed successfully!");
                System.out.println(system.generateReport("CloseAccount", String.valueOf(closeId)));
            } else {
                System.out.println("\n✗ Could not close account. Check: Account exists and is Active, Balance is non-negative");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error: Invalid account ID format.");
        }
    }

    private static void processTransactionMenu(Scanner sc, TaxBankSystem system) {
        try {
            System.out.println("\n--- Process Transaction ---");
            System.out.print("Account ID: ");
            int accId = Integer.parseInt(sc.nextLine());
            System.out.print("Type (Deposit/Withdraw/Transfer): ");
            String ttype = sc.nextLine();
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine());

            int ref = 0;
            if ("Transfer".equalsIgnoreCase(ttype)) {
                System.out.print("Target Account ID: ");
                ref = Integer.parseInt(sc.nextLine());
            }

            Transaction txnReq = new Transaction(0, accId, amt, String.valueOf(System.currentTimeMillis()), ttype, ref, "Pending");
            Transaction result = system.processTransaction(accId, txnReq);

            if (result != null) {
                String status = result.getStatus();
                if ("Succeeded".equals(status)) {
                    System.out.println("\n✓ Transaction " + status + "!");
                } else {
                    System.out.println("\n✗ Transaction " + status);
                }
                System.out.println(result);
                System.out.println(system.generateReport("Transaction", String.valueOf(result.getTransactionId())));
            } else {
                System.out.println("\n✗ Transaction failed: Account not found");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error: Invalid number format. Please check your inputs.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Validation Error: " + ex.getMessage());
        }
    }

    private static void applyTaxMenu(Scanner sc, TaxBankSystem system, AtomicInteger txnRefGen) {
        try {
            System.out.println("\n--- Apply Tax ---");
            System.out.print("Account ID: ");
            int aId = Integer.parseInt(sc.nextLine());
            System.out.print("Tax type (Income/Property/Fine): ");
            String taxType = sc.nextLine();
            System.out.print("Percentage (1-50): ");
            int pct = Integer.parseInt(sc.nextLine());

            Tax tax = new Tax(txnRefGen.getAndIncrement(), taxType, pct, "2025-01-01", "2026-12-31");
            Tax applied = system.applyTax(aId, tax);

            if (applied != null) {
                System.out.println("\n✓ Tax applied successfully!");
                System.out.println("Tax ID: " + applied.getTaxId());
                System.out.println("Type: " + applied.getTaxType());
                System.out.println("Percentage: " + applied.getPercentage() + "%");
                System.out.println(system.generateReport("ApplyTax", String.valueOf(applied.getTaxId())));
            } else {
                System.out.println("\n✗ Tax application failed: Account not found");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error: Invalid number format.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Validation Error: " + ex.getMessage());
        }
    }

    private static void checkAccountStatusMenu(Scanner sc, TaxBankSystem system) {
        try {
            System.out.println("\n--- Check Account Status ---");
            System.out.print("Account ID: ");
            int sId = Integer.parseInt(sc.nextLine());
            TaxAccount status = system.checkAccountStatus(sId);

            if (status != null) {
                System.out.println("\n--- Account Details ---");
                System.out.println(status);
            } else {
                System.out.println("\n✗ Account not found");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error: Invalid account ID format.");
        }
    }

    private static void generateReportMenu(Scanner sc, TaxBankSystem system) {
        System.out.println("\n--- Generate Report ---");
        System.out.print("Operation type: ");
        String op = sc.nextLine();
        System.out.print("Reference ID: ");
        String rid = sc.nextLine();
        System.out.println("\n--- Report ---");
        System.out.println(system.generateReport(op, rid));
    }
}

