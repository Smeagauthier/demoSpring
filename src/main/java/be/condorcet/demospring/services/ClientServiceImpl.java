package be.condorcet.demospring.services;

import be.condorcet.demospring.entities.Client;
import be.condorcet.demospring.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// @Service --> Pour étape 2 du projet
@Transactional(rollbackOn = Exception.class)
public class ClientServiceImpl implements InterfClientService {
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> read(String nom) {
        return clientRepository.findClientsByNomLike(nom+"%");
    }

    @Override // --> Pour les web services
    public Client read(String nom, String prenom, String tel) {
        return clientRepository.findClientsByNomAndPrenomAndTel(nom, prenom, tel);
    }

    @Override
    public Client create(Client client) throws Exception {
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client read(Integer id) throws Exception {
        Optional<Client> ocl= clientRepository.findById(id);
        return ocl.get();
    }

    @Override
    public Client update(Client client) throws Exception {
        read(client.getIdclient());
        clientRepository.save(client);
        return client;
    }

    @Override
    public void delete(Client client) throws Exception {
        clientRepository.deleteById(client.getIdclient());
    }

    @Override
    public List<Client> all() throws Exception {
        return clientRepository.findAll();
    }

    @Override // --> Pour les webservices
    public Page<Client> allp(Pageable pageable) throws Exception {
        return clientRepository.findAll(pageable);
    }
}
