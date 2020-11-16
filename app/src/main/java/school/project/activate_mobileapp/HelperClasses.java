package school.project.activate_mobileapp;
import android.location.Address;

import java.util.HashMap;
import java.util.Map;

public class HelperClasses {

    class ActivityType {
        private Map <Integer , String> activityTypes = new HashMap<>();

        public ActivityType(){
            this.activityTypes = getActivityTypes();
        }
        public void addActivityType(String type){
            activityTypes.put(activityTypes.size() + 1, type);
        }
        public Map<Integer, String> getActivityTypes(){
            return activityTypes;
        }
        public boolean deleteActivityType(int key){
            if(activityTypes.containsKey(key)){
                activityTypes.remove(key);
                return true;
            }
            return false;
        }
    }

    class Address {
        private String street;
        private String city;
        private int postcode;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getPostalCode() {
            return postcode;
        }

        public void setPostalcode(int pCode) {
            this.postcode = pCode;
        }
    }
    class ContactInformation {
        private android.location.Address address;
        private String phoneNumber;
        private String email;

        public android.location.Address GetAddress() {
            return address;
        }

        public void setAddress(android.location.Address address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
