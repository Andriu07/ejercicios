package SSSO.Detail_Maintence.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class DetailMaintenanceDTO {

     private Long id_detail_maintenance;

     @NotBlank
     private String name_maintenance;

     @NotBlank
     private String total_time;

     @NotBlank
     private String description;

     @NotBlank
     private String activity;

     @NotBlank
     private String img_maintenance;

     @NotNull
     private Long id_assign_maintenance;

     @NotNull
     private Long id_type_maintenance;
}
