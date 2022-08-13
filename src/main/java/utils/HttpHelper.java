package utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;

public class HttpHelper {
    public static String uploadApp(String filepath) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://api-cloud.browserstack.com/app-automate/upload")
                .header("Authorization", "Basic c2VyZ2U2Njg6ekNjY0JicHE1R2RrSEVjZTlUUXg=")
                .field("file", new File(filepath))
                .asString();
        return response.getBody();
    }
}
