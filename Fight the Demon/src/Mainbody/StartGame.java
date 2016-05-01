package Mainbody;

public class StartGame {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Role role = new Role();
		MapList map_list = new MapList();
		SkillList skill_list = new SkillList();
		MonsterList monster_list = new MonsterList();
		MissionList mission_list = new MissionList();
		UsingList using_list = new UsingList();
		WeaponList weapon_list = new WeaponList();
		NPCList npc_list = new NPCList();
		Battle battle = new Battle();
		GameControl g_control = new GameControl();
		int main_menu;
		g_control.game_init(role,map_list,monster_list,using_list,weapon_list,mission_list,npc_list ,skill_list,battle);

		while(true)
		{
			main_menu=g_control.print_game();
			if(main_menu == Global.GAME_LOAD)
			if(g_control.game_load() == -1)
			{
				System.out.println("没有进度可以读取!!!");
				continue;
			}else
				continue;
			else if(main_menu == Global.GAME_NEW)
			{
				
			}
			else if(main_menu == Global.GAME_EXIT) 
			{

				break;    /* exit the while loop,game will exit.*/
			}
			else if(main_menu == Global.GAME_INFO)
			{
				/* print info of the game.*/
				g_control.game_info();
				continue;
			}
			else if(main_menu == Global.GAME_MY)
			{
				g_control.my_game();
				continue;
			}
			/* get in the main loop of game.
			 * When this function completed ,it means that user
			 * go back to the main menu of the game. 
			 * new ,load,info,exit game.
			 */
			g_control.game_control(role,map_list,monster_list,using_list,weapon_list,mission_list,npc_list ,skill_list,battle);
		}
		/* here, the game exit!*/
}
}
