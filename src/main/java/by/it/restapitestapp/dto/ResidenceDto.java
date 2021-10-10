package by.it.restapitestapp.dto;

import by.it.restapitestapp.entity.Residence;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Employee's residence")
public class ResidenceDto {
    private String city;
    private String address;

    public ResidenceDto(Residence residence) {
        this.city = residence.getCity();
        this.address = residence.getAddress();
    }
}
