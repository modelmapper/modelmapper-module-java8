package org.modelmapper.module.jsr310;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Config for {@link Jsr310Module}
 *
 * @author Chun Han Hsiao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jsr310ModuleConfig {
  private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
  private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  private static final String DEFAULT_DATE_TIME_OFFSET_PATTERN = "yyyy-MM-dd HH:mm:ssX";
  private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

  private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
  private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN);
  private DateTimeFormatter dateTimeOffsetFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_OFFSET_PATTERN);
  private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN);
  private ZoneId zoneId = ZoneId.systemDefault();

  public void setDatePattern(String datePattern) {
    this.setDateFormatter(DateTimeFormatter.ofPattern(datePattern));
  }

  public void setDateTimePattern(String dateTimePattern) {
    this.setDateTimeFormatter(DateTimeFormatter.ofPattern(dateTimePattern));
  }

  public void setDateTimeOffsetPattern(String dateTimeOffsetPattern) {
    this.setDateTimeOffsetFormatter(DateTimeFormatter.ofPattern(dateTimeOffsetPattern));
  }

  public void setTimePattern(String timePattern) {
    this.setTimeFormatter(DateTimeFormatter.ofPattern(timePattern));
  }

  public static Jsr310ModuleConfigBuilder builder() {
    return new Jsr310ModuleConfigBuilder();
  }

  public static class Jsr310ModuleConfigBuilder {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN);
    private DateTimeFormatter dateTimeOffsetFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_OFFSET_PATTERN);
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN);
    private ZoneId zoneId = ZoneId.systemDefault();

    public Jsr310ModuleConfigBuilder datePattern(String datePattern) {
      return this.dateFormatter(DateTimeFormatter.ofPattern(datePattern));
    }

    public Jsr310ModuleConfigBuilder dateFormatter(DateTimeFormatter dateFormatter) {
      this.dateFormatter = dateFormatter;
      return this;
    }

    public Jsr310ModuleConfigBuilder dateTimePattern(String dateTimePattern) {
      return this.dateTimeFormatter(DateTimeFormatter.ofPattern(dateTimePattern));
    }

    public Jsr310ModuleConfigBuilder dateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
      this.dateTimeFormatter = dateTimeFormatter;
      return this;
    }

    public Jsr310ModuleConfigBuilder dateTimeOffsetPattern(String dateTimeOffsetPattern) {
      return this.dateTimeOffsetFormatter(DateTimeFormatter.ofPattern(dateTimeOffsetPattern));
    }

    public Jsr310ModuleConfigBuilder dateTimeOffsetFormatter(DateTimeFormatter dateTimeOffsetFormatter) {
      this.dateTimeOffsetFormatter = dateTimeOffsetFormatter;
      return this;
    }

    public Jsr310ModuleConfigBuilder timePattern(String timePattern) {
      return this.timeFormatter(DateTimeFormatter.ofPattern(timePattern));
    }

    public Jsr310ModuleConfigBuilder timeFormatter(DateTimeFormatter timeFormatter) {
      this.timeFormatter = timeFormatter;
      return this;
    }

    public Jsr310ModuleConfigBuilder zoneId(ZoneId zoneId) {
      this.zoneId = zoneId;
      return this;
    }

    public Jsr310ModuleConfig build() {
      return new Jsr310ModuleConfig(dateFormatter, dateTimeFormatter, dateTimeOffsetFormatter, timeFormatter, zoneId);
    }
  }
}
