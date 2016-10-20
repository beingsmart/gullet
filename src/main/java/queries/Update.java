package queries;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Created by sarath on 09/10/16.
 */
public class Update extends Query {

    private String operation;

    public Update(Find find, String operation) {
        //this.query = find.query;
        this.operation = operation;
    }

    void setQuery() {
        //do nothing
    }

    public Update setValue(String field, Object value) {
        ObjectNode setQuery = query.with(operation);
        if (value instanceof Long) {
            setQuery.put(field, (Long) value);
        } else if (value instanceof Integer) {
            setQuery.put(field, (Integer) value);
        } else if (value instanceof String) {
            setQuery.put(field, (String) value);
        }
        return this;
    }

    public Update incBy(String field, int inc) {
        this.operation = "$inc";
        Update incQ = setValue(field, inc);
        return incQ;
    }
}
