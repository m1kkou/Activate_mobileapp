package school.project.activate_mobileapp;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;

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
        private String Description;
        private String ImageURL;
        private int IsAvailable; //0 = varattu/ei vapaa, 1 = vapaa
        private int Price;
        private String ActivityType;
        private ArrayList<Time> availableTimes;



        public Activity(){
            this.availableTimes = Services.getActivityTimes(this.getActivityID());
        }

        public Activity(String activityID, String Name, String Description, String imageURL, String ActivityType, int isAvailable, int price ){
            this.ActivityID = activityID;
            this.Name = Name;
            this.Description = Description;
            this.ImageURL = imageURL;
            this.ActivityType = ActivityType;
            this.Price = price;
         //   this.availableTimes = Services.getActivityTimes(this.getActivityID());
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
        public ArrayList<Time> getAvailableTimes() { return availableTimes; }

        public void setActivityID(String ID) {
            ActivityID = ID;
        }
    }

    static class Time {
        public int AvailableTime;
        public String Date;
        public String Interval;





        private String timeID;
        private int intervalStartTime;
        private int intervalEndTime;

        public Time(){}

        public Time (String date, String interval){
            this.Date = date;
            this.Interval = interval;

            String[] tempList = interval.split(":");
            this.intervalStartTime = Integer.parseInt(tempList[0]);
            this.intervalEndTime = Integer.parseInt(tempList[tempList.length - 1]);
            this.AvailableTime = 1;

        }
        public void setTimeID(String ID){
            this.timeID = ID;
        }

        public void setTimeBooked(){ this.AvailableTime = 0; }
        public String getTime() {
            return this.Interval;
        }
        /*
        public String getDate() {
            return this.Date;
        }
        */
        public String getTimeID() {
            return this.timeID;
        }
        public boolean isAvailable(){
            if (this.AvailableTime == 1){
                return true;
                }
            return false;
        }
    }

    static class Customer {
        public String name;
        public String email;
        public String GSM;
        public String customerID; //tunniste tietokantaa varten


        public Customer(){
        }
        public Customer(String name, String email, String GSM) {
            this.name = name;
            this.email = email;
            this.GSM = GSM;
        }

        public String getName() {
            return name;
        }

        public String getCustomerID() {
            return customerID;
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

    static class Order implements Serializable {
        public Activity activity;
        public Customer customer;
        public Time time;

        public Order(){
            this.activity = new Activity();
            this.customer = new Customer();

        }

        public Order(Activity a,Customer c,Time t){
            this.activity = a;
            this.customer = c;
            this.time = t;

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

