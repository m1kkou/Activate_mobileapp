package school.project.activate_mobileapp;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

//BaseClasses hold the basic object structures which are used throughout the program
class BaseClasses {
    public static ArrayList<BaseClasses.Activity> activities = MainActivity.activitylist;
    public static DatabaseReference dbr = MainActivity.databaseReference;

    //This class defines Activity and holds the basic info of it.
    //It also holds an URL -reference to image shown in the ScrollView component.
    //ID is used in the database.
    //AvailableTimes array holds all Time objects which are linked to that activity.
    //The class is static so we it's available in other classes
    static class Activity {
        private String Name;
        private String ActivityID;
        private String Description;
        private String ImageURL;
        private int Price;
        private String ActivityType;
        private ArrayList<Time> availableTimes;


        //Activity uses an empty constructor so Firebase methods can
        //directly cast json -objects to this type
        //Note! in the constructor we call getActivityTimes to get a list of
        //time objects to the parent Activity object.
        public Activity(){
            this.availableTimes = Services.getActivityTimes(this.getActivityID());
        }

        //This constructor was mainly used for creating objects directly from the
        //app. F.ex. testing

        public Activity(String activityID, String Name, String Description, String imageURL, String ActivityType, int isAvailable, int price ){
            this.ActivityID = activityID;
            this.Name = Name;
            this.Description = Description;
            this.ImageURL = imageURL;
            this.ActivityType = ActivityType;
            this.Price = price;
            this.availableTimes = Services.getActivityTimes(this.getActivityID());
        }

        //getter and setters for Activity

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

    //Static class Time present one time object that holds date, available time, id
    //and availability. This is used to present when a certain Activity is available.

    static class Time {
        public int AvailableTime;
        public String Date;
        public String Interval;
        private String timeID;
        private int intervalStartTime;
        private int intervalEndTime;

        //Empty constructor for Firebase
        public Time(){}

        //Constructor with parameters. Note! Time is set to available = 1
        //because this value determines is it shown in the UI
        public Time (String date, String interval){
            this.Date = date;
            this.Interval = interval;
            String[] tempList = interval.split(":");
            this.intervalStartTime = Integer.parseInt(tempList[0]);
            this.intervalEndTime = Integer.parseInt(tempList[tempList.length - 1]);
            this.AvailableTime = 1;

        }
        //Getter & Setters
        public void setTimeID(String ID){
            this.timeID = ID;
        }
        public void setTimeBooked(){ this.AvailableTime = 0; }
        public String getTime() {
            return this.Interval;
        }
        public String getDate() {
            return this.Date;
        }
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

    //Customer object is used to save the customer information
    //when an order for activity is set
    //The class is static so it's available in other classes
    static class Customer {
        public String name;
        public String email;
        public String GSM;
        public String customerID;

        //Empty constructor for Firebase methods
        public Customer(){ }
        //Constructor w. parameters
        public Customer(String name, String email, String GSM) {
            this.name = name;
            this.email = email;
            this.GSM = GSM;
        }
        //Getters & setters:
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

    //Order class is a class for linking order information together
    //it holds Activity - which activity is in the order, Customer - who
    //made the order, Time - time which was booked
    //The class is static so we it's available in other classes
    static class Order {
        public Activity activity;
        public Customer customer;
        public Time time;

        public Order(){ }

        public Order(Activity a,Customer c,Time t){
            this.activity = a;
            this.customer = c;
            this.time = t;

        }
        //Getters & setters:
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

