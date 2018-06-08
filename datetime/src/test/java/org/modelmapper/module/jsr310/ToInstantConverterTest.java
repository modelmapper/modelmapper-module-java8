package org.modelmapper.module.jsr310;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class ToInstantConverterTest {

  private Jsr310ModuleConfig config;
  private ConditionalConverter<Object, Object> converter;

  @BeforeMethod
  public void setUp() {
    config = Jsr310ModuleConfig.builder()
        .zoneId(ZoneOffset.UTC)
        .build();
    converter = TestHelper.unsafe(new ToTemporalConverter(config));
  }

  public void shouldConvertStringWithDefaultPattern() {
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        "2018-01-01 00:00:00", String.class, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertStringWithCustomPattern() {
    config.setDateTimePattern("yyyy-MM-dd HH:mm:ss.SSS");
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        "2018-01-01 00:00:00.000", String.class, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertLong() {
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.class, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertPrimitiveLong() {
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.TYPE, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigDecimal() {
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        new BigDecimal("1514764800000"), BigDecimal.class, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigInteger() {
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        new BigInteger("1514764800000"), BigInteger.class, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertDate() {
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        new Date(1514764800000L), Date.class, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(1514764800000L);
    Instant instant = (Instant) converter.convert(TestHelper.unsafeMappingContext(
        calendar, Calendar.class, Instant.class));
    assertEquals(instant.toEpochMilli(), 1514764800000L);
  }
}
