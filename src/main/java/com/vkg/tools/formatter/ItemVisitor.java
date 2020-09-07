package com.vkg.tools.formatter;

/**
 * Created by Vishnu on 12/14/2018.
 */
public interface ItemVisitor {
    void visit(Field field);
    void visit(CompositeObject obj);
    void visit(CompositeArray arr);

    void completeVisit(CompositeObject obj);
    void completeVisit(Field field);
    void completeVisit(CompositeArray arr);
}
