package org.xingyi.utils;

import java.util.Optional;

public interface OptionalOps {
    static <T, To> Optional<To> invert(Optional<T> optional, Getter<To> ifAbsent) {
        return optional.map(t -> Optional.<To>empty()).orElse(Optional.of(ifAbsent.get()));
    }
}
