package com.SirBlobman.gemmary.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.SirBlobman.gemmary.GUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiRecipeBook extends GuiScreen
{
	private static final int BookHeight = 192;
	private static final int BookWidth = 192;
	private static int currentPage = 0;
	private static final int  TotalPages = 5;
	
	private static ResourceLocation[] bookTextures = new ResourceLocation[TotalPages];
	private GuiButton done;
	private NextButton next;
	private NextButton previous;
	
	@SideOnly(Side.CLIENT)
	static class NextButton extends GuiButton
	{
		private final boolean isNext;
		
		public NextButton(int id, int x, int y, boolean next)
		{
			super(id, x, y, 23, 13, "");
			isNext = next;
		}
		
		@Override
		public void drawButton(Minecraft mc, int X, int Y)
		{
			if(visible)
			{
				boolean isHovered = (X >= xPosition && Y >= yPosition && X < xPosition + width && Y < yPosition + height);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(bookTextures[1]);
				int x = 0;
				int y = 192;
				
				if(isHovered)
				{
					x += 23;
				}
				if(!isNext)
				{
					y += 13;
				}
				
				drawTexturedModalRect(xPosition, yPosition, x, y, 23, 13);
			}
		}
	}
	
	public GuiRecipeBook()
	{
		bookTextures[0] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book/cover.png");
		bookTextures[1] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book/1.png");
		bookTextures[2] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book/2.png");
		bookTextures[3] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book/3.png");
		bookTextures[4] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book/4.png");
	}
	
	@Override
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton gb)
	{
		if(gb == done)
		{
			mc.displayGuiScreen((GuiScreen) null);
		}
		if(gb == next)
		{
			if(currentPage < TotalPages - 1)
			{
				++currentPage;
			}
		}
		if(gb == previous)
		{
			if(currentPage > 0)
			{
				--currentPage;
			}
		}
	}
	
	@Override
	public void initGui()
	{
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		
		int bookLeft = (width - BookWidth) / 2;
		done = new GuiButton(0, width / 2 + 2, 4 + GuiRecipeBook.BookHeight, 98, 20, GUtil.translate("gui.done"));
		next = new NextButton(1, bookLeft + 120, 156, true);
		previous = new NextButton(2, bookLeft + 38, 156, false);
		
		buttonList.add(done);
		buttonList.add(next);
		buttonList.add(previous);
	}
	
	@Override
	public void drawScreen(int X, int Y, float pTicks)
	{
		GL11.glColor4f(1.0F,  1.0F,  1.0F, 1.0F);
		
		switch(currentPage)
		{
			case 0: mc.getTextureManager().bindTexture(bookTextures[0]);
				break;
			case 1: mc.getTextureManager().bindTexture(bookTextures[1]);
				break;
			case 2: mc.getTextureManager().bindTexture(bookTextures[2]);
				break;
			case 3: mc.getTextureManager().bindTexture(bookTextures[3]);
				break;
			case 4: mc.getTextureManager().bindTexture(bookTextures[4]);
				break;
			case 5: mc.getTextureManager().bindTexture(bookTextures[5]);
				break;
		}
		
		int bookLeft = (width - BookWidth) / 2;
		drawTexturedModalRect(bookLeft, 2, 0, 0, BookWidth, BookHeight);
		String pageIndicator = I18n.format("book.pageIndicator", new Object[] {Integer.valueOf(currentPage + 1), TotalPages});
		int stringWidth = fontRendererObj.getStringWidth(pageIndicator);
		fontRendererObj.drawString(pageIndicator, bookLeft - stringWidth + BookWidth - 44, 18, 0);
		
		super.drawScreen(X, Y, pTicks);
	}
}