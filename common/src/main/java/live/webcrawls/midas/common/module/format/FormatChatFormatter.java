package live.webcrawls.midas.common.module.format;

import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.formatter.ChatFormatter;
import live.webcrawls.midas.api.formatter.FormatResult;
import net.kyori.adventure.text.format.NamedTextColor;

public class FormatChatFormatter implements ChatFormatter {

    private final ScalarFormatResolver resolver;

    public FormatChatFormatter() {
        this.resolver = new ScalarFormatResolver("\\<<name>\\> <message>");
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
