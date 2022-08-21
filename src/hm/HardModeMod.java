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
        //increase units' health on wave call
        Events.on(UnitSpawnEvent.class, e -> {
            Unit u = e.unit;
            u.health *= 1.6; u.maxHealth *= 1.6;
        });

        Log.info("Hard Mode Activated. Good luck!");
    }

    @Override
    public void loadContent(){
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
        });
    }

}
