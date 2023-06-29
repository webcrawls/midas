# Developing Midas

> Midas development containers utilize the [`itzg/minecraft-server`](https://hub.docker.com/r/itzg/minecraft-server)
> container. Thanks!

To provide an easier developer experience, Midas provides a [Docker compose](#) setup to run testing servers for various platforms.

To run a development server:
- [build the plugin](#)
- run [`./copyPlugins.sh`](#) (inside `dev/`) to copy mods & plugins to the development servers 
- run `docker compose run ${PLATFORM}_${VERSION}` to start a server

> **note**: this command might take a while on first launch :)

Supported platforms and versions:

- `forge`
    - `1_19_2` (port `:25570`)
    - `1_20_1` (port `:25571`)
- `paper`
    - `1_20_1` (port `:25573`)
