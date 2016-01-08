package com.SirBlobman.gemmary.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiRecipeBook extends GuiScreen{

	private static final int BOOK_HEIGHT = 192;
	private static final int BOOK_WIDTH = 192;
	private static int currentPage = 0;
	private static final int TOTAL_PAGES = 5;
	private static ResourceLocation[] bookTextures = new ResourceLocation[TOTAL_PAGES];
	private GuiButton done;
	private NextButton next;
	private NextButton previous;

	public GuiRecipeBook() {
		bookTextures[0] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book_cover.png");
		bookTextures[1] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book_1.png");
		bookTextures[2] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book_2.png");
		bookTextures[3] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book_3.png");
		bookTextures[4] = new ResourceLocation("gemmary" + ":" + "textures/gui/recipe_book_4.png");
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if (button == done) {
			mc.displayGuiScreen((GuiScreen) null);
		}
		if (button == next) {
			if (currentPage < TOTAL_PAGES - 1) {
				++currentPage;
			}
		}
		if (button == previous) {
			if (currentPage > 0) {
				--currentPage;
			}
		}
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		int bookLeft = (width-BOOK_WIDTH)/ 2;
		done = new GuiButton(0, this.width / 2 + 2, 4 + GuiRecipeBook.BOOK_HEIGHT, 98, 20,
				I18n.format("gui.done.gemmary", new Object[0]));
		next = new NextButton(1, bookLeft + 120, 156, true);
		previous = new NextButton(2, bookLeft + 38, 156, false);
		buttonList.add(done);
		buttonList.add(next);
		buttonList.add(previous);
	}
	@Override 
	 public void drawScreen(int mouseX, int mouseY, float partialTicks){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		switch (currentPage){
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
		int bookLeft = (width-BOOK_WIDTH)/ 2;
		this.drawTexturedModalRect(bookLeft, 2, 0, 0, BOOK_WIDTH, BOOK_HEIGHT);
		String pageIndicator = I18n.format("book.pageIndicator", new Object[] {Integer.valueOf(currentPage + 1), TOTAL_PAGES});
		int stringWidth = fontRendererObj.getStringWidth(pageIndicator);
		fontRendererObj.drawString(pageIndicator, bookLeft - stringWidth + BOOK_WIDTH - 44, 18, 0);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@SideOnly(Side.CLIENT)
	static class NextButton extends GuiButton {
		private final boolean isNext;

		public NextButton(int buttonId, int x, int y, boolean isNext) {
			super(buttonId, x, y, 23, 13, "");
			this.isNext = isNext;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			if (this.visible) {
				boolean isHovered = (mouseX >= this.xPosition && mouseY >= this.yPosition
						&& mouseX < this.xPosition + width && mouseY < this.yPosition + height);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(bookTextures[1]);
				int x = 0;
				int y = 192;

				if (isHovered) {
					x += 23;
				}
				if (!isNext) {
					y += 13;
				}
				this.drawTexturedModalRect(this.xPosition, this.yPosition, x, y, 23, 13);
			}
		}
	}
}
