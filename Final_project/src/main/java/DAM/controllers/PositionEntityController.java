package DAM.controllers;

import DAM.models.Contract;
import DAM.models.EmployeeStudies;
import DAM.models.Position;
import DAM.repositories.IPositionRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/position")
public class PositionEntityController{
    @Autowired
    private IPositionRepository repository;

    @GetMapping
    public ResponseEntity<List<Position>> getALLPositions(){
        List<Position> positions = repository.findAll();
        if(positions.size() > 0){
            return new ResponseEntity<>(positions,new HttpHeaders(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ArrayList<>(),new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPosition(@PathVariable Integer id) {
        List<Position> positions = repository.findAll();
        for (Position position : positions) {
            if (position.getPositionID().equals(id)) {
                return new ResponseEntity<>(position, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Position(), HttpStatus.BAD_REQUEST);
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestParam String positionName, @RequestParam String positionDescription, @RequestParam Integer positionRate){
        Position position = new Position(1, positionName, positionDescription, positionRate);
        position = repository.save(position);
        return new ResponseEntity<>(position.toString(), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{positionName}")
    public ResponseEntity<String> updateStudy(@PathVariable String positionName, @RequestParam String positionDescription, @RequestParam Integer positionRate) {
        List<Position> positionList = repository.findAll();
        for(Position position:positionList) {
            if (position.getPositionName().equals(positionName)) {
                if (!positionDescription.equals("")) {
                    position.setPositionDescription(positionDescription);
                }
                if (!positionRate.equals(0)) {
                    position.setPositionRate(positionRate);
                }
                repository.save(position);
                return new ResponseEntity<>("Position updated!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not a valid positionName!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{positionName}")
    public ResponseEntity<String> deleteStudy(@PathVariable String positionName) {
        List<Position> positionList = repository.findAll();
        for(Position position:positionList) {
            if (position.getPositionName().equals(positionName)) {
                repository.delete(position);
                return new ResponseEntity<>("Position deleted!", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not a valid positionName!", HttpStatus.OK);
    }

    public List<Position> getPositions() {
        return null;
    }
}
