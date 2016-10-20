package queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by sarath on 08/10/16.
 */
public abstract class Query {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode query = objectMapper.createObjectNode();

    public String query() throws JsonProcessingException {
        return objectMapper.writeValueAsString(query);
    }

    abstract void setQuery();
}
