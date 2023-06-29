package live.webcrawls.midas.common.module.greentext;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.module.MidasModule;
import live.webcrawls.midas.common.module.format.FormatResult;
import net.kyori.adventure.text.format.NamedTextColor;

public class GreentextModule implements MidasModule {
    @Override
    public String id() {
        return "greentext";
    }

    @Override
    public FormatResult apply(ChatContext context) {
        if (context.rawMessage().startsWith(">")) {
            return FormatResult.changed(context.message().color(NamedTextColor.GREEN));
        } else {
            return FormatResult.unchanged(context.message());
        }
    }


}
