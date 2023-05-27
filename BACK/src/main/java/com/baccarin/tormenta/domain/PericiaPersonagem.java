package com.baccarin.tormenta.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "pericia_personagem", schema = "tormenta",
        uniqueConstraints = @UniqueConstraint(columnNames = {"personagem_id", "pericia_id"}))
public class PericiaPersonagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "personagem_id", nullable = false)
    private Personagem personagem;

    @OneToOne
    @JoinColumn(name = "pericia_id", nullable = false)
    private Pericia pericia;

    @Column(name = "graduacao")
    private Integer graduacao;

    @Column(name = "outros")
    private Integer outros;

}
