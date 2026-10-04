package net.example.nametag;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class NametagToggleMod implements ClientModInitializer {
    public static final String MOD_ID = "nametag_toggle";
    public static boolean hideNametags = false;
    private static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        // Registriert die Taste [B] in den Steuerungseinstellungen
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.nametag_toggle.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "category.nametag_toggle.title"
        ));

        // Event-Listener für jeden Client-Tick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                hideNametags = !hideNametags;
                
                // Statusmeldung in der Hotbar (Actionbar) anzeigen
                if (client.player != null) {
                    Text status = Text.literal("Namensanzeige: " + (hideNametags ? "§cAusgeblendet§r" : "§aEingeblendet§r"));
                    client.player.sendMessage(status, true);
                }
            }
        });
    }
}