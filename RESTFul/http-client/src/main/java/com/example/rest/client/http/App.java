package com.example.rest.client.http;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/21
 * Time: 14:00
 */
public class App {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpClient client = HttpClients.createDefault();
//        HttpGet request = new HttpGet("http://myd-vm04579.hpswlabs.adapps.hp.com:8080/job/static%20code%20analysis/lastBuild/api/json");
//        HttpGet request = new HttpGet("http://mydtbld0021.isr.hp.com:8080/jenkins/view/SAW/view/Master/job/MaaS-SAW-Branch-master/lastBuild/api/json");
        HttpGet request = new HttpGet("http://mydtbld0021.isr.hp.com:8080/jenkins/job/MaaS-SAW-Branch-L10n_Master/lastBuild/api/json");
        request.addHeader("accept","application/json");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        Config config = ConfigFactory.parseReader(rd);
        String line = "";
        StringBuilder result = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
            result.append(line);
            result.append(line);
        }

        System.out.printf("build [%s] status: %s\n", config.getString("number"), config.getString("result"));
    }
}
