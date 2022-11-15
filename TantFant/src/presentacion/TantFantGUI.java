package presentacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import aplicacion.TantFant;
import aplicacion.TantFantException;


/**
 * Write a description of class TantFantGUI here.
 *
 * @author ()
 * @version (2022)
 */
public class TantFantGUI extends JFrame
{
	private TantFant tantFant;
	private JButton[] board;
	
	private JMenuBar barMenu;
	private JMenu opciones;
	private JMenuItem nuevo,abrir,salvar,salir;
	
	private JPanel boardPanel;
	private JTextField xInicial;
	private JTextField yInicial;
	private JButton playButton;
	private JTextField turnPlayer;
	private JPanel infoGame;
	private JTextField playerMovements;
	private Color colorBoard;
	private JButton changeColor;
	private JButton restartButton;
	private JButton changeSizeBoardButton;
	private JTextField newSize;
	
	private JButton from;
	private JButton destination;
	

    /**
     * Constructor for objects of class TantFantGUI
     */
    public TantFantGUI()
    {
        setTitle("TantFant");
        setResizable(false);
        try {
			tantFant = new TantFant(3);
		} catch (TantFantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        prepareElements();
        prepareActions();
    }

    public void prepareElements() {
    	//CICLO 0
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int alto = dimension.height;
        int ancho = dimension.width;
        setSize(ancho/2, alto/2);
        setLocationRelativeTo(null);
        //CICLO 1
        prepareElementsMenu();
        prepareElementsBoard();
        
    }
    
    private void prepareElementsMenu() {
    	//CICLO 1
        barMenu  = new JMenuBar();
        opciones = new JMenu("Menu");
        nuevo = new JMenuItem("Nuevo");
        abrir =new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        salir =new JMenuItem("Salir");
        opciones.add(nuevo);
        opciones.add(abrir);
        opciones.add(salvar);
        opciones.add(salir);
        barMenu.add(opciones);
        setJMenuBar(barMenu);
    }
    
    private void prepareElementsBoard() {
    	setLayout(new GridLayout(1,1));
    	//setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //boardPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        //add(boardPanel);
        //add(inputPanel,1);   
        add(inputPanel);
        
         
        /*inputPanel*/
        inputPanel.setLayout(new GridLayout(2,2));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
   			 BorderFactory.createTitledBorder("Play"), 
   			 BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        infoGame = new JPanel();
        JPanel moveGame = new JPanel();
        JPanel buttonsGame = new JPanel();
        JPanel moreOptions = new JPanel();
        inputPanel.add(infoGame);
        inputPanel.add(moveGame);
        inputPanel.add(buttonsGame);
        inputPanel.add(moreOptions);
        
      
        
        /*infoGame*/
        infoGame.setBorder(BorderFactory.createCompoundBorder(
   			 BorderFactory.createTitledBorder("Information"), 
   			 BorderFactory.createEmptyBorder(10, 10, 10, 10)));
      
        infoGame.add(new JLabel("Player's Turn"));
        turnPlayer = new JTextField("Black", 12);
        turnPlayer.setEnabled(false);
        infoGame.add(turnPlayer);
        
        
        infoGame.add(new JLabel("#Movements:"));
        playerMovements = new JTextField("0", 12);
        playerMovements.setEnabled(false);
        infoGame.add(playerMovements);
        

        /*moveGame*/
        moveGame.setBorder(BorderFactory.createCompoundBorder(
      			 BorderFactory.createTitledBorder("Move"), 
      			 BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        /*moveGame --> Ubicacion Actual*/
        moveGame.add(new JLabel("Desde"));
        xInicial = new JTextField("x", 3);
        xInicial.setEnabled(false);
        moveGame.add(xInicial);
        yInicial = new JTextField("y", 3);
        yInicial.setEnabled(false);
        moveGame.add(yInicial);
        
        /*moveGame --> Ubicacion Futura*/
        
        //String[] moves = { "Up", "Down", "Right", "Left","Up-Right","Up-Left","Down-Right","Down-Left"};
        //movimientos = new JComboBox(moves);
        //moveGame.add(movimientos);
       

        /*Boton para Jugar*/
        
        playButton = new JButton("CHANGE PIECE");
        moveGame.add(playButton);
        
        /*buttonsGame*/
        buttonsGame.setBorder(BorderFactory.createCompoundBorder(
     			 BorderFactory.createTitledBorder("Options"), 
     			 BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        buttonsGame.add(new JLabel("Change Board Size"));
        newSize = new JTextField("Size", 12);
        buttonsGame.add(newSize);
        changeSizeBoardButton = new JButton("Insert other size");
        buttonsGame.add(changeSizeBoardButton);
        
        
        buttonsGame.add(new JLabel("Restart"));
        restartButton = new JButton("Start Again");
        buttonsGame.add(restartButton);
        /*moreOptions*/
        
        moreOptions.setBorder(BorderFactory.createCompoundBorder(
    			 BorderFactory.createTitledBorder("More Options"), 
    			 BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
      
        moreOptions.add(new JLabel("Board Color"));
        changeColor = new JButton("Choose Color");
        moreOptions.add(changeColor);
        
        /*Started color on the board*/
        colorBoard = Color.LIGHT_GRAY; 
        
        
        /*-----------------------------------------------------------------------------*/
       
        
        
        /*Create the board*/
        setPiecesBoard();
        
    	refresh();
    }
    
    private void setPiecesBoard() {
    	boardPanel = new JPanel();
    	add(boardPanel);
    	
    	/*boardPanel*/
        boardPanel.setBorder(BorderFactory.createCompoundBorder(
                			 BorderFactory.createTitledBorder("Game Board"), 
                			 BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
    	/*Create the board*/
        int n = tantFant.getSize();
    	board = new JButton[n*n];
    	boardPanel.setLayout(new GridLayout(n,n));
    	for(int i = 0; i < n*n; i++) {
    		board[i] = new JButton();
    		boardPanel.add(board[i]);
    	}
    }
    
    
    
    private void refresh() {
    	char[][] boardStatus = tantFant.board();
    	int indexButton = 0;
    	for(int i = 0; i < boardStatus.length;i++) {
    		for(int j = 0; j < boardStatus[i].length;j++) {
    			if(boardStatus[i][j] == 'A') board[indexButton].setBackground(Color.BLACK);
    			if(boardStatus[i][j] == 'B') board[indexButton].setBackground(Color.white);
    			if(boardStatus[i][j] == ' ') board[indexButton].setBackground(colorBoard);
    			//System.out.println(tantFant.player());
    			if(tantFant.player() !=  boardStatus[i][j]) {
    				board[indexButton].setEnabled(false);
    			}else {
    				board[indexButton].setEnabled(true);
    			}
    			
    			indexButton++;
    		}
    	}
    	updatePlayer();
    	
    	if(tantFant.winner()){
    		String ganador = " ";
    		if(tantFant.player() == 'A') ganador = "White"; 
    		if(tantFant.player() == 'B') ganador = "Black"; 
    		JOptionPane.showMessageDialog(null, "YOU WON! Congrutulation player who moves " + ganador + " pieces");
    		actionRestartGame();	
    	}
    }
    
    
        
    private void updatePlayer() {
       /*Que jugador sigue*/
       String player = "";
       if(tantFant.player() == 'A') {
       	player = "Black";
       }else {
       	player = "White";
       }
       turnPlayer.setText(player);
       /*Numero de Movimientos de ese Jugador*/
       int numero = tantFant.plays();
       numero = numero /2;
       String moves = Integer.toString(numero);
       playerMovements.setText(moves);
    }
    

    public void prepareActions() {
    	
    	/*Cerrar de la parte superior*/
    	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
            	actionExit();
            }
        });
  
    	/*Preparamos nuestro menu*/
    	prepareActionsMenu();
    	
    	/*Preparamos nuestro Tablero*/
    	prepareActionsBoard();
    }
    
    private void prepareActionsBoard() {
    	/*Buton para arrepnetise del movimiento*/
    	playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
            	actionRestarMovement();                    
            }
        });
    	
    	/*Boton para cambiar el color al tablero*/
    	changeColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                actionChangeColor();                    
            }
        });
    	
    	/*Boton para reiniciar el juego*/
    	restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                actionRestartGame();                    
            }
        });
    	
    	
    	/*Boton para cambiar tamaño al tablero*/
    	changeSizeBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                actionChangeSizeBoard();                    
            }
        });
    	
    	
    	/**/
    	prepareActionsOfThePieces();
    	
    }
    
    private void prepareActionsOfThePieces() {
    	for(int i = 0; i < board.length; i++) {
    		int index = i;
    		board[i].addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent ev){
    				
    				//Calcular posicion
    				int fila = (int) index/tantFant.getSize();
					int columna = index - (tantFant.getSize()*fila);
					
    				if(from == null) {
    					from =  board[index];
    					xInicial.setText(Integer.toString(fila));
    					yInicial.setText(Integer.toString(columna));
    					
    					
    					refreshActiveButtons(fila, columna);
    					
    				}else{
    					destination = board[index];
    					int xFromFila = Integer.parseInt(xInicial.getText());
    					int yFromFila = Integer.parseInt(yInicial.getText());
    					try {
							tantFant.play(xFromFila, yFromFila, fila, columna);
							refresh();
							actionRestarMovement();
						} catch (TantFantException exception) {
				         	JOptionPane.showMessageDialog(null, exception.getMessage());;
				        }
    					
    				}
                                          
                }
            });	
    	}
    }
    
    
    
    private void refreshActiveButtons(int x,int y) {
    
    	int[][] neighbors = {{x-1,y-1},{x-1,y},{x-1,y+1},{x,y-1},{x,y+1},{x+1,y-1},{x+1,y},{x+1,y+1}};
    	for(int i = 0; i < 8;i++) {
    		if(neighbors[i][0] >= 0 && neighbors[i][0] < tantFant.getSize() && 
    				neighbors[i][1] >= 0 && neighbors[i][1] < tantFant.getSize()) {
    			int index = neighbors[i][0] * tantFant.getSize() + neighbors[i][1];
    			//System.out.println(index);
    			board[index].setEnabled(true);
    		}
    	}
    }
    

    
    private void prepareActionsMenu() {

    	/*Abrir un archivo*/
    	abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                actionOpen();                    
            }
        });
    	
    	/*Salvar un archivo*/
    	
    	salvar.addActionListener(new ActionListener() {
            public void  actionPerformed(ActionEvent ev){
            	actionSave();
            }
        });
    	
    	/*Cerrar el programa*/
    	
    	salir.addActionListener(new ActionListener() {
            public void  actionPerformed(ActionEvent ev){
            	actionClose();
            }
        });
    	
    }
    
    private void actionChangeColor() {
    	Color newColor = JColorChooser.showDialog(this,"Select a color",colorBoard);    
    	colorBoard = newColor;
    	refresh();
    }
    
    
    
    private void actionChangeSizeBoard() {
    	try {
    		tantFant.setSize(Integer.parseInt(newSize.getText()));
    		this.remove(boardPanel);
    		setPiecesBoard();
    		prepareActionsOfThePieces();
    		refresh();
    		
    	}catch (NumberFormatException excepcion) {
    		JOptionPane.showMessageDialog(null, "PON UN NUMERO!");;
        }
    	 catch (TantFantException exception) {
         	JOptionPane.showMessageDialog(null, exception.getMessage());;
        }
    	
    }
    
    
    
    private void actionRestarMovement() {
    	 from = null;
    	 destination = null;
    	 refresh();
    }
    
    private void actionRestartGame() {
    	tantFant.restart();
        colorBoard = Color.LIGHT_GRAY; 
        actionRestarMovement();
    	refresh();
    }
    
    
    
    
    private void actionExit() {
    	int menu = JOptionPane.showConfirmDialog(null,"¿Seguro que deseas salir?");
        if(menu == JOptionPane.YES_OPTION){
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else {
        	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    
    
    private void actionOpen() {
    	JFileChooser seleccionador = new JFileChooser();
        int returnVal = seleccionador.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(seleccionador, "Las funcionalidades estan en construccion."
            + "Por el momento no se puede abrir el archivo" + seleccionador.getSelectedFile());
        }            
   }
   
    
    private void actionSave() {
    	JFileChooser seleccionador = new JFileChooser();
    	int returnVal = seleccionador.showSaveDialog(null);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
    		JOptionPane.showMessageDialog(seleccionador, "Las funcionalidades estan en construccion."
    		+ "Por el momento no se puede guardar el archivo" + seleccionador.getSelectedFile());
    	}
    }
    
    private void actionClose() {
    	int menu = JOptionPane.showConfirmDialog(null,"¿Seguro que deseas salir?");
        if(menu == JOptionPane.YES_OPTION){
        	dispose();
        	System.exit(0);
        }
    }

    public static void main(String[] args){
        TantFantGUI gui = new TantFantGUI();
        gui.setVisible(true);  
    }
}
