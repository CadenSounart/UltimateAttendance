import javax.swing.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class SecondActionListener implements ActionListener
{
    private static ArrayList<JTextField> fields;
    private static ArrayList<String> classes;

    public SecondActionListener()
    {
        fields = GUI.periodFields;
        classes = new ArrayList<String>();
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        JOptionPane pane = new JOptionPane();
        int choice = pane.showConfirmDialog(null, "Are you sure you want to finalize these configs?", "Finalize?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION)
        {
            for (int i = 0; i < fields.size(); i++)
            {
                if (!fields.get(i).getText().equals(" Input class attendance link here") && !fields.get(i).getText().isBlank())
                {
                    classes.add(fields.get(i).getText());
                    try
                    {
                        GUI.cacher.write("\n" + fields.get(i).getText());
                        System.out.print("Successfully cached google links\n");
                    }
                    catch (IOException e)
                    {
                        System.out.print("Error caching google links\n");
                        e.printStackTrace();
                    }
                }
            }
            try
            {
                GUI.cacher.close();
            }
            catch (IOException e) {}
            try
            {
                FileReader dataFile = new FileReader(Main.userCacheDir + "\\attendanceData.txt");
                Scanner reader = new Scanner(dataFile);
                String name1 = reader.next();
                String name2 = reader.next();
                String id = reader.next();
                reader.close();
                AttendanceFiller filler = new AttendanceFiller(name1, name2, id, classes);
                GUI.close();
                filler.fillAttendance();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}
