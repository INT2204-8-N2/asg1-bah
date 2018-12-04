package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.TokenType;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
            try {
                
                String numberLevel = Integer.toString(level);
                String levelPath = "/levels/Level" + numberLevel + ".txt";
//                String levelPath = "/levels/Level2.txt";
                URL urlLevel = FileLevelLoader.class.getResource(levelPath); 
                InputStreamReader inputStreamReader = new InputStreamReader(urlLevel.openStream());
               
                BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
                
                String firstLine = bufferedReader.readLine();
                
                
               
                String[] LRC = firstLine.split(" ");
                
                _level = Integer.parseInt(LRC[0]);
                _height = Integer.parseInt(LRC[1]);
                _width = Integer.parseInt(LRC[2]);
                
                _map = new char[_height][_width];
                String readByLine = "";
                
                for(int i=0;i<_height;i++)
                {
                   readByLine = bufferedReader.readLine();
                   
                   for(int j=0;j<_width;j++)
                   {
                       _map[i][j] = readByLine.charAt(j);
                       
                   }
                }
                
//                for(int i = 0; i < _height; i++){
//                    for(int j = 0; j < _width; j++){
//                        System.out.print(_map[i][j]);
//                    }
//                    System.out.println();
//                }
                
                
            } catch (IOException ex) {
                Logger.getLogger(FileLevelLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		// thêm Wall
            for (int x = 0; x < _height; x++) {
                for (int y = 0; y < _width; y++) {
                    int pos = y + x * _width;
//                        Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
//                        _board.addEntity(pos, new Grass(x, y, sprite));

                    if (_map[x][y] == '#') {
                        _board.addEntity(pos, new Wall(y, x, Sprite.wall));
                    }
                    else if(_map[x][y] == '*'){
                        _board.addEntity(pos,
				new LayeredEntity(y, x,
					new Grass(y, x, Sprite.grass),
					new Brick(y, x, Sprite.brick)
				)
                        );
                    }
                    else if(_map[x][y] == 'p'){
                        _board.addCharacter( new Bomber(Coordinates.tileToPixel(y), 
                                Coordinates.tileToPixel(x) + Game.TILES_SIZE, _board) );
                        Screen.setOffset(0, 0);
                        _board.addEntity(pos, new Grass(y, x, Sprite.grass));
                    }
                    else if(_map[x][y] == '1'){
                        _board.addCharacter( new Balloon(Coordinates.tileToPixel(y), Coordinates.tileToPixel(x) + Game.TILES_SIZE, _board));
                	_board.addEntity(pos, new Grass(y, x, Sprite.grass));
                    }
                    else if(_map[x][y] == '2'){
                        _board.addCharacter( new Oneal(Coordinates.tileToPixel(y), Coordinates.tileToPixel(x) + Game.TILES_SIZE, _board));
                	_board.addEntity(pos, new Grass(y, x, Sprite.grass));
                    }
                    else if(_map[x][y] == 's'){
                        _board.addEntity(pos,
				new LayeredEntity(y, x,
					new Grass(y ,x, Sprite.grass),
					new SpeedItem(y, x, Sprite.powerup_speed),
					new Brick(y, x, Sprite.brick)
				)
                        );
                    }
                    else if(_map[x][y] == 'f'){
                        _board.addEntity(pos,
				new LayeredEntity(y, x,
					new Grass(y ,x, Sprite.grass),
					new FlameItem(y, x, Sprite.powerup_flames),
					new Brick(y, x, Sprite.brick)
				)
                        );
                    }
                    else if(_map[x][y] == 'b'){
                        _board.addEntity(pos,
				new LayeredEntity(y, x,
					new Grass(y ,x, Sprite.grass),
					new BombItem(y, x, Sprite.powerup_bombs),
					new Brick(y, x, Sprite.brick)
				)
                        );
                    }
                    else if(_map[x][y] == 'x'){
                        _board.addEntity(pos,
				new LayeredEntity(y, x,
					new Grass(y ,x, Sprite.grass),
					new Portal( _board, y, x, Sprite.portal),
					new Brick(y, x, Sprite.brick)
				)
                        );
                    }
                    else {
                        _board.addEntity(pos, new Grass(y, x, Sprite.grass));
                    }
                }
            }
	}

}
