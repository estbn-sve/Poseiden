package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tradeId;
    @NotBlank(message = "le champs account ne peut pas être vide")
    String account;
    @NotBlank(message = "le champs type ne peut pas être vide")
    String type;
    @NotNull(message="Le champs buyQuantity ne peux pas être vide")
    @Min(0)
    Double buyQuantity;
    @NotNull(message="Le champs sellQuantity ne peux pas être vide")
    @Min(0)
    Double sellQuantity;
    @NotNull(message="Le champs buyPrice ne peux pas être vide")
    @Min(0)
    Double buyPrice;
    @NotNull(message="Le champs sellPrice ne peux pas être vide")
    @Min(0)
    Double sellPrice;
    @NotBlank(message = "le champs benchmark ne peut pas être vide")
    String benchmark;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Le champs tradeDate ne peux pas être vide")
    Date tradeDate;
    @NotBlank(message = "le champs security ne peut pas être vide")
    String security;
    @NotBlank(message = "le champs status ne peut pas être vide")
    String status;
    @NotBlank(message = "le champs trader ne peut pas être vide")
    String trader;
    @NotBlank(message = "le champs book ne peut pas être vide")
    String book;
    @NotBlank(message = "le champs creationName ne peut pas être vide")
    String creationName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Le champs creation_date ne peux pas être vide")
    Date creationDate;
    @NotBlank(message = "le champs creationDate ne peut pas être vide")
    String revisionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Le champs revisionDate ne peux pas être vide")
    Date revisionDate;
    @NotBlank(message = "le champs dealName ne peut pas être vide")
    String dealName;
    @NotBlank(message = "le champs accdealTypeount ne peut pas être vide")
    String dealType;
    @NotBlank(message = "le champs sourceListId ne peut pas être vide")
    String sourceListId;
    @NotBlank(message = "le champs side ne peut pas être vide")
    String side;
}
