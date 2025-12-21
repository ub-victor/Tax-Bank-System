package _27269.operation;

public class Tax {
    private int taxId;
    private String taxType;
    private int percentage;
    private String validFrom;
    private String validTo;

    public Tax(int taxId, String taxType, int percentage, String validFrom, String validTo) {
        validateInput(taxType, percentage, validFrom, validTo);
        this.taxId = taxId;
        this.taxType = taxType;
        this.percentage = percentage;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    private void validateInput(String taxType, int percentage, String validFrom, String validTo) {
        if (taxType == null || (!taxType.equalsIgnoreCase("Income") && !taxType.equalsIgnoreCase("Property") && !taxType.equalsIgnoreCase("Fine"))) {
            throw new IllegalArgumentException("Tax type must be Income, Property, or Fine");
        }
        if (percentage < 1 || percentage > 50) {
            throw new IllegalArgumentException("Percentage must be between 1-50%");
        }
        if (validFrom == null || validFrom.trim().isEmpty()) {
            throw new IllegalArgumentException("Valid from date cannot be empty");
        }
        if (validTo == null || validTo.trim().isEmpty()) {
            throw new IllegalArgumentException("Valid to date cannot be empty");
        }
    }

    public int getTaxId() {
        return taxId;
    }

    public String getTaxType() {
        return taxType;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public String getValidTo() {
        return validTo;
    }
}
