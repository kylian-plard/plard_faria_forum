package com.plard_faria_forum.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plard_faria_forum.modele.FormCreateUser;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_ERROR="error";
	public static final String ATT_MESSAGE="msg";
	public static final String VUE="/WEB-INF/createUser.jsp";
	public static final String VUEH="/WEB-INF/index.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String mdp=request.getParameter("mdp");
		System.out.println(id+' '+mdp);

		FormCreateUser form=new FormCreateUser();
		String msg="Vous devez renseigner tout les champs !";
		boolean error=true;
		if(form.connect(request)) {
			this.getServletContext().getRequestDispatcher(VUEH).forward(request, response);
		}
		else {
			request.setAttribute(ATT_MESSAGE, msg);
			request.setAttribute(ATT_ERROR, error);
			doGet(request, response);
		}
	}
}