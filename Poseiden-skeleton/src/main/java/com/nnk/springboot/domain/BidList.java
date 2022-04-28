package com.nnk.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer BidListId;
    @Column(name = "account")
    @NotBlank(message = "le champs account ne peut pas être vide")
    String account;
    @Column(name = "type")
    String type;
    @Column(name = "bid_quantity")
    Double bidQuantity;
    @Column(name = "ask_quantity")
    Double askQuantity;
    @Column(name = "bid")
    Double bid;
    @Column(name = "ask")
    Double ask;
    @Column(name = "benchmark")
    String benchmark;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "bid_list_date")
    Date bidListDate;
    @Column(name = "commentary")
    String commentary;
    @Column(name = "security")
    String security;
    @Column(name = "status")
    String status;
    @Column(name = "trader")
    String trader;
    @Column(name = "book")
    String book;
    @Column(name = "creation_name")
    String creationName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "creation_date")
    Date creationDate;
    @Column(name = "revision_name")
    String revisionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "revision_date")
    Date revisionDate;
    @Column(name = "deal_name")
    String dealName;
    @Column(name = "deal_type")
    String dealType;
    @Column(name = "source_list_id")
    String sourceListId;
    @Column(name = "side")
    String side;
}
