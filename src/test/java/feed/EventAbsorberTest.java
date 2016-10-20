package feed;

import events.EventType;
import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import store.GenericMongoStore;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by sarath on 24/09/16.
 */
@Ignore("ignore from build")
public class EventAbsorberTest {
    EventAbsorber ea;
    private GenericMongoStore gm;

    @Before
    public void setup() throws IOException {
        System.setProperty("OPENSHIFT_MONGODB_DB_HOST", "OPENSHIFT_MONGODB_DB_HOST");
        System.setProperty("OPENSHIFT_MONGODB_DB_PORT", "OPENSHIFT_MONGODB_DB_PORT");
        gm = new GenericMongoStore("mongodb://parkice:parkservice@ds051585.mongolab.com:51585/spaces");
        Jongo j = gm.createJongo();
        ea = new EventAbsorber(j);
    }

    @org.junit.Test
    public void sendEvent() throws Exception {
        ea.sendEvent("{name: 'Joe', age: 18}", EventType.PARKING);
    }

    @After
    public void tearDown() {
        gm.closeMongo();
    }

}