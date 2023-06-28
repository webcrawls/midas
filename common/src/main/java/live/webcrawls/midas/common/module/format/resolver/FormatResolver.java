package live.webcrawls.midas.common.module.format.resolver;

import java.util.UUID;

public interface FormatResolver {

    static FormatResolver resolving(String format) {
        return new StaticFormatResolver(format);
    }

    String getFormat(UUID uuid);

}
