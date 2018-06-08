package org.modelmapper.module.jsr310;

import static org.testng.Assert.assertEquals;

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
public class FromLocalDateTimeConverterTest {

  private Jsr310ModuleConfig config;
  private ConditionalConverter<Object, Object> converter;

  @BeforeMethod
  public void setUp() {
    config = Jsr310ModuleConfig.builder()
        .zoneId(ZoneOffset.UTC)
        .build();
    converter = TestHelper.unsafe(new FromTemporalConverter(config));
  }

  public void shouldConvertToDefaultString() {
    LocalDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, LocalDateTime.class, String.class));
    assertEquals(dateText, "2018-01-01 00:00:00");
  }

  public void shouldConvertToStringWithPattern() {
    config.setDateTimePattern("yyyy-MM-dd HH:mm:ss.SSS");
    LocalDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, LocalDateTime.class, String.class));
    assertEquals(dateText, "2018-01-01 00:00:00.000");
  }

  public void shouldConvertToLong() {
    LocalDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
    long timestamp = (Long) converter.convert(TestHelper.unsafeMappingContext(source, LocalDateTime.class, Long.class));
    assertEquals(timestamp, 1514764800000L);
  }

  public void shouldConvertToBigDecimal() {
    LocalDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
    BigDecimal timestamp = (BigDecimal) converter.convert(TestHelper.unsafeMappingContext(source, LocalDateTime.class, BigDecimal.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToBigInteger() {
    LocalDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
    BigInteger timestamp = (BigInteger) converter.convert(TestHelper.unsafeMappingContext(source, LocalDateTime.class, BigInteger.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToDate() {
    LocalDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
    Date timestamp = (Date) converter.convert(TestHelper.unsafeMappingContext(source, LocalDateTime.class, Date.class));
    assertEquals(timestamp.getTime(), 1514764800000L);
  }

  public void shouldConvertToCalendar() {
    LocalDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDateTime();
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(source, LocalDateTime.class, Calendar.class));
    assertEquals(timestamp.getTime().getTime(), 1514764800000L);
  }
}
