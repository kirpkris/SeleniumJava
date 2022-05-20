package pageobject;

public class TestUser {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public TestUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TestUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TestUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public TestUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
