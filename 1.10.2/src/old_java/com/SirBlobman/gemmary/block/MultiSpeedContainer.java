package com.SirBlobman.gemmary.block;

import net.minecraft.block.BlockContainer;

public abstract class MultiSpeedContainer extends RotateableContainer
{
	/**
	 * Speed for {@link BlockContainer}s that extend {@link MultiSpeedContainer}
	 * <br/>{@code ULTRA} is 100 ticks
	 * <br/>{@code NORMAL} is 3000 ticks
	 */
	public enum Speed 
	{
		ULTRA(100), 
		NORMAL(3000);
		
		private int speed;
		Speed(int speed) {this.speed = speed;}
		
		public int getSpeed() {return speed;}
	}
	
	private Speed speed;
	public MultiSpeedContainer(String name, Speed speed)
	{
		super(name);
		this.speed = speed;
	}
	
	public int getSpeed() {return speed.getSpeed();}
}