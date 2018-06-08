# ModelMapper Module for Java8

[![Build Status](https://travis-ci.org/chhsiao90/modelmapper-module-java8.svg)](https://travis-ci.org/chhsiao90/modelmapper-module-java8) 
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This is a module for [ModelMapper](http://modelmapper.org) to support Java 8 features.

## Java 8 Date/Time

### Registers the module

```java
modelMapper.registerModule(new Jsr310Module());
```

### Configuration

We also support for configuration.

```java
Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
    .dateTimePattern("yyyy-MM-dd HH:mm:ss") // default is yyyy-MM-dd HH:mm:ss
    .datePattern("yyyy-MM-dd") // default is yyyy-MM-dd
    .zoneId(ZoneOffset.UTF) // default is ZoneId.systemDefault()
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
| String        | Instant          |
| Long/long     | Instant          |
| BigDecimal    | Instant          |
| BigInteger    | Instant          |
| Date          | Instant          |
| Calendar      | Instant          |
