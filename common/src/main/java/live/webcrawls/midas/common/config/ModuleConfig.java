package live.webcrawls.midas.common.config;

import io.leangen.geantyref.TypeToken;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;

import java.io.File;

public class ModuleConfig<T> {

    private final File file;
    private final TypeToken<T> type;
    private @MonotonicNonNull HoconConfigurationLoader loader;

    public ModuleConfig(final File file,
                        final TypeToken<T> type) {
        this.file = file;
        this.type = type;
    }

    public @NonNull File file() {
        return this.file;
    }

    public @NonNull T load() {
        this.loader = HoconConfigurationLoader.builder()
                .file(this.file)
                .defaultOptions(opts -> opts
                        .serializers(TypeSerializerCollection.defaults())
                        .shouldCopyDefaults(true))
                .build();
        try {
            return this.loader.load().get(this.type);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }


}
