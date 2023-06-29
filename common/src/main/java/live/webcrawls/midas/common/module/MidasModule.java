package live.webcrawls.midas.common.module;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.module.format.FormatResult;

/**
 * {@code ChatModule} defines common methods a chat module should implement.
 */
public interface MidasModule {

    String id();

    FormatResult apply(ChatContext context);

}
