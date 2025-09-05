# ARSW-Lab1-Matrix
# Hecho por ALejandro Prieto
---

Un juego en Java inspirado en *The Matrix*, donde **Neo** debe llegar a un teléfono antes de ser atrapado por los **Agentes**.  

El juego se desarrolla en un **mapa representado en una cuadrícula (char[][])** que se carga desde archivo o se genera aleatoriamente.  
En este mapa se ubican:
- **N** → Neo (jugador principal).  
- **T** → Teléfono (meta).  
- **A** → Agente (enemigo).  
- **#** → Paredes/barreras.  
- `" "` → Espacios libres.  

El juego termina cuando Neo llega al teléfono (**gana**) o cuando un agente lo atrapa (**pierde**).

---

## Getting Started

Estas instrucciones te permitirán clonar y ejecutar el proyecto en tu máquina local para desarrollo y pruebas.

### Prerequisites

- **Java 11+**
- **Maven** (opcional, si deseas generar empaques JAR)

Verifica la instalación de Java con:

```bash
java -version
````

### Installing

1. Clona el repositorio:

```bash
git clone https://github.com/AlejandroPrieto82/ARSW-Lab1-Matrix.git
cd ARSW-Lab1-Matrix
```

2. Compila el proyecto:

```bash
javac -d out $(find src/main/java -name "*.java")
```

3. Ejecuta el juego:

```bash
java -cp out eci.edu.arsw.Juego
```

---

## Ejecución del Juego

Cuando inicies el programa:

1. El **mapa** se intenta cargar desde `src/main/resources/mapa.txt`.

   * Si existe, se usará ese diseño.
   * Si no, se genera un mapa aleatorio con Neo, un teléfono y agentes distribuidos.

2. Se imprime en consola el mapa inicial.

3. **Neo (N)** se mueve automáticamente hacia el teléfono usando el algoritmo BFS.

4. **Agentes (A)** persiguen a Neo, también usando BFS.

5. El juego termina si:

   * Neo llega a **T** → *Gana Neo*.
   * Un agente atrapa a Neo → *Pierdes*.

Ejemplo de mapa en consola:

```
##########
#        #
#   A    #
#        #
#    N   #
#        #
#     T  #
#        #
##########
```

---

## Documentación (Javadoc)

El proyecto incluye **comentarios Javadoc** para todas las clases.
Para generar la documentación:

```bash
javadoc -d docs -sourcepath src/main/java -subpackages eci.edu.arsw
```

Esto creará una carpeta `docs/` con la documentación HTML.
Para verla, abre en tu navegador el archivo:

```
docs/index.html
```

---

## Deployment

Si prefieres empaquetar el proyecto en un `.jar` ejecutable:

```bash
jar cfe NeoMatrix.jar eci.edu.arsw.Juego -C out .
java -jar NeoMatrix.jar
```

---

## Built With

* **Java** – Lenguaje de programación principal.
* **Algoritmo BFS** – Para el cálculo de caminos mínimos.
* **Threads** – Para manejar la concurrencia de Neo y los agentes.
* **(Opcional) Maven** – Para la gestión del proyecto.

---

## Contributing

Contribuciones bienvenidas:

1. Haz un fork de este repositorio.
2. Crea una rama con tus cambios:

   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Haz *commit* de tus cambios.
4. Envía un pull request explicando tus mejoras.

---

## Authors

* **Alejandro Prieto** – Autor principal – [@AlejandroPrieto82](https://github.com/AlejandroPrieto82)

---

## License

Este proyecto está bajo la licencia **MIT**. Consulta el archivo [Licencia.md](/LICENSE) para más información.

---

## Acknowledgments

* Inspirado en *The Matrix*.
* Ejemplo de uso de **concurrencia en Java** y **BFS** para inteligencia artificial simple.
* Trabajo desarrollado en el marco del curso **ARSW**.
