# FourInRow

El programa representa un juego similar a "Connect Four" en una matriz de 7x7.

Componentes Principales:

Tablero: Representado con una matriz de botones (JButton) de tamaño 7x7.
Jugadores: Hay dos jugadores - 'X' (Player 1) y 'O' (Player 2 o Computadora).
Juego:

El objetivo del juego es conectar cuatro de sus propias fichas consecutivamente en línea recta (verticalmente, horizontalmente o diagonalmente).
El juego comienza con la última fila (inferior) del tablero, que está disponible para ambos jugadores. El resto del tablero se inicializa como "no disponible".
Los jugadores juegan turnos. Si la opción playAgainstComputer es verdadera, el Player 2 es controlado por el ordenador, que hace movimientos aleatorios en las columnas disponibles.
Cuando un jugador logra conectar cuatro fichas, se muestra un mensaje anunciando al ganador. Si el jugador 2 (la computadora) gana, el mensaje será "La computadora ha ganado".
Interacción:

Los jugadores interactúan haciendo clic en los botones de la matriz.
Al hacer clic en un botón disponible, el botón mostrará el símbolo del jugador (ya sea 'X' o 'O') y se bloqueará.
Verificación de la Victoria:

Después de cada movimiento, el programa verifica si hay cuatro fichas consecutivas en cualquier dirección (horizontal, vertical o diagonal).
Si se detecta una victoria, se muestra un mensaje.
Reinicio:

Después de anunciar al ganador, el programa da la opción de reiniciar el juego o cerrarlo.
