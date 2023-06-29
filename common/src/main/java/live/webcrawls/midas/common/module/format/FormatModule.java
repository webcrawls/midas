package live.webcrawls.midas.common.module.format;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.formatter.ChatModule;
import live.webcrawls.midas.common.formatter.FormatResult;
import live.webcrawls.midas.common.module.format.resolver.StaticFormatResolver;
import net.kyori.adventure.text.format.NamedTextColor;

public class FormatModule implements ChatModule {

    private final StaticFormatResolver resolver;
    private final FormatConfig config;

    public FormatModule(FormatConfig config) {
        this.config = config;
        this.resolver = new StaticFormatResolver("\\<<name>\\> <message>");
    }

    @Override
    public String id() {
        return "format";
    }

    @Override
    public FormatResult apply(ChatContext context) {
        if (context.rawMessage().startsWith(">")) {
            return FormatResult.immutable(context.message().color(NamedTextColor.GREEN), true);
        } else {
            return FormatResult.immutable(context.message(), false);
        }
    }

}
