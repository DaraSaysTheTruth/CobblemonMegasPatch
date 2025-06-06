package com.selfdot.cobblemonmegas.common.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.BattleRegistry;
import com.selfdot.cobblemonmegas.common.DataKeys;
import com.selfdot.cobblemonmegas.common.util.MegaUtils;
import com.selfdot.cobblemonmegas.common.util.NbtUtils;
import dev.architectury.event.EventResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void injectUse(
        World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir
    ) {
        if (!(user instanceof ServerPlayerEntity player)) return;
        ItemStack itemStack = player.getStackInHand(hand);
        NbtCompound nbt = NbtUtils.getNbt(itemStack, "");
        if (nbt.isEmpty() || !nbt.contains(DataKeys.NBT_KEY_KEY_STONE)) return;
        if (!nbt.getBoolean(DataKeys.NBT_KEY_KEY_STONE)) return;

        PokemonBattle battle = BattleRegistry.INSTANCE.getBattleByParticipatingPlayer(player);
        if (battle == null) return;
        BattleActor playerBattleActor = battle.getActor(player);
        if (playerBattleActor == null) return;
        List<ActiveBattlePokemon> activeBattlePokemon = playerBattleActor.getActivePokemon();
        if (activeBattlePokemon.isEmpty()) return;
        int finalPos = 0;
        String reasonCannotMegaEvolve = null;
        for(int pos=0;pos < playerBattleActor.getActivePokemon().size(); pos++){
            reasonCannotMegaEvolve = MegaUtils.reasonCannotMegaEvolve(player, activeBattlePokemon.get(pos).getBattlePokemon().getEffectedPokemon(),pos);
            if (reasonCannotMegaEvolve == null){finalPos = pos;}
        }
        MegaUtils.attemptMegaEvolveInBattle(player, true,finalPos);
        cir.setReturnValue(TypedActionResult.consume(itemStack));
    }

}
