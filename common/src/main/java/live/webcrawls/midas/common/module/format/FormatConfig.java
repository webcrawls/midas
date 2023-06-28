package live.webcrawls.midas.common.module.format;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class FormatConfig {

    public @NonNull ResolverConfig resolver = new StaticResolverConfig();

    @ConfigSerializable
    public static class ResolverConfig {

        public @NonNull String type = "static";

    }

    @ConfigSerializable
    public static class StaticResolverConfig extends ResolverConfig {
        public @NonNull String type = "static";
        public @NonNull String format = "<name> <message>";
    }

}
