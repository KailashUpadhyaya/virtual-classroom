package com.ignou.vcs.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.ignou.vcs.commons.Utilities;
import com.ignou.vcs.files.database.FilesDatabaseActivities;
import com.ignou.vcs.forms.SolutionForm;

/**
 * @version 1.0
 * @author Pradeepthi S
 */
public class SolutionUploadAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		SolutionForm solutionForm = (SolutionForm) form;
		String fileId = request.getParameter("fileId");
		String userId = request.getParameter("userId");
		FormFile solutionFile = solutionForm.getSolutionFile();
		try {
			FilesDatabaseActivities dbObject = new FilesDatabaseActivities();
			UploadFileAction object = new UploadFileAction();

			String solutionFileName = object.getNewFileName(solutionFile,
					dbObject.getSolutionId());
			dbObject.insertSolution(fileId, userId, solutionFileName);

			String fileLocation = Utilities.STUDENT_SOLUTION_FILES_UPLOAD_PATH;
			object.saveUploadedFile(solutionFile, solutionFileName,
					fileLocation);

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as
			// desired)
			// forward = mapping.findForward(failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as
			// desired)
			forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);

	}
}
