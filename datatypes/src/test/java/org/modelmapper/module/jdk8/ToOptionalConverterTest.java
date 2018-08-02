package org.modelmapper.module.jdk8;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ToOptionalConverterTest {
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
  static class Source {
    Field field;
  }

  @Data
  @SuppressWarnings("all")
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


  @SuppressWarnings("all")
  public void shouldMapStringToOptional() {
    assertEquals(modelMapper.map("foo", Optional.class).get(), "foo");
  }

  public void shouldMapStringToOptionalInteger() {
    assertEquals(modelMapper.map("100", new TypeToken<Optional<Integer>>() {}.getType()), Optional.of(100));
  }

  @SuppressWarnings("all")
  public void shouldMapToOptionalProperty() {
    Destination destination = modelMapper.map(new Source(new Field("foo")), Destination.class);
    assertEquals(destination.getField().get().getValue(), "FOO");
  }
}
