package com.cvizard.converter.repositories;

import com.cvizard.converter.models.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, UUID> {

}