package be.condorcet.demospring.repositories;

import be.condorcet.demospring.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findClientsByLocaliteEndingWithAndCpAfter(String finloc, Integer cp);
    List<Client> findClientsByNomLike(String nom);

    Client findClientsByNomAndPrenomAndTel(String nom, String prenom, String tel); // --> Pour les web services
}
