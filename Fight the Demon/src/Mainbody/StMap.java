package Mainbody;

public class StMap {
	String name;
	int ID;
	int x;
	int y;
	int lock;
	int level;
	int[] arr_npc = new int[10];
	int[] arr_monster = new int[10];
	int[] arr_must_monster = new int[3];

	public StMap(String name,int ID,int x,int y,int lock,int level,int[] arr_npc,int[] arr_monster,int[] arr_must_monster) {
		this.name = name;
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.lock = lock;
		this.level = level;
		this.arr_npc = arr_npc;
		this.arr_monster = arr_monster;
		this.arr_must_monster = arr_must_monster;
		// TODO Auto-generated constructor stub
	}

}
