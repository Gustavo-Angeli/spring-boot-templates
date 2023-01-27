package com.gusta.template.controller;

import com.gusta.template.model.vo.*;
import com.gusta.template.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/person/")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("createPerson")
    public PersonVO createPerson(@RequestBody PersonVO vo) {
        return service.createPerson(vo);
    }

    @GetMapping("getPersonById/{id}")
    public PersonVO findPersonById(@PathVariable(name = "id") Long id) {
        return service.findPersonById(id);
    }
    @GetMapping("getPersonByName/{name}")
    public PersonVO findPersonById(@PathVariable(name = "name") String name) {
        return service.findByName(name);
    }
    @GetMapping("getAll")
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PutMapping("updatePersonById/{id}")
    public PersonVO updatePersonById(@PathVariable(name = "id") Long id, PersonVO vo) {
        return service.updatePersonById(id, vo);
    }
    @PutMapping("deactivatePersonById/{id}")
    public PersonVO deactivatePersonById(@PathVariable(name = "id") Long id) {
        return service.deactivatePersonById(id);
    }
    @PutMapping("activePersonById/{id}")
    public PersonVO activePersonById(@PathVariable(name = "id") Long id) {
        return service.activePersonById(id);
    }

    @DeleteMapping("deletePersonById/{id}")
    public void deletePersonById(@PathVariable(name = "id") Long id) {
        service.deletePersonById(id);
    }

}
