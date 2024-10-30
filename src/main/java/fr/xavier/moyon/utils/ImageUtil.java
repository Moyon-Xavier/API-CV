package fr.xavier.moyon.utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlElement.DEFAULT;

import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/image")
public class ImageUtil {
    private static final String DEFAULT_PATH = "C:\\xampp\\tomcat\\webapps\\CV\\neige.jfif";

    public static String getImageBase64(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        path = (path == null ? DEFAULT_PATH : path);
        System.out.println(path);
        String base64Image = "";
        try {
            Path imagePath = Paths.get(path);
            byte[] imageBytes = Files.readAllBytes(imagePath);
            base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // Créer un objet JSON avec l'image encodée en base64

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return base64Image;
    }

    public static void main(String[] args) {
        System.out.println(getImageBase64(null));
        System.out.println(getImageBase64("hello"));
    }

}
