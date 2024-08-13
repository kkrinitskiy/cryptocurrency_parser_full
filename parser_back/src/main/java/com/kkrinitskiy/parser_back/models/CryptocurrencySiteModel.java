package com.kkrinitskiy.parser_back.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sites")
public class CryptocurrencySiteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "site_name")
    private String name;
    private String url;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
    mappedBy = "site")
    private List<CryptocurrencyRateModel> rates;

}
