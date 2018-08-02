package org.modelmapper.module.jdk8;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

/**
 * Converts  {@link Optional} to {@link Object}
 *
 * @author Chun Han Hsiao
 */
public class FromOptionalConverter implements ConditionalConverter<Optional<Object>, Object> {
  private ModelMapper modelMapper;

  public FromOptionalConverter(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
    return (Optional.class.equals(sourceType) && !Optional.class.equals(destinationType))
        ? MatchResult.FULL
        : MatchResult.NONE;
  }

  @Override
  public Object convert(MappingContext<Optional<Object>, Object> mappingContext) {
    if (mappingContext.getSource() == null || !mappingContext.getSource().isPresent()) {
      return null;
    }

    return modelMapper.map(mappingContext.getSource().get(), mappingContext.getDestinationType());
  }
}
