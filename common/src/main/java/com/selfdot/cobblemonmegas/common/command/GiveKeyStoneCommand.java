package com.selfdot.cobblemonmegas.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.selfdot.cobblemonmegas.common.DataKeys;
import com.selfdot.cobblemonmegas.common.item.KeyStoneType;
import com.selfdot.cobblemonmegas.common.util.ItemUtils;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.Map;

public class GiveKeyStoneCommand implements Command<ServerCommandSource> {

    public static final Map<String, KeyStoneType> KEY_STONE_TYPES = Map.of(
        "ring", new KeyStoneType("Mega Ring", 80),
        "bracelet", new KeyStoneType("Mega Bracelet", 81),
        "cuff", new KeyStoneType("Mega Cuff", 82),
        "charm", new KeyStoneType("Mega Charm", 83),
        "sunmoon", new KeyStoneType("Key Stone", 84),
        "letsgo", new KeyStoneType("Key Stone", 85)
    );

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Collection<ServerPlayerEntity> players = EntityArgumentType.getPlayers(context, "players");
        if (players == null) return 0;
        String keyStoneType = StringArgumentType.getString(context, "keyStone");
        if (!KEY_STONE_TYPES.containsKey(keyStoneType)) {
            context.getSource().sendError(Text.literal("Invalid key stone type"));
            return -1;
        }
        KeyStoneType type = KEY_STONE_TYPES.get(keyStoneType);
        players.forEach(player -> {
            ItemStack keyStone = new ItemStack(Items.EMERALD);
            NbtCompound nbt = keyStone.getNbt();
            if (nbt == null) nbt = new NbtCompound();
            nbt.putInt("CustomModelData", type.customModelData());
            nbt.putBoolean(DataKeys.NBT_KEY_KEY_STONE, true);
            keyStone.setNbt(nbt);
            ItemUtils.setNameNoItalics(keyStone, type.name());
            player.giveItemStack(keyStone);
        });
        return SINGLE_SUCCESS;
    }

}
