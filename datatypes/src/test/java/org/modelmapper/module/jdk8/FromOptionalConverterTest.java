package org.modelmapper.module.jdk8;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class FromOptionalConverterTest {
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
    FieldDto field;
  }

  @BeforeMethod
  public void setUp() {
    modelMapper = new ModelMapper();
    modelMapper.registerModule(new Jdk8Module());

    Converter<String, String> upperCase = ctx -> ctx.getSource().toUpperCase();
    modelMapper.emptyTypeMap(Field.class, FieldDto.class).addMappings(
        mapper -> mapper.using(upperCase).map(Field::getValue, FieldDto::setValue));
  }


  public void shouldMapOptionalStringToString() {
    assertEquals(modelMapper.map(Optional.of("foo"), String.class), "foo");
    assertNull(modelMapper.map(Optional.empty(), String.class));
  }

  public void shouldMapOptionalStringToInteger() {
    assertEquals((int) modelMapper.map(Optional.of("100"), Integer.class), 100);
  }

  public void shouldMapOptionalProperty() {
    Destination destination = modelMapper.map(new Source(Optional.of(new Field("foo"))), Destination.class);
    assertEquals(destination.getField().getValue(), "FOO");
  }
}
