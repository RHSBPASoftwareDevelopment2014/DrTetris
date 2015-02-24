
package drtetris;

public class Config {
    
    public static final double BASESPEED = .08;
    public static final double BASEXSPEED = .015;
    public static final double SPEEDINCREMENT = .01;
    public static final double SPEEDXINCREMENT = .002;
    public static final double STACKTOLERANCE = 12;
    public static final double PARTICLESTRAY = 1.2;
    public static final double SPEEDLIMIT = 1.0;
    public static final double SPEEDXLIMIT = .15;
    public static final double DEFAULTY = -50;
    public static final double FALLINGBLOCKSPEED = .2;
    public static final double CREDITSSPEED = .15;
    
    public static final int BLOCKSIZE = 40;
    public static final int FIELDHEIGHT = 12;
    public static final int FIELDWIDTH = 12;
    public static final int FIELDOFFSET = 29;
    public static final int FIELDX = 150;
    public static final int FIELDY = 91;
    public static final int BLOCKDELAY = 400;
    public static final int XMOVEDELAY = 200;
    public static final int XMOVEDELAYDECREMENT = 5;
    public static final int DEFAULTX = 6;
    public static final int NEXTBLOCKX = 658;
    public static final int NEXTBLOCKY = 115;
    public static final int TUNNELSHEETWIDTH = 100;
    public static final int NUMBEROFLEVELS = 20;
    
    public static final String MAINMENU = "res/mainmenu.png";
    public static final String ERRORSCREEN = "res/errorscreen.png";
    public static final String TUTORIALBUTTON = "res/tutorialbutton.png";
    public static final String CHALLENGEBUTTON = "res/challengebutton.png";
    public static final String INFINITEBUTTON = "res/infinitebutton.png";
    public static final String OPTIONSBUTTON = "res/optionsbutton.png";
    public static final String EXITBUTTON = "res/exitbutton.png";
    public static final String BACKMAINMENUBUTTON = "res/mainmenubutton.png";
    public static final String INNEROPTIONSBUTTON = "res/optionsbutton2.png";
    public static final String SAVEBUTTON = "res/savebutton.png";
    public static final String INNEREXITBUTTON = "res/exitbutton2.png";
    public static final String BACKBUTTON = "res/backbutton.png";
    public static final String PLUSBUTTON = "res/plusbutton.png";
    public static final String MINUSBUTTON = "res/minusbutton.png";
    public static final String LEVELSELECTBUTTON = "res/levelselectoutline.png";
    public static final String EASYBUTTON = "res/easybutton.png";
    public static final String MEDIUMBUTTON = "res/mediumbutton.png";
    public static final String EXTREMEBUTTON = "res/extremebutton.png";
    public static final String PAUSESCREEN = "res/pausedscreen.png";
    public static final String GAMEOVERSCREEN = "res/gameoverscreen.png";
    public static final String DIRTTILE = "res/dirt.png";
    public static final String SAPPHIRETILE = "res/sapphire.png";
    public static final String RUBYTILE = "res/ruby.png";
    public static final String AMETHYSTTILE = "res/amethyst.png";
    public static final String SANDTILE = "res/sand.png";
    public static final String GREENGARNETTILE = "res/greengarnet.png";
    public static final String BLUETUNNEL = "res/bluetunnelspritesheet.png";
    public static final String REDTUNNEL = "res/redtunnelspritesheet.png";
    public static final String GREENTUNNEL = "res/greentunnelspritesheet.png";
    public static final String ROOTSTILE = "res/roots.png";
    public static final String LEVELNAMEBACKGROUND = "res/levelnamebackground.png";
    public static final String LEVELSELECTIONBACKGROUND = "res/levelselect.png";
    public static final String CREDITSIMAGE = "res/credits.png";
    public static final String OPTIONSBACKGROUND = "res/optionsmenu.png";
    public static final String TUTORIALSLIDES = "res/tutorial.png";
    public static final String LEVELDIRECTORY = "res/levels/";
    public static final String BLOCKDIRECTORY = "res/blocks/";
    public static final String MAINMENUBACKGROUNDMUSIC = "res/sounds/Island_Fever.ogg";
    public static final String GAMEBACKGROUNDMUSIC = "res/sounds/BEAST1.ogg";
    public static final String FONT = "res/font.TTF";
    public static final String SAVEFILE = "user.save";
    
    //Creates the different types of blocks and the three difficulty levels
    public static final int TILETYPENORMAL = 0, TILETYPETUNNEL = 1, TILETYPEAIR = 2, TILETYPESAND = 3;
    public static final int EASY = 0, MEDIUM = 1, EXTREME = 2;
    
    public static final String[] TILENAMELIST = {
            null,
            "Dirt",
            "Sapphire",
            "Ruby",
            "Amethyst",
            "Green Garnet",
            "Sand",
            "Red Tunnel",
            "Blue Tunnel",
            "Green Tunnel",
            "Roots"
        };
    
    public static final String[] TILEIMAGELIST = {
            null,
            DIRTTILE,
            SAPPHIRETILE,
            RUBYTILE,
            AMETHYSTTILE,
            GREENGARNETTILE,
            SANDTILE,
            BLUETUNNEL,
            REDTUNNEL,
            GREENTUNNEL,
            ROOTSTILE
        };
    //Chooses which block has which properties 
    public static final int[] TILETYPELIST = {
            TILETYPEAIR,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPESAND,
            TILETYPETUNNEL,
            TILETYPETUNNEL,
            TILETYPETUNNEL,
            TILETYPENORMAL
        };
    //This adds gravity effects to the sand and tunnel blocks
    public static final boolean[] TILEGRAVITYLIST = {
        false,
        false,
        false,
        false,
        false,
        false,
        true,
        true,
        true,
        true,
        false
    };
}
