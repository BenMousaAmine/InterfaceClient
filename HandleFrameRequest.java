//HandleFrameRequest Lato Client
package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HandleFrameRequest {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
     private List<Car> carList = new ArrayList<>();
    public HandleFrameRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
        setupStreams();
    }

    public void setupStreams() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void closeConex() {
    sendRequest("EXIT");

}

    public List<Car> getCarList() {
        try {
            sendRequest("ALL");
            String response = receiveResponse();
            carList = fromJsonToList(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public List<Car> MostExpensiveCar() {
        try {
            sendRequest("MORE_EXPENSIVE");
            String response = receiveResponse();
            carList = fromJsonToList(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public List<Car> CarsSortedByName() {
        try {
            sendRequest("ALL_SORTED");
            String response = receiveResponse();
            carList = fromJsonToList(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carList;
    }

    private void sendRequest(String request) {
        out.println(request);
        out.flush();
    }

    private String receiveResponse() throws IOException {
        StringBuilder jsonData = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            jsonData.append(line);
        }
        return jsonData.toString();
    }

    private List<Car> fromJsonToList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Car>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public String transformToJson(List<Car> carList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(carList);
    }
    public void closeConnections() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


