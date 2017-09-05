package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.Util;

public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Getting File");

		// reads input file from an absolute path
		String filePath = request.getParameter("filePath");
		if (null == filePath || !filePath.startsWith(Util.getShareDir())) {
			filePath = Util.getShareDir();
		}

		File requestedFile = new File(filePath);

		if (requestedFile.isFile()) {
//			System.out.println("IS FILE......");
			
			File downloadFile = new File(filePath);
			FileInputStream inStream = new FileInputStream(downloadFile);

			// if you want to use a relative path to context root:
			String relativePath = getServletContext().getRealPath("");
			System.out.println("relativePath = " + relativePath);

			// obtains ServletContext
			ServletContext context = getServletContext();
			
			// gets MIME type of the file
			String mimeType = context.getMimeType(filePath);
			if (mimeType == null) {
				// set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			System.out.println("MIME type: " + mimeType);
			
			// modifies response
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());

			// forces download
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// obtains response's output stream
			OutputStream outStream = response.getOutputStream();
			int bufferSize = 1024 * 8;
			System.out.println("buffer size : " + bufferSize);
			byte[] buffer = new byte[bufferSize];
			int bytesRead = -1;

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();
		} else if (requestedFile.isDirectory()) {
			
//			System.out.println("IS FOLDER......");
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			
			RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
			rd.include(request, response);
			
			pw.println("<span class = 'label label-info'>Folders</span>");
			
			pw.println("<ul class = 'list-group'>");
			
			File dir = new File(filePath);
			for (File f : dir.listFiles()) {
				String path = f.getAbsolutePath();
				String name = f.getName();
				if (f.isDirectory() && !f.getName().contains("#")) {
					
					pw.println("<li class = 'list-group-item'>");
					pw.println("<a href='downloadFile?filePath=" + path + "'>" + name + "</a><br>");
					pw.println("</li>");
				}
			}
			
			pw.println("</ul>");
			
			pw.print("<br><hr><hr><br>");
			
			pw.println("<span class = 'label label-info'>Files</span>");
			pw.println("<ul class = 'list-group'>");
			
			for (File f : dir.listFiles()) {
				String path = f.getAbsolutePath();
				String name = f.getName();
				if (f.isFile() && !f.getName().contains("#")) {
					pw.println("<li class = 'list-group-item'>");
					pw.println("<a href='downloadFile?filePath=" + path + "'>" + name + "</a><br>");
					pw.println("</li>");
				}
			}
			
			pw.println("</ul>");

		}

	}

}
