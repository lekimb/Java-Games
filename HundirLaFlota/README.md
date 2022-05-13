# Hundir-la-Flota
Versión para terminal del conocido juego de mesa Hundir la Flota. Se trata de un juego para un solo jugador, cuyo objetivo consistirá en hundir todos los barcos del tablero generados al azar por el programa. 
Puede escogerse entre tres niveles de dificultad (fácil, medio o difícil), variando con ello la cantidad y tipo de barcos, así como el número de disparos permitidos. 

## Clases
Las clases implementadas son las siguientes:
- `Menu:` El menú principal permite iniciar una nueva partida o salir del juego. Si optamos por iniciar partida, otro menú nos permitirá elegir el nivel de dificultad de la misma.
- `Partida:` Contiene los datos y funciones necesarios para generar y jugar la partida. Requiere un parametro numérico "nivel de dificultad" para instanciarse. 
- `Coordenada:` Clase auxiliar de la clase Partida. Permite un manejo más limpio y menos propenso a errores de las coordenadas del tablero. 
- `Vista:` Ecargada de pintar cada turno en pantalla el estado actual del tablero, los disparos restantes y los mensajes de aviso ('tocado', 'hundido', 'agua')
- `ConsoleColors:` Clase auxiliar de la clase Vista. Permite mostrar en distintos colores los mensajes de aviso.
- `GameController:` Ejecuta el bucle de juego. Pone en comunicación las clases Partida y Vista.
