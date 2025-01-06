# Cogwheel
![Status](https://img.shields.io/badge/Status-In%20Development-orange?style=flat)
![Discord API Version](https://img.shields.io/badge/Discord%20API%20Version-v10-green?style=flat)
![License](https://img.shields.io/badge/License-MIT-blue?style=flat)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/dc99d8c35fe94c87a427a07499135cd0)](https://app.codacy.com/gh/dark-comet/Cogwheel/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Automated Tests](https://github.com/dark-comet/Cogwheel/actions/workflows/automated-tests.yml/badge.svg)](https://github.com/dark-comet/Cogwheel/actions/workflows/automated-tests.yml)

A Discord API library for JVM-based languages, built in Kotlin. The project consists of two layers: 
- `cogwheel-core` is the low-level module providing Kotlin language bindings for the Discord API, 
and the core network infrastructure to interact with it.
- `cogwheel-framework` builds on top of `cogwheel-core`, providing a set of high-level language
constructs to implement bot functionalities with. In most cases, this is the recommended dependency.

Refer to the documentation in `docs/*` and/or the projects in `examples` folder to get started.

[//]: # (## Getting Started)

[//]: # (## Contributing)