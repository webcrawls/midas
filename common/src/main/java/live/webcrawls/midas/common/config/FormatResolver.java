package live.webcrawls.midas.common.config;

import live.webcrawls.midas.common.module.format.ScalarFormatResolver;

import java.util.UUID;

public interface FormatResolver {

    static FormatResolver resolving(String format) {
        return new ScalarFormatResolver(format);
    }

    String getFormat(UUID uuid);

}
