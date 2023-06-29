package live.webcrawls.midas.common.module.markdown;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.module.MidasModule;
import live.webcrawls.midas.common.module.format.FormatResult;

public class MarkdownModule implements MidasModule {
    @Override
    public String id() {
        return "markdown";
    }

    @Override
    public FormatResult apply(ChatContext context) {
        return FormatResult.unchanged(context.message());
    }
}
