package DAM.controllers;

import DAM.models.EmployeeStudies;
import DAM.models.PersonalData;
import DAM.repositories.IPersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/personal")
public class PersonalDataEntityController {
    @Autowired
    private IPersonalDataRepository repository;

    @GetMapping
    public ResponseEntity<List<PersonalData>> getStudy() {
        List<PersonalData> personalDatas = repository.findAll();
        if(personalDatas.size() > 0){
            return new ResponseEntity<>(personalDatas,new HttpHeaders(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ArrayList<>(),new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalData> getPersonalData(@PathVariable Integer id) {
        List<PersonalData> personalDatas = repository.findAll();
        for(PersonalData personalData: personalDatas){
            if(personalData.getDataID().equals(id)){
                return new ResponseEntity<>(personalData, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new PersonalData(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNo, @RequestParam String address){
        PersonalData personalData = new PersonalData(firstName, lastName, phoneNo, address);
        personalData = repository.save(personalData);
        return new ResponseEntity<>(personalData.toString(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudy(@PathVariable Integer id, @RequestParam String lastName, @RequestParam String phoneNo, @RequestParam String address) {
        if( lastName.equals("")){
            return new ResponseEntity<>("Not a valid name!", HttpStatus.OK);
        }
        List<PersonalData> personalDatas = repository.findAll();
        for(PersonalData personalData: personalDatas){
            if(personalData.getDataID().equals(id)){
                //personalData.setPhoneNo(phoneNo);
                personalData.setAddress(address);
                repository.save(personalData);
                return new ResponseEntity<>("Personal data updated!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not found!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Integer id) {
        List<PersonalData> personalDatas = repository.findAll();
        for(PersonalData personalData: personalDatas){
            if(personalData.getDataID().equals(id)){
                repository.delete(personalData);
                return new ResponseEntity<>("Personal data deleted!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not found!", HttpStatus.OK);
    }
}
