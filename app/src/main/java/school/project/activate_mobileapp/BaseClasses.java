package school.project.activate_mobileapp;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

class BaseClasses {
    public ArrayList<Activity> activities = new ArrayList<>();

    class Activities {

        public void addActivity(Activity a){
            activities.add(a);
        }
        private MainActivity mainActivity;

        public ArrayList<Activity>  GetActivities(){
            mainActivity = new MainActivity();
            activities = mainActivity.getActivitylist();
            return activities;
        }

      /*  GetActivities (Activities) {
        Tähän Joona vähän tietokantataikoja joilla palautetaan lista kaikista aktiviteeteista
        return activities

        Tarviiko tuon metodin ottaa argumenttia, jos sen tarkoitus palauttaa kaikki aktiviteetit joka tapauksessa?
        }
         */
    }

    static class Activity {
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

        public Activity(){
            //no-argument constructor for getData() at MainActivity
        }

        public Activity(String activityID, String name, String description, String imageURL, int activityTypeEnum, int isAvailable, double price ){
            this.ActivityID = activityID;
            this.name = name;
            this.description = description;
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
        public String getImageURL(){
            return this.imageURL;
        }
        public String getName(){
            return this.name;
        }
        public String getDescription(){
            return this.description;
        }
        public String getPrice(){
            return String.valueOf(this.price);
        }
    }

    class AvailableTimes {
        private ArrayList<Time> times;
        public ArrayList<Time> getReservations(String ActivityID){
            Activity activity = new Activity();
            int len = activities.size();
            for (int i=0; i<len ; i++){
                if(activities.get(i).ActivityID == ActivityID){
                    activity = activities.get(i);
                }
                //loopataan läpi activities-lista ja napataan se jossa oikea id
            }
            for(Time t : activity.availableTimes.times){
                times.add(t);
            }
            return times;
        }
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

