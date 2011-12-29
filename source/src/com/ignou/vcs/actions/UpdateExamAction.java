package com.ignou.vcs.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ignou.vcs.commons.SendMailUsingAuthentication;
import com.ignou.vcs.commons.database.CommonsDatabaseActivities;
import com.ignou.vcs.exams.beans.ExamBean;
import com.ignou.vcs.exams.database.ExamsDatabaseActivities;
import com.ignou.vcs.forms.ForgotPasswordForm;
import com.ignou.vcs.forms.UpdateExamForm;

/**
 * @version 1.0
 * @author Pradeepthi S
 */
public class UpdateExamAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		UpdateExamForm updateExamForm = (UpdateExamForm) form;
		String ex = request.getParameter("examId");
		String userName = (String) request.getSession().getAttribute("userId");
		
		try 
		{
			int examId = Integer.parseInt(ex);
			ExamBean eb = new ExamBean();
			eb.setExamId(examId);
			eb.setExamName(updateExamForm.getExamName());
			eb.setQuestionIds(updateExamForm.getQuestionIds());
			eb.setMaxMarks(updateExamForm.getMaxMarks());
			eb.setPassMarks(updateExamForm.getPassMarks());
			eb.setDuration(updateExamForm.getDuration());
			eb.setApprovedBy(userName);
			eb.setApprovalDate(new Date());
			
			ExamsDatabaseActivities eda = new ExamsDatabaseActivities();
			if(userName!=null)
			{
				Boolean b = eda.createExam(eb, userName);
			}
		} catch (Exception e) 
		{

			// Report the error using the appropriate name and ID.
			errors.add("ServerError", new ActionError("error.server.error"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as
			// desired)
			forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as
			// desired)
			forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);

	}
}
