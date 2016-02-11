package ndrwk.getweather;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by drew on 07.02.16.
 */

public class JSONTask extends AsyncTask<String, Void, String> {
    private HttpURLConnection urlConnection;
    private String resultJson;
    public AsyncResponse result = null;

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        try {
            URL urlline = new URL(url);
            urlConnection = (HttpURLConnection) urlline.openConnection();
            urlConnection.setRequestMethod("GET");
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
        if (jsonString != null) {
            result.asyncResponse(jsonString);
        }
    }
}
