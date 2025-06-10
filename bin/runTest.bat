@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n4_videotienda
REM Autor: Diana Puentes - 01-Jul-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecución de la prueba
REM ---------------------------------------------------------

cd..

java -classpath ./lib/videotienda.jar;./test/lib/videotiendaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.videotienda.test.ClienteTest
java -classpath ./lib/videotienda.jar;./test/lib/videotiendaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.videotienda.test.CopiaTest
java -classpath ./lib/videotienda.jar;./test/lib/videotiendaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.videotienda.test.PeliculaTest
java -classpath ./lib/videotienda.jar;./test/lib/videotiendaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.videotienda.test.VideoTiendaTest

cd bin
