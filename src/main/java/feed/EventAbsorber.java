package feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventType;
import log.LogInfo;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.io.IOException;

/**
 * Created by sarath on 24/09/16.
 */
public class EventAbsorber {
    private final Jongo store;
    private final ObjectMapper objectMapper;

    public EventAbsorber(Jongo store) throws IOException {
        this.store = store;
        this.objectMapper = new ObjectMapper();
    }

    public void sendEvent(String jsonEvent, EventType type) throws IOException {
        MongoCollection collection = this.store.getCollection(type.name());
        collection.insert(jsonEvent);

    }


    public void sendEvent(LogInfo logInfo) throws IOException {
        String event = logInfo.createEvent(store);

    }

}
