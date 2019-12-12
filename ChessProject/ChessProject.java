import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
  JLayeredPane layeredPane;
  JPanel chessBoard;
  JLabel chessPiece;
  int xAdjustment;
  int yAdjustment;
  int startX; //The x coordinate the piece started from.
  int startY;
  int initialX;
  int initialY;
  JPanel panels;
  JLabel pieces;
  Boolean whitePieceMoveFirst;
  Boolean possible;


  public ChessProject(){
    Dimension boardSize = new Dimension(600, 600);

    //  Use a Layered Pane for the application.
    layeredPane = new JLayeredPane();
    getContentPane().add(layeredPane);
    layeredPane.setPreferredSize(boardSize);
    layeredPane.addMouseListener(this);
    layeredPane.addMouseMotionListener(this);

    //Add a chess board to the Layered Pane.
    chessBoard = new JPanel();
    layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
    chessBoard.setLayout( new GridLayout(8, 8) );
    chessBoard.setPreferredSize( boardSize );
    chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

    for (int i = 0; i < 64; i++) {
      JPanel square = new JPanel( new BorderLayout() );
      chessBoard.add( square );

      int row = (i / 8) % 2;
      if (row == 0)
      square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
      else
      square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
    }

    // Setting up the Initial Chess board.
    for(int i=8;i < 16; i++){
      pieces = new JLabel( new ImageIcon("WhitePawn.png") );
      panels = (JPanel)chessBoard.getComponent(i);
      panels.add(pieces);
    }
    pieces = new JLabel( new ImageIcon("WhiteRook.png") );
    panels = (JPanel)chessBoard.getComponent(0);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
    panels = (JPanel)chessBoard.getComponent(1);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
    panels = (JPanel)chessBoard.getComponent(6);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
    panels = (JPanel)chessBoard.getComponent(2);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
    panels = (JPanel)chessBoard.getComponent(5);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("WhiteKing.png") );
    panels = (JPanel)chessBoard.getComponent(3);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
    panels = (JPanel)chessBoard.getComponent(4);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("WhiteRook.png") );
    panels = (JPanel)chessBoard.getComponent(7);
    panels.add(pieces);
    for(int i=48;i < 56; i++){
      pieces = new JLabel( new ImageIcon("BlackPawn.png") );
      panels = (JPanel)chessBoard.getComponent(i);
      panels.add(pieces);
    }
    pieces = new JLabel( new ImageIcon("BlackRook.png") );
    panels = (JPanel)chessBoard.getComponent(56);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("BlackKnight.png") );
    panels = (JPanel)chessBoard.getComponent(57);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("BlackKnight.png") );
    panels = (JPanel)chessBoard.getComponent(62);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("BlackBishop.png") );
    panels = (JPanel)chessBoard.getComponent(58);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("BlackBishop.png") );
    panels = (JPanel)chessBoard.getComponent(61);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("BlackKing.png") );
    panels = (JPanel)chessBoard.getComponent(59);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("BlackQueen.png") );
    panels = (JPanel)chessBoard.getComponent(60);
    panels.add(pieces);
    pieces = new JLabel( new ImageIcon("BlackRook.png") );
    panels = (JPanel)chessBoard.getComponent(63);
    panels.add(pieces);
    possible = false;
    whitePieceMoveFirst = true;
  }

  /*
  This piece of code checks if there is a chess piece present on a particular square of the board.
  */
  private Boolean piecePresent(int x, int y){
    Component c = chessBoard.findComponentAt(x, y);
    if(c instanceof JPanel){
      return false;
    }
    else{
      return true;
    }
  }

  /*
  This is a piece of code to check if a piece is a Black piece.
  */
  private Boolean checkWhiteOponent(int newX, int newY){
    Boolean oponent;
    Component c1 = chessBoard.findComponentAt(newX, newY);
    JLabel awaitingPiece = (JLabel)c1;
    String tmp1 = awaitingPiece.getIcon().toString();
    if(((tmp1.contains("Black")))){
      oponent = true;
      if(tmp1.contains("King")){
        JOptionPane.showMessageDialog(null,"White Wins \n Game Over!!!!!");
        System.exit(0);
      }
    }
    else{
      oponent = false;
    }
    return oponent;
  }

  private Boolean checkBlackOponent(int newX, int newY){
    Boolean oponent;
    Component c1 = chessBoard.findComponentAt(newX, newY);
    JLabel awaitingPiece = (JLabel)c1;
    String tmp1 = awaitingPiece.getIcon().toString();
    if(((tmp1.contains("White")))){
      oponent = true;
      if(tmp1.contains("King")){
        JOptionPane.showMessageDialog(null,"Black Wins \n Game Over!!!!!");
        System.exit(0);
      }
    }
    else{
      oponent = false;
    }
    return oponent;
  }
  //The piece of code below ensures only the enemy piece is taken and returns a validMove
  private Boolean ensureOnlyEnemyPieceCanBeKilled(int newX, int newY, String pieceName){
    Boolean validMove = false;
    System.out.println(pieceName);
    if(piecePresent(newX, newY)){
        if(pieceName.contains("White")){
          if(checkWhiteOponent(newX, newY)){
            validMove = true;
            return validMove;
          }
          else{
            validMove = false;
            return validMove;
          }
        }
        else{
          if(checkBlackOponent(newX, newY)){
            validMove = true;
            return validMove;
          }
          else{
            validMove = false;
            return validMove;
          }
        }
      }
    else{
      validMove = true;
      return validMove;
      }
  }

  private String pName(int newX, int newY){
    Component c = chessBoard.findComponentAt(newX, newY);
    if(c instanceof JLabel){
      JLabel awaitingPiece = (JLabel) c;
      String tmp1 = awaitingPiece.getIcon().toString();
      return tmp1;
    }
    else{
      return "";
    }
  }

  private Boolean underKingsRadar(int newX, int newY){
    if((piecePresent(newX, newY+75)&& pName(newX, newY+75).contains("King")||(piecePresent(newX, newY-75)&& pName(newX, newY-75).contains("King")))){
      return  true;
    }
    else if((piecePresent(newX+75,newY)&& pName(newX+75, newY).contains("King")||(piecePresent(newX-75, newY)&& pName(newX-75, newY).contains("King")))){
      return true;
    }
    else if((piecePresent(newX-75, newY+75)&& pName(newX-75, newY+75).contains("King")||(piecePresent(newX+75, newY-75)&& pName(newX+75, newY-75).contains("King")))){
      return  true;
    }
    else if((piecePresent(newX-75, newY-75)&& pName(newX-75, newY-75).contains("King")||(piecePresent(newX+75, newY+75)&& pName(newX+75, newY+75).contains("King")))){
      return  true;
    }
    return false;
  }

  /*
  This piece of code is called when we press the Mouse. So we need to find out what piece we have
  selected and make sure a piece was clicked!.
  */
  public void mousePressed(MouseEvent e){
    chessPiece = null;
    Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
    if (c instanceof JPanel)
    return;

    Point parentLocation = c.getParent().getLocation();
    xAdjustment = parentLocation.x - e.getX();
    yAdjustment = parentLocation.y - e.getY();
    chessPiece = (JLabel)c;
    initialX = e.getX();
    initialY = e.getY();
    startX = (e.getX()/75);
    startY = (e.getY()/75);
    chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
    chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
    layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
  }

  public void mouseDragged(MouseEvent me) {
    if (chessPiece == null) return;
    chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
  }

  /*
  This piece of code is used when the Mouse is released...we need to make sure the move was valid before
  putting the piece back on the board.
  */
  public void mouseReleased(MouseEvent e) {
    if(chessPiece == null) return;

    chessPiece.setVisible(false);
    Boolean success = false;
    Boolean progression = false;
    Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
    String tmp = chessPiece.getIcon().toString();
    String pieceName = tmp.substring(0, (tmp.length()-4));
    Boolean validMove = false;

    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);
    int xMovement = Math.abs((landingX-startX));
    int yMovement = Math.abs((landingY-startY));

    System.out.println("-------------");
    System.out.println("The peice being moved is : " +pieceName);
    System.out.println("Starting Coordinates: " +startX+ " , " +startY);
    System.out.println("X landing position on board: " +landingX);
    System.out.println("Y landing position on board: " +landingY);
    System.out.println("Landing Coordinates - X : " +landingX+ ", Y : " +landingY);
    System.out.println("X Movement : " +xMovement);
    System.out.println("Y Movement : " +yMovement);
    System.out.println("-------------");

    if(whitePieceMoveFirst){
  		if(pieceName.contains("White") && !(xMovement == 0 && yMovement == 0)){
  			possible = true;
  		}
  	}
  	else{
  		if(pieceName.contains("Black") && !(xMovement == 0 && yMovement == 0)){
  			possible = true;
  		}
  	}

    if(possible){
    if(pieceName.contains("King")){
      /**
      * The King can move any direction 1 square at a time and cannot jump a piece,
      * King can not take its own pieces.
      * Their, has to be 1 square distance between opponent Kings
      * Can only take oppenent pieces.
      */
      Boolean inTheWay = false;
      int distance = Math.abs(startX-landingX);
      //Moves one Square at a time.
      if(xMovement > 1 || yMovement > 1 || (xMovement == 0 && yMovement == 0)){
        validMove = false;
      }
      else{
        //this statement below checks if the piece is placed on the board.
        if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){
          validMove = false;
        }
        else{
          //Their, has to be 1 square distance between opponent Kings
          if(!(underKingsRadar(e.getX(), e.getY()))){
            if(!piecePresent(e.getX(), e.getY())){
              validMove = true;
            }
            else{
              validMove = ensureOnlyEnemyPieceCanBeKilled(e.getX(), e.getY(), pieceName);
            }
          }
        }
      }
    }
    //End of King Piece code.

    else if(pieceName.contains("Queen")){
      //Queen's moves: Compound of Rook and Bishop
      //The Queen can move in a horizontal, vertical or diagonal direction as long as the path is clear.
      Boolean inTheWay = false;
      int distance = Math.abs(startX-landingX);
      //This statement below checks if the piece is placed on the board.
      if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){
        validMove = false;
      }
      /*
      *The code below adds the Queen to move like a Rook
      *Queen can move horzontially or vertically
      */
      else if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
        if(Math.abs(startX-landingX)!=0){
          if(startX-landingX > 0){
            for(int i = 0; i < xMovement; i++){
              if(piecePresent(initialX-(i*75), e.getY())){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
          else{
            for(int i=0; i < xMovement; i++){
              if(piecePresent(initialX+(i*75),e.getY())){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
        }
        else{
          if(startY-landingY > 0){
            for(int i=0; i < yMovement; i++){
              if(piecePresent(e.getX(),initialY-(i*75))){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
          else{
            for(int i=0; i < yMovement; i++){
              if(piecePresent(e.getX(),initialY+(i*75))){
                inTheWay = true;
                break;
              }
              else{
                inTheWay = false;
              }
            }
          }
        }

        if(inTheWay){
          validMove = false;
        }
        else{
          //Checks if the piece in the way is an opponent piece and returns a Boolean.
          validMove = ensureOnlyEnemyPieceCanBeKilled(e.getX(), e.getY(), pieceName);
        }
      }
      /*
      *The code below allows the Queen to move like a Bishop
      *The Queen can now do valid bishop movement i.e diagonally
      */
      else{
        if(Math.abs(startX-landingX) == Math.abs(startY-landingY)){
          if((startX-landingX < 0)&&(startY-landingY < 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX < 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)),initialY-(i*75))){
                inTheWay=true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)),initialY-(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY < 0 )){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          if(inTheWay){
            validMove = false;
          }
          else{
            //Checks if the piece in the way is an opponents piece.
            validMove = ensureOnlyEnemyPieceCanBeKilled(e.getX(), e.getY(), pieceName);
          }
        }

        else{
          validMove = false;
        }
      }
    }
    //End of Queen Piece code.

    else if(pieceName.contains("Knight")){
      /*
      *Knights moves:
      *Knight can only move in a L direction(meaning it can move one square in the X-axis and two in the Y-axis
      *Or two in the X-axis and one in the Y-axis and the other way around too.
      *We need to check the square that we are moving to and make sure that if
      *there is a piece present that is an oppenent piece to take it.
      */

      //this statement below checks if the piece is placed on the board.
  		if(((landingX < 0)||(landingX > 7))||((landingY < 0)||landingY > 7)){
  			validMove = false;
  		}
  		else{
  			if(((landingX == startX+1)&&(landingY == startY+2))||((landingX == startX-1)&&(landingY ==
  			startY+2))||((landingX == startX+2) && (landingY == startY+1))||((landingX == startX-2) &&(landingY ==
  			startY+1))||((landingX == startX+1) && (landingY == startY-2))||((landingX == startX-1) &&(landingY ==
  			startY-2))||((landingX == startX+2) && (landingY == startY-1))||((landingX == startX-2) &&(landingY ==
  			startY-1))){
          //Checks if the piece in the way is an opponents piece.
  				validMove = ensureOnlyEnemyPieceCanBeKilled(e.getX(), e.getY(), pieceName);
  			}
  			else{
  				validMove = false;
  			}
  		}
  	}
    //End of Knight code.

    else if(pieceName.contains("Rook")){
      //Rook's Moves:
      //Rook can either move horizontally or vertically.
      //It can move any number of squares but cannot pass/jump through a piece.
      Boolean inTheWay = false;
      //this statement below checks if the piece is placed on the board.
      if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){
        validMove = false;
      }
      else{
        if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){
          if(Math.abs(startX-landingX)!=0){
            if(startX-landingX > 0){
              for(int i = 0; i < xMovement; i++){
                if(piecePresent(initialX-(i*75), e.getY())){
                  inTheWay = true;
                  break;
                }
                else{
                  inTheWay = false;
                }
              }
            }
            else{
              for(int i=0; i < xMovement; i++){
                if(piecePresent(initialX+(i*75),e.getY())){
                  inTheWay = true;
                  break;
                }
                else{
                  inTheWay = false;
                }
              }
            }
          }
          else{
            if(startY-landingY > 0){
              for(int i=0; i < yMovement; i++){
                if(piecePresent(e.getX(),initialY-(i*75))){
                  inTheWay = true;
                  break;
                }
                else{
                  inTheWay = false;
                }
              }
            }
            else{
              for(int i=0; i < yMovement; i++){
                if(piecePresent(e.getX(),initialY+(i*75))){
                  inTheWay = true;
                  break;
                }
                else{
                  inTheWay = false;
                }
              }
            }
          }

          if(inTheWay){
            validMove = false;
          }
          else{
            //Checks if the piece in the way is an opponents piece.
            validMove = ensureOnlyEnemyPieceCanBeKilled(e.getX(), e.getY(), pieceName);
          }
        }
        else{
          validMove = false;
        }
      }
    }
    //End of Rook code.

    else if(pieceName.contains("Bishop")){
      /*
      *Bishop's moves:
      *The bishop may move any number of squares on the same colured squares in a diagonal direction.
      *until it is prevented from continuing by another piece.
      *It may then capture the opposing piece by landing on the square.
      *Bishop can't jump pieces.
      */
      Boolean inTheWay = false;
      int distance = Math.abs(startX-landingX);
      //this statement below makes sure the chess piece is place on the board.
      if(((landingX < 0)||(landingX > 7)) || ((landingY < 0)||(landingY > 7))){
        validMove = false;
      }
      else{
        validMove = true;
        if(Math.abs(startX-landingX) == Math.abs(startY-landingY)){
          if((startX-landingX < 0)&&(startY-landingY < 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX < 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX+(i*75)),initialY-(i*75))){
                inTheWay=true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY > 0)){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)),initialY-(i*75))){
                inTheWay = true;
              }
            }
          }
          else if((startX-landingX > 0)&&(startY-landingY < 0 )){
            for(int i = 0; i < distance; i++){
              if(piecePresent((initialX-(i*75)), initialY+(i*75))){
                inTheWay = true;
              }
            }
          }
          if(inTheWay){
            validMove = false;
          }
          else{
            //Checks if the piece in the way is an opponent piece.
            validMove = ensureOnlyEnemyPieceCanBeKilled(e.getX(), e.getY(), pieceName);
          }
        }
        else{
          validMove = false;
        }
      }
    }
    //End of Bishop code.

    /*

    If the pawn is makeing its first movement...
    the Pawn can either move one or two squares... in the Y-axis
    as long as we are moving up the board!! Also there is no movement in the X-axis.

    */

    else if(pieceName.equals("BlackPawn")){
      if((startY == 6)&&(startX == landingX)&&(((startY-landingY) == 1)||(startY-landingY)==2)){
        if(!piecePresent(e.getX(), e.getY())){
          validMove = true;
        }
        else{
          validMove = false;
        }
      }
      else if ((Math.abs(startX-landingX)==1)&&(((startY-landingY)==1))){
        if(piecePresent(e.getX(), e.getY())){
          if(checkBlackOponent(e.getX(), e.getY())){
            validMove = true;
            if(landingY == 0){
              progression = true;
            }
          }
          else{
            validMove = false;
          }
        }
        else{
          validMove = false;
        }
      }
      else if((startY != 6)&&((startX == landingX)&&(((startY-landingY)== 1)))){
        if(!piecePresent(e.getX(), e.getY())){
          validMove = true;
          if(landingY == 0){
            progression = true;
          }
        }
        else{
          validMove = false;
        }
      }
      else{
        validMove = false;
      }
    }
    //End of Black Pawn code.

    /*
    The only piece that has been enabled to move is the White pawn... but we should really have this in a separate
    method somewhere... how would this work.

    so a Pawn is able to move one or two squares forward on it's first go but only one square after that.
    The Pawn is the only piece that cannot move backwards in chess... so be carful when comitting
    a Pawn forward. A Pawn is able to take  any of the oppenent's pieces but they have to be in a diagonal direction of the Pawns original position.
    If a Pawn makes it to the top of the other side, the pawn can turn into any other piece, for
    demonstration purposes the Pawn here turns into a Queen.
    */

    else if(pieceName.equals("WhitePawn")){
      if((startY == 1)&&(startX == landingX)&&(((landingY - startY) == 1) || (landingY - startY) == 2)){
        if((!piecePresent(e.getX(), e.getY())&&(!piecePresent(e.getX(), e.getY()-75)))){
          validMove = true;
        }
      }
      else if((Math.abs(landingX - startX) == 1)&&(((landingY - startY) == 1))){
        if (piecePresent(e.getX(), e.getY())) {
          if(checkWhiteOponent(e.getX(), e.getY())){
            validMove = true;
            if(landingY == 7){
              success = true;
            }
          }
          else{
            validMove = false;
          }
        }
        else{
          validMove = false;
        }
      }
      else if((startY != 1)&&((startX == landingX)&&(((landingY - startY) == 1)))){
        if(!piecePresent(e.getX(), e.getY())){
          validMove = true;
          if(landingY == 7){
            success = true;
          }
        }
        else{
          validMove = false;
        }
      }
      else{
        validMove = false;
      }
    }
    //End of White Pawn code.
  }
  //End of Possible Wrapper code.

  if(!validMove){
    int location=0;
    if(startY ==0){
      location = startX;
    }
    else{
      location  = (startY*8)+startX;
    }
    String pieceLocation = pieceName+".png";
    pieces = new JLabel(new ImageIcon(pieceLocation));
    panels = (JPanel)chessBoard.getComponent(location);
      panels.add(pieces);
  }
  else{
    whitePieceMoveFirst = !whitePieceMoveFirst;
    possible = false;

    if(progression){
      int location = 0 + (e.getX()/75);
      if(c instanceof JLabel){
        Container parent = c.getParent();
        parent.remove(0);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        parent = (JPanel)chessBoard.getComponent(location);
        parent.add(pieces);
      }
    }
    else if(success){
      int location = 56 + (e.getX()/75);
      if (c instanceof JLabel){
        Container parent = c.getParent();
        parent.remove(0);
        pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
        parent = (JPanel)chessBoard.getComponent(location);
        parent.add(pieces);
      }
      else{
        Container parent = (Container)c;
        pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
        parent = (JPanel)chessBoard.getComponent(location);
        parent.add(pieces);
      }
    }
    else{
      if(c instanceof JLabel){
        Container parent = c.getParent();
        parent.remove(0);
        parent.add( chessPiece );
      }
      else{
        Container parent = (Container)c;
        parent.add( chessPiece );
      }
      chessPiece.setVisible(true);
    }
  }
}

  public void mouseClicked(MouseEvent e) {

  }
  public void mouseMoved(MouseEvent e) {
  }
  public void mouseEntered(MouseEvent e){

  }
  public void mouseExited(MouseEvent e) {

  }

  /*
  Main piece of code that gets the game started.
  */
  public static void main(String[] args) {
    JFrame frame = new ChessProject();
    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
    frame.pack();
    frame.setResizable(true);
    frame.setLocationRelativeTo( null );
    frame.setVisible(true);
  }
}
