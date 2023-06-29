package live.webcrawls.midas.forge;

import com.mojang.logging.LogUtils;
import live.webcrawls.midas.common.MidasPlatform;
import live.webcrawls.midas.common.context.ChatContext;
import live.webcrawls.midas.common.sender.ChatSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.nio.file.Paths;
import java.util.Map;

@Mod(MidasForge.MODID)
public class MidasForge {

    public static final String MODID = "midas";
    private static final Logger LOGGER = LogUtils.getLogger();

    private MidasPlatform platform;

    public MidasForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ForgeMod.enableServerChatPreview();
        LOGGER.info("Loading MidasPlatform");
        this.platform = new MidasPlatform(Paths.get(".").toFile(), null);
        this.platform.load();
    }

    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        ChatSender sender = ChatSender.immutable(
                player.getUUID(),
                player.getName().plainCopy().toString(),
                Map.of()
        );

        Component text = forgeToAdventure(event.getMessage());
        ChatContext ctx = ChatContext.immutable(sender, text, event.getRawText());
        ChatContext formattedCtx = this.platform.formatContext(ctx);

        event.setMessage(adventureToForge(formattedCtx.message()));
    }

    private Component forgeToAdventure(net.minecraft.network.chat.Component component) {
        String json = net.minecraft.network.chat.Component.Serializer.toJson(component);
        Component adventure = JSONComponentSerializer.json().deserialize(json);
        return adventure;
    }

    private net.minecraft.network.chat.Component adventureToForge(Component component) {
        String json = JSONComponentSerializer.json().serialize(component);
        return net.minecraft.network.chat.Component.Serializer.fromJson(json);
    }
}
