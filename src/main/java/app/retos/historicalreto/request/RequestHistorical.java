package app.retos.historicalreto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class RequestHistorical {

    @Min(value = 0, message = "El valor minimo de page debe ser 0")
    private Integer page;
    @Min(value = 0, message = "El valor minimo de size debe ser 0")
    private Integer size;
    private String filter;
    private Boolean order;
}
