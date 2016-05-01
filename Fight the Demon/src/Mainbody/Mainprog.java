/**
 * 
 */
/**
 * @author admin
 *
 */
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
import Mainbody.MissionBoss;
import Mainbody.MissionList;
import Mainbody.MissionNpc;
import Mainbody.MissionUsing;
import Mainbody.StMonster;


//use static int to replace Macro in C++,final to set it a unchangeable constant
/*void ext_print_line()
{
	
};*/

/* this class stand for the using thing of role in game,
*    the using add hp,mp 
* and also include special-mission-using thing ,every 
* thing that can be used in game has its only ID,so we
* can get it through the ID
*/

class Using{
		public static int PERCENT = 1;
		public static int NOPERCENT = 0;
		public static long SLEEPTIME = 900;
		/*
		 * the ID in this game is only!
		 * you can find it through ID of it!
		 */
		private int ID;
		/* every thing has a name!*/
		private String name;
		/* and the money of it */
		private int money;
		/* 
		 * this var mean the number of it in role's beg
		 * when role pick up a same id of using ,then the using
		 * in role's beg must add 1,when its number is 0, role's 
		 * beg must delete it!
		 *     the number in the UsingList is always 1.
		 */
		private int number;
		/*
		 * the following two vars mean the point role add;
		 */
		private int addHitPoint,addManaPoint;
		/*
		 * percent-or-not :
		 *   1: then add -hit-point and add-mana-point is the
		 *		percent,for example add-hit-point 40 means
		 *      role recover 40% of his max-role-hit-point.
		 *   0: mean role recover add-hit-point of hp
		 */
		private int percentOrNot;
		/* 
		 * this part functions mean to get the information of using
		 * other class member function can use it !
		 */
		public int getID()
		{
			return ID;
		};
		public int getNumber()
		{
			return number;
		};
		public int getPercentOrNot()
		{
			return percentOrNot;
		};
		public String getName()
		{
			return name;
		};
		public int getAddHitPoint()
		{
			return addHitPoint;
		}
		public int getAddManaPoint()
		{
			return addManaPoint;
		};
		public int getMoney()
		{
			return money;
		};
		/*
		 * and this part means to set the information of using(item)
		 * the functions will be used  at the begin of game init();
		 */
		public int setName(String n)
		{
			//another mode:setName(String name){this.name = name;return 1;}
			name = n;
			return 1;
		};
		public int setID(int id)
		{
			ID = id;
			return ID;
		};
		public int setNumber(int num)
		{
			number = num;
			return number;
		};
		public int setPercentOrNot(int per)
		{
			if(per!=1)
				per = 0;
			percentOrNot = per;
			return percentOrNot;
		};
		public int setAddHitPoint(int add)
		{
			addHitPoint = add;
			return addHitPoint;
		};
		public int setMoney(int my)
		{
			money = my;
			return my;
		};
		public int setAddManaPoint(int add)
		{
			addManaPoint = add;
			return addManaPoint;
		};
		/*
		 * this function will be used when role pick up a using 
		 * or use a using 
		 * And those two function will be overloaded because role may
		 * throw part of them or get part of them.
		 * every function will sleep 2s for user check out result,and then
		 * go back the gui before.
		 */
		public int addNumber(int tag)
		{
			++number;
			System.out.println("你得到了: "+name+" *1");
			if(tag==1)
				try{
					Thread.currentThread().sleep(SLEEPTIME);
					}
					catch(Exception e)
			{
						e.printStackTrace();
			}
			return number;
		};
		public int addNumber(int num,int tag)
		{
			number = number +num;
			System.out.println("你得到了: "+name+" *"+num);
			if(tag==1)
				try{
					Thread.currentThread().sleep(SLEEPTIME);
					}
					catch(Exception e)
			{
						e.printStackTrace();
			}
			return number;
		};
		//tbc
		//consider to change the pointers into individual class members to solve it
		public int decreaseNumber(int tag)
		{
			if(number>0)
				--number;
			System.out.println("你失去了: "+name+" *1");
			if(tag==1)
				try{
					Thread.currentThread().sleep(SLEEPTIME);
					}
					catch(Exception e)
			{
						e.printStackTrace();
			}
			return number;
		};
		
		public int decreaseNumber(int num,int tag)
		{
			if(number-num>=0)
				number=number-num;
			else
			{
				num=number;
				number=0;
			}
			System.out.println("你失去了: "+name+" *"+num);
			if(tag==1)
				try{
					Thread.currentThread().sleep(SLEEPTIME);
					}
					catch(Exception e)
			{
						e.printStackTrace();
			}
			return number;
		};
		/*
		 * this part will print the information of using when in 
		 * the game,
		 * PrintListInfo() will worked when print the list of beg of using
		 * PrintInfo() will worked when role select to check up the information
		 * of using
		 */
		public int printListInfo()
		{
			System.out.print(name);
			if(addHitPoint!=0||addManaPoint!=0)
			{
				System.out.print(" (");
				if(addHitPoint!=0)
				{
					System.out.print(" HP:");
					if(percentOrNot==PERCENT)
						System.out.print(addHitPoint+"% ");
					else
						System.out.print(addHitPoint+" ");
				}
				if(addManaPoint!=0)
				{
					System.out.print(" MP:");
					if(percentOrNot==PERCENT)
						System.out.print(addManaPoint+"% ");
					else
						System.out.print(addManaPoint+" ");
				}
				System.out.print(") "+" *"+number);
			}
			else
				System.out.print("(物品)"+" *"+number);
			System.out.println();
			return 1;
		};
		/*
		 * this function return three sorts of value:
		 *		1:the role decide to use it
		 *		0:the role do nothing about it
		 *		-1:the role decide to throw out the using 
		 * the var in_npc_or_not mean if it show in shop.
		 * if in shop ,we should show  throw/use.
		 */
		public int printInfo(int in_npc_or_not) throws IOException
		{
			String userinput;
			System.out.println("--------------------物品信息--------------------");
			System.out.println("名称: "+name);
			System.out.println("数量: "+number);
			System.out.println("价格: "+money);
			System.out.print("信息: "+name);
			if(addHitPoint!=0||addManaPoint!=0)
			// not mission-needed exceptional items 
			{
				System.out.print("回复");
				if(addHitPoint!=0)
				{
					System.out.print(" 生命:");
					if(percentOrNot==PERCENT)
						System.out.println(this.addHitPoint+"%");
					else
						System.out.println(this.addHitPoint);
				}
				if(addManaPoint!=0)
				{
					System.out.print(" 魔法:");
					if(percentOrNot==PERCENT)
						System.out.println(this.addManaPoint+"%");
					else
						System.out.println(this.addManaPoint);
				}
			}
			else
				System.out.println("平常物品");
			System.out.println("-----------------------------------------------");
			
			if(in_npc_or_not == 1) /* buy in  shop .*/
			{
				System.out.print("1:购买");
			}
			else if(in_npc_or_not == 2)/* sell in shop*/
			{
				System.out.print("1:卖出");
			}
			else
			{
				System.out.print("1:丢弃");
				if(addHitPoint!=0||addManaPoint!=0)
					System.out.println("2:使用");
			}
			System.out.println("0:返回");
			for(int i = 0;i<55;++i)
				System.out.print("-");
			System.out.println();
			while(true){
			BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
			userinput = consoleBr.readLine();
			//userinput is a string type variable,so originally it is an array,but in java we reckon it as a class
			if(userinput.codePointAt(0)=='0')
				return 0;
			if(userinput.codePointAt(0)=='2'&&(addHitPoint!=0||addManaPoint!=0))
				return 1;
			if(userinput.codePointAt(0)=='1')
				return -1;}
			//check if the first character of userinput equals to 0/2/1
		};
		/*is the using-thing used up? this function will give you an answer*/
		public boolean useUp()
		{
			if(number<=0)
				return true;
			else
				return false;
		};
		};
	
	class UsingList {
			/*
			 * this list is the only in game ,and every using-thing in this list is 
			 * unique.
			 */
		List<Using> usingList = new ArrayList<Using>();
		Iterator<Using> itr_using = usingList.iterator();
		Using Usingle = null;
			//List is a generic interface,so you don't need to appoint it a name
			//private interface Iterator<Using> itr_using;
			//as there is iterator method in interface List,you could probably use it straightforward
			//__________________
			/*
			 * this function will return a using according to the id of using material
			 */

			public Using getUsing(final int id)
			{
				//Iterator<Using> gUit=usingList.iterator();
				/*return the iterator pointing to the List Object usingList*/;
				while (itr_using.hasNext())
				{
					Usingle = itr_using.next();
					if(Usingle.getID()==id)
						return (Usingle);
					else
						continue;
				}
					
				return (usingList.get(0));// return the first element of usingList
				
				
				//either return the first element of this List or the item matching up with id
				/* 
				 * will push a using-thing in list,if the param has been in the usinglist
				 * the function will return a value of 0,
				 * if it has been succeed push_back(), the function will return 1.
				 */
			};
			//tbc
			//given a integrate constant address,the method will return a "copy" of the address of the object
			/* 
			 * will push a using-thing in list,if the parameter has been in the usinglist
			 * the function will return a value of 0,
			 * if it has been succeed push_back(), the function will return 1.
			 */
			public int pushUsing(Using obj)
			{
				/* usingid is the using's which want to add */
				int usingID = obj.getID();
				//Iterator<Using> pUit=usingList.iterator();
				for(itr_using = usingList.iterator();itr_using.hasNext();)
				{
					Usingle = itr_using.next();
					if(Usingle.getID() == usingID)
					{
						return 0;
					}
				}
				usingList.add(obj);
				return 1;
				//Appends the specified element obj to the end of this list
			};
			/* return a value that the total number of using in this game.
			 * this function maybe not used.
			 */
			public int getSize()
			{
				return (usingList.size());
			};
			/*
			 * those several functions will be used when require to get some using
			 */
			/* initialize the itr_using to the begin of weaponlist*/
			//
			public int InitIrt()
			{
				//Iterator<Using> IIit=usingList.iterator();
				itr_using = usingList.iterator();//reset the iterator
				return 1;
				//lead the iterator to the first object in the List,and return 1
			}
			;
			/* next will make itr_using go to next using*/
			public int Next()
			{
				//Iterator<Using> Nextit=usingList.iterator();
				if(itr_using.hasNext())
					{
					Usingle = itr_using.next();
					return 1;
					}
					
				return 0;
			};
			
			/* this function will return a value of itr_using that check through till the
			 * end of the usinglist
			 */
			public int End()
			{
				//Iterator<Using> Endit=usingList.iterator();
				if(itr_using.hasNext())
					return 0;
				return 1;
			}
			;
			/* this function will get a using that current itr_using points to.
			 * returns a copy of Using .
			 */
			public Using getCurUsing()
			{
				Using obj = itr_using.next();
				return (obj);
				//return the object of iterator itr_using
			};
		}
	
	class Weapon
	{
		public final int WEAPONWHITE = 0;
		public final int WEAPONYELLOW = 1;
		public final int WEAPONBLUE = 2;
		public final int WEAPONPURPLE = 3;
		public final int WEAPONHEAD = 0;
		public final int WEAPONUP = 1;
		public final int WEAPONDOWN = 2;
		public final int WEAPONFOOT = 3;
		public final int WEAPONHAND = 4;
		public final int WEAPONWEAPON = 5;
		
		String name;
		int ID;
		int money;
		int color;
		int level;
		int addAttackPoint;
		int addDefendPoint;
		int position;
		
		public String getName()
		{
			return name;
		};
		public int getID()
		{
			return ID;
		};
		public int getMoney()
		{
			return money;
		};
		public int getColor()
		{
			return color;
		};
		public int getLevel()
		{
			return level;
		};
		public int getAddAttackPoint()
		{
			return addAttackPoint;
		};
		public int getAddDefendPoint()
		{
			return addDefendPoint;
		};
		public int getPosition()
		{
			return position;
		};
		public int setName(String n)
		{
			name = n;
			return 1;
		};
		public int setMoney(int m)
		{
			money = m;
			return money;
		};
		public int setID(int id)
		{
			ID = id;
			return ID;
		};
		public int setLevel(int l)
		{
			level = l;
			return level;
		};
		public int setColor(int co)
		{
			color = co;
			return co;
		};
		public int setAddAttackPoint(int attack)
		{
			addAttackPoint=attack;
			return addAttackPoint;
		};
		public int setAddDefendPoint(int defend)
		{
			addDefendPoint=defend;
			return addDefendPoint;
		};
		public int setPosition(int p)
		{
			position=p;
			return position;
		};
		public int generate(int value)
		{
			int clr = (int)(Math.random()*100)+value;
			//change the types of a number
			int temp;
			if(clr<6)
			{
				color = WEAPONPURPLE;
				temp = (int)(Math.random()*20);
				
				addAttackPoint = (int)((float)((addAttackPoint)*(temp+90)/100)+(float)(addAttackPoint*0.35));
				temp = (int)(Math.random()*20);
				
				addDefendPoint = (int)((float)((addDefendPoint)*(temp+90)/100)+(float)(addDefendPoint*0.35));
				temp = (int)(Math.random()*20);
				money = money * 10;
			}
			else if(clr<16)
			{
				color = WEAPONBLUE;
				temp = (int)(Math.random()*30);
				addAttackPoint = (int)((float)((addAttackPoint)*(temp+85)/100)+(float)(addAttackPoint*0.20));
				temp = (int)(Math.random()*30);
				
				addDefendPoint = (int)((float)((addDefendPoint)*(temp+85)/100)+(float)(addDefendPoint*0.20));
				temp = (int)(Math.random()*30);
				money = money * 6;
			}
			else if (clr<30)
			{
				color = WEAPONYELLOW;
				temp = (int)(Math.random()*20);
				addAttackPoint = (int)((float)((addAttackPoint)*(temp+90)/100)+(float)(addAttackPoint*0.15));
				temp = (int)(Math.random()*20);
				
				addDefendPoint = (int)((float)((addDefendPoint)*(temp+90)/100)+(float)(addDefendPoint*0.15));
				temp = (int)(Math.random()*20);
				money = money * 3;
			}
			else
			{
				color = WEAPONWHITE;
				temp = (int)(Math.random()*20);
				addAttackPoint = (int)((float)((addAttackPoint)*(temp+90)/100));
				temp = (int)(Math.random()*20);
				
				addDefendPoint = (int)((float)((addDefendPoint)*(temp+90)/100));
				temp = (int)(Math.random()*20);
			}
			return 1;
		};
		public int printListInfo()
		{
			System.out.print(name);
			if(color != WEAPONWHITE)
			{
				System.out.print("("+level+"级,");
				switch(color)
				{
				case WEAPONYELLOW:
					System.out.print("黄");
					break;
				case WEAPONBLUE:
					System.out.print("蓝");
					break;
				case WEAPONPURPLE:
					System.out.print("紫");
					break;
				}
				System.out.println(")");
				
			}
			else
			{
				System.out.println("("+level+"级)");
			}
			return 1;
		};
		public int printInfo(int tag) throws IOException
		{
			String userinput;
			System.out.println("-------------------装备信息-------------------");
			System.out.println("名称: "+name);
			System.out.println("等级: "+level);
			System.out.print("品质: ");
			switch(color)
			{
			case WEAPONWHITE:
				System.out.println("普通");
				break;
			case WEAPONYELLOW:
				System.out.println("优秀");
				break;
			case WEAPONBLUE:
				System.out.println("精良");
				break;
			case WEAPONPURPLE:
				System.out.println("传说");
				break;
			}
			System.out.print("类别: ");
			switch(position)
			{
			case WEAPONHEAD:
				System.out.println("头部");
				break;
			case WEAPONUP:
				System.out.println("上衣");
				break;
			case WEAPONDOWN:
				System.out.println("裤子");
				break;
			case WEAPONHAND:
				System.out.println("手套");
				break;
			case WEAPONFOOT:
				System.out.println("鞋子");
				break;
			case WEAPONWEAPON:
				System.out.println("武器");
				break;
			}
			System.out.print("攻击: "+addAttackPoint);
			System.out.print("防御: "+addDefendPoint);
			System.out.print("价格: "+money);
			System.out.println();
			if(tag == 1)
			{
				System.out.println("1: 卸下装备  0:返回");
				for(int i = 0;i<55;++i)
					System.out.print('-');
				System.out.println();
				while(true)
				{
					BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
					userinput = consoleBr.readLine();
					if(userinput.codePointAt(0)=='0')
						return 0;
					if(userinput.codePointAt(0)=='1')
						return -1;
				}
			}
			else if(tag == 0)
			{
				System.out.println("1: 使用装备  2： 丢弃  0: 返回");
				for(int i = 0;i<55;++i)
					System.out.print('-');
				System.out.println();
				while(true)
				{
					BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
					userinput = consoleBr.readLine();
					if(userinput.codePointAt(0)=='0')
						return 0;
					if(userinput.codePointAt(0)=='1')
						return 1;
					if(userinput.codePointAt(0)=='2')
						return -1;
				}
			}
			else if(tag == 2)
			{
				System.out.println("1: 购买  0: 返回");
				for(int i = 0;i<55;++i)
					System.out.print('-');
				System.out.println();
				while(true)
				{
					BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
					userinput = consoleBr.readLine();
					if(userinput.codePointAt(0)=='1')
						return 1;
					if(userinput.codePointAt(0)=='0')
						return 0;
				}
			}
			else if(tag == 3)
			{
				System.out.println("1: 卖出  0: 返回");
				for(int i = 0;i<55;++i)
					System.out.print('-');
				System.out.println();
				while(true)
				{
					BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
					userinput = consoleBr.readLine();
					if(userinput.codePointAt(0)=='1')
						return 1;
					if(userinput.codePointAt(0)=='0')
						return 0;
				}
			}
			return 0;
		}
	}
	
	class WeaponList
	{
		List<Weapon> weaponList = new ArrayList<Weapon>();
		Iterator<Weapon> itr_weapon;
		Weapon Weaponle = null;
		Weapon getWeapon(int id)
		{
			itr_weapon = weaponList.iterator();
			while(itr_weapon.hasNext())
			{
				Weaponle = itr_weapon.next();
				if(Weaponle.getID()==id)
				{
					return(Weaponle);
				}				
				else 
					continue;
			}
			return (weaponList.get(0));
		};
		
		int pushWeapon(Weapon weapon)
		{
			itr_weapon = weaponList.iterator();
			while(itr_weapon.hasNext())
			{
				Weaponle = itr_weapon.next();
				if(Weaponle.getID()==weapon.getID())
				{
					return 1;
				}
			}
			weaponList.add(weapon);
			return 1;
		};
		int getNum()
		{
			int temp_num = 0;
			while(itr_weapon.hasNext())
			{
				++temp_num;
			}
			return temp_num;
		};
		int initItr()
		{
			itr_weapon = weaponList.iterator();
			return 1;
		};
		int End()
		{
			if(itr_weapon.hasNext())
				return 0;
			else 
				return 1;
		};
		int Next()
		{
			if(!itr_weapon.hasNext())
			{
				return 0;
			}
			Weaponle = itr_weapon.next();
			return 1;
		};
		Weapon getCurWeapon()
		{
			return (Weaponle);
		};
	};
	class Skill
	{
		public static long SLEEPTIME = 900;
		private String name;
		int ID;
		int level;
		int percent;
		int manaNeed;
		int attackPoint;
		int healPoint;
		int defendPoint;
		int replenishOrNot;
		public String getName()
		{
			return name;
		};
		public int getID()
		{
			return ID;
		};
		public int getLevel()
		{
			return level;
		};
		public int getPercent()
		{
			return percent;
		};
		public int getManaNeed()
		{
			return manaNeed;
		};
		public int getAttackPoint()
		{
			return attackPoint;
		};
		public int getHealPoint()
		{
			return healPoint;
		};
		public int getDefendPoint()
		{
			return defendPoint;
		};
		
		public int setName(String n)
		{
			name = n;
			return 1;
		};
		public int setID(int id)
		{
			ID = id;
			return id;
		};
		public int setLevel(int l)
		{
			level = l;
			return level;
		};
		public int setPercent(int p)
		{
			percent = p;
			return percent;
		};
		public int setManaNeed(int mana)
		{
			manaNeed = mana;
			return manaNeed;
		};
		public int setAttackPoint(int attack)
		{
			attackPoint=attack;
			return attackPoint;
		};
		public int setHealPoint(int heal)
		{
			healPoint=heal;
			return healPoint;
		};
		public int setDefendPoint(int defend)
		{
			defendPoint=defend;
			return defendPoint;
		};
		
		public int printListInfo()
		{
			System.out.print("(");
			if(attackPoint!=0)
				System.out.print("攻");
			else if (healPoint!=0)
				System.out.print("愈");
			System.out.println(","+manaNeed);
			return 1;
		};
		public int printInfo() throws IOException
		{
			String userinput;
			for(int i=0;i<45;++i)
				if(i==22)
					System.out.print("技能信息");
				else
					System.out.print("-");
			System.out.println("名称:"+name);
			System.out.println("需要等级:"+level);
			System.out.println("魔耗"+manaNeed);
			System.out.println("信息:");
			if(attackPoint!=0)
			{
				System.out.print("对敌人造成");
				if(percent == 1)
				{
					System.out.print("自身攻击的");
				}
				else
					System.out.println("伤害.");
			}
			else if(healPoint!=0)
			{
				System.out.print("治愈");
				if(percent == 1)
				{
					System.out.print("自身最大生命值的");
				}
				else
					System.out.println("生命");
			}
			for(int i = 0;i<55;++i)
				System.out.print('-');
			System.out.println();
			System.out.println("1:使用  0:返回");
			for(int i = 0;i<55;++i)
				System.out.print('-');
			System.out.println();
			while(true)
			{
				BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
				userinput = consoleBr.readLine();
				if(userinput.codePointAt(0)=='1')
					return 1;
				if(userinput.codePointAt(0)=='0')
					return 0;
				else
					break;
			}
			return 0;
		};
		public int useSkill(Role role)
		{
			int temp;
			String userinput;
			if(role.changeCurManaPoint(-manaNeed,0) == -1)
			//there is sth wrong with the sourcecode,so I add a var named "0" to make sure that we are not gonna recover
			{
				System.out.println("===你没有足够的魔法！===");
				try{
					Thread.currentThread().sleep(SLEEPTIME);
					}
					catch(Exception e)
			{
						e.printStackTrace();
			}
			}
			if(healPoint!=0)
			{
				if(percent==1)
				{
					temp=(int)((float)(role.getCurHitPoint()*healPoint)/100);
					role.changeCurHitPoint(temp,1);
					return 0;
				}
				else
				{
					role.changeCurHitPoint(healPoint,1);
					return 0;
				}
			}
			if(attackPoint!=0)
			{
				System.out.print("skill "+this.name+"attack"+this.attackPoint);
				if(this.percent == 1)
					System.out.println('%');
				try{
					Thread.currentThread().sleep(1000);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				if(percent==1)
				{
					temp=(int)((float)(role.getAttackPoint())*attackPoint/100);
					System.out.println("percent"+temp);
					try{
						Thread.currentThread().sleep(SLEEPTIME);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					if(temp<=0)
						return 1;
					return temp;
				}
				else
				{
					return attackPoint;
				}
			}
			System.out.println("something wrong in Skill-UsingSkill function");
			try{
				Thread.currentThread().sleep(1000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			return -1;
		};
		public boolean canStudy(int l)
		{
			if(l>=level)
				return false;
			else
				return true;
		};
	};
	class SkillList
	{
		List<Skill> skillList = new ArrayList<Skill>();
		Iterator<Skill> itr_skill = skillList.iterator();
		Skill Skillle = new Skill();

		Skill getSkill(int id)
		{
			while(itr_skill.hasNext())
			{
				Skillle = itr_skill.next();
				if(Skillle.getID()==id)
					return (Skillle);
				else
					continue;
			}
			return (skillList.get(0));
		};
		
		int pushSkill(Skill skill)
		{
			/* the id of param*/
			int id = skill.getID();
			while(itr_skill.hasNext())
			{
				if(Skillle.getID()==id)
					return 0;
			}
			skillList.add(skill);
			Skillle = skill;
			return 1;
		}
		int InitItr()
		{
			itr_skill= skillList.iterator();
			Skillle = itr_skill.next();
			return 1;
		}
		boolean End()
		{
			if(itr_skill.hasNext())
				return true;
			else
				return false;
		}
		int Next()
		{
			if(itr_skill.hasNext())
				Skillle = itr_skill.next();
			return 0;
		}
		Skill getCurSkill()
		{
			return (Skillle);
		}
	}

	class Role
	{
		final static int MON_LOCK = 1;
		final static int MON_UNLOCK = 0;
		final static int SLEEPTIME = 1000;
		String name;
		int money;
		int hitPoint;
		int curHitPoint;
		int manaPoint;
		int curManaPoint;
		int expPoint;
		int curExpPoint;
		int level;
		
		int attackPoint;
		int defendPoint;
		int change;
		/*
		 * where the role is ,those two integer will tell you
		 */
		int posX;
		int posY;
		List<Using> usingList = new ArrayList<Using>();
		Iterator<Using> itr_using = usingList.iterator();
		Using ule = null;
		List<Integer> missionList = new ArrayList<Integer>();
		List<Integer> missionDoneList = new ArrayList<Integer>();
		//"int" is the basic "type" so you can't use it straightly,use "Integer"
		Iterator<Integer>itr_mission = missionList.iterator();
		Iterator<Integer>itr_mission_done = missionDoneList.iterator();
		List<Weapon> weaponList = new ArrayList<Weapon>();
		//List<Weapon*> equipmentList = new ArrayList<Weapon>();
		Iterator<Weapon> itr_weapon = weaponList.iterator();
		List<Skill> skillList = new ArrayList<Skill>();
		Iterator<Skill> itr_skill = skillList.iterator();
		
		List<Weapon> equipmentList;
		public String getName()
		{
			String tmp = name;
			return tmp;
		};
		public int getMoney()
		{
			int tmp = money;
			return tmp;
		};
		public int getHitPoint()
		{
			int tmp = hitPoint;
			return tmp;
		};
		public int getCurHitPoint()
		{
			int tmp = curHitPoint;
			return tmp;
		};
		public int getManaPoint()
		{
			int tmp = manaPoint;
			return tmp;
		};
		public int getCurManaPoint()
		{
			int tmp = curManaPoint;
			return tmp;
		};
		public int getExpPoint()
		{
			int tmp = expPoint;
			return tmp;
		};
		public int getCurExpPoint()
		{
			int tmp = curExpPoint;
			return tmp;
		};
		public int getLevel()
		{
			int tmp = level;
			return tmp;
		};
		public int getAttackPoint()
		{
			int tmp = attackPoint;
			return tmp;
		};
		public int getDefendPoint()
		{
			int tmp = defendPoint;
			return tmp;
		};
		
		public int setName(String n)
		{
			name = n;
			return 1;
		};
		public int setMoney(int m)
		{
			money=m;
			return m;
		};
		public int setHitPoint(int hp)
		{
			hitPoint=hp;
			return hitPoint;
		};
		public int setCurHitPoint(int chp)
		{
			curHitPoint=chp;
			return chp;
		};
		public int setManaPoint(int mp)
		{
			manaPoint=mp;
			return mp;
		};
		public int setCurManaPoint(int cmp)
		{
			curManaPoint=cmp;
			return cmp;
		};
		public int setExpPoint(int ep)
		{
			expPoint=ep;
			return ep;
		};
		public int setCurExpPoint(int cep)
		{
			curExpPoint=cep;
			return cep;
		};
		public int setLevel(int l)
		{
			level=l;
			return l;
		};
		public int setAttackPoint(int attack)
		{
			attackPoint=attack;
			return attack;
		};
		public int setDefendPoint(int defend)
		{
			defendPoint=defend;
			return defend;
		};
		
		public int initUsingItr()
		{
			itr_using = usingList.iterator();
			return 0;
		};
		public int nextUsingItr()
		{
			while(itr_using.hasNext())
			{
				ule = itr_using.next();
			}
			return 0;
		};
		public int endUsingItr()
		{
			if(!itr_using.hasNext())
			{
				return 1;
			}
			return 0;
		};
		public Using getCurUsing()
		{
			return ule;
		};
		
		public int changeMoney(final int cM)
		{
			money += cM;
			System.out.println("你获得了: "+cM+"两银两");
			return cM;
		};
		public int changeName(final String cN)
		{
			name = cN;
			return 1;
		};
		public int changeHitPoint(final int c_hitPoint)
		{
			hitPoint += c_hitPoint;
			int tmp = hitPoint;
			return tmp;
		};
		public int changeCurHitPoint(final int c_hp,final int tag)
		{
			int tmp_c_hp = c_hp;
			if(tmp_c_hp>=hitPoint-curHitPoint)
				tmp_c_hp=hitPoint-curHitPoint;
			curHitPoint += tmp_c_hp;
			if(tag == 1 && c_hp>0)
			{
				System.out.println("你回复了 "+tmp_c_hp+" 点生命");
			}
			try{
				Thread.currentThread().sleep(1000);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			int tmp = curHitPoint;
			return tmp;
		};
		public int changeManaPoint(final int c_manaPoint)
		{
			manaPoint += c_manaPoint;
			int tmp = manaPoint;
			return tmp;
		};
		public int changeCurManaPoint(final int c_mp,final int tag)
		{
			if(curManaPoint + c_mp < 0)/* role's mana is no engh */
				return -1;
			int tmp_c_mp = c_mp;
			if(tmp_c_mp>manaPoint-curManaPoint)
				tmp_c_mp=manaPoint-curManaPoint;
			curManaPoint+=tmp_c_mp;
			if(tag==1)
			{
				System.out.println("你回复了 "+tmp_c_mp+" 点魔力");
				try{
					Thread.currentThread().sleep(1000);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			}
			return 0;
		};
		public int changeExpPoint(final int c_ep)
		{
			expPoint += c_ep;
			int tmp= expPoint;
			return tmp;
		};
		public int changeCurExpPoint(final int c_ep,final int tag,SkillList skillList)
		{
			if(level<=99)
			{
				curExpPoint+=c_ep;
				if(tag==1)
				{
					System.out.println("你获得了 "+c_ep+" 点经验");
					try{
						Thread.currentThread().sleep(1000);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
				}
			}
			if(curExpPoint > expPoint)
			{
				levelUp(skillList);
			}
			return 0;
		};
		
		public int printHead()
		{
			int pointLength = 40;
			int temp;
			System.out.println(name+"  等级"+level+"  两银两"+money);
			System.out.print("HP:  ");
			temp = (int)((float)(curHitPoint)/(float)(hitPoint)*pointLength);
			for(int i=0;i<pointLength;++i)
				if(i<temp) 
					System.out.print('|');
				else
					System.out.print('.');
			temp=(int)((float)(curManaPoint)/(float)(manaPoint)*pointLength);
			
			System.out.print("MP:  ");
			for(int i=0;i<pointLength;++i)
				if(i<temp) 
					System.out.print('|');
				else
					System.out.print('.');
			
			System.out.print("EXP:  ");
			temp=(int)((float)(curExpPoint)/(float)(expPoint)*pointLength);
			for(int i=0;i<pointLength;++i)
				if(i<temp) 
					System.out.print('|');
				else
					System.out.print('.');
			System.out.println();
			for(int i=0;i<55;++i)
				System.out.print('-');
			System.out.println();
			return 1;
		};
		/*public int printInfo()
		{
			List<>
		};*/
		public int levelUp(SkillList sklist)
		{
			curExpPoint=curExpPoint-expPoint;
			expPoint=(20*level+40);
			hitPoint += 20;
			curHitPoint = hitPoint;
			
			attackPoint+=3;
			defendPoint+=1;
			++level;
			
			System.out.println(name+"升到了"+level+"级！");
			for(sklist.InitItr();!sklist.End();sklist.Next())
			{
				if((sklist.getCurSkill()).canStudy(level))
					skillList.add((sklist.getCurSkill()));
			}
			try{
				Thread.currentThread().sleep(1500);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			return level;
		};
		public boolean Dead()
		{
			if(curHitPoint <= 0)
				return true;
			else
				return false;
		};
		public int newLife()
		{
			int temp=expPoint/10;
			if(temp<curExpPoint)
				curExpPoint=curExpPoint-temp;
			else
			{
				temp=curExpPoint;
				curExpPoint=0;
			}
			curHitPoint = hitPoint;
			System.out.println("    "+name+"已经挂掉！损失经验："+temp+"点");
			try{
				Thread.currentThread().sleep(1200);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			return 1;
		};
		
		public int printBag(int battleOrNot) throws IOException
		{
			int battle;
			change=0;
			String userinput = null;
			int i,j,k;
			Iterator<Using> itr_using_t = null;
			Using iutle = null;
			Iterator<Weapon> itr_weapon_t;
			Weapon iwtle = null;
			int page = 0;
			int which;
			int maxPrint = 9;
			int nextPage;
			int tt = 1;
			int numList;
			while(true)
			{
				printHead();
				if(battleOrNot!=0)
					battle = 1;
				else 
					battle = 0;
				nextPage = 1;
				
				for(i=0;i<45;++i)
					if(i == 22)
						System.out.print("背包信息");
					else
						System.out.print('-');
				System.out.println();
				
				if((int)(usingList.size())>page*maxPrint)
					which = 0;
				else 
					which = 1;
				
				if(which == 0)
				{
					for(itr_using_t=usingList.iterator(),i=0;i<page*maxPrint;++i)
						iutle = itr_using_t.next();
					k = (int)(usingList.size() - page*maxPrint);
					if(k>maxPrint)
					{
						for(i=0;i<maxPrint;++i)
						{
							System.out.print(" "+i+1+":");
							iutle.printListInfo();
							iutle = itr_using_t.next();
						}
					}
					else
					{
						tt = 0;
						while(itr_using_t.hasNext())
						{
							System.out.print(" "+(tt+1)+":");
							iutle.printListInfo();
							++tt;
						}
						for(itr_weapon_t=weaponList.iterator();itr_weapon_t.hasNext();itr_weapon_t.next())
						{
							if(tt<maxPrint)
							{
								System.out.print(" "+(tt+1)+":");
								iwtle.printListInfo();
								++tt;
							}
							else
								break;
						}
					}
				}
				else
				{
					k=maxPrint*page-(int)(usingList.size());
					itr_weapon_t=weaponList.iterator();
					for(i=0;i<k;++i)
						iwtle=itr_weapon.next();
					tt = 0;
					for(;itr_weapon_t.hasNext();itr_weapon_t.next())
					{
						if(tt<maxPrint)
						{
							System.out.print(" "+(tt+1)+":");
							iwtle.printListInfo();
							++tt;
						}
						else
							break;
					}
				}
				if(weaponList.size()+usingList.size() != 0)
				{
					for(i=0;i<55;++i)
						System.out.print('-');
					System.out.println();
					numList = (int)(weaponList.size()+usingList.size())-page*maxPrint;
					if(numList>9)
						numList = 9;
					System.out.print("1~"+numList+"选择   ");
					if(page>0)
						System.out.print("z"+"/");
					if((page+1)*maxPrint<(int)(usingList.size()+weaponList.size()))
						System.out.println("X"+":翻页   0:退出");
					for(i=0;i<55;++i)
						System.out.print('-');
					System.out.println();
					while(true)
					{
						BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
						userinput = consoleBr.readLine();
						
						if((userinput.codePointAt(0) >= (char)(1+'0')) && (userinput.codePointAt(0) <= (char)(numList+'0')))
							break;
						if((userinput.codePointAt(0) == 'x' || userinput.codePointAt(0) =='X') && ((page + 1)*maxPrint < (int)(usingList.size())+(int)(weaponList.size())))
							break;
						if((userinput.codePointAt(0) == 'z'||userinput.codePointAt(0) == 'Z') && page > 0)
							break;
						if(userinput.codePointAt(0) == '0')
						{
							if(change==0)
								return 0;
							else
								return 1;
						}
					}
					if(userinput.codePointAt(0) >= (char)(1+'0') && userinput.codePointAt(0) <= (char)(numList+'0'))
					{
						int temp;
						i = userinput.codePointAt(0) - '0' + page*maxPrint;
						if(i>(int)(usingList.size()))
						{
							i = i-(int)(usingList.size());
							itr_weapon_t=weaponList.iterator();
							for(j = 1;j < i; ++j)
								iwtle = itr_weapon_t.next();
							
							printHead();
							temp = iwtle.printInfo(0);
							if(temp != 0)
							{
								if(temp == 1)
									useWeapon(iwtle);
								if(temp == -1)
									weaponList.remove(iwtle);
								change = 1;
								if(battle == 1)
									return 1;
							}
						}
						else/*select using*/
						{
							itr_using_t = usingList.iterator();
						}
						for(j=1;j<i;++j)
							iutle = itr_using_t.next();
						printHead();
						temp = iutle.printListInfo();
						if(temp != 0)
						{
							if(temp==1)
								useUsing(iutle);
							if(temp==-1)
								usingList.remove(iutle);
							change=1;
							if(battle == 1)
								return 1;
						}
					}
				}
				if((userinput.codePointAt(0) == 'x' || userinput.codePointAt(0) == 'X') && ((page + 1)*maxPrint < (int)(usingList.size()+weaponList.size())))
					++page;
				if((userinput.codePointAt(0) == 'z' || userinput.codePointAt(0) == 'Z') && page > 0)
					--page;
				
				else 
				{
					System.out.println("  你的背包是空的！  ");
					for(i=0;i<55;++i)
					System.out.print('-');
					System.out.println();
					System.out.println("  0: 返回");
					for(i=0;i<55;++i)
					System.out.print('-');
					System.out.println();
				
					while(true)
					{
						BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
						userinput = consoleBr.readLine();
						if(userinput.codePointAt(0) == '0')
							break;
					}
					if(userinput.codePointAt(0) == '0')
						return 0;
				}
			}
		};
		public int printSkill(int battleOrNot) throws IOException
		{
			int temp;
			Iterator<Skill> itr_skill_t;
			Skill istle = null;
			int num;
			int page = 0;
			int maxPrint = 9;
			String userinput;
			while(true)
			{
				printHead();
				for(int i=0;i<45;++i)
					if(i==22)
						System.out.print("技能栏");
					else
						System.out.print('-');
				System.out.println();
				
				if((int)(skillList.size()) == 0)
					System.out.println("你还没有技能！");
				for(int i=0;i<55;++i)
					{
						System.out.print('-');
						System.out.println();
						System.out.println("0:返回");
					}
				for(int i=0;i<55;++i)
					System.out.print('-');
				System.out.println();
				
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0) == '0')
						return 0;
					else
						break;
				}
				num = (int)((skillList.size()) - page * maxPrint);
				if(num>9)
					num = 9;
				int i;
				for(i=0,itr_skill_t = skillList.iterator();i<page*maxPrint;++i)
				{
					istle = itr_skill_t.next();
				}
				
				for(i=0;itr_skill_t.hasNext();)
				{
					istle = itr_skill_t.next();
					if(i<maxPrint)
					{
						System.out.print("  "+(i+1)+":");
						istle.printListInfo();
						++i;
					}
				}
				for(int j = 0;i<55;++i)
					System.out.print('-');
				System.out.println();
				System.out.print(" 1~"+num+":选择");
				if(page>0)
					System.out.print("Z");
				if(num>(page+1)*maxPrint)
					System.out.print("X");
				System.out.println(":翻页   0:退出");
				for(int k = 0;i<55;++i)
					System.out.print('-');
				System.out.println();
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0) == '0')
					return 0;
					
					if((userinput.codePointAt(0)=='X'||userinput.codePointAt(0)=='x') && (num>(page+1)*maxPrint))
					{
						++page;
						break;
					}
					if((userinput.codePointAt(0)=='Z'||userinput.codePointAt(0)=='z') && (page>0))
					{
						--page;
						break;
					}
					if((userinput.codePointAt(0)>='1' && (userinput.codePointAt(0) <= (char)('0'+num))))
					{
						break;
					}
					
				}
				if((userinput.codePointAt(0)>='1' && (userinput.codePointAt(0) <= (char)('0'+num))))
					{
						temp = userinput.codePointAt(0) - '0' +page*maxPrint;
						itr_skill_t = skillList.iterator();
						for(i=1;i<temp;++i)
							istle = itr_skill_t.next();
						if(battleOrNot!=0)
						{
							printHead();
							temp = istle.printInfo();
							if(temp!=0)
							{
								int k = istle.useSkill(this);
								if(k==0)/* role use heal skill*/
									return -1;
								else if(k==1)
								{
									
								}
								else
								{
									return k;
								}
							}
							else
							{
								/* the skill haven't been used , continue; */
							}
												
						}
						else
						{
							printHead();
							if(istle.printInfo()!=0)
								istle.useSkill(this);
						}
					}
			}
			/* the number of skill in current page */
			
		};
		public int showSkill()
		{
			return 0;
		};
		
		public int Sell() throws IOException
		{
			change = 0;
			String userinput;
			int i,j,k;
			Iterator<Using> itr_using_t;
			Iterator<Weapon> itr_weapon_t;
			Using iutle = null;
			Weapon iwtle = null;
			int page = 0;
			int which = 0;
			int maxPrint = 9;
			int nextPage;
			int tt = 1;
			int numlist;
			while(true)
			{
				printHead();
				nextPage = 1;
				for(i=0;i<45;++i)
					if(i==22)
						System.out.print("背包信息");
					else
						System.out.print('-');
				System.out.println();
				if(which == 0)
				{
					for(itr_using_t = usingList.iterator(),i=0;i<page*maxPrint;++i)
						iutle = itr_using_t.next();
					k = (int)(usingList.size()) - page*maxPrint;
					if(k>=maxPrint)
					{
						for(i=0;i<maxPrint;++i)
							{
								System.out.print("  "+(i+1)+":");
								iutle.printListInfo();
								iutle = itr_using_t.next();
							}
						
					}
					else
					{
						tt = 0;
						for(;itr_using_t.hasNext();)
						{
							System.out.print("  "+(tt+1)+":");
							iutle = itr_using_t.next();
							iutle.printListInfo();
							++tt;
						}
						for(itr_weapon_t = weaponList.iterator();itr_weapon_t.hasNext();)
						{
							iwtle = itr_weapon_t.next();
							if(tt<maxPrint)
							{
								System.out.print("  "+(tt+1)+":");
								iwtle.printListInfo();
								++tt;
							}
							else
								break;
						}
					}
				}
				else
				{
					k = maxPrint*page - (int)(usingList.size());
					itr_weapon_t = weaponList.iterator();
					for(i=0;i<k;++i)
						itr_weapon_t.next();
					tt = 0;
					for(;itr_weapon_t.hasNext();)
					{
						iwtle = itr_weapon_t.next();
						if(tt<maxPrint)
						{
							System.out.print("  "+(tt+1)+":");
							iwtle.printListInfo();
							++tt;
						}
						else
							break;
					}
				}
				if(weaponList.size()+usingList.size() !=0)
				{
					for(i=0;i<55;++i)
					{
						System.out.print('-');
						System.out.println();
					}
					numlist = (int)(weaponList.size() + usingList.size() -page*maxPrint);
					if(numlist>9)
						numlist = 9;
					System.out.print("1~"+numlist+"选择卖出");
					if(page>0)
						System.out.print("Z");
					if((page+1)*maxPrint<(int)(usingList.size()+weaponList.size()))
					{
						System.out.println("X"+" :翻页  0::退出");
						for(i=0;i<55;++i)
						{
							System.out.print('-');
							System.out.println();
						}
						while(true)
						{
							BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
							userinput = consolebr.readLine();
							if(userinput.codePointAt(0)>=(char)(1+'0')&&userinput.codePointAt(0)<=(char)(numlist+'0'))
								break;
							if((userinput.codePointAt(0)=='x'||userinput.codePointAt(0)=='X')&&
								((page+1)*maxPrint<(int)(usingList.size())+(int)(weaponList.size())))
								break;
							if((userinput.codePointAt(0)=='z'||userinput.codePointAt(0)=='Z')&&
								page>0)
								break;
							if(userinput.codePointAt(0)=='0')
							{
									return 1;
							}
						}
						if(userinput.codePointAt(0)>=(char)(1+'0')&&userinput.codePointAt(0)<=(char)(numlist+'0'))
						{
							i=userinput.codePointAt(0)-'0'+page*maxPrint;
							if(i>(int)(usingList.size()))
							{
								i=i-(int)(usingList.size());
								itr_weapon_t = weaponList.iterator();
								for(j=1;j<i;++j)
									iwtle = itr_weapon_t.next();
								printHead();
								int temp = iwtle.printInfo(3);
								if(temp==1)
								{
									Iterator<Weapon> itr_weapon_tt;
									Weapon iwttle = null;
									int eqp_or_not = 0;
									for(itr_weapon_tt = this.equipmentList.iterator();itr_weapon_tt.hasNext();)
									{
										iwttle = itr_weapon_tt.next();
										if(iwttle.getID() == iwtle.getID())
										{
											System.out.println("===你正在使用这件装备！===");
											eqp_or_not = 1;
											try{
												Thread.currentThread().sleep(SLEEPTIME);
												}
												catch(Exception e)
												{
													e.printStackTrace();
												}
											break;
										}
									}
									if(eqp_or_not == 1)
										;
									else
									{
										money += iwtle.getMoney();
										weaponList.remove(itr_weapon_t);
									}
								}
							}
							else	//select using
							{
								itr_using_t = usingList.iterator();
								for(j=1;j<i;++j)
									iutle = itr_using_t.next();
								printHead();
								int temp;
								temp = iutle.printListInfo();
								if(temp == -1)
								{
									System.out.println("你确定要卖出"+iutle.getName()+"*"+iutle.getNumber()+"吗？");
									System.out.println("1:确定  0:返回");
									System.out.println();
									String tempinput;
									while(true)
									{
										BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
										tempinput = consolebr.readLine();
										if(tempinput.codePointAt(0)=='1')/* decide to sell using*/
										{
											money=money+(iutle.getMoney()*(iutle.getNumber()))/2;
											usingList.remove(iutle);
											break;
										}
										if(tempinput.codePointAt(0)=='0') /* decide to quit*/
											break;
									}
								}
							}
						}
						if((userinput.codePointAt(0)=='x'||userinput.codePointAt(0)=='X')&&((page+1)*maxPrint<(int)(usingList.size()+weaponList.size())))
							++page;
						if((userinput.codePointAt(0)=='z'||userinput.codePointAt(0)=='Z')&&page>0)
							--page;
						
					}
					else	//the bag is empty
					{
						System.out.println("  你的背包是空的！");
						for(i=0;i<55;++i)
							System.out.print('-');
						System.out.println();
						System.out.println("  0:返回");
						for(i=0;i<55;++i)
							System.out.print('-');
						System.out.println();
						while(true)
						{
							BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
							userinput = consolebr.readLine();
							if(userinput.codePointAt(0)=='0')
								break;
						}
						if(userinput.codePointAt(0)=='0')
							return 0;
					}
				}
			}
		};
		
		public int printMission(MissionList mslist,UsingList uslist,MonsterList mtlist,NPCList npclist) throws IOException
		{
			String userinput;
			int cur_page = 0;
			int num_page = 0;
			int num_mission_not_done = (int)(missionList.size());
			int num_mission_done = (int)(missionDoneList.size());
			int num_mission_all = num_mission_done + num_mission_not_done;
			int max_print = 9;
			num_page = num_mission_all / max_print ;
			if(num_mission_all % max_print != 0)
				++num_page;
			
			Iterator<Integer> itr_mission_tmp;
			Integer imtle = null;
			while(true)
			{
				printHead();
				if(num_mission_not_done+num_mission_done == 0)
				{
					System.out.println("======你还没有任务！======");
					System.out.println();
					System.out.println("1：确定");
					System.out.println();
					while(true)
					{
						BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
						userinput = consolebr.readLine();
						if(userinput.codePointAt(0) == '1')
							break;
					}
				}
				
				else if(cur_page*max_print + 1> num_mission_not_done)
				{
					int temp = cur_page*max_print - num_mission_not_done;
					itr_mission_tmp = missionDoneList.iterator();
					for(int i = 0;i<temp;++i)
						itr_mission_tmp.next();
					
					for(int i = 0;i<max_print && itr_mission_tmp.hasNext();++i)
					{
						imtle = itr_mission_tmp.next();
						System.out.println("  "+(i+1)+":"+(mslist.getMissionPtr(imtle).getName()+"(已完成)"));
					}
				}
				else
				{
					int index_in_page = 0;
					for(itr_mission_tmp = missionList.iterator();index_in_page <max_print +1;)
					{
						if(itr_mission_tmp.hasNext())
						{
							System.out.println("  "+(index_in_page+1)+":"+ mslist.getMissionPtr(imtle).getName());
							++index_in_page;
							imtle = itr_mission_tmp.next();
						}
						else
							break;
					}
					if(index_in_page < max_print)
					{
						for(itr_mission_tmp = missionDoneList.iterator();index_in_page < max_print +1;)
						{
							if(itr_mission_tmp.hasNext())
							{
								System.out.println("  "+(index_in_page+1)+":"+mslist.getMissionPtr(imtle).getName()+"(已完成)");
								++index_in_page;
								imtle = itr_mission_tmp.next();
							}
							else
								break;
						}
					}
				}
				int num_of_mission_in_cur_page = num_mission_all-max_print*cur_page;
				if(num_of_mission_in_cur_page>max_print)
					num_of_mission_in_cur_page = max_print;
				System.out.println();
				System.out.print("1~"+num_of_mission_in_cur_page+":选择 ");
				if(cur_page>0)
					System.out.print("Z"+"/");
				if(cur_page + 1 < num_page)
					System.out.println("X"+":翻页  0:退出");
				System.out.println();
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0) == '0')
						return 0;
					if(userinput.codePointAt(0) == 'Z' || userinput.codePointAt(0) == 'z' )
						{
							--cur_page;
							break;
						}
					if(userinput.codePointAt(0) == 'X' || userinput.codePointAt(0) == 'x')
						{
							--cur_page;
							break;
						}
					if(userinput.codePointAt(0) - '0' >0 && userinput.codePointAt(0) - '0' <= num_of_mission_in_cur_page)
						break;
				}
				int index_select_mission = 0;
				int give_up_or_not = 0;
				if(userinput.codePointAt(0)-'0'> 0 &&
					userinput.codePointAt(0)-'0'<= num_of_mission_in_cur_page)
				{
					index_select_mission = userinput.codePointAt(0) - '0';
					if(index_select_mission + max_print*cur_page <= num_mission_not_done)
					{
						int index_mission_tmp = index_select_mission + max_print*cur_page;
						int i = 1;
						for(i = 1,itr_mission_tmp = missionList.iterator();i < index_mission_tmp;++i)
						{
							itr_mission_tmp.next();
						}
						give_up_or_not = mslist.getMissionPtr(imtle).printInfo(uslist,mtlist,npclist);
					}
					else
					{
						int index_mission_tmp = index_select_mission + max_print*cur_page - num_mission_not_done;
						int i = 1;
						itr_mission_tmp = missionDoneList.iterator();
						while(i < index_mission_tmp)
						{
							++i;
							itr_mission_tmp.next();
						}
						give_up_or_not = mslist.getMissionPtr(imtle).printInfo(uslist,mtlist,npclist);
						if(give_up_or_not == -1)
						{
							Iterator<Integer> itr_check_msn;
							Integer icmle = null;
							int flag_msn = 1;
							for(itr_check_msn = (this).missionDoneList.iterator();itr_check_msn.hasNext();)
							{
								icmle = itr_check_msn.next();
								if(icmle == imtle)
								{
									flag_msn = 0;
									break;
								}
							}
							if(flag_msn == 1)
							{
								mslist.getMissionPtr(imtle).setState(Global.MSN_BEFORE);
								Iterator<Integer> itr_find_msn;
								Integer ifmle = null;
								for(itr_find_msn = missionList.iterator();itr_find_msn.hasNext();)
								{
									ifmle = itr_find_msn.next();
									if(ifmle == imtle)
										break;
									if(itr_find_msn.hasNext())
										missionList.remove(ifmle);
								}
								return 0;
							}
						}
					}
					return -1;
				}
			}

		};
		public int moveMission(int id)
		{
			Iterator<Integer> itr_mov_msn;
			Integer immle = null;
			/* first ,find it and then delete it from missionlist.*/
			for(itr_mov_msn = missionList.iterator();itr_mov_msn.hasNext();)
			{
				immle = itr_mov_msn.next();
				if(immle == id);
				break;
			}
			if(itr_mov_msn.hasNext())
				missionList.remove(immle);
			for(itr_mov_msn = missionDoneList.iterator();itr_mov_msn.hasNext();)
				if(id == immle)
					break;
			/* if has no one , we should add */
			if(!itr_mov_msn.hasNext())
				missionDoneList.add(id);
			return 0;
		};
		/*
		 * this part will only worked in battle! 
		 * when the end of battle ,role can pick up the thing that monster have droped!
		 *
		 *  this function will be overload when role pick up one more using or role
		 * buy lots of using from npc.
		 */
		public int pickUp(Using use)
		{
			int usingid = use.getID();
			Using ule = null;
			for(itr_using = usingList.iterator();itr_using.hasNext();)
				{
					ule = itr_using.next();
					if(ule.getID() == usingid)
					{
						ule.addNumber(1);
						return 1;
					}
				}
			usingList.add(use);
			ListIterator<Using> ritr_using = usingList.listIterator(usingList.size());
			Using r_ule = ritr_using.previous();
			r_ule.addNumber(1);
			return 1;
		};
		public int pickUp(Using use,int num)
		{
			int usingid = use.getID();
			Using ule = null;
			Using rule = null;
			for(itr_using = usingList.iterator();itr_using.hasNext();)
			{
				ule = itr_using.next();
				if(ule.getID() == usingid)
				{
					ule.addNumber(num, 1);
					return 1;
				}
				/*role has no use in the bag*/
				usingList.add(use);
				ListIterator<Using> ritr_using = usingList.listIterator(usingList.size());
				rule = ritr_using.previous();
				rule.addNumber(num, 1);
			}
			return 1;
		};
		public int pickUp(Weapon weapon)
		{
			weaponList.add(weapon);
			System.out.print("你得到了:");
			weapon.printListInfo();
			try{
				Thread.currentThread().sleep(SLEEPTIME);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			return 1;
		};
		public int useUsing(Using use)
		{
			int usingid = use.getID();
			Iterator<Using> itr_using_t;
			Using iutle = null;
			for(itr_using_t = usingList.iterator();itr_using_t.hasNext();)
			{
				iutle = itr_using_t.next();
				if(iutle.getID() == usingid)
				{
					iutle.decreaseNumber(1);
					break;
				}
			}
			if(iutle.getNumber()<=0)
				usingList.remove(iutle);
			if(iutle.getAddHitPoint()>0)
			{
				int add_hp_tmp = 0;
				if(iutle.getPercentOrNot() == 1)
				{
					add_hp_tmp = (int)((float)(iutle.getAddHitPoint())/100*(this.getHitPoint()));
				}
				else
				{
					add_hp_tmp = iutle.getAddHitPoint();
				}
				this.changeCurHitPoint(add_hp_tmp,1);
				return 0;
			}
			if(iutle.getAddManaPoint() > 0) /* using-thing add mana point */
			{
				/* mp need to change */
				int add_mp_tmp = 0;
				if(iutle.getPercentOrNot() == 1) /* check if percent */
				{
					add_mp_tmp = (int)((float)(iutle.getAddManaPoint())/100*(this.getManaPoint()));
				}
				else
				{
					add_mp_tmp = iutle.getAddManaPoint();
				}
				this.changeCurManaPoint(add_mp_tmp,1);
			}
			return 1;
		};
		
		public int loseUsing(int id,int num)
		{
			Using iule = null;
			for(itr_using = usingList.iterator();itr_using.hasNext();)
			{
				iule = itr_using.next();
				if(iule.getID() == id)
				{
					iule.decreaseNumber(num, 1);
					if(iule.getNumber() <= 0)
					{
						usingList.remove(iule);
						break;
					}
				}
			}
			return 1;
			
		};
		
		public int useWeapon(Weapon weapon)
		{
			/* has role have a same one on him 1:yes 0:no*/
			int have=0;
			/* role's level is too low ,cannot use it*/
			if(weapon.getLevel()>level)
			{
				System.out.println("你目前等级太低，无法使用！");
				try{
					Thread.currentThread().sleep(SLEEPTIME);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				return 1;
			}
			Iterator<Weapon> itr_weapon_t;
			Weapon iwtle = null;
			for(itr_weapon_t = equipmentList.iterator();itr_weapon_t.hasNext();)
			{
				iwtle = itr_weapon_t.next();
				/* if role have a same position one on his body*/
				if(iwtle.getPosition() == weapon.getPosition())
				{
					have = 1;
					break;
				}
			}
			if(have!=0)
			{
				putDownWeapon(iwtle);
			}
			equipmentList.add(weapon);
			attackPoint+=weapon.getAddAttackPoint();
			defendPoint+=weapon.getAddDefendPoint();
			return 1;
		};
		
		public int loseWeapon(Weapon weapon)
		{
			Iterator<Weapon> itr_weapon_t;
			Weapon iwtle = null;
			for(itr_weapon_t = weaponList.iterator();itr_weapon_t.hasNext();)
			{
				/*delete weapon*/
				iwtle = itr_weapon_t.next();
				if(iwtle.getID() == weapon.getID())
					{
						weaponList.remove(iwtle);
						break;
					}
			}
			return 1;
		};
		
		public int putDownWeapon(Weapon weapon)
		{
			Iterator<Weapon> itr_weapon_t;
			Weapon iwtle = null;
			for(itr_weapon_t = equipmentList.iterator();itr_weapon_t.hasNext();)
			{
				iwtle = itr_weapon_t.next();
				if(iwtle.getID() == weapon.getID())
					break;
			}
			attackPoint-= iwtle.getAddAttackPoint();
			defendPoint-= iwtle.getAddDefendPoint();
			//weaponList.push_back((*itr_weapon_t));
			equipmentList.remove(iwtle);
			return 1;
		};
		
		public boolean bagFull()
		{
			if((int)(weaponList.size())>=36)
				return true;
			else 
				return false;
		};
		public int loseMission(int id,MissionList msnlist)
		{
			Integer imle = null;
			for(itr_mission = missionList.iterator();itr_mission.hasNext();)
			{
				imle = itr_mission.next();
				if((msnlist.getMissionPtr(imle)).getID() == id)
				{
					/* first, add this mission-id to the missionDoneList.
					 * then we should delete this mission from list.*/
					/* Note: must check the id of the mission in missionDonelist,
					 * if same id ,we should ignore it,because when this mission is
					 * circle mission, it may add one more time,then some bad-thing
					 * will happened.
					 */
					int have_or_not = 0;
					Integer imdle = null;
					for(itr_mission_done = missionDoneList.iterator();itr_mission_done.hasNext();)
					{
						if(imdle == imle)
						{
							have_or_not = 1;
							break;
						}
					}
					if(have_or_not != 1)
						/* the we can add this mission to the list.*/
						missionDoneList.add(imle);
					missionList.remove(imle);
					return 1;
				}
			}
			return -1;	/* no such mission should be delete.*/
		};
		public int getMission(Mission mission)
		{
			missionList.add(mission.getID());
			return 0;
		};
		public int printInfo() throws IOException
		{
			Iterator<Weapon> itr_weapon_t;
			Weapon iwtle = null;
			String userinput;
			printHead();
			System.out.println("  角色名: ");
			System.out.println("  等级: ");
			System.out.println("  银两: ");
			System.out.println("  HP: ");
			System.out.println("  MP: ");
			System.out.println("  EXP: ");
			System.out.println("  攻击力: ");
			System.out.println("  防御力: ");
			System.out.println("装备: ");

			
			for(itr_weapon_t = equipmentList.iterator();itr_weapon_t.hasNext();)
			{
				iwtle.printListInfo();
			}
			for(int i=0;i<55;++i)
				System.out.print('-');
			System.out.println();
			System.out.println("1: 确定");
			while(true)
			{
				BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
				userinput = consolebr.readLine();
				if(userinput.codePointAt(0)=='1')
					break;
			}
			return 1;
			
		};
		
		public int Initialize()
		{
			name = "Killer";
			money = 1000;
			hitPoint = 100;
			curHitPoint = hitPoint;
			manaPoint = 20;
			curManaPoint= manaPoint;
			expPoint = 20;
			curExpPoint = 0;
			level = 1;
			attackPoint = 16;
			defendPoint = 3;
			change = 1;
			posX=0;
			posY=0;
			return 1;
		};
	}
	
	class Monster
	{
		int ID;
		String name;
		int attackPoint;
		int defendPoint;
		int hitPoint;
		int curHitPoint;
		int expPoint;
		int money;
		int lock;
		int level;
		int die;
		List<Integer> usList = new ArrayList<Integer>();
		List<Integer> wpnList = new ArrayList<Integer>();
		Iterator<Integer> itr_drop;//notice:I take itr_drop as the communal iterator for both usList&wpnList
		Integer idle = null;
		
		public String getName()
		{
			return name;
		};
		public int getID()
		{
			return ID;
		};
		public int getAttackPoint()
		{
			return attackPoint;
		};
		public int getDefendPoint()
		{
			return defendPoint;
		};
		public int getHitPoint()
		{
			return hitPoint;
		};
		public int getCurHitPoint()
		{
			return curHitPoint;
		};
		public int getExpPoint()
		{
			return expPoint;
		};
		public int getMoney()
		{
			return money;
		};
		public int getLock()
		{
			return lock;
		};
		public int getLevel()
		{
			return level;
		};
		public int getDie()
		{
			return die;
		};
		
		public int setName(String n)
		{
			name = n;
			return 0;
		};
		public int setID(int id)
		{
			ID = id;
			return ID;
		};
		public int setAttackPoint(int attack)
		{
			attackPoint = attack;
			return attackPoint;
		};
		public int setDefendPoint(int defend)
		{
			defendPoint = defend;
			return defendPoint;
		};
		public int setHitPoint(int hp)
		{
			hitPoint = hp;
			return hitPoint;
		};
		public int setCurHitPoint(int curhp)
		{
			curHitPoint = curhp;
			return curHitPoint;
		};
		public int setExpPoint(int exp)
		{
			expPoint = exp;
			return expPoint;
		};
		public int setMoney(int m)
		{
			die = 0;
			money = m;
			return money;
		};
		public int setLock(int lk)
		{
			lock = lk;
			return lock;
		};
		public int setLevel(int ll)
		{
			level = ll;
			return level;
		};
		public int setDie(int d)
		{
			die = d;
			return d;
		};
		public int addUsingList(int id)
		{
			Iterator<Integer> itr_check;
			Integer icle = null;
			for(itr_check = usList.iterator();itr_check.hasNext();)
			{
				icle = itr_check.next();
				if(id == icle)
					return 1;
			}
			usList.add(id);
			return 0 ;
		};
		public int addWeaponList(int id)
		{
			Iterator<Integer> itr_check;
			Integer icle = null;
			for(itr_check = wpnList.iterator();itr_check.hasNext();)
			{
				icle = itr_check.next();
				if(id == icle)
					return 1;
			}
			wpnList.add(id);
			return 0 ;
		};
		
		public int monsterClear()
		{
			this.wpnList.clear();
			this.usList.clear();
			return 0;
		};
		public int initUsingItr()
		{
			itr_drop = usList.iterator();
			return 0;
		};
		public int nextUsingItr()
		{
			if(itr_drop.hasNext())
			{
				idle = itr_drop.next();
				return 1;
			}
			return -1;
		};
		public int endUsingItr()
		{
			if(!itr_drop.hasNext())
				return 1;
			else
				return 0;
		};
		public int getCurUsingID()
		{
			if(itr_drop.hasNext())
				return idle;
			return -1;
		};
		public int initWeaponItr()
		{
			itr_drop = wpnList.iterator();
			return 0;
		};
		public int nextWeaponItr()
		{
			if(itr_drop.hasNext())
			{
				idle =itr_drop.next();
				return 1;
			}
			return -1;
		};
		public int endWeaponItr()
		{
			if(itr_drop.hasNext())
				return 1;
			else
				return 0;
		};
		public int getCurWeaponID()
		{
			if(itr_drop.hasNext())
				return idle;
			return -1;
		};
		public int changeCurHitPoint(final int chp)
		{
			curHitPoint += chp;
			/* check if curHitPoint is valid.*/
			if(curHitPoint < 0)
				curHitPoint = 0;
			if(curHitPoint > hitPoint)
				curHitPoint = hitPoint;
			return curHitPoint;
		};
		public int Dead()
		{
			if(curHitPoint<=0)
			{
				die = 1;
				return 1;
			}
			else
				return 0;
		};

		public Monster Generate(final int lvl)
		{
			if(lvl-level>3)
			{
				/* then set the hitpoint,attack,defend.*/
				hitPoint = (int)((float)(hitPoint)*((lvl-level)*10/100+1));
				attackPoint = (int)((float)(attackPoint)*((lvl-level)*20/100+1));
				defendPoint = (int)((float)(defendPoint)*((lvl-level)*20/100+1));
				expPoint = (int)((float)(expPoint)*((lvl-level)*10/100+1));
			}
			/* then set cur_XXX*/
			curHitPoint = hitPoint;
			return this;
		};
		public int New()
		{
			curHitPoint = hitPoint;
			return curHitPoint;
		};
		public int printDead()
		{
			System.out.println("  "+name+"被你杀死了");
			return 0;
		};
	}
	
	class MonsterList
	{
		List<Monster> monsterList = new ArrayList<Monster>();
		Iterator<Monster> itr_monster = monsterList.iterator();
		Monster imle = null;
		
		public int initMonsterItr()
		{
			itr_monster = monsterList.iterator();
			return 0;
		};
		public int nextMonsterItr()
		{
			if(itr_monster.hasNext())
			{
				imle = itr_monster.next();
				return 1;
			}
			return -1;
		};
		public int endMonsterItr()
		{
			if(!itr_monster.hasNext())
				return 1;
			else 
				return -1;
		};
		public Monster getCurMonster()
		{
			return imle;
		};
		public Monster getCurMonsterPtr()
		{
			if(itr_monster.hasNext())
				return imle;
			return null;
		};
		public int addMonster(Monster monster)
		{
			int id = monster.getID();
			for(itr_monster = monsterList.iterator();itr_monster.hasNext();)
			{
				imle = itr_monster.next();
				if(imle.getID() == id)
					return -1;
			}
			monsterList.add(monster);
			return 1;
		};
		public Monster getMonster(final int id)
		{
			for(itr_monster = monsterList.iterator();itr_monster.hasNext();)
			{
				imle = itr_monster.next();
				if(imle.getID() == id)
					return imle;
			}
			return monsterList.get(0);
		};
		public int setLock(final int id,final int flag)
		{
			if(flag != Global.MON_LOCK && flag != Global.MON_UNLOCK)
				return 0;
			Iterator<Monster> itr_monster_tmp;
			Monster imtle = null;
			for(itr_monster_tmp = monsterList.iterator();itr_monster_tmp.hasNext();)
			{
				imtle = itr_monster_tmp.next();
					if(imtle.getID() == id) /* find monster.*/
					{
						imtle.setLock(flag);
						return 1;
					}
			}
			return 0;
		};

	}

	abstract class Mission
	{
		//the circle mission
		int id;
		/* the name of the mission*/
		String name;
		/* the few words about the mission information*/
		String info;
		int state;
		int lock;
		int circle;
		int level;
		int gifttype;
		int unlockmap;
		//a List saving the id of mission gift (item)
		List<Integer> giftList = new ArrayList<Integer>();
		Iterator<Integer> itr_gift;
		Integer igle = null;
		String talkbefore;
		String talkbeing;
		String talkdone;
		
		public int getID()
		{
			return id;
		};
		public String getName()
		{
			return name;
		};
		public int getState()
		{
			return state;
		};
		public int getGiftType()
		{
			return gifttype;
		};
		/* get a pointer to the list of gift*/
		List<Integer> getGiftList()
		{
			return giftList;
		};
		public String getTalkBefore()
		{
			return talkbefore;
		};	//before msn
		public String getTalkBeing()
		{
			return talkbeing;
		};	//being msn
		public String getTalkDone()
		{
			return talkdone;
		};	//done msn
		public String getInfo()
		{
			return info;
		};	//a few words of the mission
		public int getCircle()
		{
			return circle;
		}; /* get the circle of mission*/
		public int getLock()
		{
			return lock;
		};
		public int getLevel()
		{
			return level;
		};
		public int getUnlockMap()
		{
			return unlockmap;
		};
		//-----must be override
		int printInfo(UsingList uslist,MonsterList mtlist,NPCList npclist) throws IOException
		{
			return 1;
		};
		
		int printListInfo()
		{
			return 1;
		}
		int setTalkBefore(String tbefore)
		{
			talkbefore = tbefore;
			return 1;
		};
		int setTalkBeing(String tbeing)
		{
			talkbeing = tbeing;
			return 1;
		};
		int setTalkDone(String tdone)
		{
			talkdone = tdone;
			return 1;
		};
		//int setMsnInfo(String );
		int pushGiftList(int id)
		{
			giftList.add(id);
			return 1;
		};
		int clearGiftList()
		{
			giftList.clear();
			return 0;
		};
		int setLock( int lk)
		{
			lock = lk;
			return lock;
		};
		int setUnlockMap(int id)
		{
			unlockmap = id;
			return id;
		};
		int setState( int st)
		{
			state = st;
			return state;
		};
		int setLevel( int l)
		{
			level = l;
			return level;
		};
		int setID( int id)
		{
			this.id = id;
			return this.id;
		};
		int setInfo(String information)
		{
			info = information;
			return 1;
		};
		int setName(String n)
		{
			name = n;
			return 1;
		};
		int setCircle( int cir)
		{
			circle = cir;
			return circle;
		};
		int setGiftType(int gt)
		{
			gifttype = gt;
			return gt;
		};
		void mission()
		{
			/* this function is the construction of the Mission class */
			this.id = 0;
			this.name = " no string ";
			this.info = " no string ";
			this.state = 0;
			this.lock = 0;
			this.circle = 1;
			this.level = 0;
			this.gifttype = 0;
			this.unlockmap = -1;
		};
		
		int talkMsn(Role role,MonsterList mntlist,SkillList sklist,MissionList msnlist,UsingList uslist,WeaponList wpnlist,MapList maplist,final int id_npc) throws Exception
		{
			String userinput;
			role.printHead();
			if(state== Global.MSN_BEFORE)
			{
				System.out.println(talkbefore);
				System.out.println("1:接受   2:拒绝");
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0)=='1')
						break;
					if(userinput.codePointAt(0)=='2')
						return 0;
				}
				if(userinput.codePointAt(0)=='1')
				{
					this.acceptMsn(role,mntlist,uslist,wpnlist,maplist);
					return 1;
				}
			}
			else if(state == Global.MSN_BEING)
			{
				isMsnCompleted(role,mntlist,id_npc);
				if(state == Global.MSN_DONE)
				{
					role.moveMission(this.id);
				}
				if(state==Global.MSN_BEING)
				{
					System.out.println(talkbeing);
					System.out.println("1:确定");
					while(true)
					{
						BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
						userinput = consolebr.readLine();
						if(userinput.codePointAt(0) == '0')
							return 1;
					}
				}
			}
			if(state==Global.MSN_DONE)
			{
				System.out.println(talkdone);
				System.out.println("1:确定   0:返回");
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0)=='1')
						break;
					if(userinput.codePointAt(0)=='0')
						return 1;
				}
				handOnMsn(role,msnlist);
				doneMsn(role,sklist,msnlist,uslist,wpnlist );
			}
			return 0;
		};
		int acceptMsn(Role role,MonsterList mntlist,UsingList uslist,WeaponList wpnlist,MapList maplist) throws Exception
		{
			if(unlockmap == -1)
				/* it means no map should be unlock.*/
				return 0;
			/* here would unlock some map.*/
			Map ptr_map =null;  //mapping
			this.state = Global.MSN_BEING;
			ptr_map = maplist.getMapPtr(unlockmap);
			ptr_map.setLock(Global.MAP_UNLOCK);
			if(ptr_map == null) /* if no map is found .*/
				return 0;
			return 1;
		};
		int isMsnCompleted(Role role,MonsterList mntlist,int d)
		{
			return 1;
		};
		int handOnMsn(Role role,MissionList mslist)
		{
			return 1;
		};
		int doneMsn(Role role,SkillList sklist,MissionList msnlist,UsingList uslist,WeaponList wpnlist) throws IOException
		{
			String userinput;
			if(gifttype == Global.MSN_EXPGIF)
			{
				itr_gift = giftList.iterator();
				igle = itr_gift.next();
				role.changeCurExpPoint(igle, 1, sklist);
				return 1;
			}
			else if(gifttype==Global.MSN_MSNGIF)
			{
				for(itr_gift = giftList.iterator();itr_gift.hasNext();)
				{
					igle = itr_gift.next();
					msnlist.setLock(igle,Global.MSN_UNLOCK);
				}
				
				System.out.println("你打开了新的任务！");
				try{
					Thread.currentThread().sleep(Global.SLEEPTIME);
					}
					catch(Exception e)
				{
						e.printStackTrace();
				}
			}
			else if (gifttype==Global.MSN_EQPGIF)
			{
				for(itr_gift = giftList.iterator();itr_gift.hasNext();)
				{
					igle = itr_gift.next();
					Weapon tmp = wpnlist.getWeapon(igle);
					tmp.generate(0);
					role.pickUp(tmp);
				}
			}
			else if(gifttype==Global.MSN_USEGIF)
			{
				int id;
				/* the number of using*/
				int num;
				for(itr_gift = giftList.iterator();itr_gift.hasNext();)
				{
					id = itr_gift.next();
					num = itr_gift.next();
					role.pickUp(uslist.getUsing(id), num);
				}
			}
			System.out.println("1:确定");
			while(true)
			{
				BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
				userinput = consolebr.readLine();
				if(userinput.codePointAt(0)=='1')
					break;
			}
			/* must set the  state if mission is circle*/
			if(circle== Global.MSN_CIRCLE)
				state= Global.MSN_BEFORE;
			return 1;
		};
		int canSeeMsn(Role role,NPC npc)
		{
			if((role.getLevel()>=level)&&(lock==Global.MSN_UNLOCK)&&(state!=Global.MSN_DONE))
				return 1;
			else
				return 0;
		};
		
		
	}
	
	class NPC
	{
		String name;
		int ID;
		String talk;
		int sellOrNot;
		List<Integer> missionIDList = new ArrayList<Integer>();
		Iterator<Integer> itr_mission_id;
		Integer imile = null;
		Iterator<Integer> itr_weapon_id;
		Integer iwile = null;
		List<Weapon> weaponList = new ArrayList<Weapon>();
		Iterator<Weapon> itr_weapon;
		Weapon iwle = null;
		Iterator<Integer> itr_using_id;
		List<Using> usingList = new ArrayList<Using>();
		Iterator<Using> itr_using;
		
		public int npcClear()
		{
			missionIDList.clear();
			return 0;
		};
		/* add id-of-mission to missionlist */
		public int addMission(int id)
		{
			Iterator<Integer> itr_check;
			Integer icle = null;
			for(itr_check = missionIDList.iterator();itr_check.hasNext();)
			{
				icle = itr_check.next();
				if(id == (icle))
					return 1;				
			}
			missionIDList.add(id);
			return 0;
		};
		/* this part is get()*/
		String getName()
		{
			return name;
		};
		public int getSellOrNot()
		{
			return sellOrNot;
		};
		public int getID()
		{
			int tmp = ID;
			return tmp;
		};
		String getTalk()
		{
			return talk;
		};
		/* this part is set()*/
		public int setName(String n)
		{
			name=n;
			return 1;
		};
		public int setSellOrNot(int sell)
		{
			sellOrNot=sell;
			return sellOrNot;
		};
		public int setID(int id)
		{
			ID=id;
			return ID;
		};
		public int setTalk(String tk)
		{
			talk=tk;
			return 1;
		};
		
		int Talk(Role role,UsingList uslist,WeaponList wpnlist,MissionList msnlist,SkillList sklist,MonsterList mntlist,MapList maplist) throws Exception
		{
			String userinput;
			while(true)
			{
				role.printHead();
				switch(sellOrNot)
				{
					case Global.SELLWEAPON:
					{
						System.out.println("[装备店]"+name+":");
						System.out.println("1:买装备 2:卖装备  0:退出");
						for(int i = 0;i<55;++i)
							System.out.print('-');
						System.out.println();
						while(true)
						{
							BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
							userinput = consolebr.readLine();
							if(userinput.codePointAt(0)=='1')
							{
								sellWeapon(role,wpnlist,uslist);
								break;
							}
							if(userinput.codePointAt(0)=='2')
							{
								roleSell(role);
								break;
							}
							if(userinput.codePointAt(0)=='0')
							{
								return 1;
							}
						}
						break;
					}
					case Global.SELLUSING:
					{
						System.out.println("[药店]"+name+":");
						System.out.println("1:买物品 2:卖物品   0:退出");
						for(int i = 0;i<55;++i)
							System.out.print('-');
						System.out.println();
						while(true)
						{
							BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
							userinput = consolebr.readLine();
							if(userinput.codePointAt(0)=='1')
							{
								sellUsing(role,uslist);
								break;
							}
							if(userinput.codePointAt(0)=='2')
							{
								roleSell(role);
								break;
							}
							if(userinput.codePointAt(0)=='0')
							{
								return 1;
							}
						}
						break;
					}
					case Global.SELLNOTHING:
					{
						speak(role,msnlist,uslist,wpnlist,mntlist,sklist,maplist);
						return 1;
					}
				}
				return 1;
			}
		};
		int sellWeapon(Role role,WeaponList wpnlist,UsingList uslist) throws IOException
		{
			int maxPrint=9;
			/* the number of weapon in current page*/
			int num_in_current_page;
			/* user input */
			String userinput;
			/* the index of page that print now.*/
			int page = 0;
			for(wpnlist.initItr();wpnlist.End() == 0;wpnlist.Next())
			{
				int temp = (wpnlist.getCurWeapon()).getLevel();
				if((temp<=role.getLevel()+3)&&(temp>=role.getLevel()-3))
				{
					weaponList.add(wpnlist.getCurWeapon());
					ListIterator<Weapon> ritr_weapon;
					Weapon riwle = null;
					ritr_weapon = weaponList.listIterator(weaponList.size()-1);//size - 1才是index
					riwle = ritr_weapon.next();
					riwle.generate(0);
				}
			}
			if((int)(weaponList.size())>maxPrint * 2)
			{
				int delete_i;
				int delete_num=(int)(weaponList.size())-maxPrint*2;
				for(int i=0;i<delete_num;++i)
				{
					delete_i = (int)(Math.random() * (weaponList.size()));
					itr_weapon=weaponList.iterator();
					for(int j=0;j<delete_i;++j)
						iwle = itr_weapon.next();
					weaponList.remove(iwle);
				}
			}
			int num_weapon = (int)(weaponList.size());
			while(true)
			{
				itr_weapon = weaponList.iterator();
				for(int i=0;i<maxPrint*page;++i)
					iwle = itr_weapon.next();
				for(int i = 0;itr_weapon.hasNext();++i)
				{
					iwle = itr_weapon.next();
					if(i<maxPrint)
					{
						System.out.print("  "+(i+1));
						iwle.printListInfo();
					}
				}
				page = 0;
				num_in_current_page = (int)(weaponList.size())-maxPrint*page;
				if(num_in_current_page>9)
					num_in_current_page=9;
				for(int i=0;i<55;++i)
					System.out.print('-');
				System.out.println();
				System.out.print("  1~"+num_in_current_page+":选择购买");
				if(page>0)
					System.out.print("Z"+"\n");
				if(page<1 && num_weapon>maxPrint)
					System.out.println("X"+":翻页  0:退出");
				
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0)-'0'>= 1 && userinput.codePointAt(0) -'0'<=num_in_current_page)
						break;
					if((userinput.codePointAt(0)=='X'||userinput.codePointAt(0)=='x')&&page<1&& num_weapon>maxPrint)
					{
						++page;
						break;
					}
					if((userinput.codePointAt(0)=='Z'||userinput.codePointAt(0)=='z')&&page>0)
					{
						--page;
						break;
					}
					if(userinput.codePointAt(0)=='0')
					{
						return 0;
					}
				}
				if(userinput.codePointAt(0)-'0'>=1 && userinput.codePointAt(0)-'0'<=num_in_current_page)
				{
					int temp=userinput.codePointAt(0)-'0';
					itr_weapon=weaponList.iterator();
					for(int i=1;i<=temp+page*maxPrint;++i)//i<=,or will disorder
						iwle = itr_weapon.next();
					role.printHead();
					if(iwle.printInfo(2)==0)
						continue;
					if(iwle.getMoney()>role.getMoney())
					{
						System.out.println("你米有足够的金钱!!");
						try{
							Thread.currentThread().sleep(1500);
							}
							catch(Exception e)
						{
								e.printStackTrace();
						}
						continue;
					}
					if(role.bagFull())
					{
						System.out.println("背包已满!!");
						try{
							Thread.currentThread().sleep(1500);
							}
							catch(Exception e)
						{
								e.printStackTrace();
						}
						continue;
					}
					role.changeMoney(-iwle.getMoney()*2);
					role.pickUp(iwle);
					weaponList.remove(iwle);
				}
			}
		};
		int sellUsing(Role role,UsingList uslist) throws IOException
		{
			int maxPrint=9;
			/* current page should be print */
			int page=0;
			/* number of using in current page, no begger than maxPrint*/
			int num_in_current_page;
			/* user input*/
			String userinput;
			/* show the using*/
			while(true)
			{
				role.printHead();
				/* num of using should be print in current page*/
				num_in_current_page=uslist.getSize()-page*maxPrint;
				if(num_in_current_page>9)
					num_in_current_page=9;
				/*get the itr_using in current page*/
			 	uslist.InitIrt();
				for(int i=0;i<page*maxPrint;++i)
					uslist.Next();
				for(int i=0;i<num_in_current_page;++i)
				{
					System.out.print("  "+(i+1)+":");
					(uslist.getCurUsing()).printListInfo();
				}
				for(int i=0;i<55;++i)
					System.out.print('-');
				System.out.println();
				System.out.print("  1~"+num_in_current_page+":选择购买");
				if(page>0)
					System.out.print("Z"+"\n");
				if((page+1)*maxPrint<uslist.getSize())
					System.out.println("X"+":翻页  0:退出");
				for(int i=0;i<55;++i)
					System.out.print('-');
				System.out.println();
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0)=='0')
						return 0;
					if((userinput.codePointAt(0)=='X'||userinput.codePointAt(0)=='x')&&(page+1)*maxPrint<uslist.getSize())
					{
						++page;
						break;
					}
					if((userinput.codePointAt(0)=='Z'||userinput.codePointAt(0)=='z')&&page>0)
					{
						--page;
						break;
					}		
					if(userinput.codePointAt(0)-'0'>=1&&userinput.codePointAt(0)-'0'<=num_in_current_page)
						break;
				}
				if(userinput.codePointAt(0)-'0'>=1&&userinput.codePointAt(0)-'0'<=num_in_current_page)
				{
					uslist.InitIrt();
					for(int i=1;i<page*maxPrint+userinput.codePointAt(0)-'0';++i)
						uslist.Next();
					role.printHead();
					if((uslist.getCurUsing()).printInfo(1) != -1)
						continue;
					/* the number user input*/
					int num_of_using=0;
					while(true)
					{
						System.out.println("请输入要购买的数量(1~999):");
						BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
						userinput = consolebr.readLine();
						int num_size=3;
						if((int)(userinput.length())<3)
							num_size=(int)(userinput.length());
						if(userinput.codePointAt(0)<'0'||userinput.codePointAt(0)>'9')
							continue;
						for(int i=0;i<num_size;++i)
						{
							if(userinput.codePointAt(i)<'0'||userinput.codePointAt(i)>'9')
								break;
							num_of_using=10*num_of_using+userinput.codePointAt(i)-'0';
						}
						break;
					}
					if((uslist.getCurUsing()).getMoney()*2*num_of_using>role.getMoney())
					{
						System.out.println("金钱不足！！");
						try{
							Thread.currentThread().sleep(1500);
							}
							catch(Exception e)
						{
								e.printStackTrace();
						}
						continue;
					}
					role.changeMoney(-(uslist.getCurUsing()).getMoney()*2*num_of_using);
					role.pickUp(uslist.getCurUsing(),num_of_using);
				}
			}
		};
		int roleSell(Role role) throws IOException
		{
			role.Sell();
			return 1;
		};
		
		int speak(Role role,MissionList msnlist,UsingList uslist,WeaponList wpnlist,MonsterList mntlist,SkillList sklist,MapList maplist) throws Exception
		{
			String userinput;
			while(true)
			{
				int num_msn_see = 0;
				int num_msn_done = 0;
				Mission p_msn = null;
				for(itr_mission_id = missionIDList.iterator();itr_mission_id.hasNext();)
				{
					imile = itr_mission_id.next();
					p_msn = msnlist.getMissionPtr(imile);
					if(p_msn.getState()==Global.MSN_DONE)
					{
						++num_msn_done;
					}
					if(p_msn.canSeeMsn(role,this)==1)
					{
							++num_msn_see;
					}
				}
				if(num_msn_done == 0 && num_msn_see==0)
				{
					System.out.println(name+":");
					System.out.println("  "+talk);
					System.out.println("1:确定");
					while(true)
					{
						BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
						userinput = consolebr.readLine();
						if(userinput.codePointAt(0) == '1')
							return 0;
					}
				}
				else if(num_msn_see==1)
				{
					for(itr_mission_id = missionIDList.iterator();itr_mission_id.hasNext();)
					{
						imile = itr_mission_id.next();
						p_msn = msnlist.getMissionPtr(imile);
						if(p_msn.canSeeMsn(role,this) == 1 && p_msn.getState()!= Global.MSN_DONE)
						{
							p_msn.talkMsn(role,mntlist,sklist,msnlist,uslist,wpnlist,maplist,ID);
							return 1;
						}
					}
				}
				else if(num_msn_see>1) 
				{
					int index_msn = 0;/* the index of msn list(can see,but not done)*/
					printListInfo();
					for(itr_mission_id = missionIDList.iterator();itr_mission_id.hasNext();)
					{
						imile = itr_mission_id.next();
						p_msn = msnlist.getMissionPtr(imile);
						if(p_msn.canSeeMsn(role,this) == 1 && p_msn.getState()!= Global.MSN_DONE)
						{
							++index_msn;
							System.out.println("  "+index_msn+":");
							System.out.println(p_msn.getName());
						}
					}
					System.out.println("1~"+index_msn+"选择  0:退出");
					while(true)
					{
						BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
						userinput = consolebr.readLine();
						if(userinput.codePointAt(0) == '0')
							return 0;
						if(userinput.codePointAt(0) - '0' > 0 && userinput.codePointAt(0) - '0' <= index_msn)
						{
							index_msn = userinput.codePointAt(0) - '0';
							break;
						}
					}
					
					int tmp_i;
					for(itr_mission_id = missionIDList.iterator(),tmp_i = 0;itr_mission_id.hasNext();)
					{
						imile = itr_mission_id.next();
						p_msn=msnlist.getMissionPtr(imile);
						if(p_msn.canSeeMsn(role,this) == 1 && p_msn.getState()!= Global.MSN_DONE)
						{
							++tmp_i;
							if(tmp_i == index_msn)
								break;
						}
					}
					p_msn.talkMsn(role,mntlist,sklist,msnlist,uslist,wpnlist,maplist,ID);
					return 1;
				}
				else if(num_msn_see==0&&num_msn_done==1)
				{
					for(itr_mission_id = missionIDList.iterator();itr_mission_id.hasNext();)
					{
						imile = itr_mission_id.next();
						p_msn=msnlist.getMissionPtr(imile);
						if(p_msn.canSeeMsn(role,this)==1 && p_msn.getState()==Global.MSN_DONE)
						{
							printListInfo();
							System.out.println(" "+p_msn.getTalkDone());
							System.out.println("1:确定");
							while(true)
							{
								BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
								userinput = consolebr.readLine();
								if(userinput.codePointAt(0)=='1')
									return 1;
							}
						}
					}
				}
				if(num_msn_see==0&&num_msn_done>1)
				{
					int all_num=0;/* all number of the done-mission*/
					for(itr_mission_id = missionIDList.iterator();itr_mission_id.hasNext();)
					{
						imile = itr_mission_id.next();
						p_msn = msnlist.getMissionPtr(imile);
						if(p_msn.canSeeMsn(role,this) != 0 && p_msn.getState() == Global.MSN_DONE)
							++all_num;
					}
					/* here all_num is the random of mission*/
					all_num = (int)(Math.random() * all_num) + 1;
					/* find the random mission*/
					itr_mission_id = missionIDList.iterator();
					while(all_num-- != 0)
					{
						for(itr_mission_id = missionIDList.iterator();itr_mission_id.hasNext();)
						{
							imile = itr_mission_id.next();
							p_msn = msnlist.getMissionPtr(imile);
							if(p_msn.canSeeMsn(role,this)==1 && p_msn.getState()== Global.MSN_DONE)
							{
								printListInfo();
								System.out.println("  "+p_msn.getTalkDone());
								System.out.println("1:确定");
								while(true)
								{
									BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
									userinput = consolebr.readLine();
									if(userinput.codePointAt(0) =='1')
										return 1;
								}
							}
						}
					}
				}
			}
		};
		int printListInfo()
		{
			System.out.println("[NPC]"+name);
			switch(sellOrNot)
			{
			case Global.SELLWEAPON:
				System.out.println("---武器店");
				break;
			case Global.SELLUSING:
				System.out.println("---药店");
				break;
			case Global.SELLNOTHING:
				break;
			}
			return 1;
		};
	}

	class NPCList
	{
		LinkedList<NPC> npcList = new LinkedList<NPC>();
		Iterator<NPC> itr_npc;
		NPC inle = null;
		
		public int getNPCNum()
		{
			return (int)(npcList.size());
		};
		NPC getNPC(int id)
		{
			for(itr_npc = npcList.iterator();itr_npc.hasNext();)
			{
				inle = itr_npc.next();
				if(inle.getID()==id)
					return  inle;
			}
				
			return npcList.getFirst();
		};
		/* return a pointer to id-npc*/
		NPC getNPCPtr(int id)
		{
			for(itr_npc = npcList.iterator();itr_npc.hasNext();)
			{
				inle = itr_npc.next();
				if(inle.getID() == id)
					return inle;
			}
			return null;
		};
		/* add a npc to the list of npc*/
		int pushNPC(NPC npc)
		{
			int id = npc.getID();
			for(itr_npc = npcList.iterator();itr_npc.hasNext();)
			{
				inle = itr_npc.next();
				if(inle.getID() == id)
					return 0;
			}
			npcList.add(npc);
			return 1;
		};
		
		int InitItr()
		{
			itr_npc = npcList.iterator();
			return 1;
		};
		int End()
		{
			if(!itr_npc.hasNext())
				return 1;
			return 0;
		};
		NPC getCurNpc()
		{
			return inle;
		};
	}
	
	class Map
	{
		String name;
		int ID;
		/* 
		 * the position of the map ,and if two map are close ,then those
		 * two map are close.
		 */
		int pos_x;
		int pos_y;
		/* those four pointers is the next map,it will be set
		 * to NULL when an object of map init.
		 */
		Map east;
		Map west;
		Map north;
		Map south;
		
		int lock;
		int level;
		List<Integer>NPCIDList = new ArrayList<Integer>();
		Iterator<Integer>itr_npcid;
		Integer inile = null;
		List<Integer>monsterIDList = new ArrayList<Integer>();
		Iterator<Integer>itr_monsterid;
		Integer imile = null;
		List<Integer>monsterIDMustList = new ArrayList<Integer>();
		
		public Map()
		{
			this.east = null;
			this.west = null;
			this.north = null;
			this.south = null;
		};
		
		public int setName(String n)
		{
			name = n;
			return 1;
		};
		public int setID(int id)
		{
			ID = id;
			return ID;
		};
		public int setLevel(int l)
		{
			level = l;
			return level;
		};
		public int setLock(int lk)
		{
			lock = lk;
			return lock;
		};
		public int setPosX(int x)
		{
			pos_x = x;
			return x;
		};
		public int setPosY(int y)
		{
			pos_y = y;
			return y;
		};
		public Map setEast(Map pmap)
		{
			east = pmap;
			return east;
		};
		public Map setWest(Map pmap)
		{
			west = pmap;
			return west;
		};
		public Map setNorth(Map pmap)
		{
			north = pmap;
			return north;
		};
		public Map setSouth(Map pmap)
		{
			south = pmap;
			return south;
		};
		public int addNPC(int npcid)
		{
			for(itr_npcid = NPCIDList.iterator();itr_npcid.hasNext();)
			{
				inile = itr_npcid.next();
				if(inile == npcid)
					return 0;
			}
			NPCIDList.add(npcid);
			return npcid;
		};
		public int addMonster(int monsterid)
		{
			for(itr_monsterid = monsterIDList.iterator();itr_monsterid.hasNext();)
			{
				imile = itr_monsterid.next();
				if(imile == monsterid)
					return 0;
			}
			monsterIDList.add(monsterid);
			return monsterid;
		};
		public int addMonsterMust(int monsterid)
		{
			for(itr_monsterid = monsterIDMustList.iterator();itr_monsterid.hasNext();)
			{
				imile = itr_monsterid.next();
				if(imile == monsterid)
					return 0;
			}
			monsterIDMustList.add(monsterid);
			return monsterid;
		};
		public int Clear()
		{
			this.NPCIDList.clear();
			this.monsterIDList.clear();
			this.monsterIDMustList.clear();
			return 0;
		};
		/*
		 * this part is the GetXXX().
		 */
		public String getName()
		{
			return name;
		};
		public int getID()
		{
			return ID;
		};
		public int getLevel()
		{
			return level;
		};
		public int getLock()
		{
			return lock;
		};
		public int getPosX()
		{
			return pos_x;
		};
		public int getPosY()
		{
			return pos_y;
		};
		public Map getEast()
		{
			return east;
		};
		public Map getWest()
		{
			return west;
		};
		public Map getNorth()
		{
			return north;
		};
		public Map getSouth()
		{
			return south;
		};
		
		public int getMonsterID(final int num)
		{
			itr_monsterid = monsterIDList.iterator();
			for(int i = 1;i<num;++i)
				imile = itr_monsterid.next();
			return imile;
		};
		public int initItrNPCID()
		{
			if(!NPCIDList.isEmpty())
			{
				itr_npcid = NPCIDList.iterator();
				inile = itr_npcid.next();
			}
			return 1;
		};
		public int getCurNPCID()
		{
			return inile;
		};
		public int nextItrNPC()
		{
			if(itr_npcid.hasNext())
				inile = itr_npcid.next();
			return 1;
		};
		public int endNPCID()
		{
			if(!NPCIDList.isEmpty())
			{
				if(!itr_npcid.hasNext())
					return 1;
				else
				return 0;
			}
			return 2;
		};
		public int initItrMonsterID()
		{
			itr_monsterid = monsterIDList.iterator();
			if(!monsterIDList.isEmpty())
				imile = itr_monsterid.next();
			return 1;
		};
		public int getCurMonsterID()
		{
			return imile;
		};
		public int nextItrMonsterID()
		{
			if(itr_monsterid.hasNext())
			{
				imile = itr_monsterid.next();
			}
			return 1;
		};
		public int endMonsterID()
		{
			if(!itr_monsterid.hasNext())
				return 1;
			else
				return 0;
		};
		public int initItrMonsterIDMust()
		{
			itr_monsterid = monsterIDMustList.iterator();
			return 1;
		};
		public int getCurMonsterIDMust()
		{
			return imile;
		};
		public int nextItrMonsterIDMust()
		{
			if(itr_monsterid.hasNext())
			{
				imile = itr_monsterid.next();
				return 1;
			}
			else
				return 0;
		};
		public int endMonsterIDMust()
		{
			if(!itr_monsterid.hasNext())
				return 1;
			else
				return 0;
		};
	}
	
	class MapList
	{
		LinkedList<Map> mapList = new LinkedList<Map>();
		Iterator<Map> itr_map;
		Map imple;
		
		int curMapID;
		Map curMap = new Map();
		
		List<Integer> npc = new ArrayList<Integer>();
		Iterator<Integer> itr_npc;
		Integer inle = null;
		
		List<Monster> monster = new ArrayList<Monster>();
		Iterator<Monster> itr_monster;
		Monster imle = null;
		
		int mapLinked(Map pmap_1,Map pmap_2)
		{
			int tag[] = new int[1000];
			/* initialize  array.*/
			for(int i=0;i<1000;++i)
				tag[i] = 0;
			/* check of map .*/
			int link_or_not = mapDfs(pmap_1,pmap_2,tag);
			if(link_or_not == 1) /* yes they are linked.*/
				return 1;
			return 0;
		};
		int mapDfs(Map pmap_st,Map pmap_des,int[] arr)
		{
			arr[pmap_st.getID()] = 1;
			Map pmap_tmp;
			if(pmap_st.getID() == pmap_des.getID())
				return 1;
			if(pmap_st.getNorth() != null) /* north*/
			{
				pmap_tmp = pmap_st.getNorth();
				if(arr[pmap_tmp.getID()] == 0)
					if(pmap_tmp.getLock() == Global.MAP_UNLOCK) /* this map is unlocked.*/
					{
						if(mapDfs(pmap_tmp,pmap_des,arr)!=0) /*if find,it will return 1.*/
							return 1;
					}
			}
			if(pmap_st.getSouth() != null) /* south.*/
			{
				pmap_tmp = pmap_st.getSouth();
				if(arr[pmap_tmp.getID()] == 0)
					if(pmap_tmp.getLock() == Global.MAP_UNLOCK) /* this map is unlocked.*/
					{
						if(mapDfs(pmap_tmp,pmap_des,arr)!=0) /*if find,it will return 1.*/
							return 1;
					}
			}
			if(pmap_st.getWest() != null) /* south.*/
			{
				pmap_tmp = pmap_st.getWest();
				if(arr[pmap_tmp.getID()] == 0)
					if(pmap_tmp.getLock() == Global.MAP_UNLOCK) /* this map is unlocked.*/
					{
						if(mapDfs(pmap_tmp,pmap_des,arr) != 0) /*if find,it will return 1.*/
							return 1;
					}
			}
			if(pmap_st.getEast() != null) /* south.*/
			{
				pmap_tmp = pmap_st.getEast();
				if(arr[pmap_tmp.getID()] == 0)
					if(pmap_tmp.getLock() == Global.MAP_UNLOCK) /* this map is unlocked.*/
					{
						if(mapDfs(pmap_tmp,pmap_des,arr) != 0) /*if find,it will return 1.*/
							return 1;
					}
			}
			return 0;
		};
		
		public int setCurMap()
		{
			curMap = mapList.getFirst();
			System.out.println(curMap.getName());
			return 0;
		};
		public int mapClear()
		{
			this.npc.clear();
			return 0;
		};
		public int pushMap(Map map)
		{
			mapList.add(map);
			return 0;
		};
		public int getMonsterNum()
		{
			return (int)(monster.size());
		};
		public int getNPCNum()
		{
			return (int)(npc.size());
		};
		public int getNPCID(final int num)
		{
			itr_npc = npc.iterator();
			for(int i = 1;i<=num;++i)
				inle = itr_npc.next();
			return inle;
		};
		public Monster getMonster(final int num)
		{
			itr_monster = monster.iterator();
			for(int i = 1;i<num;++i)
				imle = itr_monster.next();
			return imle;
		};
		public int deleteMonster(final int num)
		{
			itr_monster = monster.iterator();
			for(int i = 1;i<num;++i)
				imle = itr_monster.next();
			monster.remove(imle);
			return 1;
		};
		public int Generate(Role role,MonsterList mntlist)
		{
			npc.clear();
			monster.clear();
			curMap.initItrNPCID();
			if(curMap.endNPCID()!=2)
			{
				while(curMap.endNPCID() == 0)
				{
					npc.add(curMap.getCurNPCID());
					curMap.nextItrNPC();
				}
			}

			curMap.initItrMonsterIDMust();
			while(curMap.endMonsterIDMust() == 0)
			{
				if((mntlist.getMonster(curMap.getCurMonsterIDMust()).getLock() == Global.MON_UNLOCK))
					monster.add(mntlist.getMonster(curMap.getCurMonsterIDMust()).Generate(0));
				curMap.nextItrMonsterIDMust();
			}
			/* at last, generate monster random,and the sum of npc and monster
			 * shouldn't be larger than 9. And those several monsters are randomized
			 * when role win one,then this one should be delete from monster 
			 * list.
			 */
			/* this data is the number that monster can have*/
			int most_monster = 9 - (int)(npc.size()) - (int)(monster.size());
			int num_monster=0;
			if(most_monster > 6)
				{
					num_monster=6-(int)(Math.random()*3); /* 4~6*/
				}
			else
				{
					num_monster=most_monster-(int)(Math.random()*3);
					/* here num_monst may be <0,we should set to >0.*/
					if(num_monster<0)
						num_monster=0;
				}
			int all_monster=0;/* all monster in this map*/
			int[] rand_monster = new int[10];/* the monster that random:1~all_monster.*/
			for(int i=0;i<(rand_monster.length);++i)
				rand_monster[i]=-1;
			for(curMap.initItrMonsterID();curMap.endMonsterID() == 0;curMap.nextItrMonsterID())
			{
				++all_monster;
			}
			if(all_monster != 0)
			{
				for(int i =0;i<num_monster;++i)
				{
					int temp = (int)(Math.random()*all_monster);
					curMap.initItrMonsterID();
					for(int j = 0;j<temp;++j)
						curMap.nextItrMonsterID();
					if((mntlist.getMonster(curMap.getCurMonsterIDMust())).getLock() == Global.MON_UNLOCK)
					monster.add(mntlist.getMonster(curMap.getCurMonsterID()).Generate(role.getLevel()));
				}
			}
			return 0;
		};
	
		

		public int setPointer()
		{
			Iterator<Map> itr_map_i;
			Map imile = null;
			Iterator<Map> itr_map_j;
			Map imjle = null;
			int pos_x_i;
			int pos_y_i;
			int pos_x_j;
			int pos_y_j;
			for(itr_map_i = mapList.iterator();itr_map_i.hasNext();)
			{
				imile = itr_map_i.next();
				for(itr_map_j = mapList.iterator();itr_map_j.hasNext();)
				{
					imjle = itr_map_j.next();
					if(imile.getID() == imjle.getID())
						continue;
					pos_x_i = imile.getPosX();
					pos_y_i = imile.getPosY();
					pos_x_j = imjle.getPosX();
					pos_y_j = imjle.getPosY();
					
					if((pos_x_i == pos_x_j)&&(pos_y_i-pos_y_j == 1))
					{
						/* then set the pointers of them 
					     * j i
					  	 */
						imile.setWest(imjle);
						imjle.setEast(imile);
					}
					else if((pos_x_i == pos_x_j)&&(pos_y_j - pos_y_i == 1))
					{
						/* i j
						 */
						imile.setEast(imjle);
						imjle.setWest(imile);
					}
					else if((pos_y_i == pos_y_j)&&(pos_x_i - pos_x_j ==1))
					{
						/* j
						 * i
						 */
						imile.setNorth(imjle);
						imjle.setSouth(imile);
					}
					else if((pos_y_i == pos_y_j)&&(pos_x_j - pos_x_i == 1))
					{
						/* i
						 * j
						 */
						imile.setSouth(imjle);
						imjle.setNorth(imile);
					}
				}
			}
			return 0;
		};
		public int changeMap(int pos,Role role) throws InterruptedException
		{
			switch(pos)
			{
				case Global.MAP_EAST:
					if(curMap.getEast() == null)
						return 0;
					if(curMap.getEast().getLock() == Global.MAP_LOCK)
						return 0;
					if(curMap.getEast().getLevel() > role.getLevel())
					{
						System.out.println("=====你的等级太低=====");
						try{
							Thread.currentThread().sleep(Global.SLEEPTIME);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						return 0;
					}
					curMap = curMap.getEast();
					return 1;
				case Global.MAP_WEST:
					if(curMap.getWest() == null)
						return 0;
					if(curMap.getWest().getLock() == Global.MAP_LOCK)
						return 0;
					if(curMap.getWest().getLevel() > role.getLevel())
					{
						System.out.println("=====你的等级太低=====");
						try{
							Thread.currentThread().sleep(Global.SLEEPTIME);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						return 0;
					}
					curMap = curMap.getWest();
					return 1;
				case Global.MAP_NORTH:
					if(curMap.getNorth() == null)
						return 0;
					if(curMap.getNorth().getLock() == Global.MAP_LOCK)
						return 0;
					if(curMap.getNorth().getLevel() > role.getLevel())
					{
						System.out.println("=====你的等级太低=====");
						try{
							Thread.currentThread().sleep(Global.SLEEPTIME);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						return 0;
					}
					curMap = curMap.getNorth();
					return 1;
				case Global.MAP_SOUTH:
					if(curMap.getSouth() == null)
						return 0;
					if(curMap.getSouth().getLock() == Global.MAP_LOCK)
						return 0;
					if(curMap.getSouth().getLevel() > role.getLevel())
					{
						System.out.println("=====你的等级太低=====");
						try{
							Thread.currentThread().sleep(Global.SLEEPTIME);
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						return 0;
					}
					curMap = curMap.getSouth();
					return 1;
				default:
					return 0;
			}
		};
		public int printMap(NPCList npclist,MonsterList monsterlist)
		{
			for(itr_monster = monster.iterator();itr_monster.hasNext();)
			{
				imle = itr_monster.next();
				if(imle.getLock() == Global.MON_LOCK)
				{
					monster.remove(imle);
					itr_monster = monster.iterator();
				}
			}
			System.out.println("["+curMap.getName()+"]你遇到了:");
			int index = 1;
			for(itr_npc = npc.iterator();itr_npc.hasNext();)
			{
				inle = itr_npc.next();
				System.out.println("  "+(index++)+":");
				(npclist.getNPCPtr(inle)).printListInfo();
			}
			for(itr_monster = monster.iterator();itr_monster.hasNext();)
			{
				imle = itr_monster.next();
				System.out.println("  "+(index++)+":");
				System.out.println(imle.getName());
			}
			if(curMap.getNorth() != null && curMap.getNorth().getLock() == Global.MAP_UNLOCK)
			{
				System.out.println("[w北]--["+curMap.getNorth().getName()+" ]");
			}
			if(curMap.getSouth() != null && curMap.getSouth().getLock() == Global.MAP_UNLOCK)
			{
				System.out.println("[s南]--["+curMap.getSouth().getName()+" ]");
			}
			if(curMap.getWest() != null && curMap.getWest().getLock() == Global.MAP_UNLOCK)
			{
				System.out.println("[a西]--["+curMap.getWest().getName()+" ]");
			}
			if(curMap.getEast() != null && curMap.getEast().getLock() == Global.MAP_UNLOCK)
			{
				System.out.println("[d东]--["+curMap.getEast().getName()+" ]");
			}
			return 0;

		};
		public int showMap() throws IOException
		{
			for(int k=0;k<20;k++)
				System.out.println();
			int a[][]= new int[5][5];

			int cur_x = curMap.getPosX()-2;
			int cur_y = curMap.getPosY()-2;
			Map map_ptr_tmp = null;
			
			for(int i=0;i<5;++i)
				for(int j=0;j<5;++j)
				{
					map_ptr_tmp = getMap(cur_x+i,cur_y+j);
					if(map_ptr_tmp == null)/* no map here.*/
					{
						a[i][j]=0;
						continue;
					}
					else/* there's a map here.*/
					{
						/* if this map is the role stand on.
						 * we should mark it!*/
						if(map_ptr_tmp.getPosX() == curMap.getPosX()&&map_ptr_tmp.getPosY() == curMap.getPosY())
						{
							a[i][j] = 2;
							continue;
						}
						/* to check if role can see it .*/
						if(mapLinked(curMap,map_ptr_tmp) != 0)
							/* yes role can see it.Don't worry!*/
							a[i][j] = 1;
						else/* role can not see map,set it to 0.*/
							a[i][j] = 0;
					}
				}
			for(int i=0;i<45;++i)
				if(i != 22)
					System.out.print('-');
				else
					System.out.println("地图信息");
			for( int i = 0;i<5;++i)
			{
				System.out.println("  ");
				for(int j = 0;j<5;++j)
					if(a[i][j]==1)
						System.out.println("* ");
					else if(a[i][j] == 2)
						System.out.println("Q ");
					else
						System.out.println("  ");
			}
			System.out.println("0:返回");
			String userinput;
			while(true)
			{
				BufferedReader consoleBr = new BufferedReader (new InputStreamReader(System.in));
				userinput = consoleBr.readLine();
				if(userinput.codePointAt(0) == '0')
					break;
			}
			return 0;
		};
		public Map getMap(final int x,final int y)
		{
			for(itr_map = mapList.iterator();itr_map.hasNext();)
			{
				imple = itr_map.next();
				if(imple.getPosX() == x && imple.getPosY() == y)
					return imple;
			}
			return null;
		};
		
		public Map getMapPtr(int id)
		{
			Iterator<Map> itr_map_tmp;
			Map imtle = null;
			for(itr_map_tmp = mapList.iterator();itr_map_tmp.hasNext();)
			{
				imtle = itr_map_tmp.next();
				if(imtle.getID() == id)
					return imtle;
			}
			return null;
		};
	}
	
	class Battle
	{
		int whosTurnWhenAttack;
		int roletoMonster;
		int monstertoRole;
		List<Using> usingDropList = new ArrayList<Using>();
		Iterator<Using> itr_using;
		Using iule = null;
		
		List<Weapon>weaponDropList = new ArrayList<Weapon>();
		Iterator<Weapon> itr_weapon;
		Weapon iwle;
		
		public Battle(){};
		public int inBattle(Role role,Monster monster,MonsterList monsterlist) throws InterruptedException, IOException
		{
			whosTurnWhenAttack = Global.BTL_ROLETURN;
			int temp;
			monster.New();
			while(true)
			{
				role.printHead();
				System.out.println("战斗中 ......");
				System.out.println("   "+role.getName()+":");
				temp=(int)(((float)(role.getCurHitPoint())/(float)(role.getHitPoint()))*40);
				for(int i_hit=0;i_hit<40;++i_hit)
					if(i_hit<temp) 
						System.out.print('|');
					else    
						System.out.print('.');
				System.out.println('('+role.getCurHitPoint()+')'+"   "+monster.getName()+":");
				temp=(int)(((float)(monster.getCurHitPoint())/(float)(monster.getHitPoint()))*40);
				for(int i_hit=0;i_hit<40;++i_hit)
					if(i_hit<temp) 
						System.out.print('|');
					else 
						System.out.print('.');
				System.out.println('('+monster.getCurHitPoint()+')');
				
				if(whosTurnWhenAttack==Global.BTL_ROLETURN)
				{ 
					roletoMonster = Fight(role,monster);
					/* user give up using some thing or skill*/
					if(roletoMonster==0)
						continue;
					if(roletoMonster<0) 
					{ // you didn't hit the monster ,you miss!
						System.out.println("you miss!!");
					}
					else
					{
						monster.changeCurHitPoint(-roletoMonster);
						System.out.println("你对"+monster.getName()+"造成了"+roletoMonster+"点伤害");
					}
					try{
						Thread.currentThread().sleep(Global.SLEEPTIME);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}					whosTurnWhenAttack=Global.BTL_MONSTERTURN;
					//if the monster had been killed by role
					if(monster.Dead() != 0)
					{
						/* we should check the monsterlist to set the die.
						 * why? becuase "this monster" is the true,becuase it
						 * has been generated.
						 */
						Monster itr_monster_set;
						for(monsterlist.initMonsterItr(); ; monsterlist.nextMonsterItr())
						{
							itr_monster_set = monsterlist.getCurMonsterPtr();
							if(itr_monster_set.getID() == monster.getID())
							{
								/* we found it ! now we set the die=1 */
								itr_monster_set.setDie(1);
								break;
							}
							if(itr_monster_set == null)
								break;
						}
						int monster_money = (int)(monster.getMoney()*((float)(Math.random()*100+300)/350));
						role.changeMoney(monster.getMoney());
						break;
					}
				}
				else
				{   
					monstertoRole= getDemagePoint2(monster,role,getAttackPoint(monster));
					if(monstertoRole<0)
					{ //monster didn't hit role ,he miss!
						System.out.println(monster.getName()+"打空了！");
					}
					else
					{
						role.changeCurHitPoint(-monstertoRole,1);
						System.out.println(monster.getName()+"对你造成了"+monstertoRole+"伤害");
					}
					try{
						Thread.currentThread().sleep(Global.SLEEPTIME);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					whosTurnWhenAttack=Global.BTL_ROLETURN;
						// if the role had been killed by the monster!
						if(role.Dead())  
							break;
				}//end of else 
			}//end of while
			return 1;
		};
		public int afterBattle(Role role,Monster monster, UsingList usinglist,WeaponList weaponlist,SkillList skilllist) throws IOException
		{
			String userinput;
			if(monster.Dead() != 0)
			{
				monster.printDead();
				role.changeCurExpPoint(monster.getExpPoint(),1,skilllist);
				generateDroped(monster,usinglist,weaponlist);;
				System.out.println("1:确认  0:继续");
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					/* user will pick up the thing*/
					if(userinput.codePointAt(0)=='1')
						break;
					/* user will not pick up the thing */
					if(userinput.codePointAt(0)=='0')
						break;
				}
				/* 
				 * this part mean the user to decide select the thing 
				 * to pick up which droped by the monster or not
				 * 1: select 0: give up selecting 
				 */
				if(userinput.codePointAt(0)=='1')
					pickUp(role);
			}
			else if(role.Dead())/* the role had been dead -----unlucky!*/
				role.newLife();
			return 1;
		};
		public int getAttackPoint(Role role)
		{
			int temp_1,temp_2;
			temp_1=(int)(Math.random()*(role.getAttackPoint()*2/5));
			temp_2=role.getAttackPoint()*4/5+temp_1;
			return temp_2;
		};
		public int getAttackPoint(Monster monster)
		{
			int temp_1,temp_2;
			temp_1=(int)(Math.random()*(monster.getAttackPoint()*2/5));
			temp_2=monster.getAttackPoint()*4/5+temp_1;
			return (int)(temp_2);
		};
		public int getDemagePoint1(Role role, Monster monster,final int attackPoint)
		{
			int rollMissOrNot;
			int missNum;
			int tempDemagePoint;
		    rollMissOrNot=(int)Math.random()/100;
		    missNum=(monster.getLevel()-role.getLevel())*5;
			missNum=missNum>40?40:missNum;
			if(rollMissOrNot<missNum) /* monster didn't hit role ,he miss!*/
				return -1;
			else
			{
				tempDemagePoint=getAttackPoint(role);
				tempDemagePoint=tempDemagePoint-monster.getDefendPoint();
				if(tempDemagePoint<=0)
					tempDemagePoint=1;
				return tempDemagePoint;
			}
		};
		public int getDemagePoint2(Monster monster,Role role,final int attackPoint)
		{
			int rollMissOrNot;
			int missNum;
			int tempDemagePoint;
			rollMissOrNot= (int)(Math.random()/100);
			missNum=(role.getLevel()-monster.getLevel())*5;
			missNum=missNum>30?30:missNum;
			if(rollMissOrNot<missNum) /* monster didn't hit role ,he miss!*/
				return -1;
			else
			{
				tempDemagePoint=getAttackPoint(monster);
				tempDemagePoint=tempDemagePoint-role.getDefendPoint()*2;
				if(tempDemagePoint<=0)
					tempDemagePoint=1;
				return tempDemagePoint;
			}
		};
		
		public int generateDroped(Monster monster,UsingList uslist,WeaponList wpnlist)
		{
			usingDropList.clear();
			weaponDropList.clear();
			int num_drop_one = 0;
			for(monster.initUsingItr();monster.endUsingItr()==0;monster.nextUsingItr())
				++num_drop_one;
			if(num_drop_one > 0)
			{
				int number = 0;
				int temp = (int)Math.random()*100;
				if(temp<40)
					number = 0;
				else if(temp<60)
					number = 1;
				else if(temp<80)
					number = 2;
				else 
					number = 3;
				/* the rand Index of using in monster*/
				int index_using = 0;
				while(number-- != 0)
				{
					index_using = (int)Math.random()*num_drop_one+1;
					int i=1;
					for(monster.initUsingItr();monster.endUsingItr() == 0;monster.nextUsingItr(),++i)
					{
					/* now, get it*/
						if(i==index_using)
						{
							usingDropList.add(uslist.getUsing(monster.getCurUsingID()));
							break;
						}
					}
				}
				num_drop_one = 0;
				for(monster.initWeaponItr();monster.endWeaponItr() == 0;monster.nextWeaponItr())
				{
					++num_drop_one;
				}
				if(num_drop_one > 0)
				{
					int index_weapon = (int)Math.random()*num_drop_one + 1;
					int i = 0;
					for(monster.initWeaponItr(),i=1;monster.endWeaponItr() == 0;monster.nextWeaponItr(),++i)
					{
						if(i == index_weapon)/* yes, it does .*/
						{
							Weapon p_weapon_temp = wpnlist.getWeapon(monster.getCurWeaponID());
							(p_weapon_temp).generate(1);
							weaponDropList.add(p_weapon_temp);
							break;
						}
					}
				}
			}
			return 0;
		};
		
		public int pickUp(Role role) throws IOException
		{
			String userinput;
			int label;
			while(true)
			{
				role.printHead();
				if(usingDropList.isEmpty() && weaponDropList.isEmpty())
					break;
				System.out.println("怪物掉落了：");
				if(!usingDropList.isEmpty())
					for(itr_using=usingDropList.iterator(),label=1;itr_using.hasNext();++label)
					{
						iule = itr_using.next();
						System.out.println("---"+label+":"+iule.getName());
					}
						
				if(!weaponDropList.isEmpty())
					for(itr_weapon=weaponDropList.iterator(),label=(int)(usingDropList.size())+1;itr_weapon.hasNext();++label)
					{
						iwle = itr_weapon.next();
						System.out.println("---"+label+":");
						iwle.printListInfo();
					}
					/* print some thing*/
				System.out.println("--------------------please select--------------------");
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					if(userinput.codePointAt(0)>'0'&&userinput.codePointAt(0)<=(int)(usingDropList.size()+weaponDropList.size())+'0')
						break;
					if(userinput.codePointAt(0)=='B'||userinput.codePointAt(0)=='b')
						break;
					if(userinput.codePointAt(0) == '0')
						break;
				}
				if(userinput.codePointAt(0)=='b'||userinput.codePointAt(0)=='B')
				{
					role.printBag(1);
					break;
				}
				else if(userinput.codePointAt(0)-'0'<=(int)(usingDropList.size())) /* pick up using*/
				{
					int i=0;
					for(i=1,itr_using=usingDropList.iterator();i<userinput.codePointAt(0)-'0';iule = itr_using.next(),++i)
						;
					role.pickUp(iule);
					usingDropList.remove(iule);
				}
				else  /* pick up the weapon*/
		 		{
					if(!role.bagFull())
					{
						int i=0;
						for(itr_weapon = weaponDropList.iterator(),i=1;i<(int)(userinput.codePointAt(0)-'0'-usingDropList.size());iwle = itr_weapon.next(),++i)
							;
						role.pickUp(iwle); 
						weaponDropList.remove(iwle);
					}
					else /* the beg is full ,please the role to check it*/
						role.printBag(1);
				}
			}
			return 1;
		};
		
		public int Fight(Role role,Monster monster) throws IOException
		{
			String userinput;
			int temp_1,temp_2;
			char select;
			System.out.println("1:普通攻击 k:使用技能 b:查看物品");
			while(true)
			{
				BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
				userinput = consolebr.readLine();
				select = (char) userinput.codePointAt(0);
				if(select=='B'||select=='b'||select=='1'||select=='k'||select=='K'||select=='r'||select=='R')
					break;
			}
			if(select=='B'||select=='b')
			{
				temp_1=role.printBag(1);
				if(temp_1==0)/* do nothing*/
					;
				else 
					return 0;
			}
			else if(select=='k'||select=='K') /* turn to the skill of role*/
			{
				temp_2=role.printSkill(1);
				if(temp_2==0)/*do nothing*/
					;
				else if(temp_2==-1)/* use the heal skill*/
				{
					whosTurnWhenAttack=Global.BTL_MONSTERTURN;
					return 0;
				}
				else
					return temp_2;
			}
			else 
			{
				temp_1=getAttackPoint(role);
				temp_2=getDemagePoint1(role,monster,temp_1);
				return temp_2;
			}
			return 0;
		};
	}
	
	class GameControl
	{
		int init_time;
		List<MissionBoss> missionBossList = new ArrayList<MissionBoss>();
		List<MissionNpc> missionNpcList = new ArrayList<MissionNpc>();
		List<MissionUsing> missionUsingList = new ArrayList<MissionUsing>();
		int init_mission(MissionList missionlist)
		{
			MissionUsing mission_use_tmp = new MissionUsing();
			mission_use_tmp.useList.clear();
			mission_use_tmp.useNum.clear();
			mission_use_tmp.setID(400);
			mission_use_tmp.setName("孟三的生意");
			mission_use_tmp.setInfo("帮助孟三找到他需要的东西，他说好会有报酬的。");
			mission_use_tmp.setState(0);
			mission_use_tmp.setLock(1);
			mission_use_tmp.setCircle(0);
			mission_use_tmp.setLevel(1);
			mission_use_tmp.setGiftType(3);
			mission_use_tmp.setTalkBefore("少侠，我这生意是我们家的命，可是现在缺几样东西,'\' 能帮我找来么，我一定会答谢你的！");
			mission_use_tmp.setTalkBeing("啊？还没找到啊，求你了，千万要帮帮我啊....");
			mission_use_tmp.setTalkDone("上次的事情真是太感谢你了，'\'要不然我这养家糊口的生意怕是做不成了.....");
			mission_use_tmp.pushGiftList(1);
			mission_use_tmp.pushGiftList(5);
			mission_use_tmp.pushGiftList(4);
			mission_use_tmp.pushGiftList(5);
			mission_use_tmp.useList.add(11);
			mission_use_tmp.useNum.add(3);
			mission_use_tmp.useNum.add(3);
			mission_use_tmp.useList.add(16);
			mission_use_tmp.setUnlockMap(-1);
			missionUsingList.add(mission_use_tmp);

			mission_use_tmp = new MissionUsing();
			mission_use_tmp.useList.clear();
			mission_use_tmp.useNum.clear();
			mission_use_tmp.setID(401);
			mission_use_tmp.setName("沈公子的拜托");
			mission_use_tmp.setInfo("沈公子好像十分需要一些铁矿。");
			mission_use_tmp.setState(0);
			mission_use_tmp.setLock(1);
			mission_use_tmp.setCircle(1);
			mission_use_tmp.setLevel(1);
			mission_use_tmp.setGiftType(3);
			mission_use_tmp.setTalkBefore("哎呀，我需要打造一把上好剑鞘，可是少了铁，你方便的话'\'帮我找点吧。");
			mission_use_tmp.setTalkBeing("只要铁够了，剑鞘就会很快炼制成的。");
			mission_use_tmp.setTalkDone("炼制剑鞘的事就多谢你了，日后定会报答！");
			mission_use_tmp.pushGiftList(2);
			mission_use_tmp.pushGiftList(4);
			mission_use_tmp.useList.add(12);
			mission_use_tmp.useNum.add(5);
			mission_use_tmp.setUnlockMap(-1);
			missionUsingList.add(mission_use_tmp);
			
			mission_use_tmp = new MissionUsing();
			mission_use_tmp.useList.clear();
			mission_use_tmp.useNum.clear();
			mission_use_tmp.setID(406);
			mission_use_tmp.setName("景阳的爱好");
			mission_use_tmp.setInfo("听说景阳特别喜欢收集稀奇古怪的东西，比如虎牙、狼牙等。");
			mission_use_tmp.setState(0);
			mission_use_tmp.setLock(1);
			mission_use_tmp.setCircle(0);
			mission_use_tmp.setLevel(5);
			mission_use_tmp.setGiftType(2);
			mission_use_tmp.setTalkBefore("哈哈，我最喜欢古怪的东西，如果你能给我些虎牙，我会把我的剑送给你！");
			mission_use_tmp.setTalkBeing("还没收集到啊，我都快等的急死了。");
			mission_use_tmp.setTalkDone("虎牙你都能弄到，真厉害，希望那把剑能给你带来好运！");
			mission_use_tmp.pushGiftList(101);
			mission_use_tmp.useList.add(17);
			mission_use_tmp.useNum.add(10);
			mission_use_tmp.setUnlockMap(-1);
			missionUsingList.add(mission_use_tmp);
			
			mission_use_tmp = new MissionUsing();
			mission_use_tmp.useList.clear();
			mission_use_tmp.useNum.clear();
			mission_use_tmp.setID(411);
			mission_use_tmp.setName("威严的邢捕头");
			mission_use_tmp.setInfo("邢捕头非常痛恨怪物，以至于非要收集怪物的脚去焚烧才解心头只恨...");
			mission_use_tmp.setState(0);
			mission_use_tmp.setLock(1);
			mission_use_tmp.setCircle(0);
			mission_use_tmp.setLevel(10);
			mission_use_tmp.setGiftType(2);
			mission_use_tmp.setTalkBefore("年轻人，听说你身法不错，那你给我去砍些怪物的蹄子给我，我会给你报酬的！");
			mission_use_tmp.setTalkBeing("不把怪物的蹄子烧干净，我誓不罢休！");
			mission_use_tmp.setTalkDone("上次的事情做得不错，希望你继续努力，为百姓除害！");
			mission_use_tmp.pushGiftList(107);
			mission_use_tmp.useList.add(14);
			mission_use_tmp.useNum.add(5);
			mission_use_tmp.setUnlockMap(-1);
			missionUsingList.add(mission_use_tmp);

			MissionNpc mission_npc_tmp = new MissionNpc();
			mission_npc_tmp.clearGiftList();
			mission_npc_tmp.setID(402);
			mission_npc_tmp.setName("传送加急的信件(一)");
			mission_npc_tmp.setInfo("孟三好像非常迫切的去送信，去帮他吧。");
			mission_npc_tmp.setLock(1);
			mission_npc_tmp.setState(0);
			mission_npc_tmp.setLevel(3);
			mission_npc_tmp.setCircle(0);
			mission_npc_tmp.setGiftType(1);
			mission_npc_tmp.pushGiftList(405);
			mission_npc_tmp.setTalkBefore("我的哥哥孟良在后山挺长时间了，能帮我把这封信送过去么，我们已经好长'\'时间没联系过了。");
			mission_npc_tmp.setTalkBeing("信送到了么？哥哥他怎么样？还好吧？");
			mission_npc_tmp.setTalkDone("少侠，你真是个热心肠的人啊。");
			mission_npc_tmp.setUnlockMap(604);
			mission_npc_tmp.setNpcID1(303);
			mission_npc_tmp.setNpcID2(307);
			mission_npc_tmp.setMapID(0);
			missionNpcList.add(mission_npc_tmp);
			
			mission_npc_tmp = new MissionNpc();
			mission_npc_tmp.clearGiftList();
			mission_npc_tmp.setID(405);
			mission_npc_tmp.setName("传送加急的信件(二)");
			mission_npc_tmp.setInfo("孟三的哥哥孟良找他弟弟好像有什么事，这封信件好像很重要吧。");
			mission_npc_tmp.setLock(0);
			mission_npc_tmp.setState(0);
			mission_npc_tmp.setLevel(3);
			mission_npc_tmp.setCircle(0);
			mission_npc_tmp.setGiftType(0);
			mission_npc_tmp.pushGiftList(100);
			mission_npc_tmp.setTalkBefore("少侠，拜托你，把这封信送给我弟弟，后山有重要的事发生了。");
			mission_npc_tmp.setTalkBeing("希望弟弟看到后不要担心啊。");
			mission_npc_tmp.setTalkDone("送信的事情谢谢你了，你真是个好心人！");
			mission_npc_tmp.setUnlockMap(-1);
			mission_npc_tmp.setNpcID1(307);
			mission_npc_tmp.setNpcID2(303);
			mission_npc_tmp.setMapID(0);
			missionNpcList.add(mission_npc_tmp);
			
			mission_npc_tmp = new MissionNpc();
			mission_npc_tmp.clearGiftList();
			mission_npc_tmp.setID(404);
			mission_npc_tmp.setName("踏上征途");
			mission_npc_tmp.setInfo("村里的事情已经平息了，你已经积累了足够的经验和技巧，那么，就向着更广阔的'\'世界前进吧！去洛阳找邢捕头，他会告诉你些什么的。");
			mission_npc_tmp.setLock(0);
			mission_npc_tmp.setState(0);
			mission_npc_tmp.setLevel(5);
			mission_npc_tmp.setCircle(0);
			mission_npc_tmp.setGiftType(1);
			mission_npc_tmp.pushGiftList(407);
			mission_npc_tmp.setTalkBefore("镇上的事情多亏大侠帮助了，我代表本镇谢谢大侠了，听说洛阳那边出现了'\'怪物，大侠要是有心就去那看看吧，那儿的人们遭受怪物折磨很久了。你往东部'\'走就行了，邢捕头会和你交谈的。");
			mission_npc_tmp.setTalkBeing("哎...天下苍生，还要被怪物折磨多久啊....");
			mission_npc_tmp.setTalkDone("小伙子，娄知县就是说得你呀，挺精神的。这就是洛阳了，不过这'\'里怪物横生，你还是小心点吧。");
			mission_npc_tmp.setUnlockMap(609);
			mission_npc_tmp.setNpcID1(305);
			mission_npc_tmp.setNpcID2(309);
			mission_npc_tmp.setMapID(0);
			missionNpcList.add(mission_npc_tmp);
			
			mission_npc_tmp = new MissionNpc();
			mission_npc_tmp.clearGiftList();
			mission_npc_tmp.setID(410);
			mission_npc_tmp.setName("兴奋的宋书生");
			mission_npc_tmp.setInfo("洛阳的宋书生说如果帮他找到太白仙人，他就一定会中举的，不知能不能找到。");
			mission_npc_tmp.setLock(1);
			mission_npc_tmp.setState(0);
			mission_npc_tmp.setLevel(10);
			mission_npc_tmp.setCircle(0);
			mission_npc_tmp.setGiftType(2);
			mission_npc_tmp.pushGiftList(103);
			mission_npc_tmp.setTalkBefore("啊，昨晚梦见一个神秘的太白仙人，说如果白天能还见到他，我就会中举！如果你'\'看到了，一定要给我说一声啊！");
			mission_npc_tmp.setTalkBeing("如果真的找到这位仙人，我寒窗苦读十几年就要有回报了！");
			mission_npc_tmp.setTalkDone("啊？你真的找到了？真不敢相信！书生在这写过侠客了！");
			mission_npc_tmp.setUnlockMap(-1);
			mission_npc_tmp.setNpcID1(311);
			mission_npc_tmp.setNpcID2(308);
			mission_npc_tmp.setMapID(0);
			missionNpcList.add(mission_npc_tmp);

			MissionBoss mission_boss_tmp = new MissionBoss();
			mission_boss_tmp.clearGiftList();
			mission_boss_tmp.setID(403);
			mission_boss_tmp.setName("为祸人间的山妖");
			mission_boss_tmp.setInfo("瓦当镇的后山深处有一山妖，危害镇民，传闻此山妖厉害无比，一般人不敢靠近。");
			mission_boss_tmp.setLock(1);
			mission_boss_tmp.setState(0);
			mission_boss_tmp.setLevel(3);
			mission_boss_tmp.setCircle(0);
			mission_boss_tmp.setGiftType(1);
			mission_boss_tmp.pushGiftList(404);
			mission_boss_tmp.setUnlockMap(607);
			mission_boss_tmp.pushBossID(851);
			mission_boss_tmp.setTalkBefore("哎...后山的山妖把镇民们的田地都破坏了，还抓走了小孩，现在'\'都没人去后山了，希望少侠能帮我们铲除掉山妖啊。");
			mission_boss_tmp.setTalkBeing("少侠不要太勉强啊，实在不行就算了吧，我们不会怪你的...");
			mission_boss_tmp.setTalkDone("少侠真是挽救了我们这个镇啊，我们要拿什么来报答少侠呢。");
			missionBossList.add(mission_boss_tmp);
			
			mission_boss_tmp = new MissionBoss();
			mission_boss_tmp.clearGiftList();
			mission_boss_tmp.setID(407);
			mission_boss_tmp.setName("岸上妖孽");
			mission_boss_tmp.setInfo("洛阳东北部悬崖有妖孽，需要小心应付。");
			mission_boss_tmp.setLock(1);
			mission_boss_tmp.setState(0);
			mission_boss_tmp.setLevel(5);
			mission_boss_tmp.setCircle(0);
			mission_boss_tmp.setGiftType(1);
			mission_boss_tmp.pushGiftList(408);
			mission_boss_tmp.setUnlockMap(-1);
			mission_boss_tmp.pushBossID(852);
			mission_boss_tmp.setTalkBefore("城东北部有个非常厉害的秃鹫，专吃人心，如果你能除掉它，你在'\'洛阳城的威望必会大幅提升。");
			mission_boss_tmp.setTalkBeing("还没除掉吗，原来你的实力不过如此.....");
			mission_boss_tmp.setTalkDone("你居然能杀掉秃鹫，年轻人果然不可貌相啊....");
			missionBossList.add(mission_boss_tmp);
			
			mission_boss_tmp = new MissionBoss();
			mission_boss_tmp.clearGiftList();
			mission_boss_tmp.setID(408);
			mission_boss_tmp.setName("不断失踪的渔民");
			mission_boss_tmp.setInfo("洛阳城河边的渔民最近一个接一个的失踪，去看看发生什么事了吧。");
			mission_boss_tmp.setLock(0);
			mission_boss_tmp.setState(0);
			mission_boss_tmp.setLevel(8);
			mission_boss_tmp.setCircle(0);
			mission_boss_tmp.setGiftType(1);
			mission_boss_tmp.pushGiftList(409);
			mission_boss_tmp.setUnlockMap(617);
			mission_boss_tmp.pushBossID(853);
			mission_boss_tmp.setTalkBefore("最近河边的渔民不断失踪，听说是有海怪作怪，你能杀掉秃鹫，说明'\'你是个优秀的侠客，你能去调查清楚此事吗？");
			mission_boss_tmp.setTalkBeing("河边的渔民还在一个一个地失踪，这该怎么办！");
			mission_boss_tmp.setTalkDone("啊！这次多亏大侠帮助，渔民方能脱身，大侠好身手啊！");
			missionBossList.add(mission_boss_tmp);
			
			mission_boss_tmp = new MissionBoss();
			mission_boss_tmp.clearGiftList();
			mission_boss_tmp.setID(409);
			mission_boss_tmp.setName("最终的决战");
			mission_boss_tmp.setInfo("随着妖魔不断铲除，只剩下最后的魔界首领，去林中城找到首领，铲除掉它，'\'天下方能太平.....");
			mission_boss_tmp.setLock(0);
			mission_boss_tmp.setState(0);
			mission_boss_tmp.setLevel(11);
			mission_boss_tmp.setCircle(0);
			mission_boss_tmp.setGiftType(3);
			mission_boss_tmp.pushGiftList(3);
			mission_boss_tmp.pushGiftList(20);
			mission_boss_tmp.setUnlockMap(619);
			mission_boss_tmp.pushBossID(854);
			mission_boss_tmp.setTalkBefore("年轻人果然身手不凡，竟能为我城百姓立下如此大功，但仍有一事相求，'\'魔界的首领在洛阳南部，至今无人知晓，如果大侠能够到达首领所在地'\'并且除掉它，大侠功绩必将流传千古，永垂不朽！");
			mission_boss_tmp.setTalkBeing("少侠，魔界首领一事就拜托了！");
			mission_boss_tmp.setTalkDone("少侠的功德天下苍生必将永远铭记！希望少侠还能够继续为民造福！");
			missionBossList.add(mission_boss_tmp);
			
			Iterator<MissionBoss> itr_msn_boss;
			MissionBoss imble = null;
			Iterator<MissionNpc> itr_msn_npc;
			MissionNpc imnle = null;
			Iterator<MissionUsing> itr_msn_using;
			MissionUsing imule = null;
			
			for(itr_msn_boss = missionBossList.iterator();itr_msn_boss.hasNext();)
			{
				imble = itr_msn_boss.next();
				missionlist.addMission(imble);
			}
			for(itr_msn_npc = missionNpcList.iterator();itr_msn_npc.hasNext();)
			{
				imnle = itr_msn_npc.next();
				missionlist.addMission(imnle);
			}
			for(itr_msn_using = missionUsingList.iterator();itr_msn_using.hasNext();)
			{
				imule = itr_msn_using.next();
				missionlist.addMission(imule);
			}
			return 0;
		};
		int init_skill(SkillList skilllist)
		{
			Skill skill_tmp = new Skill();
			skill_tmp.setName("强力攻击");
			skill_tmp.setID(200);
			skill_tmp.setLevel(1);
			skill_tmp.setPercent(0);
			skill_tmp.setManaNeed(5);
			skill_tmp.setAttackPoint(10);
			skill_tmp.setHealPoint(0);
			skill_tmp.setDefendPoint(0);
			skilllist.pushSkill(skill_tmp);

			skill_tmp.setName("自愈");
			skill_tmp.setID(201);
			skill_tmp.setLevel(3);
			skill_tmp.setPercent(1);
			skill_tmp.setManaNeed(7);
			skill_tmp.setAttackPoint(0);
			skill_tmp.setHealPoint(10);
			skill_tmp.setDefendPoint(0);
			skilllist.pushSkill(skill_tmp);

			skill_tmp.setName("地震摇山");
			skill_tmp.setID(202);
			skill_tmp.setLevel(5);
			skill_tmp.setPercent(0);
			skill_tmp.setManaNeed(12);
			skill_tmp.setAttackPoint(20);
			skill_tmp.setHealPoint(0);
			skill_tmp.setDefendPoint(0);
			skilllist.pushSkill(skill_tmp);

			skill_tmp.setName("晕锤");
			skill_tmp.setID(203);
			skill_tmp.setLevel(7);
			skill_tmp.setPercent(0);
			skill_tmp.setManaNeed(15);
			skill_tmp.setAttackPoint(50);
			skill_tmp.setHealPoint(0);
			skill_tmp.setDefendPoint(0);
			skilllist.pushSkill(skill_tmp);

			skill_tmp.setName("双倍打击");
			skill_tmp.setID(204);
			skill_tmp.setLevel(9);
			skill_tmp.setPercent(1);
			skill_tmp.setManaNeed(20);
			skill_tmp.setAttackPoint(200);
			skill_tmp.setHealPoint(0);
			skill_tmp.setDefendPoint(0);
			skilllist.pushSkill(skill_tmp);

			skill_tmp.setName("医疗波");
			skill_tmp.setID(205);
			skill_tmp.setLevel(11);
			skill_tmp.setPercent(1);
			skill_tmp.setManaNeed(30);
			skill_tmp.setAttackPoint(0);
			skill_tmp.setHealPoint(80);
			skill_tmp.setDefendPoint(0);
			skilllist.pushSkill(skill_tmp);

			return 0;
		};
		int init_monster(MonsterList monsterlist)
		{
			Set<StMonster> st_monster = new LinkedHashSet<StMonster>();
			int[] temp_a = {1,4,16,-1};
			int[] temp_b = {105,100,-1,-1};
			StMonster e = new StMonster("恶狼",800,5,5,8,2,1,0,0,1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{11,16,2,3};
			temp_b = new int[]{100,104,-1,-1};
			e = new StMonster("仓眼狼",  801,   8,   7,   10,   3,0,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{9,11,16,3};
			temp_b = new int[]{102,103,-1,-1};
			e = new StMonster("疯狗",    802,   10,  9,   10,   2,2,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{2,3,12,11};
			temp_b = new int[]{150,106,101,-1};
			e = new StMonster("僵尸",    803,   10,  9,   12,   3,1,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{2,5,8,9};
			temp_b = new int[]{104,110,130,-1};
			e = new StMonster("瘟疫僵尸", 804,   12,  10,  13,  3,2,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{2,3,12,11};
			temp_b = new int[]{150,106,101,-1};
			e = new StMonster("僵尸",    803,   10,  9,   12,   3,1,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{6,15,21,3};
			temp_b = new int[]{131,110,106,101};
			e = new StMonster("骷髅战士", 805,   15,   12,  18,  4,2,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{15,25,23,4};
			temp_b = new int[]{131,170,111,160};
			e = new StMonster("骷髅法师", 806,   18,   12,  13,  5,1,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{21,2,3,-1};
			temp_b = new int[]{113,-1,-1,-1};
			e = new StMonster("恶魔之虫",  807,  20,   18,  20,   6,3,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{17,14,11,4};
			temp_b = new int[]{151,111,170,141};
			e = new StMonster("毒蝎虎",   808,   24,   22,  24,   8,2,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{8,9,2,4};
			temp_b = new int[]{171,132,112,160};
			e = new StMonster("铁甲猪",   809,   30,   28,  25,   7,8,   0,   0,   1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{3,4,6,14};
			temp_b = new int[]{161,112,141,-1};
			e = new StMonster("独眼腐尸",  810,   35,  32,   30,   10,2,   0,   0,  1,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{2,3,4,25};
			temp_b = new int[]{150,160,171,-1};
			e = new StMonster("邪恶的山妖", 851,   100, 100,   50,   10, 2, 1,   0,  3,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{3,6,14,21};
			temp_b = new int[]{132,111,141,-1};
			e = new StMonster("独眼秃鹫",  852,   400,  220,   160,   25,6,   1,   0,5,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{3,6,21,25};
			temp_b = new int[]{161,107,171,113};
			e = new StMonster("盐湖水怪",  853,   600,  300,   260,   30,10,   1,   0,  8,temp_a,temp_b);
			st_monster.add(e);
			temp_a = new int[]{3,6,1,25};
			temp_b = new int[]{113,171,-1,-1};
			e = new StMonster("魔界首领",  854,   1000,  550,  380,   50,15,   1,   0,   10,temp_a,temp_b);
			st_monster.add(e);
			
			//将Set<StMonster>内的对象全部放入monsterList
			Monster monster_tmp;
			Iterator<StMonster> itr_st_monster;
			for(itr_st_monster = st_monster.iterator();itr_st_monster.hasNext();)
			{
				monster_tmp = new Monster();
				StMonster stmle = itr_st_monster.next();
				monster_tmp.monsterClear();
				monster_tmp.setName(stmle.name);
				monster_tmp.setID(stmle.ID);
				monster_tmp.setMoney(stmle.money);
				monster_tmp.setExpPoint(stmle.exppoint);
				monster_tmp.setHitPoint(stmle.hitpoint);
				monster_tmp.setAttackPoint(stmle.attackpoint);
				monster_tmp.setDefendPoint(stmle.defendpoint);
				monster_tmp.setLock(stmle.lock);
				monster_tmp.setDie(stmle.die);
				monster_tmp.setLevel(stmle.level);
				for(int j = 0;j < 4; ++j)
					if(stmle.arr_using[j] >= 0)
						monster_tmp.addUsingList(stmle.arr_using[j]);
					else 
						break;
				for(int j = 0;j < 4; ++j)
					if(stmle.arr_weapon[j] >= 0)
						monster_tmp.addWeaponList(stmle.arr_weapon[j]);
					else 
						break;
				monsterlist.addMonster(monster_tmp);
			}
			return 0;
		};
		int init_role(Role role)
		{
			role.Initialize();
			return 0;
		};
		int init_using(UsingList usinglist)
		{
			Set<StUsing> st_using = new LinkedHashSet<StUsing>();
			StUsing e;
			e = new StUsing("止血草",  1,  15,  0,  10,   0,   0);
			st_using.add(e);
			e = new StUsing("药瓶",    2,  50,  0,  25,   0,   1);
			st_using.add(e);
			e = new StUsing("补血药",  3,  40,  0,  25,   0,   0);
			st_using.add(e);
			e = new StUsing("小补气瓶", 4,  10,  0,  0,   15,   0);
			st_using.add(e);
			e = new StUsing("补气瓶",  5,  40,  0,  0,   40,   0);
			st_using.add(e);
			e = new StUsing("回魔药",  6,  80,  0,  0,   40,   1);
			st_using.add(e);
			e = new StUsing("狼皮",   7,  80,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("矿石",   9,  50,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("铁粉",   8,  23,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("水晶",   15,  150,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("虎牙",   17,  90,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("铁矿",   12,  0,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("兽皮",   11,  100,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("怪脚",   14,  160,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("戒指",   21,  200,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("金属",   16,  220,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("木炭",   23,  190,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("恶魔的碎片", 24,  400,  0,  0,   0,   0);
			st_using.add(e);
			e = new StUsing("天使之翼", 25,  360,  0,  0,   0,   0);
			st_using.add(e);
			
			//把Set<StUsing>里的对象全部放入usingList
			Using using_tmp;
			Iterator<StUsing> itr_st_using;
			StUsing stule = null;
			for(itr_st_using = st_using.iterator();itr_st_using.hasNext();)
			{
				using_tmp = new Using();
				stule = itr_st_using.next();
				using_tmp.setName(stule.name);
				using_tmp.setMoney(stule.money);
				using_tmp.setID(stule.ID);
				using_tmp.setAddHitPoint(stule.addhitpoint);
				using_tmp.setAddManaPoint(stule.addmanapoint);
				using_tmp.setPercentOrNot(stule.percentornot);
				using_tmp.setNumber(stule.number);
				usinglist.pushUsing(using_tmp);
			}
			return 0;
		};
		int init_weapon(WeaponList weaponlist)
		{
			Set<StWeapon> st_weapon = new LinkedHashSet<StWeapon>();
			StWeapon e;
			e = new StWeapon("布帽子",  105,   105,   0,   1,   1,   2,   0 );
			st_weapon.add(e);
			e = new StWeapon("锂矿帽",   150,   150,   0,   4,   1,   1,   0 );
			st_weapon.add(e);
			e = new StWeapon("旧铜矿诺曼头盔",   151,   270,   0,   6,   2,   2,   0 );
			st_weapon.add(e);
			e = new StWeapon("布衣",   102,   100,   0,   2,   0,   3,   1 );
			st_weapon.add(e);
			e = new StWeapon("战士之甲", 130,  120,   0,   4,   0,   4,   1 );
			st_weapon.add(e);
			e = new StWeapon("绿龙服",   131,  250,   0,   6,   0,   7,   1 );
			st_weapon.add(e);
			e = new StWeapon("黑镇月铠甲", 132, 350,   0,   9,   1,   12,  1 );
			st_weapon.add(e);
			e = new StWeapon( "马裤",   103,   150,   0,   2,   0,   3,   2 );
			st_weapon.add(e);
			e = new StWeapon("褐板裤",   140,   120,   0,   4,   0,   3,   2 );
			st_weapon.add(e);
			e = new StWeapon("银军官裤", 141,   260,   0,   7,   1,   6,   2 );
			st_weapon.add(e);
			e = new StWeapon("草鞋",   104,   110,   0,   2,   0,   2,   3 );
			st_weapon.add(e);
			e = new StWeapon("斗士鞋",   160,   140,   0,   4,   0,   2,   3 );
			st_weapon.add(e);
			e = new StWeapon("红雪鞋",   161,   230,   0,   7,   0,   4,   3 );
			st_weapon.add(e);
			e = new StWeapon("工地手套", 106,  200,   0,   3,   1,   1,   4 );
			st_weapon.add(e);
			e = new StWeapon("旧合金手套", 170,  125,   0,   4,   3,   1,   4 );
			st_weapon.add(e);
			e = new StWeapon("黑暗影手套", 171,  235,   0,   6,   4,   3,   4 );
			st_weapon.add(e);
			e = new StWeapon("霸王剑",  107,  1000,   0,   10,   20,   5,   5 );
			st_weapon.add(e);
			e = new StWeapon("木剑",   100,   50,    0,    1,    3,   0,   5 );
			st_weapon.add(e);
			e = new StWeapon("铁剑",   101,   150,   0,   1,   6,   0,   5 );
			st_weapon.add(e);
			e = new StWeapon("细剑",     110,  220,   0,   4,   8,   1,   5 );
			st_weapon.add(e);
			e = new StWeapon("长矛枪",   111,  280,   0,   6,   11,   2,   5 );
			st_weapon.add(e);
			e = new StWeapon("武士刀",   111,  320,   0,   8,   15,   3,   5 );
			st_weapon.add(e);
			e = new StWeapon("树灵之剑", 112,  380,   0,   10,   20,   6,   5 );
			st_weapon.add(e);
			e = new StWeapon("高原之剑", 113,  480,   0,   12,   25,   8,   5 );
			st_weapon.add(e);
			
			//把Set<StWeapon>内对象全部放入weaponList
			Weapon weapon_tmp;
			int _num_of_weapon = st_weapon.size();
			Iterator<StWeapon> itr_st_weapon;
			StWeapon stwle = null;
			for(itr_st_weapon = st_weapon.iterator();itr_st_weapon.hasNext();)
			{
				weapon_tmp = new Weapon();
				stwle = itr_st_weapon.next();
				weapon_tmp.setName(stwle.name);
				weapon_tmp.setID(stwle.ID);
				weapon_tmp.setMoney(stwle.money);
				weapon_tmp.setColor(stwle.colour);
				weapon_tmp.setAddAttackPoint(stwle.addattackpoint);
				weapon_tmp.setAddDefendPoint(stwle.adddefendpoint);
				weapon_tmp.setLevel(stwle.level);
				weapon_tmp.setPosition(stwle.position);
				weaponlist.pushWeapon(weapon_tmp);
			}
			return 0;
		};
		int init_npc(NPCList npclist)
		{
			Set<StNpc> st_npc = new LinkedHashSet<StNpc>();
			StNpc e;
			int[] temp_a;
			temp_a= new int[]{401,-1,-1,-1,-1};
			e = new StNpc("沈万山","哎，村子里的村民受妖怪折磨太久了.....",300, 0, temp_a);
			st_npc.add(e);
			temp_a= new int[]{-1,-1,-1,-1,-1};
			e = new StNpc("赵大娘","看看有什么需要的，这里卖的很便宜啊。",	301,   1,  temp_a);
			st_npc.add(e);
			temp_a= new int[]{-1,-1,-1,-1,-1};
			e = new StNpc("欧阳铁匠","我这可以打造天下最好的武器。",	302, 2,  temp_a);
			st_npc.add(e);
			temp_a= new int[]{400,402,405,-1,-1};
			e = new StNpc("孟三","哥哥去后山很久了，不知到发现了什么还不回来...",303,   0,   temp_a);
			st_npc.add(e);
			temp_a= new int[]{-1,-1,-1,-1,-1};
			e = new StNpc("秦女","我相公去山穴采药，不慎被山妖抓住，至今了无音讯，我该如何是好啊...5555555",304,   0, temp_a);
			st_npc.add(e);
			temp_a= new int[]{404,-1,-1,-1,-1};
			e = new StNpc("娄知县","只有彻底消灭了魔怪，天下方能安生啊....",305, 0,  temp_a);
			st_npc.add(e);
			temp_a= new int[]{403,-1,-1,-1,-1};
			e = new StNpc("程伯伯","老了...身体都快动不了了...哎......",306, 0,  temp_a);
			st_npc.add(e);
			temp_a= new int[]{402,405,-1,-1,-1};
			e = new StNpc("孟良","啊！后山的确有很多不知到的秘密呢！",307, 0, temp_a);
			st_npc.add(e);
			temp_a= new int[]{410,-1,-1,-1,-1};
			e = new StNpc("太白仙人","呵呵，世人的污浊真是可笑啊，哈哈哈....",	308, 0, temp_a);
			st_npc.add(e);
			temp_a= new int[]{404,411,-1,-1,-1};
			e = new StNpc("邢捕头","我是洛阳的捕头总管，这里的治安都是我负责。",	309, 0, temp_a);
			st_npc.add(e);
			temp_a= new int[]{406,407,-1,-1,-1};
			e = new StNpc("景阳","稀奇古怪的东西总是能令人兴奋。",310,0, temp_a);
			st_npc.add(e);
			temp_a= new int[]{410,-1,-1,-1,-1};
			e = new StNpc("宋书生","苦读十几年，总该要有个结果吧.....",	311,0,  temp_a);
			st_npc.add(e);
			temp_a= new int[]{408,-1,-1,-1,-1};
			e = new StNpc("梦璃","人妖之争何时了.....为什么总要有战争.....",	312,0, temp_a);
			st_npc.add(e);temp_a= new int[]{409,-1,-1,-1,-1};
			e = new StNpc("总督","现在的年轻人可真不得了，应该刮目先看了。",313, 0, temp_a);
			st_npc.add(e);temp_a= new int[]{-1,-1,-1,-1,-1};
			e = new StNpc("云天河","啊，我是出来打酱油的........",314,0, temp_a);
			st_npc.add(e);
			
			//把Set<StNpc>内对象全部放入npcList
			NPC npc;
			Iterator<StNpc> itr_st_npc;
			StNpc stnle = null;
			for(itr_st_npc = st_npc.iterator();itr_st_npc.hasNext();)
			{
				npc = new NPC();
				stnle = itr_st_npc.next();
				/* first, we should clear the mission-list-of-npc */
				npc.npcClear();
				npc.setName(stnle.name);
				npc.setTalk(stnle.talk);
				npc.setID(stnle.ID);
				npc.setSellOrNot(stnle.sellornot);
				for(int j = 0;j < 5; ++j)
					if(stnle.arr_mission[j]>=0)
						npc.addMission(stnle.arr_mission[j]);
					else
						break;
				npclist.pushNPC(npc);
			}
			return 0;
		};
		int init_map(MapList maplist)
		{
			/* 瓦当镇 中央平原森林小道鬼泣崖空山穴
			 * 后山泾阳小路洛阳沼泽地南部丘陵 太巴山
			 * 河边  赵家村
			 */
			Set<StMap> st_map = new LinkedHashSet<StMap>();
			StMap e;
			int[] temp_a;
			int[] temp_b;
			int[] temp_c;
			
			temp_a = new int[]{300,301,302,303,304,305,306,-1,-1,-1};
			temp_b = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("瓦当镇",601, 0,0,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{800,801,802,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("泥泞小道",602, 1,0,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{300,301,302,303,304,305,306,-1,-1,-1};
			temp_b = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("后山（一）",603, 2,0,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{307,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("后山（二）",604, 2,1,  0,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("山谷",605, 3,0,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{800,801,802,803,804,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("山腰",606, 4,0,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{801,802,803,804,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("空山穴",607, 4,1,  0,  4,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{800,801,802,803,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("东部平原（一）",608, 0,1,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{804,801,802,803,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("东部平原（二）",609, 0,2,  0,  5, temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{803,804,805,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("平原丘陵",610, 0,3,  1,  0, temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{803,804,805,806,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("南方小路（一）",611, 1,3,  1,  0, temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{300,301,302,303,304,305,306,-1,-1,-1};
			temp_b = new int[]{803,804,805,806,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("南方小路（二）",612, 1,4,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{300,301,302,303,304,305,306,-1,-1,-1};
			temp_b = new int[]{806,805,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{852,-1,-1} ;
			e = new StMap("鬼泣崖",613, 1,5,  1,  0, temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{312,314,301,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{804,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("洛阳城西",614, 2,3,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{310,302,309,311,313,-1,-1,-1,-1,-1};
			temp_b = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("洛阳",615, 2,4,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{806,807,808,-1,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("森林小路",616, 3,4,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{806,807,808,809,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{853,-1,-1};
			e = new StMap("河边",617, 3,5,  0,  10,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{807,808,809,810,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{-1,-1,-1};
			e = new StMap("森林深处",618, 4,4,  1,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			temp_a = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
			temp_b = new int[]{807,808,809,810,-1,-1,-1,-1,-1,-1 };
			temp_c = new int[]{854,-1,-1};
			e = new StMap("林中之城",619, 4,3,  0,  0,temp_a,temp_b,temp_c);
			st_map.add(e);
			
			Iterator<StMap> itr_st_map;
			StMap stmle = null;
			Map map;//这里map不能new，因为java对象传的是地址，如果已new，则下面改的仅仅是同一地址的同一map的不同属性，也就是说只有最后一次修改有效
			for(itr_st_map = st_map.iterator();itr_st_map.hasNext();)
			{
				map = new Map();//在循环内new，则每次循环都是新的map对象地址，添加时不会重复
				stmle = itr_st_map.next();
				map.Clear();
				map.setName(stmle.name);
				map.setID(stmle.ID);
				map.setPosX(stmle.x);
				map.setPosY(stmle.y);
				map.setLock(stmle.lock);
				map.setLevel(stmle.level);
				for(int j = 0; j < 10; j++)
				{
					if(stmle.arr_npc[j]>=0)
						map.addNPC(stmle.arr_npc[j]);
					else
						break;
					//System.out.println(stmle.arr_npc[j]);//说明至少执行了6次循环，NPC已添加
				}
				for(int j = 0; j < 10; j++)
				{
					if(stmle.arr_monster[j]>=0)
				
						map.addMonster(stmle.arr_monster[j]);
					else
						break;
					//System.out.println(stmle.arr_monster[j]);
				}
				for(int j = 0; j < 3 ; ++j)
				{
					if(stmle.arr_must_monster[j]>=0)
						map.addMonsterMust(stmle.arr_must_monster[j]);
					else
						break;
					//System.out.println(stmle.arr_must_monster[j]);
				}
				maplist.pushMap(map);
			}
			maplist.setPointer();
			return 0;
		};
		
		GameControl()
		{
			init_time = 1;
			/* clear the list of mission */
			/*this.missionBossList.clear();
			this.missionNpcList.clear();
			this.missionUsingList.clear();*/
		};
		
		public int game_init(Role role,MapList maplist,MonsterList monsterlist,UsingList usinglist,WeaponList weaponlist,MissionList missionlist,NPCList npclist,SkillList skilllist,Battle battle)
		{
			/* 
			 *  Here' s all the IDs of thing occured in game ! It is very 
			 *  import!!!
			 *  Note:And 0 is the role!!
			 *
			 *  Using: 1~99;
			 *  weapon 100~199
			 *  skill 200~299
			 *  npc 300~399
			 *  mission 400~599
			 *  map 600~799
			 *  monster 800~999   850~870
			 *
			 */
			System.out.println(".......载入中.");
			init_mission(missionlist);
			System.out.print('.');//Sleep(100);
			init_skill(skilllist);
			System.out.print('.');//Sleep(100);
			init_monster(monsterlist);
			System.out.print('.');//Sleep(100);
			init_role(role);
			System.out.print('.');//Sleep(100);
			init_using(usinglist);
			System.out.print('.');//Sleep(100);
			init_weapon(weaponlist);
			System.out.print('.');//Sleep(100);
			init_npc(npclist);
			System.out.print('.');//Sleep(100);
			init_map(maplist);
			System.out.print('.');//Sleep(/100);
			return 1;
		};
		public int game_save()
		{
			return 1;
		};
		public int game_load()
		{
			return -1;
		};
		public int game_control(Role role,MapList maplist,MonsterList monsterlist,UsingList usinglist,WeaponList weaponlist,MissionList missionlist,NPCList npclist,SkillList skilllist,Battle battle) throws IOException, Exception
		{
			String userinput;
			int num_in_map = 0;
			//if(init_time == 1)
			{
				maplist.setCurMap();
				maplist.Generate(role,monsterlist);
				
				/* set the init_time to 1.*/
				init_time = 1;
			}
			while(true)
			{
				role.printHead();
				maplist.printMap(npclist,monsterlist);
				num_in_map = maplist.getMonsterNum() + maplist.getNPCNum();
				System.out.println("1~"+num_in_map+" 选择    0:菜单");
				while(true)
				{
					BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
					userinput = consolebr.readLine();
					
					if(userinput.codePointAt(0) == '0')
						break;
					if(userinput.codePointAt(0)-'0'>=1&&userinput.codePointAt(0)-'0'<=num_in_map)
						break;
					if(userinput.codePointAt(0) == 'w'||userinput.codePointAt(0) == 'W')/* change map*/
						break;
					if(userinput.codePointAt(0) == 's'||userinput.codePointAt(0) == 'S')/* change map*/
						break;
					if(userinput.codePointAt(0) == 'a'||userinput.codePointAt(0) == 'A')/* change map*/
						break;
					if(userinput.codePointAt(0) == 'd'||userinput.codePointAt(0) == 'D')/* change map*/
						break;
					if(userinput.codePointAt(0) == 'b'||userinput.codePointAt(0) == 'B')/* bag */
						break;
					if(userinput.codePointAt(0) == 'k'||userinput.codePointAt(0) == 'K')/* skill*/
						break;
					if(userinput.codePointAt(0) == 'm'||userinput.codePointAt(0) == 'M')/* map*/
						break;
					if(userinput.codePointAt(0) == 'q'||userinput.codePointAt(0) == 'Q')/* mission*/
						break;
					if(userinput.codePointAt(0) == 'c'||userinput.codePointAt(0) == 'C')/* infomation*/
						break;
				}
				if(userinput.codePointAt(0) == '0')
				{
					if(game_menu(role,maplist,monsterlist,usinglist,weaponlist,missionlist,npclist,skilllist)==-1)
						return 0;
				}
				/*因为ASC码48就是'0'，也就是说'0'的值是48，而后依次是'1'到'9'.这样正好是char型减去48就是它对应的int值。不过这样写不好理解，直接写成str[i]-'0'就好。 */
				else if(userinput.codePointAt(0)-'0'>=1&&userinput.codePointAt(0)-'0'<=num_in_map)
				{
					if(userinput.codePointAt(0)-'0'<=maplist.getNPCNum())
					{
						/* then select the npc .*/
						((npclist.getNPCPtr(maplist.getNPCID(userinput.codePointAt(0)-'0')))).Talk(role, usinglist,weaponlist,missionlist,skilllist,monsterlist,maplist);
					}
					else /* then select the monster */ 
					{
						int temp_index = userinput.codePointAt(0) - '0' - maplist.getNPCNum();
						/* the find the monster */
						Monster monster_selected = maplist.getMonster(temp_index); 
						/* then generate the monster according to the role's level.*/
						monster_selected.Generate(role.getLevel());
						battle.inBattle(role,monster_selected,monsterlist);
						battle.afterBattle(role,monster_selected,usinglist,weaponlist,skilllist);
					}
				}
				else if(userinput.codePointAt(0) == 'w'||userinput.codePointAt(0) == 'W')/* change map*/
					if(maplist.changeMap(Global.MAP_NORTH,role) != 0)
						maplist.Generate(role,monsterlist);
					else
						;
				else if(userinput.codePointAt(0) == 's'||userinput.codePointAt(0) == 'S')/* change map*/
					if(maplist.changeMap(Global.MAP_SOUTH,role) != 0)
						maplist.Generate(role,monsterlist);
					else
						;
				else if(userinput.codePointAt(0) == 'a'||userinput.codePointAt(0) == 'A')/* change map*/
					if(maplist.changeMap(Global.MAP_WEST,role) != 0)
						maplist.Generate(role,monsterlist);
					else
						;
				else if(userinput.codePointAt(0) == 'd'||userinput.codePointAt(0) == 'D')/* change map*/
					if(maplist.changeMap(Global.MAP_EAST,role) != 0)
						maplist.Generate(role,monsterlist);
					else
						;
				else if(userinput.codePointAt(0) == 'b'||userinput.codePointAt(0) == 'B')/* beg */
					role.printBag(1);
				else if(userinput.codePointAt(0) == 'k'||userinput.codePointAt(0) == 'K')/* skill*/
					role.printSkill(1);
				else if(userinput.codePointAt(0) == 'm'||userinput.codePointAt(0) == 'M')/* map*/
					maplist.showMap();
				else if(userinput.codePointAt(0) == 'q'||userinput.codePointAt(0) == 'Q')/* mission*/
					role.printMission(missionlist,usinglist,monsterlist,npclist);
				else if(userinput.codePointAt(0) == 'c'||userinput.codePointAt(0) == 'C')/* infomation*/
					role.printInfo();
			}
		};
		public int game_menu(Role role,MapList maplist,MonsterList monsterlist,UsingList usinglist, WeaponList weaponlist,MissionList missionlist,NPCList npclist,SkillList skilllist)
		{
			return -1;
		};
		public int print_game() throws IOException
		{
			String select_menu;
			while(true)
			{
				System.out.println("    打怪兽       ver 1.0");
				System.out.println("1: 新游戏");
				System.out.println("2: 载入游戏（尚未添加）");
				System.out.println("3: 游戏帮助");
				System.out.println("4: 制作信息");
				System.out.println("5: 退出");
				System.out.println("请选择（1~5）:");
				
				BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
				select_menu = consolebr.readLine();
				if(select_menu.codePointAt(0) == '1') return Global.GAME_NEW;
				else if(select_menu.codePointAt(0) == '2') return Global.GAME_LOAD;
				else if(select_menu.codePointAt(0) == '3') return Global.GAME_INFO;
				else if(select_menu.codePointAt(0) == '4') return Global.GAME_MY;
				else if(select_menu.codePointAt(0) == '5') return Global.GAME_EXIT;
			}
		};
		public int game_info() throws IOException
		{
			print_txt("./game_info.txt");
			return 0;
		};
		public int my_game() throws IOException
		{
			print_txt("./my_info.txt");
			return 0;
		};
		/* show some *.txt files */
		public int print_txt(String filename) throws IOException
		{
			String txt = "";
			File file_info = new File(filename);			
			if(file_info.canRead())
			{
				BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
				while((txt = buff.readLine())!= null)
				{
					System.out.println(txt);
				};
			}
			System.out.println("1:确定");
			
			while(true)
			{
				BufferedReader consolebr = new BufferedReader(new InputStreamReader(System.in));
				txt = consolebr.readLine();
				if(txt.codePointAt(0) == '1')
					break;
			}
			
			return 0;
		};
		
	}
	