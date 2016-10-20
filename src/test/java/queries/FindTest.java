package queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sarath on 08/10/16.
 */
public class FindTest {

    private Find find;

    @Before
    public void setUp() throws Exception {
        find = new Find("userid", "135adbca1343");
    }

    @Test
    public void testJsonQuery() throws JsonProcessingException {
        String query = find.query();
        System.out.println(query);
    }

    @Test
    public void testMultipleAndQuery() throws JsonProcessingException {
        Find find = new Find("userid", "1asdf43rdfasdfasfd")
                .and("placeid", 12341111813311L)
                .and("actionTime", DateTime.now().getMillis());
        System.out.println(find.query());
    }

}