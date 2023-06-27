# Developing Midas

> Midas development containers utilize the [`itzg/minecraft-server`](https://hub.docker.com/r/itzg/minecraft-server)
> container. Thanks!

The Midas development environment allows you to quickly spin up pre-configured servers for all supported platforms.

To run a development server, [build Midas](#) and run the following commands:

- `./copyPlugins.sh`
- `docker compose up ${PLATFORM}_${VERSION}`

> **note**: this command will take a while on first launch :)

Supported platforms and versions:

- `forge`
    - `1_19_2` (port `:25570`)
    - `1_20_1` (port `:25571`)
- `paper`
    - `1_20_1` (port `:25572`)
