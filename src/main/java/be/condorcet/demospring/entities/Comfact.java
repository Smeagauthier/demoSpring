package be.condorcet.demospring.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "API_COMFACT", schema = "ora25", catalog = "orcl")
public class Comfact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comfact_generator")
    @SequenceGenerator(name = "comfact_generator", sequenceName = "API_COMFACT_SEQ", allocationSize = 1)
    private Integer numcommande;
    private Integer numfact;
    @NonNull
    private Date datecom;
    @NonNull
    private String etat;
    @NonNull
    private BigDecimal montant;
    @NonNull
    @ManyToOne @JoinColumn(name = "IDCLIENT")
    private Client client;
}
