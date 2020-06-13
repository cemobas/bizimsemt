package com.krakus.bizimsemt.converter;

import com.krakus.bizimsemt.domain.Gender;
import org.springframework.core.convert.converter.Converter;

import static com.krakus.bizimsemt.domain.Gender.FEMALE;
import static com.krakus.bizimsemt.domain.Gender.MALE;

public class StringToEnumConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        if("M".equals(source)) return MALE;
        if("F".equals(source)) return FEMALE;
        throw new EnumConstantNotPresentException(Gender.class, source);
    }
}
