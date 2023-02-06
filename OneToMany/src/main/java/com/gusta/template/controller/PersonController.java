package com.gusta.template.controller;

import com.gusta.template.models.vo.*;
import com.gusta.template.services.*;
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

    @PutMapping("update-person-by-id/{id}")
    public PersonVO updatePersonById(PersonVO vo) {
        return service.updatePersonById(vo);
    }
    @PutMapping("add-phone-number/{id}")
    public PersonVO addPhoneNumber(@PathVariable(name = "id") Long id, @RequestBody PhoneNumberVO phoneNumberVO) {
        return service.addNumberToPerson(id, phoneNumberVO);
    }

    @DeleteMapping("delete-person-by-id/{id}")
    public void deletePersonById(@PathVariable(name = "id") Long id) {
        service.deletePersonById(id);
    }
    @DeleteMapping("delete-person-number/{id-person}/{id-phone}")
    public PersonVO deletePersonNumber(
            @PathVariable(name = "id-person") Long idPerson,
            @PathVariable(name = "id-phone") Long idPhone
    ) {
        return service.deletePersonNumber(idPerson, idPhone);
    }

}
