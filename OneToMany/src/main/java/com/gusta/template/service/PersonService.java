package com.gusta.template.service;

import com.gusta.template.model.entities.*;
import com.gusta.template.model.vo.*;
import com.gusta.template.repository.*;
import com.gusta.template.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

import static com.gusta.template.utils.ParamValidation.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonVO createPerson(PersonVO vo) {
        checkIfIsNullOrBlankThrowingEx(vo.getName());

        if (checkIfIsNullOrBlank(vo.getMoney())) vo.setMoney(0D);
        if (checkIfIsNullOrBlank(vo.getActivated())) vo.setActivated(false);

        repository.save(
                PersonEntity.builder()
                        .name(vo.getName())
                        .money(vo.getMoney())
                        .activated(vo.getActivated())
                        .build());
        return vo;
    }

    public PersonVO findPersonById(Long id) {
        checkIfIsNullOrBlankThrowingEx(id);

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        return PersonVO.builder()
                .name(entity.getName())
                .money(entity.getMoney())
                .activated(entity.getActivated())
                .build();
    }
    public PersonVO findByName(String name) {
        checkIfIsNullOrBlankThrowingEx(name);

        PersonEntity entity = repository.findByName(name)
                .orElseThrow(() -> new NullPointerException("Person " + name + " nonexistent"));

        return PersonVO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .money(entity.getMoney())
                .activated(entity.getActivated())
                .build();
    }
    public List<PersonVO> findAll() {
        List<PersonEntity> entityList = repository.findAll();
        List<PersonVO> voList = new ArrayList<>();

        entityList.stream()
                .forEach(e -> voList.add(
                        PersonVO.builder()
                            .id(e.getId())
                            .name(e.getName())
                            .money(e.getMoney())
                            .activated(e.getActivated())
                            .numbers(e.getNumbers())
                            .build()
                        )
                );

        return voList;
    }

    public PersonVO updatePersonById(Long id, PersonVO vo) {
        checkIfIsNullOrBlankThrowingEx(id, vo.getName(), vo.getMoney());

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        if (!vo.getName().equals(entity.getName())) entity.setName(vo.getName());
        if (!vo.getMoney().equals(entity.getMoney())) entity.setMoney(vo.getMoney());
        if (checkIfIsNullOrBlank(vo.getActivated())) vo.setActivated(false);

        repository.save(entity);

        return vo;
    }
    public PersonVO deactivatePersonById(Long id) {
        checkIfIsNullOrBlankThrowingEx(id);

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        entity.setActivated(false);

        repository.save(entity);

        return PersonVO.builder()
                .name(entity.getName())
                .activated(entity.getActivated())
                .build();
    }
    public PersonVO activePersonById(Long id) {
        checkIfIsNullOrBlankThrowingEx(id);

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        entity.setActivated(true);

        repository.save(entity);

        return PersonVO.builder()
                .name(entity.getName())
                .activated(entity.getActivated())
                .build();
    }
    public PersonVO addNumberToPerson(Long id, PhoneNumberVO numberVO) {
        checkIfIsNullOrBlankThrowingEx(id, numberVO.getPhoneNumber());

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        entity.getNumbers().add(
                PhoneNumberEntity.builder()
                        .phoneNumber(numberVO.getPhoneNumber())
                        .build()
        );

        repository.save(entity);

        return PersonVO.builder()
                .name(entity.getName())
                .money(entity.getMoney())
                .activated(entity.getActivated())
                .numbers(entity.getNumbers())
                .build();
    }

    public void deletePersonById(Long id) {
        ParamValidation.checkIfIsNullOrBlankThrowingEx(id);

        repository.deleteById(id);
    }
    public PersonVO deletePersonNumber(Long idPerson, Long idPhone) {
        PersonEntity entity = repository.findById(idPerson)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        List<PhoneNumberEntity> list = entity.getNumbers().stream()
                .filter(p -> !p.getId().equals(idPhone))
                .collect(Collectors.toList());

        entity.setNumbers(list);

        repository.save(entity);

        return PersonVO.builder()
                .name(entity.getName())
                .numbers(entity.getNumbers())
                .build();
    }

}