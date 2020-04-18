# hexagonal-spring-boot-maven [![Build Status](https://travis-ci.com/devs-from-matrix/hexagonal-spring-boot-maven.svg?branch=master)](https://travis-ci.com/devs-from-matrix/hexagonal-spring-boot-maven) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/c4a6502171544f3a919bcdce35cb50b1)](https://www.codacy.com/gh/devs-from-matrix/hexagonal-spring-boot-maven?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=devs-from-matrix/hexagonal-spring-boot-maven&amp;utm_campaign=Badge_Grade)

This project is a template reference for hexagonal spring boot. This repository is to be used by [app-generator](https://github.com/devs-from-matrix/app-generator) for scaffolding.

The keywords of the app-generators are the following

- `packagename` - to rename the package names
- `artifactName` - to rename the artifact id
- `Example` - to rename class, variables 

Use it with caution as these will be used by the app-generator to replace them with domain specific name in the scaffold code. 

## Pre-requisite 

- maven
- open jdk 11

## How to build ?

`mvn clean install`

## How to start ?

`cd bootstrap && mvn spring-boot:run`

## Contribution guidelines

We are really glad you're reading this, because we need volunteer developers to help this project come to fruition.

Request you to please read our [contribution guidelines](https://devs-from-matrix.github.io/basic-template-repository/#/README?id=contribution-guidelines)
