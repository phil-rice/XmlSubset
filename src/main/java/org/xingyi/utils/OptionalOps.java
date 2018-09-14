package org.xingyi.utils;

import java.util.Optional;
import java.util.function.Function;

public interface OptionalOps {
    static <T, To> Optional<To> invert(Optional<T> optional, Getter<To> ifAbsent) {
        return optional.map(t -> Optional.<To>empty()).orElse(Optional.of(ifAbsent.get()));
    }
    static <T, To> To fold(Optional<T> optional, Function<T,To> ifPresent, Getter<To> ifAbsent) {
        return optional.map(ifPresent).orElse(ifAbsent.get());
    }

    static <T> Optional<T> fromBoolean(boolean b, Getter<T> getter){
        return b ? Optional.of(getter.get()) : Optional.empty();
    }
}
