package org.modelmapper.module.jsr310;

import java.time.Instant;
import org.modelmapper.internal.MappingContextImpl;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

public class TestHelper {
  public static <S, D> MappingContext<S, D> mappingContext(S source, Class<S> sourceType, Class<D> destinationType) {
    return new MappingContextImpl<>(source ,sourceType, null,
        destinationType,null, "", null);
  }
  public static <S, D> MappingContext<Object, Object> unsafeMappingContext(S source, Class<S> sourceType, Class<D> destinationType) {
    MappingContext<?, ?> mappingContext = mappingContext(source ,sourceType, destinationType);
    return unsafe(mappingContext);
  }

  @SuppressWarnings("unchecked")
  public static MappingContext<Object, Object> unsafe(MappingContext<?, ?> mappingContext) {
    return (MappingContext<Object, Object>) mappingContext;
  }

  @SuppressWarnings("unchecked")
  public static ConditionalConverter<Object, Object> unsafe(ConditionalConverter<?, ?> converter) {
    return (ConditionalConverter<Object, Object>) converter;
  }

  public static Instant instantOf(int year, int month, int day) {
    return Instant.parse(String.format("%04d-%02d-%02dT00:00:00Z", year, month, day));
  }
}
