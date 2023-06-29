package live.webcrawls.midas.common.formatter;

import net.kyori.adventure.text.Component;

/**
 * {@code ChatResult}
 */
public interface FormatResult {

    static FormatResult immutable(Component result, boolean applied) {
        return new ImmutableFormatResult(result, applied);
    }

    boolean applied();

    Component result();

}
