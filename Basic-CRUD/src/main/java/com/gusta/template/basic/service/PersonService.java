package com.gusta.template.basic.service;

import com.gusta.template.basic.model.entities.*;
import com.gusta.template.basic.model.vo.*;
import com.gusta.template.basic.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import static com.gusta.template.basic.utils.ParamValidation.*;
import static com.gusta.template.basic.utils.ParamValidation.checkIfIsNullOrBlank;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonVO createPerson(PersonVO vo) {
        checkIfIsNullOrBlankThrowingEx(vo.getName());

        if (checkIfIsNullOrBlank(vo.getMoney())) {
            vo.setMoney(0D);
        }

        if (checkIfIsNullOrBlank(vo.getActivated())) {
            vo.setActivated(false);
        }

        repository.save(
            PersonEntity.builder()
                .name(vo.getName())
                .money(vo.getMoney())
                .activated(vo.getActivated())
                .build());
        return vo;
    }

    public PersonVO findPersonById(Long id) {
        checkIfIsNullOrBlank(id);

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        return PersonVO.builder()
                .name(entity.getName())
                .money(entity.getMoney())
                .activated(entity.getActivated())
                .build();
    }
    public PersonVO findByName(String name) {
        checkIfIsNullOrBlank(name);

        PersonEntity entity = repository.findByName(name)
                .orElseThrow(() -> new NullPointerException("Person " + name + " nonexistent"));

        return PersonVO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .money(entity.getMoney())
                .activated(entity.getActivated())
                .build();
    }

    public PersonVO updatePersonById(Long id, PersonVO vo) {
        checkIfIsNullOrBlankThrowingEx(id);
        checkIfIsNullOrBlankThrowingEx(vo.getName());
        checkIfIsNullOrBlankThrowingEx(vo.getMoney());
        checkIfIsNullOrBlankThrowingEx(vo.getActivated());

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        if (!vo.getName().equals(entity.getName())) entity.setName(vo.getName());
        if (!vo.getMoney().equals(entity.getMoney())) entity.setMoney(vo.getMoney());
        if (!vo.getActivated().equals(entity.getActivated())) entity.setActivated(vo.getActivated());

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

    public void deletePersonById(Long id) {
        checkIfIsNullOrBlank(id);

        repository.deleteById(id);
    }

}
