package live.webcrawls.midas.common.formatter;

import net.kyori.adventure.text.Component;

public class ImmutableFormatResult implements FormatResult {

    private final boolean applied;
    private final Component result;

    public ImmutableFormatResult(final Component result,
                                 final boolean applied) {
        this.applied = applied;
        this.result = result;
    }

    @Override
    public boolean applied() {
        return this.applied;
    }

    @Override
    public Component result() {
        return this.result;
    }
}
