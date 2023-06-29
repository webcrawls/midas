package live.webcrawls.midas.paper.sender;

import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.sender.ChatSender;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

import java.util.Map;
import java.util.UUID;

public class ConsoleChatSender implements ChatSender {

    public static final ConsoleChatSender SENDER = new ConsoleChatSender();

    private static final UUID UUID = new UUID(0, 0);

    @Override
    public UUID uuid() {
        return UUID;
    }

    @Override
    public String username() {
        return "console";
    }

    @Override
    public Map<String, String> meta() {
        return Map.of();
    }

    @Override
    public void sendMessage(ChatContext ctx) {
        // todo
        // MidasPlatform.LOGGER.info(plaintextserialized);
    }

    @Override
    public void sendMessage(Identity identity, Component message) {
        // todo
        // MidasPlatform.LOGGER.info();
    }
}
