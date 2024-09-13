package pl.sages.training.restassured.framework.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAddressData {

//    @JsonProperty("street_name")
    private String street;

    private String city, zipCode;

    public UserAddressData() { // empty constructor is left for Jackson DataBind
    }

    public UserAddressData(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "UserAddressData{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
//        "street": "Główna 8",
//        "city": "Warszawa",
//        "zipCode": "00-300"