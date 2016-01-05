# AcademiaRobot

This game was made for Academia de Codigo's Open Day, which was held during European CodeWeek.

AcademiaRobot is deeply inspired in Karel The Robot and its' purpose is to show the basic syntax for object creation and method calling in Java. You need to give orders to a robot in order to make him pick a bunch of jars and put it in a target.

To play the game, you may head to the Main.java file where you will write your first lines of Java code. Inside the file you will see how you can create your robot and how you can make it move. The robot only knows 4 actions (move forward, turn left, pick a jar, or drop a jar) and may only store 2 jars. The robot will 'read' those lines (actions) and will execute them one by one.

To make a custom map (or change the robot initial position), go to resources/map.txt and look at how it is made. You can freely change the dimension ratio (square or rectangular) but all rows should have the same number of columns. The field, robot and objects are identified by symbols, and using them you can edit/create your own map.

* r = robot
* _ = grass
* # = wall
* * = jar
* x = destination target
