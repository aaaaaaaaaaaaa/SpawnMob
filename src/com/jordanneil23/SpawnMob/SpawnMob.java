package com.jordanneil23.SpawnMob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

/**
 * SpawnMob - Main
 * @version 1.9.8
 * @author jordanneil23
 */
public class SpawnMob extends JavaPlugin {
	private final SpawnerListener blockListener = new SpawnerListener(this);
	private final PListener PlayerListener = new PListener(this);
	public java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	public static PermissionHandler Permissions;
    private final CommandHandler handler = new CommandHandler();
    private static final String CONFIG_FILE_NAME = "plugins/SpawnMob/SpawnMob.properties";
    static SpawnMob sm1;
    public static boolean permissions = true;
    public boolean mobspawnerdrops = true;
    public String spawnlimit;
    
    public void onEnable() {
    	sm1 = this;
    	File confFile = new File(CONFIG_FILE_NAME);
		if(!confFile.exists()) {
			writeConfigFile();
		}
    	loadProps();
    	PluginManager pm = getServer().getPluginManager();
    	if (permissions){
    	setupPermissions();
    	}
    	if (mobspawnerdrops){
            pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
		}
    	pm.registerEvent(Event.Type.PLAYER_INTERACT, PlayerListener, Priority.Normal, this);
        if (permissions){
    		PluginDescriptionFile pdfFile = this.getDescription();
    		log.info("[SpawnMob] Version " + pdfFile.getVersion() + " enabled.");
    		Kit.setKitPerms();
        }else{
        	log.info("[SpawnMob] Using ops.txt!");
        }
    		handler.CommandListener(this);
    	}
    
    public void onDisable() {
    	File confFile = new File(CONFIG_FILE_NAME);
		if(!confFile.exists()) {
			writeConfigFile();
		}
    	PluginDescriptionFile pdfFile = this.getDescription();
    	log.info( "[SpawnMob]" + " Version " + pdfFile.getVersion() + " disabled.");
    }
    
    static public boolean getPerms(String s, Player p){
    	if (p.hasPermission(s) == true){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private void setupPermissions() {
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        if (SpawnMob.Permissions == null) {
            if (test != null) {
                SpawnMob.Permissions = ((Permissions)test).getHandler();
                log.info("[SpawnMob] Permission system found, plugin enabled");
            } else {
                log.info("[SpawnMob] Permission system not detected! Please go into the SpawnMob.properties and set use-permissions to false.");
                log.info("[SpawnMob] Please go into the SpawnMob.properties and set use-permissions to false.");
                permissions = false;
            }
        }
    }
    
    void setPerms(String s){
    	PluginManager pm = getServer().getPluginManager();
    	pm.addPermission(new Permission(s));
    }
    
    void remPerms(String s){
    	PluginManager pm = getServer().getPluginManager();
    	pm.removePermission(s);
    }
    
    static boolean playerhas(Player p, String perm, boolean perms){
    	if (perms == false)
    	{
    		if (!p.isOp())
    			return false;
    		else 
    			return true;
    	}
    	if (perms == true)
    	{
    		if (!(p.hasPermission(perm) || Permissions.has(p, perm)))
    			return false;
    		else
    			return true;
    	} 
		return true;
    }
    
    public void loadProps() {
	try {
		Properties props = new Properties();
		props.load(new FileReader("plugins/SpawnMob/SpawnMob.properties"));
		permissions = props.getProperty("use-permissions").contains("true") ? true : false;
		mobspawnerdrops = props.getProperty("mobspawners-have-drops").contains("true") ? true : false;
		spawnlimit = props.getProperty("spawn-limit");
	} catch (IOException ex) {
		System.out.println("[SpawnMob] Unable to load the properites!");
	}
}
    public void writeConfigFile() {
		log.info(String.format("[SpawnMob] Saving config file, please restart after this is done"));
		File file = new File("plugins/SpawnMob");
		if(!file.exists()) {
		new File("plugins/SpawnMob").mkdir();
		}
		Properties props = new Properties();
		props.setProperty("use-permissions", "true");
		props.setProperty("mobspawners-have-drops", "true");
		props.setProperty("spawn-limit", "300");
		try {
			props.store(new FileOutputStream(CONFIG_FILE_NAME), null);
		} catch (FileNotFoundException e) {
			log.info(String.format("[SpawnMob] FileNotFoundException while saving config file"));
		} catch (IOException e) {
			log.info(String.format("[SpawnMob] IOException while saving config file"));
		}		
	}
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    		return handler.perform(sender, command, args);
    }
    
public boolean isMonster(LivingEntity e){
    return (e instanceof Creeper) || (e instanceof Monster) || (e instanceof Skeleton) || (e instanceof Spider) || (e instanceof Zombie) || (e instanceof PigZombie) || (e instanceof Ghast) || (e instanceof Giant) || (e instanceof Slime);
}

public boolean isAnimal(LivingEntity e){
  return (e instanceof Chicken) || (e instanceof Cow) || (e instanceof Sheep) || (e instanceof Squid) || (e instanceof Pig);
}
//start of individual monsters
public boolean isZombie (LivingEntity e){
	return (e instanceof Zombie);
}
public boolean Monster (LivingEntity e){
	return (e instanceof Monster);
}
public boolean isCreeper (LivingEntity e){
	return (e instanceof Creeper);
}
public boolean isSkeleton (LivingEntity e){
	return (e instanceof Skeleton);
}
public boolean isSpider (LivingEntity e){
	return (e instanceof Spider);
}
public boolean isPigZombie (LivingEntity e){
	return (e instanceof PigZombie);
}
public boolean isGiant (LivingEntity e){
	return (e instanceof Giant);
}
public boolean isSlime (LivingEntity e){
	return (e instanceof Slime);
}
public boolean isGhast (LivingEntity e){
	return (e instanceof Ghast);
}
public boolean isEnderman (LivingEntity e){
	return (e instanceof Enderman);
}
public boolean isCavespider (LivingEntity e){
	return (e instanceof CaveSpider);
}
public boolean isSilverfish (LivingEntity e){
	return (e instanceof Silverfish);
}
//end
//
//Start of individual animals
public boolean isCow (LivingEntity e){
	return (e instanceof Cow);
}
public boolean isPig (LivingEntity e){
	return (e instanceof Pig);
}
public boolean isSheep (LivingEntity e){
	return (e instanceof Sheep);
}
public boolean isChicken (LivingEntity e){
	return (e instanceof Chicken);
}
public boolean isWolf (LivingEntity e){
	return (e instanceof Wolf);
}
public boolean isSquid(LivingEntity e){
	return (e instanceof Squid);
}
//end

public void Kill(World world, String type){
    List<?> mobs = world.getLivingEntities();
    for(Iterator<?> iterator = mobs.iterator(); iterator.hasNext();)
    {
        LivingEntity m = (LivingEntity)iterator.next();
        if(isAnimal(m) && (type.equalsIgnoreCase("animals") || type.equals("all")))
        {
            m.setHealth(0);
        } else
        if(isMonster(m) && (type.equalsIgnoreCase("monsters") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isZombie(m) && (type.equalsIgnoreCase("zombie") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isCreeper(m) && (type.equalsIgnoreCase("creeper") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isSkeleton(m) && (type.equalsIgnoreCase("skeleton") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isSpider(m) && (type.equalsIgnoreCase("spider") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isGhast(m) && (type.equalsIgnoreCase("ghast") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isSlime(m) && (type.equalsIgnoreCase("slime") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isGiant(m) && (type.equalsIgnoreCase("giant") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isPigZombie(m) && (type.equalsIgnoreCase("pigzombie") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(Monster(m) && (type.equalsIgnoreCase("monster") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isEnderman(m) && (type.equalsIgnoreCase("enderman") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isCavespider(m) && (type.equalsIgnoreCase("cavespider") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isSilverfish(m) && (type.equalsIgnoreCase("silverfish") || type.equals("all")))
        {
            m.setHealth(0);
        }
        //start of animals
        if(isSheep(m) && (type.equalsIgnoreCase("sheep") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isChicken(m) && (type.equalsIgnoreCase("chicken") || type.equals("all")))
        {
            m.setHealth(0);
        }if(isSquid(m) && (type.equalsIgnoreCase("squid") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isCow(m) && (type.equalsIgnoreCase("cow") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isPig(m) && (type.equalsIgnoreCase("pig") || type.equals("all")))
        {
            m.setHealth(0);
        }
        if(isWolf(m) && (type.equalsIgnoreCase("wolf") || type.equals("all")))
        {
        	if (((Wolf) m).isTamed() != true) {
            m.setHealth(0);
        	}else{}
        }
        if(isWolf(m) && (type.equalsIgnoreCase("twolf")))
        {
        	if (((Wolf) m).isTamed() == true) {
                m.setHealth(0);
        	}else{}
        }
    }
}
}

