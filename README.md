# IQ Puzzler Pro
This program is created to fulfill Tugas Kecil 1 Mata Kuliah IF2211 Strategi Algoritma. This program is created to find a solution from a game called IQ puzzler pro with brute force.
The algorithm will try to put the piece on the board in every possible way and the piece will also be rotated 90 degrees and being flipped horizontally and vertically.

## How To Compile
Make sure your terminal location is in the root project or in /Tucil1_13523074 and then run this command.
javac -d bin $(find . -name "*.java")  # Linux
javac -d bin -cp . src/component/*.java src/solver/*.java src/main/*.java src/io/*.java  # Windows

## How To Run the Program
- Run this command : java -cp bin main.Main
- Write a .txt file in to the command choose one of the txt file inside the test/input/ folder
- Wait for the algorithm to work
- After finished choose whether to save the output or not, if you choose "Y" input the file name ("output") the program will automatically save the output to a matrix text and an image in the test/output/ folder

## Author Identity
Ahsan Malik Al Farisi - 13523074
