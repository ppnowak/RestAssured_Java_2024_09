package pl.sages.training.restassured.framework.models.users;

public class UserDetailsResponse {

    private int id;
    private String firstName, lastName, registrationDate, status;
    private UserAddressData address;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public UserAddressData getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "UserDetailsResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", status='" + status + '\'' +
                ", address=" + address +
                '}';
    }
}

//{
//        "id": 1,
//        "firstName": "Piotr",
//        "lastName": "Nowak",
//        "registrationDate": "2024-01-05T03:40:33.486Z",
//        "status": "Active",
//        "address": {
//        "street": "Główna 8",
//        "city": "Warszawa",
//        "zipCode": "00-300"
//        }
//        }