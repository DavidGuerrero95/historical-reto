package app.retos.historicalreto.controllers;

import app.retos.historicalreto.models.Historical;
import app.retos.historicalreto.repository.HistoricalRepository;
import app.retos.historicalreto.request.FileEventResponse;
import app.retos.historicalreto.services.IHistoricalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/historico")
public class HistoricalController {

    @Autowired
    HistoricalRepository historicalRepository;
    @Autowired
    IHistoricalService historicalService;

    @PostMapping("/crear")
    public String crearHistorico(@RequestParam("eventId") String eventId, @RequestParam("type") Integer type,
                                 @RequestParam("date") String date, @RequestParam("time") String time,
                                 @RequestParam("typeEmergency") Integer typeEmergency,
                                 @RequestParam("location") List<Double> location, @RequestParam("status") Integer status,
                                 @RequestParam("comment") String comment, @RequestParam("zoneCode") Integer zoneCode) throws IOException {
        try {
            log.info("Se conecto correctamente");
            return historicalService.guardarEvento(eventId, type, date, time, typeEmergency, location, status, comment, zoneCode);
        } catch (Exception e) {
            throw new IOException("Error crear historico del evento: " + eventId + " Error: " + e.getMessage());
        }
    }

    @PutMapping("/files/{historicalId}")
    public Boolean guardarFiles(@PathVariable("historicalId") String historicalId, @RequestBody FileEventResponse fileEventResponse) throws IOException {
        try {
            return historicalService.saveFiles(historicalId, fileEventResponse);
        } catch (Exception e) {
            throw new IOException("Error en la edicion de archivos " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaHistorico() {
        return historicalRepository.findAll();
    }

    @GetMapping("/listar/zona/{zoneCode}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaZonas(@PathVariable("zoneCode") Integer zoneCode) {
        return historicalRepository.findByZoneCode(zoneCode);
    }

    @GetMapping("/listar/zona/{zoneCode}/status/{status}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaZonasStatus(@PathVariable("zoneCode") Integer zoneCode, @PathVariable("status") Integer status) {
        return historicalRepository.findByZoneCodeAndStatus(zoneCode, status);
    }

    @GetMapping("/listar/zona/{zoneCode}/type/{type}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaZonasType(@PathVariable("zoneCode") Integer zoneCode, @PathVariable("type") Integer type) {
        return historicalRepository.findByZoneCodeAndType(zoneCode, type);
    }

    @GetMapping("/listar/zona/{zoneCode}/day/{day}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaZonasDay(@PathVariable("zoneCode") Integer zoneCode, @PathVariable("day") String day) {
        return historicalRepository.findByZoneCodeAndDay(zoneCode, day);
    }

    @GetMapping("/listar/zona/{zoneCode}/day/{day}/hour/{hour}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaZonasDayHour(@PathVariable("zoneCode") Integer zoneCode, @PathVariable("day") String day, @PathVariable("hour") String hour) {
        return historicalRepository.findByZoneCodeAndDayAndHour(zoneCode, day,hour);
    }

    @GetMapping("/listar/status/{status}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaStatus(@PathVariable("status") Integer status) {
        return historicalRepository.findByStatus(status);
    }

    @GetMapping("/listar/type/{type}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Historical> listaType(@PathVariable("type") Integer type) {
        return historicalRepository.findByType(type);
    }

    @DeleteMapping("/eliminar-todo")
    @ResponseStatus(code = HttpStatus.OK)
    public void eliminarTodo(){
        historicalService.eliminarTodo();
    }

}