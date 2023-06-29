package live.webcrawls.midas.common.module.format;

import net.kyori.adventure.text.Component;

public class FormatResult {

    public static FormatResult changed(final Component result) {
        return new FormatResult(result, true);
    }

    public static FormatResult unchanged(final Component result) {
        return new FormatResult(result, false);
    }

    private final boolean applied;
    private final Component result;

    public FormatResult(final Component result,
                        final boolean applied) {
        this.applied = applied;
        this.result = result;
    }

    public boolean applied() {
        return this.applied;
    }

    public Component result() {
        return this.result;
    }
}
