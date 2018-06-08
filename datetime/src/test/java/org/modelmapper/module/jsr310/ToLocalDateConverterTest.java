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
public class ToLocalDateConverterTest {

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
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        "2018-01-01", String.class, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertStringWithCustomPattern() {
    config.setDatePattern("yyyy/MM/dd");
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        "2018/01/01", String.class, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertLong() {
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.class, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertPrimitiveLong() {
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        1514764800000L, Long.TYPE, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigDecimal() {
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        new BigDecimal("1514764800000"), BigDecimal.class, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertBigInteger() {
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        new BigInteger("1514764800000"), BigInteger.class, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertDate() {
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        new Date(1514764800000L), Date.class, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }

  public void shouldConvertCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(1514764800000L);
    LocalDate localDate = (LocalDate) converter.convert(TestHelper.unsafeMappingContext(
        calendar, Calendar.class, LocalDate.class));
    assertEquals(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 1514764800000L);
  }
}
