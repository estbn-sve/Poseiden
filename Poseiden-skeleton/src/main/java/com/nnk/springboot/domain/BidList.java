package com.nnk.springboot.domain;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer BidListId;
    @Column(name = "account")
    @NotBlank(message = "Le champs account ne peut pas être vide")
    String account;
    @Column(name = "type")
    @NotBlank(message = "Le champs type ne peux pas être vide")
    String type;
    @Column(name = "bid_quantity")
    @NotNull(message="Le champs bidQuantity ne peux pas être vide")
    @Min(0)
    double bidQuantity;
    @Column(name = "ask_quantity")
    @NotNull(message="Le champs askQuantity ne peux pas être vide")
    @Min(0)
    double askQuantity;
    @Column(name = "bid")
    @NotNull(message="Le champs bid ne peux pas être vide")
    @Min(0)
    double bid;
    @Column(name = "ask")
    @NotNull(message="Le champs ask ne peux pas être vide")
    @Min(0)
    double ask;
    @Column(name = "benchmark")
    @NotBlank(message = "Le champs benchmark ne peux pas être vide")
    String benchmark;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "bid_list_date")
    @NotNull(message = "Le champs bid_list_date ne peux pas être vide")
    Date bidListDate;
    @Column(name = "commentary")
    @NotBlank(message = "Le champs commentary ne peux pas être vide")
    String commentary;
    @Column(name = "security")
    @NotBlank(message = "Le champs security ne peux pas être vide")
    String security;
    @Column(name = "status")
    @NotBlank(message = "Le champs status ne peux pas être vide")
    String status;
    @Column(name = "trader")
    @NotBlank(message = "Le champs trader ne peux pas être vide")
    String trader;
    @Column(name = "book")
    @NotBlank(message = "Le champs book ne peux pas être vide")
    String book;
    @Column(name = "creation_name")
    @NotBlank(message = "Le champs creationName ne peux pas être vide")
    String creationName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "creation_date")
    @NotNull(message = "Le champs creation_date ne peux pas être vide")
    Date creationDate;
    @Column(name = "revision_name")
    @NotBlank(message = "Le champs revisionName ne peux pas être vide")
    String revisionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "revision_date")
    @NotNull(message = "Le champs revision_date ne peux pas être vide")
    Date revisionDate;
    @Column(name = "deal_name")
    @NotBlank(message = "Le champs dealName ne peux pas être vide")
    String dealName;
    @Column(name = "deal_type")
    @NotBlank(message = "Le champs dealType ne peux pas être vide")
    String dealType;
    @Column(name = "source_list_id")
    @NotBlank(message = "Le champs sourceListId ne peux pas être vide")
    String sourceListId;
    @Column(name = "side")
    @NotBlank(message = "Le champs side ne peux pas être vide")
    String side;
}
