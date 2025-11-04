package com.vijay.serviceImpl;

import com.vijay.entity.StudentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TemplateService {
    Logger log = LoggerFactory.getLogger(TemplateService.class);

    private static final String TEMPLATE_PATH = "src/main/resources/templates/registrationTemplate.html";

    /**
     * this method to generateRegistrationHtml page
     * @param student  student object
     * @return String
     * @throws IOException error
     * @throws IOException error
     */
    public String generateRegistrationHtml(StudentEntity student) throws IOException, IOException {
        log.info("Inside generateRegistrationHtml method with param {}",student);
        String template = new String(Files.readAllBytes(Paths.get(TEMPLATE_PATH)));

        return template
                .replace("[Student's Name]", student.getFirstName() + " " + student.getLastName())
                .replace("[Your Institution Name]", "Vijay University") // Replace or fetch from student/institution entity
                .replace("[Student's Full Name]", student.getFirstName() + " " + student.getLastName())
                .replace("[Student ID]", student.getId().toString())
                .replace("[Program Name]", "Java Full Course")
                .replace("[Start Date]", student.getCreateAt().toString())
                .replace("[Login Portal URL]", "")
                .replace("[Support Email]", "")
                .replace("[Year]", String.valueOf(student.getCreateAt().getYear()));
    }
}


