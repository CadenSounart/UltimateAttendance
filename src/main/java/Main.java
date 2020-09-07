import com.licensespring.LicenseManager;
import com.licensespring.LicenseSpringConfiguration;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;
import org.apache.commons.io.FileUtils;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;


public class Main
{
   public static void main(String[] args) throws InterruptedException {
        LicenseSpringConfiguration configuration = LicenseSpringConfiguration.builder()
                .apiKey("d234f79f-40bc-408d-836b-8f7fd98c7e1b")
                .productCode("ua")
                .sharedKey("HJ-4gNBzMoQAvcOowTNmnbYWCpjzyiiUOy6VuHq5Q2g")
                .appName("Ultimate Attendance")
                .appVersion("1.0.0")
                .build();
        LicenseManager manager = LicenseManager.getInstance();
        manager.initialize(configuration);
        if(manager.getCurrent() == null)
        {
            boolean done = false;
            String[] choices = {"Exit", "Retry"};
            while (!done)
            {
                LicenseGrabber.grabLicense();
                if (manager.getCurrent() == null)
                {
                    int choice = JOptionPane.showOptionDialog(null, "License is not valid", "License Error", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, choices, "lol");
                    if (choice == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                else
                {
                    done = true;
                }
            }
            run();
        }
        else
        {
            run();
        }
    }

    private static void run() throws InterruptedException
    {
        final JFrame frame = new JFrame("Ultimate Attendance");
        final JProgressBar progressBar = new JProgressBar();

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);

        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(progressBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        for (int i = 1; i <= 47; i++) {
            progressBar.setValue(i);
            int low = 20;
            int high = 101;
            int waitTime = generator.nextInt(high - low) + low;
            java.lang.Thread.sleep(waitTime);
        }

        final AppDirs appDirs = AppDirsFactory.getInstance();
        userDataDir = appDirs.getUserDataDir(CREDENTIALS_APP_NAME, CREDENTIALS_VERSION, CREDENTIALS_AUTHOR);
        userConfigDir = appDirs.getUserConfigDir(CREDENTIALS_APP_NAME, CREDENTIALS_VERSION, CREDENTIALS_AUTHOR);
        userCacheDir = appDirs.getUserCacheDir(CREDENTIALS_APP_NAME, CREDENTIALS_VERSION, CREDENTIALS_AUTHOR);

        if (!hasChromeUserData()) {
            String chromeDataToCopy = getChromeDataDir();
            File srcDir = new File(chromeDataToCopy);
            File destDir = new File(userDataDir + "\\SeleniumChromeData\\");
            try {
                FileUtils.copyDirectory(srcDir, destDir);
            } catch (IOException e) {
                System.out.print("Error copying Google Chrome user data\n");
                e.printStackTrace();
            }
        }
        for (int i = 47; i <= 100; i++) {
            progressBar.setValue(i);
            int low = 20;
            int high = 101;
            int waitTime = generator.nextInt(high - low) + low;
            java.lang.Thread.sleep(waitTime);
        }
        if (hasCacheData()) {
            frame.dispose();
            int choice = JOptionPane.showConfirmDialog(null, "Would you like to change configs", "Change Configs?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                new GUI();
            } else if (choice == JOptionPane.CANCEL_OPTION) {
                System.exit(0);
            } else if (choice == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            } else {
                System.out.print("Data file found\n");
                Scanner reader = new Scanner(dataFile);
                String name1 = reader.next();
                String name2 = reader.next();
                String id = reader.next();
                while (reader.hasNext()) {
                    classes.add(reader.next());
                }
                reader.close();
                filler = new AttendanceFiller(name1, name2, id, classes);
                filler.fillAttendance();
            }
        } else {
            new GUI();
        }
    }

    public static boolean hasCacheData()
    {
        try
        {
            dataFile = new FileReader(userCacheDir + "\\attendanceData.txt");
            return true;
        }
        catch (FileNotFoundException e)
        {
            System.out.print("File not found\n");
            return false;
        }
    }

    public static boolean hasChromeUserData()
    {
        try
        {
            new FileReader(userDataDir + "\\SeleniumChromeData\\chrome_debug.log");
            return true;
        }
        catch (FileNotFoundException e)
        {
            return false;
        }
    }

    public static String getChromeDataDir()
    {
        boolean done = false;
        int i = 1;
        String path = System.getenv("TEMP");
        char temp;
        String finalPath;
        while (!done)
        {
            char test = path.charAt(path.length() - i);
            if (test == '/' || test == '\\')
            {
                done = true;
            }
            i++;
        }
        finalPath = path.substring(0, (path.length() - i) + 1);
        finalPath = finalPath + "\\Google\\Chrome\\User Data";
        return finalPath;
    }
    private static final String CREDENTIALS_APP_NAME = "Ultimate Attendance";
    private static final String CREDENTIALS_AUTHOR = "TheAttendanceGuy";
    private static final String CREDENTIALS_VERSION = "v1.0";

    public static String userDataDir;
    public static String userConfigDir;
    public static String userCacheDir;

    private static FileReader dataFile;
    private static ArrayList<String> classes = new ArrayList<String>();
    private static AttendanceFiller filler;
    private static Random generator = new Random();
}