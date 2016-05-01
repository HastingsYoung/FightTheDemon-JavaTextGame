package Mainbody;

public class Global {
	public final static int SLEEPTIME = 900;
	public final static int PERCENT = 1;
	public final static int NOPERCENT = 0;
	public final static int MON_LOCK = 1;
	public final static int MON_UNLOCK = 0;
	public final static int MAP_LOCK = 0; 
	public final static int MAP_UNLOCK = 1;
	/* the destination of the role */
	public final static int MAP_EAST = 0;
	public final static int MAP_WEST = 1;
	public final static int MAP_NORTH = 2;
	public final static int MAP_SOUTH = 3;

	public final static int SELLWEAPON = 2; /* NPC sell weapon*/
	public final static int SELLUSING = 1; /* NPC sell using*/
	public final static int SELLNOTHING = 0; /* npc is mission-npc*/
	public final static int MSN_BEFORE = 0;	//the mission isn't do
	public final static int MSN_BEING = 1;		// the mission is doing 
	public final static int MSN_DONE = 2; 		//the mission have been done
	public final static int MSN_NONE = 3;		//haven't been used here
	//gift type
	public final static int MSN_EXPGIF = 0;	//the experience gift
	public final static int MSN_MSNGIF = 1;	//the next important mission unlock 
	public final static int MSN_EQPGIF = 2;	//the equipment gift	
	public final static int MSN_USEGIF = 3;	//the using gift
	public final static int MSN_NONEGIF = 4;   //haven't been used here
	//mission lock
	public final static int MSN_LOCK = 0;    //the mission have been locked
	public final static int MSN_UNLOCK = 1;   //the mission is unlock
	// mission circle or not
	public final static int MSN_NOCIRCLE = 0;  //the no-circle mission
	public final static int MSN_CIRCLE = 1; 
	
	public final static int BTL_ROLETURN = 0;    //the role's turn to attack
	public final static int BTL_MONSTERTURN = 1; ///the monster's turn to attack
	public final static int GAME_SAVE = 0;
	public final static int GAME_LOAD = 1;
	public final static int GAME_EXIT = 2;
	public final static int GAME_INIT = 3;
	public final static int GAME_NEW  = 4;
	public final static int GAME_INFO = 5;
	public final static int GAME_MY   = 6;
}
