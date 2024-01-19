import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=650;

    JFrame frame=new JFrame("Tic Tac Toe");
    JLabel txtlbl=new JLabel();
    JPanel txtpnl=new JPanel();
    JPanel gameBoard=new JPanel();

    JButton [][] board=new JButton[3][3];

    String player1="X";
    String player2="O";
    String currentPlayer= player1;

    boolean gameOver=false;
    int turns=0;

    //constructor
    public TicTacToe() {
       frame.setVisible(true);
       frame.setSize(boardWidth,boardHeight);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(new BorderLayout());

       //text label
       txtlbl.setBackground(Color.blue);
       txtlbl.setForeground(Color.white);
       txtlbl.setFont(new Font("Ariel",Font.BOLD,50));
       txtlbl.setHorizontalAlignment(JLabel.CENTER);
       txtlbl.setText("Tic-Tac-Toe");
       txtlbl.setOpaque(true);

       //text panel
        txtpnl.setLayout(new BorderLayout());
        txtpnl.add(txtlbl);
        frame.add(txtpnl,BorderLayout.NORTH);

        //game board
        gameBoard.setLayout(new GridLayout(3,3));
        gameBoard.setBackground(Color.white);
        frame.add(gameBoard);

        //add tiles
        for(int r = 0; r < 3; r ++){
            for(int c=0; c<3; c++){
                JButton tile=new JButton();
                board[r][c]=tile;
                gameBoard.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                //tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameOver) return;
                        JButton tile=(JButton) e.getSource();
                        //preventing overwrite the tile
                        if(tile.getText()=="") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver) {
                                currentPlayer = currentPlayer == player1 ? player2 : player1;
                                txtlbl.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
     
    }

     void checkWinner() {
        //checking the horizontal
        for(int r=0; r<3; r++){
            if(board[r][0].getText()=="")continue;

            if(board[r][0].getText()==board[r][1].getText()&&
                    board[r][1].getText()==board[r][2].getText()){
                for(int i=0;i<3;i++){
                    setWinner(board[r][i]);
                }
                gameOver=true;
                return;
            }
        }
        //checking vertical
         for(int c=0;c<3;c++){
             if(board[0][1].getText()=="")continue;

             if(board[0][c].getText()==board[1][c].getText()&&
             board[1][c].getText()==board[2][c].getText()){
                 for(int i=0;i<3;i++){
                     setWinner(board[i][c]);
                 }
                 gameOver=true;
                 return;
             }
         }

         //checking diagonally
            if(board[0][0].getText()==board[1][1].getText()&&
                    board[1][1].getText()==board[2][2].getText()&&
                board[0][0].getText()!=""){
                for(int i=0;i<3;i++){
                    setWinner(board[i][i]);

                }
                gameOver=true;
                return;
            }
        //checking anti diagonally
        if(board[0][2].getText()==board[1][1].getText()&&
        board[1][1].getText()==board[2][0].getText()&&
                board[0][2].getText() !=""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver=true;
            return;
        }

        //checking whether it is tie or not
         if(turns==9){
             for(int r=0;r<3;r++){
                 for(int c=0;c<3;c++){
                     setTie(board[r][c]);
                 }
             }
             gameOver=true;
         }
    }

     void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.GRAY);
        txtlbl.setText(currentPlayer +" is the winner");

    }

    void setTie(JButton tile) {
        tile.setForeground(Color.red);
        tile.setBackground(Color.GRAY);
        txtlbl.setText("Tie");
    }
}
