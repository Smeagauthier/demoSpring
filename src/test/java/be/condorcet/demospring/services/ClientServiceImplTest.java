package be.condorcet.demospring.services;

import be.condorcet.demospring.entities.Client;
import be.condorcet.demospring.entities.Comfact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceImplTest {

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @Autowired
    private ComfactServiceImpl comfactServiceImpl;

    Client cl;

    @BeforeEach
    void setUp() {
        try {
            cl = new Client(null,"NomTest","PrenomTest",1000,"LocTest","Rue test","1","0001",new ArrayList<>());
            clientServiceImpl.create(cl);
            System.out.println("Création du client : "+cl);
        } catch(Exception e) {
            System.out.println("Erreur de création du client : "+cl+". Erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            clientServiceImpl.delete(cl);
            System.out.println("Suppression du client : "+cl);
        } catch(Exception e) {
            System.out.println("Erreur de suppression du client : "+cl+". Erreur : "+e);
        }
    }

    @Test
    void read() {
        try {
            int numcli = cl.getIdclient();
            Client cl2 = clientServiceImpl.read(numcli);
            assertEquals("NomTest",cl2.getNom(),"Noms différents "+"NomTest"+"-"+cl2.getNom());
            assertEquals("PrenomTest",cl2.getPrenom(),"Prénoms différents "+"PrenomTest"+"-"+cl2.getPrenom());
        } catch (Exception e) {
            fail("Recherche infructueuse : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,cl.getIdclient(),"ID client non incrémenté");
        assertEquals("NomTest",cl.getNom(),"Nom client non enregistré : "+cl.getNom()+" au lieu de NomTest");
        assertEquals("PrenomTest",cl.getPrenom(),"Prénom client non enregistré : "+cl.getPrenom()+" au lieu de PrenomTest");
        //etc
    }

    @Test
    void creationDoublon() { //ajouté
        Client cl2 = new Client(null,"NomTest","PrenomTest",2000,"LocTest2","Rue test2","1","0001",null);
        Assertions.assertThrows(Exception.class, () -> {
            clientServiceImpl.create(cl2);
        },"Création d'un doublon");
    }

    //@Test
    void testRead() {
    }

    @Test
    void update() {
        try{
            cl.setNom("NomTest2");
            cl.setPrenom("PrenomTest2");
            //etc
            cl = clientServiceImpl.update(cl);
            assertEquals("NomTest2",cl.getNom(),"Noms différents "+"NomTest2-"+cl.getNom());
            assertEquals("PrenomTest2",cl.getPrenom(),"Prénoms différents "+"PrenomTest2-"+cl.getPrenom());
            //etc
        }
        catch(Exception e){
            fail("Erreur de mise à jour : "+e);
        }
    }

    @Test
    void delete() {
        try {
            clientServiceImpl.delete(cl);
            Assertions.assertThrows(Exception.class, () -> {
                clientServiceImpl.read(cl.getIdclient());
            },"Record non effacé");
        } catch (Exception e) {
            fail("Erreur d'effacement : "+e);
        }
    }

    @Test
    void delAvecCom() { //ajouté
        try{
            Comfact cf = new Comfact(null,null, Date.valueOf(LocalDate.now()),"C",new BigDecimal(1000),cl);
            comfactServiceImpl.create(cf);
            cl.getComfacts().add(cf);
            clientServiceImpl.update(cl);
            Assertions.assertThrows(Exception.class, () -> {
                clientServiceImpl.delete(cl);
            },"Effacement réalisé malgré commande liée");
            comfactServiceImpl.delete(cf);
        }catch (Exception e){
            fail("Erreur de création de commande : "+ e);
        }

    }

    @Test
    void rechNom() { //ajouté
        List<Client> lc = clientServiceImpl.read("NomTest");
        boolean trouve=false;
        for(Client c : lc){
            if(c.getNom().startsWith("NomTest"))  trouve=true;
            else fail("Un record ne correspond pas, nom = "+c.getNom());
        }
        assertTrue(trouve,"Record non trouvé dans la liste");
    }

    @Test
    void all() {
        try {
           List<Client> lc = clientServiceImpl.all();
           assertNotEquals(0,lc.size(),"La liste ne contient aucun élément");
        } catch(Exception e) {
            fail("Erreur de recherche de tous les clients : "+e);
        }
    }
}