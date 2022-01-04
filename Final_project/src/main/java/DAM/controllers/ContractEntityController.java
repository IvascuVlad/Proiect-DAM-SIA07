package DAM.controllers;

import DAM.models.*;
import DAM.repositories.IContractEntityRepository;
import DAM.services.ContractEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractEntityController {
    @Autowired
    private IContractEntityRepository repository;

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts(){
        List<Contract> contracts = repository.findAll();
        if(contracts.size() > 0){
            return new ResponseEntity<>(contracts,new HttpHeaders(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ArrayList<>(),new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable Integer id) {
        List<Contract> contracts = repository.findAll();
        for(Contract contract:contracts){
            if(contract.getContractID().equals(id)){
                return new ResponseEntity<>(contract, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Contract(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam Integer userID, @RequestParam String positionsIDs){
        List<Contract> contracts = repository.findAll();
        for(Contract contract:contracts){
            if(contract.getUser().getUserID().equals(userID)){
                return new ResponseEntity<>("This user already has a contract", HttpStatus.BAD_REQUEST);
            }
        }
        String urio = "http://localhost:8081/user/" + userID;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> userResponse = restTemplate.getForEntity(urio, User.class);
        if(userResponse.getStatusCode() == HttpStatus.BAD_REQUEST){
            return new ResponseEntity<>("Not a valid user!", HttpStatus.BAD_REQUEST);
        }
        User user = userResponse.getBody();

        String [] positionsIDsArray = positionsIDs.split(",+");
        Position emptyPosition = new Position();
        if(positionsIDsArray.length == 1) {
            urio = "http://localhost:8081/position/" + positionsIDsArray[0];
            ResponseEntity<Position> positionResponse = restTemplate.getForEntity(urio, Position.class);
            Position position = positionResponse.getBody();

            if (position.equals(emptyPosition)) {
                return new ResponseEntity<>("Not a valid position!", HttpStatus.BAD_REQUEST);
            }
            ContractEntityFactory contractEntityFactory = new ContractEntityFactory();
            Contract newContract = contractEntityFactory.buildSimpleContract(user, position);
            //return new ResponseEntity<>(newContract.toString(), HttpStatus.BAD_REQUEST);
            List <Position> tmpPositionsList = new ArrayList<>();
            tmpPositionsList.add(position);
            newContract = new Contract(user,tmpPositionsList);
            repository.save(newContract);
            return new ResponseEntity<>(newContract.toString(), HttpStatus.CREATED);
        }else if (positionsIDsArray.length == 2){
            //Position position1 = null, position2= null;

            urio = "http://localhost:8081/position/" + positionsIDsArray[0];
            ResponseEntity<Position> positionResponse = restTemplate.getForEntity(urio, Position.class);
            Position position1 = positionResponse.getBody();

            if(position1.equals(emptyPosition)){
                return new ResponseEntity<>("Not a valid first position!", HttpStatus.BAD_REQUEST);
            }

            urio = "http://localhost:8081/position/" + positionsIDsArray[1];
            positionResponse = restTemplate.getForEntity(urio, Position.class);
            Position position2 = positionResponse.getBody();

            if(position2.equals(emptyPosition)){
                return new ResponseEntity<>("Not a valid second position!", HttpStatus.BAD_REQUEST);
            }

            ContractEntityFactory contractEntityFactory =  new ContractEntityFactory();
            Contract newContract = contractEntityFactory.buildContractWith2P(user, position1, position2);
            repository.save(newContract);
            return new ResponseEntity<>(newContract.toString(), HttpStatus.CREATED);
        }else{
            List <Position> tmpPositionsList = new ArrayList<>();
            for(String positionID:positionsIDsArray){
                urio = "http://localhost:8081/position/" + positionID;
                ResponseEntity<Position> positionResponse = restTemplate.getForEntity(urio, Position.class);

                if(positionID.equals(emptyPosition)){
                    return new ResponseEntity<>("Not a valid position with ID= " + positionID + "!", HttpStatus.BAD_REQUEST);
                }
                tmpPositionsList.add(positionResponse.getBody());
            }
            ContractEntityFactory contractEntityFactory =  new ContractEntityFactory();
            Contract newContract = contractEntityFactory.buildPositionWithNP(user, tmpPositionsList);
            repository.save(newContract);
            return new ResponseEntity<>(newContract.toString(), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(@PathVariable Integer id, @RequestParam String positionID) {
        List<Contract> contracts = repository.findAll();
        for(Contract contract:contracts){
            if(contract.getContractID().equals(id)){
                String urio = "http://localhost:8081/position/" + positionID;
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Position> positionResponse = restTemplate.getForEntity(urio, Position.class);
                Position position = positionResponse.getBody();
                Position emptyPosition = new Position();

                if (position.equals(emptyPosition)) {
                    return new ResponseEntity<>("Not a valid position!", HttpStatus.BAD_REQUEST);
                }

                List <Position> tmpPositionsList = contract.getPositions();
                tmpPositionsList.add(position);
                contract.setReleases(tmpPositionsList);
                repository.save(contract);
                return new ResponseEntity<>("The simple contract was created!", HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("Not a valid contract id!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable Integer id) {
        List<Contract> contracts = repository.findAll();
        for (Contract contract : contracts) {
            if(contract.getContractID().equals(id)) {
                repository.delete(contract);
                return new ResponseEntity<>("Contarct with id = " + id + " removed", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Contract not found", HttpStatus.BAD_REQUEST);
    }
}
