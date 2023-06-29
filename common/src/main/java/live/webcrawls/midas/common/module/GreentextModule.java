package live.webcrawls.midas.common.module;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.formatter.ChatModule;
import live.webcrawls.midas.common.formatter.FormatResult;
import net.kyori.adventure.text.format.NamedTextColor;

public class GreentextModule implements ChatModule {
    @Override
    public String id() {
        return "greentext";
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
