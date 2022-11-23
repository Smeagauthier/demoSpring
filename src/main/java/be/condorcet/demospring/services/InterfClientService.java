package be.condorcet.demospring.services;

import be.condorcet.demospring.entities.Client;

import java.util.List;

public interface InterfClientService extends InterfService<Client> {
    public List<Client> read(String nom);

    public Client read(String nom, String prenom, String tel); // --> Pour les webservices
}
