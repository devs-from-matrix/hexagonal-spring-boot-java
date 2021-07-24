CREATE SCHEMA IF NOT EXISTS ${spring.liquibase.liquibase-schema:LIQUIBASE};
CREATE SCHEMA IF NOT EXISTS ${spring.liquibase.default-schema:EXAMPLE};
CREATE SCHEMA IF NOT EXISTS ${spring.jpa.properties.org.hibernate.envers:EXAMPLE_AUDIT};