# Cube3DViewer

## Overview
`Cube3DViewer` is a simple Java application that allows users to visualize and interact with a 3D cube. The cube can be rotated along the X and Y axes by dragging the mouse. The application uses the **Swing** library for the GUI and **2D Graphics** for rendering the cube.

## Features
- **3D Cube Rendering**: A 3D cube is drawn using basic 2D graphics.
- **Mouse Interaction**: Users can rotate the cube by dragging the mouse horizontally and vertically.
- **Swing GUI**: The interface is built using Java Swing and the JPanel component.

## Dependencies
- **Java SE Development Kit (JDK)** version 8 or higher

## How to Run
1. **Ensure JDK is Installed**: Make sure that you have Java installed on your system.
2. **Compile the Program**:
   ```bash
   javac Cube3DViewer.java
Run the Program:
bash
Copy code
java Cube3DViewer
Key Classes and Methods
1. Cube3DViewer (Main Class)
Implements MouseListener and MouseMotionListener to handle mouse events for rotating the cube.
The cube is drawn inside a JPanel with paintComponent() method.
2. drawCube(Graphics2D g2d)
Handles the rendering of the 3D cube. The vertices and edges of the cube are defined and then transformed based on the current rotation.
3. mouseDragged(MouseEvent e)
Updates the cube’s rotation based on the user’s mouse drag events, providing an interactive 3D experience.
Interaction
Rotate the Cube: Click and drag the mouse within the window to rotate the cube along the X and Y axes.
Notes
This is a basic implementation and could be further extended to include more complex features, such as rotation along the Z-axis, shading, or lighting for a more realistic 3D effect.












![image](https://github.com/user-attachments/assets/67c10d95-7593-48b4-a25a-036fea4afe3b)
