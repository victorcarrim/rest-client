package victorcarrim.restclient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import victorcarrim.restclient.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
