/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Buyer;
import entity.Car;
import entity.History;
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
import session.BuyerFacade;
import session.CarFacade;
import session.HistoryFacade;

/**
 *
 * @author vi
 */
@WebServlet(name = "WebController", urlPatterns = {
    "/showLogin",
    "/login",
    "/newCar",
    "/createCar",
    "/newBuyer",
    "/createBuyer",
    "/listCars",
    "/listBuyers",
    "/newOrderCar",
    "/createOrderCar",
    "/listBuyCars",
    "/buyCar"
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
        String path = request.getServletPath();
        switch (path) {
            case "/showLogin":
                request.getRequestDispatcher("/showLogin.jsp")
                            .forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if ("Viktoria".equals(login) && "123456".equals(password)) {
                    request.setAttribute("info", "Привет, " + login + "!");
                } else {
                    request.setAttribute("info", "Неправильный логин или пароль!");
                }
                request.getRequestDispatcher("/index.jsp")
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
        
            case "/newOrderCar":
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
                car = carFacade.find(Long.parseLong(carId));
                buyer = buyerFacade.find(Long.parseLong(buyerId));
                
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
