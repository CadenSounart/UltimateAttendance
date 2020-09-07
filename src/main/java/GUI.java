import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class GUI implements ActionListener
{
    private static JFrame frame = new JFrame("Ultimate Attendance Setup!");
    private static JPanel card1;
    private static JPanel card2;
    private static JPanel cardPanel;
    private static JLabel firstName;
    private static JTextField firstNameField;
    private static JTextField lastNameField;
    private static JTextField studentIdField;
    private static JLabel studentId;
    private static JLabel lastName;
    private static JButton nextButton;
    public static ArrayList<JLabel> periodLabels;
    public static ArrayList<JTextField> periodFields;

    public GUI()
    {
        card1 = new JPanel();
        card2 = new JPanel();
        cardPanel = new JPanel(new CardLayout());
        firstName = new  JLabel("First Name");
        lastName = new JLabel("Last Name");
        studentId = new JLabel("Student Id");
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        studentIdField = new JTextField();
        nextButton = new JButton("Next Step");

        firstName.setBounds(10, 20, 80, 25);
        firstNameField.setBounds(100, 20, 165, 25);
        lastName.setBounds(10, 50, 80, 25);
        lastNameField.setBounds(100, 50, 165, 25);
        studentId.setBounds(10, 80, 80, 25);
        studentIdField.setBounds(100, 80, 165, 25);
        nextButton.setBounds(10, 120, 300, 25);
        nextButton.addActionListener(this);

        card1.setLayout(null);
        card1.add(firstName);
        card1.add(firstNameField);
        card1.add(lastName);
        card1.add(lastNameField);
        card1.add(studentId);
        card1.add(studentIdField);
        card1.add(nextButton);

        periodLabels = new ArrayList<JLabel>();
        periodFields = new ArrayList<JTextField>();
        for (int i = 0; i < 8; i++)
        {
            periodLabels.add(new JLabel("Period #" + i));
            periodFields.add(new JTextField(" Input class attendance link here"));
        }
        periodLabels.get(0).setBounds(10, 20, 80,25);
        periodFields.get(0).setBounds(100, 20, 185, 25);
        periodLabels.get(1).setBounds(10, 50, 80,25);
        periodFields.get(1).setBounds(100, 50, 185, 25);
        periodLabels.get(2).setBounds(10, 80, 80,25);
        periodFields.get(2).setBounds(100, 80, 185, 25);
        periodLabels.get(3).setBounds(10, 110, 80,25);
        periodFields.get(3).setBounds(100, 110, 185, 25);
        periodLabels.get(4).setBounds(10, 140, 80,25);
        periodFields.get(4).setBounds(100, 140, 185, 25);
        periodLabels.get(5).setBounds(10, 170, 80,25);
        periodFields.get(5).setBounds(100, 170, 185, 25);
        periodLabels.get(6).setBounds(10, 200, 80,25);
        periodFields.get(6).setBounds(100, 200, 185, 25);
        periodLabels.get(7).setBounds(10, 230, 80,25);
        periodFields.get(7).setBounds(100, 230, 185, 25);

        card2.setLayout(null);
        for(int i = 0; i < periodLabels.size(); i++)
        {
            card2.add(periodLabels.get(i));
            card2.add(periodFields.get(i));
        }
        JButton attendance = new JButton("Finalize Configs");
        attendance.setBounds(10, 280, 300, 50);
        SecondActionListener listener = new SecondActionListener();
        attendance.addActionListener(listener);
        card2.add(attendance);

        cardPanel.add(card1, "card1");
        cardPanel.add(card2, "card2");

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void close()
    {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        String name1 = firstNameField.getText();
        String name2 = lastNameField.getText();
        String id = studentIdField.getText();
        try
        {
            File dirMaker = new File(Main.userCacheDir);
            if (dirMaker.mkdirs())
            {
                System.out.print("Directory created!\n");
            }
            else
            {
                System.out.print("Error creating directory\n");
            }
            File dataFile = new File(Main.userCacheDir + "\\attendanceData.txt");
            System.out.println(dataFile.getAbsolutePath());
            System.out.println("File created");
            cacher = new FileWriter(dataFile);
            cacher.write(name1 + "    " + name2 + "    " + id);
            System.out.println("Data successfully cached");
        }
        catch (IOException e)
        {
            System.out.println("Error caching data");
            e.printStackTrace();
        }
        CardLayout cardLayout = (CardLayout)(cardPanel.getLayout());
        frame.resize(350, 400);
        cardLayout.show(cardPanel, "card2");
    }

    public static FileWriter cacher;
}