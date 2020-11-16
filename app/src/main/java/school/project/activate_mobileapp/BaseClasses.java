package school.project.activate_mobileapp;
import java.util.Map;

import school.project.activate_mobileapp.HelperClasses;

class BaseClasses extends HelperClasses{

    class Activity {
        private String name;
        private ContactInformation contactInformation;
        private String description; //Tästä voisi tehdä apuluokan
        private int isAvailable; //0 = varattu/ei vapaa, 1 = vapaa
        private double price;
        private ActivityType activityType; //numeerinen arvo aktiviteetin tyypille
        private String ActivityID; //tunniste tietokantaa varten

        public String createID() {
            return name;
        }
    }

    class Business {
        private String name;
        private ContactInformation contactInformation;

        private String businessID; //tunniste tietokantaa varten


        public Business(String name) {
            this.name = name;
        }

        public String createID() {
            return name;
        }

    }

    class Customer {
        private String name;
        private ContactInformation contactInformation;

        private String customerID; //tunniste tietokantaa varten

        public Customer(String name) {
            this.name = name;
        }

        public String createID() {
            return name;
        }

    }




}

