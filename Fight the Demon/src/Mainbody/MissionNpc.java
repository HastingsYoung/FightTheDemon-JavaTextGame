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

public class MissionNpc extends Mission
{
	int npcID_1;
	int npcID_2;
	int mapID;
	
	public int setNpcID1(int id)
	{
		npcID_1 = id;
		return id;
	};
	public int setNpcID2(int id)
	{
		npcID_2 = id;
		return id;
	};
	public int setMapID(int id)
	{
		mapID = id;
		return id;
	};
	public int getNpcID1()
	{
		return npcID_1;
	};
	public int getNpcID2()
	{
		return npcID_2;
	};
	public int getMapID()
	{
		return mapID;
	};
	public int printInfo(UsingList uslist,MonsterList mtlist, NPCList npclist) throws IOException
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
			System.out.println("������Ҫ:"+"ȥѰ��"+(npclist.getNPC(npcID_2)).getName()+"�Ի�");
			System.out.println("1:ȷ��  2:��������");
			while(true)
			{
				BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
				userinput = consolebr.readLine();
				if(userinput.codePointAt(0)=='1')
					return 1;
				if(userinput.codePointAt(0)=='2')
					break;
			}
			if(userinput.codePointAt(0)=='2')
			{
				System.out.println("��ȷ������������?");
				System.out.println("1:ȷ��  0:ȡ��");
				String temp;
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					temp = consolebr.readLine();
					if(temp.codePointAt(0)=='1')
						return -1;
					if(temp.codePointAt(0)=='0')
						break;
				}
			}
		}
	};
	public int isMsnCompleted( Role role, MonsterList mntlist, int id)
	{
		if( id == npcID_2)
			state = Global.MSN_DONE;
		return 0;
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
	public int acceptMsn(Role role,MonsterList mntlist,UsingList uslist,WeaponList wpnlist,MapList mplist) throws Exception
	{
		String userinput;
		role.getMission(this);
		state=Global.MSN_BEING;
		System.out.println("�������������:"+name);
		System.out.println("1:ȷ��");
		while(true)
		{
			BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
			userinput = consolebr.readLine();
			if(userinput.codePointAt(0)=='1')
				return 1;
		}
	};
	public int canSeeMsn( Role role, NPC npc)
	{
		if(state==Global.MSN_DONE)
			return 0;
		if((lock==Global.MSN_UNLOCK)&&(role.getLevel()>=level))
		{
			if((npc.getID()==npcID_1)&&(state==Global.MSN_BEFORE))
				return 1;
			if((npc.getID()==npcID_2)&&(state==Global.MSN_BEING))
				return 1;
			return 0;
		}
		return 0;
	};
}
