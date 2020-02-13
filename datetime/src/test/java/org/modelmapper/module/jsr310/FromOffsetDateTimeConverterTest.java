package org.modelmapper.module.jsr310;

import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@Test
public class FromOffsetDateTimeConverterTest {

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
    OffsetDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toOffsetDateTime();
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, OffsetDateTime.class, String.class));
    assertEquals(dateText, "2018-01-01 00:00:00Z");
  }

  public void shouldConvertZonedTimeToDefaultString() {
    OffsetDateTime source = TestHelper.instantOf(2018, 1, 1)
            .atZone(ZoneId.of("-05:00"))
            .toOffsetDateTime();
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, OffsetDateTime.class, String.class));
    assertEquals(dateText, "2017-12-31 19:00:00-05");
  }

  public void shouldConvertToLong() {
    OffsetDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toOffsetDateTime();
    long timestamp = (Long) converter.convert(TestHelper.unsafeMappingContext(source, OffsetDateTime.class, Long.class));
    assertEquals(timestamp, 1514764800000L);
  }

  public void shouldConvertToBigDecimal() {
    OffsetDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toOffsetDateTime();
    BigDecimal timestamp = (BigDecimal) converter.convert(TestHelper.unsafeMappingContext(source, OffsetDateTime.class, BigDecimal.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToBigInteger() {
    OffsetDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toOffsetDateTime();
    BigInteger timestamp = (BigInteger) converter.convert(TestHelper.unsafeMappingContext(source, OffsetDateTime.class, BigInteger.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToDate() {
    OffsetDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toOffsetDateTime();
    Date timestamp = (Date) converter.convert(TestHelper.unsafeMappingContext(source, OffsetDateTime.class, Date.class));
    assertEquals(timestamp.getTime(), 1514764800000L);
  }

  public void shouldConvertToCalendar() {
    OffsetDateTime source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toOffsetDateTime();
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(source, OffsetDateTime.class, Calendar.class));
    assertEquals(timestamp.getTime().getTime(), 1514764800000L);
  }

  public void shouldConvertNull() {
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(null, OffsetDateTime.class, Calendar.class));
    assertNull(timestamp);
  }
}
