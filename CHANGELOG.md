# 1.3.4
- Pokémon can now be in any slot within Double and Triple PVP Battles.
- Right-clicking the keystone will now give feedback based on active Pokémon eligibility on Mega Evolution.
    - Right-clicking the keystone with an eligible active Pokémon will now give feedback on mega evolving next turn when the player chooses a move.
    - You can right-click again to toggle off the mega evolution choice. 
    - The keystone will gain an enchantment glint when toggled on.

# 1.3.3
- Fixed Charizard and Mewtwo abilities not being set after mega evolving
- Made mega evolution possible in double-triple pvp formats. However ! : 
    - The Pokémon you chose to mega evolve must be in the first battleground slot of your side (The first Pokémon you take action on at the beginning of a turn).
    - You must right-click with the keystone in hand before choosing a move. The mega evolution button won't work.
  

*Note: After following feedbacks from some Cobblemon addon and original mod developers, 
it seems that fully supporting Mega Evolution in multi battles may be technically not possible. 
This limitation comes from the fact that the mod is server-side only, and Cobblemon clients don't handle communication properly for Mega Evolution and other gimmicks outside of single battles.*

*If you have any insights or solutions, feel free to open a ticket to get in touch.*

# 1.3.2
- Fixed abilities being set incorrectly after mega evolving.

# 1.3.0
- Made key stones required for mega evolving in battle.
- Added /migratestone command to migrate mega stones from AaronMusk's mega mod.
- Fixed compatibility with other gimmick mods.

# 1.2.0
- Added mega evolve button in battle GUI (still only requires mod on server-side)

# 1.1.3
- Fixed Mega raid bosses in CobblemonRaids being changed to normal form on first battle.
- Fixed permissions not working on Arclight.

# 1.1.2
- Fixed crash on start when using Arclight.

# 1.1.1
- Increased mixin compatibility level from Java 16 to Java 17.

# 1.1.0
- Added key stone items which can be used to mega evolve in battle:
  - Mega Ring
  - Mega Bracelet
  - Mega Cuff
  - Mega Charm
  - Key Stone (Sun/Moon)
  - Key Stone (Let's Go Pikachu/Eevee)
- Added /givekeystone \<keyStoneType\> \<player\> (selfdot.megas.givekeystone)
- Changed key stone and mega stone item names to not be in italics.
- Fixed Rayquaza being allowed to mega evolve without Dragon Ascent.

# 1.0.2
- Added config option "megaRayquazaAllowed" to control whether mega rayquaza can be used.
- Fixed /givemegastone only being usable by players.

# 1.0.1
- Fixed Forge crash on startup
