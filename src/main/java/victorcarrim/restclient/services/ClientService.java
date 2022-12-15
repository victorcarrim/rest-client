package victorcarrim.restclient.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import victorcarrim.restclient.dto.ClientDTO;
import victorcarrim.restclient.entities.Client;
import victorcarrim.restclient.exceptions.DataBaseException;
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

    @Transactional
    public ClientDTO insert(ClientDTO clientDto){
        Client clientEntity = new Client();
        copyDto(clientDto, clientEntity);
        clientEntity = repository.save(clientEntity);
        return new ClientDTO(clientEntity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDto){
        try {
            Client clientEntity = repository.getReferenceById(id);
            copyDto(clientDto, clientEntity);
            clientEntity = repository.save(clientEntity);
            return new ClientDTO(clientEntity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found");
        }
    }

    @Transactional()
    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("id not found : " + id);
        } catch (DataIntegrityViolationException dt) {
            throw new DataBaseException("Integrity violation");
        }
    }

    private void copyDto(ClientDTO clientDto, Client clientEntity){
        clientEntity.setName(clientDto.getName());
        clientEntity.setCpf(clientDto.getCpf());
        clientEntity.setIncome(clientDto.getIncome());
        clientEntity.setBirthDate(clientDto.getBirthDate());
        clientEntity.setChildren(clientDto.getChildren());
    }

}
