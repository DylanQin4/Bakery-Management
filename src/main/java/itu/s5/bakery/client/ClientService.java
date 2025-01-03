package itu.s5.bakery.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    } 

     public List<Client> getAllClient(){
        return clientRepository.findAll();   
    }

    public Optional<Client> getClientById(Long id){
        return clientRepository.findById(id);
    }

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteClientById(Long Id){
        clientRepository.deleteById(Id);
    }
}
