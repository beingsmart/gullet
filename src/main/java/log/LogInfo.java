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
public abstract class LogInfo {
    DateTime actionTime;
    EventType eventType;

    abstract String logInfo();

    abstract DateTime actionTime();

    abstract EventType eventType();

    public abstract String createEvent(Jongo store) throws JsonProcessingException;

    String updateQuery(Find find, long count) throws JsonProcessingException {
        Update $set = new Update(find, "$set");
        if (count == 0 && !eventType.get().equals("DEETS")) {
            $set.setValue("first_seen_time", actionTime.getMillis());
        }
        String activityTime = "last_seen_time";
        if (eventType.get().equals("DEETS")) {
            activityTime = "activity_time";
        }
        $set
                .setValue(activityTime, actionTime.getMillis())
                .setValue("type", eventType.name())
                .incBy("times_seen", 1);
        return $set.query();
    }

    void createEvent(Jongo store, Find find) throws JsonProcessingException {
        MongoCollection collection = store.getCollection(eventType.get());
        String findUser = find.query();
        collection.update(findUser).upsert().with(updateQuery(find, collection.count(findUser)));
    }
}


