package net.sealing99.silentsilhouette.sound;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.TheSilentSilhouette;

public class ModSounds {
    public static final SoundEvent AMBIENT_SILHOUETTE = registerSoundEvent("ambient_silhouette");

    public static final SoundEvent MINECRAFT = registerSoundEvent("minecraft");
    public static final RegistryKey<JukeboxSong> MINECRAFT_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(TheSilentSilhouette.MOD_ID, "minecraft_key"));

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(TheSilentSilhouette.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        TheSilentSilhouette.LOGGER.info("Registering Mod Sounds for " + TheSilentSilhouette.MOD_ID);
    }
}
