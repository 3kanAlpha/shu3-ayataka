package net.mgcup.ayataka.util;

import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
public class InjectionUtil {
    @SuppressWarnings({"ConstantConditions", "SameReturnValue"})
    public static <T> T Null() {
        return null;
    }
}
