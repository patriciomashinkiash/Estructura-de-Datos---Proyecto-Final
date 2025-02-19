# Proyecto Final

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/logo-ups.jpg)

- Carrera: Computación.
- Materia: Estructura de Datos.
- Fecha de entrega: 18/02/2025.
- Docente: Ing. Pablo Torres.
Integrantes:

◦ David Larriva [dlarrivac@est.ups.edu.ec](mailto:dlarriva@est.ups.edu.ec)

◦ John Tigre [jtigrec4@est.ups.edu.ec](mailto:jtigrec4@est.ups.edu.ec)

◦ Patricio Mashinkiash [pmashinkiash@est.ups.edu.ec](mailto:pmashinkiash@est.ups.edu.ec )

## Descripción del Problema

- El proyecto consiste en desarrollar una aplicación que permita simular y analizar el recorrido de un laberinto mediante algoritmos de búsqueda como BFS (Breadth-First Search), DFS (Depth-First Search), búsqueda recursiva y con memorización (Cache). 

- El sistema deberá permitir la configuración dinámica del tamaño del laberinto, la selección de celdas no transitables (obstáculos) y la elección de puntos de inicio y destino para la simulación.

- El objetivo principal del proyecto es encontrar la ruta más óptima entre dos puntos dentro del laberinto, respetando las restricciones de movimiento (puede desplazarse hacia arriba, abajo, izquierda o derecha) y evitando obstáculos que bloqueen el camino.

- Además, la aplicación debe ser capaz de comparar los tiempos de ejecución de los diferentes algoritmos para determinar cuál de ellos es el más eficiente en distintos escenarios de prueba.
_
## Propuesta de Solución

### Marco Teórico
#### 1. Teoría de Grafos
La teoría de grafos es un campo de estudio matemático que modela relaciones entre elementos mediante nodos (vértices) y conexiones (aristas). En este proyecto, el laberinto se representa como un grafo donde las celdas son nodos y sus conexiones representan caminos disponibles.

#### 2. Algoritmos de Búsqueda Implementados
- Programación Dinámica: Optimiza la búsqueda almacenando resultados previos para evitar cálculos redundantes.  
- BFS (Breadth-First Search): Explora el laberinto por niveles, asegurando encontrar el camino más corto.  
- DFS (Depth-First Search): Explora el laberinto en profundidad, siguiendo un camino hasta el final antes de retroceder.  
- Recursividad: Implementa la búsqueda de manera estructurada a través de llamadas recursivas.  

## Desarrollo
El proyecto fue desarrollado siguiendo el patrón MVC (Modelo-Vista-Controlador), facilitando la organización del código y su mantenimiento.

### Herramientas Utilizadas
- Lenguaje: Java  
- IDE: Visual Studio Code
- Framework: Java Swing para la interfaz gráfica  
- Control de Versiones: Git y GitHub  

### Explicación de las Clases
- Modelo (Model): Define la estructura del laberinto y los algoritmos implementados.  
- Vista (View): Contiene la interfaz gráfica para la interacción con el usuario.  
- Controlador (Controller): Administra la lógica del sistema y la comunicación entre el modelo y la vista.  

## Interfaz de Usuario (UI)
La aplicación cuenta con tres interfaces principales para configurar y ejecutar la simulación del laberinto.

### Interfaz 1: Configuración del Laberinto
- Permite ingresar el tamaño del laberinto (3x3 a 9x9).
- Valida la entrada del usuario antes de continuar.  
- Botón "Siguiente" para avanzar a la siguiente configuración.  

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/Interfaz1.png)

### Interfaz 2: Selección de Obstáculos
- Muestra el laberinto visualmente.  
- Permite marcar hasta 4 celdas como obstáculos.  
- Botón "Siguiente" para continuar con la simulación.  

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/Interfaz2.png)

### Interfaz 3: Simulación del Laberinto
- Permite establecer punto de inicio y fin en la cuadrícula.  
- Visualiza la ruta generada por el algoritmo seleccionado.  
- Opción "Limpiar" para reiniciar la simulación.  
- Muestra tiempos de ejecución en nanosegundos para comparar eficiencia.  

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/Interfaz3.png)
 
En las siguientes imagenes se  mostrara el camino y tiempo que realiza cada uno de los algoritmos.
- Programación Dinamica: 

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/Interfaz3%20CAC.png)

- BFS (Breadth-First Search):

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/Interfaz3%20BFS.png)

- DFS (Depth-First Search): 

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/Interfaz3%20DFS.png)

- Recursividad:

![image alt](https://github.com/patriciomashinkiash/Estructura-de-Datos---Proyecto-Final/blob/e75e505e5644b53143cd22038ec61fc01d11fcba/images/Interfaz3%20REC.png)

## Criterio por Estudiante

- Patricio Mashinkiash:  
  Durante el desarrollo del proyecto, optamos por implementar varios algoritmos de búsqueda (BFS, DFS, Recursivo y Cache) con el objetivo de analizar cuál de ellos era más eficiente para encontrar la ruta óptima en un laberinto. Consideramos que era fundamental incluir una comparación de tiempos de ejecución, ya que en la vida real, estos algoritmos se aplican en problemas como la navegación GPS y la inteligencia artificial en juegos. Personalmente, me pareció interesante trabajar con la lógica de los algoritmos y comprobar cómo, dependiendo del escenario, algunos eran más efectivos que otros. En retrospectiva, creo que habría sido beneficioso optimizar aún más la gestión de memoria, especialmente en la implementación recursiva, y quizás explorar alternativas como A* para mejorar la búsqueda de caminos óptimos.



- John Tigre:  
  La elección de Java Swing para la interfaz gráfica se debió a su facilidad de integración con el resto del código y su compatibilidad con el patrón MVC. Aunque el resultado fue funcional, me hubiera gustado explorar otras opciones de diseño para hacer la interfaz más interactiva y amigable para el usuario. Durante el desarrollo, enfrenté desafíos en la representación visual del laberinto y en la selección de obstáculos, lo que me llevó a pensar que un enfoque basado en eventos gráficos, en lugar de entrada manual de coordenadas, habría mejorado la experiencia del usuario. En general, considero que este proyecto fue una excelente oportunidad para aplicar conceptos de estructuras de datos y algoritmos de búsqueda en un entorno práctico.

- David Larriva:  
  Optamos por utilizar el patrón MVC para mantener una estructura modular y facilitar la escalabilidad del código. Esto permitió una separación clara entre la lógica de los algoritmos, la interfaz gráfica y el controlador, lo que resultó en un código más organizado y fácil de mantener. La implementación de GitHub como herramienta de control de versiones fue esencial para el trabajo colaborativo, permitiéndonos gestionar cambios y resolver conflictos de manera eficiente. En términos generales, creo que el proyecto fue un buen ejercicio de aplicación de estructuras de datos, pero aún hay margen para mejorar en cuanto a optimización de código y diseño de la interfaz. Como posible mejora, se podría agregar la opción de guardar y cargar laberintos previamente configurados, lo que facilitaría la ejecución de pruebas comparativas sin necesidad de repetir manualmente la configuración en cada intento.

## Conclusión  

En la implementación de los diferentes métodos, se observaron los siguientes resultados:

- Método Recursivo: Funcional, pero su eficiencia disminuye en laberintos grandes por el alto consumo de memoria.  
- Programación Dinámica (Cache): Optimiza el rendimiento evitando cálculos redundantes.  
- BFS: El método más eficiente para encontrar la ruta más corta gracias a su exploración por niveles.  
- DFS: Puede ser más rápido en algunos casos, pero no siempre garantiza la mejor ruta y usa más memoria en laberintos grandes.  
_

##  Consideraciones  

- Patricio Mashinkiash:  
  Se logró implementar correctamente los algoritmos de búsqueda (BFS, DFS, Recursivo y Cache), pero se podría optimizar la gestión de memoria en búsquedas más complejas. Además, sería interesante añadir una funcionalidad para visualizar múltiples soluciones cuando existen varios caminos óptimos dentro del laberinto.  

- John Tigre:  
  La interfaz gráfica cumple con su función, pero podría mejorarse en términos de interactividad y diseño. Agregar animaciones o un sistema de colores más intuitivo ayudaría a que el usuario comprenda mejor el recorrido de cada algoritmo. También sería útil permitir la selección de obstáculos de una manera más visual, como haciendo clic en las celdas del laberinto en lugar de ingresar coordenadas manualmente.  

- David Larriva:  
  La estructura del código sigue el patrón MVC, lo cual mejora la organización, pero aún se pueden realizar optimizaciones. Sería útil reducir la cantidad de clases y mejorar la reutilización de código para evitar redundancias. Además, se podría implementar una funcionalidad de exportación para guardar los laberintos y resultados, lo que permitiría analizar diferentes escenarios sin necesidad de reconfigurar todo desde cero.
