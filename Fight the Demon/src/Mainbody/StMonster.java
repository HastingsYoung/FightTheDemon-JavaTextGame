package Mainbody;

public class StMonster {

	String name;
	int ID;
	int money;
	int exppoint;
	int hitpoint;
	int attackpoint;
	int defendpoint;
	int lock;
	int die;
	int level;
	/* here is the uslist and wplist ,at most 4 elemetes. */
	int[] arr_using = new int[4];
	int[] arr_weapon = new int[4];
	public StMonster(String name,int ID,int money,int exppoint,int hitpoint,int attackpoint,int defendpoint,int lock,int die,int level,int[] arr_using,int[] arr_weapon) 
	{
		// TODO Auto-generated constructor stub
		this.name = name;
		this.ID = ID;
		this.money = money;
		this.exppoint = exppoint;
		this.hitpoint = hitpoint;
		this.attackpoint = attackpoint;
		this.defendpoint = defendpoint;
		this.lock = lock;
		this.die = die;
		this.level = level;
		/* here is the uslist and wplist ,at most 4 elemetes. */
		this.arr_using = arr_using;
		this.arr_weapon = arr_weapon;
	}

}
