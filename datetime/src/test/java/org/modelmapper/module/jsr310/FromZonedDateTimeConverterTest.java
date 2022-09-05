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
import static org.testng.Assert.assertNull;

@Test
public class FromZonedDateTimeConverterTest {

  private Jsr310ModuleConfig config;
  private ConditionalConverter<Object, Object> converter;

  @BeforeMethod
  public void setUp() {
    config = Jsr310ModuleConfig.builder()
         .zoneId(ZoneId.of("-05:00"))
        .build();
    converter = TestHelper.unsafe(new FromTemporalConverter(config));
  }

  public void shouldConvertToDefaultString() {
    ZonedDateTime source = TestHelper.instantOf(2018, 1, 1).atZone(ZoneOffset.UTC);
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, ZonedDateTime.class, String.class));
    assertEquals(dateText, "2018-01-01 00:00:00Z");
  }

  public void shouldConvertToStringWithPattern() {
    config.setDateTimeOffsetPattern("yyyy-MM-dd HH:mm:ss.SSSX");
    ZonedDateTime source = TestHelper.instantOf(2018, 1, 1).atZone(ZoneOffset.UTC);
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, ZonedDateTime.class, String.class));
    assertEquals(dateText, "2018-01-01 00:00:00.000Z");
  }

  public void shouldConvertToLong() {
    ZonedDateTime source = TestHelper.instantOf(2018, 1, 1).atZone(ZoneOffset.UTC);
    long timestamp = (Long) converter.convert(TestHelper.unsafeMappingContext(source, ZonedDateTime.class, Long.class));
    assertEquals(timestamp, 1514764800000L);
  }

  public void shouldConvertToBigDecimal() {
    ZonedDateTime source = TestHelper.instantOf(2018, 1, 1).atZone(ZoneOffset.UTC);
    BigDecimal timestamp = (BigDecimal) converter.convert(TestHelper.unsafeMappingContext(source, ZonedDateTime.class, BigDecimal.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToBigInteger() {
    ZonedDateTime source = TestHelper.instantOf(2018, 1, 1).atZone(ZoneOffset.UTC);
    BigInteger timestamp = (BigInteger) converter.convert(TestHelper.unsafeMappingContext(source, ZonedDateTime.class, BigInteger.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToDate() {
    ZonedDateTime source = TestHelper.instantOf(2018, 1, 1).atZone(ZoneOffset.UTC);
    Date timestamp = (Date) converter.convert(TestHelper.unsafeMappingContext(source, ZonedDateTime.class, Date.class));
    assertEquals(timestamp.getTime(), 1514764800000L);
  }

  public void shouldConvertToCalendar() {
    ZonedDateTime source = TestHelper.instantOf(2018, 1, 1).atZone(ZoneOffset.UTC);
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(source, ZonedDateTime.class, Calendar.class));
    assertEquals(timestamp.getTime().getTime(), 1514764800000L);
  }

  public void shouldConvertNull() {
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(null, ZonedDateTime.class, Calendar.class));
    assertNull(timestamp);
  }
}
