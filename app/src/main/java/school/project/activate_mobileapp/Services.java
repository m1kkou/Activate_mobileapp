package school.project.activate_mobileapp;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Services extends MainActivity{


    public static DataSnapshot dss = MainActivity.dataSnapshot;

    public static void getData(DataSnapshot datasnapshot){
        // getData saves all activity-nodes from datasnapshot to ArrayList "activitylist"
        // Value of each activity-node from firebase is saved as Activity.class
        // Keys of activity-nodes are saved in variable "ActivityID" to the corresponding activity.class
        // To access AvailableTimes or Time -nodes in a specific activity-node, use getActivityTimes instead of activitylist
        Log.d("test", "getData called");
        activitylist.clear();
        DataSnapshot activitysnapshot = datasnapshot.child("Activities");
        int Index = 0;
        for(DataSnapshot ds : activitysnapshot.getChildren()){
            activitylist.add(ds.getValue(BaseClasses.Activity.class));
            activitylist.get(Index).setActivityID(ds.getKey());
            Index++;
        }
        Log.d("test", activitylist.get(0).getActivityID());
        String activity0 = activitylist.get(0).getName() + " " + activitylist.get(0).getDescription();
        Log.d("test", String.valueOf(activitylist.size()));
    }
    public static ArrayList<BaseClasses.Activity> getActivityDate(String Date){
        // getActivityDate (parempi nimi tälle xD) goes through every activity-node in datasnapshot
        // and saves activities in corresponding date to ArrayList DateActivityList (tälle parempi nimi kans)
        // To get access to times in each activity, you need to use variable ActivityID with method getActivityTimes
        DataSnapshot ActivitiesSnap = dss.child("Activities");
        ArrayList<BaseClasses.Activity> DateActivityList = new ArrayList<>();
        int Index = 0;
        for(DataSnapshot ds : ActivitiesSnap.getChildren()){
            DataSnapshot Availableds = ds.child("AvailableTimes");
            for(DataSnapshot timedss : Availableds.getChildren()){
                String Date_ = timedss.child("Date").getValue().toString();
                if(Date.equals(Date_)){
                    DateActivityList.add(ds.getValue(BaseClasses.Activity.class));
                    DateActivityList.get(Index).setActivityID(ds.getKey());
                    Index++;
                    break;
                }
            }
        }
        return DateActivityList;
    }
/*
    public static ArrayList<String> getActivityTimes(String ActivityID){
        //getActivityTimes takes ActivityID as parameter, and uses it to find corresponding activity from DataSnapshot
        //ActivityID is the same as key of each activity-node in firebase database
        //Within given Activity, getActivityTimes loops through every Time-node, and saves Interval-values in ArrayList freeTimes
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
*/
    public static ArrayList<BaseClasses.Time> getActivityTimes(String ActivityID){
        //getActivityTimes takes ActivityID as parameter, and uses it to find corresponding activity from DataSnapshot
        //ActivityID is the same as key of each activity-node in firebase database
        //Within given Activity, getActivityTimes loops through every Time-node, and saves Interval-values in ArrayList freeTimes
        ArrayList<BaseClasses.Time> freeTimes = new ArrayList();
        DataSnapshot Actds = dss.child("Activities");
        DataSnapshot ActivityDs = Actds.child(ActivityID);
        DataSnapshot AvailableDs = ActivityDs.child("AvailableTimes");
        for(DataSnapshot Timeds : AvailableDs.getChildren()){
            freeTimes.add(Timeds.getValue(BaseClasses.Time.class));
        }
        return freeTimes;
    }

    public static ArrayList<BaseClasses.Activity> getFilteredActivities(ArrayList<String> filters) {
        if(filters.isEmpty()){
            return BaseClasses.activities;
        }
        DataSnapshot ActivitiesSnapShot = dss.child("Activities");
        ArrayList<BaseClasses.Activity> filteredActivities = new ArrayList<>();
        int index = 0;
        for(DataSnapshot ds : ActivitiesSnapShot.getChildren()){
            String Filter = ds.child("ActivityType").getValue().toString();
            for(String filter : filters){
                if(Filter.equals(filter)){
                    filteredActivities.add(ds.getValue(BaseClasses.Activity.class));
                    filteredActivities.get(index).setActivityID(ds.getKey());
                    index++;
                }
            }
        }
        return filteredActivities;
    }
}
