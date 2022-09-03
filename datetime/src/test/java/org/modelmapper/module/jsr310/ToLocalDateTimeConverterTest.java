package org.modelmapper.module.jsr310;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ToLocalDateTimeConverterTest {

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
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        "2018-01-01 00:00:00", String.class, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertStringWithCustomPattern() {
    config.setDateTimePattern("yyyy-MM-dd HH:mm:ss.SSS");
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        "2018-01-01 00:00:00.000", String.class, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertLong() {
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.class, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertPrimitiveLong() {
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.TYPE, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigDecimal() {
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new BigDecimal("1514764800000"), BigDecimal.class, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigInteger() {
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new BigInteger("1514764800000"), BigInteger.class, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertDate() {
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        new Date(1514764800000L), Date.class, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(1514764800000L);
    LocalDateTime localDateTime = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
        calendar, Calendar.class, LocalDateTime.class));
    assertEquals(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertNull() {
    LocalDateTime timestamp = (LocalDateTime) converter.convert(TestHelper.unsafeMappingContext(
            null, Date.class, LocalDateTime.class));
    assertNull(timestamp);
  }
}
