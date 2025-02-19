# Proyecto Final
___
**Realizado por:**  Patricio Alexander Mashinkiash Mora
**Correo:** pmashinkiash@est.ups.edu.ec
![image alt](https://github.com/patriciomashinkiash/Proyecto-Final---Estructura-de-Datos/blob/dbc8cbb0d8f5dbb8cc642d7ba6c68765fdb4cce7/images/logo-ups.jpg)
# Descripcion del Problema
___
Se requiere desarrollar una aplicación que permita simular el recorrido de un laberinto utilizando algoritmos de búsqueda (BFS, DFS, Recursivo y Cache). El sistema debe permitir la configuración dinámica del tamaño del laberinto, la selección de celdas no transitables, así como la elección de un punto de inicio y de finalización. Además, se deben comparar los tiempos de ejecución de los diferentes algoritmos para determinar el más eficiente.
# Propuesta de solucion
___
Se desarrolló una aplicación en Java siguiendo el patrón MVC (Modelo-Vista-Controlador). Se utilizaron las librerías estándar de Java Swing para las interfaces gráficas y estructuras de datos para representar el laberinto.
## Marco Teorico
___
### 1. Teoría de Grafos
___
La teoria de grafos tiene origen matematico, y no informatico, sirve para resolver varios problemas matematicos e informaticos gracias. Un grafo es un conjunto de nodos que se relacionan entre si atraves de objetos conocidos como aristas, esta mecanica es usada para resolver problemas complejos en el area de matemticas discretas, ciencias sociales e informaticas.
#### 1.1. Nodos y Aristas
- **Nodos (Vértices):** Son los puntos de un grafo que representan entidades o elementos del sistema.
- **Aristas (Bordes)** Son las conexiones entre nodos que representan las relaciones o caminos posibles entre ellos. 
#### 1.2. Tipos de Grafos
- **Grafos Dirigidos:** Las aristas tienen una dirección, indicando un camino unidireccional.
- **Grafos No Dirigidos:** Las aristas no tienen dirección, indicando una conexión bidireccional.
- **Grafos Ponderados:** Las aristas tienen un peso o costo asociado, representando la "distancia" o el "costo" de la conexión.
### 2. Algoritmos de busqueda
- **Programación Dinámica:** Técnica que resuelve problemas complejos dividiéndolos en subproblemas más simples y almacenando los resultados para su reutilización. Esto permite optimizar el rendimiento, como se evidencia en el algoritmo Cache implementado.
- **BFS (Breadth-First Search):** Recorre el laberinto por niveles, explorando todas las celdas vecinas antes de pasar al siguiente nivel.
- **DFS (Depth-First Search):** Explora el laberinto en profundidad, siguiendo un camino hasta el final antes de retroceder y probar otras opciones.
- **Recursividad:** Estrategia que resuelve problemas al hacer que una función se llame a sí misma hasta alcanzar una condición base.
## Desarrollo
___
El proyecto se desarrolló siguiendo el patrón arquitectónico MVC (Modelo-Vista-Controlador), que permite una mejor organización del código, facilitando su mantenimiento y expansión futura. Se utilizaron las herramientas y lenguajes siguientes:

- **Lenguaje:** Java.
- **IDE:** NetBeans.
- **Framework:** Java Swing para el desarrollo de interfaces gráficas.
- **Control de Versiones:** Git y GitHub.

### Explicación de las Clases
- **Modelo (Model):** Define las estructuras de datos del laberinto, las celdas y los algoritmos implementados.
- **Vista (View):** Incluye tres interfaces para la configuración y ejecución de los algoritmos.
- **Controlador (Controller):** Gestiona la lógica y comunicación entre el modelo y las vistas.

### Interfaz 1: Ingreso del Tamaño del Laberinto
- Permite ingresar el número de filas y columnas (entre 3 y 9).
- Muestra un mensaje de advertencia si el tamaño no es válido.
- Al presionar el botón "Siguiente", se pasa a la siguiente interfaz.
[No carga la imagen](./images/Interfaz1.png)
### Interfaz 2: Selección de Celdas No Transitables
- Muestra el laberinto generado.
- Permite seleccionar hasta 4 celdas no transitables.
- Incluye un botón "Siguiente" para pasar a la interfaz final.
[No carga la imagen](./images/Interfaz2.png)
### Interfaz 3: Simulación del Laberinto
- Permite ingresar la celda de inicio y fin.
- Muestra el recorrido utilizando el algoritmo seleccionado.
- Incluye un botón "Limpiar" para restaurar la tabla.
- Muestra los tiempos de ejecución en nanosegundos con 7 decimales.
[No carga la imagen](./images/Interfaz3.png)
 
En las siguientes imagenes se  mostrara el camino y tiempo que realiza cada uno de los algoritmos.
- **Programación Dinamica:** 
[No carga la imagen](./images/Interfaz3%20CAC.png)
- **BFS (Breadth-First Search):**
[No carga la imagen](./images/Interfaz3%20BFS.png)
- **DFS (Depth-First Search):** 
[No carga la imagen](./images/Interfaz3%20DFS.png)
- **Recursividad:**
[No carga la imagen](./images/Interfaz3%20REC.png)
## Criterio por Estudiante
___
En cierto punto del desarrollo de las interfaces estaba por optar por cambio de lenguaje de Java a HTML al ser un poco tedioso la parte del diseño de cada  una de las interfaces, igualmente la solucion que se planteo en el proyecto permite al usuario interactuar de manera dinamica con el laberinto, eligiendo su tamaño, las celdas no transitables y los puntos de inicio y final. Ademas de las opciones de los algoritmos de busqueda.
# Conclusiones
___
Segun el analisis de los resultados, se observa que el algoritmo BFS es mas eficiente en laberintos con multiples caminos debido a su exploración por niveles. Por otro lado, el algoritmo Cache ofrece ventajas cuando se repiten cálculos, mientras que el DFS es más eficiente en laberintos sin muchas bifurcaciones.
# Consideraciones
___
- Mejoras UI: Implementar una visualización tridimensional del laberinto.
- Aplicación Practica: Uso en simuladores de robots para navegación autonoma.
- Optimización: Incluir un algoritmo A* para mejorar la eficiencia en laberintos complejos.
