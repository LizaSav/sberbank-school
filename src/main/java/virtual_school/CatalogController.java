package virtual_school;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "catalogController", value = "/catalog")
public class CatalogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("myname","Liza");
        req.setAttribute("products", List.of("стол", "стул"));
        req.getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }
}
