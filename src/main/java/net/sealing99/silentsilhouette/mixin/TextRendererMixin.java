package net.sealing99.silentsilhouette.mixin;

import net.minecraft.client.gui.DrawContext;
import net.sealing99.silentsilhouette.util.dozen.ModNumberBaseUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DrawContext.class)
public class TextRendererMixin {
    @ModifyVariable(
            method = "drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private String modifyText(String text) {
        return text;
        //return ModNumberBaseUtils.convertText(text);
    }
}