package live.webcrawls.midas.common.sender;

import live.webcrawls.midas.common.context.ChatContext;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

import java.util.Map;
import java.util.UUID;

public record ImmutableChatSender(UUID uuid,
                                  String username,
                                  Map<String, String> meta) implements ChatSender {
    @Override
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public Map<String, String> meta() {
        return this.meta;
    }

    @Override
    public void sendMessage(ChatContext ctx) {
        return;
    }

    @Override
    public void sendMessage(Identity identity, Component message) {
        return;
    }
}
