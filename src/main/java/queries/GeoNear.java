package queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by sharath on 27/1/16.
 * GeoNear Query creator.
 * In Alpha, It only accepts a point.
 */
public class GeoNear extends Query{

    private final String collectionName;
    private final Double[] coordinates;

    public GeoNear(String collectionName, Double[] coordinates) {
        this.collectionName = collectionName;
        this.coordinates = coordinates;
        setQuery();
    }

    void setQuery() {
        query.put("geoNear", collectionName);
        ObjectNode near = query.with("near");
        near.put("type", "Point");
        ArrayNode coordinates = near.withArray("coordinates");
        for (Double coordinate : this.coordinates) {
            coordinates.add(coordinate);
        }
        query.put("spherical", true);
    }

    public GeoNear limit(int limit) {
        query.put("limit", limit);
        return this;
    }

    public GeoNear geoNearQuery() {
        return this;
    }

    public GeoNear maxDistance(int maxDistance) {
        query.put("maxDistance", maxDistance);
        return this;
    }
}
