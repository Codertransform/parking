package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.TypeMapper;
import com.yibo.parking.entity.car.Type;
import com.yibo.parking.service.TypeService;
import com.yibo.parking.utils.EntityIdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getTypes() {
        return typeMapper.getTypes();
    }

    @Override
    public Type get(String id) {
        return typeMapper.get(id);
    }

    @Override
    public int save(Type type) {
        if (type.getId() != null && !type.getId().isEmpty()){
            return typeMapper.update(type);
        }else {
            type.setId(EntityIdGenerate.generateId());
            return typeMapper.insert(type);
        }
    }

    @Override
    public int del(String id) {
        return typeMapper.del(id);
    }

    @Override
    public int dels(String[] ids) {
        int d = 0;
        for (String id : ids) {
            typeMapper.del(id);
            d++;
        }
        return d;
    }
}
