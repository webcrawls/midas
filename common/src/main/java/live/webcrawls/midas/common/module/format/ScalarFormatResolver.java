package live.webcrawls.midas.common.module.format;

import live.webcrawls.midas.common.config.FormatResolver;

import java.util.UUID;

public class ScalarFormatResolver implements FormatResolver {
    private final String format;

    public ScalarFormatResolver(final String format) {
        this.format = format;
    }

    @Override
    public String getFormat(UUID _uuid) {
        return this.format;
    }
}
