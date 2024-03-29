<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>

<%@page import="com.ignou.vcs.beans.StudentBean"%>
<%@page import="com.ignou.vcs.forms.Schedule"%>

<%@page import="com.ignou.vcs.database.VCSDatabaseActivities"%>
<%@page import="javax.sql.CommonDataSource"%>
<%@page import="com.ignou.vcs.commons.database.CommonsDatabaseActivities"%>
<%@page import="com.ignou.vcs.beans.FacultyBean"%>
<%@page import="com.ignou.vcs.beans.MgmtBean"%><html:html>
<head>
<title>bottom_box</title>
<%@page import="java.util.ArrayList"%>

<%@page import="com.ignou.vcs.commons.beans.UserBean"%>
<%@page import="com.ignou.vcs.files.beans.FileBean"%>
<%@page import="com.ignou.vcs.commons.Utilities"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<body>
		<b style="text-align: center"><u>New Discussions</u></b>
		<table cellspacing = "2" border = "0" align="center" cellpadding="0" padding="2">
<%
		String userId = (String) request.getSession().getAttribute("userId");
		VCSDatabaseActivities dbObj = new VCSDatabaseActivities();
		CommonsDatabaseActivities dbObj1 = new CommonsDatabaseActivities();
		int level = dbObj.getLevel(userId);
		ArrayList scheduleList = new ArrayList();
		if(level == 0)
		{
			StudentBean studentBean = (StudentBean)dbObj.getStudentDetails(userId);
			String courseid = studentBean.getCourseId();
			scheduleList = dbObj.getStudentSchedule(courseid,"0");
			
		}
		else
		{
			if(level == 1)
			{
				scheduleList = dbObj.getFacultySchedule("mgmt",userId);
				
			}
			else
			{
				if(level == 2)
				{
					scheduleList = dbObj.getMgmtSchedule("All",userId);
					
				}
			}		
		}		
		System.out.println("scheduleList " + scheduleList.size());
		
		for(int i = 0;i<2;i++)
		{
			Schedule scheduleForm = (Schedule)scheduleList.get(i);
			String title = scheduleForm.getTitle();
			String Date = scheduleForm.getDate();
			String Time = scheduleForm.getTime();
			String duration = scheduleForm.getDuration();
			String description = scheduleForm.getDescription();
			String organizedby = scheduleForm.getOrganizedBy(); 
			if(level == 0)
			{
				FacultyBean facultyInfo = dbObj.getFacultyDetails(organizedby);
				organizedby = facultyInfo.getName();
			}
			else
			{
				
					MgmtBean bean = dbObj.getMgmDetails(organizedby);
					String mgmtName = bean.getName();
					String designation = bean.getDesignation(); 
					organizedby = mgmtName + " ( " + designation + " )";
			}
			
			%>	
			<tr height="50">
				<td width="70px"> <a href="#"><img src="#" class="icon" align="left"></a></td>
				<td width="10px"></td>
				<td align="left" width="180px">
					<u><b><a href="#"><%= title %></b> (<%= Date %></u>)</a><br />
					<%= Time %><br />
					-- <b><%= organizedby %></b>
				</td>
			</tr>			 
			<br>		  
		<%
		}	
		
		
%>
</table>
</body>
</html:html>
