package pl.sages.training.restassured.framework.models.users;

public class AddUserRequest {

    private String firstName, lastName;
    private UserAddressData address;

    public AddUserRequest() {
    }

    public AddUserRequest(String firstName, String lastName, UserAddressData address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAddressData getAddress() {
        return address;
    }


}