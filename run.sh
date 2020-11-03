#!/bin/bash
# compile the program in Java
javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -d out files/code/*.java
# execute the program
java -Dprism.verbose=true --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.fxml -cp out code.Main