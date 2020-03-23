package org.wilson.corejava.v1.ch05.resources;

import lombok.Cleanup;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by wilson on 2020/3/23.
 */
public class ResourceTest {
    public static void main(String[] args) throws IOException {
        var cl = ResourceTest.class;
        var aboutURL = cl.getResource("about.gif");
        var icon = new ImageIcon(aboutURL);

        @Cleanup
        var stream = cl.getResourceAsStream("data/about.txt");
        var about = new String(stream.readAllBytes(), StandardCharsets.UTF_8);

        @Cleanup
        var stream2 = cl.getResourceAsStream("/org/wilson/corejava/v1/ch05/corejava/title.txt");
        var title = new String(stream2.readAllBytes(), StandardCharsets.UTF_8);

        JOptionPane.showMessageDialog(null, about, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }
}
