package com.example.rest.client.jersey;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/7/21
 * Time: 13:40
 */
public class App {
    public static void main(String[] args) {
        Form form = new Form();
        form.param("username", "david");
        form.param("password", "david");

        Client client = ClientBuilder.newClient();
        String uri = "http://myd-vm04579.hpswlabs.adapps.hp.com:8080";
        WebTarget resource = client.target(uri).path("/job/static%20code%20analysis/api/json");
        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);

        Response response = request.get();

        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity());
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity());
        }
    }
}
