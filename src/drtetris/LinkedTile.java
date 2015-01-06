package drtetris;

public class LinkedTile extends Tile {

    public static final int STRAIGHT = 0, ALL = 1, CORNER = 2, T = 3, LEG = 4, NONE = 5;

    public static final TileConstruct[][][][] TUNNELCONSTRUCTS = new TileConstruct[][][][]{ //DONE
        {//0 False
            {//1 False
                {//2 False
                    new TileConstruct(STRAIGHT, 90F),//3 False DONE
                    new TileConstruct(STRAIGHT, 0F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(STRAIGHT, 0F),//3 False DONE
                    new TileConstruct(STRAIGHT, 0F)//3 True DONE
                }
            },
            {// 1 True
                {//2 False
                    new TileConstruct(STRAIGHT, 90F),//3 False DONE
                    new TileConstruct(CORNER, -90F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(CORNER, 0F),//3 False DONE
                    new TileConstruct(T, 0F)//3 True DONE
                }
            }
        },
        {//0 True
            {//1 False
                {//2 False
                    new TileConstruct(STRAIGHT, 90F),//3 False DONE
                    new TileConstruct(CORNER, 180F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(CORNER, 90F),//3 False DONE
                    new TileConstruct(T, 180F)//3 True DONE
                }
            },
            {//1 True
                {//2 False
                    new TileConstruct(STRAIGHT, 90F),//3 False DONE
                    new TileConstruct(T, -90F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(T, 90F),//3 False DONE
                    new TileConstruct(ALL, 0F)//3 True DONE
                }
            }
        }
    };

    public static final TileConstruct[][][][] CONSTRUCTS = new TileConstruct[][][][]{//DONE
        {//0 False
            {//1 False
                {//2 False
                    new TileConstruct(NONE, 0F),//3 False DONE
                    new TileConstruct(LEG, 180F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(LEG, 0F),//3 False DONE
                    new TileConstruct(STRAIGHT, 0F)//3 True DONE
                }
            },
            {// 1 True
                {//2 False
                    new TileConstruct(LEG, -90F),//3 False DONE
                    new TileConstruct(CORNER, -90F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(CORNER, 0F),//3 False DONE
                    new TileConstruct(T, 0F)//3 True DONE
                }
            }
        },
        {//0 True
            {//1 False
                {//2 False
                    new TileConstruct(LEG, 90F),//3 False DONE
                    new TileConstruct(CORNER, 180F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(CORNER, 90F),//3 False DONE
                    new TileConstruct(T, 180F)//3 True DONE
                }
            },
            {//1 True
                {//2 False
                    new TileConstruct(STRAIGHT, 90F),//3 False DONE
                    new TileConstruct(T, -90F)//3 True DONE
                },
                {//2 True
                    new TileConstruct(T, 90F),//3 False DONE
                    new TileConstruct(ALL, 0F)//3 True DONE
                }
            }
        }
    };

    private final String blockId;

    public LinkedTile(Tile tile, String blockId) {
        super(tile.name, tile.image, tile.length);
        this.blockId = blockId;
    }
    
    public LinkedTile(String name, String image, int length, int tWidth, int tHeight, String blockId) {
        super(name, image, length, tWidth, tHeight);
        this.blockId = blockId;
    }

    public String getBlockId() {
        return blockId;
    }
}
