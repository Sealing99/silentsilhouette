package net.sealing99.silentsilhouette.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.sound.ModSounds;

import java.util.concurrent.CompletableFuture;

public class ModJukeboxSongDataProvider extends FabricDynamicRegistryProvider {
    public ModJukeboxSongDataProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        RegistryWrapper.Impl<SoundEvent> soundEvents = registries.getWrapperOrThrow(RegistryKeys.SOUND_EVENT);

        Identifier soundId = Identifier.of("silentsilhouette", "minecraft");

        var soundEntry = soundEvents.getOptional(RegistryKey.of(RegistryKeys.SOUND_EVENT, soundId))
                .orElseThrow(() -> new IllegalStateException("Sound Event not found in registry: " + soundId));

        JukeboxSong song = new JukeboxSong(
                soundEntry,
                Text.translatable("item.silentsilhouette.minecraft_music_disc.desc"),
                170.0F,
                15
        );

        entries.add(ModSounds.MINECRAFT_KEY, song);
    }

    @Override
    public String getName() {
        return "JukeboxSongDataProvider";
    }
}
