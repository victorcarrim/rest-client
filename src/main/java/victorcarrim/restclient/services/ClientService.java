package victorcarrim.restclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import victorcarrim.restclient.dto.ClientDTO;
import victorcarrim.restclient.entities.Client;
import victorcarrim.restclient.exceptions.ResourceNotFoundException;
import victorcarrim.restclient.repositories.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
        Page<Client> listClients = repository.findAll(pageRequest);
        Page<ClientDTO> listDtoClients = listClients.map(x -> new ClientDTO(x));
        return listDtoClients;
    }


    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> clientOptional = repository.findById(id);
        Client entity = clientOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClientDTO(entity);
    }

}
