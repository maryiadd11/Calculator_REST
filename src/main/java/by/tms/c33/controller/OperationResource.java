package by.tms.c33.controller;

import by.tms.c33.entity.Operation;
import by.tms.c33.repository.OperationRepository;
import by.tms.c33.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/operation")
public class OperationResource {

    private final CalcService calcService;

    public OperationResource(CalcService calcService) {
        this.calcService = calcService;
    }

    @Autowired
    private OperationRepository operationRepository;


    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Operation>> getAll(){
        return ResponseEntity.ok(operationRepository.findAll());
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<Operation> getById(@RequestParam long id){
        Optional<Operation> byId = operationRepository.findById(id);
        return ResponseEntity.of(byId);
    }

    @GetMapping(path = "/getByType")
    public ResponseEntity<Optional<List<Operation>>> getByType(String type){
        return ResponseEntity.ok(operationRepository.findAllByType(type));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Operation> save(@RequestBody Operation operation) {
        operation.setResult(calcService.calc(operation));
        Operation saved = operationRepository.save(operation);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }
}
