package by.it.restapitestapp.dto;

import by.it.restapitestapp.entity.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Employee's permissions")
public class PermissionDto {

    @Schema(description = "Permit name")
    private String title;

    public PermissionDto(Permission permission) {
        this.title = permission.getTitle();
    }
}
