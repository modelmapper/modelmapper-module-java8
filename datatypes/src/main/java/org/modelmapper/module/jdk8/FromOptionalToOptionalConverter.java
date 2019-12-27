package org.modelmapper.module.jdk8;

import org.modelmapper.internal.typetools.TypeResolver;
import org.modelmapper.internal.util.Types;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.Mapping;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.PropertyInfo;
import org.modelmapper.spi.PropertyMapping;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

/**
 * Converts  {@link Optional} to {@link Optional}
 */
public class FromOptionalToOptionalConverter implements ConditionalConverter<Optional, Optional> {
  @Override
  public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
    return (Optional.class.equals(sourceType) && Optional.class.equals(destinationType))
        ? MatchResult.FULL
        : MatchResult.NONE;
  }

  @Override
  public Optional<?> convert(MappingContext<Optional, Optional> mappingContext) {
    Class<?> optionalType = getElementType(mappingContext);
    Optional source = mappingContext.getSource();
    Object dest = null;
    if (source != null && source.isPresent()) {
      MappingContext<?, ?> optionalContext = mappingContext.create(source.get(), optionalType);
      dest = mappingContext.getMappingEngine().map(optionalContext);
    }

    return Optional.ofNullable(dest);
  }


  private Class<?> getElementType(MappingContext<Optional, Optional> mappingContext) {
    Mapping mapping = mappingContext.getMapping();
    if (mapping instanceof PropertyMapping) {
      PropertyInfo destInfo = mapping.getLastDestinationProperty();
      Class<?> elementType = TypeResolver.resolveRawArgument(destInfo.getGenericType(),
          destInfo.getInitialType());
      return elementType == TypeResolver.Unknown.class ? Object.class : elementType;
    } else if (mappingContext.getGenericDestinationType() instanceof ParameterizedType) {
      return Types.rawTypeFor(((ParameterizedType) mappingContext.getGenericDestinationType()).getActualTypeArguments()[0]);
    }

    return Object.class;
  }

}
