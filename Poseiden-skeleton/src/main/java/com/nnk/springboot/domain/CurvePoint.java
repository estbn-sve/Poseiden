package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull(message = "le champs curveId ne peut pas être vide")
    @Min(0)
    Integer curveId;
    @NotNull(message = "le champs asOfDate ne peut pas être vide")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date asOfDate;
    @NotNull(message = "le champs term ne peut pas être vide")
    @Min(0)
    double term;
    @NotNull(message = "le champs value ne peut pas être vide")
    @Min(0)
    double value;
    @NotNull(message = "le champs creationDate ne peut pas être vide")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date creationDate;
}
