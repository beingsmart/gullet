package log;

import com.fasterxml.jackson.core.JsonProcessingException;
import events.EventType;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import queries.Find;

/**
 * Created by sarath on 08/10/16.
 */
public class ActivityInfo extends LogInfo {
    private final String userid;
    private final long placeid;

    public ActivityInfo(String userid, long placeid, DateTime actionTime, EventType eventType) {

        this.userid = userid;
        this.placeid = placeid;
        this.actionTime = actionTime;
        this.eventType = eventType;
    }

    public String getUserid() {
        return userid;
    }

    public long getPlaceid() {
        return placeid;
    }

    public DateTime actionTime() {
        return actionTime;
    }

    public EventType eventType() {
        return eventType;
    }

    public String createEvent(Jongo store) throws JsonProcessingException {
        Find find = new Find("userid", this.userid)
                .and("placeid", placeid)
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
        return "userid:" + userid + " placeid:" + placeid + " actionTime:" + actionTime.getMillis() + " eventType:" + eventType.name();
    }
}
