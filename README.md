# Enchanted Woods
<img src="img/screenshot.png" alt="screenshot" width="800"/>

#### General Info
Enchanted Woods is an interactive maze game.<br/>
It is the course project of Programming of Interactive System in University Paris Saclay.

#### Technologies
java version 11.0.8 <br>
javafx-sdk-11.0.2

#### Setup
To run this project, you should first setup Javafx:<br/>
How to get JavaFX and Java 11 working in IntelliJ IDEA:<br/>
https://www.jetbrains.com/help/idea/javafx.html

## About the game
### Brief introduction of the gaming process
* Launch the game
* Customize the appearance of the gaming character
* Select gaming difficulty level
* Start the game


### Goal
Get out of the maze within a limited time

### Operation
* Move around the character with arrow keys
* Avoid the contact with ghosts
* Get the key
* Click to burst the bubble and pick up tool:
* Timer: gain extra time for the game
* Magic stick: draw a path and the character will  follow it to move automatically
* Drag and drop the axe to chop off the corresponding trees

## Implemented functionalities
### Before starting the game

* Route between different pages according to instruction
* Drag and drop to customize the appearance of the character
* Select the level of difficulty
* Watch the tutorial before start to play the game

### Watching the tutorial

### Starting the game
* Navigate the character around with arrow keys
* Remaining time: the character need to get out of the maze before time runs out, otherwise the game will restart
* 2 auto moving ghosts:<br/>
    * The ghosts are moving randomly every 0.5s
    * The game will detect the collision with the character and the ghosts every 0.5s
    * The character will get killed if he contact with the ghost, and the game will restart

### Game Operation
#### Bubble
* Click the bubble for 5 times
    * Bubble will burst and randomly reveal one of the following tool
    * Timer: collect the timer by dragging, and you will gain 
    * Magic stick: click the magic stick by dragging, the magic stick on toolbar will be enabled and number will change accordingly
* After the bubble is exploded and tools get collected, this cell will allow the character to pass

#### Magic Stick
* Click the magic stick when it’s not disabled (number>1 && no opacity)
    * Cursor turns to magic stick
    * Press Shift + D while hovering the mouse to draw the path
    * The magic stick can only last for 8s, and it’ll be disabled and cursor returns to default
    * The character will start to navigate automatically following the drawn path

#### Axe
* Click the magic stick when it’s not disabled (number>1 && no opacity)
    * Cursor turns to magic stick
    * Press Shift + D while hovering the mouse to draw the path
    * The magic stick can only last for 8s, and it’ll be disabled and cursor returns to default
    * The character will start to navigate automatically following the drawn path
    
### Gaming Status
* A pop-up window will show up either the character win the game or lose it
* A pop-up window will show up if the cat at the exit without key.




