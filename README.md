# OSRS Player Render Boundary

A RuneLite plugin that draws a square boundary centered at the player's true tile, showing where other players and NPCs begin to be rendered.

## Features

- **Configurable boundary size**: Default is 31 tiles (the actual render distance in OSRS)
- **Customizable appearance**: Configure the color and line width of the boundary
- **Real-time overlay**: The boundary follows the player as they move

## Configuration

The plugin provides the following settings:

- **Boundary Color**: The color of the render boundary (default: white)
- **Boundary Width**: The width of the boundary line in pixels (default: 2, range: 1-10)
- **Boundary Size**: The size of the boundary in tiles (default: 31, range: 1-100)

## Building

To build the plugin, ensure you have Java 11 or later installed, then run:

```bash
./gradlew build
```

The compiled plugin will be available in `build/libs/`.

## Installation

1. Build the plugin or obtain the JAR file
2. Place the JAR file in your RuneLite plugins folder
3. Enable the plugin in RuneLite's plugin configuration

## About

This plugin helps visualize the render boundary where other players and NPCs begin to appear. In Old School RuneScape, entities typically render within a 31x31 tile area centered on the player.