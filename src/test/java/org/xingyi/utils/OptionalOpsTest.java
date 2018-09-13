package org.xingyi.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalOpsTest {
    @Test
    public void testInvert() {
        assertEquals(Optional.empty(), OptionalOps.invert(Optional.of(1), () -> "ignore"));
        assertEquals(Optional.of("value"), OptionalOps.invert(Optional.empty(), () -> "value"));
    }
}
