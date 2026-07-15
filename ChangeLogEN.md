[中文版](https://github.com/XiaoYuOvO/MITE-ITE/blob/master/ChangeLogCN.md)
# v2.1.0 Preview
* Annihilation Skeletons strengthen themselves by picking up vibranium nuggets
* Annihilation Skeletons enhanced by the Beheading enchantment may drop their heads; these heads are used for crafting and summoning bosses
* Stackable curses (configurable)
* Forge an Essence Record with assorted materials to randomly increase one modifier by one level
* A new bat combining the Vampire Bat and Shadow Bat, with texture-based invisibility
* Added Stand Firm, a leggings enchantment that reduces knockback and impact
* More modifiers
* Bow modifiers and bow forging
  + Piercing / Infinity / Stabbing / Durability / Power / Bone Crushing
  + Forging bonuses to arrow velocity

---

# v2.0.29 Changelog
## Added
* Monsters holding weapons enchanted with Disarming now continuously emit identifying particles

---

# v2.0.28 Changelog
## Bug Fixes
* Fixed a server crash caused by a disarmed item entity being null in certain situations

---

# v2.0.27 Changelog
## Bug Fixes
* Fixed crafting speed when installed together with ITF-Reborn

---

# v2.0.26 Changelog
## Bug Fixes
* Fixed the Atomic Reconstructor achievement being unobtainable

---

# v2.0.25 Changelog
## Bug Fixes
* Further relaxed Overworld lightning-rendering restrictions that previously required thunderstorms

---

# v2.0.24 Changelog
## Bug Fixes
* Corrected the player's maximum health

---

# v2.0.23 Changelog
## Bug Fixes
* Relaxed lightning-rendering conditions so lightning summoned by Extreme's Zombie Boss and similar mobs is displayed correctly
## Other
* Added probability settings for ore generation

---

# v2.0.22 Changelog
## Bug Fixes
* Fixed the player's maximum health being 40 instead of 60

---

# v2.0.21 Changelog
## Bug Fixes
* Fixed the longer first-day daytime setting not working as intended

---

# v2.0.20 Changelog
## Bug Fixes
* Fixed several furnace recipe conflicts

---

# v2.0.19 Changelog
## Mechanics
* Reworked the extended first day: time now resets from 18:00 to 06:00 to avoid bugs
## Other
* Optimized code
## Bug Fixes
* Fixed the longer first-day daytime setting having no effect
* Fixed a crash during random Underworld teleportation
* Fixed several other crashes

---

# v2.0.18 Changelog
## Compatibility
* Added compatibility with the custom Extreme build by Liu Hao
## Other
* Optimized code
## Bug Fixes
* Fixed a possible crash when attacking a Zombie Boss while Extreme is installed

---

# v2.0.17 Changelog
## Compatibility
* Improved compatibility
## Bug Fixes
* Fixed a series of enchanting issues, including issues affecting add-ons

---

# v2.0.16 Changelog
## Items
* Moved this mod's enchanted books to a dedicated creative inventory tab
* Updated the textures of vibranium arrows and the vibranium bow
## GUI
* Added EMI avoidance for forging information in the forging-table interface
## Mechanics
* Added the ***Player blocking requires an enchantment*** setting
## Development
* Improved modifier registration
* Improved material-related code
* Changed the packages of `Materials` and `EnumEquipmentMaterials`
## Bug Fixes
* Fixed untranslated portions of equipment-upgrade messages on servers

---

# v2.0.15 Changelog
## Resources
* Improved some item texture paths
* Removed duplicate textures
## Mechanics
* Added configurable ***spawn frequencies for Zombie Lords, Zombie Pigman Lords, Annihilation Skeletons, and Wandering Witches***
* Changed the default ***mob base attribute/equipment enhancement day cap*** to 366 days
## Bug Fixes
* Fixed fertilizer being unable to cure crops
* Fixed vibranium weapons being unable to have Sharpness, Smite, and Bane of Arthropods together
* Fixed skeletons being unable to be forced into melee combat
* Fixed untranslated portions of tool/equipment upgrade messages on servers
* Fixed the held-item position of Annihilation Skeletons
* Fixed enhanced mob equipment ignoring the ***mob base attribute/equipment enhancement day cap***

---

# v2.0.14 Changelog
## Items
* When Extreme is also installed, this mod's basic vibranium ingot recipe is disabled
## Bug Fixes
* Fixed excessive adamantium generation; adamantium generation now uses the v1.1.0 mechanics
* Fixed fertilizer being unable to cure crops
* Fixed vibranium weapons being unable to have Sharpness, Smite, and Bane of Arthropods together

---

# v2.0.13 Changelog
## Bug Fixes
* Fixed Ghasts not spawning
* Fixed a server crash when upgrading modifiers

---

# v2.0.12 Changelog
## Commands
* Replaced `/forging_grade set` with `/forging <player> <set> <level>`
* Replaced `/itemlevel setLevel` and `/itemlevel setXp` with `/itemlevel <player> <set> <level/xp> <value>`
* Removed `/teleport` and `/xsummon`; use the commands provided by OhMyCommands instead
## Development
* Improved equipment and weapon modifier code. Add-ons can now extend `ToolModifierTypes` or `ArmorModifierTypes` to create modifiers
## Bug Fixes
* Fixed Nether portals failing to activate above mantle level Y=4
* Fixed vanilla MITE and modded mobs not spawning in the Nether
* Fixed weapons registered by other mods being unable to receive weapon modifiers
* Fixed the Underworld adamantium weight adjustment having no effect
* Fixed server crashes when receiving or upgrading modifiers
* Fixed the incorrect adamantium generation height
* Fixed a crash when closing the Villager trading GUI in certain situations
* Fixed a crash when forging level exceeded 3000
* Fixed the Immunity modifier lacking a translation
* Fixed several compatibility issues with Extreme

---

# v2.0.11 Changelog
## Mobs
* Added the ***mob base attribute/equipment enhancement day cap*** setting, defaulting to 256
## Items
* Added the ***lapis lazuli experience*** setting, defaulting to 40
* Upgrading ancient-metal equipment to adamantium equipment now preserves modifiers, enchantments, and forging level
* Upgrading ancient-metal tools to vibranium tools now preserves modifiers, enchantments, and forging level
## Other
* Optimized code and improved compatibility
* Added localization for equipment enhancements
* Renamed the armor Persistence modifier to Durability
* Restored vanilla subtitles and moved the previous content to the lower-right corner
## Bug Fixes
* Fixed Annihilation Skeletons spawning in water
* Fixed a crash caused by the `/stats` command
* Fixed Underworld rune portals possibly teleporting players into the mantle

---

# v2.0.10 Changelog
## FishModLoader 3.4.0 is now required
## Other
* Moved this mod's resources under `assets/miteite/`
## Bug Fixes
* Fixed Wooden Earth Elementals being invulnerable

---

# v2.0.9 Changelog
## RustedIronCore 1.3.5 is now required
## Items
* Fixed incorrect emerald experience
* **_Wolves now attack players carrying a greater variety of meat in their inventory_**
## Development
* Mods can now register meats that cause wolves to attack when carried in a player's hotbar
## Other
* Moved added items and blocks to a dedicated creative inventory tab
* Block recipes now use the API provided by RustedIronCore
* Removed Mixins that duplicated behavior already supplied by FishModLoader

---

# v2.0.8 Changelog
## RustedIronCore 1.3.4 is now required
## Mobs
* Reduced angry Pigman movement speed from 1 to 0.5
* Annihilation Skeletons no longer spawn in water
* At most one Zombie Lord, Zombie Pigman Lord, Wandering Witch, or Annihilation Skeleton may spawn at once
## Mechanics
* Restored Beacon crafting acceleration
  * Each acceleration interval now lasts 8 seconds
* Crafting adamantium equipment into vibranium equipment now preserves quality
## Bug Fixes
* Fully fixed excessively high Pigman movement speed
* Fixed several unexplained crashes
* Fixed the Forging Table mining too slowly
* Fixed the missing Zombie Pigman Lord translation
* Fixed Beacon acceleration frequently disappearing

---

# v2.0.7 Changelog
## Development
* Mods can add a `mite_ite` entrypoint under `entrypoints` in `mod.json` to modify or integrate with this mod
## Other
* Restored the wool-for-emerald-shard trade
* Added EMI integration
## Bug Fixes
* Fixed abnormal durability loss when mining with the Durability modifier
* Fixed the missing Forging Table translation

---

# v2.0.6 Changelog
## Mobs
* Changed mob attributes
  + Enderman
    - Health: 60 + days / 20
    - Attack: 15 + days / 20
  + Zombie Pigman
    - Health: 40 + days / 16
## Mechanics
* Removed sitting on stairs; install SitMod to restore this feature
## Bug Fixes
* Fixed the Durability modifier having no effect
## Other
* Optimized some code

---

# v2.0.5 Changelog
## RustedIronCore v1.3.0 is now required
## Other
* Greatly improved compatibility. MITE-ITF-Reborn is now supported, but GA-Higher is not
* Removed some `@Overwrite` methods and Mixins
* Completed several TODOs
* Renamed vibranium material to `mite_ite_vibranium` to avoid conflicts with MITE-ITF-Reborn
* Temporarily removed wool-for-emerald-shard trades and disabled the related setting
* Updated several translations to match the newer translation set
* Separated the Vibranium Workbench
* Removed the Zombie Pigman's Speed effect
## Bug Fixes
* Fixed Vibranium Strongbox textures when held or shown in containers
* Fixed a conflict with inventory sorting mods using the R key
* Fixed abnormal Zombie health
* Fixed abnormal Zombie Pigman movement speed

---

# v2.0.4 Changelog
## Other
* Fully removed the nutrition bar to allow server compatibility with Bread Skin

---

# v2.0.3 Changelog
## Mechanics
* Removed Beacon workbench acceleration due to compatibility issues
## Bug Fixes
* Fixed sickles not gaining experience

---

# v2.0.2 Changelog
## Bug Fixes
* Fixed skeleton-type mobs not rendering held weapons and equipment
* Fixed Revenants' held weapons disappearing
* Fixed incorrect enchantment limits on vibranium items
* Fixed incorrect enchantments on some vibranium items
* Fixed Nether portals failing to activate at mantle level 75
* Fixed mattocks not gaining experience from mining
## Other
* Added namespaces
* Added support for FishModLoader v3.3.2
* Removed the nutrition bar; install Bread Skin Mod if needed

---

# v2.0.1 Changelog
## Bug Fixes
* See the commits
## Other
* Added support for FishModLoader v3.2.0

---

# v2.0.0 Changelog
## Mobs
* Added spawn eggs for the new mobs
## Other
* Added support for FishModLoader v3.0.0
* Optimized a large amount of code

---

# v1.1.0-pre14 Changelog
## Mobs
* Added a setting controlling whether Annihilation Skeletons can spawn in lit areas of the Overworld
* Doubled Green Slime damage (configurable)
## Bug Fixes
* Fixed players with negative levels dealing no progressive damage

---

# v1.1.0-pre13 Changelog
## Mobs
* Wandering Witch spawning can now be changed in the configuration
## Bug Fixes
* Fixed a crash involving bats summoned by Wandering Witches

---

# v1.1.0-pre12 Changelog
## Mobs
* Moved the Wandering Witch spawn day to the configuration
## Bug Fixes
* Fixed an incorrect maximum equipment enhancement level

---

# v1.1.0-pre11 Changelog
## World Generation
* Moved all Underworld ore generation heights upward to Y=130-250

---

# v1.1.0-pre10 Changelog
## Bug Fixes
* Fixed Nether portals sending players into the mantle and fixed incorrect portal type detection

---

# v1.1.0-pre9 Changelog
## World Generation
* Reduced Wandering Witch spawn rates to 1/3 in the Overworld and 1/2 in the Underworld; both are configurable
## Bug Fixes
* Fixed Giant Zombie pathfinding AI

---

# v1.1.0-pre8 Changelog
## Blocks
* Added the Vibranium Strongbox
## World Generation
* Halved Nether adamantium attempts per chunk and maximum vein size, with configuration settings
* Reduced Ghast spawns during Blood Moons
## Enchantments
* Emergency can no longer appear below 5,000 enchanting experience
## Bug Fixes
* Fixed Underworld rune portals trapping players in the mantle
* Fixed the wool-for-emerald-shard setting not appearing in the configuration
* Fixed axes gaining no experience from logs and incorrect experience bonuses for shovels and hoes
* Fixed Blood Moons failing to spawn Ghasts, Giant Zombies, and other special mobs

---

# v1.1.0-pre7 Changelog
## Bug Fixes
* Fixed Pigmen attacking animals with default settings
* Fixed a crash caused by Wandering Witches

---

# v1.1.0-pre6 Changelog
## Mobs
* The Ender Dragon actively attacks players below 150 health, and its attack bonus is now 40%

---

# v1.1.0-pre5 Changelog
## Mobs
* Below 150 health, the Ender Dragon no longer heals; it instead takes 20% less damage, deals 20% more damage, and gains 20% attack reach
* Reduced base attack damage of all Skeleton variants by 2 and reduced tool scaling to 1.15x
## Enchantments and Enhancements
* Changed Extend enchantment difficulty to 15
* Changed forging success rates: Polishing Stone 65%, Refining Stone 45%, Tempering Stone 35%
* Doubled the weight of Geology
## Bug Fixes
* Fixed Pigman attacks setting players on fire for too long
* Fixed excessively low Vibranium Bow durability

---

# v1.1.0-pre4 Changelog
## Mobs
* Slimes no longer trigger progressive damage
* Maximum mob buff levels are Speed IV and Strength III
* Reduced default mob attack reach by 20%
* Increased direct-hit Ghast damage radius to about five blocks
## Bug Fixes
* Fixed Overworld mob equipment progression starting at iron equipment
* Fixed instant kills granting no experience

---

# v1.1.0-pre3 Changelog
## World Generation
* Reduced Underworld adamantium frequency to 1/8 without depth scaling
* Increased Nether adamantium by 20%
## Mobs
* Mob progression in every dimension now uses the Overworld day
* Wandering Witch changes:
  + Spawns after day 64 and summons bats upon encountering a player
  + Attacks with potions every four seconds while fleeing and applies Slowness I for 1-2.5 seconds
  + Flees 10% faster, summons only special bats (20% Nightwing and 80% Giant Vampire Bat), and throws potions 30% faster
* Annihilation Skeleton changes:
  + Movement speed increased by another 5%, spawning begins on day 32, Knockback V added, and health set to 30
## Items
* Iron armor can be upgraded to mithril armor by adding the iron piece to the original recipe without reducing mithril cost
* Increased default breeding experience: chicken 8, sheep 10, pig 15, cow 25, fishing 10
* Standardized default experience values to 400 for diamonds and 300 for emeralds
* Increased apple insulin by 50%
* Mining-tool enhancements now increase mining speed by a percentage
* Reduced armor enhancement value per level by 25%
* Set iron armor enhancement cap to 3 and mithril armor cap to 5
* Doubled armor durability consumption and armor experience gain
* Only vibranium weapons and tools can receive Blocking
* Emergency is rarer on vibranium equipment, but reaches a 40% chance at maximum enchanting power
## Other
* Updated forging-table textures to distinguish tiers
## Bug Fixes
* Fixed quality changes after enhancement imposing an unintended repair-level requirement
* Fixed hoppers collecting disarmed mob weapons and allowing players to obtain them
* Fixed Underworld equipment progression not starting from iron
* Fixed random Underworld teleportation trapping players in the mantle
## Mechanics
* Forging bonuses can be freely combined: iron axe 0%, iron hammer 0%, mithril axe 2%, mithril hammer 3%, adamantium axe 4%, adamantium hammer 6%, vibranium axe 8%, vibranium hammer 12%

---

# v1.1.0-pre2 Changelog
## World Generation
* Made Underworld adamantium generation more evenly distributed and reduced vein size
## Mechanics
* Changed failure penalties: levels 0-4 lose 5% durability; 5-7 lose 5% and one level; 8-10 lose 10% and one level
## Mobs
* Reduced Skeleton attack tool scaling from 2x to 1.25x
* Reduced Splitting Skeleton chance to 3%
## Items
* Changed Forging Table recipes (see recipe list)
* Iron Forging Tables can now be crafted at iron workbenches
## Bug Fixes
* Fixed enchanting Vibranium Leggings with three to four bookshelf rows freezing the save and requiring rollback

---

# v1.1.0-pre1 Changelog
## Mechanics
* Iron blocks can be smelted into mithril in an obsidian furnace using lava
* Players can trigger progressive damage (`level * 0.1`, maximum 2)
* Workbench crafting bonuses: flint 0%, copper/silver 10%, iron 20%, mithril 30%, adamantium 40%, vibranium 55%
* Beacon crafting bonuses: level I 25%, II 40%, III 60%, IV 85%
* Nether ground heat begins after day 96
* Underworld Fatigue is capped at level II; after 20 minutes at Fatigue II the player is randomly teleported
* Added iron, mithril, adamantium, and vibranium Forging Tables
* Removed spending experience to improve quality and removed quality-based durability bonuses
* Enhancement quality tiers: 2-3 Fine, 4-5 Excellent, 6-7 Epic, 8-9 Legendary, 10 Mythic
* Added enhancement stones: Polishing Stone for levels 0-4 at 50%, Refining Stone for 5-7 at 40%, and Tempering Stone for 8-10 at 30%
* Enhancements grant attribute bonuses and additional equipment experience
## World Generation
* Changed all Underworld ore generation to Y=140-225
* Changed all Nether ore generation to Y=35-115 and added adamantium
* Raised the Underworld mantle to Y=75
## Items
* Buffed Golden Apples: Regeneration I for 60s, Absorption I for 20s, Resistance I for 10s, and Fire Resistance for 10s
* Buffed Enchanted Golden Apples: Regeneration II for 60s, Absorption II for 20s, Resistance II for 30s, and Fire Resistance for 60s
* Increased Golden Apple enchanting experience to 400
* Set equipment level cap to 15 for every material
* Changed Golden Apple, adamantium equipment, vibranium equipment, and Forging Table recipes
* Reduced experience required to level armor and tools
## Bug Fixes
* Zombies summoned by Giant Zombies now appear at their feet instead of across walls
* Fixed mob equipment progression and mob enchantments
## Enchantments and Equipment Modifiers
* Blocking has an 8-second cooldown and a maximum duration of two seconds
* Blocking with a Blocking-enchanted sword may disarm the attacker at the attack's disarm chance
* Blocking with a Disarming weapon triggers at half the enchantment's disarm chance for players and mobs
* Disarmed player weapons fly 2.5 blocks to the player's right and have a one-second pickup delay
* Emergency cooldown is stored on equipment, so death does not reset it but changing equipment can
* Worn Emergency items share cooldown; each additional item reduces cooldown by 20%, from 100 minutes for one item to 40 minutes for four
* Emergency and Blocking do not count toward enchantment points; spending more experience when enchanting them increases their chance
* Added Conqueror, increasing progressive damage by one per level
* Moved Beheading to an axe enchantment with a 10% decapitation chance per level
* Added modifiers:
  + Indomitable: chestplate modifier granting +25% damage below 50% health, +40% below 35%, +60% below 20%, and +100% below 10%
  + Demon Power: rare weapon modifier granting +25% damage against mobs outside the Overworld
  + Geology: rarest pickaxe modifier granting smelting experience equal to 50% of mined ore experience
  + Immunity: helmet modifier reducing negative-effect duration by 15% per level, up to level IV
## Mobs
* Reduced Zombie Pigman Lord spawn chance
* Increased Giant Zombie collision size and attack reach
* Increased mob vertical reach to hit players at two blocks but not 2.5 blocks
* Reduced special Skeleton spawns: 80% ranged and 20% melee; 25% of ranged Skeletons are forced into melee, and Splitting Skeletons are 5% overall
* Mobs with Disarming weapons can disarm players at half the corresponding player chance
* Pigmen no longer attack animals
* Splitting Skeletons are immobilized, invulnerable, and unable to attack briefly after splitting, giving players two seconds to react
* Mob equipment progression now reaches vibranium
* Random Fire Aspect requires a held weapon and persists when the weapon changes; most Nether mobs receive it
* All Underworld Ancient Skeletons have a 25% chance to switch between melee and ranged combat
* Progressive mob damage is now `1 + days / 24`, capped at 4
* Weapons dropped through mob disarming cannot be picked up and disappear quickly
* Ancient Skeleton Dukes now switch to longswords
* Mob enchanting power scales with days and reaches 10,000 experience at day 320
* Increased Hellhound spawn rates in the order Overworld < Underworld < Nether
* Underworld mob equipment progression starts from iron
* Removed Ghast triple-shot, increased damage by 50%, and increased fireball speed by 15%
* Attribute formulas:
  + Zombie: health `30 + days / 16`, attack `8 + days / 24 + tool`
  + Skeleton: health `15 + days / 16`, attack `10 + days / 24 + tool * 2`; triple-shot chance 15%
  + Creeper: health `20 + days / 8`, point-blank explosion damage 20, fuse `90 - days * 0.6` with a two-second minimum
  + Infernal Creeper: twice the Creeper explosion radius with the same fuse
  + Spider: health `base * 1.5 + days / 24`, attack `base + days / 24` (base health 10, attack 6)
  + Ancient Skeleton Duke: health `40 + days / 12`, attack `15 + days / 20 + tool * 2`
  + Bone Lord: health `35 + days / 16`, attack `12 + days / 20 + tool * 2`
  + Longdead Guardian: health `25 + days / 16`, attack `12 + days / 20 + tool * 2`
  + Longdead: health `15 + days / 16`, attack `10 + days / 20 + tool * 2`
  + Invisible Stalker: health `20 + days / 16`, attack `6 + days / 16`
  + Giant Zombie: health `100 + days / 16`, attack `25 + days / 16`
  + Revenant: health `35 + days / 16`, attack `12 + days / 24 + tool`
  + Wither Skeleton: health `45 + days / 16`, attack `15 + days / 20 + tool * 2`
  + Pigman: health `50 + days / 16`, attack `12 + days / 20 + tool`
  + Enderman: health `100 + days / 24`, attack `30 + days / 20`
  + Ghast: health 15, explosion radius 3
  + Iron Golem: health `200 + days / 16`, attack `25 + days / 16`
* Ender Dragon changes: health 1,250; crystals require a vibranium pickaxe or hammer; 20% chance to inflict Wither for 10s on attackers; attacks inflict Slowness for 15s; below 50% health all players receive Weakness for 30s every 60s; below 30% health it is immune to ranged damage
* Added the **Annihilation Skeleton**: infinite attack damage, bright red eyes and ash particles, extensive damage and movement immunities, spawns in the Overworld after day 64 and without a day limit elsewhere, ignores attacks across height differences of at least three blocks, disappears three minutes after pursuing or being attacked, and drops five vibranium nuggets and two diamonds
* Added the **Wandering Witch**: a red-eyed, red-robed witch that curses players on sight, uses only Poison and Harming potions, summons Vampire Bats every 15 seconds, flees nearby players without attracting other mobs, and drops four adamantium nuggets, one to three Nether Wart, and other items

---

# B0.4.0-final
### This release only updates the version number and contains no substantive changes
_**This was expected to be the final MITE-ITE release**_, as there was little room left for MITE-ITE development. Future work was planned to focus on MITE for 1.16.5 without the same limitations.

---

# B0.4.0-pre15 Changelog
## Bug Fixes
* Fixed a crash when dying in fire
* Fixed the Ender Dragon instantly killing players because of progressive damage

---

# B0.4.0-pre14 Changelog
## Blocks
* Active Beacons grant crafting speed within their effect range: level I 10%, II 35%, III 100%, and IV 175%
* Only the highest-level nearby Beacon applies; the bonus is shown at workbenches and also works in the inventory
* Vibranium blocks can be used as Beacon bases
## Mobs
* Overworld Ghast fireballs receive a ***1.25x*** explosion-strength bonus `{ghast_overworld_boost}[Double]`
## Commands
* Added `/sleep` to inspect player sleeping status
## Enchantments
* Emergency now prevents sudden death, restores health to 20%, and grants Resistance II for three seconds
## Other
* ***Using a rune portal, except a world portal, is announced to other players*** `{log_players_interact_with_portal}[Boolean]`
* Equipment and tool levels are always displayed as Roman numerals
## Bug Fixes
* Fixed an occasional crash

---

# B0.4.0-pre13 Changelog
## Bug Fixes
* Fixed mob equipment not being saved

---

# B0.4.0-pre12 Changelog
## Mobs
* The emerald-shard amount in Villager wool trades is configurable `{villager_wool_to_emerald_shard_shard_count}[Integer]`
* Overworld Ghast fireballs no longer damage terrain, but have 1.5x explosion radius
* Changed elite Nether Pigman chance to ***2.5%*** `{zombie_pigman_boost_chance}[Double]`
## Bug Fixes
* Fixed item duplication using negative stack sizes and dispensers, two hopper-minecart exploits, Villagers, and TNT
* Fixed ghost Zombies appearing client-side when Giant Zombies summoned Zombies
* Fixed a crash at the five-minute Underworld teleport warning
* Fixed picked-up player items sometimes not dropping when the mob was killed
## Other
* Underworld and Nether penalty countdowns now appear above the hotbar

---

# B0.4.0-pre11 Changelog
## Blocks
* Forging Tables are now exclusive to one player at a time
## Items
* The Book of Creation no longer grants experience

---

# B0.4.0-pre10 Changelog
## Bug Fixes
* Fixed a crash when killing an entity with empty hands

---

# B0.4.0-pre9 Changelog
## Bug Fixes
* Fixed a client crash when opening a Forging Table on a server
* Fixed the Supernatural modifier only applying to weapons
## Mobs
* ***Skeletons have a 20% chance to be forced into melee combat*** `{skeleton_force_melee_attack}[Boolean]`
## Other
* Added a warning five minutes before random Underworld teleportation

---

# B0.4.0-pre8 Changelog
## Blocks
* Added the **Forging Table**, including tiered tools, required materials, duration, failure chance, durability costs, penalties, progress cancellation, and success/failure feedback
## Items
* Added **Enhancement Stones** and changed Vibranium Anvil and Furnace recipes
* Added Beheading for weapons (+5% decapitation chance per level, maximum V, weight 1)
* Added Supernatural for tools (+0.1 efficiency per level against lower-tier blocks, maximum V, weight 1)
* Each enhancement level grants 10% durability; attack bonus is `y = a[(1.028)^(2.28x) - 1]`, and armor bonus is `y = x/3 + 0.68 * b[(1.028)^(2.28x) - 1]`
## Entities
* Added the Zombie Pigman Lord, replacing Nether Zombie Lords and displaying lava particles
* Zombie Lords no longer burn and only drop diamonds when killed by a player
* Giant Zombies only drop loot when killed by players, drop 4-6 ancient-metal nuggets before day 128, and summon one Zombie every minute `{giant_zombie_spawn_zombie_cooldown}[Integer]`
* Infernal Creeper explosion radius doubles after day 256
## Mechanics
* Logging out no longer clears player progressive damage
## Bug Fixes
* Fixed a multiplayer crash when opening ancient-metal strongboxes
## Other
* Added the development command `/forging_grade set <value>`

---

# B0.4.0-pre7 Changelog
## Mobs
* Giant Zombies drop 0-3 mithril nuggets before day 128
* Halved Infernal Creeper armor scaling to 0.075 per day plus 2 base armor
## Items
* Vibranium Pickaxes and Bows now correctly require their ancient-metal counterparts
## World Generation
* Lowered Overworld adamantium generation below Y=16
## Bug Fixes
* Fixed emerald duplication through crafting recipes

---

# B0.4.0-pre6 Changelog
## Mobs
* Increased Creeper explosion radius by another 75%, multiplicatively
* Nerfed Giant Zombies: attack reach from 6.5x to 2x, burning in daylight, and vertical hitbox from 8x to 6.5x
## Bug Fixes
* Fixed progressive damage applying to non-entity damage
* Changed rain Weakness duration to three minutes
* Fixed experience orbs containing negative experience

---

# B0.4.0-pre5 Changelog
## World Generation
* Corrected lapis lazuli vein size to 2
## Bug Fixes
* Fixed Skeletons striking with bows and Zombie Lord particle errors
## Mobs
* Increased Creeper explosion radius by 25% and made fuse speed scale with days
* Buffed Giant Zombies with longer reach, no low-health fleeing, higher movement speed, 30 base attack, new AI, and Zombie sounds
* Removed excessive speed from enraged Zombie Pigmen
* Zombies and Zombie Pigmen now block only entity-sourced damage

---

# B0.4.0-pre4 Changelog
## Enchantments
* Increased Blocking difficulty to 20 and Emergency difficulty to 15
## Mobs
* Added the Zombie Lord with full vibranium equipment, Speed I, Strength I, 12 attack, 50 health, 0.3 movement speed, three diamond drops, and a non-disarmable sword
* Endermen launch attacked mobs like Iron Golems
* Optimized experience orbs to reduce lag after high-level deaths
## Bug Fixes
* Fixed Iron Golems being impossible to enhance and mithril failing to smelt into adamantium
## Items
* Reduced diamond experience to ***300*** `{diamond_exp}[Integer]`

---

# B0.4.0-pre3 Changelog
## Bug Fixes
* Fixed a crash after a fish bites

---

# B0.4.0-pre2 Changelog
## Bug Fixes
* Fixed crashes when Skeletons attacked players and Infernal Creepers damaged animals
* Fixed excessive bow-wielding Pigmen in the Nether

---

# B0.4.0 ChangeLog
**_BIG update!_**\
**_Note:All the places in bold italics in this change log can be modified through the configuration file, and all these values are the default values in this change log, for example:192,\
Enclosed in braces are the property names in the configuration file,\
The types are in square brackets_**

### !WARNING!,After this update, the required minimum version of the mod loader will be changed to B0.1.4, please pay attention to the update!

## Mobs
* ***The pigmen in hell have a 20% chance of launching a long-range attack with a bow***`{zombie_pigman_use_bow}[Boolean]`
* Increased the explosion range of creepers(2 times)
* ***The explosion range of Infernal Creeper becomes 3 times the original, but the explosion time increases and decreases as the number of days increases (the initial value is 4.5 s), and the defense increases (increases by 0.15 per day)*** `{infernal_creeper_boost}[Boolean]`
* ***Skeletons will have a 40% chance to use horizontal three-shot bow*** `{skeleton_triple_shot}[Boolean]`
* ***When a vampire bat attacks the player, there will be a 50% chance of poisoning the player*** `{bat_poison_attack}[Boolean]`
## Game Mechanics
* Giant zombies will spawn on the surface of overworld in the blood moon day(Has the same AI as zombies)
* Ghasts will spawn on the surface of overworld in the blood moon ***96*** days later`{ghast_spawn_limit_day}[Integer]`
* Ancient bone lord will spawn on the surface of overworld in the blood moon ***192*** days later`{ancient_bone_lord_spawn_limit_day}[Integer]`
* Increase in the number of mobs:
    + The default for Blood Moon mobs spawning is the 256 / **_128_**,which is ***2*** times`{blood_moon_max_hostile_fraction}[Integer]`
    + The upper limit of spawning monsters will increase with the number of days, and increase ***1*** per day`{mob_max_spawn_count_increase_per_day}[Double]`
* Players who stay in the underground world for too long will get debuff
    + Staying for***72000***tick means ***1 hour*** will get SlownessⅠ`{underworld_debuff_period1}[Integer]`
    + Staying for***108000***tick means ***1 and half an hour*** will get SlownessⅡ`{underworld_debuff_period2}[Integer]`
    + Staying for***132000***tick means ***1 hour and 50 minutes*** will get SlownessⅢ`{underworld_debuff_period3}[Integer]`
    + You must stay in other worlds for **the same amount of time** to **completely** eliminate the impact (there will be a prompt in the chat when completely eliminated)
* ***Players will have progressive damage when they are attacked (when attacked by the same entity multiple times, the damage will increase, each time it increases by 10%)*** `{stepped_mob_damage}[Boolean]`
* In wall damage *to the player* increased to ***10***`{in_wall_damage_for_player}[Double]`
* The probability of the player's enchantment to good enchantments is reduced
* The player stays in the rain for ***3600*** ticks means ***30*** minutes will get SlownessⅠ`{in_rain_debuff_time}[整数]`
## GUI
* Players can see their nutritional value in real time, around their own shortcut bar,X offset***0***,Y offset***0***`{health_bar_x_offset}[Integer] {health_bar_y_offset}[Integer]`
## World Generation
* Reduce the vein size of lapis to 2, but increase the experience of a single lapis to 40
* _**The overworld will generate Adamantium ore. The probability of generation is one-sixth of Mithril,and the maximum of vein is 3**_ `{overworld_adamantite_ore}[Boolean]`
## Items
* Added *Extend* enchantment(Rare,Enchantment difficulty is 10),Can be enchanted to any tool,Up to level 5,increase***0.5*** reach bouns per level `{extend_enchantment_boost_per_lvl}[Double]`
* Added *Crit* enchantment(Rare,Enchantment difficulty is 10),Can be enchanted to any sword,Up to level 5,increase ***2*** * 10% = ***20%*** critical strike chance per level and ***0.4*** crit damage \
  `{crit_enchantment_chance_boost_per_lvl}[Integer],{crit_enchantment_damage_boost_per_lvl[Double]}`
* Added *Defence* enchantment(Rare,Enchantment difficulty is 15),Can be enchanted to vibrantium sowrd,Up to level 1,can use block with it
* Only players whose level are lower than ***80*** can use quartz to obtain exp`{quartz_max_exp_level}[Integer]`
* Added some recipes for *Vibrantium Furnace*(The required heat level is *5*)
    + Four iron blocks can be smelt into a mithril ingot,with the cook time of ***6000***tick means ***5 minutes*** `{iron_to_mithril_cook_time}[Integer]`
    + A mithril block can be smelt into an adamantium ingot ingot,with the cook time of ***9000***tick means ***7.5 minutes*** `{mithril_to_adamantium_cook_time}[Integer]`
* Now crafting every vibrantium armor requires an ancient metal block(The ancient metal block of the shoe is placed between the vibrantium ingot in the bottom row.The rest are placed in the top space between vibrantium ingots,vibrantium ingots' position remains the same)
* Each vibrating tool needs a corresponding ancient metal tool to craft, replacing the obsidian rod in the center of the crafting table (If there is only one obsidian rod, it becomes the corresponding ancient metal tool)
# Blocks
* ***The monster spawning block will speed up spawning when there are players within the spawning radius (approximately 2 times the speed)*** `{mob_spawner_speed_up_with_players}[Boolean]`
## Bug fix
* Fixed the bug of using glowstone to X-ray
---

# B0.3.13 ChangeLog
## Blocks
* Make players cannot use leaves to use x-ray
## Game mechanism
* Make the hitbox of nether fire damage bigger(in Y axis) to prevent jumping to avoid damage
## Mobs
* Changed the village trade of wool to emerald to wool to emerald shard 
## Items
* Make weapons and tools cannot use block
* Decresed the max armor level of armors
## Bug Fix
* Fixed the bug that players can exit game to escape random teleport

---

# B0.3.12-1 ChangeLog
* Fixed the problem that players can use dev commands without permission

---

# B0.3.12 ChangeLog
## Mobs
* Make bone lord and the ancient bone lord's health,damage,xp drops doubled **_(Can be disabled in config< name:`bone-lord-tweak`>)_**
* Make players will get 1 damage per second when they are standing netherrack in the nether **_(can be disabled in config< name:`netherrack-damage`>)_**
* Ghost will shoot three fireballs for a time
## Game mechanism
* Players will be teleported randomly if they stay for a time in underworld **_(can be modified<name:> or disable< name:`underworld-random-teleport-time`> in config)_**

_**Config file: `mite-ite-cfg.json`**_
 
---
# B0.3.11 ChangeLog
## Mobs
* After 128 days, pigmen in the nether have a 10% chance to spawn a special zombie pigman (with smoke effect prompt), which will add power and speed effects to the zombie pigmen in the surrounding 8 blocks (level and duration increase with the number of days)
* After 128 days, the monster has a 1/4 probability to obtain a speed effect, and a 1/5 probability to obtain a power effect (the higher the probability in the later stage, the higher the effect level)
* After 196 days, the skeleton has a 1/2 probability of using the fire bow
---
# B0.3.10 ChangeLog
## Mobs
* Blaze's max health changed to 40 and attack damage changed to 6
* Ghast's max health changed to 15,and it's fireball's explosion radio changed to 2(it was 1),the speed of shooting fireball changed to 1 fireball per 2.5s
* Wither skeleton's basic max health changed to 24(plus 1 per 16 days after 64 day),and it has a ancient metal sword now
* Zombie pigman's basic health changed to 50 and add 1 per 12 days after 64 day,it's basic attack damage added to 8 now(plus 1 per 48 days after 64 day)
## Others
* Players can sit on stairs now
---
# B0.3.9,B0.3.8,B0.3.7 ChangeLog
## Items
* Add vibranium bow(give 75% additional arrow speed)
* Make weapons and armors have levels,and they can get or strengthen a modifier when they level up(All current modifiers is shown below)
* Only vibranium items can have powerful enchantment
## Mobs
* Increased the health amount and attack damage of some mobs(Ancient bone lord,long dead,revenant,zombie,zombie pigman)
* Creeper's explosion radius will increase with the day
* Ancient bone lord,bone lord,revenant will have better armors in late period
* Make ender dragon stronger:
    + If you dont hit its head,you can only deal 20% damage
    + Increased the damage of it hit player(Now is 20)
    + Its max health is 500 now
    + It will always fly in the sky if its heal is lower than 150,and it will heal itself 2HP per 1.5s
    + The distance of ender crystal healing ender dragon is 48 blocks now
    + The dragon only take 5HP of damage when the nearby crystal exploded
    + Ender crystal will heal the dragon 2HP per 0.5s
* Skeleton will have better weapon(iron sword or iron dagger)

### _List of available modifiers_

   Name | Effect | Can use at
    --------------------------------|---------------------------------------------------------|---------------------
      Efficiency Modifier           | Make your tool mines faster                             | Tools
      Aquadynamic Modifier          | Make your tool mines faster when you're in water or rain| Tools
      Durability Modifier           | Make your item drops durability more slower             | Tools Weapons Armors
      Damage Modifier               | Make your weapon deals more damage                      | Weapons
      Slowdown Modifier             | Make your weapon slow down the entity that you hit      | Weapons
      Projectile Protection Modifier| Make you take less damage of projectiles                | Armors
      Explosion Protection Modifier | Make you take less damage of explosion                  | Armors
      Fire Protection Modifier      | Make you take less damage of fire                       | Armors
      Protection Modifier           | Make you take less damage                               | Armors
      Steady Modifier               | Make you not easy to be knockbacked                     | Armors
      Blessed Modifier              | Make you take less damage from undead mobs              | Armors
      
---
# B0.3.6 ChangeLog
## Items
* Add vibranium weapons and tools(they are better that adamantium's things,all the vibranium tools need use obsidian stick to craft) 
* Add vibranium anvil
* Add vibranium armors' and vibranium anvil's achievements
* Manure can be used as bonemeal to heal blighted crops
* Added blaze coal,and its heat value is 5 and burning time is 6800
## Blocks
* Add vibranium furnace(max heat level is 5)
* Add vibranium workbench
* The better the fuel in the furnace be,the faster the items burn
## Mobs
* Decreased the damage and health amount of most mobs
* Add a new variant of earth elemental,Wood Elemental,it only can be attacked by axe,battle axe,lava and fire
* Increase zombie pigman's damage and heal amount
* Creeper,Spider,Zombie,Skeleton's health and damage amount will increase as the day count increase(Skeleton will increase its knock back distance)
* As the number of days increases, the monsters will have better equipments (the highest level of armors is Adamantium, and may have enchantments)\
    _**(their own equipment will not drop, so don't think about getting rich with this)**_
## Recipes
* Make coal block can burn into diamond chunk(4 diamond chunks can make a diamond) in the heat level of 5(use blaze coal)
## Others
* Add vibranium's achievements
* Now if someone destroyed others' strong box the server will tell all the players
## Enchantments
* Increase Enchantment Looting,Fortune,Protection,Thorns's max level
* Now the enchantment sharpness,undead killer and Amputation killer can be compatible with each other in vibranium weapons
* Vibranium weapons and tools can use 15800 xp to enchant at most(use 4 layers of bookshelves and diamond enchantment table),and it can have 4 enchantments at most
* ## Weather
* Blood moon will occurs per 16 days after the blue moon
* Blue moon only occurs at 128 day
#### _And some little changes_

---
# B0.1.0 ChangeLog
## Entities
* Increased the health amount and attack damage of most mobs
* Make bone lord,ancient bone lord,longdead,longdead guardian,revenant and zombie have more powerful enchanted armors\
    (A chance 20% to have enchanted armor at each slot,every 64 days increase 10%,\
    enchantments of the armors also increase with the number of days)
* After 32 days, every other day, the mobs will have a speed effect and a 20% chance to get a strength effect at night
* Mobs won't drop their own armors unless they have picked up other armors or weapons\
    But they won't drop the item that has a low durability(less than quarter) 
* Mobs will have more powerful armors,Except for specific monsters\
    all monsters have a 20% chance to get equipment in each equipment slot\
    After 64 days, normal monster's equipments may be enchanted\
    (Every monster will have armors after 365 days,and the tier of armors will increase with the number of days)
* Skeleton's bow will have 5 level of enchantment arrow knockback
* Player can have 40 max health by level up

[B0.3.12 ChangeLog]: #b0312-changelog

