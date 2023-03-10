package app.retos.historicalreto.services;

import app.retos.historicalreto.models.Historical;
import app.retos.historicalreto.repository.HistoricalRepository;
import app.retos.historicalreto.request.FileEventResponse;
import app.retos.historicalreto.request.RequestHistorical;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
@Service
@Slf4j
public class HistoricalServiceImpl implements IHistoricalService {

    @Autowired
    HistoricalRepository historicalRepository;

    @Override
    public String guardarEvento(String eventId, Integer type, String date, String time, Integer typeEmergency, List<Double> location, Integer status, String comment, Integer zoneCode) {
        List<Historical> historicalList = historicalRepository.findByEventId(eventId);
        if(historicalList.size() > 5){
            Historical h = historicalList.get(0);
            historicalRepository.delete(h);
        }
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        String hour = time.substring(0, 2);
        String minute = time.substring(3, 5);
        String second = time.substring(6, 8);
        Historical historical = new Historical(eventId, type, day, month, year, hour, minute, second, typeEmergency, location, status,
                comment, zoneCode, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        historicalRepository.save(historical);
        Historical h = historicalRepository.findByEventIdAndYearAndMonthAndDayAndHourAndZoneCodeAndLocationAndMinuteAndSecond(eventId, year, month, day, hour, zoneCode, location, minute, second);
        return h.getId();
    }

    @Override
    public boolean saveFiles(String historicalId, FileEventResponse fileEventResponse) {
        Optional<Historical> historical = historicalRepository.findById(historicalId);
        if(historical.isPresent()){
            Historical h = historical.get();
            h.setAudios(fileEventResponse.getAudios());
            h.setPhotos(fileEventResponse.getPhotos());
            h.setVideos(fileEventResponse.getVideos());
            historicalRepository.save(h);
            return true;
        }
        return false;
    }

    @Override
    public void eliminarTodo() {
        historicalRepository.deleteAll();
    }

    @Override
    public HashMap findAllFilters(RequestHistorical requestHistorical) {
        Pageable pageable;
        if(requestHistorical.getSize() == null) requestHistorical.setSize(0);
        if(requestHistorical.getPage() == null) requestHistorical.setPage(0);
        if(requestHistorical.getFilter() == null) requestHistorical.setFilter("id");
        if(requestHistorical.getOrder() == null) requestHistorical.setOrder(true);
        if (requestHistorical.getOrder()) {
            pageable = PageRequest.of(requestHistorical.getPage(), requestHistorical.getSize(),
                    Sort.by(requestHistorical.getFilter()).ascending());
        } else {
            pageable = PageRequest.of(requestHistorical.getPage(), requestHistorical.getSize(),
                    Sort.by(requestHistorical.getFilter()).descending());
        }
        Page<Historical> all = historicalRepository.findAll(pageable);
        HashMap response = new HashMap<>();
        response.put("TotalData",all.getTotalElements());
        response.put("totalPages",all.getTotalPages());
        response.put("data",all.get());
        return response;
    }
}
