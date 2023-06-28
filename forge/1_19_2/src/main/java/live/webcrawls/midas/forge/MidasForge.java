package live.webcrawls.midas.forge;

import com.mojang.logging.LogUtils;
import live.webcrawls.midas.api.context.ChatContext;
import live.webcrawls.midas.api.sender.ChatSender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MidasForge.MODID)
public class MidasForge {

    public static final String MODID = "midas";
    private static final Logger LOGGER = LogUtils.getLogger();

    private final ForgeMidasPlatform platform;

    public MidasForge() {
        this.platform = new ForgeMidasPlatform();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Midas commonSetup");
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
