package org.modelmapper.module.jsr310;

import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@Test
public class ToOffsetDateTimeConverterTest {

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
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
        "2018-01-01 00:00:00Z", String.class, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertZonedTimeStringWithDefaultPattern() {
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
            "2017-12-31 19:00:00-05", String.class, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertLong() {
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.class, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertPrimitiveLong() {
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.TYPE, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigDecimal() {
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new BigDecimal("1514764800000"), BigDecimal.class, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigInteger() {
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new BigInteger("1514764800000"), BigInteger.class, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertDate() {
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new Date(1514764800000L), Date.class, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(1514764800000L);
    OffsetDateTime offsetDateTime = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
        calendar, Calendar.class, OffsetDateTime.class));
    assertEquals(offsetDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertNull() {
    OffsetDateTime timestamp = (OffsetDateTime) converter.convert(TestHelper.unsafeMappingContext(
            null, Date.class, OffsetDateTime.class));
    assertNull(timestamp);
  }
}
