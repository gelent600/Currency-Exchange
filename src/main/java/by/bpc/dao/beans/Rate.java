package by.bpc.dao.beans;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cur_id")
    private Long curId;

    @Column(name = "date")
    private LocalDate date;

    @Size(max = 255)
    @Column(name = "cur_abbreviation")
    private String curAbbreviation;

    @Column(name = "cur_scale")
    private Integer curScale;

    @Size(max = 255)
    @Column(name = "cur_name")
    private String curName;

    @Column(name = "cur_official_rate")
    private Double curOfficialRate;
}
