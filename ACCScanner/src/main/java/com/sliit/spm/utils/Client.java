package com.sliit.spm.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sliit.spm.model.Project;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Client {

    private static final Logger LOGGER = Logger.getLogger(Client.class);
    private static String accUrl = PropertyReader.getInstance().getProperty("accUrl");
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static void sendAnalysisData(Project project) {
        try {

            LOGGER.debug("Submitting analysis data to the server...");

            HttpPost request = new HttpPost(accUrl);
            request.addHeader("content-type", "application/json");

            String json = gson.toJson(project); //convert
            System.out.println(json);

            StringEntity params = new StringEntity(json);
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                LOGGER.info("Analysis data submitted to the server " + response.getStatusLine());
            } else {
                LOGGER.error("Failed to submit data to the server " + response.getStatusLine());
            }

        } catch (ClientProtocolException e) {
            LOGGER.error("Failed to submit data to the server ");
        } catch (IOException e) {
            LOGGER.error("Failed to submit data to the server ");
        }
    }
}
