# Saber-y-Ganar
Programa que simula un concurso televisivo tipo "Saber y Ganar". Los jugadores responden preguntas por rondas, resultando ganador aquel que más preguntas correctas haya contestado al final de todas las rondas.

El juego es personalizable, pudiendo escogerse la duración en rondas y el número de jugadores antes de comenzar cada partida. El número máximo de jugadores es 6 y la duración de la partida podrá oscilar entre 3 y 10 rondas. 

Antes de jugar, los jugadores deberán registrarse con un alias único a través de un menú dedicado. Los jugadores registrados se almacenan en un archivo `registro.txt`, que hace las veces de base de datos. Si un jugador no figura en el registro no podrá escogerse para jugar la partida. 

Al finalizar cada partida, ésta queda registrada en el histórico de partidas: `histórico.txt`. A partir de este archivo, se calcula y se muestra un ranking con los puntos totales acumulados por cada jugador.

## Clases implementadas
Las clases implementadas son: Partida, Jugador, Pregunta y Storage. Brevemente, la clase `Partida` se ocupa de la gestión de la partida, guardando un registro de los jugadores, el número de rondas que dura la partida, así como la ronda actual en que nos encontramos. La clase `Jugador` se ocupa de llevar la cuenta del marcador de cada jugador en la partida; también, incorpora métodos para responder preguntas y sumar punto. La clase `Pregunta` tiene enunciado, pregunta y tipo como atributos; permite instanciar preguntas de cada tipo. Por último, la clase `Storage` se encarga de la lectura y escritura de los archivos `registro.txt` e `histórico.txt`.

## Ampliación del juego
Aunque en la implementación actual del juego solo se incluyen preguntas de matemáticas, puede extenderse fácilmente para que incluya preguntas de otro tipo, como Inglés o Lengua. En el código de la clase Pregunta están indicadas, en forma de comentarios, las funciones que habrían de implementarse en tal caso.
