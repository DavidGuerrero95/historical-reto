package app.retos.historicalreto.services;

import app.retos.historicalreto.request.FileEventResponse;
import app.retos.historicalreto.request.RequestHistorical;

import java.util.HashMap;
import java.util.List;

public interface IHistoricalService {

    String guardarEvento(String eventId, Integer type, String date, String time, Integer typeEmergency,
                          List<Double> location, Integer status, String comment, Integer zoneCode);

    boolean saveFiles(String historicalId, FileEventResponse fileEventResponse);

    void eliminarTodo();

    HashMap findAllFilters(RequestHistorical requestHistorical);
}
