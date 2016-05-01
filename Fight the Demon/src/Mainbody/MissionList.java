package Mainbody;
import java.awt.*;
import java.lang.Math;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.*;
import java.util.*;
import java.io.*;

import Mainbody.Global;

public class MissionList extends Mission
{
	List<Mission> missionList = new ArrayList<Mission>();
	Iterator<Mission> itr_mission;
	Mission imle = null;
	public Mission getMissionPtr(int id)
	{
		for(itr_mission = missionList.iterator();itr_mission.hasNext();)
		{
			imle = itr_mission.next();
			if(imle.getID() == id)
			{
				return imle;
			}
		}
		return null;
	};
	public int addMission(Mission msn)
	{
		missionList.add(msn);
		return 0;
	};
	public int InitItr()
	{
		itr_mission = missionList.iterator();
		imle = itr_mission.next();
		return 0;
	};
	public int EndItr()
	{
		if(itr_mission.hasNext())
			return 0;
		else
			return 1;
	};
	public int NextItr()
	{
		if(itr_mission.hasNext())
			itr_mission.next();
		else
			return -1;
		return 1;
	};
	public Mission getCurPtr()
	{
		return imle;
	};
	public Mission getCurMsn()
	{
		return imle;
	};
	public int setLock(int id,int lk)
	{
		if(lk != (Global.MSN_LOCK) && lk != (Global.MSN_UNLOCK))
			return -1;
		for(itr_mission = missionList.iterator();itr_mission.hasNext();)
		{
			imle = itr_mission.next();
			if(imle.getID() == id)
			{
				imle.setLock(lk);
				return 0;
			}		
		}
		return -1;
	};
	
	
}
