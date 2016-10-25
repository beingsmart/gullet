package log;

import java.math.BigDecimal;

/**
 * Created by sarath on 25/10/16.
 */
public class Location {
    public static final int SCALE_TO_10_M = 4;
    Double lat;
    Double lng;
    public long placeid;

    public Location(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
        this.placeid = generatePlaceid();
    }

    private long generatePlaceid() {
        String lat = new BigDecimal(this.lat).setScale(SCALE_TO_10_M, BigDecimal.ROUND_CEILING).toString().replace(".", "");
        String lng = new BigDecimal(this.lng).setScale(SCALE_TO_10_M, BigDecimal.ROUND_CEILING).toString().replace(".", "");
        return Long.valueOf(lat.concat(lng));
    }

    String get() {
        return "lat:" + lat + " lng:" + lng + " placeid:" + placeid;
    }
}
