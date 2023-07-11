package live.webcrawls.midas.common.config;

import live.webcrawls.midas.common.MidasPlatform;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;

import java.io.File;

public class ConfigurationService {

    private final File dataDirectory;
    private HoconConfigurationLoader configurationLoader;
    private ConfigurationNode root;
    private boolean triedToLoad = false;

    public ConfigurationService(final File dataDirectory) {
        this.dataDirectory = dataDirectory;

        // set defaults
        this.configurationLoader = null;
        this.root = null;
    }

    public MidasConfiguration getConfiguration() {
        if (this.root != null) {
            try {
                return this.root.get(MidasConfiguration.class);
            } catch (SerializationException e) {
                throw new RuntimeException(e);
            }
        }

        if (this.triedToLoad) {
            return null;
        }

        try {
            this.triedToLoad = true;
            this.configurationLoader = makeConfigurationLoader(new File(this.dataDirectory, "config.conf"));
            this.root = this.configurationLoader.load();
            this.configurationLoader.save(this.root);
        } catch (Exception e) {
            MidasPlatform.LOGGER.severe("Failed to load configuration.");
            e.printStackTrace();
        }

        if (this.root != null) {
            try {
                return this.root.get(MidasConfiguration.class);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public <T> T getModuleConfiguration(final String moduleId) {
        // todo stub
        return null;
    }

    private HoconConfigurationLoader makeConfigurationLoader(File file) {
        return HoconConfigurationLoader
                .builder()
                .file(file)
                .defaultOptions(opts -> opts
                        .shouldCopyDefaults(true)
                        // todo load module configuration serializers
                        .serializers(TypeSerializerCollection.defaults()))
                .build();
    }

}
