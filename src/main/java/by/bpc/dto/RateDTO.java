package by.bpc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "DTO, rate")
public class RateDTO {
    @ApiModelProperty(value = "curId", example = "440")
    private Long curId;

    @ApiModelProperty(value = "date", example = "2023-01-10")
    private LocalDate date;

    @ApiModelProperty(value = "curAbbreviation", example = "AUD")
    private String curAbbreviation;

    @ApiModelProperty(value = "curScale", example = "1")
    private Integer curScale;

    @ApiModelProperty(value = "curName", example = "Австралийский доллар")
    private String curName;

    @ApiModelProperty(value = "curOfficialRate", example = "1.8767")
    private Double curOfficialRate;
}
