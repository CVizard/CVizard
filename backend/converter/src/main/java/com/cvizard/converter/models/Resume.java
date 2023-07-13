package com.cvizard.converter.models;

import java.time.LocalDate;
import java.util.List;

public class Resume {
    private List<Work> work;
    private List<Education> education;
    private List<Certificate> certificates;
    private List<Skill> skills;
    private List<Language> languages;
    private List<Project> projects;
    private List<Interest> interests;
    static private class Work{
        private String name;
        private String position;
        private LocalDate startDate;
        private LocalDate endDate;
        private String summary;
    }
    static private class Education{
        private String institution;
        private String area;
        private String studyType;
        private LocalDate startDate;
        private LocalDate endDate;
    }
    static private class Certificate{
        private String name;
        private LocalDate date;
        private String issuer;
    }
    static private class Skill{
        private String name;
        private String level;
        private List<String> keywords;
    }
    static private class Language{
        private String language;
        private String level;
    }
    static private class Project{
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private String summary;
    }
    static private class Interest{
        private String name;
        private List<String> keywords;
    }


}

