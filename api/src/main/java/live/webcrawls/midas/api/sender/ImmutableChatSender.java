package live.webcrawls.midas.api.sender;

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
}
