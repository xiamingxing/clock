package com.hackthon.bean;


public class RemindStrategyBean 
{
	/*
	 * ��ʾ���� 1��������ʾ 2��ָ��������ʾ 3��������
	 */
	public static final int AFTER_SOME_TIME = 1;
	public static final int AFTER_SOME_SPORT = 2;
	public static final int AFTER_SOME_PHOTO = 3;
	
	private int remindstrategy  = AFTER_SOME_TIME;

	
	public RemindStrategyBean(int rstragy)
	{
		remindstrategy = rstragy;
	}
	public void setsRemindTrategy(int rstragy)
	{
		remindstrategy = rstragy;
	}
	public int getRemindTrategy()
	{
		return remindstrategy;
	}

}
