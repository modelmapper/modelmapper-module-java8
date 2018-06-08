package org.modelmapper.module.jsr310;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import org.modelmapper.spi.ConditionalConverter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class FromLocalDateConverterTest {

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
    LocalDate source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDate();
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, LocalDate.class, String.class));
    assertEquals(dateText, "2018-01-01");
  }

  public void shouldConvertToStringWithPattern() {
    config.setDatePattern("yyyy/MM/dd");
    LocalDate source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDate();
    String dateText = (String) converter.convert(TestHelper.unsafeMappingContext(source, LocalDate.class, String.class));
    assertEquals(dateText, "2018/01/01");
  }

  public void shouldConvertToLong() {
    LocalDate source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDate();
    long timestamp = (Long) converter.convert(TestHelper.unsafeMappingContext(source, LocalDate.class, Long.class));
    assertEquals(timestamp, 1514764800000L);
  }

  public void shouldConvertToBigDecimal() {
    LocalDate source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDate();
    BigDecimal timestamp = (BigDecimal) converter.convert(TestHelper.unsafeMappingContext(source, LocalDate.class, BigDecimal.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToBigInteger() {
    LocalDate source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDate();
    BigInteger timestamp = (BigInteger) converter.convert(TestHelper.unsafeMappingContext(source, LocalDate.class, BigInteger.class));
    assertEquals(timestamp.longValue(), 1514764800000L);
  }

  public void shouldConvertToDate() {
    LocalDate source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDate();
    Date timestamp = (Date) converter.convert(TestHelper.unsafeMappingContext(source, LocalDate.class, Date.class));
    assertEquals(timestamp.getTime(), 1514764800000L);
  }

  public void shouldConvertToCalendar() {
    LocalDate source = TestHelper.instantOf(2018, 1, 1)
        .atZone(ZoneOffset.UTC)
        .toLocalDate();
    Calendar timestamp = (Calendar) converter.convert(TestHelper.unsafeMappingContext(source, LocalDate.class, Calendar.class));
    assertEquals(timestamp.getTime().getTime(), 1514764800000L);
  }
}
