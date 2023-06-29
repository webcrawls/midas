package live.webcrawls.midas.common.config;

import live.webcrawls.midas.common.MidasPlatform;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;

import java.io.File;

public class ConfigurationService {

    private final File dataDirectory;
    private HoconConfigurationLoader configurationLoader;
    private MidasConfiguration configuration;
    private boolean triedToLoad = false;

    public ConfigurationService(final File dataDirectory) {
        this.dataDirectory = dataDirectory;

        // set defaults
        this.configurationLoader = null;
        this.configuration = null;
    }

    public MidasConfiguration getConfiguration() {
        if (this.configuration != null) {
            return this.configuration;
        }

        if (this.triedToLoad) {
            return null;
        }

        try {
            this.triedToLoad = true;
            this.configurationLoader = makeConfigurationLoader(new File(this.dataDirectory, "config.conf"));
            this.configuration = this.configurationLoader.load().get(MidasConfiguration.class);
        } catch (Exception e) {
            MidasPlatform.LOGGER.severe("Failed to load configuration.");
            e.printStackTrace();
        }

        return this.configuration;
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
                        .serializers(TypeSerializerCollection.builder().build()))
                .build();
    }

}
