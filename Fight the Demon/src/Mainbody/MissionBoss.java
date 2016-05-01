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

public class MissionBoss extends Mission
{
	List<Integer> bossIDList = new ArrayList<Integer>();
	Iterator<Integer> itr_boss;
	Integer ible = null;
	
	public int pushBossID(int id)
	{
		this.bossIDList.add(id);
		return 0;
	};
	public int acceptMsn(Role role,MonsterList mntlist,UsingList uslist,WeaponList wpnlist,MapList mplist) throws IOException
	{
		acceptMsn(role,mntlist,uslist,wpnlist,mplist);
		String userinput;
		role.getMission(this);
		state = Global.MSN_BEING;
		System.out.println("�������������:"+name);
		System.out.println("1:ȷ��");
		while(true)
		{
			BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
			userinput = consolebr.readLine();
			if(userinput.codePointAt(0)=='1')
				break;
		}
		for(itr_boss=bossIDList.iterator();itr_boss.hasNext();)
		{
			ible = itr_boss.next();
			int id = (mntlist.getMonster(ible)).getID();
			mntlist.setLock(id, Global.MON_UNLOCK);
		}
		
		Map ptr_map = null;
		ptr_map = mplist.getMapPtr(unlockmap);
		if(ptr_map == null)
			return 0;
		ptr_map.setLock(Global.MAP_UNLOCK);
		return 0;
	};
	public int printInfo(UsingList uslist,MonsterList mtlist,NPCList npclist) throws IOException
	{
		String userinput;
		while(true)
		{
			for(int i=0;i<45;++i)
				if(i==22)
					System.out.println("����鿴");
				else
					System.out.println('-');
			System.out.println("��������:"+name);
			System.out.println("������Ϣ:"+info);
			System.out.println("��Ҫ:");
			for(itr_boss = bossIDList.iterator();itr_boss.hasNext();)
			{
				ible = itr_boss.next();
				System.out.println("  "+(mtlist.getMonster(ible)).getName());
			}
			System.out.println("1:ȷ��  2:��������");
			BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
			userinput = consolebr.readLine();
			if(userinput.codePointAt(0)=='1')
				return 1;
			if(userinput.codePointAt(0)=='2')
				{
					String temp;
					System.out.println("��ȷ������������?");
					System.out.println("1:ȷ��  0:ȡ��");
					while(true)
					{
						consolebr = new BufferedReader(new InputStreamReader(System.in));
						temp = consolebr.readLine();
						if(temp.codePointAt(0)=='1')
						{
							for(itr_boss = bossIDList.iterator();itr_boss.hasNext();)
							{
								ible = itr_boss.next();
								mtlist.setLock(ible, Global.MON_LOCK);
							}
							return -1;
						}
						if(temp.codePointAt(0)=='0')
							break;
					}
				}
		}
	};
	public int handOnMsn(Role role,MissionList mntlist)
	{
		System.out.println("===========�������!��"+name+")============");
		try{
			Thread.currentThread().sleep(Global.SLEEPTIME);
			}
			catch(Exception e)
		{
				e.printStackTrace();
		}
		role.loseMission(id, mntlist);
		return 1;
	};
	public int isMsnCompleted(Role role, MonsterList mntlist, int id)
	{
		int flag=1;
		if(state==Global.MSN_BEING)
		{
			System.out.println(name);
			for(itr_boss=bossIDList.iterator();itr_boss.hasNext();)
			{
				ible = itr_boss.next();
				if((mntlist.getMonster(ible)).getDie()==1)
				{
					;
				}
				else
				{
					flag = 0;
					break;
				}
			}
			if(flag == 1)
			{
				state=Global.MSN_DONE;
				return 1;
			}
			else
				return 0;
		}
		return 0;
	};
}
