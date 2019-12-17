package io.se7en.grpctest;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import coprocess.CoprocessObject.Object;

public final class HookRegistry {
  private HookRegistry() {}

  private static Map<String, UnaryOperator<Object>> getHooks() {
    return Collections.emptyMap();
  }

  public static Function<Object, Optional<Object>> getTransformer() {
    Map<String, UnaryOperator<Object>> hooks = getHooks();
    UnaryOperator<Object> nullHook = r -> null;

    return request -> Optional.ofNullable(hooks.getOrDefault(request.getHookName(), nullHook).apply(request));
  }
}
