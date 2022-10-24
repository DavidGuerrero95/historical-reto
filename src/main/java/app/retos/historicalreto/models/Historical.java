package app.retos.historicalreto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "historical")
@Data
@NoArgsConstructor
public class Historical {

    @Id
    @JsonIgnore
    private String id;

    private String eventId;
    private Integer type;
    private String day;
    private String month;
    private String year;
    private String hour;
    private String minute;
    private String second;
    private Integer typeEmergency;

    @NotEmpty(message = "locacion no puede esta vacia")
    @Size(min = 2, max = 2, message = "Debe tener dos valores")
    private List<Double> location;

    private Integer status;
    private String comment;
    private Integer zoneCode;

    private List<String> photos;
    private List<String> videos;
    private List<String> audios;

    public Historical(String eventId, Integer type, String day, String month, String year, String hour, String minute, String second, Integer typeEmergency, List<Double> location, Integer status, String comment, Integer zoneCode, List<String> photos, List<String> videos, List<String> audios) {
        this.eventId = eventId;
        this.type = type;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.typeEmergency = typeEmergency;
        this.location = location;
        this.status = status;
        this.comment = comment;
        this.zoneCode = zoneCode;
        this.photos = photos;
        this.videos = videos;
        this.audios = audios;
    }
}
