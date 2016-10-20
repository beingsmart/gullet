package queries;

/**
 * Created by sarath on 07/02/16.
 */
public class Find extends Query {
    private final String field;
    private final Object value;

    public Find(String field, Object value) {
        this.field = field;
        this.value = value;
        setQuery();
    }

    void setQuery() {
        if (value instanceof Integer) {
            query.put(field, (Integer) value);
        } else if (value instanceof String) {
            query.put(field, (String) value);
        }
    }

    public Find and(String actionTime, long millis) {
        query.put(actionTime, millis);
        return this;
    }
}
