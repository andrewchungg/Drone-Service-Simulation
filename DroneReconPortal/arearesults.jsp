<%@ page import="com.dronerecon.ws.AreaGridTile" %>
<%@ page import="com.dronerecon.ws.DBManager" %>
<%@ page import="java.util.ArrayList" %>


<html>
<body>
<% 
	String sAreaid = request.getParameter("areaid");
	DBManager oDBManager = new DBManager();
	oDBManager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + oDBManager.DBLocation;
	ArrayList<AreaGridTile> lstTiles = new ArrayList<>();
	lstTiles = oDBManager.readAreaGridTiles(sAreaid);
	int tempR = 0;
	int tempx = 0;
	int tempy = 0;
	int tempG = 0;
	int tempGx = 0;
	int tempGy = 0;
	
	for(int i = 0; i < lstTiles.size(); i++)
	{
		if(lstTiles.get(i).r > tempR)
		{
		tempR = lstTiles.get(i).r;
		tempx = lstTiles.get(i).x;
		tempy = lstTiles.get(i).y;
		}
	}
	out.println("The tile with the highest R of: "+tempR+" is x: " + tempx + " y: "+ tempy);
	
	for(int i = 0; i < lstTiles.size(); i++)
	{
		
		if(lstTiles.get(i).g > tempG)
		{
			tempG = lstTiles.get(i).g;
			tempGx = lstTiles.get(i).x;
			tempGy = lstTiles.get(i).y;
		}
	}
%>
<br>
<%
	out.println("The tile with the highest G of: "+tempG+" is x: " + tempGx + " y: "+ tempGy);
	
%>

</body>
</html>
