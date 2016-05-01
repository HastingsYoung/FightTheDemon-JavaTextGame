package Mainbody;

public class StNpc {
	String name;
	String talk;
	int ID;
	int sellornot;
	int[] arr_mission = new int[5];

	public StNpc(String name,String talk,int ID,int sellornot,int[] arr_mission) 
	{
		this.name = name;
		this.talk = talk;
		this.ID = ID;
		this.sellornot = sellornot;
		this.arr_mission = arr_mission;
		// TODO Auto-generated constructor stub
	}

}
