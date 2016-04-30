package com.portalsandtimemachines.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random;

import javax.swing.*;

/**
 * This class holds all the functions that is used for the running the game.
 * 
 *  @author Team24 for CSE 360 Spring 2016
 *  @version 1.1 April 15,2016
 *  
 */
@SuppressWarnings("unused")
public class Game extends ApplicationAdapter{
	SpriteBatch batch;
	Texture boardBackground;
	Texture gamePieceTexturePlayer1pawn1;
	Texture gamePieceTexturePlayer1pawn2;
	Texture gamePieceTexturePlayer2pawn1;
	Texture gamePieceTexturePlayer2pawn2;
	Texture portalTexture;
	Texture timemachineTexture;
	Texture bountyTexture;
	
	private Viewport viewport;
	OrthographicCamera camera;
	
	private Sprite boardSprite;
	
	private Sprite bountySprite1;
	private Sprite bountySprite2;
	private Sprite bountySprite3;
	
	private Sprite timemachineSprite1;
	private Sprite timemachineSprite2;
	private Sprite timemachineSprite3;
	
	private Sprite portalSprite1;
	private Sprite portalSprite2;
	private Sprite portalSprite3;
	private Sprite portalSprite4;
	private Sprite portalSprite5;
	private Sprite portalSprite6;
	private Sprite portalSprite7;
	private Sprite portalSprite8;
	
	private Dice dice;
	
//	DB Connect
	
	@SuppressWarnings("rawtypes")
	HashMap dbValues = new HashMap();
	DBGameConnect dbGame;

	private float boardSize;
	private float numberOfSpacesPerRow;
	private float windowWidth;
	private Array<Vector2> boardTransforms;
	private Array<GamePiece> gamePieces;
	
	private int indexPlayer1Pawn1 = 0;
	private int indexPlayer1Pawn2 = 0;
	private int indexPlayer2Pawn1 = 0;
	private int indexPlayer2Pawn2 = 0;
	
//	ShapeRenderer shapeRenderer;
	
	GamePiece gamePiecePlayer1Pawn1;
	GamePiece gamePiecePlayer1Pawn2;
	GamePiece gamePiecePlayer2Pawn1;
	GamePiece gamePiecePlayer2Pawn2;
	
	String playernamePlayer1;
	String playernamePlayer2;
	
	int noofplayer = 0;
	
	int cardkillPlayer1 = 0;
	int cardswapPlayer1 = 0;
	int cardrevPlayer1 = 0;
	
	

	int cardkillPlayer2 = 0;
	int cardswapPlayer2 = 0;
	int cardrevPlayer2 = 0;
	
	
	Stage gamestage; 	
	Skin gameskin;
	Skin gameskinp;
	
	TextButton rollDice;
	
	TextButton playerOne;
	TextButton Pawn_One;
	TextButton playerTwo;
	TextButton Pawn_Two;
	TextButton useCard;
	
	Texture cardImgOne;
	Texture cardImgTwo;
	Texture cardImgThree;
	Texture cardImgFour;
	ImageButton cardOne;
	ImageButton cardTwo;
	ImageButton cardThree;
	ImageButton cardFour;
	
	private Label label;
	private boolean diceHasBeenRolled;

	GameBoard obj = new GameBoard();
	
	
	int[] temporary_PortalsPosition;
	int[] temporary_TimeMachinePosition;
	int[] bounty_positions;
	ArrayList<String> playerNames = new ArrayList<String>();
	int temp;
	
	static int timeMachine_counter_Player1Pawn1 = 0;
	static int timeMachine_flag_Player1Pawn1 = 0;
	static int final_pos_Player1Pawn1 = 0;

	static int timeMachine_counter_Player1Pawn2 = 0;
	static int timeMachine_flag_Player1Pawn2 = 0;
	static int final_pos_Player1Pawn2 = 0;
	
	static int timeMachine_counter_Player2Pawn1 = 0;
	static int timeMachine_flag_Player2Pawn1 = 0;
	static int final_pos_Player2Pawn1 = 0;
	
	static int timeMachine_counter_Player2Pawn2 = 0;
	static int timeMachine_flag_Player2Pawn2 = 0;
	static int final_pos_Player2Pawn2 = 0;
	
	Label cardOneLabel;
	Label cardTwoLabel;
	Label cardThreeLabel;
	Label cardFourLabel;
	
	Texture tutorialImage;
	TextureRegion tutorialImageRegion;
	ImageButtonStyle tutorialImageStyle;
	ImageButton tutorialImageButton;
	
	private boolean turnInProgress;
	private boolean firstClick;
	

	/**
	 * This function for retrieving user selection on whether to play game first or see score first.
	 * 
	 * @return choice
	 */
	public int showPane() {
		Object[] options = {"Start Game", "Show Highscores"};
		int choice = JOptionPane.showOptionDialog(null, "Portals and Time Machines", "Start Menu", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , options[0]);
		return choice;
	}
	
	/**
	 * This function is used for the purpose of drawing the board and
	 * setting up other board pieces initially.
	 * 
	 *  @param none
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void create () {
		turnInProgress = true;
		firstClick = true;
		Object[] options = {"Start Game", "Show Highscores"};

		dbGame = new DBGameConnect();
		int i = 2;
		
		JOptionPane.showMessageDialog(null, "Enter the name of both the players" );

		while(i > 0)
		{
			switch (i)
			{
				case 1:
					playernamePlayer1 = JOptionPane.showInputDialog("Please enter your usename for user 2 : ");
					while(playernamePlayer1.isEmpty())
					{
						playernamePlayer1 = JOptionPane.showInputDialog("Please enter your username for user 2 : ");
					}
					playerNames.add(playernamePlayer1);
					JOptionPane.showMessageDialog(null, "Hello " + playernamePlayer1 + '!' + "\nWelcome to Portals & Time-Machines" );
					break;
				case 2:
					playernamePlayer2 = JOptionPane.showInputDialog("Please enter your usename for user 1 : ");
					while(playernamePlayer2.isEmpty())
					{
						playernamePlayer2 = JOptionPane.showInputDialog("Please enter your username for user 1 : ");
					}
					playerNames.add(playernamePlayer2);
					JOptionPane.showMessageDialog(null, "Hello " + playernamePlayer2 + '!' + "\nWelcome to Portals & Time-Machines" );
					break;
			}
			--i;
		}
		
		dbValues.put("pname",playerNames);
		dbValues.put("gWon", false);
		dbGame.dbConnect(dbValues);

		int flagValue = showPane();
		int xvalue = 0;
		int yvalue = 0;
		if(flagValue == 0) {
			xvalue = 1;
			yvalue = 0;
		}
		else {
			xvalue = 1;
			yvalue = 1;
		}
		if(yvalue == 1){
			dbValues.put("fetchFlag", true);
			dbGame.dbConnect(dbValues);
		}

	
		batch = new SpriteBatch();
		gamestage = new Stage();
		gameskin = new Skin();
		gameskinp = new Skin();
		
		// Checkered background texture
		
		boardBackground = new Texture("10x10_checkered_board.png");
		gamePieceTexturePlayer1pawn1 = new Texture("Green2.png");
		gamePieceTexturePlayer1pawn2 = new Texture("Green1.png");
		gamePieceTexturePlayer2pawn1 = new Texture("Blue_second.png");
		gamePieceTexturePlayer2pawn2 = new Texture("Blue_first.png");
		portalTexture = new Texture("brunswick-spiral-black-white.png");
		timemachineTexture = new Texture("Time-Machine.png");
		bountyTexture = new Texture("bounty.png");
		
		cardImgOne = new Texture("Kill.png");
		cardImgTwo = new Texture("Drag2.png");
		cardImgThree = new Texture("reverse.png");
		cardImgFour = new Texture("swap.png");
		
		TextureRegion cOne = new TextureRegion(cardImgOne);
		TextureRegion cTwo = new TextureRegion(cardImgTwo);
		TextureRegion cThree = new TextureRegion(cardImgThree);
		TextureRegion cFour = new TextureRegion(cardImgFour);
		
		BitmapFont bfont=new BitmapFont();
		gameskin.add("default",bfont);
		
		Pixmap pixmap = new Pixmap(100, 50, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		gameskin.add("white", new Texture(pixmap));

		Gdx.input.setInputProcessor(gamestage);
        
		dice = new Dice();
		dice.setVisible(false);
		
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.fontColor =  Color.BLACK;
		textButtonStyle.up = gameskin.newDrawable("white", Color.WHITE);
		textButtonStyle.down = gameskin.newDrawable("white", Color.WHITE);
		textButtonStyle.checked = gameskin.newDrawable("white", Color.WHITE);
		textButtonStyle.over = gameskin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.disabled = gameskin.newDrawable("white", Color.BLACK);

		
		textButtonStyle.font = gameskin.getFont("default");

		gameskin.add("default", textButtonStyle);
		
		rollDice = new TextButton("Start Game", gameskin);
		rollDice.setPosition(0, 0);
		

		
		Pawn_One = new TextButton("Pawn 1", gameskin);
		Pawn_One.setPosition(0, 450);
		
		Pawn_Two = new TextButton("Pawn 2", gameskin);
		Pawn_Two.setPosition(0, 350);
		
		playerOne = new TextButton(playernamePlayer2, gameskin);
		playerOne.setPosition(0, 700);
		playerOne.setColor(Color.CYAN);
		playerOne.setDisabled(true);
		
		playerTwo = new TextButton(playernamePlayer1, gameskin);
		playerTwo.setPosition(0, 600);
		playerTwo.setColor(Color.GREEN);
		playerTwo.setDisabled(true);
		
		useCard = new TextButton("Use Card",gameskin);
		useCard.setPosition(0, 150);
		useCard.setDisabled(true);
		
		
		ImageButtonStyle style = new ImageButtonStyle();
		style.imageUp = new TextureRegionDrawable(cOne);
		style.imageDown = new TextureRegionDrawable(cOne);
		
		ImageButtonStyle style1 = new ImageButtonStyle();
		style1.imageUp = new TextureRegionDrawable(cTwo);
		style1.imageDown = new TextureRegionDrawable(cTwo);
		
		ImageButtonStyle style2 = new ImageButtonStyle();
		style2.imageUp = new TextureRegionDrawable(cThree);
		style2.imageDown = new TextureRegionDrawable(cThree);
		
		ImageButtonStyle style3 = new ImageButtonStyle();
		style3.imageUp = new TextureRegionDrawable(cFour);
		style3.imageDown = new TextureRegionDrawable(cFour);
		
		LabelStyle labelStyle = new LabelStyle(bfont, Color.WHITE);
		label = new Label("WELCOME!", labelStyle);
		label.setPosition(10, 250);
		label.setWrap(true);
		label.setWidth(100);
		label.setText("Welcome to portals and time machines!");
		label.setColor(Color.CYAN);
		gamestage.addActor(label);
		
		diceHasBeenRolled = false;
		
		cardOne = new ImageButton(style);
		cardOne.setSize(101, 153);
		cardOne.setPosition(Gdx.graphics.getWidth()-100, 600);
		
		/**
		 * Listener for Card kill.
		 */
		cardOne.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				if(cardkillPlayer1 == 1)
				{
					if(indexPlayer1Pawn1 < 99)
					{
						indexPlayer1Pawn1 = 0;
						gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn1));
					}
					
					if(indexPlayer1Pawn2 < 99)
					{
						indexPlayer1Pawn2 = 0;
						
						gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn2));
					}
					
					cardkillPlayer1 = 0;
				}
				else if(cardkillPlayer2 == 1)
				{
					
					if(indexPlayer2Pawn1 < 99)
					{
						indexPlayer2Pawn1 = 0;
						gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn1));
					}
					
					if(indexPlayer2Pawn2 < 99)
					{
						indexPlayer2Pawn2 = 0;
						gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn2));
					}
					cardkillPlayer2 = 0;
				}
				else
				{
					System.out.println("Nobody has the card");
				}
				
			}	
		});
		cardOne.setVisible(false);
		
		int temp_move1;
		int temp_move2;
		
		cardTwo = new ImageButton(style1);
		cardTwo.setSize(101, 153);
		cardTwo.setPosition(Gdx.graphics.getWidth()-100, 400);
		
		/**
		 * Listener for card swap.
		 */
		cardTwo.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				System.out.println("Click cardTwo");
				int temp_move1;
				int temp_move2;
				if(cardswapPlayer1 == 1)
				{
					if(indexPlayer2Pawn1 < 99 && indexPlayer1Pawn1 < 99)
					{
						temp_move1 = indexPlayer1Pawn1;
						indexPlayer1Pawn1 = indexPlayer2Pawn1;
						indexPlayer2Pawn1 = temp_move1;
						gamePiecePlayer1Pawn2.setPosition(boardTransforms.get(temp_move1));
						gamePiecePlayer1Pawn1.setPosition(boardTransforms.get(indexPlayer1Pawn1));
					}
					
					if(indexPlayer1Pawn2 < 99 && indexPlayer2Pawn2 < 99)
					{
						temp_move2 = indexPlayer1Pawn2;
						indexPlayer1Pawn2 = indexPlayer2Pawn2;
						indexPlayer2Pawn2 = temp_move2;
						gamePiecePlayer2Pawn2.setPosition(boardTransforms.get(temp_move2));
						gamePiecePlayer2Pawn1.setPosition(boardTransforms.get(indexPlayer1Pawn2));
						
					}
					
					
					cardswapPlayer1 = 0;
				}
				else if(cardswapPlayer2 == 1)
				{
					
					if(indexPlayer2Pawn1 < 99 && indexPlayer1Pawn1 < 99)
					{
						temp_move1 = indexPlayer1Pawn1;
						indexPlayer1Pawn1 = indexPlayer2Pawn1;
						indexPlayer2Pawn1 = temp_move1;
						gamePiecePlayer1Pawn2.setPosition(boardTransforms.get(temp_move1));
						gamePiecePlayer1Pawn1.setPosition(boardTransforms.get(indexPlayer1Pawn1));
					}
					
					if(indexPlayer1Pawn2 < 99 && indexPlayer2Pawn2 < 99)
					{
						temp_move2 = indexPlayer1Pawn2;
						indexPlayer1Pawn2 = indexPlayer2Pawn2;
						indexPlayer2Pawn2 = temp_move2;
						gamePiecePlayer2Pawn2.setPosition(boardTransforms.get(temp_move2));
						gamePiecePlayer2Pawn1.setPosition(boardTransforms.get(indexPlayer1Pawn2));
						
					}
						
					cardswapPlayer2 = 0;
				}
				else
				{
					System.out.println("Nobody has the card");
				}
			}	
		});
		
		cardTwo.setVisible(false);
		
		cardThree = new ImageButton(style2);
		cardThree.setSize(101, 153);
		cardThree.setPosition(Gdx.graphics.getWidth()-100, 200);
		
		/**
		 * Listener for card reverse.
		 */
		cardThree.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				System.out.println("Click cardThree");
			}	
		});
		cardThree.setVisible(false);
		
		cardFour = new ImageButton(style3);
		cardFour.setSize(101,153);
		cardFour.setPosition(Gdx.graphics.getWidth()-100, 0);
		
		/**
		 * Listener for restart card.
		 */
		cardFour.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				System.out.println("Click cardFour");
			}	
		});
		cardFour.setVisible(false);
		
		cardOneLabel = new Label("Kill", labelStyle);
		cardOneLabel.setPosition(cardOne.getX(), cardOne.getY());
		cardTwoLabel = new Label("Swap", labelStyle);
		cardTwoLabel.setPosition(cardTwo.getX(), cardTwo.getY());

		gamestage.addActor(rollDice);
		gamestage.addActor(playerOne);
		gamestage.addActor(playerTwo);
		gamestage.addActor(Pawn_One);
		gamestage.addActor(Pawn_Two);
		gamestage.addActor(useCard);
		
		gamestage.addActor(cardOne);
		gamestage.addActor(cardTwo);
		gamestage.addActor(cardThree);
		gamestage.addActor(cardFour);	
		
		
	    // Camera to manage viewport and  view matrices
		
		camera = new OrthographicCamera();
		
		// Initialize camera to window size
		
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// Initialize viewport size to camera size
		
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		
		// helper variable for board size
		
		boardSize = camera.viewportHeight;
		numberOfSpacesPerRow = 10;
		windowWidth = camera.viewportWidth;

		// Sprite to help with board texture transformation
		
		boardSprite = new Sprite(boardBackground);
		boardSprite.setOriginCenter();
		boardSprite.setSize(boardSize, boardSize);
		boardSprite.setPosition(Gdx.graphics.getWidth()/2 - boardSize/2, 0); // 0 may need to be set as screenHeight/2 - boardsize/2

		timemachineSprite1 = new Sprite(timemachineTexture);
		timemachineSprite1.setOriginCenter();
		timemachineSprite1.setSize(32, 32);
		timemachineSprite1.setColor(0,0,0,0);
		
		timemachineSprite2 = new Sprite(timemachineTexture);
		timemachineSprite2.setOriginCenter();
		timemachineSprite2.setSize(32, 32);
		timemachineSprite2.setColor(0,0,0,0);
		
		timemachineSprite3 = new Sprite(timemachineTexture);
		timemachineSprite3.setOriginCenter();
		timemachineSprite3.setSize(32, 32);
		timemachineSprite3.setColor(0,0,0,0);
		
		portalSprite1 = new Sprite(portalTexture);
		portalSprite1.setOriginCenter();
		portalSprite1.setSize(32, 32);
		
		portalSprite2 = new Sprite(portalTexture);
		portalSprite2.setOriginCenter();
		portalSprite2.setSize(32, 32);
		
		portalSprite3 = new Sprite(portalTexture);
		portalSprite3.setOriginCenter();
		portalSprite3.setSize(32, 32);
		
		portalSprite4 = new Sprite(portalTexture);
		portalSprite4.setOriginCenter();
		portalSprite4.setSize(32, 32);
		
		portalSprite5 = new Sprite(portalTexture);
		portalSprite5.setOriginCenter();
		portalSprite5.setSize(32, 32);
		
		portalSprite6 = new Sprite(portalTexture);
		portalSprite6.setOriginCenter();
		portalSprite6.setSize(32, 32);
		
		portalSprite7 = new Sprite(portalTexture);
		portalSprite7.setOriginCenter();
		portalSprite7.setSize(32, 32);
		
		portalSprite8 = new Sprite(portalTexture);
		portalSprite8.setOriginCenter();
		portalSprite8.setSize(32, 32);		
		
		
		bountySprite1 = new Sprite(bountyTexture);
		bountySprite1.setOriginCenter();
		bountySprite1.setSize(32, 32);
		
		bountySprite2 = new Sprite(bountyTexture);
		bountySprite2.setOriginCenter();
		bountySprite2.setSize(32, 32);
		
		bountySprite3 = new Sprite(bountyTexture);
		bountySprite3.setOriginCenter();
		bountySprite3.setSize(32, 32);
		
		// Used to draw shapes on screen 	
		// ArrayList to hold centers of the spaces
		
		boardTransforms = new Array<Vector2>(100);
		boardTransforms = calculateTransforms(boardSize, numberOfSpacesPerRow, windowWidth);
		
		// Draw portalSprite at the 44th space
		// Simply for testing purposes.
		
		obj.init();
		obj.init_time_machine();
		obj.init_bounty();
		
		temporary_PortalsPosition = obj.portal_positions(); 
		int i_index = 0;
		temporary_TimeMachinePosition = obj.timeMachine_positions();
		int j_index = 0;
		bounty_positions = obj.bounty_positions();
		int k_index = 0;
		
		timemachineSprite1.setPosition(boardTransforms.get(temporary_TimeMachinePosition[j_index] ).x - 32, 
				boardTransforms.get(temporary_TimeMachinePosition[j_index]).y);
		j_index++;
		
		timemachineSprite2.setPosition(boardTransforms.get(temporary_TimeMachinePosition[j_index] ).x - 32, 
				boardTransforms.get(temporary_TimeMachinePosition[j_index]).y);
		j_index++;
		
		timemachineSprite3.setPosition(boardTransforms.get(temporary_TimeMachinePosition[j_index] ).x - 32, 
				boardTransforms.get(temporary_TimeMachinePosition[j_index]).y);
		j_index++;
		
		portalSprite1.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite2.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite3.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite4.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index]).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite5.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite6.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite7.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite8.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index]).x - 32, 
				boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		bountySprite1.setPosition(boardTransforms.get(bounty_positions[k_index]).x - 32, 
				boardTransforms.get(bounty_positions[k_index]).y);
		k_index++;
		
		bountySprite2.setPosition(boardTransforms.get(bounty_positions[k_index]).x - 32, 
				boardTransforms.get(bounty_positions[k_index]).y);
		k_index++;
		
		bountySprite3.setPosition(boardTransforms.get(bounty_positions[k_index]).x - 32, 
				boardTransforms.get(bounty_positions[k_index]).y);
		k_index++;
		
		
		gamePiecePlayer1Pawn1 = new GamePiece(0, gamePieceTexturePlayer1pawn1, boardTransforms.get(0), 0);
		gamePiecePlayer2Pawn1 = new GamePiece(0, gamePieceTexturePlayer1pawn2, boardTransforms.get(0), 0);
		gamePiecePlayer1Pawn2 = new GamePiece(1, gamePieceTexturePlayer2pawn1, boardTransforms.get(0), -15f);
		gamePiecePlayer2Pawn2 = new GamePiece(1, gamePieceTexturePlayer2pawn2, boardTransforms.get(0), -15f);
		
		/**
		 * Listener Function for roll dice button.
		 */
		rollDice.addListener(new ChangeListener() 
		{	
			public void changed (ChangeEvent event, Actor actor) 
			{
				System.out.println("Click rollDice");
				dice.setVisible(true);
				if(firstClick){
					playerOne.setDisabled(true);
					playerTwo.setDisabled(false);
					firstClick = false;
				}
				
				if(!turnInProgress && !diceHasBeenRolled){
					dice.changeAnimate();
					float delay = 0.5f; // seconds
					label.setText("Press either Pawn 1 or Pawn 2");
					Timer.schedule(new Task(){
					    @Override
					    public void run() {
					    	temp = obj.roll_die();
							dice.showNumber(temp);
					    }
					}, delay);
					
					++flag;
					diceHasBeenRolled = true;
					ArrayList<String> tempArr = new ArrayList<String>();
					if(indexPlayer1Pawn1 > 98 && indexPlayer1Pawn2 > 98) {
						JOptionPane.showMessageDialog(null,"Congrats " + playernamePlayer1 + "! You Won!!");
						tempArr.add(playernamePlayer1);
						dbValues.put("pname",tempArr);
						dbValues.put("gWon", true);
						dbGame.dbConnect(dbValues);
						Gdx.app.exit();
					}
					else if(indexPlayer2Pawn1 > 98 && indexPlayer2Pawn2 > 98)
					{
						JOptionPane.showMessageDialog(null,"Congrats " + playernamePlayer2 + "! You Won!!");
						tempArr.add(playernamePlayer2);
						dbValues.put("pname",tempArr);
						dbValues.put("gWon", true);
						dbGame.dbConnect(dbValues);
						Gdx.app.exit();
					}
					rollDice.setText("End Turn");
					turnInProgress = true;
				}
				else if(turnInProgress && !diceHasBeenRolled){
					playerTwo.setDisabled(!playerTwo.isDisabled());
					playerOne.setDisabled(!playerOne.isDisabled());
					if(playerTwo.isDisabled()){
						label.setColor(Color.CYAN);
					}
					else{
						label.setColor(Color.GREEN);
					}
					turnInProgress = false;
					rollDice.setText("Roll Dice");
					label.setText("Roll the dice");
				}
			}
		});
		
		/**
		 * Listener function for pawn one button
		 */
		Pawn_One.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				System.out.println("Click Pawn_One");
				if(diceHasBeenRolled && !gamePiecePlayer1Pawn1.moving && !gamePiecePlayer2Pawn1.moving && !gamePiecePlayer1Pawn2.moving && !gamePiecePlayer2Pawn2.moving){
					diceHasBeenRolled = false;
					moving_piece(temp, 1);
					label.setText("Play a card or press end turn");
				}
			}
		});
		
		/**
		 * Listener function for pawn two button
		 */
		Pawn_Two.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				System.out.println("Click Pawn_Two");
				if(diceHasBeenRolled && !gamePiecePlayer1Pawn1.moving && !gamePiecePlayer2Pawn1.moving && !gamePiecePlayer1Pawn2.moving && !gamePiecePlayer2Pawn2.moving){
					diceHasBeenRolled = false;
					moving_piece(temp, 2);
					label.setText("Play a card or press end turn");
				}
			}
		});
		
		tutorialImage = new Texture("tutorial.png"); 
		tutorialImageRegion = new TextureRegion(tutorialImage);
		tutorialImageStyle = new ImageButtonStyle();
		tutorialImageStyle.imageUp = new TextureRegionDrawable(tutorialImageRegion);
		tutorialImageStyle.imageDown = new TextureRegionDrawable(tutorialImageRegion);
		tutorialImageButton = new ImageButton(tutorialImageStyle);
		tutorialImageButton.setSize(1024, 768);
		tutorialImageButton.setPosition(0, 0);
		tutorialImageButton.setVisible(true);
		tutorialImageButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				removeTutorialImage();
			}
		});
		gamestage.addActor(tutorialImageButton);
	}
	
	
	/**
	 * This function handles the movement of the pawn in a proper order on the board.
	 * 
	 * @param boardSize		size of the board is passed.
	 * @param numberOfSpacesPerRow	passing the number of spaces in the row.
	 * @param windowWidth	passing the window width for manipulation as parameter.
	 * @return	Array<Vector2> which is the movement that it should follow.
	 */
	public Array<Vector2> calculateTransforms(float boardSize, float numberOfSpacesPerRow, float windowWidth){
		Array<Vector2> temporaryArray = new Array<Vector2>(100);
		for(int i = 0; i < 10; i++){ // Horizontal movement
			if(i % 2 == 0){
				for(int j = 0; j < 10; j++){ // Vertical movement
					temporaryArray.add(new Vector2(boardSize/numberOfSpacesPerRow * j + ((boardSize/numberOfSpacesPerRow)/2) + ((windowWidth - boardSize)/2), 
							boardSize/numberOfSpacesPerRow * i + ((boardSize/numberOfSpacesPerRow)/2)));
				}
			}
			else{
				for(int j = 9; j >= 0; j--){ // Vertical movement
					temporaryArray.add(new Vector2(boardSize/numberOfSpacesPerRow * j + ((boardSize/numberOfSpacesPerRow)/2) + ((windowWidth - boardSize)/2), 
							boardSize/numberOfSpacesPerRow * i + ((boardSize/numberOfSpacesPerRow)/2)));
				}
			}
		}
		return temporaryArray;
	}
	
	/**
	 * Function that is used for the purpose resizing the board.
	 * 
	 * @param width used for altering the width.
	 * @param height used for altering the height.
	 */
	public void resize(int width, int height) {
	    viewport.update(width, height);
	}
	
	/**
	 * This is the main function that is used for rendering the UI of the application.
	 * 
	 */
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		batch.begin();
		boardSprite.draw(batch);
		timemachineSprite1.draw(batch);
		timemachineSprite2.draw(batch);
		timemachineSprite3.draw(batch);
		portalSprite1.draw(batch);
		portalSprite2.draw(batch);
		portalSprite3.draw(batch);
		portalSprite4.draw(batch);
		portalSprite5.draw(batch);
		portalSprite6.draw(batch);
		portalSprite7.draw(batch);
		portalSprite8.draw(batch);
		
		bountySprite1.draw(batch);
		bountySprite2.draw(batch);
		bountySprite3.draw(batch);
		
		gamePiecePlayer1Pawn1.draw(batch);
		gamePiecePlayer1Pawn2.draw(batch);
		gamePiecePlayer2Pawn2.draw(batch);
		gamePiecePlayer2Pawn1.draw(batch);
		
		label.draw(batch, 1);
		if(dice.isVisible()){
			dice.draw(batch);
		}
		batch.end();
		gamestage.draw();
	}
	
	/**
	 * 
	 */
	public void removeTutorialImage()
	{
		tutorialImageButton.setVisible(false);
		tutorialImageButton.setTouchable(Touchable.disabled);
		tutorialImageButton.setDisabled(true);
	}
	
	int playersel = 0;
	int pawnsel;
	int pawnsel1;
	int flag;

	/**
	 * This function is used for moving the pawn on the board.
	 * 
	 * @param value_tomove	value of which the pawn is to be moved.
	 */
	public void moving_piece(int value_tomove, int pawnsol)
	{
		Random rand = new Random();

		pawnsel = pawnsol;
		pawnsel1 = pawnsol;
		++playersel;
		if(playersel % 2 == 0)
		{

			if(cardkillPlayer2 == 1)
			{
				cardOne.setVisible(true);
			}
			else
			{
				cardOne.setVisible(false);
			}
			
			if(cardswapPlayer2 == 1)
			{
				cardTwo.setVisible(true);
			}
			else
			{
				cardTwo.setVisible(false);
			}
			
			if(cardrevPlayer2 == 1)
			{
				cardThree.setVisible(true);
			}
			else
			{
				cardThree.setVisible(false);
			}
			
			if(pawnsel % 2 == 0)
			{
				if(timeMachine_flag_Player1Pawn1 == 0)
				{
					indexPlayer1Pawn1 = indexPlayer1Pawn1 + value_tomove;
					if(indexPlayer1Pawn1 > 98)
					{
						gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn1));
					}
				}
				else if( timeMachine_flag_Player1Pawn1 == 1)
				{
					label.setText("Time machine is still active. Move away from it..!!!!");
					indexPlayer1Pawn1 = indexPlayer1Pawn1 + value_tomove;
					if(timeMachine_counter_Player1Pawn1 > 0 && indexPlayer1Pawn1 < final_pos_Player1Pawn1 )
					{
						timeMachine_counter_Player1Pawn1--;
						if(timeMachine_counter_Player1Pawn1 == 0 && indexPlayer1Pawn1 <final_pos_Player1Pawn1)
						{
							label.setText("Oops..!!!, Time is Up. The pawn restarts.");
							timeMachine_flag_Player1Pawn1 = 0;
							indexPlayer1Pawn1 = 0;
							gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn1));
						}
						else
						{
							if(indexPlayer1Pawn1 > 98)
							{
								gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn1));
							}
						}
					}
					else if(timeMachine_counter_Player1Pawn1 == 0 && indexPlayer1Pawn1 < final_pos_Player1Pawn1)
					{
						timeMachine_flag_Player1Pawn1 = 0;
						indexPlayer1Pawn1 = 0;
						gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn1));
					}
					else if(timeMachine_counter_Player1Pawn1 >= 0 && indexPlayer1Pawn1 >= final_pos_Player1Pawn1)
					{
	
						timeMachine_flag_Player1Pawn1 = 0;
						if(indexPlayer1Pawn1 > 98)
	
							timeMachine_flag_Player1Pawn1 = 0;
						indexPlayer1Pawn1 = indexPlayer1Pawn1 + value_tomove;
						if(indexPlayer1Pawn1 > 98)
						{
							gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(99));
						}
						else
						{
							gamePiecePlayer1Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn1));
						}
					}
				}
	
				int ret = obj.check_portal(indexPlayer1Pawn1);
				int bounty_ret = obj.check_bounty(indexPlayer1Pawn1);
				if(ret != 0)
				{
					label.setText("You stepped on a portal...!!");
					System.out.println("Portal ");
					if(indexPlayer1Pawn1 > 98)
					{
						gamePiecePlayer1Pawn1.secondaryMove(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer1Pawn1.portalMovement(boardTransforms.get(indexPlayer1Pawn1), boardTransforms.get(ret));
						indexPlayer1Pawn1 = ret;
						
						if(obj.check_portal(indexPlayer1Pawn1) != 0)
						{
							indexPlayer1Pawn1 = indexPlayer1Pawn1 + obj.check_portal(indexPlayer1Pawn1);
							System.out.println("Portal ");
							if(indexPlayer1Pawn1 > 98)
							{
								gamePiecePlayer1Pawn1.portalMovement(boardTransforms.get(indexPlayer1Pawn1), boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer1Pawn1.portalMovement(boardTransforms.get(indexPlayer1Pawn1), boardTransforms.get(ret));
							}
						}
					}
					
				}
				else if(bounty_ret == 1)
				{
					label.setText("You stepped on the bounty square. You will receive a bounty card drawn at random!");
					int common = rand.nextInt((2 - 1) + 1) + 1;
					if(common % 2 == 0)
					{
						cardswapPlayer2 = 1;
					}
					else if(common % 2 == 1)
					{
						cardkillPlayer2 = 1;
					}
				}
				if(obj.check_timeMachine(indexPlayer1Pawn1) != 0)
				{
					label.setText("Oops !! You stepped over a time machine. You will have to move 8 sqaures within 2 turns or you will pushed to the beginning..");
					timeMachine_counter_Player1Pawn1 = 2;
					timeMachine_flag_Player1Pawn1 = 1;
					if(indexPlayer1Pawn1 == temporary_TimeMachinePosition[0])
					{
						timemachineSprite1.setColor(1,1,1,1);
					}
					else if(indexPlayer1Pawn1 == temporary_TimeMachinePosition[1])
					{
						timemachineSprite2.setColor(1,1,1,1);
					}
					else
					{
						timemachineSprite3.setColor(1,1,1,1);
					}
					final_pos_Player1Pawn1 = obj.check_timeMachine(indexPlayer1Pawn1);
				}
			}
			else
			{
				if(timeMachine_flag_Player2Pawn1 == 0)
				{
					indexPlayer1Pawn2 = indexPlayer1Pawn2 + value_tomove;
					if(indexPlayer1Pawn2 > 98)
					{
						gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn2));
					}
				}
				else if( timeMachine_flag_Player2Pawn1 == 1)
				{
					label.setText("Time machine is still active. Move away from it..!!!!");
					indexPlayer1Pawn2 = indexPlayer1Pawn2 + value_tomove;
					if(timeMachine_counter_Player2Pawn1 > 0 && indexPlayer1Pawn2 < final_pos_Player2Pawn1 )
					{
						timeMachine_counter_Player2Pawn1--;
						if(timeMachine_counter_Player2Pawn1 == 0 && indexPlayer1Pawn2 <final_pos_Player2Pawn1)
						{
							label.setText("Oops..!!!, Time is Up. The pawn restarts.");
							timeMachine_flag_Player2Pawn1 = 0;
							indexPlayer1Pawn2 = 0;
							gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn2));
						}
						else
						{
							if(indexPlayer1Pawn2 > 98)
							{
								gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn2));
							}
						}
					}
					else if(timeMachine_counter_Player2Pawn1 == 0 && indexPlayer1Pawn2 < final_pos_Player2Pawn1)
					{
						timeMachine_flag_Player2Pawn1 = 0;
						indexPlayer1Pawn2 = 0;
						gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn2));
					}
					else if(timeMachine_counter_Player2Pawn1 >= 0 && indexPlayer1Pawn2 >= final_pos_Player2Pawn1)
					{
	
						timeMachine_flag_Player2Pawn1 = 0;
						if(indexPlayer1Pawn2 > 98)
							timeMachine_flag_Player2Pawn1 = 0;
						indexPlayer1Pawn2 = indexPlayer1Pawn2 + value_tomove;
						if(indexPlayer1Pawn2 > 98)
						{
							gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(99));
						}
						else
						{
							gamePiecePlayer2Pawn1.moveToPosition(boardTransforms.get(indexPlayer1Pawn2));
						}
					}
				}
	
				int ret = obj.check_portal(indexPlayer1Pawn2);
				int bounty_ret = obj.check_bounty(indexPlayer1Pawn2);
				if(ret != 0)
				{
					label.setText("You stepped on a portal...!!");
					System.out.println("Portal ");
					if(indexPlayer1Pawn2 > 98)
					{
						gamePiecePlayer2Pawn1.secondaryMove(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer2Pawn1.portalMovement(boardTransforms.get(indexPlayer1Pawn2), boardTransforms.get(ret));
						indexPlayer1Pawn2 = ret;

						if(obj.check_portal(indexPlayer1Pawn2) != 0)
						{
							indexPlayer1Pawn2 = indexPlayer1Pawn2 + obj.check_portal(indexPlayer1Pawn2);
							System.out.println("Portal ");
							if(indexPlayer1Pawn2 > 98)
							{
								gamePiecePlayer2Pawn1.portalMovement(boardTransforms.get(indexPlayer1Pawn2), boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer2Pawn1.portalMovement(boardTransforms.get(indexPlayer1Pawn2), boardTransforms.get(ret));
							}
						}
					}
					
				}
				else if(bounty_ret == 1)
				{
					label.setText("You stepped on the bounty square. You will receive a bounty card drawn at random!");
					int common = rand.nextInt((2 - 1) + 1) + 1;
					if(common % 2 == 0)
					{
						cardswapPlayer2 = 1;
					}
					else if(common % 2 == 1)
					{
						cardkillPlayer2 = 1;
					}

				}
				if(obj.check_timeMachine(indexPlayer1Pawn2) != 0)
				{
					label.setText("Oops !! You stepped over a time machine. You will have to move 8 sqaures within 2 turns or you will pushed to the beginning..");
					timeMachine_counter_Player2Pawn1 = 2;
					timeMachine_flag_Player2Pawn1 = 1;
					if(indexPlayer1Pawn2 == temporary_TimeMachinePosition[0])
					{
						timemachineSprite1.setColor(1,1,1,1);
					}
					else if(indexPlayer1Pawn2 == temporary_TimeMachinePosition[1])
					{
						timemachineSprite2.setColor(1,1,1,1);
					}
					else
					{
						timemachineSprite3.setColor(1,1,1,1);
					}
					final_pos_Player2Pawn1 = obj.check_timeMachine(indexPlayer1Pawn2);
				}
				
			}
		}
		else
		{
			if(cardkillPlayer1 == 1)
			{
				cardOne.setVisible(true);
			}
			else
			{
				cardOne.setVisible(false);
			}
			
			if(cardswapPlayer1 == 1)
			{
				cardTwo.setVisible(true);
			}
			else
			{
				cardTwo.setVisible(false);
			}
			
			if(cardrevPlayer1 == 1)
			{
				cardThree.setVisible(true);
			}
			else
			{
				cardThree.setVisible(false);
			}
			
			if(pawnsel1 % 2 == 0)
			{
				if(timeMachine_flag_Player1Pawn2 == 0)
				{
					indexPlayer2Pawn1 = indexPlayer2Pawn1 + value_tomove;
					if(indexPlayer2Pawn1 > 98)
					{
						gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn1));
					}
				}
				else if( timeMachine_flag_Player1Pawn2 == 1)
				{
					label.setText("Time machine is still active. Move away from it..!!!!");
					indexPlayer2Pawn1 = indexPlayer2Pawn1 + value_tomove;
					if(timeMachine_counter_Player1Pawn2 > 0 && indexPlayer2Pawn1 < final_pos_Player1Pawn2 )
					{
						timeMachine_counter_Player1Pawn2--;
						if(timeMachine_counter_Player1Pawn2 == 0 && indexPlayer2Pawn1 <final_pos_Player1Pawn2)
						{
							label.setText("Oops..!!!, Time is Up. The pawn restarts.");
							timeMachine_flag_Player1Pawn2 = 0;
							indexPlayer2Pawn1 = 0;
							gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn1));
						}
						else
						{
							if(indexPlayer2Pawn1 > 98)
							{
								gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn1));
							}
						}
					}
					else if(timeMachine_counter_Player1Pawn2 == 0 && indexPlayer2Pawn1 < final_pos_Player1Pawn2)
					{
						timeMachine_flag_Player1Pawn2 = 0;
						indexPlayer2Pawn1 = 0;
						gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn1));
					}
					else if(timeMachine_counter_Player1Pawn2 >= 0 && indexPlayer2Pawn1 >= final_pos_Player1Pawn2)
					{
	
						timeMachine_flag_Player1Pawn2 = 0;
						if(indexPlayer2Pawn1 > 98)
	
							timeMachine_flag_Player1Pawn2 = 0;
						indexPlayer2Pawn1 = indexPlayer2Pawn1 + value_tomove;
						if(indexPlayer2Pawn1 > 98)
						{
							gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(99));
						}
						else
						{
							gamePiecePlayer1Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn1));
						}
					}
				}
				int bounty_ret = obj.check_bounty(indexPlayer1Pawn1);
				int ret = obj.check_portal(indexPlayer2Pawn1);
				if(ret != 0)
				{
					label.setText("You stepped on a portal...!!");
					if(indexPlayer2Pawn1 > 98)
					{
						gamePiecePlayer1Pawn2.secondaryMove(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer1Pawn2.portalMovement(boardTransforms.get(indexPlayer2Pawn1), boardTransforms.get(ret));
						indexPlayer2Pawn1 = ret;
						
						if(obj.check_portal(indexPlayer2Pawn1) != 0)
						{
							indexPlayer2Pawn1 = indexPlayer2Pawn1 + obj.check_portal(indexPlayer2Pawn1);
							if(indexPlayer2Pawn1 > 98)
							{
								gamePiecePlayer1Pawn2.portalMovement(boardTransforms.get(indexPlayer2Pawn1), boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer1Pawn2.portalMovement(boardTransforms.get(indexPlayer2Pawn1), boardTransforms.get(ret));
							}
						}
					}
					
				}
				else if(bounty_ret == 1)
				{
					label.setText("You stepped on the bounty square. You will receive a bounty card drawn at random!");
					int common = rand.nextInt((2 - 1) + 1) + 1;
					if(common % 2 == 0)
					{
						cardswapPlayer1 = 1;
					}
					else if(common % 2 == 1)
					{
						cardkillPlayer1 = 1;
					}
				}
				if(obj.check_timeMachine(indexPlayer2Pawn1) != 0)
				{
					label.setText("Oops !! You stepped over a time machine. You will have to move 8 sqaures within 2 turns or you will pushed to the beginning..");
					timeMachine_counter_Player1Pawn2 = 2;
					timeMachine_flag_Player1Pawn2 = 1;
					if(indexPlayer2Pawn1 == temporary_TimeMachinePosition[0])
					{
						timemachineSprite1.setColor(1,1,1,1);
					}
					else if(indexPlayer2Pawn1 == temporary_TimeMachinePosition[1])
					{
						timemachineSprite2.setColor(1,1,1,1);
					}
					else
					{
						timemachineSprite3.setColor(1,1,1,1);
					}
					final_pos_Player1Pawn2 = obj.check_timeMachine(indexPlayer2Pawn1);
				}
			}
			else
			{
				if(timeMachine_flag_Player2Pawn2 == 0)
				{
					indexPlayer2Pawn2 = indexPlayer2Pawn2 + value_tomove;
					if(indexPlayer2Pawn2 > 98)
					{
						gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn2));
					}
				}
				else if( timeMachine_flag_Player2Pawn2 == 1)
				{
					label.setText("Time machine is still active. Move away from it..!!!!");
					indexPlayer2Pawn2 = indexPlayer2Pawn2 + value_tomove;
					if(timeMachine_counter_Player2Pawn2 > 0 && indexPlayer2Pawn2 < final_pos_Player2Pawn2 )
					{
						timeMachine_counter_Player2Pawn2--;
						if(timeMachine_counter_Player2Pawn2 == 0 && indexPlayer2Pawn2 <final_pos_Player2Pawn2)
						{
							label.setText("Oops..!!!, Time is Up. The pawn restarts.");
							timeMachine_flag_Player2Pawn2 = 0;
							indexPlayer2Pawn2 = 0;
							gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn2));
						}
						else
						{
							if(indexPlayer2Pawn2 > 98)
							{
								gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn2));
							}
						}
					}
					else if(timeMachine_counter_Player2Pawn2 == 0 && indexPlayer2Pawn2 < final_pos_Player2Pawn2)
					{
						timeMachine_flag_Player2Pawn2 = 0;
						indexPlayer2Pawn2 = 0;
						gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn2));
					}
					else if(timeMachine_counter_Player2Pawn2 >= 0 && indexPlayer2Pawn2 >= final_pos_Player2Pawn2)
					{
	
						timeMachine_flag_Player2Pawn2 = 0;
						if(indexPlayer2Pawn2 > 98)
	
							timeMachine_flag_Player2Pawn2 = 0;
						indexPlayer2Pawn1 = indexPlayer2Pawn2 + value_tomove;
						if(indexPlayer2Pawn2 > 98)
						{
							gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(99));
						}
						else
						{
							gamePiecePlayer2Pawn2.moveToPosition(boardTransforms.get(indexPlayer2Pawn2));
						}
					}
				}
				int bounty_ret = obj.check_bounty(indexPlayer2Pawn2);
				int ret = obj.check_portal(indexPlayer2Pawn2);
				if(ret != 0)
				{
					label.setText("You stepped on a portal...!!");

					System.out.println("Portal ");
					if(indexPlayer2Pawn2 > 98)
					{
						gamePiecePlayer2Pawn2.secondaryMove(boardTransforms.get(99));
					}
					else
					{
						gamePiecePlayer2Pawn2.portalMovement(boardTransforms.get(indexPlayer2Pawn2), boardTransforms.get(ret));
						indexPlayer2Pawn2 = ret;
						
						if(obj.check_portal(indexPlayer2Pawn2) != 0)
						{
							indexPlayer2Pawn2 = indexPlayer2Pawn2 + obj.check_portal(indexPlayer2Pawn2);
							System.out.println("Portal ");
							if(indexPlayer2Pawn1 > 98)
							{
								gamePiecePlayer2Pawn2.portalMovement(boardTransforms.get(indexPlayer2Pawn2), boardTransforms.get(99));
							}
							else
							{
								gamePiecePlayer2Pawn2.portalMovement(boardTransforms.get(indexPlayer2Pawn2), boardTransforms.get(ret));
							}
						}
					}
					
				}
				else if(bounty_ret == 1)
				{
					label.setText("You stepped on the bounty square. You will receive a bounty card drawn at random!");
					int common = rand.nextInt((2 - 1) + 1) + 1;
					if(common % 2 == 0)
					{
						cardswapPlayer1 = 1;
					}
					else if(common % 2 == 1)
					{
						cardkillPlayer1 = 1;
					}
				}
				if(obj.check_timeMachine(indexPlayer2Pawn2) != 0)
				{
					label.setText("Oops !! You stepped over a time machine. You will have to move 8 sqaures within 2 turns or you will pushed to the beginning..");
					timeMachine_counter_Player2Pawn2 = 2;
					timeMachine_flag_Player2Pawn2 = 1;
					if(indexPlayer2Pawn2 == temporary_TimeMachinePosition[0])
					{
						timemachineSprite1.setColor(1,1,1,1);
					}
					else if(indexPlayer2Pawn2 == temporary_TimeMachinePosition[1])
					{
						timemachineSprite2.setColor(1,1,1,1);
					}
					else
					{
						timemachineSprite3.setColor(1,1,1,1);
					}
					final_pos_Player2Pawn2 = obj.check_timeMachine(indexPlayer2Pawn2);
				}
				
			}
			
		}
		
	}
	
	/**
	 * This function is used for disposing the board.
	 */
	@Override
	public void dispose(){
		boardBackground.dispose();
		batch.dispose();
	}
}
