package dpackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	
	private ImageIcon titleImage; 
	
	private int[] snakeXLength = new int[750];
	private int[] snakeYLength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightMouth;
	private ImageIcon upMouth;
	private ImageIcon leftMouth;
	private ImageIcon downMouth;
	
	
	private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
			525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
			525,550,575,600,625};
	
	private ImageIcon enemyimage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34); 
	private int ypos = random.nextInt(23);
	private int score = 0;
	
	private int lengthOfSnake = 3;
	private int moves = 0;
	
	private Timer timer;
	private int delay = 100;
	
	private ImageIcon snakeImage;
	
	
	public Gameplay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
		
		
	}

	
	public void paint(Graphics g) {
		
		
		if (moves == 0) {
			
			snakeXLength[2] = 50;
			snakeXLength[1] = 75;
			snakeXLength[0] = 100;
			
			snakeYLength[2] = 100;
			snakeYLength[1] = 100;
			snakeYLength[0] = 100;
		}
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw background for the gameplay
		g.setColor(Color.BLACK);
		g.fillRect(24, 74, 850, 575);
		
		
		//draw score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 780, 30);
		
		//draw length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length:" + lengthOfSnake, 780, 50);
		
		rightMouth = new ImageIcon("rightmouth.png");
		rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
		
		for (int i=0; i<lengthOfSnake; i++) {
			
			if(i==0 && right) {
				rightMouth = new ImageIcon("rightmouth.png");
				rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			
			if(i==0 && left) {
				leftMouth = new ImageIcon("leftmouth.png");
				leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			if(i==0 && down) {
				downMouth = new ImageIcon("downmouth.png");
				downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			if(i==0 && up) {
				upMouth = new ImageIcon("upmouth.png");
				upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
			
			if(i!=0) {
				
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);;
			}
			
		}
		
		enemyimage = new ImageIcon("enemy.png");
		
		if((enemyxpos[xpos] == snakeXLength[0]) && (enemyypos[ypos]==snakeYLength[0])) {
			
			score++;
			lengthOfSnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
			
		}
		
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for (int b= 1; b < lengthOfSnake; b++) {
			
			if((snakeXLength[b] == snakeXLength[0]) && (snakeYLength[b] == snakeYLength[0])){
				
				right = false;
				left = false;
				down = false;
				up = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD, 20));
				g.drawString("Space to Restart", 350, 340);
				
			}
		}
		
		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		timer.start();
		if(right) {
			
			for (int r = lengthOfSnake-1; r>=0; r--) {
				snakeYLength[r+1] = snakeYLength[r];
			}
			for(int r = lengthOfSnake; r>=0; r--) {
				
				if(r==0) {
					snakeXLength[r] = snakeXLength[r] +25;
				}
				
				else {
					snakeXLength[r] = snakeXLength[r-1];
				}
				
				if(snakeXLength[r] > 850) {
					
					snakeXLength[r] = 25;
				}
				
			}
			repaint();
		}
		if(left) {
				
			for (int r = lengthOfSnake-1; r>=0; r--) {
				snakeYLength[r+1] = snakeYLength[r];
			}
			for(int r = lengthOfSnake; r>=0; r--) {
				
				if(r==0) {
					snakeXLength[r] = snakeXLength[r] - 25;
				}
				
				else {
					snakeXLength[r] = snakeXLength[r-1];
				}
				
				if(snakeXLength[r] < 25) {
					
					snakeXLength[r] = 850;
				}
				
				}
			repaint();
		}
		if(up) {
			for (int r = lengthOfSnake-1; r>=0; r--) {
				snakeXLength[r+1] = snakeXLength[r];
			}
			for(int r = lengthOfSnake; r>=0; r--) {
				if(r==0) {
					snakeYLength[r] = snakeYLength[r] - 25;
				}
				
				else {
					snakeYLength[r] = snakeYLength[r-1];
				}
				
				if(snakeYLength[r] < 75) {
					
					snakeYLength[r] = 625;
				}
				
		}
		repaint();
		}
		
		if(down) {
			
			for (int r = lengthOfSnake-1; r>=0; r--) {
				snakeXLength[r+1] = snakeXLength[r];
			}
			for(int r = lengthOfSnake; r>=0; r--) {
				
				if(r==0) {
					snakeYLength[r] = snakeYLength[r] + 25;
				}
				
				else {
					snakeYLength[r] = snakeYLength[r-1];
				}
				
				if(snakeYLength[r] > 625) {
					
					snakeYLength[r] = 75;
				}
				
		}
		repaint();
		}

	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		
if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	
	moves++;
	left = true;
	if(!right) {
		left = true;
	}
	else {
		left = false;
		right = true;
	}
	
	up = false;
	down = false;
}
if (e.getKeyCode() == KeyEvent.VK_UP) {
	
	moves++;
	up = true;
	if(!down) {
		up = true;
	}
	else {
		up = false;
		down = true;
	}
	
	right = false;
	left = false;
}
if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	
	moves++;
	down = true;
	if(!up) {
		down = true;
	}
	else {
		down = false;
		up = true;
	}
	
	right = false;
	left = false;
}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
