package school.project.activate_mobileapp;
import android.app.Activity;

import java.util.ArrayList;

class BaseClasses {

    class Activities {
        private ArrayList<Activity> activities;


        /*GetActivities (Activities) {
        Tähän Joona vähän tietokantataikoja joilla palautetaan lista kaikista aktiviteeteista
        return activities
        }
         */
    }

    class Activity {
        private String name;
        private ContactInformation contactInformation;
        private String description; //Tästä voisi tehdä apuluokan
        private String imageURL;
        private int isAvailable; //0 = varattu/ei vapaa, 1 = vapaa
        private double price;
        private int activityTypeEnum; //numeerinen arvo aktiviteetin tyypille asetettava if -checkillä koodissa
        private String activityType;
        private AvailableTimes availableTimes;
        private String ActivityID; //tunniste tietokantaa varten

        public Activity(String activityID, String name, String imageURL, int activityTypeEnum, int isAvailable, double price ){
            this.ActivityID = activityID;
            this.name = name;
            this.imageURL = imageURL;
            this.activityTypeEnum = activityTypeEnum;
            this.isAvailable = isAvailable;
            this.price = price;
        }

        public String GetActivityType(int activityTypeEnum){
            if (activityTypeEnum == 0){
                return "Hippa";
            }
            if (activityTypeEnum == 1){
                return "Urheilu";
            }
            if (activityTypeEnum == 2){
                return "Elämys";
            }
            if (activityTypeEnum == 3){
                return "Hyvinvointi";
            }
            return "undefined";
        }
    }

    class AvailableTimes {
        private ArrayList<Time> times;

        /* Joona tänne tarvisi vähän taikoja kanssa, että saadaan kannasta haettua lista ajoista eli Time olioista:
        public ArrayList<Time> getReservations(String ActivityID){
        Haetaan ID:n mukaisen aktiviteetin aika -oliot.

            return ArrayList<Time>;
        }
         */
    }
    class Time {
        private String availableTime;
        private String date;
        private String interval;
        private int intervalStartTime;
        private int intervalEndTime;

        public Time (String date, String interval){
            this.date = date;
            this.interval = interval;
            // Tässä splittaan aikavälin listaan, jotta saamme integer tyyppiset arvot alkuajalle ja loppuajalle
            String[] tempList = interval.split(":");
            this.intervalStartTime = Integer.parseInt(tempList[0]);
            this.intervalEndTime = Integer.parseInt(tempList[tempList.length - 1]);
            //Voi olla, että tämä joudutaan vielä muuttamaan integeriksi:
            this.availableTime = "1"; //Oletuksena asetettu aika on vapaa/olemassa

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

    class ContactInformation {
        private String phoneNumber;
        private String email;

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

