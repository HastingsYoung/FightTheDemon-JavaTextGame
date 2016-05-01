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

import Mainbody.Role;

public class MissionUsing extends Mission
{
	public List<Integer> useList = new ArrayList<Integer>();
	public List<Integer> useNum = new ArrayList<Integer>();
	public Iterator<Integer> itr_use;
	public Integer iule = null;
	public Iterator<Integer> itr_num;
	public Integer inle = null;
	
	public void missionUsing()
	{
		
	};
	public int isMsnCompleted(Role role,MonsterList mstlist,int id)
	{
		int flag = 1;
		for(itr_use = useList.iterator(),itr_num = useNum.iterator();itr_use.hasNext();)
		{
			iule = itr_use.next();
			inle = itr_num.next();
			flag = 0;
			for(role.initUsingItr();role.endUsingItr() != 0;role.nextUsingItr())
			{
				if((role.getCurUsing()).getID() == iule && (role.getCurUsing()).getNumber() >= inle)
				{
					flag = 1;
					break;
				}
			}
			if(flag == 0)
				break;
		}
		if(flag == 0)
			return 0;
		else 
			{
			state=Global.MSN_DONE;
			return 1;
			}
	};
	public int handOnMsn(Role role,MissionList mntlist)
	{
		itr_use = useList.iterator();
		itr_num = useNum.iterator();
		while(itr_use.hasNext())
		{
			iule = itr_use.next();
			inle = itr_num.next();
			role.loseUsing(iule, inle);
		}
		role.loseMission(id ,mntlist);
		System.out.println("============�������!===========("+name+")============");
		try{
			Thread.currentThread().sleep(Global.SLEEPTIME);
			}
			catch(Exception e)
		{
				e.printStackTrace();
		}
		return 1;
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
			try {
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0)=='1')
						return 1;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	};
	
	int printInfo(UsingList uslist,MonsterList mtlist,NPCList npclist) throws IOException
	{
		String userinput;
		while(true)
		{
			for(int i=0;i<45;++i)
				if(i==22)
					System.out.println("����鿴");
			System.out.println("��������:"+name);
			System.out.println("������Ϣ:"+info);
			System.out.println("��Ҫ:");
			for(itr_use = useList.iterator();itr_num.hasNext();)
			{
				iule = itr_use.next();
				inle = itr_num.next();
				System.out.println("  "+uslist.getUsing(iule).getName()+"*"+inle);
			}
			System.out.println("1:ȷ��2:��������");
			
			BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
			userinput = consolebr.readLine();
			if(userinput.codePointAt(0)=='1')
				return 1;
			if(userinput.codePointAt(0)=='2')
				{
					System.out.println("��ȷ��Ҫ��������ô?");
					System.out.println("1:ȷ��Ҫ����   0:����");
					String temp;
					while(true)
					{
						consolebr = new BufferedReader(new InputStreamReader(System.in));
						temp = consolebr.readLine();
						if(temp.codePointAt(0)=='1')
							return -1;
						if(temp.codePointAt(0)=='0')
							break;
					}
				}
		}
	}
}

