import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MovieSelectorUI extends JFrame implements ActionListener {
    private JComboBox<String> listSelector;
    private JButton randomButton;
    private JLabel movieLabel;
    private ArrayList<String> cumulativeList;
    private  ArrayList<String> antList;
    private ArrayList<String> skyList;

    public MovieSelectorUI() throws FileNotFoundException {
        super("Movie Selector");
        //cumulative list
        Scanner cum1Scan = new Scanner(new File("C:\\Users\\sevar\\eclipse-workspace\\MovieSelectorUI\\antList.txt"));
        cumulativeList = new ArrayList<String>();
        while (cum1Scan.hasNextLine()){
            cumulativeList.add(cum1Scan.nextLine());
        }
        cum1Scan.close();

        Scanner cum2Scan = new Scanner(new File("C:\\Users\\sevar\\eclipse-workspace\\MovieSelectorUI\\skyList.txt"));
        while (cum2Scan.hasNextLine()) {
        	cumulativeList.add(cum2Scan.nextLine());
        }
        cum2Scan.close();
        
        //ant list
        Scanner antScan = new Scanner(new File("C:\\Users\\sevar\\eclipse-workspace\\MovieSelectorUI\\antList.txt"));
        antList = new ArrayList<String>();
        while (antScan.hasNextLine()){
            antList.add(antScan.nextLine());
        }
        antScan.close();
        //sky list
        Scanner skyScan = new Scanner(new File("C:\\Users\\sevar\\eclipse-workspace\\MovieSelectorUI\\skyList.txt"));
        skyList = new ArrayList<String>();
        while (skyScan.hasNextLine()) {
        	skyList.add(skyScan.nextLine());
        }
        skyScan.close();

        listSelector = new JComboBox<>();
        listSelector.addItem("Ant's List");
        listSelector.addItem("Sky's List");
        listSelector.addItem("Both Lists");

        randomButton = new JButton("Random Movie");
        randomButton.addActionListener(this);

        movieLabel = new JLabel("Select a list and click the button to get a random movie.");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel selectionPanel = new JPanel();
        selectionPanel.add(listSelector);
        selectionPanel.add(randomButton);

        mainPanel.add(selectionPanel, BorderLayout.NORTH);
        mainPanel.add(movieLabel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        int selection = listSelector.getSelectedIndex();
        String movie;

        if (selection == 0) { // Ant's List
            movie = antList.get(random.nextInt(antList.size()));
        } else if (selection == 1) { // Sky's List
            movie = skyList.get(random.nextInt(skyList.size()));
        } else { // Both Lists
            ArrayList<String> combinedList = new ArrayList<>(cumulativeList);
            combinedList.addAll(antList);
            combinedList.addAll(skyList);
            movie = combinedList.get(random.nextInt(combinedList.size()));
        }

        movieLabel.setText("Random Movie: " + movie);
    }

    public static void main(String[] args) throws FileNotFoundException {
    	new MovieSelectorUI();
    }
}
