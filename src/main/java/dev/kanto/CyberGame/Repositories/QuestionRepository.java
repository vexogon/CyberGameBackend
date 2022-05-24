package dev.kanto.CyberGame.Repositories;

import dev.kanto.CyberGame.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@RepositoryRestResource(collectionResourceRel = "Question", path = "question")
@CrossOrigin(origins = "http://192.168.240.3:3001")
public interface QuestionRepository extends MongoRepository<Question, String> {
    //Find By ID Data Manager
    Question findQuestionByid(String id);


}

