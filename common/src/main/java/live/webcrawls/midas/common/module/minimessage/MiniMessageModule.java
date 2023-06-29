package live.webcrawls.midas.common.module.minimessage;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.module.MidasModule;
import live.webcrawls.midas.common.module.format.FormatResult;

public class MiniMessageModule implements MidasModule {
    @Override
    public String id() {
        return "minimessage";
    }

    @Override
    public FormatResult apply(ChatContext context) {
        return FormatResult.unchanged(context.message());
    }
}
