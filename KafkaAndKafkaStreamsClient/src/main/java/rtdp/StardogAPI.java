package rtdp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StardogAPI
{
    private String capability;
    private String apiUrl;

    public static void main(String[] args) throws Exception {
        try {
            StardogAPI api = new StardogAPI();
            api.setApiUrl("https://rtdm-flask-client.herokuapp.com/topics?term=");
            api.setCapability("Bagging");
            List<String> results = api.getResults();

            System.out.println(results);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public StardogAPI(String apiUrl, String capability) {
        this.capability = capability;
        this.apiUrl = apiUrl;
    }

    public StardogAPI() {
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public List<String> getResults() {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
        String desiredUrl = apiUrl + capability;
        try
        {
            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15*1000);
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            String returnString = stringBuilder.toString().replace("\"", "");
            return new ArrayList<String>(Arrays.asList(returnString.substring(1,returnString.length()-2).split(",")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }
}