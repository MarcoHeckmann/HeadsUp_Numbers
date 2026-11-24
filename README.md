# HeadsUp Numbers

A Minecraft Paper plugin project for a two-player number-guessing game. The repository is marked **under maintenance**; the notes below describe the source currently present and do not imply a finished release.

## Current implementation

The source includes a game state for two players, a randomly selected number within a configured range, turn selection, a maximum guess count, and chat and join listeners. The range and guess limit are read from `game.yml`.

The plugin metadata names the plugin `HeadsUpNumbers` and declares Paper API version `1.20`. This README describes the source project; it does not provide server installation instructions.

## Build from source

The Maven project is in `HeadsUpNumbers/`. With Java 21 and Maven installed:

```bash
cd HeadsUpNumbers
mvn clean package
```

Maven writes the build output under `HeadsUpNumbers/target/`.

## Project layout

- `HeadsUpNumbers/src/main/java/` — plugin, game state, and listeners
- `HeadsUpNumbers/src/main/resources/game.yml` — game settings
- `HeadsUpNumbers/src/main/resources/plugin.yml` — Paper plugin metadata

The project is licensed under the [MIT License](LICENSE).
