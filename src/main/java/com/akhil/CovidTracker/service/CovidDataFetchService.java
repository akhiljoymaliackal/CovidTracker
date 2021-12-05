package com.akhil.CovidTracker.service;

import com.akhil.CovidTracker.model.Data;
import com.akhil.CovidTracker.model.DataBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDataFetchService {

    public static final String COVID_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static List<Data> dataList = new ArrayList<>();
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCovidData() throws IOException, InterruptedException {
        List<Data> newDataList = new ArrayList<>(300);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(COVID_DATA_URL))
                .build();
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        Reader in = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            Data data = new DataBuilder()
                    .setCountry(record.get("Country/Region"))
                    .setState(record.get("Province/State"))
                    .setCasesCount(Integer.parseInt(record.get(record.size()-1)))
                    .setChange((Integer.parseInt(record.get(record.size()-1)))- (Integer.parseInt(record.get(record.size()-2))))
                    .build();
            newDataList.add(data);
        }
        setDataList(newDataList);
    }

    private void setDataList(List<Data> data){
        synchronized (dataList) {
            this.dataList = data;
        }
        System.out.println(" Data set updated \n " + data);
    }

    public List<Data> getCovidData(){
        return this.dataList;
    }
}
