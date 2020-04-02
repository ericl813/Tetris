import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Applet 
{
	private static final long serialVersionUID = 5138623139734386524L;

	/**
     * The applet parameter information structure.
     */
    private static final String PARAMETER[][] = 
    {
        { "tetris.color.background", "color",  
            "The overall background color." },
        { "tetris.color.label", "color", 
            "The text color of the labels." },
        { "tetris.color.button", "color", 
            "The start and pause button bolor." }, 
        { "tetris.color.board.background", "color", 
            "The background game board color." },
        { "tetris.color.board.message", "color", 
            "The game board message color." },
        { "tetris.color.figure.square", "color", 
            "The color of the square figure." },
        { "tetris.color.figure.line", "color", 
            "The color of the line figure." },
        { "tetris.color.figure.s", "color", 
            "The color of the 's' curved figure." },
        { "tetris.color.figure.z", "color", 
            "The color of the 'z' curved figure." },
        { "tetris.color.figure.right", "color", 
            "The color of the right angle figure." },
        { "tetris.color.figure.left", "color", 
            "The color of the left angle figure." },
        { "tetris.color.figure.triangle", "color", 
            "The color of the triangle figure." }
    };

    /**
     * The Tetris game being played (in applet mode).
     */
    private Game game = null;

    /**
     * The stand-alone main routine.
     * 
     * @param args      the command-line arguments
     */
    public static void main(String[] args) 
    {
        Frame  frame = new Frame("Tetris");
        Game   game = new Game();

        // Set up frame
        frame.add(game.getComponent());
        frame.pack();

        // Add frame window listener
        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });

        // Show frame (and start game)
        frame.setVisible(true);
    }

    /**
     * Returns information about the parameters that are understood by 
     * this applet.
     * 
     * @return an array describing the parameters to this applet
     */
    public String[][] getParameterInfo() 
    {
        return PARAMETER;
    }

    /**
     * Initializes the game in applet mode.
     */
    public void init() 
    {
        String  value;

        // Set all configuration parameters
        for (int i = 0; i < PARAMETER.length; i++) 
        {
            value = getParameter(PARAMETER[i][0]);
            if (value != null) 
            {
                Configuration.setValue(PARAMETER[i][0], value);
            }
        }

        // Create game object
        game = new Game();

        // Initialize applet component
        setLayout(new BorderLayout());
        add(game.getComponent(), "Center");
    }

    /**
     * Stops the game in applet mode.
     */
    public void stop() 
    {
        game.quit();
    }
    
}
