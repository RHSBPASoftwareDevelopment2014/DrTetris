
package drtetris;

public class Config {
    
    public static final double BASESPEED = .16;
    public static final double BASEXSPEED = .015;
    public static final double SPEEDINCREMENT = .02;
    public static final double SPEEDXINCREMENT = .002;
    public static final double STACKTOLERANCE = 13;
    public static final double PARTICLESTRAY = 1.2;
    public static final double SPEEDLIMIT = 1.0;
    public static final double SPEEDXLIMIT = .15;
    public static final double DEFAULTY = -50;
    public static final double FALLINGBLOCKSPEED = .15;
    
    public static final int FIELDHEIGHT = 12;
    public static final int FIELDWIDTH = 12;
    public static final int FIELDOFFSET = 10;
    public static final int FIELDX = 150;
    public static final int FIELDY = 90;
    public static final int BLOCKDELAY = 400;
    public static final int BLOCKSIZE = 40;
    public static final int XMOVEDELAY = 200;
    public static final int XMOVEDELAYDECREMENT = 5;
    public static final int DEFAULTX = 6;
    public static final int TUNNELSHEETWIDTH = 100;
    
    public static final String GAMEBACKGROUND = "res/gamebackgrounddirt.png";
    public static final String MAINMENU = "res/mainmenu.png";
    public static final String ERRORSCREEN = "res/errorscreen.png";
    public static final String TUTORIALBUTTON = "res/tutorialbutton.png";
    public static final String CHALLENGEBUTTON = "res/challengebutton.png";
    public static final String INFINITEBUTTON = "res/infinitebutton.png";
    public static final String OPTIONSBUTTON = "res/optionsbutton.png";
    public static final String EXITBUTTON = "res/exitbutton.png";
    public static final String BACKMAINMENUBUTTON = "res/mainmenubutton.png";
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
    public static final String LEVELDIRECTORY = "res/levels/";
    public static final String BLOCKDIRECTORY = "res/blocks/";
    public static final String BACKGROUNDMUSIC = "res/LiveAndSlam.ogg";
    
    public static final Tile[] TILELIST = {null,
            new Tile("Dirt", Config.DIRTTILE, 6),
            new Tile("Sapphire", Config.SAPPHIRETILE, 6),
            new Tile("Ruby", Config.RUBYTILE, 6),
            new Tile("Amethyst", Config.AMETHYSTTILE, 6),
            new Tile("Green Garnet", Config.GREENGARNETTILE, 6),
            new Tile("Sand", Config.SANDTILE, 6),
            new Tunnel("Blue Tunnel", Config.BLUETUNNEL),
            new Tunnel("Red Tunnel", Config.REDTUNNEL),
            new Tunnel("Green Tunnel", Config.GREENTUNNEL)};
}
