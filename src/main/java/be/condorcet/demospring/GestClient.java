package be.condorcet.demospring;

import be.condorcet.demospring.entities.Client;
import be.condorcet.demospring.repositories.ClientRepository;
import be.condorcet.demospring.services.ClientServiceImpl;
import be.condorcet.demospring.services.InterfClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clients")
public class GestClient {
    /*@Autowired
    private ClientRepository clientRepository;*/

    /*@Autowired
    private ClientServiceImpl clientServiceImpl; // Pour étape 2 du projet*/

    @Autowired
    private InterfClientService clientServiceImpl;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche clients");
        try {
            //Collection<Client> lcl= clientRepository.findAll();
            Collection<Client> lcl = clientServiceImpl.all();
            model.put("mesClients", lcl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affichagetousClients";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String nom, @RequestParam String prenom, @RequestParam String tel, Map<String, Object> model){
        System.out.println("création de client");
        Client cl = new Client(nom,prenom,tel);
        try {
            //clientRepository.save(cl);//mise à jour du client avec son id par l'environnement
            //System.out.println(cl);
            //Collection<Client> lcl= clientRepository.findAll();
            clientServiceImpl.create(cl);
            cl = clientServiceImpl.read(cl.getIdclient());
            cl.setCp(1000);
            clientServiceImpl.update(cl);
            model.put("nouvcli",cl);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouveauClient";
    }

    @RequestMapping("/read")
    public String read(@RequestParam int idclient, Map<String, Object> model) {
        System.out.println("Recherche du client n° "+idclient);
        try {
            Client cl = clientServiceImpl.read(idclient);
            model.put("moncli",cl);
        }catch (Exception e) {
            System.out.println("----------Erreur lors de la recherche ----- --- " + e);
            model.put("Error",e.getMessage());
            return "error";
        }
        return "affclient";
    }
}
