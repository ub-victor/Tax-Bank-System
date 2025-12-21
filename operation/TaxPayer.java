package _27269.operation;

public class TaxPayer {
    private int taxPayerId;
    private String name;
    private String email;
    private String phone;
    private String nationalId;
    private int age;

    public TaxPayer(int taxPayerId, String name, String email, String phone, String nationalId, int age) {
        validateInput(name, email, phone, nationalId, age);
        this.taxPayerId = taxPayerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nationalId = nationalId;
        this.age = age;
    }

    private void validateInput(String name, String email, String phone, String nationalId, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email must contain '@' symbol");
        }
        if (phone == null || phone.trim().isEmpty() || phone.length() < 8 || phone.length() > 15) {
            throw new IllegalArgumentException("Phone must be 8-15 digits");
        }
        if (nationalId == null || nationalId.trim().isEmpty() || nationalId.length() < 8) {
            throw new IllegalArgumentException("National ID must be at least 8 characters");
        }
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age must be realistic (<=150)");
        }
    }

    public int getTaxPayerId() {
        return taxPayerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getNationalId() {
        return nationalId;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "TaxPayer{" +
                "taxPayerId=" + taxPayerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", age=" + age +
                '}';
    }
}
