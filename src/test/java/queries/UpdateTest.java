package queries;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sarath on 09/10/16.
 */
public class UpdateTest {

    private Update update;

    @Before
    public void setup(){
        update = new Update(new Find("userid", "1adbadfadf1"), "$set");
    }
    @Test
    public void setValue() throws Exception {
        Update lastUpdate = update.setValue("lastUpdate", "13adsfasdf");
        System.out.println(lastUpdate.query());

    }

}