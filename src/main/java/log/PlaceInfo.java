package log;

import com.fasterxml.jackson.core.JsonProcessingException;
import events.EventType;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import queries.Find;

import java.math.BigDecimal;

/**
 * Created by sarath on 06/10/16.
 */
public class PlaceInfo extends LogInfo {
    private final Location location;

    public String logInfo() {
        return "location:" + location.get() + " actionTime:" + actionTime + "eventType: " + eventType.name();
    }

    public PlaceInfo(DateTime actionTime, Double lat, Double lng) {
        this.actionTime = actionTime;
        this.location = new Location(lat, lng);
        this.eventType = EventType.UNIQUE_PARKING_LOCATIONS;
    }

    public PlaceInfo(DateTime actionTime, Location location) {
        this.actionTime = actionTime;
        this.location = location;
        this.eventType = EventType.UNIQUE_PARKING_LOCATIONS;
    }

    public Location getLocation() {
        return location;
    }

    public DateTime actionTime() {
        return actionTime;
    }

    public EventType eventType() {
        return eventType;
    }

    public String createEvent(Jongo store) throws JsonProcessingException {
        Find find = new Find("placeid", this.location.placeid);
        createEvent(store, find);
        return null;
    }


}
