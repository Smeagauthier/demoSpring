package be.condorcet.demospring.services;

import be.condorcet.demospring.entities.Client;
import be.condorcet.demospring.entities.Comfact;
import be.condorcet.demospring.repositories.ClientRepository;
import be.condorcet.demospring.repositories.ComfactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

// @Service --> Pour étape 2 du projet
@Transactional(rollbackOn = Exception.class)
public class ComfactServiceImpl implements InterfComfactService {
    @Autowired
    private ComfactRepository comfactRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Comfact create(Comfact comfact) throws Exception {
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public Comfact read(Integer id) throws Exception {
        return comfactRepository.findById(id).get();
    }

    @Override
    public Comfact update(Comfact comfact) throws Exception {
        read(comfact.getNumcommande());
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public void delete(Comfact comfact) throws Exception {
        comfactRepository.deleteById(comfact.getNumcommande());
    }

    @Override
    public List<Comfact> all() throws Exception {
        return comfactRepository.findAll();
    }

    @Override // --> Pour les webservices
    public Page<Comfact> allp(Pageable pageable) throws Exception {
        return comfactRepository.findAll(pageable);
    }

    @Override
    public List<Comfact> getComfacts(Client cl) {
        List<Comfact> lcf = comfactRepository.findComfactByClient(cl);
        return lcf;
    }
}
