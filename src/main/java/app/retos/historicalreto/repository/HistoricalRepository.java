package app.retos.historicalreto.repository;

import app.retos.historicalreto.models.Historical;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface HistoricalRepository extends MongoRepository<Historical, String> {

    @RestResource(path = "find-eventId")
    List<Historical> findByEventId(@Param("eventId") String eventId);

    @RestResource(path = "find-zoneCode")
    List<Historical> findByZoneCode(@Param("zoneCode") Integer zoneCode);

    @RestResource(path = "find-zoneCode-status")
    List<Historical> findByZoneCodeAndStatus(@Param("zoneCode") Integer zoneCode,
                                             @Param("status") Integer status);

    @RestResource(path = "find-zoneCode-type")
    List<Historical> findByZoneCodeAndType(@Param("zoneCode") Integer zoneCode,
                                           @Param("type") Integer type);

    @RestResource(path = "find-zoneCode-day")
    List<Historical> findByZoneCodeAndDay(@Param("zoneCode") Integer zoneCode,
                                          @Param("Day") String Day);

    @RestResource(path = "find-zoneCode-day-hour")
    List<Historical> findByZoneCodeAndDayAndHour(@Param("zoneCode") Integer zoneCode,
                                                 @Param("Day") String Day,
                                                 @Param("hour") String hour);

    @RestResource(path = "find-status")
    List<Historical> findByStatus(@Param("status") Integer status);

    @RestResource(path = "find-type")
    List<Historical> findByType(@Param("type") Integer type);

    @RestResource(path = "find-object")
    Historical findByEventIdAndYearAndMonthAndDayAndHourAndZoneCodeAndLocationAndMinuteAndSecond(@Param("eventId") String eventId, @Param("Year") String Year,
                                                                                                 @Param("month") String month, @Param("day") String day,
                                                                                                 @Param("hour") String hour, @Param("zoneCode") Integer zoneCode,
                                                                                                 @Param("location") List<Double> location, @Param("minute") String minute,
                                                                                                 @Param("second") String second);

}
