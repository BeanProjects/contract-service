package io.beandev.contracts;

public class ChildA extends AbstractParent {
    public static void alterParentStaticField(int value) {
        ChildA.staticField = value;
    }
}
