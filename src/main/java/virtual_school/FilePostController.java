package virtual_school;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "photouploadservlet", urlPatterns = "/photouploadservlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100, // 100 MB
        location = "/photos"
)

public class FilePostController extends HttpServlet {
    private static final String TMP_DIR_PATH = "/photos";
    private static final String REAL_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/work/Catalina/localhost/myapp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        File tmpDir = new File(REAL_PATH + TMP_DIR_PATH);

        if (!tmpDir.exists()) {
            tmpDir.mkdir();
            System.out.println("created : " + REAL_PATH + TMP_DIR_PATH);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Collection<Part> parts = req.getParts();
        final PrintWriter writer = resp.getWriter();
        if (parts.isEmpty()) {
            writer.print("No parts provided.");
        } else {
            for (Part part : parts) {
                final String submittedFileName = part.getSubmittedFileName();
                final String partName = part.getName();
                if (submittedFileName.isBlank()) {
                    writer.printf("File name is empty for %s.", partName);
                } else {
                    writer.printf(" getSubmittedFileName: %s, ", submittedFileName);
                    writer.printf(" getName: %s, ", partName);
                    part.write(partName + "_" + submittedFileName);
                    writer.printf("The file %s uploaded sucessfully.", submittedFileName);
                }
            }
        }
        writer.close();

    }
}
