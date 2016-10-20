package log;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.DateTime;
import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queries.Find;
import store.GenericMongoStore;

import java.io.IOException;

/**
 * Created by sarath on 09/10/16.
 */
public class UserInfoTest {

    private UserInfo userInfo;
    private Jongo store;
    private GenericMongoStore gm;

    @Before
    public void setup() throws IOException {
        System.setProperty("OPENSHIFT_MONGODB_DB_HOST", "OPENSHIFT_MONGODB_DB_HOST");
        System.setProperty("OPENSHIFT_MONGODB_DB_PORT", "OPENSHIFT_MONGODB_DB_PORT");
        gm = new GenericMongoStore("mongodb://parkice:parkservice@ds051585.mongolab.com:51585/spaces");
        store = gm.createJongo();
        userInfo = new UserInfo(DateTime.now(), "1asdfasdf11");
    }

    @Test
    public void updateQuery() throws Exception {
        String query = userInfo.updateQuery(new Find("userid", "1asdfasf1we"), 0);
        System.out.println(query);
    }

    @Test
    public void testCreateQuery() throws JsonProcessingException {
        userInfo.createEvent(store);
    }
    @After
    public void tearDown(){
        gm.closeMongo();
    }

}