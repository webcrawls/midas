package live.webcrawls.midas.common.context;

import live.webcrawls.midas.common.sender.ChatSender;
import net.kyori.adventure.text.Component;

/**
 * {@code ChatContext};
 */
public interface ChatContext {

    static ChatContext immutable(ChatSender sender,
                                 Component message,
                                 String rawMessage) {
        return new ImmutableChatContext(sender, message, rawMessage);
    }

    ChatSender sender();

    Component message();

    String rawMessage();

}
