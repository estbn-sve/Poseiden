package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank(message = "le champs moodysRating ne peut pas être vide")
    String moodysRating;
    @NotBlank(message = "le champs sandPRating ne peut pas être vide")
    String sandPRating;
    @NotBlank(message = "le champs fitchRating ne peut pas être vide")
    String fitchRating;
    @NotNull(message = "le champs orderNumber ne peut pas être vide")
    @Min(0)
    Integer orderNumber;

}
