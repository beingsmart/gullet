package log;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import store.GenericMongoStore;
import store.MongoFields;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by sarath on 08/10/16.
 */
public class PlaceInfoTest {

    private PlaceInfo placeInfo;
    private GenericMongoStore gm;
    Jongo j;
    @Before
    public void setup() throws IOException {
        placeInfo = new PlaceInfo(DateTime.now(), 122.14561234455, 81.34524355433);
        System.setProperty("OPENSHIFT_MONGODB_DB_HOST", "OPENSHIFT_MONGODB_DB_HOST");
        System.setProperty("OPENSHIFT_MONGODB_DB_PORT", "OPENSHIFT_MONGODB_DB_PORT");
        gm = new GenericMongoStore("mongodb://parkice:parkservice@ds051585.mongolab.com:51585/spaces");
        j = gm.createJongo();
        j.getCollection(placeInfo.eventType().get()).ensureIndex(MongoFields._2D_SPHERE);
    }

    @Test
    public void getLocation() throws Exception {
        System.out.println(placeInfo.getLocation().placeid);
    }

    @Test
    public void createEvent() throws JsonProcessingException {
        placeInfo.createEvent(j);
    }

    @After
    public void tearDown() {
        gm.closeMongo();
    }

}