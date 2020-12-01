package school.project.activate_mobileapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Services extends MainActivity{


    public static DataSnapshot dss = MainActivity.dataSnapshot;

    public static void getData(DataSnapshot datasnapshot){
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
        Log.d("test", activity0);
    }
    public static ArrayList<BaseClasses.Activity> getActivityDate(String Date){
        DataSnapshot ActivitiesSnap = dss.child("Activities");
        ArrayList<BaseClasses.Activity> DateActivityList = new ArrayList<>();
        for(DataSnapshot ds : ActivitiesSnap.getChildren()){
            DataSnapshot Availableds = ds.child("AvailableTimes");
            for(DataSnapshot timedss : Availableds.getChildren()){
                String Date_ = timedss.child("Date").getValue().toString();
                if(Date.equals(Date_)){
                    DateActivityList.add(ds.getValue(BaseClasses.Activity.class));
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
}
