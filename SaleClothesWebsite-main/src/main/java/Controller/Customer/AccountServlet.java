package Controller.Customer;

import JPAConfig.JPAConfig;
import entity.CustomerEntity;
import entity.CustomeraccountEntity;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/account", name = "AccountServlet")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get current action
        String action = request.getParameter("action");
        
        String url = "/editcustomerin4.jsp";
        if (action.equals("edit")) {
            
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String[] address = request.getParameterValues("address");
            String phonenumber = request.getParameter("phonenumber");
    
            String message;
            if (firstName == null || lastName == null || address == null || phonenumber == null ||
                        firstName.isEmpty() || lastName.isEmpty() || address.length==0 || phonenumber.isEmpty()) {
                message = "Please fill out all three text boxes.";
            }
            else {
                message = null;
                HttpSession session = request.getSession();
                CustomeraccountEntity account = (CustomeraccountEntity) session.getAttribute("user");
                CustomerEntity customer = CustomerEntity.findByID(account.getCustomerId());
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setPhoneNumber(phonenumber);
                customer.setAddress(address[3]+", "+address[2]+", " + address[1]+", " +address[0]);
    
                EntityManager entityManager = JPAConfig.getEntityManager();
                entityManager.getTransaction().begin();
                entityManager.merge(customer);
                entityManager.getTransaction().commit();
                
                url = "/CustomerAccount.jsp";
            }
            
            request.setAttribute("message", message);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
