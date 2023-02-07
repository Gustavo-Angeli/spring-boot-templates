package com.gusta.template.service;

import com.gusta.template.mapper.*;
import com.gusta.template.model.entities.*;
import com.gusta.template.model.vo.*;
import com.gusta.template.repository.*;
import com.gusta.template.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.gusta.template.utils.ParamValidation.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public PersonVO createPerson(PersonVO vo) {
        checkIfIsNullOrBlankThrowingEx(vo.getName());

        if (checkIfIsNullOrBlank(vo.getMoney())) vo.setMoney(0D);
        if (checkIfIsNullOrBlank(vo.getActivated())) vo.setActivated(false);
        if (checkIfIsNullOrBlank(vo.getInfo().getCpf())) vo.getInfo().setCpf(null);
        if (checkIfIsNullOrBlank(vo.getInfo().getDateOfBirth())) vo.getInfo().setDateOfBirth(null);

        if (!checkIfIsNullOrBlank(vo.getInfo().getDateOfBirth())) {
            vo.getInfo().setDateOfBirth(FormattedBirthDate.formatted(vo.getInfo().getDateOfBirth()));

        }
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
        checkIfIsNullOrBlankThrowingEx(vo.getId(), vo.getName(), vo.getMoney());

        PersonEntity entity = repository.findById(vo.getId())
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        if (!vo.getName().equals(entity.getName())) entity.setName(vo.getName());
        if (!vo.getMoney().equals(entity.getMoney())) entity.setMoney(vo.getMoney());
        if (checkIfIsNullOrBlank(vo.getActivated())) entity.setActivated(false);

        repository.save(entity);

        return DozerMapper.parseObject(entity, PersonVO.class);
    }
    public PersonVO deactivatePersonById(Long id) {
        checkIfIsNullOrBlankThrowingEx(id);

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        entity.setActivated(false);

        repository.save(entity);

        return DozerMapper.parseObject(entity, PersonVO.class);
    }
    public PersonVO activePersonById(Long id) {
        checkIfIsNullOrBlankThrowingEx(id);

        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NullPointerException("Person not found!"));

        entity.setActivated(true);

        repository.save(entity);

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public void deletePersonById(Long id) {
        checkIfIsNullOrBlankThrowingEx(id);

        if (checkIfIsNullOrBlank(repository.findById(id))) throw new NullPointerException("Person not exists");

        repository.deleteById(id);
    }

}