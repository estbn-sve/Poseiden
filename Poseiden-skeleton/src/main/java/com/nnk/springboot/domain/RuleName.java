package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank(message = "le champs name ne peut pas être vide")
    String name;
    @NotBlank(message = "le champs description ne peut pas être vide")
    String description;
    @NotBlank(message = "le champs json ne peut pas être vide")
    String json;
    @NotBlank(message = "le champs template ne peut pas être vide")
    String template;
    @NotBlank(message = "le champs sqlStr ne peut pas être vide")
    String sqlStr;
    @NotBlank(message = "le champs sqlPart ne peut pas être vide")
    String sqlPart;
}
