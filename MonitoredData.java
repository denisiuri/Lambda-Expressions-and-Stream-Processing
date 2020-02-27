import java.io.IOException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MonitoredData {
    private String startTime;
    private String endTime;
    private String activity;

    public MonitoredData() {

    }

    public MonitoredData(String startTime, String endTime, String activity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return  activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public long countDays(List<MonitoredData> activities) {
        String pattern = "yyyy-MM-dd HH:mm:ss";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ld = LocalDateTime.now();

        return activities.stream()
                .map(x -> ld.parse(x.getStartTime(), formatter).getDayOfMonth())
                .distinct()
                .count();

    }

    public Map<String, Long> countActivities(List<MonitoredData> activities) {

        Map<String, Long> mapActivities = activities.stream()
                .collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));

        return mapActivities;
    }

/*    public Map<String, Long> countActivitiesEachDay(List<MonitoredData> activities) {

        Map<String, Long> list = new HashMap<String, Long>();
            for (MonitoredData md : activities) {
                list.put(md.getStartTime().charAt(8) + "" + md.getStartTime().charAt(9) + " " + md.getActivity(), list.get(md.getActivity()) + 1);

        return list;
    }*/

public void time(List<MonitoredData> activities)
{
    String pattern = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    for(MonitoredData it : activities) {
        LocalDateTime fromDateTime =
        LocalDateTime.parse(it.getStartTime(), formatter);

        LocalDateTime toDateTime =
        LocalDateTime.parse(it.getEndTime(), formatter);

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);

        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);
        //tempDateTime = tempDateTime.plusSeconds(seconds);

        System.out.println(it.getActivity() + " " + "Years: " + years + " " + "Months: " + " " + months + "Days: " + days + " " + "Hours: " + hours + " " + "Minutes: " + minutes + " " + "Seconds: " + seconds);
    }


}

    public String toString() {
        return "Start Time: " + startTime + " " + "End Time: " + endTime + " " + "Activity: " + activity;
    }
}
