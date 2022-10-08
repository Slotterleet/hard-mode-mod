package hm;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.type.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.power.*;

public class HardModeMod extends Mod {
    public HardModeMod() {
        //modify the stats of turrets and power gens
        Events.on(ClientLoadEvent.class, e ->
            Vars.content.blocks().each(b -> {
                if (b instanceof Turret) {
                    ItemStack[] req = b.requirements;
                    for (ItemStack i : req) {
                        i.amount *= 2;
                    }

                    return;
                }
                if (b instanceof PowerGenerator) {
                    ((PowerGenerator) b).powerProduction *= 2f/3f;
                }
            })
        );

        //increase units' health on wave call
        Events.on(UnitSpawnEvent.class, e -> {
            Unit u = e.unit;
            u.health *= 1.6; u.maxHealth *= 1.6;
        });
        //increase fabricated enemy drones' health
        Events.on(UnitCreateEvent.class, e -> {
            Unit u = e.unit;
            //is it campaign?
            if ( (Vars.state.isCampaign() ||
            //or maybe a custom map? if so, then make sure it isn't PvP
            (!(Vars.state.isCampaign()) && !(Vars.state.rules.pvp)) ) &&
            //and finally, if a unit is indeed an enemy
            u.team == Vars.state.rules.waveTeam) {
                u.health *= 1.6; u.maxHealth *= 1.6;
            }
        });

        Log.info("Hard Mode Activated. Good luck!");
    }

    @Override
    public void init() {
        Mods.LoadedMod xfmod = Vars.mods.list().find(mod -> mod.meta.author.equals("XenoTale"));

        if (xfmod != null) {
            Vars.ui.showOkText("@xenoforce.title", Core.bundle.format("xenoforce.text", xfmod.meta.displayName), () -> Core.app.exit());
        }
    }

    @Override
    public void loadContent(){

    }

}
