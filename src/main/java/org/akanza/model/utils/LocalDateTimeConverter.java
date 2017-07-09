package org.akanza.model.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;

/**
 * Created by Christian Amani on 09/07/2017.
 */
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime,String>
{
    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute)
    {
        String value = attribute.toString();
        return value.replace("T"," ");
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData)
    {
        String value = dbData.replace(" ","T");
        return LocalDateTime.parse(value);
    }
}
