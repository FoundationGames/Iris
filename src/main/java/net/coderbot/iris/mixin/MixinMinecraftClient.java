package net.coderbot.iris.mixin;

import net.coderbot.iris.Iris;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "joinWorld", at = @At("TAIL"))
    public void gameJoin(ClientWorld world, CallbackInfo ci) {
        reloadIris();
    }

    @Inject(method = "setWorld", at = @At("TAIL"))
    public void worldSet(ClientWorld world, CallbackInfo ci) {
        reloadIris();
    }

    private void reloadIris() {
        try {
            Iris.reload();
        } catch (IOException e) {
            Iris.logger.error("Error loading shaders on game join");
            e.printStackTrace();
        }
    }
}
