package dev.kanto.CyberGame.Repositories;

import dev.kanto.CyberGame.model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@RepositoryRestResource(collectionResourceRel = "Answer", path = "Answer")
@CrossOrigin(origins = "http://localhost:3001")
public interface AnswerRepository extends MongoRepository<Answer, String> {
    Answer findAnswerByid(String id);
}

