import javax.swing.JInternalFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class PlayerTurnsInternalFrame  extends JInternalFrame
{


    public JLabel q, WhichTurns1; //q de question, WhichTurns escribe de quien es el turno
    public JButton firstTurn1 , firstTurn2, Exit; //firstTurn1 = boton para seleccionar el jugador 1 y firstTurn2 = boton para seleccionar el jugador 2
    public int turn; //turn guarda el numero 1 o 2 dependiendo de que jugador tiene el turno
    public boolean firstTurn = true; //firstTurn indica si es la primera ronda de juego o no
    public JButton playComputer; // Button to play against computer


    public PlayerTurnsInternalFrame()
    {
        // get content pane and set its layout
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        q = new JLabel("Who wants to start?"); //declarar JLabel
        container.add(q); //agregar al contenedor

        firstTurn1 = new JButton("Player 1"); //declarar boton de jugador 1
        container.add(firstTurn1); //agregar al contenedor

        firstTurn2 = new JButton("Player 2"); //declarar boton de jugador 2
        container.add(firstTurn2); //agregar al contenedor

        WhichTurns1 = new JLabel(" "); //declarar JLabel
        container.add(WhichTurns1); //agregar al contenedor

        Exit = new JButton("Exit"); //declarar boton de salida
        container.add(Exit); //agregar al contenedor - todavia no sirve

        ButtonHandler handler = new ButtonHandler(); //crear un nuevo handler
        firstTurn1.addActionListener( handler ); //register JBUttons to receive events from ButtonHandler - boton para jugador 1
        firstTurn2.addActionListener( handler ); //register JBUttons to receive events from ButtonHandler - boton para jugador 2

        setSize(275, 100); //formatear tamaño
        setVisible(true); //hacer visible
    }

    private class ButtonHandler implements ActionListener
    {
        //handle button event
        public void actionPerformed(ActionEvent event)
        {

            if (firstTurn == true) //si el juego se encuentra en su primera ronda de turnos
            {
                if (event.getSource() == firstTurn1) //si origen de la acción es igual al boton del jugador 1
                {
                    turn = 1; //dar el turno al jugador 1
                    Tablero newGame = new Tablero(false); //llamar al Tablero
                    newGame.turn = 1; //hacer que dentro del tablero el turno sea del jugador 1

                    String turn1 = Integer.toString(turn); //convertir la variable int del turno a string
                    WhichTurns1.setText("It's your turn player " + turn1); //cambiar el texto de la JLabel para que diga de quien es el turno
                    turn = 2; //pasar el turno al jugador 2

                    firstTurn = false; //converitr la variable del primer turno a falso

                    if (firstTurn == false) // si la variable firstTurn es falsa
                    {
                        //hacer que los botones de ambos jugadores, el boton de salida, y la JLabel que pregunta quien empezara dejen de ser visibles
                        q.setVisible(false);
                        Exit.setVisible(false);
                        firstTurn1.setVisible(false);
                        firstTurn2.setVisible(false);
                    }
                }
                if (event.getSource() == firstTurn2)
                {
                    turn = 2; //pasar el turno al jugador 2
                    Tablero newGame = new Tablero(false); //llamar al Tablero
                    newGame.turn = 2;  //hacer que dentro del tablero el turno sea del jugador 2

                    String turn1 = Integer.toString(turn); //convertir la variable int del turno a string
                    WhichTurns1.setText("It's your turn player " + turn1); //cambiar el texto de la JLabel para que diga de quien es el turno
                    turn = 1; //pasar el turno al jugador 1
                    firstTurn = false; //converitr la variable del primer turno a falso

                    if (firstTurn == false) // si la variable firstTurn es falsa
                    {
                        //hacer que los botones de ambos jugadores, el boton de salida, y la JLabel que pregunta quien empezara dejen de ser visibles
                        q.setVisible(false);
                        Exit.setVisible(false);
                        firstTurn1.setVisible(false);
                        firstTurn2.setVisible(false);
                    }
                }
            }
        }
    }
}
