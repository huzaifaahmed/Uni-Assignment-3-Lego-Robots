# Uni-Assignment-3-Lego-Robots

A variation of the children's game, Twister, using a Lego Robot.

### Assignment Website
https://staffwww.dcs.shef.ac.uk/people/S.North/campus_only/com1003/three/assignment3.pdf

The assignment assessed upon the following abilities:

* Understanding how software objects can be used to represent real-world objects;
* Demonstrating experience of problem solving and algorithm design;
* Understanding how to use the API documentation to explore what methods are available;
* Appreciating the difficulties involved in developing robust systems that involve the interaction of hardware and software, and operate in a real-world environment.

The Java program involved programming a Lego Mindstorm EV3 Robot to navigate a grid of coloured dots linked by black lines. The robot was expected to be able to set out, find the grid and explore it, whilst also memorising the location of each coloured dot it meets. Furthermore, the robot was expected to "sing" a tune (bleep) when encountering a second dot of a colour it had already found and then set off directly to the first dot, this time without following lines of the grid. when reaching the first dot it would then dance (spin) to indicate the program has finished.

The program achieved the tasks outlined above and makes use of the javadoc facility to explain how the algorithms were designed and implemented.

## Setup

1. Clone this repository with `https://github.com/huzaifaahmed/Uni-Assignment-3-Lego-Robots.git`
2. Run `setupConsole.bat`
3. Run `javac Assignment3.java`
4. Follow the "Connecting the robot" section of the instructions [here](https://staffwww.dcs.shef.ac.uk/people/S.North/campus_only/com1003/three/grid.jpg) (University of Sheffield user account required).
5. Run `java Assignment3`

You will also need a grid that looks something a bit like this:

![Picture of the board](https://cloud.githubusercontent.com/assets/8095888/22085042/5e37f606-ddca-11e6-9d45-fb3d8d54936b.jpg)

The circle in the grid have a diameter of approximately 20cm.
