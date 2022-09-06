package app.retos.historicalreto.services;

import app.retos.historicalreto.request.FileEventResponse;

import java.util.List;

public interface IHistoricalService {

    String guardarEvento(String eventId, Integer type, String date, String time, String eventDescription,
                          List<Double> location, Integer status, String comment, Integer zoneCode);

    boolean saveFiles(String historicalId, FileEventResponse fileEventResponse);
}
