package com.SirBlobman.gemmary.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiRecipeBook extends GuiScreen
{
	private final int BOOK_H = 192;
	private final int BOOK_W = 192;
	private final int TOTAL_PAGES = 5;
	private int page = 0;
	
	private ResourceLocation[] textures = new ResourceLocation[TOTAL_PAGES];
	private GuiButton done;
	private NextButton next;
	private NextButton prev;
	
	public GuiRecipeBook()
	{
		String path = "gemmary:textures/gui/recipe_book/";
		textures[0] = new ResourceLocation(path + "cover.png");
		textures[1] = new ResourceLocation(path + "1.png");
		textures[2] = new ResourceLocation(path + "2.png");
		textures[3] = new ResourceLocation(path + "3.png");
		textures[4] = new ResourceLocation(path + "4.png");
	}
	
	@Override
	public void onGuiClosed() {Keyboard.enableRepeatEvents(false);}
	@Override
	public boolean doesGuiPauseGame() {return false;}
	
	@Override
	public void actionPerformed(GuiButton gb)
	{
		if(gb == done) mc.displayGuiScreen(null);
		if(gb == next) {if(page < TOTAL_PAGES - 1) ++page;}
		if(gb == prev) {if(page < TOTAL_PAGES - 1) --page;}
	}
	
	@Override
	public void initGui()
	{
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		int bookLeft = (width - BOOK_W) / 2;
		done = new GuiButton(0, width / 2 + 2, 4 + BOOK_H, 98, 20, I18n.format("gui.recipe_book.done"));
		next = new NextButton(1, bookLeft + 120, 156, true);
		prev = new NextButton(2, bookLeft + 38, 156, false);
		buttonList.add(done);
		buttonList.add(next);
		buttonList.add(prev);
	}
	
	@Override
	public void drawScreen(int x, int y, float ticks)
	{
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft m = Minecraft.getMinecraft();
		TextureManager tm = m.getTextureManager();
		tm.bindTexture(textures[page]);
		
		int bookLeft = (width - BOOK_W) / 2;
		drawTexturedModalRect(bookLeft, 2, 0, 0, BOOK_W, BOOK_H);
		String pagei = I18n.format("gui.recipe_book.page", (page + 1), TOTAL_PAGES);
		FontRenderer fr = fontRendererObj;
		int sw = fr.getStringWidth(pagei);
		fr.drawString(pagei, bookLeft - sw + BOOK_W - 44, 18, 0);
		
		super.drawScreen(x, y, ticks);
	}
	
	class NextButton extends GuiButton
	{
		private final boolean next;
		
		public NextButton(int id, int x, int y, boolean next)
		{
			super(id, x, y, 23, 13, "");
			this.next = next;
		}
		
		@Override
		public void drawButton(Minecraft m, int x, int y)
		{
			if(visible)
			{
				boolean b1 = x >= xPosition;
				boolean b2 = y >= yPosition;
				boolean b3 = x < xPosition + width;
				boolean b4 = y < yPosition + height;
				boolean hover = b1 && b2 && b3 && b4;
				GL11.glColor4f(1, 1, 1, 1);
				TextureManager tm = m.getTextureManager();
				tm.bindTexture(textures[1]);
				int x2 = 0;
				int y2 = 192;
				if(hover) x2 += 23;
				if(next) y2 += 13;
				
				drawTexturedModalRect(xPosition, yPosition, x2, y2, 23, 13);
			}
		}
	}
}