package com.plard_faria_forum.controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plard_faria_forum.modele.User;

/**
 * Servlet implementation class HelloWorld
 */

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u=new User();
		u.setNom("Nom");
		u.setPrenom("Prénom");
		request.setAttribute("u", u);

        /* Récupération de la date courante */
        LocalDateTime dt=LocalDateTime.now();

        /* Conversion de la date en String selon le format défini */
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String date = dt.format(formatter).toString();
        
        // Envoi de la date
        request.setAttribute("date", date);

		this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String mdp=request.getParameter("mdp");
		System.out.println(id+' '+mdp);
		
		String msg="OK";
		boolean error=false;
		if(id=="" || mdp=="") {
			msg="Vous devez renseigner tout les champs !";
			error=true;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("error", error);

		doGet(request, response);
	}

}
