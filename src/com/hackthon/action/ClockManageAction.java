package com.hackthon.action;

import java.util.ArrayList;

public class ClockManageAction 
{
	private ArrayList<Clock> ClockList  = new ArrayList<Clock>();
	
	void Add(Clock clock)
	{
		ClockList.add(clock);
	}
	void delete(int pos)
	{
		ClockList.remove(pos);
	}
	Clock getAt(int idx)
	{
		return ClockList.get(idx);
	}
	int size()
	{
		return ClockList.size();
	}
}

