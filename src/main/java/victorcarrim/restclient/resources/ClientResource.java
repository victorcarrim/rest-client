package victorcarrim.restclient.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import victorcarrim.restclient.dto.ClientDTO;
import victorcarrim.restclient.services.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDTO> listClients = service.findAllPaged(pageRequest);
        return ResponseEntity.ok(listClients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO client = service.findById(id);
        return ResponseEntity.ok(client);
    }
}
