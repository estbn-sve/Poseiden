package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer BidListId;
    String account;
    String type;
    Double bidQuantity;
    Double askQuantity;
    Double bid;
    Double ask;
    String benchmark;
    LocalDateTime bidListDate;
    String commentary;
    String security;
    String status;
    String trader;
    String book;
    String creationName;
    LocalDateTime creationDate;
    String revisionName;
    LocalDateTime revisionDate;
    String dealName;
    String dealType;
    String sourceListId;
    String side;

}
