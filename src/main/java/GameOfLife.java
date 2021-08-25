import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Class contains the GUI of game of life **/
public class GameOfLife {

    /** Width of the frame **/
    private static final int WIDTH_FRAME = 1000;
    /** Length of the frame **/
    private static final int LENGTH_FRAME = 1000;
    /** Color of the Title **/
    private static final Color COLOR_TITLE = Color.LIGHT_GRAY;

    /** Logic of the game **/
    private GOL_Logic logic;

    /** Frame of the Game of Life **/
    private JFrame frame;

    /** Panel containing each cell **/
    private JPanel cellsPanel;

    /** Panel containing the title **/
    private JPanel titlePanel;
    /** Title of the Game of Life **/
    private JLabel titleLabel;

    /** Panel containing the options **/
    private JPanel optionsPanel;
    /** Button toggles the game of life **/
    private JButton toggleStart;
    /** Button clears the Field **/
    private JButton clear;
    /** Button slows down the game of life **/
    private JButton timer_slower;
    /** Button enhances the speed of the game of life **/
    private JButton timer_faster;
    /** Listener for the start button **/
    private ActionListener listener_toggleStart;
    /** Listener for the clear button **/
    private ActionListener listener_clear;
    /** Listener for the timer_slower button **/
    private ActionListener listener_timer_slower;
    /** Listener for the timer_faster button **/
    private ActionListener listener_timer_faster;

    /** Timer to start the game **/
    private Timer timer;
    private int timerSpeed;

    /** Constructor **/
    public GameOfLife(){
        logic = new GOL_Logic();
        timerSpeed = 100;

        initTitle();
        initCells();

        init_listener_toggleStart();
        init_listener_clear();
        init_listener_timer_slower();
        init_listener_timer_faster();
        initOptions();

        initTimer();

        initFrame();

        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(cellsPanel);
        frame.add(optionsPanel, BorderLayout.SOUTH);
    }

    /** Method initializes the frame **/
    private void initFrame(){
        frame = new JFrame("Game Of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH_FRAME, LENGTH_FRAME);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.setVisible(true);
    }

    /** Method initialized the TitlePanel and the TitleLabel **/
    private void initTitle(){
        titlePanel = new JPanel();
        titlePanel.setBackground(COLOR_TITLE);
        titlePanel.setBounds(0,0, WIDTH_FRAME, 100);

        titleLabel = new JLabel("Conway's Game Of Life");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);
    }

    /** Method initializes the optionPanel and each option **/
    private void initOptions(){
        optionsPanel = new JPanel();
        optionsPanel.setBounds(0,0,WIDTH_FRAME, 100);
        optionsPanel.setBackground(COLOR_TITLE);

        toggleStart = new JButton("start");
        clear = new JButton("clear");
        timer_slower = new JButton("<<<");
        timer_faster = new JButton(">>>");

        toggleStart.setFont(new Font("Arial Black", Font.PLAIN, 15));
        clear.setFont(new Font("Arial Black", Font.PLAIN, 15));
        timer_slower.setFont(new Font("Arial Black", Font.PLAIN, 15));
        timer_faster.setFont(new Font("Arial Black", Font.PLAIN, 15));

        toggleStart.addActionListener(listener_toggleStart);
        clear.addActionListener(listener_clear);
        timer_slower.addActionListener(listener_timer_slower);
        timer_faster.addActionListener(listener_timer_faster);

        optionsPanel.add(toggleStart);
        optionsPanel.add(clear);
        optionsPanel.add(timer_slower);
        optionsPanel.add(timer_faster);
    }

    /** Method initialized the cellsPanel and each cell **/
    private void initCells(){
        cellsPanel = new JPanel();
        cellsPanel.setLayout(new GridLayout(logic.WIDTH_CELLS, logic.LENGTH_CELLS));
        cellsPanel.setBackground(COLOR_TITLE);

        for(int i = 0 ; i < logic.WIDTH_CELLS * logic.LENGTH_CELLS; ++i){
            cellsPanel.add(logic.getCells().get(i));
        }
    }

    /** Method initializes {@link #timer} and {@link #timerSpeed}**/
    private void initTimer(){
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.generateNextIteration();
            }
        };
        timer = new Timer(timerSpeed, timerListener);
    }

    /** Method initializes {@link #listener_toggleStart} **/
    private void init_listener_toggleStart(){
        listener_toggleStart = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleStart.getText().equals("start")){
                    toggleStart.setText("stop");
                    timer.start();
                }else{
                    toggleStart.setText("start");
                    timer.stop();
                }
                logic.generateNextIteration();
            }
        };
    }

    /** Method initializes {@link #listener_clear} **/
    private void init_listener_clear(){
        listener_clear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.renderField();
            }
        };
    }

    /** Method initializes {@link #listener_timer_slower} **/
    private void init_listener_timer_slower(){
        listener_timer_slower = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timerSpeed += 25;
                initTimer();
                timer.start();
            }
        };
    }

    /** Method initializes {@link #listener_timer_faster} **/
    private void init_listener_timer_faster(){
        listener_timer_faster = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timerSpeed -= 25;
                initTimer();
                timer.start();
            }
        };
    }

}
