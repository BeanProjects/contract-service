package io.beandev.contracts._aggregate_.repositories._entity_;

public interface StatementInterface<T> {
    StatementInterface<T> by_Entity__Attribute_(_Entity__Attribute_<T> _entity__Attribute_);

    T execute();
}
