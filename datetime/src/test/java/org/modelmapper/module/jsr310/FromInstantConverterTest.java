package org.modelmapper.module.jsr310;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class FromInstantConverterTest {

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
    Instant source = TestHelper.instantOf(2018, 1, 1);
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, Instant.class, String.class));
    assertEquals(dateText, "2018-01-01 00:00:00");
  }

  public void shouldConvertToStringWithPattern() {
    config.setDateTimePattern("yyyy-MM-dd HH:mm:ss.SSS");
    Instant source = TestHelper.instantOf(2018, 1, 1);
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, Instant.class, String.class));
    assertEquals(dateText, "2018-01-01 00:00:00.000");
  }

  public void shouldConvertToLong() {
    Instant source = TestHelper.instantOf(2018, 1, 1);
    long timestamp = (Long) converter.convert(TestHelper.unsafeMappingContext(source, Instant.class, Long.class));
    assertEquals(timestamp, 1514764800000L);
  }

  public void shouldConvertToBigDecimal() {
    Instant source = TestHelper.instantOf(2018, 1, 1);
    BigDecimal timestamp = (BigDecimal) converter.convert(TestHelper.unsafeMappingContext(source, Instant.class, BigDecimal.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToBigInteger() {
    Instant source = TestHelper.instantOf(2018, 1, 1);
    BigInteger timestamp = (BigInteger) converter.convert(TestHelper.unsafeMappingContext(source, Instant.class, BigInteger.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToDate() {
    Instant source = TestHelper.instantOf(2018, 1, 1);
    Date timestamp = (Date) converter.convert(TestHelper.unsafeMappingContext(source, Instant.class, Date.class));
    assertEquals(timestamp.getTime(), 1514764800000L);
  }

  public void shouldConvertToCalendar() {
    Instant source = TestHelper.instantOf(2018, 1, 1);
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(source, Instant.class, Calendar.class));
    assertEquals(timestamp.getTime().getTime(), 1514764800000L);
  }

  public void shouldConvertNull() {
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(null, Instant.class, Calendar.class));
    assertNull(timestamp);
  }
}
