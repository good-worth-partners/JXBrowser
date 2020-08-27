import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;
public final class JXBrowse {
    public static void main(String[] args) {
        // Creating and running Chromium engine
        Engine engine = Engine.newInstance(
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).licenseKey("1BNDHFSC1FWIJ060C1P9G8XK6L6DH2APZ7FDB22ZMMIX6VGRRMBIE3WMAW5VF3V1SN3NQ1").build());

        Browser browser = engine.newBrowser();
        // Loading the required web page
        browser.navigation().loadUrl("http://46.165.243.164:9876/gwplpos/menucard?pt=4_EXT6");

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance
            BrowserView view = BrowserView.newInstance(browser);

            // Creating and displaying Swing app frame
            JFrame frame = new JFrame("POS");
            // Closing the engine when app frame is about to close
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    engine.close();
                }
            });
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.add(view, BorderLayout.CENTER);
            frame.setSize(1400, 600);
            frame.setVisible(true);
        });
    }
}