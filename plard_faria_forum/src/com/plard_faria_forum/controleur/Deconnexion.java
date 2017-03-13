package com.plard_faria_forum.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnexion
 */
public class Deconnexion extends HttpServlet {
    public static final String URL_REDIRECTION="hello";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération et destruction de la session en cours
        HttpSession session=request.getSession();
        session.invalidate();

        // Redirection vers l'index
        response.sendRedirect(URL_REDIRECTION);
    }
}