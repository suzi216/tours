package com.discoveralbania.tours.utils;

import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.Set;

public class FieldUpdater {

    // Fields that should never be copied from DTO to Entity
    private static final Set<String> IGNORED_FIELDS = Set.of(
            "id",
            "createdAt",
            "updatedAt",
            "deletedAt"
    );

    public static void updateFields(Object target, Object source) {
        updateFields(target, source, false);
    }

    public static void updateFields(Object target, Object source, boolean allowSettingNulls) {

        Class<?> targetClass = target.getClass();
        Class<?> sourceClass = source.getClass();

        for (Field targetField : targetClass.getDeclaredFields()) {

            // Ignore system fields
            if (IGNORED_FIELDS.contains(targetField.getName())) {
                continue;
            }

            Field sourceField;

            try {
                sourceField = sourceClass.getDeclaredField(targetField.getName());
            } catch (NoSuchFieldException e) {
                // DTO simply doesn't contain this field
                continue;
            }

            // Skip MultipartFile and List<MultipartFile>
            String genericType = sourceField.getGenericType().getTypeName();

            if (MultipartFile.class.isAssignableFrom(sourceField.getType())
                    || genericType.contains("MultipartFile")) {
                continue;
            }

            try {
                targetField.setAccessible(true);
                sourceField.setAccessible(true);

                Object value = sourceField.get(source);

                if (allowSettingNulls || value != null) {
                    targetField.set(target, value);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(
                        "Failed to copy field: " + targetField.getName(), e);
            }
        }
    }
}