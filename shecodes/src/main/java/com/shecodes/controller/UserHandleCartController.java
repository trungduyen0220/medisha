package com.shecodes.controller;
//package com.shecodes.controller.user;
//
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.shecodes.entity.Book;
//import com.shecodes.entity.CartItem;
//import com.shecodes.utils.DBexecute;
//
///**
// * 
// * @author ntmduyen
// *
// */
//@Controller
//public class HandleCartController extends HttpServlet {
//
//	private static final long serialVersionUID = 199042919895880972L;
//
//	/**
//	 * 
//	 * @author ntmduyen
//	 * @datetime Jul 23, 2020 - 10:59:07 AM
//	 * @param model
//	 * @param session
//	 * @return
//	 */
//	@GetMapping("/cart")
//	public String showCart(Model model, HttpSession session) {
//		if (session.getAttribute("cart") == null) {
//			model.addAttribute("empCart", "1");
//		}
//		return "myCart";
//	}
//
//	public String removeFromCart(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		int bookID = Integer.parseInt(request.getParameter("bookID"));
//		ArrayList<CartItem> cart = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
//		for (int i = 0; i < cart.size(); i++) {
//			if ((cart.get(i).getBook().getBookId() == bookID)) {
//				cart.remove(i);
//				break;
//			}
//		}
//		int xTotalPrice = 0;
//		for (int i = 0; i < cart.size(); i++) {
//			xTotalPrice += cart.get(i).getBook().getRealPrice() * cart.get(i).getQuantity();
//		}
//		String totalPrice = new DecimalFormat("#,###").format(xTotalPrice);
//		request.getSession().setAttribute("totalPrice", totalPrice);
//		request.getSession().setAttribute("cart", cart);
//		response.sendRedirect("Cart");
//		return totalPrice;
//	}
//
//	public String addCart(HttpServletRequest request, HttpServletResponse response) {
//		int bookID = Integer.parseInt(request.getParameter("bookID"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        List<CartItem> cart = new ArrayList<>();
//        boolean had = false;
//        if (request.getSession().getAttribute("cart") != null) {
//            cart = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
//        }
//        Book book = new DBexecute().getBook(bookID);
//        for (int i=0;i<cart.size();i++) {
//            if (book.getBookId() == cart.get(i).getBook().getBookId()) {
//                cart.get(i).setQuantity(quantity+cart.get(i).getQuantity());
//                had = true;
//                break;
//            }
//        }
//        if (had==false) {
//        cart.add(new CartItem(book, quantity));
//        }
//        int xTotalPrice = 0;
//        for (int i=0;i<cart.size();i++) {
//            xTotalPrice += cart.get(i).getBook().getRealPrice()*cart.get(i).getQuantity();
//        }
//        String totalPrice = new DecimalFormat("#,###").format(xTotalPrice);
//        request.getSession().setAttribute("totalPrice", totalPrice);
//        request.getSession().setAttribute("cart", cart);
//        response.sendRedirect("Home");
//	}
//}
