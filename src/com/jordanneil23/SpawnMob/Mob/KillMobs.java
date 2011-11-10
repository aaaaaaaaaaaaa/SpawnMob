package com.jordanneil23.SpawnMob.Mob;

import java.util.Iterator;
import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.*;

public class KillMobs {
	public static void Kill(World world, String type) {
		List<?> mobs = world.getLivingEntities();
	    for(Iterator<?> iterator = mobs.iterator(); iterator.hasNext();)
	    {
	    	LivingEntity m = (LivingEntity)iterator.next();
			if (m instanceof LivingEntity) {
				if (m instanceof Creature) {
					if (m instanceof Animals) {
						if (m instanceof Chicken && (type.equalsIgnoreCase("Chicken") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.setHealth(0);
						} else if (m instanceof Cow && (type.equalsIgnoreCase("Cow") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.setHealth(0);
						} else if (m instanceof Pig && (type.equalsIgnoreCase("Pig") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.setHealth(0);
						} else if (m instanceof Sheep && (type.equalsIgnoreCase("Sheep") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.setHealth(0);
						} else if (m instanceof MushroomCow && (type.equalsIgnoreCase("MushroomCow") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.setHealth(0);
						} else if (m instanceof Villager && (type.equalsIgnoreCase("Villager") || type.equalsIgnoreCase("NPC") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.setHealth(0);
						} else if (m instanceof Wolf && (type.equalsIgnoreCase("Wolf") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							if (((Wolf) m).isTamed() != true) {
					            m.setHealth(0);
					        	}else{}
						}
						 else if (m instanceof Wolf && type.equalsIgnoreCase("TWolf")) {
							 if (((Wolf) m).isTamed() == true) {
						            m.setHealth(0);
						        	}else{}
							}
					}
					else if (m instanceof Monster) {
						if (m instanceof Zombie && (type.equalsIgnoreCase("Zombie") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof Creeper && (type.equalsIgnoreCase("Creeper") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof PigZombie && (type.equalsIgnoreCase("PigZombie") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof Giant && (type.equalsIgnoreCase("Giant") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof Skeleton && (type.equalsIgnoreCase("Skeleton") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof Spider && (type.equalsIgnoreCase("Spider") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof Slime && (type.equalsIgnoreCase("Slime") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof EnderDragon && (type.equalsIgnoreCase("EnderDragon") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof Silverfish && (type.equalsIgnoreCase("Silverfish") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof CaveSpider && (type.equalsIgnoreCase("CaveSpider") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						} else if (m instanceof Enderman && (type.equalsIgnoreCase("EnderMan") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.setHealth(0);
						}
					}
					else if (m instanceof WaterMob) {
						if (m instanceof Squid && (type.equalsIgnoreCase("Squid") || type.equalsIgnoreCase("All"))) {
							m.setHealth(0);
						}
					}
				}
				else if (m instanceof Flying) {
					if (m instanceof Ghast && (type.equalsIgnoreCase("Chast") || type.equalsIgnoreCase("All"))) {
						m.setHealth(0);
					}else if (m instanceof Blaze && (type.equalsIgnoreCase("Blaze") || type.equalsIgnoreCase("All"))) {
						m.setHealth(0);
					}
				}
			}
			return;
	    }
		return;
	}
}
