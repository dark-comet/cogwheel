# Cogwheel
![Status](https://img.shields.io/badge/Status-In%20Development-orange?style=flat)
![Discord API Version](https://img.shields.io/badge/Discord%20API%20Version-v10-green?style=flat)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/dc99d8c35fe94c87a427a07499135cd0)](https://app.codacy.com/gh/dark-comet/Cogwheel/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Automated Tests](https://github.com/dark-comet/Cogwheel/actions/workflows/automated-tests.yml/badge.svg)](https://github.com/dark-comet/Cogwheel/actions/workflows/automated-tests.yml)

## Overview
Cogwheel is a Discord API library written in Kotlin, for building Discord bots
in Kotlin or Java. Other JVM-based languages may be supported but are not the 
intended target audience.

This library is designed to be a streamlined one-stop-shop for quickly getting
started with Discord bot development. The public API is designed with developer 
productivity in mind: high level domain models to allow the succinct expression
of ideas, strongly typed domain primitives to prevent API misuse, 
comprehensively enumerated option values to save time cross-checking Discord's 
official documentation, and carefully chosen type and parameter names for 
self-documenting code. 

It is a key design goal for Cogwheel to be intuitive to use, easy to get started, 
and powerful enough to support most (if not all) common use cases. I hope you have 
a delightful time crafting your own bots with it.

[//]: # (## Example Code)


## Getting Started
Architecturally, the project consists of two layers: 
- `cogwheel-core` is the low-level module that defines the data transfer objects (DTOs) 
   and the core network infrastructure to interface with Discord's REST and Gateway APIs.
- `cogwheel-framework` builds on top of `cogwheel-core`, providing a set of opinionated, 
   high-level, domain-driven language constructs. These facilitate the succinct expression 
   of behavior, and improve the overall integrity of the client code through domain primitives. 

In most cases, `cogwheel-framework` is the recommended dependency to quickly get started 
with building your bot. Use `cogwheel-core` only if you want to provide a different set of 
high level constructs, without repeating the dry work of repeating low-level infrastructure.

## Supported Discord API Versions
Currently, the library targets Discord's API version 10.

[//]: # (## How to Contribute)