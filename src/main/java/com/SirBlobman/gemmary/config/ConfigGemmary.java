package com.SirBlobman.gemmary.config;

import com.SirBlobman.gemmary.constant.ModInfo;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.Config.Type;

@LangKey("gemmary.config.main")
@Config(modid=ModInfo.MODID, type=Type.INSTANCE, category="gemmary")
public class ConfigGemmary {
	@Name("Diamond TNT Explosion Size")
	@LangKey("gemmary.config.option.diamond_tnt.explosion_size")
	@RangeDouble(min=0.0D, max=5000.0D)
	@Comment({
		"How big do you want the explosion size to be?",
		" ",
		"0.0: No explosion",
		"4.0: Normal TNT",
		"5000.0: Nuclear Explosion",
		" "
	})
	public static double DIAMOND_TNT_EXPLOSION_SIZE = 8.0D;
	
	
	@Name("Cloth Durability")
	@LangKey("gemmary.config.option.cloth.durability")
	@RangeInt(min=1, max=32767)
	@Comment("How much durability should the cloth item have?")
	@RequiresMcRestart
	public static int CLOTH_DURABILITY = 10;
	
	@Name("HD Textures")
	@LangKey("gemmary.config.option.hd_textures")
	@Comment("Should we use 1024x textures instead of the default 16x?")
	@RequiresMcRestart
	public static boolean HD_TEXTURES = false;
}