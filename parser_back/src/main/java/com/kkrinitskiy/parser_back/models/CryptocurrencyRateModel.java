package com.kkrinitskiy.parser_back.models;

import jakarta.persistence.*;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rates")
public class CryptocurrencyRateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rate;
    @ManyToOne
    @JoinColumn(name = "site_id")
    private CryptocurrencySiteModel site;

}
