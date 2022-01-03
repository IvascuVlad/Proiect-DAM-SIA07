package DAM.controllers;

import DAM.models.EmployeeStudies;
import DAM.repositories.IStudiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/studies")
public class StudiesEntityController {
    @Autowired
    private IStudiesRepository repository;

    @GetMapping
    public ResponseEntity<List<EmployeeStudies>> getAllStudies() {
        List<EmployeeStudies> studies = repository.findAll();
        if(studies.size() > 0){
            return new ResponseEntity<>(studies,new HttpHeaders(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ArrayList<>(),new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeStudies> getStudy(@PathVariable Integer id) {
        List<EmployeeStudies> studies = repository.findAll();
        for(EmployeeStudies study:studies){
            if(study.getstudiesID().equals(id)){
                return new ResponseEntity<>(study, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new EmployeeStudies(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam String institution, @RequestParam String profile, @RequestParam Integer baseSalary){
        EmployeeStudies study = new EmployeeStudies(institution, profile, baseSalary);
        study = repository.save(study);
        return new ResponseEntity<>(study.toString(), HttpStatus.OK);
    }

    @PutMapping("/{institution}")
    public ResponseEntity<String> updateStudy(@PathVariable String institution, @RequestParam String profile, @RequestParam Integer baseSalary) {
        String responseText = "";
        List<EmployeeStudies> studies = repository.findAll();
        for(EmployeeStudies study: studies){
            if(study.getInstitution().equals(institution)){
                //return new ResponseEntity<>(profile, HttpStatus.OK);
                if(!profile.equals("")){
                    study.setProfile(profile);
                    responseText += "Profile updated!";
                }
                if(baseSalary > 0){
                    study.setBaseSalary(baseSalary);
                    responseText += "Base salary updated!";
                }
                repository.save(study);
                return new ResponseEntity<>(responseText, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not a valid institution!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{institution}")
    public ResponseEntity<String> deleteStudy(@PathVariable String institution, @RequestParam String profile) {
        if(profile.equals("")){
            return new ResponseEntity<>("Not a valid profile!", HttpStatus.OK);
        }
        List<EmployeeStudies> studies = repository.findAll();
        for(EmployeeStudies study: studies){
            if(study.getInstitution().equals(institution)){
                repository.delete(study);
                return new ResponseEntity<>("Study deleted!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not a valid institution!", HttpStatus.OK);
    }
}
