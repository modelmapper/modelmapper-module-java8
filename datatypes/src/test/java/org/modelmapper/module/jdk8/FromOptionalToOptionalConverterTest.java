package org.modelmapper.module.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

@Test
public class FromOptionalToOptionalConverterTest {
  private ModelMapper modelMapper;

  @Data
  @AllArgsConstructor
  static class Field {
    private String value;
  }

  @Data
  static class FieldDto {
    private String value;
  }

  @Data
  @AllArgsConstructor
  @SuppressWarnings("all")
  static class Source {
    Optional<Field> field;
  }

  @Data
  static class Destination {
    Optional<FieldDto> field;
  }

  @BeforeMethod
  public void setUp() {
    modelMapper = new ModelMapper();
    modelMapper.registerModule(new Jdk8Module());

    Converter<String, String> upperCase = ctx -> ctx.getSource().toUpperCase();
    modelMapper.emptyTypeMap(Field.class, FieldDto.class).addMappings(
        mapper -> mapper.using(upperCase).map(Field::getValue, FieldDto::setValue));
  }


  public void shouldMapOptionalStringToOptionalString() {
    assertEquals(modelMapper.map(Optional.of("foo"), Optional.class), Optional.of("foo"));
    assertEquals(modelMapper.map(Optional.empty(), Optional.class), Optional.empty());
  }

  public void shouldMapOptionalStringToOptionalInteger() {
    Type type = new TypeToken<Optional<Integer>>() {}.getType();
    assertEquals(modelMapper.map(Optional.of("100"), type), Optional.of(100));
  }

  public void shouldMapOptionalPropertyToOtherOptionalProperty() {
    Destination destination = modelMapper.map(new Source(Optional.of(new Field("foo"))), Destination.class);
    assertEquals(destination.getField().get().getValue(), "FOO");
  }
}
