import com.licensespring.License;
import com.licensespring.LicenseManager;
import com.licensespring.model.ActivationLicense;
import com.licensespring.model.exceptions.LicenseSpringException;

import javax.swing.*;

public class LicenseGrabber
{
    public static void grabLicense()
    {
        key = JOptionPane.showInputDialog("Enter your license key below");
        if (key == null)
        {
            System.exit(0);
        }
        key.toUpperCase();
        LicenseManager instance = LicenseManager.getInstance();
        try {
            instance.activateLicense(ActivationLicense.fromKey(key));
        }
        catch(LicenseSpringException e)
        {
            //Do nothing
        }
    }
    private static String key;
}
