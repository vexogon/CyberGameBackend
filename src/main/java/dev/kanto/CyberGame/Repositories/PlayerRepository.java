package dev.kanto.CyberGame.Repositories;

import dev.kanto.CyberGame.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "player", path = "player")
@CrossOrigin(origins = "http://localhost:3001")
public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findPlayerById(String id);
}
