package io.beandev.contracts._aggregate_.repositories._entity_;

import io.beandev.contracts._aggregate_.entities._Entity_;

import java.util.stream.Stream;

public interface Find_Entity_Collection extends StatementInterface<Stream<_Entity_>> {
    Find_Entity_Collection findOne_Entity_();
}
