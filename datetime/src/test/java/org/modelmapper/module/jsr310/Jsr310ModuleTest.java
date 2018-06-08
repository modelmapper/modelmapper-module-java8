package org.modelmapper.module.jsr310;

import java.time.Instant;
import java.time.ZoneOffset;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class Jsr310ModuleTest {

  @Data
  private static class StringWrapper {
    private String date;
  }

  @Data
  private static class InstantWrapper {
    private Instant date;
  }

  private ModelMapper modelMapper;

  @BeforeMethod
  public void setUp() {
    Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
        .zoneId(ZoneOffset.UTC)
        .build();
    modelMapper = new ModelMapper()
        .registerModule(new Jsr310Module(config));
  }

  public void shouldMapStringToInstant() {
    StringWrapper source = new StringWrapper();
    source.setDate("2018-01-01 00:00:00");
    InstantWrapper destination = modelMapper.map(source, InstantWrapper.class);
    assertEquals(destination.getDate().toEpochMilli(), 1514764800000L);
  }

  public void shouldMapInstantToString() {
    InstantWrapper source = new InstantWrapper();
    source.setDate(TestHelper.instantOf(2018, 1, 1));
    StringWrapper destination = modelMapper.map(source, StringWrapper.class);
    assertEquals(destination.getDate(), "2018-01-01 00:00:00");
  }
}
