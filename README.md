# hexagonal-spring-boot-java [![Build Status](https://travis-ci.com/devs-from-matrix/hexagonal-spring-boot-java.svg?branch=master)](https://travis-ci.com/devs-from-matrix/hexagonal-spring-boot-java) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/c4a6502171544f3a919bcdce35cb50b1)](https://www.codacy.com/gh/devs-from-matrix/hexagonal-spring-boot-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=devs-from-matrix/hexagonal-spring-boot-java&amp;utm_campaign=Badge_Grade)

This project is a template reference for hexagonal spring boot. This repository is to be used
by [app-generator](https://github.com/devs-from-matrix/app-generator) for scaffolding.

The keywords of the app-generators are the following

- `packagename` - to rename the package names
- `artifactName` - to rename the artifact id
- `Example` - to rename class, variables

Use it with caution as these will be used by the app-generator to replace them with domain specific name in the scaffold
code.

## Pre-requisite

- maven
- open jdk 11

## How to build ?

`mvn clean install`

### How to build a docker image ?

`cd bootstrap && mvn compile jib:dockerBuild`

[More information](https://cloud.google.com/java/getting-started/jib)

## How to start ?

`cd bootstrap && mvn spring-boot:run`

## Formatting

This project uses [git-code-format-maven-plugin](https://github.com/Cosium/git-code-format-maven-plugin) for formatting
the code per [google style guide](https://google.github.io/styleguide/javaguide.html)

### How to format ?

`mvn git-code-format:format-code`

## Validating

This project
uses [githook-maven-plugin](https://mvnrepository.com/artifact/io.github.phillipuniverse/githook-maven-plugin) which is
a maven plugin to configure and install local git hooks by running set of commands during build.

### Command to validate formatted code

`mvn git-code-format:validate-code-format`

## Contribution guidelines

We are really glad you're reading this, because we need volunteer developers to help this project come to fruition.

Request you to please read
our [contribution guidelines](https://devs-from-matrix.github.io/basic-template-repository/#/README?id=contribution-guidelines)
