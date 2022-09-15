package com.dronerecon.ws;

        import javax.servlet.*;
        import javax.servlet.http.*;
        import java.io.*;
        import java.net.URL;
        import java.util.*;
        import java.security.SecureRandom;

/**
 *
 * @author andrew chung
 */
public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.

        // Get the following parameters from the request object and put them into strings:
        // area_id
        // tilex
        // tiley
        // totalcols
        // totalrows
        // ##############################
        String sArea_id = request.getParameter("area_id");
        int iTilex = Integer.parseInt(request.getParameter("tilex"));
        int iTiley = Integer.parseInt(request.getParameter("tiley"));
        int iTotalCols = Integer.parseInt(request.getParameter("totalcols"));
        int iTotalRows = Integer.parseInt(request.getParameter("totalrows"));
        int r = Integer.parseInt(request.getParameter("r"));
        int g = Integer.parseInt(request.getParameter("g"));

        try {

            // Call Drone API.
            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id="+sArea_id+"&tilex="+iTilex+"&tiley="+iTiley+"&r="+r+"&g="+g);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("An error occurred in : " + ex.toString());
        }




        // ##############################
        // 2. Default value of beginning direction.

        // Set a string called sDirection to "right".
        // ##############################
        String sDirection = "right";


        // ##############################
        // 3. Calculate next drone move.

        // Determine next tile to move to.
        // Base this on current x and y.
        // Change sDirection if necessary.
        // Drone must serpentine from top left of grid back and forth down.
        // If rows are done, change sDirection to "stop".
        // ##############################
        if(iTiley%2 == 0)
        {

            //if current x is equal to total cols
            //increase current y by 1
            //set direction to "left"
            if(iTilex == iTotalCols - 1)
            {
                iTiley++;
                sDirection = "left";
            }
            else
            {
                iTilex++;
                sDirection = "right";
            }
        }
        else
        {
            //if current x is...
            if(iTilex == 0)
            {
                iTiley++;
                sDirection = "right";
            }
            else {
                iTilex--;
                sDirection = "left";
            }
        }

        if(iTiley == iTotalRows)
        {
            sDirection = "stop";
        }


        // ##############################
        // 4. Format & Return JSON string to caller.

        /* Return via out.println() a JSON string like this:
        {"area_id":"[area id from above]", "nextTileX":"[next tile x]", "nextTileY":"[next tile y]", "direction":"[direction string from above]"}
        */
        // ##############################
        String sReturnJson = "{\"area_id\":\""+ sArea_id +"\", \"nextTileX\":\""+iTilex+"\", \"nextTileY\":\""+iTiley+"\", \"direction\":\""+sDirection+"\"}";
        out.println(sReturnJson);


    }
}
