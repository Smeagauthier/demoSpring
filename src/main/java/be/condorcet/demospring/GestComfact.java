package be.condorcet.demospring;

import be.condorcet.demospring.entities.Client;
import be.condorcet.demospring.entities.Comfact;
import be.condorcet.demospring.services.ClientServiceImpl;
import be.condorcet.demospring.services.ComfactServiceImpl;
import be.condorcet.demospring.services.InterfClientService;
import be.condorcet.demospring.services.InterfComfactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comfacts")
public class GestComfact {
    /*@Autowired
    private ComfactRepository comfactRepository;

    @Autowired
    private ClientRepository clientRepository;*/

    /*@Autowired
    private ClientServiceImpl clientServiceImpl; --> Pour étape 2 du projet
    @Autowired
    private ComfactServiceImpl comfactServiceImpl; --> Pour étape 2 du projet*/

    @Autowired
    private InterfClientService clientServiceImpl;

    @Autowired
    private InterfComfactService comfactServiceImpl;

    @RequestMapping("/rechparcli")
    public String read(@RequestParam int idclient, Map<String, Object> model) { System.out.println("Recherche du client n° " + idclient);
        try {
          /*  Optional<Client> ocl = clientRepository.findById(idclient);//findById lance une exception si id inconnu
            Client cl = ocl.get();

            List<Comfact> lcf = comfactRepository.findComfactByClient(cl);*/

            Client cl = clientServiceImpl.read(idclient);
            List<Comfact> lcf = comfactServiceImpl.getComfacts(cl);
            model.put("moncli",cl);
            model.put("clicf", lcf);
        } catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche -------- " + e);
            model.put("Error", e.getMessage());
            return "error";
        }
        return "affclicf";
    }
}
