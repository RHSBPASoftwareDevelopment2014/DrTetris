
package drtetris;

public class Config {
    
    public static final double BASESPEED = .08;
    public static final double BASEXSPEED = .015;
    public static final double SPEEDINCREMENT = .02;
    public static final double SPEEDXINCREMENT = .002;
    public static final double STACKTOLERANCE = 12;
    public static final double PARTICLESTRAY = 1.2;
    public static final double SPEEDLIMIT = 1.0;
    public static final double SPEEDXLIMIT = .15;
    public static final double DEFAULTY = -50;
    public static final double FALLINGBLOCKSPEED = .15;
    
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
    
    public static final String GAMEBACKGROUND = "res/gamebackgroundend.png";
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
    public static final String LEVELDIRECTORY = "res/levels/";
    public static final String BLOCKDIRECTORY = "res/blocks/";
    public static final String BACKGROUNDMUSIC = "res/LiveAndSlam.ogg";
    
    public static final int TILETYPENORMAL = 0;
    public static final int TILETYPETUNNEL = 1;
    public static final int TILETYPEAIR = 2;
    
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
    
    public static final int[] TILETYPELIST = {
            TILETYPEAIR,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPENORMAL,
            TILETYPETUNNEL,
            TILETYPETUNNEL,
            TILETYPETUNNEL,
            TILETYPENORMAL
        };
}
