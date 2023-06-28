package live.webcrawls.midas.common.module.format.resolver;

import java.util.UUID;

public class StaticFormatResolver implements FormatResolver {

    private final String format;

    public StaticFormatResolver(final String format) {
        this.format = format;
    }

    @Override
    public String getFormat(UUID _uuid) {
        return this.format;
    }

}
