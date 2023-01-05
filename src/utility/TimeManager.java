package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/** Time Manager class. Creates Observable list of business hours to populate start and end time
    combo boxes.
 */
public class TimeManager {

    /** Get Business Hours method.
        @param hr
        @return businessHours. Fills time combo boxes. This method will display local times that correspond
        to business hours in EST. */

    public static ObservableList<LocalTime> getBusinessHours(int hr) {

        ObservableList<LocalTime> businessHours = FXCollections.observableArrayList();
        LocalDate now = LocalDate.now();
        LocalTime startTime = LocalTime.of(hr,0);
        ZoneId estZID = ZoneId.of("America/New_York");
        ZonedDateTime estZDT = ZonedDateTime.of(now, startTime, estZID);
        ZoneId localZID = ZoneId.systemDefault();
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(estZDT.toInstant(), localZID);
        int startHour = localZDT.getHour();
        int midnight = 0;

        for(int i = startHour; i < startHour + 14; i++) {
            if (i < 24) {
                businessHours.add(LocalTime.of(i, 0));
            }
            else {
                businessHours.add(LocalTime.of(midnight, 0));
                midnight += 1;
            }
        }
        return businessHours;
    }
}
