package log;

import com.fasterxml.jackson.core.JsonProcessingException;
import events.EventType;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import queries.Find;
import queries.Update;

/**
 * Created by sarath on 06/10/16.
 */
public class UserInfo extends LogInfo {
    private final String userid;

    public UserInfo(DateTime actionTime, String userid) {
        this.actionTime = actionTime;
        this.userid = userid;
        this.eventType = EventType.USER_ACTIVITY;
    }

    public DateTime actionTime() {
        return actionTime;
    }

    public EventType eventType() {
        return eventType;
    }

    public String userid() {
        return userid;
    }

    public String createEvent(Jongo store) throws JsonProcessingException {
        Find find = new Find("userid", this.userid);
        createEvent(store, find);
        return null;
    }

    public String logInfo() {
        return "userid:" + userid + " actionTime:" + actionTime.getMillis() + " eventType:" + eventType.name();
    }
}
