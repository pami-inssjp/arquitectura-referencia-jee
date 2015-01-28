# Arquitectura de Referencia Backend

El proyecto contiene diferentes ejemplos y pruebas sobre posibles arquitecturas de referencia. La idea es generar tanto ejemplos, como los arquetipos necesarios para poder crear nuevos proyectos y la documentación pertinente a cada decisión.

## Índice
* [Estructura del Proyecto](#/## Estructura del proyecto)
* [Requerimientos](#/## Requerimientos)



## Estructura del proyecto

* **docs**: Documentos escritos principalmente en LaTeX. Por una cuestión de comodidad, en caso de no tener LaTeX instalado se proveeran los pdf compilados.
* **examples**: Ejemplos completos sobre diferentes arquitecturas backend(CDI + EJB + JPA, FUSE, Spring, etc.)
* **archetypes**: En esta carpeta se encontraran todos los arquitipos utilizados para generar los ejemplos, los cuales también se van a poder utilizar para poder generar proyectos basados en estas tecnologías.

## Requerimientos

* Para la compilación de los documentos se necesitará **_LaTeX_** (http://www.latex-project.org/) y se puede descargar desde [aqupi](http://latex-project.org/ftp.html) dependiendo del sistema operativo que poseas.
* **Maven** (http://maven.apache.org/) para poder construir los proyectos bajo esta estructura. La última versión se puede descargar desde [aquí](http://maven.apache.org/download.cgi)
