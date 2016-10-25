package log;

import com.fasterxml.jackson.core.JsonProcessingException;
import events.EventType;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import queries.Find;
import queries.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sarath on 08/10/16.
 */
public class ActivityInfo extends LogInfo {
    private final String userid;
    private final Location location;

    public ActivityInfo(String userid, Location location, DateTime actionTime, EventType eventType) {

        this.userid = userid;
        this.location = location;
        this.actionTime = actionTime;
        this.eventType = eventType;
    }

    public String getUserid() {
        return userid;
    }

    public DateTime actionTime() {
        return actionTime;
    }

    public EventType eventType() {
        return eventType;
    }

    public String createEvent(Jongo store) throws JsonProcessingException {
        Find find = new Find("userid", this.userid)
                .and("placeid", location.placeid)
                .and("activity_time", actionTime.getMillis());
        createEvent(store, find);
        return null;
    }

    public String type() {
        return null;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String logInfo() {
        return "userid:" + userid + " placeid:" + location.placeid + " actionTime:" + actionTime.getMillis()
                + " eventType:" + eventType.name();
    }
}
