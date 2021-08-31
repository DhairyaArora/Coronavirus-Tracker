package com.example.Coronavirustracker.services;

import com.example.Coronavirustracker.models.Locationstats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class Coronavirudataservice {

    private static String Virus_data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<Locationstats> allstats=new ArrayList<>();

    public List<Locationstats> getAllstats() {
        return allstats;
    }

    //Run when the application starts
    @PostConstruct
    //To run it one a regular basis we use @scheduler,in this case to run @first hour of every day
    //cron=second minute hour day month year
    @Scheduled(cron = " * * 1 * * * ")
    public void fetchvirusdata() throws IOException, InterruptedException {
        List<Locationstats> newstats=new ArrayList<>();
        //Http calls using HTTP clients
        HttpClient client=HttpClient.newHttpClient();
        //HTTP request using builder pattern (where do i need go create the request? get the uri)
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(Virus_data_url)).build();
        //Storing the response as synchronous send
        HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
//
        //For parsing the CSV using Apache CSV
        StringReader csvbodyreader=new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyreader);

        for (CSVRecord record : records) {
            Locationstats locationstats = new Locationstats();
            locationstats.setState(record.get("Province/State"));
            locationstats.setCountry(record.get("Country/Region"));
            int latestcases=Integer.parseInt(record.get(record.size() - 1));
            int previousdaycases=Integer.parseInt(record.get(record.size() - 2));
            locationstats.setLatesttotalcases(latestcases);
            locationstats.setDiffrompreviousday(latestcases-previousdaycases);
            System.out.println(latestcases-previousdaycases);

            newstats.add(locationstats);
        }
        this.allstats=newstats;
        }




    }
