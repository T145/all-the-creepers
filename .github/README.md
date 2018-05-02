<p align="center"><img src="https://i.imgur.com/0xeDA4S.png"/></p>

***

Redux of the original Elemental Creepers mod!

[![DownloadCount](http://cf.way2muchnoise.eu/elemental-creepers-redux.svg)](https://minecraft.curseforge.com/projects/elemental-creepers-redux)
[![SupportedVersions](http://cf.way2muchnoise.eu/versions/For%20MC%20_elemental-creepers-redux_all.svg)](https://minecraft.curseforge.com/projects/elemental-creepers-redux)

---
**_Table of Contents_**

1. [Dependencies](https://github.com/T145/elemental-creepers#dependencies)
2. [Workspace Setup](https://github.com/T145/elemental-creepers#workspace-setup)
3. [Project License](https://github.com/T145/elemental-creepers#license)
4. [Dev Support](https://github.com/T145/elemental-creepers#support)
5. [Contributing](https://github.com/T145/elemental-creepers/blob/master/.github/CONTRIBUTING.md)

---

## Dependencies

In order to get started with Minecraft mod development in this workspace, a few prerequisites are required:

1. [Git](https://git-scm.com/downloads)
2. [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (JDK 8)
3. *(Optional)* [Gradle](http://gradle.org/gradle-download/)

Each of the listed requirements contains a hyperlink that should take you directly to the correspondant download page.
Just download and install what is compatible with your OS.
Gradle is optional is because this workspace includes a Gradle wrapper,
so when executing commands that begin with `gradle`,
execute them using `gradlew` located in `ForgeWorkspace` instead and everything will function normally.

If you're using OSX, I highly recommend using [Homebrew](https://brew.sh/),
and installing everything by executing the following commands:
```bash
brew cask install java
brew install gradle
```
If you don't have Apple's Command Line Utilities installed before installing Homebrew, Hombrew will install them automatically.

---

## Workspace Setup

First, prepare the workspace by executing the following commands:
```bash
git submodule init
git submodule update
```

If you plan to use the Eclipse IDE, then execute:
```bash
gradle setupEclipseWorkspace
```
else try out IntelliJ IDEA:
```bash
gradle setupIdeaWorkspace
```

> If you don't have Gradle installed, use the executable located at `ForgeWorkspace/gradlew{.bat}`.
> This will install the Gradle wrapper once, and any proceeding commands should be executed using it.

Depending on your internet connection and the processing power of your machine, it may take a while to build.
For most people it takes about 10 minutes.

---

## License

Please consult the [official license](http://www.apache.org/licenses/LICENSE-2.0) if you wish to use mod source code. To use any mod assets, you may contatct [myself](https://github.com/T145) or the original creator for permission.

---

## Support

<div align="center">

**Patreon**: [https://www.patreon.com/user?u=152139](https://www.patreon.com/user?u=152139)
</div>

<div align="center">

**Paypal**: [paypal.me/T145](paypal.me/T145)
</div>

<div align="center">

**Bitcoin**: `1qrrPQqfbfXLRqzS6jb7A7Mgnzz85Mjxu`
</div>

<div align="center">

**Ethereum**: `0x9dbafc51abe8ce05cac6f8a39ebd4351706840b0`
</div>

<div align="center">

**Litecoin**: `LiV9SfDjFYLFRCzf9wTf7ap8BuRF39J7PB`
</div>

<div align="center">

**Vertcoin**: `Vc6ss1NaitEtdrZZsDhQuv9pytKR5caiFy`
</div>
