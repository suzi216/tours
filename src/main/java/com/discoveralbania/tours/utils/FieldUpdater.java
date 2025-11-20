package com.discoveralbania.tours.utils;

import java.lang.reflect.Field;


public class FieldUpdater {

    public static void updateFields(Object target, Object source, boolean allowSettingNulls) {
        Class<?> classA = target.getClass();
        Class<?> classB = source.getClass();

        for (Field fieldA : classA.getDeclaredFields()) {
            String targetTypeName = fieldA.getAnnotatedType().getType().getTypeName();
            if (targetTypeName.endsWith("UploadedFile")) {
                continue;
            }

            try {
                Field fieldB = classB.getDeclaredField(fieldA.getName());

                String sourceTypeName = fieldB.getAnnotatedType().getType().getTypeName();
                if (sourceTypeName.endsWith("MultipartFile")) {
                    continue;
                }

                fieldA.setAccessible(true);
                fieldB.setAccessible(true);
                Object value = fieldB.get(source);
                if (allowSettingNulls || value != null) {
                    fieldA.set(target, value);
                }

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public static void updateFields(Object target, Object source) {
        Class<?> classA = target.getClass();
        Class<?> classB = source.getClass();

        for (Field fieldA : classA.getDeclaredFields()) {
            String targetTypeName = fieldA.getAnnotatedType().getType().getTypeName();
            if (targetTypeName.endsWith("UploadedFile")) {
                continue;
            }

            try {
                Field fieldB = classB.getDeclaredField(fieldA.getName());

                String sourceTypeName = fieldB.getAnnotatedType().getType().getTypeName();
                if (sourceTypeName.endsWith("MultipartFile")) {
                    continue;
                }

                fieldA.setAccessible(true);
                fieldB.setAccessible(true);
                Object value = fieldB.get(source);
                fieldA.set(target, value);

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}
