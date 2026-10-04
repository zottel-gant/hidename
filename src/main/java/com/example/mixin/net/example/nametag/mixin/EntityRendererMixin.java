package net.example.nametag.mixin;

import net.example.nametag.NametagToggleMod;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {
    @Inject(method = "hasLabel", at = @At("HEAD"), cancellable = true)
    private void onHasLabel(T entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof PlayerEntity && NametagToggleMod.hideNametags) {
            cir.setReturnValue(false);
        }
    }
}