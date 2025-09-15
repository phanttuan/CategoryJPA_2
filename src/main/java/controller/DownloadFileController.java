package controller;
	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	
	import org.apache.commons.io.IOUtils;
	
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import util.Constant;
	
	@SuppressWarnings("serial")
	@WebServlet(urlPatterns = { "/image" })
	public class DownloadFileController extends HttpServlet {
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String fileName = req.getParameter("fname");
			File file = new File(Constant.upload + File.separator + fileName);
			if(file.exists()) {
				String mimeType = getServletContext().getMimeType(file.getName());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				resp.setContentType(mimeType);
				IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		
	}