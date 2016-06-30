package com.vkg.tools.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WorksheetComparator<T> {
    private Workbook workbook;
    private String sheetTwoName;
    private String sheetOneName;
    private final Map<Field, Integer> fieldMap;
    private final Set<Field> keyFields;
    private final Class<T> clazz;
    private final boolean allowDuplicate;

    public WorksheetComparator(String workbookPath, Class<T> clazz) {
        try {
            workbook = new XSSFWorkbook(getInputStream(workbookPath));
            final com.vkg.tools.excel.Sheet sheet = clazz.getAnnotation(com.vkg.tools.excel.Sheet.class);
            this.sheetOneName = sheet.oldName();
            this.sheetTwoName = sheet.newName();
            this.allowDuplicate = sheet.allowDuplicate();
            this.clazz = clazz;
            fieldMap = prepareMap(clazz, this.sheetOneName);
            keyFields = filterFields(fieldMap.keySet(), Key.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends Annotation> Set<Field> filterFields(final Set<Field> fields, Class<T> annotationClass) {
        return fields.stream().filter(f -> f.getAnnotation(annotationClass) != null).collect(Collectors.toSet());
    }

    private Map<Field, Integer> prepareMap(final Class<T> clazz, String sheetName) {
        Map<Field, Integer> map = new HashMap<Field, Integer>();
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        if(rowCount > 1) {
            final Field[] fields = clazz.getDeclaredFields();
            Row row = sheet.getRow(0);
            final int cellCount = row.getPhysicalNumberOfCells();
            for (int i = 0; i < cellCount; i++) {
                String colName = row.getCell(i).getStringCellValue();
                colName = convertToCamelCase(colName);
                for (Field f : fields) {
                    if (f.getName().equals(colName)) {
                        f.setAccessible(true);
                        map.put(f, i);
                        break;
                    }
                }
            }
        }

        return map;
    }

    private String convertToCamelCase(final String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = false;

        for (char c : input.toLowerCase().toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            if(!nextTitleCase) {
                titleCase.append(c);
            }
        }

        return titleCase.toString();
    }

    private InputStream getInputStream(final String workbookPath) {
        try {
            return new FileInputStream(workbookPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public CompareResult compare() {
        System.out.println("Loading " + sheetOneName);
        Map<RowKey, T> sheetOne = getSheetData(sheetOneName, clazz);
        System.out.println("Loaded records: " + sheetOne.size());
        System.out.println("Loading " + sheetTwoName);
        Map<RowKey, T> sheetTwo = getSheetData(sheetTwoName, clazz);
        System.out.println("Loaded records: " + sheetTwo.size());
        CompareResult result = new CompareResult();
        result.deletedRecords = sheetOne.keySet().stream().filter(key -> !sheetTwo.containsKey(key)).map(k -> k.row).collect(Collectors.toList());
        result.addedRecords = sheetTwo.keySet().stream().filter(key -> !sheetOne.containsKey(key)).map(k -> k.row).collect(Collectors.toList());
        Set<Field> valueFields = filterFields(fieldMap.keySet(), Value.class);
        result.updatedRecords = sheetTwo.keySet().stream().filter(key -> sheetOne.containsKey(key) && !areSame(sheetOne.get(key), sheetTwo.get(key), valueFields))
                .map(k -> new Pair(sheetOne.get(k), k.row)).collect(Collectors.toList());
        System.out.println("Only Sheet One records: " + result.deletedRecords.size());
        System.out.println("Only Sheet Two records: " + result.addedRecords.size());
        System.out.println("Different records: " + result.updatedRecords.size());
        return result;
    }

    private Map<RowKey, T> getSheetData(final String sheetName, Class<T> tClass) {
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        Map<RowKey, T> list = new HashMap<RowKey, T>(rowCount);
        long duplicateCount = 0;
        for (int i = 1; i < rowCount; i++) {
            final T t = adapt(sheet.getRow(i), tClass);
            final T existing = list.put(new RowKey(t), t);
            if(existing != null) {
                if (allowDuplicate) {
                    duplicateCount++;
                } else {
                    throw new IllegalStateException("Duplicate record found: " + existing);
                }
            }
        }

        System.out.println("Duplicate records found: " + duplicateCount);
        return list;
    }

    private T adapt(final Row row, final Class<T> tClass) {
        final T obj;
        try {
            obj = tClass.newInstance();
            for (Field f : fieldMap.keySet()) {
                Cell cell = row.getCell(fieldMap.get(f));
                Object value = getCellValue(cell, f.getType());
                f.set(obj, value);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return obj;
    }

    private Object getCellValue(final Cell cell, Class<?> destinationType) {
        Object value = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN: value = cell.getBooleanCellValue(); break;
            case Cell.CELL_TYPE_STRING: String val = cell.getStringCellValue();
                if(!"<null>".equals(val)) {
                    value = val;
                }
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(destinationType == Date.class) {
                    value = cell.getDateCellValue();
                } else {
                    Double x = cell.getNumericCellValue();
                    if(destinationType == Integer.class || destinationType == int.class) {
                        value = x.intValue();
                    } else if (destinationType == Long.class || destinationType == long.class) {
                        value = x.longValue();
                    } else if (destinationType == Float.class || destinationType == float.class) {
                        value = x.floatValue();
                    } else if (destinationType == Byte.class || destinationType == byte.class) {
                        value = x.byteValue();
                    } else if (destinationType == Short.class || destinationType == short.class) {
                        value = x.shortValue();
                    } else if (destinationType == Boolean.class || destinationType == boolean.class) {
                        value = x != 0;
                    } else {
                        value = x;
                    }
                }
                break;
        }

        if(value != null && String.class.isAssignableFrom(destinationType) && !(value instanceof String)) {
            value = value.toString();
        }

        return value;
    }

    public class CompareResult {
        private List<T> addedRecords;
        private List<T> deletedRecords;
        private List<Pair> updatedRecords;

        public List<T> getAddedRecords() {
            return addedRecords;
        }

        public List<T> getDeletedRecords() {
            return deletedRecords;
        }

        public List<Pair> getUpdatedRecords() {
            return updatedRecords;
        }
    }

    public class Pair {
        T first;
        T second;

        public Pair(final T first, final T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }
    }

    private boolean areSame(final T row1, final T row2, final Field field) {
        try {
            final Object o1 = field.get(row1);
            final Object o2 = field.get(row2);
            final Value annotation = field.getAnnotation(Value.class);

            double delta = 0;
            if(annotation != null && annotation.delta() > delta) {
                delta = annotation.delta();
            }
            if (delta > 0 && o1 != null && o2 != null) {
                double diff = Math.abs(((Number)o1).doubleValue() - ((Number)o2).doubleValue());
                return diff <= delta;
            } else {
                return (o1 != null && o1.equals(o2)) || o1 == o2;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean areSame(T row, T otherRow, Set<Field> fields) {
        for (Field col : fields) {
            if(!areSame(row, otherRow, col)) {
                //System.out.println(col.getName());
                return false;
            }
        }

        return true;
    }

    private class RowKey {
        private final T row;

        public RowKey(final T row) {
            this.row = row;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final T otherRow = ((RowKey) o).row;
            return WorksheetComparator.this.areSame(row, otherRow, WorksheetComparator.this.keyFields);
        }

        private int getHashCode(T row, Field field) {
            try {
                return field.get(row).hashCode();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public int hashCode() {
            int code = 31;
            for (Field col : WorksheetComparator.this.keyFields) {
                int val = getHashCode(row, col);
                code += 31 * val;
            }
            return row != null ? code : 0;
        }
    }
}
