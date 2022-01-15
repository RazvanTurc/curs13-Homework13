package ro.fasttrackit.curs13.homework13.ex1;

import java.util.*;

public class DailyPlanner {
    private final List<DaySchedule> daySchedule;

    public DailyPlanner(Collection<DaySchedule> daySchedule) {
        this.daySchedule = daySchedule == null ?
                new ArrayList<>() :
                new ArrayList<>(daySchedule);
    }

    public void addActivity(DaysOfTheWeek day, String activity) throws NoActivityException {
        checkActivityNull(activity);
        if(dayIndex(day) == 7){
            List<String> newActivities = new ArrayList<>();
            daySchedule.add(new DaySchedule(day, newActivities));
        }
            daySchedule.get(dayIndex(day)).activities().add(activity);
    }


    public void removeActivity(DaysOfTheWeek day, String activity) throws NoActivityException {
        checkActivityNull(activity);
        if(!daySchedule.get(dayIndex(day)).activities().contains(activity)) {
            throw new NoActivityException(day + " does not have the activity: " + activity);
        } else {
            daySchedule.get(dayIndex(day)).activities().remove(activity);
        }
    }

    public List<String> getActivities(DaysOfTheWeek day) throws NoDayInListException {
        if(dayIndex(day) == 7) {
            throw new NoDayInListException(day + " is not in the Daily Planner");
        } else {
            return daySchedule.get(dayIndex(day)).activities();
        }
    }

    public Map<DaysOfTheWeek, List<String>> endPlanning() {
        Map<DaysOfTheWeek, List<String>> weekPlan= new HashMap<>();
        for(DaySchedule day : daySchedule) {
            weekPlan.put(day.day(), day.activities());
        }
        return weekPlan;
    }

    private void checkActivityNull(String activity) {
        if (activity == null) {
            throw new NoActivityException("No Activity Delivered");
        }
    }

    private int dayIndex(DaysOfTheWeek day) {
        for (DaySchedule dayType : daySchedule) {
            if (dayType.day() == day) {
                return daySchedule.indexOf(dayType);
            }
        }
        return 7;
    }
}
