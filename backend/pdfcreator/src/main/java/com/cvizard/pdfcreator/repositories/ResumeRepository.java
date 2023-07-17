package com.cvizard.pdfcreator.repositories;

import com.cvizard.pdfcreator.models.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResumeRepository extends MongoRepository<Resume, String> {

}
