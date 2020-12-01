package school.project.activate_mobileapp;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

class BaseClasses {
    public static ArrayList<BaseClasses.Activity> activities = MainActivity.activitylist;
    public static DatabaseReference dbr = MainActivity.databaseReference;

    class Activities {
       public ArrayList GetActivities(){
           return activities;
       }
    }

    static class Activity {
        private String Name;
        private String ActivityID;
        private ContactInformation contactInformation;
        private String Description; //Tästä voisi tehdä apuluokan
        private String ImageURL;
        private int IsAvailable; //0 = varattu/ei vapaa, 1 = vapaa
        private int Price;
        private String ActivityType;



        public Activity(){ }

        public Activity(String activityID, String Name, String Description, String imageURL, String ActivityType, int isAvailable, int price ){
            this.ActivityID = activityID;
            this.Name = Name;
            this.Description = Description;
            this.ImageURL = imageURL;
            this.ActivityType = ActivityType;
            this.IsAvailable = isAvailable;
            this.Price = price;
        }

        public String getImageURL(){
            return this.ImageURL;
        }
        public String getName(){
            return this.Name;
        }
        public String getDescription(){
            return this.Description;
        }
        public String getPrice(){
            return String.valueOf(this.Price);
        }
        public String getActivityID() {
            return String.valueOf(this.ActivityID);
        }

        public void setActivityID(String ID) {
            ActivityID = ID;
        }
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
            String[] tempList = interval.split(":");
            this.intervalStartTime = Integer.parseInt(tempList[0]);
            this.intervalEndTime = Integer.parseInt(tempList[tempList.length - 1]);
            this.availableTime = "1";

        }

        public String getTime() {
            return this.interval;
        }
        public String getDate() {
            return this.date;
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
        public void SaveCustomer(){
            dbr.child("Customers").child(customerID).setValue(this);
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

