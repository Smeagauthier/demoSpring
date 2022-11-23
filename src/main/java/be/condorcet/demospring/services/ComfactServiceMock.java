package be.condorcet.demospring.services;

import be.condorcet.demospring.entities.Client;
import be.condorcet.demospring.entities.Comfact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComfactServiceMock implements InterfComfactService {
    private List<Comfact> lc = new ArrayList<>();
    private int numact = 0;

    @Autowired
    private InterfClientService clientServiceImpl;

    @Override
    public Comfact create(Comfact comfact) throws Exception {
        numact++;
        comfact.setNumcommande(numact);
        Client cl = comfact.getClient();
        if (cl.getComfacts() == null) cl.setComfacts(new ArrayList<>());
        cl.getComfacts().add(comfact);
        lc.add(comfact);
        return comfact;
    }

    @Override
    public Comfact read(Integer id) throws Exception {
        for (Comfact c : lc) {
            if (c.getNumcommande().equals(id)) return c;
        }
        throw new Exception("Code inconnu");
    }

    @Override
    public Comfact update(Comfact comfact) throws Exception {
        Integer id = comfact.getNumcommande();
        Comfact oldCom = read(id);
        oldCom.setNumfact(comfact.getNumfact());
        oldCom.setDatecom(comfact.getDatecom());
        oldCom.setEtat(comfact.getEtat());
        oldCom.setMontant(comfact.getMontant());
        return read(oldCom.getNumcommande());
    }

    @Override
    public void delete(Comfact comfact) throws Exception {
        Iterator<Comfact> itc = lc.iterator();
        while (itc.hasNext()) {
            Comfact cf = itc.next();
            if (cf.getNumcommande().equals(comfact.getNumcommande())) {
                cf.getClient().getComfacts().remove(cf);
                itc.remove();
            }
        }
    }

    @Override
    public List<Comfact> getComfacts(Client cl) {
        return (List<Comfact>) cl.getComfacts();
    }

    @Override
    public List<Comfact> all() throws Exception {
        return lc;
    }

    @Override // --> Pour les webservices
    public Page<Comfact> allp(Pageable pageable) throws Exception {
        return null;
    }
}