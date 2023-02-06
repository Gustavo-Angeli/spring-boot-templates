package com.gusta.template.services;

import com.gusta.template.exceptions.*;
import com.gusta.template.mapper.*;
import com.gusta.template.models.entities.*;
import com.gusta.template.models.vo.*;
import com.gusta.template.repositories.*;
import com.gusta.template.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.gusta.template.mapper.DozerMapper.*;
import static com.gusta.template.utils.ParamValidation.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductVO addProductToStore(ProductVO vo) {
        checkIfIsNullOrBlankThrowingEx(vo.getName(), vo.getPrice());
        if (!checkIfIsNullOrBlank(repository.findByName(vo.getName()))) {
            throw new IllegalArgumentException("This product is already registered");
        }

        repository.save(parseObject(vo, ProductEntity.class));

        return vo;
    }

    public List<ProductVO> getAllProducts() {
        return parseListObjects(repository.findAll(), ProductVO.class);
    }

    public ProductVO updateProduct(ProductVO vo) {
        checkIfIsNullOrBlankThrowingEx(vo.getId(), vo.getName(), vo.getPrice());

        ProductEntity entity = repository.findById(vo.getId())
                .orElseThrow(() -> new RequiredObjectIsNullException("Product not found!"));

        if (!checkIfIsNullOrBlank(repository.findByName(vo.getName()))) {
            throw new IllegalArgumentException();
        }

        entity.setName(vo.getName());
        entity.setPrice(vo.getPrice());

        return parseObject(repository.save(entity), ProductVO.class);
    }

    public void deleteProductById(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RequiredObjectIsNullException("Product not found!"));

        repository.deleteById(id);
    }

}
