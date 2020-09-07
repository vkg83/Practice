package com.vkg.tools.formatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

public class ExcelWriter implements ItemVisitor {
    private Workbook workbook;
    private Sheet curSheet;
    private Stack<ExcelItem> itemStack = new Stack<>();

    public ExcelWriter() {
        workbook = new XSSFWorkbook();
    }
    private void printKey(String name) {
        if(itemStack.peek().keyRequired) {
            addCell(name);
        }
    }

    private void addCell(String value) {
        System.out.println(itemStack + value);
        int row = itemStack.peek().nextRow();
        Row r = curSheet.getRow(row);
        if(r == null) {
            r = curSheet.createRow(row);
        }
        int col = itemStack.peek().nextCol();
        Cell cell = r.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        cell.setCellValue(value);
        itemStack.peek().next();
    }

    @Override
    public void visit(Field field) {
        printKey(field.getName());
        addCell(field.getDefaultValue().toString());
    }

    @Override
    public void completeVisit(Field field) {
        itemStack.peek().addField();
    }

    @Override
    public void visit(CompositeObject obj) {
        printKey(obj.getName());
        ExcelVisitorConfig c = (ExcelVisitorConfig) obj.getConfig();
        ExcelItem i= new ExcelItem(obj.getName(), itemStack.peek(), true);
        if(c != null) {
            i.keyRequired = c.isKeyRequired();
            i.vertical = c.isFlip() != i.vertical;
        }
        itemStack.push(i);
    }

    @Override
    public void completeVisit(CompositeObject obj) {
        ExcelItem i = itemStack.pop();
        itemStack.peek().addObject(i);
    }

    @Override
    public void visit(CompositeArray arr) {
        printKey(arr.getName());
        ExcelVisitorConfig c = (ExcelVisitorConfig) arr.getConfig();
        ExcelItem i= new ExcelItem(arr.getName(), itemStack.peek(), false);
        if(c != null) {
            i.keyRequired = c.isKeyRequired();
            i.vertical = c.isFlip() != i.vertical;
        }
        itemStack.push(i);
    }

    @Override
    public void completeVisit(CompositeArray arr) {
        ExcelItem i = itemStack.pop();
        itemStack.peek().addObject(i);
    }

    public void addSheet(String name, Item item) throws IOException {
        curSheet = workbook.createSheet(name);
        itemStack.push(new ExcelItem());
        item.accept(this);
    }
    public void addSheet(String name, Item item, boolean vert) throws IOException {
        curSheet = workbook.createSheet(name);
        itemStack.push(new ExcelItem(vert));
        item.accept(this);
    }
    public void write(String filename) {
        try {
            FileOutputStream out = new FileOutputStream(new File(filename));
            workbook.write(out);
            out.close();
            System.out.println(filename + " written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ExcelItem {
        String name;
        int sRow;
        int sCol;
        int rowSize;
        int colSize;
        int nextRow;
        int nextCol;
        boolean keyRequired;
        boolean vertical;

        public ExcelItem() {
            this.name = "t";
            keyRequired = true;
            vertical = true;
        }

        ExcelItem(String name,ExcelItem parent, boolean keyRequired) {
            this.name = name;
            sRow = parent.nextRow();
            sCol = parent.nextCol();
            nextRow = sRow;
            nextCol = sCol;
            this.keyRequired = keyRequired;
            this.vertical = !parent.vertical;
        }

        public ExcelItem(boolean vert) {
            this();
            this.vertical = vert;
        }

        public void next() {
            if(vertical) {
                nextCol++;
            } else {
                nextRow++;
            }
        }

        public void addField() {
            add(1, 1, 0);
        }

        private void add(int r, int c, int gap) {
            if (vertical) {
                if(keyRequired)c++;
                rowSize += r + gap;
                colSize = colSize < c ? c : colSize;
                nextRow += r + gap;
                nextCol = sCol;
            } else {
                if(keyRequired)r++;
                colSize += c + gap;
                rowSize = rowSize < r ? r : rowSize;
                nextRow = sRow;
                nextCol += c + gap;
            }
        }

        public void addObject(ExcelItem item) {
            add(item.rowSize, item.colSize, 1);
        }

        public int nextRow() {
            return nextRow;
        }

        public int nextCol() {
            return nextCol;
        }

        public String toString() {
            return name +"("+sRow+","+sCol+":"+rowSize+","+colSize+")";
        }
    }
}
