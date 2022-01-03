package DAM.controllers;

import DAM.models.EmployeeStudies;
import DAM.models.PersonalData;
import DAM.models.Role;
import DAM.models.User;
import DAM.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserEntityController{
    @Qualifier("IUserRepository")
    @Autowired
    private IUserRepository repository;

    private PersonalDataEntityController personalDataEntityController;

    private StudiesEntityController studiesEntityController;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = repository.findAll();
        if(users.size() > 0){
            return new ResponseEntity<>(users,new HttpHeaders(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ArrayList<>(),new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        List<User> users = repository.findAll();
        for(User user:users){
            if(user.getUserID().equals(id)){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam String userName, @RequestParam String password, @RequestParam String email, @RequestParam Integer personalID, @RequestParam Integer studiesID){
        String urio = "http://localhost:8081/personal/" + personalID;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PersonalData> personalResponse = restTemplate.getForEntity(urio, PersonalData.class);
        PersonalData personalData = personalResponse.getBody();

        urio = "http://localhost:8081/studies/" + studiesID;
        ResponseEntity<EmployeeStudies> studiesResponse = restTemplate.getForEntity(urio, EmployeeStudies.class);
        List<EmployeeStudies> employeeStudiesList = new ArrayList<>();
        employeeStudiesList.add(studiesResponse.getBody());

        User newUser = new User(userName, password,email, Role.TESTER, personalData, employeeStudiesList);
        newUser = repository.save(newUser);
        return new ResponseEntity<>(newUser.toString(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudy(@PathVariable Integer id, @RequestParam String password, @RequestParam String email, @RequestParam Integer personalID, @RequestParam Integer studiesID) {
        List<User> users = repository.findAll();
        for(User user: users) {
            if (user.getUserID().equals(id)) {
                if(!password.equals("-")){
                    user.setPassword(password);
                }
                if(!email.equals("-")){
                    user.setEmail(email);
                }
                if(personalID != -1){
                    String urio = "http://localhost:8081/personal/" + personalID;
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<PersonalData> personalResponse = restTemplate.getForEntity(urio, PersonalData.class);
                    PersonalData personalData = personalResponse.getBody();
                    user.setPersonalData(personalData);
                }
                if(studiesID != -1){
                    String urio = "http://localhost:8081/studies/" + studiesID;
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<EmployeeStudies> studiesResponse = restTemplate.getForEntity(urio, EmployeeStudies.class);
                    List<EmployeeStudies> employeeStudiesList = user.getEmployeeStudies();
                    employeeStudiesList.add(studiesResponse.getBody());
                    user.setEmployeeStudies(employeeStudiesList);
                }
                repository.save(user);
                return new ResponseEntity<>("User updated", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not a valid user id!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStudy(@PathVariable Integer id) {
        List<User> users = repository.findAll();
        for(User user: users){
            if(user.getUserID().equals(id)){
                repository.delete(user);
                return new ResponseEntity<>("User deleted!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not a valid user id!", HttpStatus.OK);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }
}
