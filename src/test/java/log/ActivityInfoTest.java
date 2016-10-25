package log;

import events.EventType;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import store.GenericMongoStore;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by sarath on 10/10/16.
 */
@Ignore("ignore from build")
public class ActivityInfoTest {

    private GenericMongoStore gm;
    Jongo j;

    @Before
    public void setup() throws IOException {
        System.setProperty("OPENSHIFT_MONGODB_DB_HOST", "OPENSHIFT_MONGODB_DB_HOST");
        System.setProperty("OPENSHIFT_MONGODB_DB_PORT", "OPENSHIFT_MONGODB_DB_PORT");
        gm = new GenericMongoStore("mongodb://parkice:parkservice@ds051585.mongolab.com:51585/spaces");
        j = gm.createJongo();
    }

    @Test
    public void createEvent() throws Exception {
        String userid = "1abc123";
        Location location = new Location(123.1111, 81.2222);

        //APP OPENED
        ActivityInfo appOpenActivity = new ActivityInfo(userid, location, DateTime.now(), EventType.APP_OPEN);
        appOpenActivity.createEvent(j);
        //PARKING
        ActivityInfo parkingActivity = new ActivityInfo(userid, location, DateTime.now(), EventType.PARKING);
        parkingActivity.createEvent(j);
        //AD CLICK
        ActivityInfo adClickActivity = new ActivityInfo(userid, location, DateTime.now(), EventType.AD_CLICK);
        adClickActivity.createEvent(j);
        //VACATING
        ActivityInfo vacatingActivity = new ActivityInfo(userid, location, DateTime.now(), EventType.VACATING);
        vacatingActivity.createEvent(j);

    }

}