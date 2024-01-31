package io.beandev.contracts._aggregate_.entities;

public record _AggregateRoot_(
        String _attribute_String,
        Integer _attribute_Integer
) {

    static _AggregateRoot_Builder builder() {
        return new _AggregateRoot_Builder();
    }

    static class _AggregateRoot_Builder {
        private String _attribute_String;
        private Integer _attribute_Integer;

        _AggregateRoot_Builder build_Attribute_String(String _attribute_String) {
            this._attribute_String = _attribute_String;
            return this;
        }

        _AggregateRoot_Builder build_Attribute_Integer(Integer _attribute_Integer) {
            this._attribute_Integer = _attribute_Integer;
            return this;
        }

        _AggregateRoot_ build() {
            return new _AggregateRoot_(
                    this._attribute_String,
                    this._attribute_Integer
            );
        }
    }
}