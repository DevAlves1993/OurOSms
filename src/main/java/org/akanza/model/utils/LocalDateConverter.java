package org.akanza.model.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

/**
 * Created by Christian Amani on 09/07/2017.
 */
@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate,String>
{
    @Override
    public String convertToDatabaseColumn(LocalDate attribute)
    {
        return attribute.toString();
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData)
    {
        return LocalDate.parse(dbData);
    }
}
