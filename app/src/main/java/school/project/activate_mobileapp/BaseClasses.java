package school.project.activate_mobileapp;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

class BaseClasses {
    public static ArrayList<Activity> activities = MainActivity.activitylist;
    public static DatabaseReference dbr = MainActivity.databaseReference;
    public static DataSnapshot dss = MainActivity.dataSnapshot;

    class Activities {

        /*
        Näin? Vai pitääkö tuohon saada argumentti sisään jotenkin? -j
         */
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
        private int ActivityTypeEnum; //numeerinen arvo aktiviteetin tyypille asetettava if -checkillä koodissa
        private String ActivityType;
        private ArrayList AvailableTimes; //Tämä objekti sisältää vapaat ajat



        public Activity(){
            //no-argument constructor for getData() at MainActivity
        }

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
        /*public ArrayList<Time> getReservations(String ActivityID){

            ArrayList<Time> times = new ArrayList<>();
            Activity activity = new Activity();

            for (int i=0; i<activities.size() ; i++){
                if(activities.get(i).getActivityID().equals(ActivityID)){
                    activity = activities.get(i);
                    for(int = 0; i <  )
                }


                //loopataan läpi activities-lista ja napataan se jossa oikea id
            }

            for(Time t : activity.){
                times.add(t);
            }

            return times;
        }
    }
/*
    static class AvailableTimes {
            protected ArrayList<Time> times;


        static public ArrayList<Time> getReservations(String ActivityID){

            Activity activity = new Activity();

            for (int i=0; i<activities.size() ; i++){
                if(activities.get(i).getActivityID().equals(ActivityID)){
                        activity = activities.get(i);
                }
                //loopataan läpi activities-lista ja napataan se jossa oikea id
            }

            for(Time t : activity.AvailableTimes.times){
                times.add(t);
            }

            return times;
        }
        /* Joona tänne tarvisi vähän taikoja kanssa, että saadaan kannasta haettua lista ajoista eli Time olioista:
        public ArrayList<Time> getReservations(String ActivityID){
        Haetaan ID:n mukaisen aktiviteetin aika -oliot.

            return ArrayList<Time>;
        }

        En oo varma toimiiko ylläoleva koodi, toivottavasti :D
        Mietin vaan, missä tilanteessa tarvitaan metodia joka ottaa activity-id:n ja näyttää tuon time-listan?
        Esim. jos käyttäjä valitsee aktiviteetin josta sitten aikoja näytetään, niin siinä vaiheessahan ollaan jo
        Activity valittu ja voidaan näyttää time-luokan sisältöä siinä missä muutakin Activity-luokan tietoja.


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


    public static ArrayList<Activity> getActivityDate(String Date){
        DataSnapshot ActivitiesSnap = dss.child("Activities");
        ArrayList<Activity> DateActivityList = new ArrayList<>();
        for(DataSnapshot ds : ActivitiesSnap.getChildren()){
            DataSnapshot Availableds = ds.child("AvailableTimes");
            for(DataSnapshot timedss : Availableds.getChildren()){
                String Date_ = timedss.child("Date").getValue().toString();
                if(Date.equals(Date_)){
                    DateActivityList.add(ds.getValue(Activity.class));
                    break;
                }
            }
        }
        return DateActivityList;
    }

    public static ArrayList<String> getActivityTimes(String ActivityID){
        ArrayList<String> freeTimes = new ArrayList();
        DataSnapshot Actds = dss.child("Activities");
        DataSnapshot ActivityDs = Actds.child(ActivityID);
        DataSnapshot AvailableDs = ActivityDs.child("AvailableTimes");
        for(DataSnapshot Timeds : AvailableDs.getChildren()){
            String Interval = Timeds.child("Interval").getValue().toString();
            freeTimes.add(Interval);
        }
        return freeTimes;
    }

    public static ArrayList<Activity> FilteredActivities(ArrayList<String> Filters){
        DataSnapshot DataSs = dss.child("Activities");
        ArrayList<Activity> FilteredList = new ArrayList<>();
        for(DataSnapshot ds : DataSs.getChildren()){
            for(String Filter : Filters){
                if(ds.child("ActivityType").toString().equals(Filter)){
                    FilteredList.add(ds.getValue(Activity.class));
                }
            }
        }
        return FilteredList;
    }
}

