package com.cvizard.converter.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Document("cvizard")
public class Resume {
    @Id
    private String id;
//    @JsonProperty(value = "Work")
    private List<Work> work;
//    @JsonProperty(value = "Education")
    private List<Education> education;
//    @JsonProperty(value = "Certificate")
    private List<Certificate> certificates;
//    @JsonProperty(value = "Skill")
    private List<Skill> skills;
//    @JsonProperty(value = "Language")
    private List<Language> languages;
//    @JsonProperty(value = "Project")
    private List<Project> projects;
//    @JsonProperty(value = "Interest")
    private List<Interest> interests;
    
    @NoArgsConstructor
    @Data
    public static class Work{
        private String name;
        private String position;
        private String startDate;
        private String endDate;
        private String summary;
    }
    
    @NoArgsConstructor
    @Data
    public static class Education{
        private String institution;
        private String area;
        private String studyType;
        private String startDate;
        private String endDate;
    }
    
    @NoArgsConstructor
    @Data
    public static class Certificate{
        private String name;
        private String date;
        private String issuer;
    }
    
    @NoArgsConstructor
    @Data
    public static class Skill{
        private String name;
        private String level;
        private List<String> keywords;
    }
    
    @NoArgsConstructor
    @Data
    public static class Language{
        private String language;
        private String level;
    }
    
    @NoArgsConstructor
    @Data
    public static class Project{
        private String name;
        private String startDate;
        private String endDate;
        private String summary;
    }
    
    @NoArgsConstructor
    @Data
    public static class Interest{
        private String name;
        private List<String> keywords;
    }


}

