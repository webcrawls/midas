package live.webcrawls.midas.paper;

import live.webcrawls.midas.api.formatter.ChatFormatter;
import live.webcrawls.midas.common.module.GreentextChatFormatter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MidasPlugin extends JavaPlugin {

    private PaperMidasPlatform chatService;
    private List<ChatFormatter> formatters;

    @Override
    public void onEnable() {
        this.getLogger().info("hello, world");
        this.formatters = new ArrayList<>();
        this.formatters.add(new GreentextChatFormatter());

        this.chatService = new PaperMidasPlatform(this, this.formatters);
        this.chatService.registerListeners();
    }

}
