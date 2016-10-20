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

    public class Location {
        public static final int SCALE_TO_10_M = 4;
        Double lat;
        Double lng;
        String placeid;

        public Location(Double lat, Double lng) {
            this.lat = lat;
            this.lng = lng;
            this.placeid = generatePlaceid();
        }

        private String generatePlaceid() {
            String lat = new BigDecimal(this.lat).setScale(SCALE_TO_10_M, BigDecimal.ROUND_CEILING).toString().replace(".", "");
            String lng = new BigDecimal(this.lng).setScale(SCALE_TO_10_M, BigDecimal.ROUND_CEILING).toString().replace(".", "");
            return lat.concat(lng);
        }

        String get() {
            return "lat:" + lat + " lng:" + lng + " placeid:" + placeid;
        }
    }

    public PlaceInfo(DateTime actionTime, Double lat, Double lng) {
        this.actionTime = actionTime;
        this.location = new Location(lat, lng);
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
