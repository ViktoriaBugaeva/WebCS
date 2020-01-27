/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Buyer;
import entity.Car;
import entity.History;
import entity.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BuyerFacade;
import session.CarFacade;
import session.HistoryFacade;

/**
 *
 * @author vi
 */
@WebServlet(name = "WebController", urlPatterns = {
    
    "/newCar",
    "/createCar", 
    "/listBuyers",

})
public class WebController extends HttpServlet {

    @EJB
    CarFacade carFacade;
    @EJB
    BuyerFacade buyerFacade;
    @EJB
    HistoryFacade historyFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        
        //защита ресурсов
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.setAttribute("info", "У вас нет прав, войдите или зарегистрируйтесь");
            request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                        .forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("info", "У вас нет прав, войдите или зарегистрируйтесь");
            request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                        .forward(request, response);
            return;
        }
        
        if (!"admin".equals(user.getLogin())) {
            request.setAttribute("info", "У вас нет прав, войдите как ");
            request.getRequestDispatcher("/WEB-INF/showLogin.jsp")
                        .forward(request, response);
             return;
        }
        
        
                String path = request.getServletPath();
                switch (path) {
            case "/newBuyer":
                request.getRequestDispatcher("/newBuyer.jsp")
                            .forward(request, response);
                break;
            case "/createBuyer":
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String email = request.getParameter("email");
                String money = request.getParameter("money");
                Buyer buyer = new Buyer(
                            name,
                            lastname,
                            email,
                            Integer.parseInt(money)
                );
                buyerFacade.create(buyer);
                request.setAttribute("info", "Ваши данные внесены в базу данных нашего магазина. Приятных покупок");
                request.getRequestDispatcher("/index.jsp")
                            .forward(request, response);
                break;
            case "/listBuyers":
                List<Buyer> listBuyers = buyerFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/listBuyers.jsp")
                            .forward(request, response);
                break;
        
             case "/newCar":
                request.getRequestDispatcher("/newCar.jsp")
                            .forward(request, response);
                break;
            case "/createCar":
                String marka = request.getParameter("marka");
                String model = request.getParameter("model");
                String year = request.getParameter("year");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                Car car;
                car = new Car(
                            marka,
                            model,
                            Integer.parseInt(year),
                            Integer.parseInt(price),
                            Integer.parseInt(quantity));
                carFacade.create(car);
                request.setAttribute("info", "Автомобиль добавлен");
                request.getRequestDispatcher("/index.jsp")
                            .forward(request, response);
                break;
            case "/listCars":
                List<Car> listCars = carFacade.findAll();
                request.setAttribute("listCars", listCars);
                request.getRequestDispatcher("/listCars.jsp")
                            .forward(request, response);
                break;
               
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
