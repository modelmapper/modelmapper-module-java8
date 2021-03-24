# ModelMapper Module for Java8

[![Build Status](https://travis-ci.org/modelmapper/modelmapper-module-java8.svg)](https://travis-ci.org/modelmapper/modelmapper-module-java8) 
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.chhsiao90/modelmapper-module-java8/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.chhsiao90/modelmapper-module-java8)

This is a module for [ModelMapper](http://modelmapper.org) to support Java 8 features.

## Java 8 Date/Time

### Registers the module

```java
modelMapper.registerModule(new Jsr310Module());
```

### Configuration

We also support for configuration.

```java
// using String patterns
Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
    .dateTimePattern("yyyy-MM-dd HH:mm:ss") // default is yyyy-MM-dd HH:mm:ss
    .datePattern("yyyy-MM-dd") // default is yyyy-MM-dd
    .zoneId(ZoneOffset.UTC) // default is ZoneId.systemDefault()
    .build()
modelMapper.registerModule(new Jsr310Module(config));
```
```java
// using DateTimeFormatter directly
Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
    .dateTimeFormatter(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    .dateFormatter(DateTimeFormatter.ISO_LOCAL_DATE)
    .zoneId(ZoneOffset.UTC)
    .build()
modelMapper.registerModule(new Jsr310Module(config));
```

### Support Mappings

| Source Type   | Destination Type |
|---------------|------------------|
| LocalDateTime | String           |
| LocalDateTime | Long/long        |
| LocalDateTime | BigDecimal       |
| LocalDateTime | BigInteger       |
| LocalDateTime | Date             |
| LocalDateTime | Calendar         |
| LocalDate     | String           |
| LocalDate     | Long/long        |
| LocalDate     | BigDecimal       |
| LocalDate     | BigInteger       |
| LocalDate     | Date             |
| LocalDate     | Calendar         |
| OffsetDateTime| String           |
| OffsetDateTime| Long/long        |
| OffsetDateTime| BigDecimal       |
| OffsetDateTime| BigInteger       |
| OffsetDateTime| Date             |
| OffsetDateTime| Calendar         |
| Instant       | String           |
| Instant       | Long/long        |
| Instant       | BigDecimal       |
| Instant       | BigInteger       |
| Instant       | Date             |
| Instant       | Calendar         |
| String        | LocalDateTine    |
| Long/long     | LocalDateTine    |
| BigDecimal    | LocalDateTine    |
| BigInteger    | LocalDateTine    |
| Date          | LocalDateTine    |
| Calendar      | LocalDateTine    |
| String        | DateTine         |
| Long/long     | DateTine         |
| BigDecimal    | DateTine         |
| BigInteger    | DateTine         |
| Date          | DateTine         |
| Calendar      | DateTine         |
| String        | OffsetDateTime   |
| Long/long     | OffsetDateTime   |
| BigDecimal    | OffsetDateTime   |
| BigInteger    | OffsetDateTime   |
| Date          | OffsetDateTime   |
| Calendar      | OffsetDateTime   |
| String        | Instant          |
| Long/long     | Instant          |
| BigDecimal    | Instant          |
| BigInteger    | Instant          |
| Date          | Instant          |
| Calendar      | Instant          |
