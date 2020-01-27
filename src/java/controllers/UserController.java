/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Buyer;
import entity.History;
import entity.Car;
import java.io.IOException;
import java.io.PrintWriter;
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
import session.BuyerFacade;
import session.CarFacade;
import session.HistoryFacade;

/**
 *
 * @author User
 */
@WebServlet(name = "UserController", urlPatterns = {
    "/listBuyCars",
    "/newOrderCar",
    "/createOrderCar",
    "/buyCar"
})
public class UserController extends HttpServlet {
    @EJB
    CarFacade carFacade;
    @EJB
    BuyerFacade buyerFacade;
    @EJB
    HistoryFacade historyFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/newOrderCar":
                List<Car> listCars = carFacade.findAll();
                List<Buyer> listBuyers = buyerFacade.findAll();
                listCars = carFacade.findAll();
                listBuyers = buyerFacade.findAll();
                request.setAttribute("listCars", listCars);
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/newOrderCar.jsp")
                            .forward(request, response);
                break;
            case "/createOrderCar":
                String carId = request.getParameter("carId");
                String buyerId = request.getParameter("buyerId");
                Car car = carFacade.find(Long.parseLong(carId));
                Buyer buyer = buyerFacade.find(Long.parseLong(buyerId));
                
                if (car.getCount() > 0 ) {
                    if(buyer.getMoney() - car.getPrice()>=0){
                        car.setCount(car.getCount() - 1);
                        carFacade.edit(car);
                        buyer.setMoney(buyer.getMoney() - car.getPrice());
                        buyerFacade.edit(buyer);
                        History history = new History();
                        history.setCar(car);
                        history.setBuyer(buyer);
                        history.setTakeOn(new Date());
                        historyFacade.create(history);
                        request.setAttribute("info",
                                    "Машина \""
                                    + car.getMarka()
                                    + " " + car.getModel()
                                    + "\" продана покупателю "
                                    + buyer.getName()
                                    + " " + buyer.getLastname()
                        );
                    }else{
                         request.setAttribute("info", "Недостаточно средств");
                    }

                } else {
                    request.setAttribute("info", "Все машины проданы");
                }

                request.getRequestDispatcher("/index.jsp").forward(request, response);

                break;
            case "/listBuyCars":
                List<History> listHistories = historyFacade.findBuyCar();
                request.setAttribute("listHistories", listHistories);
                request.getRequestDispatcher("/listBuyCars.jsp")
                            .forward(request, response);
                break;

            case "/buyCar":
                String historyId = request.getParameter("historyId");
                History history = historyFacade.find(Long.parseLong(historyId));
                history.setTakeOn(new Date());
                historyFacade.edit(history);
                request.setAttribute("info",
                            "Автомобиль \""
                            + history.getCar().getMarka()
                            + " " + history.getCar().getModel()
                            + "\" продан покупателю: "
                            + history.getBuyer().getName()
                            + " " + history.getBuyer().getLastname()
                );
                request.getRequestDispatcher("/index.jsp")
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
