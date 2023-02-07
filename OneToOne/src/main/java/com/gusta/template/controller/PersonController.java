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

    @PostMapping("create-person")
    public PersonVO createPerson(@RequestBody PersonVO vo) {
        return service.createPerson(vo);
    }

    @GetMapping("get-person-by-id/{id}")
    public PersonVO findPersonById(@PathVariable(name = "id") Long id) {
        return service.findPersonById(id);
    }
    @GetMapping("get-person-by-name/{name}")
    public PersonVO findPersonById(@PathVariable(name = "name") String name) {
        return service.findByName(name);
    }
    @GetMapping("get-all")
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PutMapping("update-person-by-id")
    public PersonVO updatePersonById(@RequestBody PersonVO vo) {
        return service.updatePersonById(vo);
    }
    @PutMapping("deactivate-person-by-id/{id}")
    public PersonVO deactivatePersonById(@PathVariable(name = "id") Long id) {
        return service.deactivatePersonById(id);
    }
    @PutMapping("active-person-by-id/{id}")
    public PersonVO activePersonById(@PathVariable(name = "id") Long id) {
        return service.activePersonById(id);
    }

    @DeleteMapping("delete-person-by-id/{id}")
    public void deletePersonById(@PathVariable(name = "id") Long id) {
        service.deletePersonById(id);
    }

}
