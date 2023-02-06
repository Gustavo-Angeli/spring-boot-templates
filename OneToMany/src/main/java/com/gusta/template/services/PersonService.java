package com.gusta.template.services;

import com.gusta.template.mapper.*;
import com.gusta.template.models.entities.*;
import com.gusta.template.models.vo.*;
import com.gusta.template.repository.*;
import com.gusta.template.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

import static com.gusta.template.utils.ParamValidation.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonVO createPerson(PersonVO vo) {
        checkIfIsNullOrBlankThrowingEx(vo.getName());

        repository.save(DozerMapper.parseObject(vo, PersonEntity.class));
        return vo;
    }

    public PersonVO findPersonById(Long id) {
        checkIfIsNullOrBlankThrowingEx(id);

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }
    public PersonVO findByName(String name) {
        checkIfIsNullOrBlankThrowingEx(name);

        PersonEntity entity = repository.findByName(name)
                .orElseThrow(() -> new NullPointerException("Person " + name + " nonexistent"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }
    public List<PersonVO> findAll() {
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO updatePersonById(PersonVO vo) {
        checkIfIsNullOrBlankThrowingEx(vo.getId(), vo.getName());

        PersonEntity entity = repository.findById(vo.getId())
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        if (!vo.getName().equals(entity.getName())) entity.setName(vo.getName());

        repository.save(entity);

        return vo;
    }
    public PersonVO addNumberToPerson(Long id, PhoneNumberVO numberVO) {
        checkIfIsNullOrBlankThrowingEx(id, numberVO.getPhoneNumber());

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        entity.getNumbers().add(DozerMapper.parseObject(numberVO, PhoneNumberEntity.class));

        repository.save(entity);

        return DozerMapper.parseObject(entity, PersonVO.class);
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

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

}