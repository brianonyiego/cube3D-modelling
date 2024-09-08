import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cube3DViewer extends JPanel implements MouseListener, MouseMotionListener {
    private static final int SIZE = 100;
    private static final int HALF_SIZE = SIZE / 2;
    private static final int CUBE_CENTER_X = 150;
    private static final int CUBE_CENTER_Y = 150;
    private static final int CUBE_CENTER_Z = 150;

    private int prevMouseX;
    private int prevMouseY;
    private double rotationX = 0;
    private double rotationY = 0;

    public Cube3DViewer() {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void drawCube(Graphics2D g2d) {
        // Define the 8 vertices of the cube
        int[][] vertices = {
                {CUBE_CENTER_X - HALF_SIZE, CUBE_CENTER_Y - HALF_SIZE, CUBE_CENTER_Z - HALF_SIZE},
                {CUBE_CENTER_X + HALF_SIZE, CUBE_CENTER_Y - HALF_SIZE, CUBE_CENTER_Z - HALF_SIZE},
                {CUBE_CENTER_X + HALF_SIZE, CUBE_CENTER_Y + HALF_SIZE, CUBE_CENTER_Z - HALF_SIZE},
                {CUBE_CENTER_X - HALF_SIZE, CUBE_CENTER_Y + HALF_SIZE, CUBE_CENTER_Z - HALF_SIZE},
                {CUBE_CENTER_X - HALF_SIZE, CUBE_CENTER_Y - HALF_SIZE, CUBE_CENTER_Z + HALF_SIZE},
                {CUBE_CENTER_X + HALF_SIZE, CUBE_CENTER_Y - HALF_SIZE, CUBE_CENTER_Z + HALF_SIZE},
                {CUBE_CENTER_X + HALF_SIZE, CUBE_CENTER_Y + HALF_SIZE, CUBE_CENTER_Z + HALF_SIZE},
                {CUBE_CENTER_X - HALF_SIZE, CUBE_CENTER_Y + HALF_SIZE, CUBE_CENTER_Z + HALF_SIZE}
        };

        // Define the edges of the cube
        int[][] edges = {
                {0, 1}, {1, 2}, {2, 3}, {3, 0}, // Front face
                {4, 5}, {5, 6}, {6, 7}, {7, 4}, // Back face
                {0, 4}, {1, 5}, {2, 6}, {3, 7}  // Connections between front and back faces
        };

        // Apply rotation transformations
        double cosX = Math.cos(Math.toRadians(rotationX));
        double sinX = Math.sin(Math.toRadians(rotationX));
        double cosY = Math.cos(Math.toRadians(rotationY));
        double sinY = Math.sin(Math.toRadians(rotationY));

        int[][] rotatedVertices = new int[8][3];
        for (int i = 0; i < vertices.length; i++) {
            int[] vertex = vertices[i];
            int x = vertex[0] - CUBE_CENTER_X;
            int y = vertex[1] - CUBE_CENTER_Y;
            int z = vertex[2] - CUBE_CENTER_Z;

            // Rotation around X-axis
            int rotatedY = (int) (y * cosX - z * sinX) + CUBE_CENTER_Y;
            int rotatedZ = (int) (y * sinX + z * cosX) + CUBE_CENTER_Z;

            // Rotation around Y-axis
            int rotatedX = (int) (x * cosY + rotatedZ * sinY) + CUBE_CENTER_X;
            rotatedZ = (int) (-x * sinY + rotatedZ * cosY) + CUBE_CENTER_Z;

            rotatedVertices[i] = new int[]{rotatedX, rotatedY, rotatedZ};
        }

        // Draw edges
        g2d.setColor(Color.BLACK);
        for (int[] edge : edges) {
            int[] p1 = rotatedVertices[edge[0]];
            int[] p2 = rotatedVertices[edge[1]];

            // Check if any vertex is behind the cube
            boolean visible = true;
            for (int[] vertex : rotatedVertices) {
                if (vertex[2] < CUBE_CENTER_Z) {
                    visible = false;
                    break;
                }
            }

            // Draw the edge only if it's visible
            if (visible) {
                g2d.drawLine(p1[0], p1[1], p2[0], p2[1]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawCube(g2d);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevMouseX = e.getX();
        prevMouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int currentX = e.getX();
        int currentY = e.getY();

        // Update rotation angles based on mouse movement
        rotationY += (currentX - prevMouseX) * 0.5;
        rotationX += (currentY - prevMouseY) * 0.5;

        prevMouseX = currentX;
        prevMouseY = currentY;

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("3D Cube Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Cube3DViewer());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
