import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.io.File;
import java.io.FileNotFoundException;

public class BlackJackGUI implements ActionListener{
	BlackJack bj = new BlackJack();
	JPanel playerPanel = new JPanel();
	JPanel bankPanel = new JPanel();
	JButton anotherCard = new JButton("Another Card");
	JButton noMoreCard = new JButton("No More Card");
	JButton Reset = new JButton("Reset");
	JLabel playerScore = new JLabel();
	JLabel bankScore = new JLabel();
	JLabel playerLabel = new JLabel();
	JLabel bankLabel = new JLabel();
	
	public BlackJackGUI() {
		
		JFrame frame = new JFrame("BlackJack GUI");
		frame.setMinimumSize(new Dimension(640,480));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		FlowLayout flowlayout = new FlowLayout(FlowLayout.LEFT);//put things to the left
		GridLayout gridlayout = new GridLayout(2,1);//split the frame in two parts
		bankPanel = new JPanel(flowlayout);
		playerPanel = new JPanel(flowlayout);
		JPanel topPanel = new JPanel(flowlayout);
		JPanel centerPanel = new JPanel(gridlayout);
		topPanel.add(anotherCard);
		topPanel.add(noMoreCard);
		topPanel.add(Reset);
		anotherCard.setActionCommand("key1");
		anotherCard.addActionListener(this);
		noMoreCard.setActionCommand("key2");
		noMoreCard.addActionListener(this);
		Reset.setActionCommand("key3");
		Reset.addActionListener(this);
		frame.add(topPanel, BorderLayout.NORTH);
		centerPanel.add(bankPanel, BorderLayout.NORTH);
		bankPanel.setBorder(BorderFactory.createTitledBorder("Bank"));
		centerPanel.add(playerPanel, BorderLayout.SOUTH);
		playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
		frame.add(centerPanel,BorderLayout.CENTER);
		
		
		
		
		frame.pack();
		frame.setVisible(true);
		
		updatePlayerPanel();
		
		updateBankPanel();

	}
	public void addToPanel(JPanel p, String token) throws FileNotFoundException{
		File file = new File("D:\\workspace\\Java\\BlackJack\\src\\CartesPNG\\"+ token +".png");
		if(!file.exists()) {
			throw new FileNotFoundException("Can't find"+file.getPath());
		}
		ImageIcon icon = new ImageIcon(file.getPath());//create the image from the filename
		JLabel label = new JLabel(icon);//associate the image to a label
		p.add(label);//add the label to a panel
	}
	public void updateBankPanel() {
		for(int i=0; i < bj.getBankCardList().size(); i++) {//add the cards that the player has in the playerPanel
			try{
				addToPanel(bankPanel, bj.getBankCardList().get(i).toString());
			}catch(FileNotFoundException ex){
				System.out.println(ex.getMessage());
			}	
		}
		bankScore.setText(" Bank Best: "+bj.getBankBest());
		bankPanel.add(bankScore);
		SwingUtilities.updateComponentTreeUI(bankPanel);
	}
	public void updatePlayerPanel()  {//update the playerPanel
		for(int i=0; i < bj.getPlayerCardList().size(); i++) {//add the cards that the player has in the playerPanel
			try{
				addToPanel(playerPanel, bj.getPlayerCardList().get(i).toString());
			}catch( FileNotFoundException ex){
				System.out.println(ex.getMessage());
			}
		}
		playerScore.setText(" Player Best: "+bj.getPlayerBest());
		playerPanel.add(playerScore);
	SwingUtilities.updateComponentTreeUI(playerPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e){// connect the button to the manager via the addActionListener method
		
		switch(e.getActionCommand()) {
			case "key1" :
				
				try {
					bj.playerDrawAnotherCard();
					playerPanel.removeAll();
				} catch (EmptyDeckException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(-1);
				}
				updatePlayerPanel();
				if(bj.isGameFinished()) {
					anotherCard.setEnabled(false);
					noMoreCard.setEnabled(false);
					if(bj.isPlayerWinner()) {
						playerLabel.setText("You Won!");
						playerPanel.add(playerLabel);
						bankLabel.setText("You lost!");
						bankPanel.add(bankLabel);
					}
					else if(bj.isBankWinner()) {
						playerLabel.setText("You lost!");
						playerPanel.add(playerLabel);
						bankLabel.setText("You win!");
						bankPanel.add(bankLabel);
					}
					else if(!bj.isPlayerWinner() && !bj.isBankWinner()) {
						playerLabel.setText("Draw!");
						playerPanel.add(playerLabel);
						bankLabel.setText("Draw!");
						bankPanel.add(bankLabel);
					}
				}

			break;
			case "key2" :
				try {
					bj.bankLastTurn();
					bankPanel.removeAll();
				} catch (EmptyDeckException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(-1);
				}
				updateBankPanel();
				if(bj.isGameFinished()) 
				{
					noMoreCard.setEnabled(false);
					anotherCard.setEnabled(false);
					
					if(bj.isBankWinner()) 
					{
						bankLabel.setText("You Won!");
						bankPanel.add(bankLabel);
						playerLabel.setText("You Lost!");
						playerPanel.add(playerLabel);
					}else if(bj.isPlayerWinner()) 
					{
						bankLabel.setText("You Lost!");
						bankPanel.add(bankLabel);
						playerLabel.setText("You Won!");
						playerPanel.add(playerLabel);
					}
					else if(!bj.isPlayerWinner() && !bj.isBankWinner() && bj.isBankWinner()== bj.isPlayerWinner()) {
						playerLabel.setText("Draw!");
						playerPanel.add(playerLabel);
						bankLabel.setText("Draw!");
						bankPanel.add(bankLabel);
					}	
				}
				
				break;
			case "key3" :
				bj.reset();
				bj.gameFinished=false;
				playerPanel.removeAll();
				bankPanel.removeAll();
				anotherCard.setEnabled(true);
				noMoreCard.setEnabled(true);
				updateBankPanel();
				updatePlayerPanel();
				
				break;
			}
		
	}
	public static void main(String[] args) {
		new BlackJackGUI();
	}
}
