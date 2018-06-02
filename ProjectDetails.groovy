
class SCMDetails
{
    String GitHubProjectName = "";
    String GitHubCredentials = "";
    String GitHubBranch = "";
    SCMDetails(String GitHubProjectName,String GitHubCredentials,String GitHubBranch)
    {
         this.GitHubBranch=GitHubBranch
        this.GitHubCredentials=GitHubCredentials
        this.GitHubProjectName=GitHubProjectName
    }

}

class ProjectDetails {
    String JobID = ""
    String Description = ""
    
    SCMDetails SCMInformation;

    ProjectDetails(String JobID,String Description,String GitHubProjectName,String GitHubCredentials,
    String GitHubBranch)
    {
        this.JobID = JobID
        this.Description = Description
        this.SCMInformation=new SCMDetails(GitHubProjectName,GitHubCredentials,GitHubBranch);
    }  
}