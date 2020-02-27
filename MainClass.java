import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) throws IOException {

        MonitoredData md = new MonitoredData();

        List<MonitoredData> activities = new ArrayList<MonitoredData>();

            Stream<String> stream = Files.lines(Paths.get("Activities.txt"));
            stream.map(x -> x.split("\\t\\t"))
                    .map(y -> new MonitoredData(y[0], y[1], y[2]))
                    .sequential().collect(Collectors.toCollection(() -> activities));


        for(MonitoredData it : activities)
        {
            System.out.println(it);
        }
        System.out.println("Count days: " + md.countDays(activities));

        System.out.println(md.countActivities(activities));

        md.time(activities);
    }


}
