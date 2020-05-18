package com.yibo.parking.service.Impl.car;

import com.yibo.parking.dao.car.TypeInfoMapper;
import com.yibo.parking.dao.car.TypeMapper;
import com.yibo.parking.entity.car.Car;
import com.yibo.parking.entity.car.Type;
import com.yibo.parking.entity.car.TypeInfo;
import com.yibo.parking.service.TypeService;
import com.yibo.parking.utils.EntityIdGenerate;
import com.yibo.parking.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TypeInfoMapper infoMapper;

    @Autowired
    private CarServiceImpl carService;

    @Override
    public List<Type> getTypes() {
        List<Type> types = typeMapper.getTypes();
        for (Type t : types) {
            List<TypeInfo> infos = infoMapper.gets(t);
            for (TypeInfo info: infos) {
                switch (info.getKey()){
                    case "hour":
                        t.setHour(info.getValue());
                        break;
                    case "halfday":
                        t.setHalfday(info.getValue());
                        break;
                    case "allday":
                        t.setAllday(info.getValue());
                        break;
                    case "week":
                        t.setWeek(info.getValue());
                        break;
                    case "month":
                        t.setMonth(info.getValue());
                        break;
                    default:
                        t.setHalfyear(info.getValue());
                        break;
                }
            }
        }
        return types;
    }

    @Override
    public Type get(String id) {
        Type type = typeMapper.get(id);
        List<TypeInfo> infos = infoMapper.gets(type);
        for (TypeInfo info : infos) {
            switch (info.getKey()){
                case "hour":
                    type.setHour(info.getValue());
                    break;
                case "halfday":
                    type.setHalfday(info.getValue());
                    break;
                case "allday":
                    type.setAllday(info.getValue());
                    break;
                case "week":
                    type.setWeek(info.getValue());
                    break;
                case "month":
                    type.setMonth(info.getValue());
                    break;
                default:
                    type.setHalfyear(info.getValue());
                    break;
            }
        }
        return type;
    }

    @Override
    public int save(Type type) {
        if (type.getId() != null && !type.getId().isEmpty()){
            int i = 0;
            List<TypeInfo> infos = infoMapper.gets(type);
            for (TypeInfo in : infos) {
                System.out.println(in.getTypeId());
                if (type.getHour() != null && in.getKey().equals("hour")){
                    in.setValue(type.getHour());
                }
                if (type.getHalfday() != null && in.getKey().equals("halfday")){
                    in.setValue(type.getHalfday());
                }
                if (type.getAllday() != null && in.getKey().equals("allday")){
                    in.setValue(type.getAllday());
                }
                if (type.getWeek() != null && in.getKey().equals("week")){
                    in.setValue(type.getWeek());
                }
                if (type.getMonth() != null && in.getKey().equals("month")){
                    in.setValue(type.getMonth());
                }
                if (type.getHalfyear() != null && in.getKey().equals("halfyear")){
                    in.setValue(type.getHalfyear());
                }
                if (infoMapper.update(in) != 0)
                    i++;
                else return 0;
            }
            int j = typeMapper.update(type);
            if (i != 0 && j != 0)
                return 1;
            else return 0;
        }else {
            int i = 0;
            type.setId(EntityIdGenerate.generateId());
            String[] fields = ObjectUtils.getFiledName(type);
            //获取属性的名字
            for (String name : fields) {     //遍历所有属性
                if (!name.equals("name") && !name.equals("id") && !name.equals("infos")){
                    Object temp = ObjectUtils.getFieldValueByName(name, type);
                    Integer value = Integer.parseInt(temp == null ? "0":temp.toString());
                    TypeInfo info = new TypeInfo();
                    info.setId(EntityIdGenerate.generateId());
                    info.setTypeId(type.getId());
                    info.setKey(name);
                    info.setValue(value);
                    if (infoMapper.insert(info) != 0)
                    i++;
                    else return 0;
                }
            }
            int j = typeMapper.insert(type);
            if (i != 0 && j != 0)
            return 1;
            else return 0;
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

    public List<TypeInfo> getTypeInfos(String carId){
        Car car = carService.get(carId);
        Type type = typeMapper.get(car.getTypeId());
        return infoMapper.findByTypeId(type.getId());
    }
}
