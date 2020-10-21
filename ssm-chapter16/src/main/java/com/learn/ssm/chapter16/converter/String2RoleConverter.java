package com.learn.ssm.chapter16.converter;

import com.learn.ssm.chapter16.pojo.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class String2RoleConverter implements Converter<String, Role> {


    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param str the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Role convert(String str) {
        if(StringUtils.isEmpty(str)){
            return null;
        }
        if(!str.contains("-")){
            return null;
        }
        String[] arr = str.split("-");
        if( arr.length != 3 ){
            return null;
        }
        Role role = new Role();
        role.setId(Long.parseLong(arr[0]));
        role.setRoleName(arr[1]);
        role.setNote(arr[2]);
        return role;
    }
}
