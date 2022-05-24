package dev.kanto.CyberGame.Repositories;

import dev.kanto.CyberGame.model.Answer;
import dev.kanto.CyberGame.model.Light;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@RepositoryRestResource(collectionResourceRel = "Light", path = "Light")
@CrossOrigin(origins = "http://localhost:3001")
public interface LightRepository extends MongoRepository<Light, String> {
    Light findLightByid(String id);
}