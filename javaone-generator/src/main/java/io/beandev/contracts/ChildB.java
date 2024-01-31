package io.beandev.contracts;

public class ChildB extends AbstractParent implements ParentInterface {
    public static void alterParentStaticField(int value) {
        ChildB.staticField = value;
    }

    public static void method() {
        System.out.println("ChildB.method()");
    }
}
