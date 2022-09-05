package org.modelmapper.module.jsr310;

import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertEquals;

@Test
public class ToZonedDateTimeConverterTest {

  private Jsr310ModuleConfig config;
  private ConditionalConverter<Object, Object> converter;

  @BeforeMethod
  public void setUp() {
    config = Jsr310ModuleConfig.builder()
        .zoneId(ZoneId.of("-05:00"))
        .build();
    converter = TestHelper.unsafe(new ToTemporalConverter(config));
  }

  public void shouldConvertStringWithDefaultPattern() {
    ZonedDateTime zonedDateTime = (ZonedDateTime) converter.convert(TestHelper.unsafeMappingContext(
        "2018-01-01 00:00:00Z", String.class, ZonedDateTime.class));
    assertEquals(zonedDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }


  public void shouldConvertLong() {
    ZonedDateTime zonedDateTime = (ZonedDateTime) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.class, ZonedDateTime.class));
    assertEquals(ZoneId.of("-05:00"), zonedDateTime.getZone());
    assertEquals(zonedDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertPrimitiveLong() {
    ZonedDateTime zonedDateTime = (ZonedDateTime) converter.convert(TestHelper.unsafeMappingContext(
            1514764800000L, Long.class, ZonedDateTime.class));
    assertEquals(zonedDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigDecimal() {
    ZonedDateTime zonedDateTime = (ZonedDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new BigDecimal("1514764800000"), BigDecimal.class, ZonedDateTime.class));
    assertEquals(zonedDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigInteger() {
    ZonedDateTime zonedDateTime = (ZonedDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new BigInteger("1514764800000"), BigInteger.class, ZonedDateTime.class));
    assertEquals(zonedDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertDate() {
    ZonedDateTime zonedDateTime = (ZonedDateTime)converter.convert(TestHelper.unsafeMappingContext(
        new Date(1514764800000L), Date.class, ZonedDateTime.class));
    assertEquals(zonedDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(1514764800000L);
    ZonedDateTime zonedDateTime = (ZonedDateTime) converter.convert(TestHelper.unsafeMappingContext(
        calendar, Calendar.class, ZonedDateTime.class));
    assertEquals(zonedDateTime.toInstant().toEpochMilli(), 1514764800000L);
  }
}
