package it.baldilorenzo.utility.utilitylib.jpa;

import jakarta.persistence.Column;

import java.lang.reflect.Field;

public abstract class JpaUtils {

    public static <T> String getColumnName(Class<T> tipoEntita, String nomeCampo){
        try {
            Field field = tipoEntita.getDeclaredField(nomeCampo);
            Column column = field.getAnnotation(Column.class);
            if(column != null)
                return column.name();

        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Property name not found");
        }

        throw new RuntimeException("Property name not found");
    }

}
