package ndrwk.getweather;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by drew on 07.02.16.
 */

public class JSONTask extends AsyncTask<String, Void, String> {

    private static final int TIMEOUT_MILLIS = 5000;
    private HttpURLConnection urlConnection;
    public static final String TIMEOUTERROR = "timeoutError";
    public static final String URLERROR = "urlError";
    public static final String IOERROR = "ioError";
    public IAsyncResponse result = null;

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String resultJson;
        try {
            URL urlline = new URL(url);
            urlConnection = (HttpURLConnection) urlline.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(TIMEOUT_MILLIS);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resultJson = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            resultJson = URLERROR;
        } catch (SocketTimeoutException e){
            e.printStackTrace();
            resultJson = TIMEOUTERROR;
        } catch (IOException e) {
            e.printStackTrace();
            resultJson = IOERROR;
        } finally {
            urlConnection.disconnect();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
        result.asyncResponse(jsonString);
//        if (jsonString != null) {
//            result.asyncResponse(jsonString);
//        }
    }
}
