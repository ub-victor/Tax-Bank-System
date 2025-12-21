# Tax Bank System

A comprehensive Java-based banking system for managing tax payer accounts, transactions, and tax calculations with robust validation and error handling.

## Features

- **Account Management**: Open and close tax payer accounts
- **Transaction Processing**: Support Deposit, Withdraw, and Transfer operations
- **Tax Calculation**: Apply tax deductions based on configurable tax rates and types
- **Account Status Tracking**: Monitor active/closed status and balance
- **Operation Reporting**: Generate detailed operation reports with timestamps
- **Input Validation**: Comprehensive validation for all user inputs

## Project Structure

```
_27269/
├── main/
│   └── Main.java                    # Interactive CLI interface
├── operation/
│   ├── BankCharge.java             # Bank charge model
│   ├── Tax.java                    # Tax configuration model
│   ├── TaxAccount.java             # Tax account model
│   ├── TaxBankSystem.java          # Core system implementation
│   ├── TaxPayer.java               # Tax payer model
│   └── Transaction.java            # Transaction model
└── taxinterface/
    └── TaxBankOperations.java      # Interface for bank operations
```

## Compilation

```bash
cd _27269
javac -d out $(find . -name "*.java")
```

## Running the Application

```bash
java -cp out _27269.main.Main
```

## Usage Guide

### Menu Options

1. **Open Account**
   - Create a new tax payer account
   - Required fields:
     - **Name**: Non-empty string
     - **Email**: Must contain '@' symbol
     - **Phone**: 8-15 digits
     - **National ID**: Minimum 8 characters
     - **Age**: Must be >= 18
     - **Account Type**: Business or Personal

2. **Close Account**
   - Close an active account
   - Validates: Account exists and has Active status
   - Prevents: Closing accounts with negative balance

3. **Process Transaction**
   - **Deposit**: Add funds to account
   - **Withdraw**: Remove funds (validates sufficient balance)
   - **Transfer**: Move funds between accounts (validates both accounts exist and source has sufficient balance)

4. **Apply Tax**
   - Apply tax deduction to account
   - Validates: Account exists, percentage is valid

5. **Check Account Status**
   - View account details, balance, and status

6. **Generate Report**
   - Generate timestamped operation reports

7. **Exit**
   - Cleanly exit the application

## Validation Rules

### TaxPayer Validation
- **Age**: Must be >= 18
- **Email**: Must contain '@' symbol
- **Phone**: Must be 8-15 characters
- **National ID**: Must be at least 8 characters
- **Name**: Cannot be empty

### TaxAccount Validation
- **Balance**: Must be non-negative
- **Account Type**: Must be Business or Personal
- **Status**: Must be Active or Closed
- **Unique Account ID**: Auto-generated, starts at 1000

### Transaction Validation
- **Amount**: Must be positive
- **Type**: Must be Deposit, Withdraw, or Transfer
- **Withdraw/Transfer**: Validated against available balance
- **Transfer**: Both source and target accounts must exist

### Tax Validation
- **Percentage**: Between 1-50%
- **Type**: Income, Property, or Fine
- **Validity**: Date range must be valid

### System-Level Validation
- All numeric inputs validated with try-catch error handling
- Invalid formats display error message and return to menu
- Account existence checked before operations
- Status checks prevent invalid state transitions

## Example Session

```
--- Tax Bank System ---
1. Open Account
2. Close Account
3. Process Transaction
4. Apply Tax
5. Check Account Status
6. Generate Report
7. Exit
Select option: 1
Name: John Doe
Email: john@example.com
Phone: 1234567890
National ID: 1234567890123456
Age: 25
Account type (Business/Personal): Business
Account opened: TaxAccount{accountId=1000, taxPayer=1, accountType='Business', balance=0.0, status='Active'}
Operation:OpenAccount Reference:1000 Timestamp:1766307321196 Status:OK
```

## Data Structures

### TaxPayer
- Tax Payer ID (auto-generated)
- Name
- Email
- Phone
- National ID
- Age

### TaxAccount
- Account ID (auto-generated)
- Tax Payer ID
- Account Type
- Balance
- Status (Active/Closed)

### Transaction
- Transaction ID (auto-generated)
- Account ID
- Amount
- Transaction Date
- Transaction Type (Deposit/Withdraw/Transfer)
- Reference Number
- Status (Succeeded/Failed)

### Tax
- Tax ID (auto-generated)
- Tax Type
- Percentage
- Valid From
- Valid To

## Error Handling

The application includes comprehensive error handling:
- **NumberFormatException**: Caught and displays error message
- **NullPointerException**: Prevented through null checks
- **Invalid Input**: Validated before processing
- **Business Logic Errors**: Account status, balance, permissions checked

## Standards & Best Practices

- Object-oriented design with clear separation of concerns
- Interface-based architecture (TaxBankOperations)
- Immutable ID generation with AtomicInteger
- HashMap-based in-memory data storage
- Input validation at all entry points
- Clear error messages for user guidance

## System Constraints

- In-memory storage (data not persisted)
- Single-threaded CLI interface
- Account IDs start at 1000
- Transaction IDs start at 1
- Tax Payer IDs auto-increment from 1

## Future Enhancements

- Database persistence
- Multi-user login system
- Advanced reporting and analytics
- Batch transaction processing
- Tax rate history tracking
- Account statement export (PDF/CSV)

## License

Educational project for tax banking system demonstration.
