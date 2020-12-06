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

    static class Customer {
        public String name;
        public String email;
        public String GSM;
        public String customerID; //tunniste tietokantaa varten

        public Customer(String name, String email, String GSM) {
            this.name = name;
            this.email = email;
            this.GSM = GSM;
        }

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }

        public void SaveCustomer(Customer c) {
            String key = dbr.child("Customers").push().getKey();
            c.setCustomerID(key);
            dbr.child("Customers").child(key).setValue(c);
        }
    }

    static class Order {
        public Activity activity;
        public Customer customer;

        public Order(){
            this.activity = new Activity();
            this.customer = new Customer("", "", "");
        }

        public void setActivityForOrder(Activity a){
            this.activity = a;
        }

        public void setCustomerForOrder(Customer c){
            this.customer = c;
        }

        public Order getOrder(){
            return this;
        }

    }
}

