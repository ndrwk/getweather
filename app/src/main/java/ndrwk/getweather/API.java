package ndrwk.getweather;

/**
 * Created by drew on 12.02.16.
 */
public class API {

    private static final String JSON_URL = "http://192.168.10.113:8000/cgi-bin/ws.py";
//    private static final String JSON_URL = "http://192.168.20.1/cgi-bin/ws.py";
    private static final int MTD_GET_APIVERSION = 0;
    private static final int MTD_GET_LAST = 1;
    private static final int MTD_GET_ALL = 2;
    private static final int MTD_GET_INTERVAL = 3;

    private static String makeURL(String url, int mtd, long minTime, long maxTime) {
        String resUrl = url;
        switch (mtd) {
            case MTD_GET_APIVERSION:
                resUrl += "?mtd=version";
                break;
            case MTD_GET_INTERVAL:
                resUrl += "?mtd=interval&min=" + minTime + "&max=" + maxTime;
                break;
            case MTD_GET_ALL:
                resUrl += "?mtd=all";
                break;
            case MTD_GET_LAST:
                resUrl += "?mtd=last";
                break;
        }
        return resUrl;
    }

    public static String getAll(){
        return makeURL(JSON_URL, MTD_GET_ALL, 0, 0);
    }

    public static String getLast(){
        return makeURL(JSON_URL, MTD_GET_LAST, 0, 0);
    }

    public static String getInterval(long minTime, long maxTime){
        return makeURL(JSON_URL, MTD_GET_INTERVAL, minTime, maxTime);
    }

}
