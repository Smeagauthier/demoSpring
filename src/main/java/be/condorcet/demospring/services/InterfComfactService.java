package be.condorcet.demospring.services;

import be.condorcet.demospring.entities.Client;
import be.condorcet.demospring.entities.Comfact;

import java.util.List;

public interface InterfComfactService extends InterfService<Comfact> {
    public List<Comfact> getComfacts(Client cl);
}
