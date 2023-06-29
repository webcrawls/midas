package live.webcrawls.midas.common.config;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public class MidasConfiguration {

    public @NonNull List<String> enabledModules = List.of(
            "url",
            "greentext",
            "format"
    );

}
